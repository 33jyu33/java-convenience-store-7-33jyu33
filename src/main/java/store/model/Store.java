package store.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store {
    private final List<Product> stock = new ArrayList<>();

    public Store(Scanner productScanner){
        while (productScanner.hasNextLine()) {
            setStock(productScanner.nextLine());
        }
    }

    private void setStock(String product){
        stock.add(new Product(product));
    }
}
