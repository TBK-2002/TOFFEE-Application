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
    public HashMap<Integer, Account> accounts;
    public ArrayList<Product> products;
    public Catalog catalog;
    
    public TOFFEE(){
        this.accounts = new HashMap<Integer, Account>();
        this.products = new ArrayList<Product>();
        loadProducts();
        this.catalog = new Catalog(products);
    }

    public void viewCatalog(){
        catalog.viewCatalog();
    }


    public void loadProducts() {
        // load products from json file
        try{
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
            for(int i = 0; i < jsonObject.length(); i++){
                JSONObject productJson = jsonObject.getJSONObject(i);
                double price = productJson.getDouble("price");
                int quantity = productJson.getInt("quantity");
                String name = productJson.getString("name");
                int cat = productJson.getInt("category");
                String description = productJson.getString("description");
                String brand = productJson.getString("brand");
                double discountPercentage = productJson.getDouble("discountPercentage");
                int tp = productJson.getInt("type");
                ArrayList<Order> sales = new ArrayList<Order>();
                Product product = new Product(price, quantity, name, category.values()[cat], description, brand, discountPercentage, Type.values()[tp], sales);
                products.add(product);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}


