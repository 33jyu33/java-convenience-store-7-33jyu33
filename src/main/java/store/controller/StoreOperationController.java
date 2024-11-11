package store.controller;

import store.model.Order;
import store.model.Product;
import store.model.PromotionRepository;
import store.model.Store;
import store.view.InputView;
import store.view.OutputView;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class StoreOperationController {
    public void run() {
        final String productTextPath = "C:\\WoowaCourse\\java-convenience-store-7-33jyu33\\src\\main\\resources\\products.md";
        final String promotionTextPath = "C:\\WoowaCourse\\java-convenience-store-7-33jyu33\\src\\main\\resources\\promotions.md";
        // 재고, 프로모션 등록
        PromotionRepository promotionRepository = new PromotionRepository(getScanner(promotionTextPath));
        Store store = new Store(getScanner(productTextPath), promotionRepository);

        // 재고 출력
        printProductInformation(store);

        // 주문 내역 저장
        Order order = setOrder(store);
        order.setMembership(InputView.askMembership());
        OutputView.receipt(order.getProducts(), order.getPromotion(), order.getResult());
    }

    private Scanner getScanner(String path) {
        try {
            return new Scanner(new File(path));
        } catch (IOException e) {
            throw new IllegalArgumentException("스캐너 사용할 수 없다 욘석아");
        }
    }

    private void printProductInformation(Store store) {
        OutputView.productInformation(store);
    }

    private Order setOrder(Store store) {
        while (true) {
            Order order = new Order();
            try {
                HashMap<String, Integer> orderMap = InputView.getOrder();
                // [콜라-3],[사이다-10]
                orderMap.forEach((name, count) -> {
                    // 판매할 Product 객체
                    Product saleProduct = store.getSaleProduct(name, count);
                    // 프로모션 적용 물품의 개수
                    Integer promotionProductQuantity = saleProduct.availableProductQuantity(count);
                    if (promotionProductQuantity!=null && !Objects.equals(promotionProductQuantity, count)) {
                        if(!InputView.checkNotApplyPromotion(name, count - promotionProductQuantity)) return;
                    }

                    //Order에 판매된 프로모션 new Product 추가
                    order.addProduct(saleProduct.getSoldProduct(count));

                    // 판매한 물품 제외하기
                    saleProduct.sold(count);
                });
                return order;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
