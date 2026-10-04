package Lab1;

public class MilkProduct extends Product{
    private double fatPercent;
    private int shelfLife;
    private String milkType;

    public MilkProduct(String name, double price, double weightKg,
                        double fatPercent, int shelfLife, String milkType) {
        super(name, price, weightKg);
        this.fatPercent = fatPercent;
        this.shelfLife = shelfLife;
        this.milkType = milkType;
    }

    // methods
    public boolean isFatFree() {
        return this.fatPercent <= 0.5;
    }

    public boolean isFresh(int daysSinceProduction) {
        return daysSinceProduction >= 0 && daysSinceProduction <= this.shelfLife;
    }

    public String getFatCategory() {
        if (fatPercent < 0.5) {
            return "Обезжиренный";
        } else if (fatPercent <= 5.0) {
            return "Низкой жирности";
        } else if (fatPercent <= 15.0) {
            return "Средней жирности";
        } else {
            return "Высокой жирности";
        }
    }

    // getters, setters
    public double getFatPercent() {
        return this.fatPercent; 
    }

    public void setFatPercent(double fatPercent) {
        this.fatPercent = fatPercent; 
    }

    public int getShelfLifeDays() {
        return this.shelfLife;
    }

    public void setShelfLifeDays(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getMilkType() {
        return this.milkType;
    }

    public void setMilkType(String milkType) {
        this.milkType = milkType;
    }
}


