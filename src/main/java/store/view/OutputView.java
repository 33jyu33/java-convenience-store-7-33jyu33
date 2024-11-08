package store.view;

import store.constant.StoreGuide;
import store.model.Product;
import store.model.Store;

public class OutputView {
    public static void productInformation(Store store){
        System.out.println(StoreGuide.START_STORE.getContext());
        changeLine();
        System.out.println(store.getProductInformation());
    }

    public static void changeLine(){
        System.out.println();
    }
}
