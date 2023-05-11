
package Controller;

import javax.security.auth.login.AccountNotFoundException;
import ModelsClasses.Account;
import ModelsClasses.ProductRelatedModels.Product;
import ViewClasses.CartView;

public class BuyingController {
    private Account account;
    private CartView cartView;
    private TOFFEE toffee;

    public BuyingController(Account account, TOFFEE toffee) {
        this.account = account;
        this.cartView = new CartView(account, this);
        this.toffee = toffee;
    };

    public void viewCart() {
        cartView.viewCart();
    }

    public void addToCart(Product product) {
        account.addToCart(product);
    }

    public void updateView() {
        cartView.viewCart();
    }

    public void viewCatalog() {
        toffee.viewCatalog();
    }

    public void checkout() {
        cartView.checkout();
        account.clearCart();
        toffee.toJSON();
        toffee.viewCatalog();
    }
    public void viewOrderHistory() {
        cartView.view_order_history();
    }
}