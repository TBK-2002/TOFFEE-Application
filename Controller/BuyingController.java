/**
 * BuyingController
 */
package Controller;

import javax.security.auth.login.AccountNotFoundException;
import ModelsClasses.Account;
import ModelsClasses.ProductRelatedModels.Product;
import ViewClasses.CartView;

public class BuyingController {
    /**
     * account is the account of the user
     */
    private Account account;
    /**
     * cartView is the view of the cart
     */
    private CartView cartView;
    /**
     * toffee Controller
     */
    private TOFFEE toffee;

    /**
     * Constructor for BuyingController
     * @param account
     * @param toffee
     */
    public BuyingController(Account account, TOFFEE toffee) {
        this.account = account;
        this.cartView = new CartView(account, this);
        this.toffee = toffee;
    };

    /**
     * viewCart is the function to view the cart
     */

    public void viewCart() {
        cartView.viewCart();
    }

    /**
     * add to cart is the function to add a product to the cart
     * @param product
     */
    public void addToCart(Product product) {
        account.addToCart(product);
    }

    /**
     * updateView is the function to update the view of the cart
     */

    public void updateView() {
        cartView.viewCart();
    }

    /**
     * viewCatalog is the function to view the catalog
     */

    public void viewCatalog() {
        toffee.viewCatalog();
    }

    /**
     * checkout is the function to checkout the cart
     */

    public void checkout() {
        cartView.checkout();
        account.clearCart();
        toffee.toJSON();
        toffee.viewCatalog();
    }
    /**
     * viewOrderHistory is the function to view the order history
     */
    public void viewOrderHistory() {
        cartView.view_order_history();
    }
}