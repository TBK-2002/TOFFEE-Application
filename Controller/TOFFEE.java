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
    private Account account;
    private ArrayList<Product> products;
    private Catalog catalog;
    private Authentication authentication;

    public TOFFEE() {
        this.account = null;
        this.products = new ArrayList<Product>();
        loadProducts();
        this.catalog = new Catalog(products, this);
        this.authentication = new Authentication(this);
    }

    public void setAccount(Account account) {
        this.account = account;
        catalog.setAccount(account);
    }

    public void login() {
        authentication.login();
        catalog.viewCatalog();
    }

    public void register() {
        authentication.signUp();
        catalog.viewCatalog();
    }

    public void viewCatalog() {
        catalog.viewCatalog();
    }

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
}
