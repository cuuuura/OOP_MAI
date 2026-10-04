package BaseOfJava.task3;

public class Circle extends Figure {
    private double r;

    public Circle(double r) {
        this.r = r;
    }

    public double area() {
        return  Math.PI * this.r * this.r;
    }

    public double perimetr() {
        return 2 * Math.PI * this.r;
    }
}
