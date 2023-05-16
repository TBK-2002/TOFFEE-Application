/**
 * @file Payment.java
 * @brief This is the abstract class for payment
 */
package ModelsClasses.PaymentPack;

public abstract class Payment {
    /**
     * @brief This is the constructor of Payment class
     */
    public Payment() {
    };

    /**
     * @brief This is the method for payment of the order
     */

    public abstract void pay();
}