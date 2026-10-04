package BaseOfJava.task3;

public class Main {
    public static void main(String[] args) {
        Square square = new Square(4);
        Rectangle rectangle = new Rectangle(2, 3);
        Circle circle = new Circle(5);
        Parallelogram parallelogram = new Parallelogram(4, 3, 1.5);
        Triangle triangle = new Triangle(2, 3, 4);

        System.out.println("Square area and perimetr: " + square.area() + ", " + square.perimetr());
        System.out.println("Circle area and perimetr: " + circle.area() + ", " + circle.perimetr());
        System.out.println("Rectangle area and perimetr: " + rectangle.area() + ", " + rectangle.perimetr());
        System.out.println("Parallelogram area and perimetr: " + parallelogram.area() + ", " + parallelogram.perimetr());
        System.out.println("Triangle area and perimetr: " + triangle.area() + ", " + triangle.perimetr());
    }
}
