
package Controller;

import javax.security.auth.login.AccountNotFoundException;
import ModelsClasses.Account;
import ModelsClasses.ProductRelatedModels.Product;
import ViewClasses.CartView;

public class BuyingController {
    private Account account;
    private CartView cartView;

    public BuyingController(Account account) {
        this.account = account;
        this.cartView = new CartView(account);
    };

    public void viewCart() {
        cartView.viewCart();
    }

    public void addToCart(Product product) {
        account.addToCart(product);
    }

    public  void updateView(){
        cartView.viewCart();
    }

}