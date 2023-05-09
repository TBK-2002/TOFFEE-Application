package ModelsClasses.ProductRelatedModels;

import java.util.ArrayList;

import ModelsClasses.Buying.Order;

public class Product {
    private Double price;
    private int quantity;
    private String name;
    private category category;
    private String description;
    // private Image image;
    private String brand;
    private double discountPercentage;
    private Type type;
    private ArrayList<Order> sales;

    public Product(Double price, int quantity, String name, category category, String description, String brand, double discountPercentage, Type type, ArrayList<Order> sales) {
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.category = category;
        this.description = description;
        // this.image = image;
        this.brand = brand;
        this.discountPercentage = discountPercentage;
        this.type = type;
        this.sales = sales;
    }

    public Double getPrice() {return price;}

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public void setName(String name) {this.name = name;}

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory(category category) {this.category = category;}

    public void setDescription(String description) {this.description = description;}

    public void setBrand(String brand) {this.brand = brand;}

    public void setDiscountPercentage(double discountPercentage) {this.discountPercentage = discountPercentage;}

    public String getName() {return name;}

    public category getCategory() {return category;}

    public String getDescription() {return description;}

    // public Image getImage() {return image;}

    public String getBrand() {return brand;}

    public double getDiscountPercentage() {return discountPercentage;}

    public Type getType() {return type;}

    public ArrayList<Order> getSales() {return sales;}


}
