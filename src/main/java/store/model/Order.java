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

    public String getResult(){
        Integer total = 0;
        Integer count = 0;
        for(Product product : soldProducts){
            total += product.getPrice()*product.getQuantity();
            count += product.getQuantity();
        }
        Integer promotionDiscount = 0;
        for(Product promotionProduct : promotionProducts){
            // 1+1이면 1개 가격만큼 빼기
            promotionDiscount = promotionProduct.getPromotionDiscount();
        }
        Integer membershipDiscount = 0;
        if(membership) {
            membershipDiscount = (total-promotionDiscount)*30/100;
        }
        Integer price = total-promotionDiscount-membershipDiscount;
        return String.format("총구매액\t\t%d\t%,d\n" +
                "행사할인\t\t\t-%,d\n" +
                "멤버십할인\t\t\t-%,d\n" +
                "내실돈\t\t\t %,d",
                count, total, promotionDiscount, membershipDiscount, price
                );
    }
}
