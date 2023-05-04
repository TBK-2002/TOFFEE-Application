package Authentication;

import Order.Order;

public class Account {
    private String email;
    private String password;
    private Order[] orderHistory;
    private Cart cart;
    private int loyaltyPoints;
    private String address;
    public Account(String email, String password, String address) {
        this.email = email;
        this.password = password;
        this.orderHistory = new Order[0];
        this.cart = new Cart(); 
        this.loyaltyPoints = 0;
        this.address = address;
    }
}
