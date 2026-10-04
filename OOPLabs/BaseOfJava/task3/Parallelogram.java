package BaseOfJava.task3;

public class Parallelogram {
    private double a;
    private double b;
    private double h;

    public Parallelogram(double a, double b, double h) {
        this.a = a;
        this.b = b;
        this.h = h;
    }

    public double area() {
        return this.a * this.h;
    }

    public double perimetr() {
        return 2 * this.a + 2 * this.b;
    }
}
