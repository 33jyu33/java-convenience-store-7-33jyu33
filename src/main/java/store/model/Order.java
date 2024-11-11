package store.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    List<Product> soldProducts = new ArrayList<>();
    boolean membership = false;

    public void addProduct(Product product){
        soldProducts.add(product);
    }

    public void setMembership(boolean member){
        membership = member;
    }

    public String getProducts(){
        ArrayList<String> productInformation = new ArrayList<>();
        for(Product product: soldProducts){
            productInformation.add(product.getProductReceipt());
        }
        return String.join("\n", productInformation);
    }

    public String getPromotionalProducts(){
        ArrayList<String> promotionalProductInformation = new ArrayList<>();
        for(Product product: soldProducts){
            String promotionReceipt = product.getPromotionReceipt();
            if(promotionReceipt != null) {
                promotionalProductInformation.add(promotionReceipt);
            }
        }
        return String.join("\n", promotionalProductInformation);
    }

    public String getResult(){
        Integer total = 0;
        Integer count = 0;
        Integer promotionDiscount = 0;
        Integer membershipDiscount = 0;

        for(Product product : soldProducts){
            total += product.getPrice()*product.getQuantity();
            count += product.getQuantity();
            promotionDiscount += product.getPromotionDiscount();
            membershipDiscount += product.getMembershipDiscount();
        }
        membershipDiscount = membershipDiscount * 30/100;

        if(!membership) membershipDiscount = 0;
        Integer price = total-promotionDiscount-membershipDiscount;
        return String.format("총구매액\t\t%d\t%,d\n" +
                "행사할인\t\t\t-%,d\n" +
                "멤버십할인\t\t\t-%,d\n" +
                "내실돈\t\t\t %,d",
                count, total, promotionDiscount, membershipDiscount, price
                );
    }
}
