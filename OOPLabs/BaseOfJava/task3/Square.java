package BaseOfJava.task3;

public class Square extends Figure{
    private double a;

    public Square(double a) {
        this.a = a;
    }

    public double area() {
        return this.a * this.a;
    }

    public double perimetr() {
        return 4 * a;
    }
}
