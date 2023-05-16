/**
 * This class is the model class for the product object.
 */
package ModelsClasses.ProductRelatedModels;

import java.util.ArrayList;

import ModelsClasses.BuyingModels.Order;

public class Product {
    /**
     * id is the unique id of the product
     */
    private int id;
    /**
     * price is the price of the product
     */
    private Double price;
    /**
     * quantity is the quantity of the product
     */
    private int quantity;
    /**
     * name is the name of the product
     */
    private String name;
    /**
     * category is the category of the product
     */
    private category category;
    /**
     * description is the description of the product
     */
    private String description;
    /**
     * brand is the brand of the product
     */
    private String brand;
    /**
     * discountPercentage is the discount percentage of the product
     */
    private double discountPercentage;
    /**
     * type is the type of the product
     */
    private Type type;
    /**
     * sales is the list of sales of the product
     */
    private ArrayList<Order> sales;

    /**
     * This is the constructor of the product class
     * @param id
     * @param price
     * @param quantity
     * @param name
     * @param category
     * @param description
     * @param brand
     * @param discountPercentage
     * @param type
     * @param sales
     */
    public Product(int id,Double price, int quantity, String name, category category, String description, String brand,
            double discountPercentage, Type type, ArrayList<Order> sales) {
         this.id = id;   
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

    /**
     * getter for price
     * @return price (Double)
     */

    public Double getPrice() {
        return price;
    }

    /**
     * getter for quantity
     * @return quantity (int)
     */

    public int getQuantity() {
        return quantity;
    }

    /**
     * setter for quantity
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * setter for name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for price
     * @param price
     */

    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * setter for category
     * @param category
     */

    public void setCategory(category category) {
        this.category = category;
    }

    /**
     * setter for description
     * @param description
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * setter for brand
     * @param brand
     */

    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * setter for discountPercentage
     * @param discountPercentage
     */

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    /**
     * getter for name
     * @return name (String)
     */

    public String getName() {
        return name;
    }

    /**
     * getter for category
     * @return category (category)
     */

    public category getCategory() {
        return category;
    }

    /**
     * getter for description
     * @return description (String)
     */

    public String getDescription() {
        return description;
    }

    /**
     * getter for brand
     * @return brand (String)
     */

    public String getBrand() {
        return brand;
    }

    /**
     * getter for discountPercentage
     * @return discountPercentage (double)
     */

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * getter for type
     * @return type (Type)
     */

    public Type getType() {
        return type;
    }

    /**
     * getter for sales
     * @return sales (ArrayList<Order>)
     */
    public ArrayList<Order> getSales() {
        return sales;
    }

    /**
     * getter for id
     * @return id (int)
     */
    public int getId() {
        return id;
    };
}
