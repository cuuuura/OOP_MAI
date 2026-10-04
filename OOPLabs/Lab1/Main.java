package Lab1;

public class Main {
    public static void main(String[] args) {
        Yogurt yogurt = new Yogurt(
                "Йогурт клубничный",     
                89.90,                  
                0.5,               
                3.2,               
                14,             
                "COW",      
                "Active",    
                500,                         
                8.5                    
        );

        System.out.println(yogurt.getName());
        yogurt.setName("Йогурт со злаками");
        System.out.println(yogurt.getName());
    }
}
