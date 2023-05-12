/**
 * @file TOFFEE.java
 * @brief This file contains the Toffee Controller Class Implementation.
 */
package Controller;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import ModelsClasses.Account;
import ModelsClasses.BuyingModels.Order;
import ModelsClasses.ProductRelatedModels.Product;
import ModelsClasses.ProductRelatedModels.Type;
import ModelsClasses.ProductRelatedModels.category;
import ViewClasses.Catalog;

public class TOFFEE {
    /**
     * account: The account that is currently logged in.
     */
    private Account account;
    /**
     * products: The list of products in the system.
     */
    private ArrayList<Product> products;
    /**
     * catalog: The catalog of the system.
     */
    private Catalog catalog;
    /**
     * authentication: The authentication controller.
     */
    private Authentication authentication;
    /**
     * buyingController: The buying controller.
     */
    private BuyingController buyingController;

    /**
     * Constructor for TOFFEE class.
     */

    public TOFFEE() {
        this.account = null;
        this.products = new ArrayList<Product>();
        loadProducts();
        this.catalog = new Catalog(products, this);
        this.authentication = new Authentication(this);
    }

    /**
     * Setter for account.
     * @param account
     */

    public void setAccount(Account account) {
        this.account = account;
        catalog.setAccount(account);
        buyingController = new BuyingController(account, this);
    }

    /**
     * login: Logs in the user.
     */

    public void login() {
        authentication.login();
        catalog.viewCatalog();
    }
    /**
     * register: Registers the user.
     */

    public void register() {
        authentication.signUp();
        catalog.viewCatalog();
    }

    /**
     * viewCatalog: Views the catalog.
     */

    public void viewCatalog() {
        catalog.viewCatalog();
    }
    /**
     * viewCart: Views the cart.
     */

    public void viewCart() {
        buyingController.viewCart();
    }

    /**
     * addToCart: Adds a product to the cart.
     * @param product
     */

    public void addToCart(Product product) {
        buyingController.addToCart(product);
        authentication.toJSON();
    }

    /**
     * getAccount: Getter for account.
     * @return account
     */

    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * loadProducts: Loads the products from the json file.
     */

    public void loadProducts() {
        // load products from json file
        try {
            Scanner scanner = new Scanner(new FileReader("jsonFiles/productsJson.json"));

            // Use the nextLine() method to read the contents of the file line by line.
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }
            // Convert the StringBuilder object to a String object.
            String json = stringBuilder.toString();

            JSONArray jsonObject = new JSONArray(json);
            // JSONArray productsJson = jsonObject.getJSONArray();
            for (int i = 0; i < jsonObject.length(); i++) {
                JSONObject productJson = jsonObject.getJSONObject(i);
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
                Product product = new Product(id, price, quantity, name, category.values()[cat], description, brand,
                        discountPercentage, Type.values()[tp], sales);
                products.add(product);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * toJSON: Converts the account to json.
     */

    public void toJSON() {
        authentication.toJSON();
    }
}