package BaseOfJava.task2;
public class Vector {
    private double x;
    private double y;
    private double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // methods
    public double scalarPr(Vector other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector sumVectors(Vector other) {
        Vector result = new Vector(this.x + other.x, this.y + other.y, this.z + other.z);
        return result;
        
    }

    public Vector multiplyVector(double k) {
        Vector result = new Vector(this.x * k, this.y * k, this.z * k);
        return result;
    }

    // getters, setters
    public void setX(double x) { this.x = x; }
    public double getX() { return this.x; }

    public void setY(double y) { this.y = y; }
    public double getY() { return this.y; }

    public void setZ(double z) { this.z = z; }
    public double getZ() { return this.z; }
}

