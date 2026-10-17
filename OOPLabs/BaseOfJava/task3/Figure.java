package BaseOfJava.task3;

abstract class Figure {
    private String name;

    public Figure(String name) {
        this.name = name;
    }

    abstract double area();

    abstract double perimetr();

    public String getName() {
        return this.name;
    }
}



