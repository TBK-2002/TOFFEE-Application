/**
 * Class for AuthenticationView
 */
package ViewClasses;
import Controller.*;
import ModelsClasses.Account;

import java.util.Scanner;

public class AuthenticationView {
    /**
     * email: The email of the account.
     */
    private String email;
    /**
     * password: The password of the account.
     */
    private String password;

    /**
     * Authentication controller.
     */
    private Authentication authController;
    /**
     * Constructor for AuthenticationView class.
     * @param authController
     */

    public AuthenticationView(Authentication authController) {
        this.authController = authController;
    }

    /**
     * Sett data for login function in Authentication controller.
     * @return email
     */

    public String setDataforLogin() {
        String password = "";
        String email = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome back!");
        System.out.println("Please enter email:");
        email = scanner.nextLine();
        while (!authController.validateEmail(email)) {
            System.out.println("Invalid email. Please enter email again:");
            email = scanner.nextLine();
        }
        System.out.println("Enter password: ");
        password = scanner.nextLine();
        while (!authController.validatePassword(password, email)) {
            System.out.println("Invalid password. Please enter password again:");
            password = scanner.nextLine();
        }
        return email;
    }

    /**
     * Set data for register function in Authentication controller.
     * @param email
     * @param password
     * @param address
     * @return acc (Account)
     */

    public Account setDataforRegister(String email, String password, String address) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome!");
        System.out.println("Please enter email:");
        email = scanner.nextLine();
        System.out.println("Enter password: ");
        password = scanner.nextLine();
        System.out.println("Enter address: ");
        address = scanner.nextLine();
        Account acc = new Account(email, password, address);
        return acc;
    }

    /**
     * send OTP to email.
     * @param OTP
     * @return OTP (String)
     */

    public String set_otp(String OTP){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter OTP: ");
        String otp = scanner.nextLine();
        OTP = otp;
        return OTP;
    }

}
