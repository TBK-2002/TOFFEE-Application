package ModelsClasses.PaymentPack;

import java.util.Scanner;

public class cashOnDelivery extends Payment {
    public void pay() {
        choosePhoneNumber();
    }

    private void choosePhoneNumber() {
        System.out.print("\033\143");
        System.out.println("Enter your phone number: ");
        Scanner scanner = new Scanner(System.in);
        String phoneNumber = scanner.nextLine();
        System.out.println("Your phone number is: " + phoneNumber);
    }
}