public class Catalog {
    private Product[] products;
    public Catalog() {
        this.products = new Product[0];
    }
    public Product[] filter(String name, String brand, Double price) {
        Product[] filteredProducts = new Product[0];
        for (int i = 0; i < this.products.length; i++) {
            if (this.products[i].getName().equals(name) || this.products[i].getBrand().equals(brand) || this.products[i].getPrice().equals(price)) {
                filteredProducts += this.products[i];
            }
        }
        return filteredProducts;
    }
    public Product[] search(String name) {
        Product[] filteredProducts = new Product[0];
        for (int i = 0; i < this.products.length; i++) {
            if (this.products[i].getName().equals(name)) {
                filteredProducts += this.products[i];
            }
        }
        return filteredProducts;
    }
    public String cancelItem(Product product) {
        if (this.products[i].getName().equals(product.getName())) {
            this.products[i].setQuantity(0);
            return "Item removed from cart";
        }

        return "Item not found";
    }
    public String updateItem(Product product, int quantity,String name, String brand, Double price,String description) {
        if (this.products[i].getName().equals(product.getName())) {
            this.products[i].setQuantity(quantity);
            this.products[i].setPrice(price);
            this.products[i].setName(name);
            this.products[i].setBrand(brand);
            this.products[i].setDescription(description);
            return "Item updated";
        }

        return "Item not found";
    }
    public Product[] viewCatalog(){
        for(int i = 0; i < this.products.length; i++){
            System.out.println(this.products[i].getName());
            System.out.println(this.products[i].getBrand());
            System.out.println(this.products[i].getPrice());
            System.out.println(this.products[i].getDescription());
            System.out.println(this.products[i].getDiscountPercentage());
        }
    }
    public 
}

