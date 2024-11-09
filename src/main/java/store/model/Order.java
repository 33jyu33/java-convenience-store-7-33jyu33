package store.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {
    List<Product> soldProducts = new ArrayList<>();
    List<Product> promotionProducts = new ArrayList<>();
    List<String> freeProduct;
    boolean membership = false;

    public Order(){

    }

    public void addPromotionProduct(Product product){
        promotionProducts.add(product);
    }

    public void addProduct(Product product){
        soldProducts.add(product);
    }

    public void setMembership(boolean member){
        membership = member;
    }

    public String getProducts(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Product product: soldProducts){
            stringBuilder.append(product.getProductReceipt());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String getPromotion(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Product product: promotionProducts){
            stringBuilder.append(product.getPromotionReceipt());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
