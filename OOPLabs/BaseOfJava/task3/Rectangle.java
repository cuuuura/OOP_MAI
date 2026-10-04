package BaseOfJava.task3;

public class Rectangle extends Figure {
    private double a;
    private double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double area() {
        return a * b;
    }

    public double perimetr() {
        return 2 * a + 2 * b;
    }
}