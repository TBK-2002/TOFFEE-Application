/**
 * This class is a subclass of Payment class.
 */
package ModelsClasses.PaymentPack;

import java.util.Scanner;

public class cashOnDelivery extends Payment {
    /**
     * This method is used to pay for the order.
     */
    public void pay() {
        choosePhoneNumber();
    }

    /**
     * This method is used to choose the phone number of the customer.
     */

    private void choosePhoneNumber() {
        System.out.print("\033\143");
        System.out.println("Enter your phone number: ");
        Scanner scanner = new Scanner(System.in);
        String phoneNumber = scanner.nextLine();
        System.out.println("Your phone number is: " + phoneNumber);
    }
}