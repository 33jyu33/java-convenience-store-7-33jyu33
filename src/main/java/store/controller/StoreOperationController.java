package store.controller;

import store.model.Store;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class StoreOperationController {
    public void run() {
        Store store = new Store(getProductScanner());
    }

    private Scanner getProductScanner() {
        final String productTextPath = "C:\\WoowaCourse\\java-convenience-store-7-33jyu33\\src\\main\\resources\\products.md";

        try {
            return new Scanner(new File(productTextPath));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
