package ViewClasses;

import java.util.ArrayList;

import ModelsClasses.ProductRelatedModels.Product;

public class Catalog {
    private ArrayList<Product> products;
    public Catalog(ArrayList<Product> products) {
        this.products = products;
    }
    public ArrayList<Product> filter(String name, String brand, Double price) {
        ArrayList<Product> filteredProducts = new ArrayList<Product>();
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getName().equals(name) || this.products.get(i).getBrand().equals(brand) || this.products.get(i).getPrice().equals(price)) {
                filteredProducts.add(this.products.get(i));
            }
        }
        return filteredProducts;
    }
    public ArrayList<Product> search(String name) {
        ArrayList<Product> filteredProducts = new ArrayList<Product>();
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getName().equals(name)) {
                filteredProducts.add(this.products.get(i));
            }
        }
        return filteredProducts;
    }
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
    public boolean updateItem(Product product, int quantity,String name, String brand, Double price,String description) {
        for (int i = 0; i < products.size(); i++) 
        {
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
    public void viewCatalog(){
        for(int i = 0; i < this.products.size(); i++){
            System.out.println("--------------------");
            System.out.println("Name: " + this.products.get(i).getName() + " Quantity: " + this.products.get(i).getQuantity());
            System.out.println("Brand: " + this.products.get(i).getBrand() + " Category: " + this.products.get(i).getCategory());
            System.out.println(this.products.get(i).getDescription());
            System.out.println(this.products.get(i).getPrice() + "$"  + " Discount: " + this.products.get(i).getDiscountPercentage());
            // System.out.println(this.products.get(i).getDiscountPercentage());
            System.out.println("--------------------");
        }
    }
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
