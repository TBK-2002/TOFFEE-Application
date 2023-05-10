package ViewClasses;

import Controller.*;
import ModelsClasses.Account;

import java.util.Scanner;

public class AuthenticationView {
    private String email;
    private String password;

    private Authentication authController;

    public AuthenticationView(Authentication authController) {
        this.authController = authController;
    }

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

}
