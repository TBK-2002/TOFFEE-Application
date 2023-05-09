package ModelsClasses;

import java.util.ArrayList;

import ModelsClasses.BuyingModels.Order;
import ModelsClasses.ProductRelatedModels.Product;

public class Account {
    private String email;
    private String password;
    private Order[] orderHistory;
    private ArrayList<Product> cartProducts;
    private int loyaltyPoints;
    private String address;

    public Account() {
    };

    public Account(String email, String password, String address) {
        this.email = email;
        this.password = password;
        this.orderHistory = new Order[0];
        this.cartProducts = new ArrayList<>();
        this.loyaltyPoints = 0;
        this.address = address;
    }



    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Order[] getOrderHistory() {
        return this.orderHistory;
    }

    public ArrayList<Product> getCartProducts() {
        return this.cartProducts;
    }

    public int getLoyaltyPoints() {
        return this.loyaltyPoints;
    }

    public String getAddress() {
        return this.address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addToCart(Product prod) {
        cartProducts.add(prod);
    }
}
