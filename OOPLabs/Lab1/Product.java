package Lab1;

public class Product {
    private String name;
    private double price;
    private double weight;

    public Product(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    // methods
    public double calculateDiscountPrice(double discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Скидка должна быть от 0 до 100");
        }
        return this.price * (1 - discountPercent / 100.0);
    }

    public void increasePrice(double percent) {
        if (percent < 0) {
            throw new IllegalArgumentException("Процент не может быть отрицательным");
        }
        this.price += this.price * percent / 100.0;
    }

    public boolean isMoreExpensiveThan(Product other) {
        return this.price > other.price;
    }

    // getter, setter
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getweight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
