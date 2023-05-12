/**
 * Catalog: This class is responsible for the catalog.
 */
package ViewClasses;
import java.util.ArrayList;
import java.util.Scanner;
import ModelsClasses.Account;
import ModelsClasses.ProductRelatedModels.Product;
import Controller.*;

public class Catalog {
    /**
     * products: The list of products in the system.
     */
    private ArrayList<Product> products;
    /**
     * acc: The account that is currently logged in.
     */
    private Account acc;
    /**
     * scanner: The scanner used to read input from the user.
     */
    private Scanner scanner;
    /**
     * toffee: The TOFFEE Controller .
     */
    private TOFFEE toffee;
    /**
     * Constructor for Catalog class.
     * @param products
     * @param toffee
     */

    public Catalog(ArrayList<Product> products, TOFFEE toffee) {
        this.products = products;
        acc = null;
        this.toffee = toffee;
        scanner = new Scanner(System.in);
    }
    /**
     * Setter for account.
     * @param acc
     */

    public void setAccount(Account acc) {
        this.acc = acc;
    }

    /**
     * filter: Filters the products based on the given parameters.
     * @param name
     * @param brand
     * @param price
     * @return ArrayList<Product>
     */

    public ArrayList<Product> filter(String name, String brand, Double price) {
        ArrayList<Product> filteredProducts = new ArrayList<Product>();
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getName().equals(name) || this.products.get(i).getBrand().equals(brand)
                    || this.products.get(i).getPrice().equals(price)) {
                filteredProducts.add(this.products.get(i));
            }
        }
        return filteredProducts;
    }

    /**
     * search: Searches for a product with the given name.
     * @param name
     * @return ArrayList<Product> The list of products with the given name.
     */

    public ArrayList<Product> search(String name) {
        ArrayList<Product> filteredProducts = new ArrayList<Product>();
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getName().equals(name)) {
                filteredProducts.add(this.products.get(i));
            }
        }
        return filteredProducts;
    }

    /**
     * cancelItem: Cancels the given product from the cart.
     * @param product
     * @return boolean True if the product was found and removed, false otherwise.
     */

    public boolean cancelItem(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (this.products.get(i).getName().equals(product.getName())) {
                this.products.get(i).setQuantity(0);
                System.out.println("Item removed from cart");
                return true;
            }
        }
        System.out.println("Item not found");
        return false;
    }

    /**
     * updateItem: Updates the given product with the given parameters.
     * @param product
     * @param quantity
     * @param name
     * @param brand
     * @param price
     * @param description
     * @return boolean True if the product was found and updated, false otherwise.
     */

    public boolean updateItem(Product product, int quantity, String name, String brand, Double price,
            String description) {
        for (int i = 0; i < products.size(); i++) {
            if (this.products.get(i).getName().equals(product.getName())) {
                this.products.get(i).setQuantity(quantity);
                this.products.get(i).setPrice(price);
                this.products.get(i).setName(name);
                this.products.get(i).setBrand(brand);
                this.products.get(i).setDescription(description);
                System.out.println("Item updated");
                return true;
            }
        }
        System.out.println("Item not found");
        return true;
    }

    /**
     * viewCatalog: Displays the catalog.
     */

    public void viewCatalog() {
        System.out.print("\033\143");
        for (int i = 0; i < this.products.size(); i++) {
            System.out.println("--------------------");
            System.out.println("ID: " + this.products.get(i).getId() + " Type: " + this.products.get(i).getType());
            System.out.println(
                    "Name: " + this.products.get(i).getName() + " Quantity: " + this.products.get(i).getQuantity());
            System.out.println(
                    "Brand: " + this.products.get(i).getBrand() + " Category: " + this.products.get(i).getCategory());
            System.out.println(this.products.get(i).getDescription());
            System.out.println(this.products.get(i).getPrice() + "$" + " Discount: "
                    + this.products.get(i).getDiscountPercentage());
            // System.out.println(this.products.get(i).getDiscountPercentage());
            System.out.println("--------------------");
        }
        if (acc == null) {
            System.out.println("Options: ");
            System.out.println("1. login");
            System.out.println("2. register");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            if (option == 1) {
                toffee.login();
            } else if (option == 2) {
                toffee.register();
            }
        } else {
            System.out.println("You are logged in from: " + acc.getEmail());
            System.out.println("Options: ");
            System.out.println("1. Add item to cart");
            System.out.println("2. View cart");
            System.out.println("3. log out");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            if (option == 1) {
                addToCartOption();
            } else if (option == 2) {
                toffee.viewCart();
            } else if (option == 3) {
                acc = null;
                toffee.setAccount(null);
                this.viewCatalog();
            }
        }
    }
    /**
     * addToCartOption: Adds a product to the cart.
     */

    public void addToCartOption() {
        Product prdct = null;
        while (prdct == null) {
            System.out.print("Enter product id: ");
            int id = scanner.nextInt();
            System.out.print("Enter product quantity: ");
            int quantity = scanner.nextInt();
            for (int i = 0; i < this.products.size(); i++) {
                if (this.products.get(i).getId() == id) {
                    prdct = this.products.get(i);
                }
            }
            if (prdct == null) {
                System.out.println("Product not found");
                continue;
            }
            while (quantity > prdct.getQuantity()) {
                System.out.println("Quantity not available");
                System.out.print("Enter product quantity: ");
                quantity = scanner.nextInt();
            }
            prdct.setQuantity(prdct.getQuantity()-quantity);
            Product temp = new Product(prdct.getId(), prdct.getPrice(), quantity, prdct.getName(),
                    prdct.getCategory(), prdct.getDescription(), prdct.getBrand(), prdct.getDiscountPercentage(),
                    prdct.getType(), prdct.getSales());
            
            System.out.println(temp.getName() + " is added to cart");
            toffee.addToCart(temp);
            this.viewCatalog();
        }
    }

    /**
     * addItem: Adds the given product to the cart.
     * @param product
     * @return String A message indicating whether the product was added or not.
     */

    public String addItem(Product product) {
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getName().equals(product.getName())) {
                this.products.get(i).setQuantity(this.products.get(i).getQuantity() + product.getQuantity());
                return "Item added to cart";
            }
        }
        this.products.add(product);
        return "Item added to cart";
    }
}
