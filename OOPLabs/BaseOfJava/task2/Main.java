package BaseOfJava.task2;

public class Main {
    public static void main(String[] args) {
        Vector v1 = new Vector(5, 1, 2);
        Vector v2 = new Vector(1, 3, 2);

        double res = v1.scalarPr(v2);
        Vector sum = v1.sumVectors(v2);
        Vector v3 = v2.multiplyVector(4);

        System.out.println("(v1, v2) = " + res + " \nv1 + v2 = " + 
            "(" + sum.getX() + " , " + sum.getY() + " , " + sum.getZ() + ")"
            + " \n4 * v2 = " + "(" + v3.getX() + " , " + v3.getY() + " , " + v3.getZ() + ")");
    }   
}
