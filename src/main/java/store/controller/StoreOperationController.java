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

        PromotionRepository promotionRepository = new PromotionRepository(getScanner(promotionTextPath));
        Store store = new Store(getScanner(productTextPath), promotionRepository);
        printProductInformation(store);
        Order order = setOrder(store);
        order.setMembership(InputView.askMembership());
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
                orderMap.forEach((name, count) -> {
                    // 판매할 Product 객체 리스트
                    List<Product> saleProducts = store.getSaleProduct(name, count);
                    // 프로모션 물품의 개수
                    Integer promotion = saleProducts.getFirst().getAvailableQuantity(count);
                    if (!Objects.equals(promotion, count)) {
                        System.out.printf("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)", name, count - promotion);
                        //Y이면 계속하기, N이면 끝내기
                    }
                    // Order에 판매된 new Product 추가
                    for (Product saleProduct : saleProducts) {
                        order.addProduct(saleProduct.getSoldProduct(count));
                    }
                });

                //판매한 물품은 제외하기
                orderMap.forEach((name, count) -> {
                    List<Product> soldProducts = store.getSaleProduct(name, count);
                    Integer soldCount = soldProducts.getFirst().getAvailableQuantity(count);
                    for (Product soldProduct : soldProducts) {
                        soldProduct.sold(soldCount);
                        soldCount = count - soldCount;
                    }
                });

                return order;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
