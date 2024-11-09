package store.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {
    List<Product> soldProducts = new ArrayList<>();
    List<String> freeProduct;
    boolean Membership = false;
    public Order(){

    }

    public void addProduct(Product product){
        soldProducts.add(product);
    }
}
