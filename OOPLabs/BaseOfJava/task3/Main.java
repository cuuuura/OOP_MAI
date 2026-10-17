package BaseOfJava.task3;

public class Main {
    public static void main(String[] args) {
        Square square = new Square(4);
        Rectangle rectangle = new Rectangle(2, 3);
        Circle circle = new Circle(5);
        Parallelogram parallelogram = new Parallelogram(4, 3, 1.5);
        Triangle triangle = new Triangle(2, 3, 4);

        System.out.println(square.getName() + " area and perimetr: " + square.area() + ", " + square.perimetr());
        System.out.println(circle.getName() + " area and perimetr: " + circle.area() + ", " + circle.perimetr());
        System.out.println(rectangle.getName() + " are a and perimetr: " + rectangle.area() + ", " + rectangle.perimetr());
        System.out.println(parallelogram.getName() + " area and perimetr: " + parallelogram.area() + ", " + parallelogram.perimetr());
        System.out.println(triangle.getName() + " area and perimetr: " + triangle.area() + ", " + triangle.perimetr());
    }
}
