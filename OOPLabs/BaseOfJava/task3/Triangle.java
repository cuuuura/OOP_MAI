package BaseOfJava.task3;

public class Triangle extends Figure{
    private double a;
    private double b;
    private double c;    

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double area() {
        double p = perimetr() / 2;
        return Math.sqrt(p*(p - a) * (p - b) * (p - c));
    }

    public double perimetr() {
        return a + b + c;
    }
}
