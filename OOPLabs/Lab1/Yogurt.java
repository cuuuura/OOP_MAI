package Lab1;

public class Yogurt extends MilkProduct {
    private String manufacturer;
    private int volume;
    private double sugarPer100g;

    public Yogurt(String name, double price, double weightKg,
                  double fatPercent, int shelfLifeDays, String milkType,
                  String manufacturer, int volume, double sugarPer100g) {
        super(name, price, weightKg, fatPercent, shelfLifeDays, milkType);
        this.manufacturer = manufacturer;
        this.volume = volume;
        this.sugarPer100g = sugarPer100g;
    }

    // methods
    public boolean isSugarFree() {
        return this.sugarPer100g < 0.5;
    }

    public boolean isDietary() {
        return isFatFree() && isSugarFree();
    }

    public double calculateCaloriesForPackage(double caloriesPer100g) {
        return caloriesPer100g * this.volume / 100.0;
    }

    // Геттеры и сеттеры
    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getSugarPer100g() {
        return this.sugarPer100g;
    }

    public void setSugarPer100g(double sugarPer100g) {
        this.sugarPer100g = sugarPer100g;
    }
}
