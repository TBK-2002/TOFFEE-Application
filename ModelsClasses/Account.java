/**
 * Class that represents an account in the system.
 *
 */
package ModelsClasses;

import java.util.ArrayList;

import ModelsClasses.BuyingModels.Order;
import ModelsClasses.ProductRelatedModels.Product;

/**
 * Class that represents an account in the system.
 *
 */
public class Account {
    /**
     * The account's email.
     */
    private String email;
    /**
     * The account's password.
     */
    private String password;
    /**
     * The account's order history.
     */
    private ArrayList<Order> orderHistory;
    /**
     * The account's cart.
     */
    private ArrayList<Product> cartProducts;
    /**
     * The account's loyalty points.
     */
    private int loyaltyPoints;
    /**
     * The account's address.
     */
    private String address;


    public Account() {
    };
    /**
     * Constructor for Account class.
     *
     * @param email    The account's email.
     * @param password The account's password.
     * @param address  The account's address.
     */

    public Account(String email, String password, String address) {
        this.email = email;
        this.password = password;
        this.orderHistory = new ArrayList<>();
        this.cartProducts = new ArrayList<>();
        this.loyaltyPoints = 0;
        this.address = address;
    }

    /**
     * Constructor for Account class.
     *
     * @param email        The account's email.
     * @param password     The account's password.
     * @param address      The account's address.
     * @param cartProducts The account's cart.
     * @param orderHistory The account's order history.
     */
    public Account(String email, String password, String address, ArrayList<Product> cartProducts,
                   ArrayList<Order> orderHistory) {
        this.email = email;
        this.password = password;
        this.orderHistory = new ArrayList<>();
        this.cartProducts = new ArrayList<>();
        this.loyaltyPoints = 0;
        this.address = address;
        this.cartProducts = cartProducts;
        this.orderHistory = orderHistory;
    }

    /**
     * clears the cart.
     */

    public void clearCart() {
        this.cartProducts = new ArrayList<Product>();
    }
    /**
     * getter method for email attribute.
     * @return email
     */

    public String getEmail() {
        return this.email;
    }
    /**
     * getter method for password attribute.
     * @return password
     */

    public String getPassword() {
        return this.password;
    }
    /**
     * getter method for orderHistory attribute.
     * @return orderHistory
     */

    public ArrayList<Order> getOrderHistory() {
        return this.orderHistory;
    }
    /**
     * getter method for cartProducts attribute.
     * @return cartProducts
     */

    public ArrayList<Product> getCartProducts() {
        return this.cartProducts;
    }
    /**
     * getter method for loyaltyPoints attribute.
     * @return loyaltyPoints
     */

    public int getLoyaltyPoints() {
        return this.loyaltyPoints;
    }
    /**
     * getter method for address attribute.
     * @return address
     */

    public String getAddress() {
        return this.address;
    }
    /**
     * setter method for email attribute.
     *
     * @param email The email to set.
     */

    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * setter method for password attribute.
     *
     * @param password The password to set.
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * add product to cart.
     * @param prod
     */

    public void addToCart(Product prod) {
        cartProducts.add(prod);
    }
}