/**
 * Class Order
 */
package ModelsClasses.BuyingModels;

import java.util.ArrayList;
import ModelsClasses.PaymentPack.Payment;
import ModelsClasses.ProductRelatedModels.Product;

public class Order {
    /**
     * Address of the order
     */
    private String address;
    /**
     * Payment method of the order
     */
    Payment payment;
    /**
     * List of products in the order
     */
    private ArrayList<Product> orderItems;
    /**
     * List of secondary payments
     */
    private ArrayList<Payment> Secondary_payments;

    /**
     * setter for payment method
     * @param payment
     */

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    /**
     * setter for address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * constructor for order
     * @param orderItems
     */

    public Order(ArrayList<Product> orderItems) {
        this.orderItems = orderItems;
    }

    /**
     * constructor for order
     * @param address
     * @param payment
     * @param orderItems
     * @param secondary_payments
     */

    public Order(String address, Payment payment, ArrayList<Product> orderItems,
            ArrayList<Payment> secondary_payments) {
        this.address = address;
        this.payment = payment;
        this.orderItems = orderItems;
        Secondary_payments = secondary_payments;
    }

    /**
     * constructor for order
     * @param address
     * @param payment
     * @param orderItems
     */

    public Order(String address, Payment payment, ArrayList<Product> orderItems) {

        this.address = address;
        this.payment = payment;
        this.orderItems = orderItems;
    }

    /**
     * getter for address
     * @return address
     */

    public String getAddress() {
        return address;
    }

    /**
     * getter for payment method
     * @return payment method
     */

    public Payment getPayment() {
        return payment;
    }

    /**
     * getter for order items
     * @return order items
     */

    public ArrayList<Product> getOrderItems() {
        return orderItems;
    }


}
