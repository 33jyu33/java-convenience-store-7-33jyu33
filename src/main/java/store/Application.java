package store;

import store.controller.StoreOperationController;

public class Application {
    public static void main(String[] args) {
        StoreOperationController storeOperationController = new StoreOperationController();
        storeOperationController.run();
    }
}
