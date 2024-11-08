package store.controller;

import store.model.PromotionRepository;
import store.model.Store;
import store.view.OutputView;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class StoreOperationController {
    public void run() {
        final String productTextPath = "C:\\WoowaCourse\\java-convenience-store-7-33jyu33\\src\\main\\resources\\products.md";
        final String promotionTextPath = "C:\\WoowaCourse\\java-convenience-store-7-33jyu33\\src\\main\\resources\\promotions.md";

        PromotionRepository promotionRepository = new PromotionRepository(getScanner(promotionTextPath));
        Store store = new Store(getScanner(productTextPath), promotionRepository);

        printProductInformation(store);
    }

    private Scanner getScanner(String path) {
        try {
            return new Scanner(new File(path));
        } catch (IOException e) {
            throw new IllegalArgumentException("스캐너 사용할 수 없다 욘석아");
        }
    }

    private void printProductInformation(Store store){
        OutputView.productInformation(store);
    }

}
