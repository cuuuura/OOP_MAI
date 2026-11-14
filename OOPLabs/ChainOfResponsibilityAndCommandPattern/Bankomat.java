package ChainOfResponsibilityAndCommandPattern;

// Цепочка ответственности пример

class Currency {
    private int amount;

    public Currency(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void dispenseSum(int summ) {
        this.amount -= summ;
    }
}

interface DispenseChain {
    void setNextChain(DispenseChain nextChain);
    void dispense(Currency cur);
}

abstract class Dispenser implements DispenseChain {
    protected DispenseChain next;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.next = nextChain;
    }
}

class Dollar50Dispenser extends Dispenser {
    @Override
    public void dispense(Currency cur) {
        // Выдаем 50-ки, пока можем
        while (cur.getAmount() >= 50) {
            System.out.println("Шуршу купюрами...");
            System.out.println("Выдаю 50 долларов");
            cur.dispenseSum(50);
        }
        // Если остались деньги и есть следующий в цепочке - передаем ему
        if (cur.getAmount() > 0 && next != null) {
            next.dispense(cur);
        }
    }
}

class Dollar20Dispenser extends Dispenser {
    @Override
    public void dispense(Currency cur) {
        // Выдаем 20-ки, пока можем
        while (cur.getAmount() >= 20) {
            System.out.println("Шуршу купюрами...");
            System.out.println("Выдаю 20 долларов");
            cur.dispenseSum(20);
        }
        if (cur.getAmount() > 0 && next != null) {
            next.dispense(cur);
        }
    }
}

class Dollar10Dispenser extends Dispenser {
    @Override
    public void dispense(Currency cur) {
        // Выдаем 10-ки, пока можем
        while (cur.getAmount() >= 10) {
            System.out.println("Шуршу купюрами...");
            System.out.println("Выдаю 10 долларов");
            cur.dispenseSum(10);
        }
        // Для конца цепочки: если остались деньги, которые нельзя выдать (например, 1, 2, 3...)
        if (cur.getAmount() > 0) {
            System.out.println("10 долларов неть");
        }
    }
}

public class Bankomat {
    public static void main(String[] args) {
        DispenseChain dollar50 = new Dollar50Dispenser();
        DispenseChain dollar20 = new Dollar20Dispenser();
        DispenseChain dollar10 = new Dollar10Dispenser();
        
        dollar50.setNextChain(dollar20);
        dollar20.setNextChain(dollar10);

        Currency amount = new Currency(101);
        dollar50.dispense(amount);
    }
}