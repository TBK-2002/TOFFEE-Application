package Controller;
import java.util.ArrayList;
import java.util.HashMap;

import ModelsClasses.Account;
import ModelsClasses.Buying.Order;
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
        // load products from database
        ArrayList<Order> sales = new ArrayList<Order>();
        Product p1 = new Product(5.55, 0, "Cadbury", category.CHOCOLATE, "Milk Chocolate", "Cadbury", 0.0, Type.sealed, sales);
        Product p2 = new Product(5.55, 0, "Galaxy", category.CHOCOLATE, "Milk Chocolate", "Galaxy", 0.0, Type.sealed, sales);
        Product p3 = new Product(5.55, 0, "KitKat", category.CHOCOLATE, "Milk Chocolate", "KitKat", 0.0, Type.sealed, sales);
        products.add(p3);
        products.add(p2);
        products.add(p1);
    }
}


