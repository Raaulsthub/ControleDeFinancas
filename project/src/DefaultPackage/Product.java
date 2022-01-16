package DefaultPackage;
public class Product {
    public final String name;
    public final String code;
    public final Double price;
    public final Double quantity;
    public final String measure;

    public Product(String name, String code, Double price, Double quantity, String measure) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Double getPrice() {
        return price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public String getMeasure() { return measure; }
}
