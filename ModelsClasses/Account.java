package ModelsClasses;

import java.util.ArrayList;

import ModelsClasses.BuyingModels.Order;
import ModelsClasses.ProductRelatedModels.Product;

public class Account {
    private int id;
    private String email;
    private String password;
    private Order[] orderHistory;
    private ArrayList<Product> cartProducts;
    private int loyaltyPoints;
    private String address;
    public Account(){};
    public Account(int id,String email, String password, String address) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.orderHistory = new Order[0];
        this.cartProducts = new ArrayList<>();
        this.loyaltyPoints = 0;
        this.address = address;
    }
    public int getId() {
        return this.id;
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


}
