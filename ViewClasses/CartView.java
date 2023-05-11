package ViewClasses;

import java.util.ArrayList;
import java.util.Scanner;
import Controller.BuyingController;
import Controller.TOFFEE;
import ModelsClasses.Account;
import ModelsClasses.BuyingModels.Order;
import ModelsClasses.PaymentPack.Payment;
import ModelsClasses.PaymentPack.cashOnDelivery;
import ModelsClasses.ProductRelatedModels.Product;

public class CartView {
    private Account account;
    private Scanner scanner;
    private BuyingController buyingController;

    public CartView(Account account, BuyingController buyingController) {
        scanner = new Scanner(System.in);
        this.account = account;
        this.buyingController = buyingController;
    };

    public void setAccount(Account account) {
        this.account = account;
    };

    public void viewCart() {
        System.out.print("\033\143");
        System.out.println("Items in cart: ");
        double sum = 0;
        System.out.println("-----------");
        for (int i = 0; i < account.getCartProducts().size(); i++) {
            System.out.println(
                    account.getCartProducts().get(i).getName() + " - " + account.getCartProducts().get(i).getPrice());
            sum += account.getCartProducts().get(i).getPrice()*account.getCartProducts().get(i).getQuantity();
        }
        System.out.println("-----------");
        System.out.println("Total: " + sum);
        System.out.println("Options: ");
        System.out.println("1. Go to catalog");
        System.out.println("2. Checkout");
        System.out.println("3. Go to order history");
        int option = scanner.nextInt();
        while (option != 1 && option != 2 && option != 3) {
            System.out.println("Invalid option");
            option = scanner.nextInt();
        }
        if (option == 1) {
            buyingController.viewCatalog();
        } else if (option == 2) {
            if (account.getCartProducts().size() == 0) {
                System.out.println("Cart is empty");
                buyingController.viewCatalog();
            } else
                buyingController.checkout();
        } else {
            buyingController.viewOrderHistory();
        }
    }

    public void checkout() {
        System.out.print("\033\143");
        System.out.println("Choose Address: ");
        System.out.println("1. Current Address: " + account.getAddress());
        System.out.println("2. New Address");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        String address = "";
        Payment payment;
        Order order = new Order(account.getCartProducts());
        while (option != 1 && option != 2) {
            System.out.println("Invalid option");
            option = scanner.nextInt();
        }
        if (option == 1) {
            address = account.getAddress();
        } else if (option == 2) {
            System.out.println("Enter new address: ");
            scanner.nextLine();
            address = scanner.nextLine();
        }
        order.setAddress(address);
        System.out.println("Choose Payment Method: ");
        System.out.println("1. Cash on Delivery");
        option = scanner.nextInt();
        while (option != 1) {
            System.out.println("Invalid option");
            option = scanner.nextInt();
        }
        if (option == 1) {
            payment = new cashOnDelivery();
            order.setPayment(payment);
        }
        order.getPayment().pay();
        account.getOrderHistory().add(order);
        System.out.println("Order placed");
    }

    public void view_order_history() {
        System.out.print("\033\143");
        ArrayList<Order> orders = account.getOrderHistory();
        System.out.println("Order History: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println("Order " + (i + 1) + ": ");
            System.out.println("Address: " + orders.get(i).getAddress());
            System.out.println("Payment Method: " + "Cash on Delivery");
            System.out.println("Products: ");
            for (int j = 0; j < orders.get(i).getOrderItems().size(); j++) {
                System.out.println(orders.get(i).getOrderItems().get(j).getName() + " - "
                        + orders.get(i).getOrderItems().get(j).getPrice());
            }
            System.out.println("-----------");
        }

        System.out.println("Options: ");
        System.out.println("1. Go to catalog");
        System.out.println("2. Go to cart");
        int option = scanner.nextInt();
        while (option != 1 && option != 2) {
            System.out.println("Invalid option");
            option = scanner.nextInt();
        }
        if (option == 1) {
            buyingController.viewCatalog();
        } else if (option == 2) {
            buyingController.viewCart();
        }
    }
}
