/**
 * @file Authentication.java
 * @brief This file contains the Authentication Controller Class Implementation.
 * @details This file contains all the functions required for the Authentication Controller
 */
package Controller;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

//import com.github.lbovolini.mapper.ObjectMapper;

import ModelsClasses.Account;
import ModelsClasses.BuyingModels.Order;
import ModelsClasses.PaymentPack.Payment;
import ModelsClasses.PaymentPack.cashOnDelivery;
import ModelsClasses.ProductRelatedModels.Product;
import ModelsClasses.ProductRelatedModels.Type;
import ModelsClasses.ProductRelatedModels.category;
import ViewClasses.AuthenticationView;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;

public class Authentication {
    /**
     * map of accounts
     */
    private HashMap<String, Account> accounts;
    /**
     * authenticationView: The authentication view.
     */

    private AuthenticationView authenticationView;
    /**
     * toffee: The toffee controller.
     */
    private TOFFEE toffee;

    /**
     * Constructor for Authentication class.
     * @param toffee The toffee controller.
     */
    public Authentication(TOFFEE toffee) {
        this.accounts = new HashMap<String, Account>();
        this.authenticationView = new AuthenticationView(this);
        this.toffee = toffee;
        loadmap();
    }

    /**
     * loadmap: loads the map of accounts from the json file.
     */
    public void loadmap() {
        try {
            Scanner scanner = new Scanner(new FileReader("jsonFiles/accounts.json"));

            // Use the nextLine() method to read the contents of the file line by line.
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }

            // Convert the StringBuilder object to a String object.
            String json = stringBuilder.toString();

            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> keys = jsonObject.keys();

            while (keys.hasNext()) {
                String key = keys.next();
                if (jsonObject.get(key) instanceof JSONObject) {
                    JSONObject accObject = jsonObject.getJSONObject(key);
                    // int id = accObject.getInt("id");
                    // String name = accObject.getString("name");
                    String email = accObject.getString("email");
                    String password = accObject.getString("password");
                    String address = accObject.getString("address");
                    double loyaltyPoints = accObject.getDouble("loyaltyPoints");
                    ArrayList<Product> cart = new ArrayList<Product>();
                    Payment payment = new cashOnDelivery();
                    JSONArray jsonCart = accObject.getJSONArray("cart");
                    for (int i = 0; i < jsonCart.length(); i++) {
                        JSONObject productJson = jsonCart.getJSONObject(i);
                        int id = productJson.getInt("id");
                        double price = productJson.getDouble("price");
                        int quantity = productJson.getInt("quantity");
                        String name = productJson.getString("name");
                        int cat = productJson.getInt("category");
                        String description = productJson.getString("description");
                        String brand = productJson.getString("brand");
                        double discountPercentage = productJson.getDouble("discountPercentage");
                        int tp = productJson.getInt("type");
                        ArrayList<Order> sales = new ArrayList<Order>();
                        Product product = new Product(id, price, quantity, name, category.values()[cat], description,
                                brand,
                                discountPercentage, Type.values()[tp], sales);
                        cart.add(product);
                    }
                    JSONArray jsonOrderHistory = accObject.getJSONArray("orderHistory");
                    ArrayList<Order> orderHistory = new ArrayList<Order>();
                    for (int i = 0; i < jsonOrderHistory.length(); i++) {
                        JSONObject orderJson = jsonOrderHistory.getJSONObject(i);
                        String address1 = orderJson.getString("address");
                        JSONArray jsonOrderItems = orderJson.getJSONArray("orderItems");
                        ArrayList<Product> orderItems = new ArrayList<Product>();
                        for (int j = 0; j < jsonOrderItems.length(); j++) {
                            JSONObject productJson = jsonOrderItems.getJSONObject(j);
                            int id1 = productJson.getInt("id");
                            double price = productJson.getDouble("price");
                            int quantity = productJson.getInt("quantity");
                            String name = productJson.getString("name");
                            int cat = productJson.getInt("category");
                            String description = productJson.getString("description");
                            String brand = productJson.getString("brand");
                            double discountPercentage = productJson.getDouble("discountPercentage");
                            int tp = productJson.getInt("type");
                            ArrayList<Order> sales = new ArrayList<Order>();
                            Product product = new Product(id1, price, quantity, name, category.values()[cat],
                                    description, brand, discountPercentage, Type.values()[tp], sales);
                            orderItems.add(product);
                        }
                        Order order = new Order(address1, payment, orderItems);
                        orderHistory.add(order);
                    }
                    Account account = new Account(email, password, address, cart, orderHistory);
                    this.accounts.put(email, account);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * sendOTP: sends an OTP to the user's email.
     * @param email
     * @return OTP
     */

    public String sendOTP(String email) {
        String to = email;
        String from = "toffeeappotp@gmail.com";
        String password = "dmqyfszeyqedwzgl";
        // Set properties for the email server
        Properties props = new Properties();
        // props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.host", "smtp.gmail.com"); // use your email server's SMTP host
        props.put("mail.smtp.port", "587"); // use your email server's SMTP port
        props.put("mail.smtp.auth", "true"); // enable SMTP authentication
        props.put("mail.smtp.starttls.enable", "true"); // enable TLS encryption
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        String otp = "";
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            for (int i = 0; i < 6; i++) {
                otp += (int) (Math.random() * 10);
            }
            message.setSubject("OTP for TOFFEE");
            message.setText("Your OTP is " + otp);
            Transport.send(message);
        } catch (Exception e) {
            System.out.println(e);
        }
        return otp;
    }

    /**
     * verifyOTP: verifies the OTP entered by the user.
     * @param email
     * @return true if the OTP is correct, false otherwise.
     */

    public Boolean verifyOTP(String email) {
        String otp1 = sendOTP(email);
        String otp = authenticationView.set_otp("");
        if (otp.equals(otp1)) {
            return true;
        }
        return false;
    }

    /**
     * checkAccount: checks if the account exists.
     * @param email
     * @return true if the account exists, false otherwise.
     */

    public Boolean checkAccount(String email) {
        Account account = this.accounts.get(email);
        if (account != null) {
            return true;
        }
        return false;
    }

    /**
     * login: logs in the user.
     */

    public void login() {
        String email = "";
        email = authenticationView.setDataforLogin();
        Account account = this.accounts.get(email);
        toffee.setAccount(account);
    }

    /**
     * validateEmail: validates the email entered by the user.
     * @param email
     * @return true if the email is valid, false otherwise.
     */
    public boolean validateEmail(String email) {
        if (email.contains("@") && email.contains(".") && this.accounts.get(email) != null) {
            return true;
        }
        return false;
    }
    /**
     * validatePassword: validates the password entered by the user.
     * @param password
     * @param email
     * @return true if the password is valid, false otherwise.
     */

    public boolean validatePassword(String password, String email) {
        if (password.length() >= 4 && this.accounts.get(email).getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    /**
     * toJson : stores accounts objects in json file.
     */

    public void toJSON() {
        try {
            File file = new File("jsonFiles/accounts.json");
            JSONObject jsonObject = new JSONObject();
            for (String key : this.accounts.keySet()) {
                Account account = this.accounts.get(key);
                JSONObject accountObject = new JSONObject();
                accountObject.put("email", account.getEmail());
                accountObject.put("password", account.getPassword());
                accountObject.put("address", account.getAddress());
                accountObject.put("loyaltyPoints", account.getLoyaltyPoints());
                JSONArray cart = new JSONArray();
                for (Product product : account.getCartProducts()) {
                    JSONObject productObject = new JSONObject();
                    productObject.put("id", product.getId());
                    productObject.put("name", product.getName());
                    productObject.put("price", product.getPrice());
                    productObject.put("quantity", product.getQuantity());
                    productObject.put("category", product.getCategory().ordinal());
                    productObject.put("description", product.getDescription());
                    productObject.put("brand", product.getBrand());
                    productObject.put("discountPercentage", product.getDiscountPercentage());
                    productObject.put("type", product.getType().ordinal());
                    cart.put(productObject);
                }
                JSONArray orderHistory = new JSONArray();
                for (Order order : account.getOrderHistory()) {
                    JSONObject orderObject = new JSONObject();
                    JSONArray products = new JSONArray();
                    for (Product product : order.getOrderItems()) {
                        JSONObject productObject = new JSONObject();
                        productObject.put("id", product.getId());
                        productObject.put("name", product.getName());
                        productObject.put("price", product.getPrice());
                        productObject.put("quantity", product.getQuantity());
                        productObject.put("category", product.getCategory().ordinal());
                        productObject.put("description", product.getDescription());
                        productObject.put("brand", product.getBrand());
                        productObject.put("discountPercentage", product.getDiscountPercentage());
                        productObject.put("type", product.getType().ordinal());
                        products.put(productObject);
                    }
                    orderObject.put("orderItems", products);
                    orderObject.put("address", order.getAddress());
                    orderHistory.put(orderObject);
                }
                accountObject.put("orderHistory", orderHistory);
                accountObject.put("cart", cart);
                jsonObject.put(account.getEmail(), accountObject);
            }
            // write json object to file
            FileWriter fileWriter = new FileWriter(file);
            String json = jsonObject.toString();
            fileWriter.write(json);
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * signUp: registers the user.
     * @return true if the user is registered, false otherwise.
     */

    public Boolean signUp() {
        String email = "";
        String password = "";
        String address = "";
        Account account = null;
        account = authenticationView.setDataforRegister(email, password, address);
        System.out.println("");
        System.out.println("Sending otp This Might take a While...");
        if (accounts.get(account.getEmail()) == null && verifyOTP(account.getEmail())) {
            this.accounts.put(email, account);
            toJSON();
            toffee.setAccount(account);
            return true;
        } else if (accounts.get(account.getEmail()) != null) {
            System.out.println("Account already exists");
            return false;
        } else {
            System.out.println("Invalid OTP");
            return false;
        }

    }
}