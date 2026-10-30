package Lab2;

abstract class Currency {
    // 3 собственных атрибута
    protected String name;
    protected String symbol;
    protected double rateToUSD;

    public Currency(String name, String symbol, double rateToUSD) {
        this.name = name;
        this.symbol = symbol;
        this.rateToUSD = rateToUSD;
    }
    
    // Вывод для валют
    public abstract void displayDetails();

    // Конвертация в другую валюту
    public double convertTo(Currency targetCurrency, double amount) {
        double amountInUSD = amount * this.rateToUSD;
        return amountInUSD / targetCurrency.getRateToUSD();
    }

    // Красивое форматирование суммы
    public String formatAmount(double amount) {
        return String.format("%.2f %s", amount, this.symbol);
    }

    // Геттеры и сеттеры 
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }
    public double getRateToUSD() { return rateToUSD; }
    public void setRateToUSD(double rateToUSD) { this.rateToUSD = rateToUSD; }
}

class FiatCurrency extends Currency {
    // 3 собственных атрибута
    private String country;
    private String centralBank;
    private boolean isReserve;

    public FiatCurrency(String name, String symbol, double rateToUSD, String country, String centralBank, boolean isReserve) {
        super(name, symbol, rateToUSD);
        this.country = country;
        this.centralBank = centralBank;
        this.isReserve = isReserve;
    }

    @Override
    public void displayDetails() {
        System.out.println("Фиатная " + name + " (" + symbol + ") выпущен " + country);
    }

    public void calculateInflationImpact(double inflationRate) {
        double newRate = getRateToUSD() * (1 - inflationRate / 100);
        System.out.println("С учетом инфляции " + inflationRate + "%, новый курс к USD: " + newRate);
    }

    public void checkReserveStatus() {
        System.out.println("Является ли " + name + " резервной валютой: " + (isReserve ? "Да" : "Нет"));
    }

    // Геттеры и сеттеры
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getCentralBank() { return centralBank; }
    public void setCentralBank(String centralBank) { this.centralBank = centralBank; }
    public boolean isReserve() { return isReserve; }
    public void setReserve(boolean reserve) { isReserve = reserve; }
}

class CryptoCurrency extends Currency {
    // 3 собственных атрибута
    private String blockchain;
    private double marketCap;
    private String consensus;

    public CryptoCurrency(String name, String symbol, double rateToUSD, String blockchain, double marketCap, String consensus) {
        super(name, symbol, rateToUSD);
        this.blockchain = blockchain;
        this.marketCap = marketCap;
        this.consensus = consensus;
    }

    @Override
    public void displayDetails() {
        System.out.println("Криптовалюта " + name + " (" + symbol + ") на " + blockchain + " сети");
    }

    public void calculateMarketDominance(double totalMarketCap) {
        double dominance = (this.marketCap / totalMarketCap) * 100;
        System.out.printf("Доля " + name + " на рынке: %.2f%%\n", dominance);
    }

    public void validateTransaction() {
        System.out.println("Транзакция в сети " + blockchain + " подтверждена механизмом " + consensus);
    }

    // Геттеры и сеттеры
    public String getBlockchain() { return blockchain; }
    public void setBlockchain(String blockchain) { this.blockchain = blockchain; }
    public double getMarketCap() { return marketCap; }
    public void setMarketCap(double marketCap) { this.marketCap = marketCap; }
    public String getConsensus() { return consensus; }
    public void setConsensus(String consensus) { this.consensus = consensus; }
}

// Интерфейс фабрики
interface ICurrencyFactory {
    Currency createCurrency();
}

// конкретные фабрики
class FiatFactory implements ICurrencyFactory {
    // 3 атрибута
    private String apiUrl;
    private String operatorName;
    private boolean isOnline;

    public FiatFactory(String apiUrl, String operatorName, boolean isOnline) {
        this.apiUrl = apiUrl;
        this.operatorName = operatorName;
        this.isOnline = isOnline;
    }

    // 3 метода
    public void connectToApi() {
        if (isOnline) System.out.println("Подключено к " + apiUrl);
        else System.out.println("Нет соединения с " + apiUrl);
    }

    public void logAction(String action) {
        System.out.println("[LOG " + operatorName + "] " + action);
    }

    public void checkSystemHealth() {
        System.out.println("Система фиатных операций в норме.");
    }

    @Override
    public Currency createCurrency() {
        return new FiatCurrency("Доллар США", "USD", 1.0, "США", "ФРС", true);
    }

    // Геттеры и сеттеры
    public String getApiUrl() { return apiUrl; }
    public void setApiUrl(String apiUrl) { this.apiUrl = apiUrl; }
    public String getOperatorName() { return operatorName; }
    public void setOperatorName(String operatorName) { this.operatorName = operatorName; }
    public boolean isOnline() { return isOnline; }
    public void setOnline(boolean online) { isOnline = online; }
}

class CryptoFactory implements ICurrencyFactory {
    // 3 атрибута
    private String nodeUrl;
    private String walletAddress;
    private boolean isSynced;

    public CryptoFactory(String nodeUrl, String walletAddress, boolean isSynced) {
        this.nodeUrl = nodeUrl;
        this.walletAddress = walletAddress;
        this.isSynced = isSynced;
    }

    // 3 метода
    public void syncNode() {
        this.isSynced = true;
        System.out.println("Синхронизация с нодой " + nodeUrl + " завершена.");
    }

    public void printWalletStatus() {
        System.out.println("Кошелек " + walletAddress + " активен. Синхронизация: " + isSynced);
    }

    public double estimateGasFee() {
        return 0.0005;
    }

    @Override
    public Currency createCurrency() {
        if (!isSynced) syncNode();
        return new CryptoCurrency("Биткоин", "BTC", 65000.0, "Bitcoin", 1200000000000.0, "Proof of Work");
    }

    // Геттеры и сеттеры
    public String getNodeUrl() { return nodeUrl; }
    public void setNodeUrl(String nodeUrl) { this.nodeUrl = nodeUrl; }
    public String getWalletAddress() { return walletAddress; }
    public void setWalletAddress(String walletAddress) { this.walletAddress = walletAddress; }
    public boolean isSynced() { return isSynced; }
    public void setSynced(boolean synced) { isSynced = synced; }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Фабричный метод. Валюта\n");

        ICurrencyFactory fiatFactory = new FiatFactory("https://api.bank.com", "Ivan", true);
        Currency usd = fiatFactory.createCurrency();
        
        usd.displayDetails();
        System.out.println("Форматирование: " + usd.formatAmount(150.5));
        
        if (usd instanceof FiatCurrency) {
            ((FiatCurrency) usd).checkReserveStatus();
        }

        System.out.println("\n\n");

        ICurrencyFactory cryptoFactory = new CryptoFactory("http://btc-node.net", "1A1zP1...", false);
        Currency btc = cryptoFactory.createCurrency();
        
        btc.displayDetails();
        
        double btcAmount = btc.convertTo(usd, 1.0);
        System.out.println("1 " + btc.getSymbol() + " = " + usd.formatAmount(btcAmount));
        
        if (btc instanceof CryptoCurrency) {
            ((CryptoCurrency) btc).validateTransaction();
        }
    }
}