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
        Order order = getOrder(store);
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

    private Order getOrder(Store store) {
        while (true) {
            Order order = new Order();
            try {
                Map<String, Integer> orderMap = InputView.getOrder();
                orderMap.forEach((name, count) -> {
                    Product saleProduct = store.getSaleProduct(name, count);
                    checkPromotionalProductCount(saleProduct, count, name);
                    order.addProduct(saleProduct.getSoldProduct(count));
                    saleProduct.sold(count);
                });
                return order;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void checkPromotionalProductCount(Product saleProduct, Integer count, String name) {
        Integer promotionProductQuantity = saleProduct.availableProductQuantity(count);
        if (promotionProductQuantity != null && !Objects.equals(promotionProductQuantity, count)) {
            if (!InputView.checkNotApplyPromotion(name, count - promotionProductQuantity)) return;
        }
    }

}
