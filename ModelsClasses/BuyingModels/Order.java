package ModelsClasses.BuyingModels;

import java.util.ArrayList;
import ModelsClasses.PaymentPack.Payment;
import ModelsClasses.ProductRelatedModels.Product;

public class Order {
    private String address;
    Payment payment;
    private ArrayList<Product> orderItems;
    private ArrayList<Payment> Secondary_payments;

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Order(ArrayList<Product> orderItems) {
        this.orderItems = orderItems;
    }

    public Order(String address, Payment payment, ArrayList<Product> orderItems,
            ArrayList<Payment> secondary_payments) {
        this.address = address;
        this.payment = payment;
        this.orderItems = orderItems;
        Secondary_payments = secondary_payments;
    }

    public Order(String address, Payment payment, ArrayList<Product> orderItems) {

        this.address = address;
        this.payment = payment;
        this.orderItems = orderItems;
    }

    public String getAddress() {
        return address;
    }

    public Payment getPayment() {
        return payment;
    }

    public ArrayList<Product> getOrderItems() {
        return orderItems;
    }


}
