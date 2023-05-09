
package Controller;

import ModelsClasses.Account;
import ModelsClasses.ProductRelatedModels.Product;
import ViewClasses.CartView;

public class BuyingController {
    private Account account;
    private CartView cartView;

    public BuyingController(Account account) {
        this.account = account;
        this.cartView = new CartView();
    };
    
    public void viewCart() {
        cartView.viewCart();
    }

    public void addToCart(Product product) {
        account.getCart().addProduct(product);
    }


}