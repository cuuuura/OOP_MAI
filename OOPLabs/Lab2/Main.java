package Lab2;

import java.util.Locale;

abstract class Currency {
    private String name;
    private String symbol;
    private double rateToUsd;

    protected Currency(String name, String symbol, double rateToUsd) {
        setName(name);
        setSymbol(symbol);
        setRateToUsd(rateToUsd);
    }

    public abstract void displayDetails();

    public double convertTo(Currency target, double amount) {
        if (target == null) {
            throw new IllegalArgumentException("Целевая валюта не задана");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма не может быть отрицательной");
        }
        return amount * rateToUsd / target.rateToUsd;
    }

    public String formatAmount(double amount) {
        return String.format(Locale.US, "%.2f %s", amount, symbol);
    }

    public boolean isStrongerThan(Currency other) {
        return this.rateToUsd > other.rateToUsd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getRateToUsd() {
        return rateToUsd;
    }

    public void setRateToUsd(double rateToUsd) {
        this.rateToUsd = rateToUsd;
    }
}

class FiatCurrency extends Currency {
    private String country;
    private String centralBank;
    private boolean reserve;

    public FiatCurrency(String name, String symbol, double rateToUsd, 
                        String country, String centralBank, boolean reserve) {
        super(name, symbol, rateToUsd);
        this.country = country;
        this.centralBank = centralBank;
        this.reserve = reserve;
    }

    @Override
    public void displayDetails() {
        System.out.println("Фиатная валюта " + getName() + " (" + getSymbol() + "), страна-эмитент: " + country + ", ЦБ: " + centralBank);
    }

    public double rateAfterInflation(double inflationPercent) {
        return getRateToUsd() * (1 - inflationPercent / 100.0);
    }

    public void checkReserveStatus() {
        System.out.println(getName() + " — резервная валюта: " + (reserve ? "да" : "нет"));
    }

    public boolean isIssuedBy(String country) {
        return this.country.equalsIgnoreCase(country);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCentralBank() {
        return centralBank;
    }

    public void setCentralBank(String centralBank) {
        this.centralBank = centralBank;
    }

    public boolean isReserve() {
        return reserve;
    }

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }
}

class CryptoCurrency extends Currency {
    private String blockchain;
    private double marketCap;
    private String consensus;

    public CryptoCurrency(String name, String symbol, double rateToUsd,
                          String blockchain, double marketCap, String consensus) {
        super(name, symbol, rateToUsd);
        this.blockchain = blockchain;
        this.marketCap = marketCap;
        this.consensus = consensus;
    }

    @Override
    public void displayDetails() {
        System.out.println("Криптовалюта " + getName() + " (" + getSymbol() + "), сеть: " + blockchain + ", консенсус: " + consensus);
    }

    public double marketDominance(double totalMarketCap) {
        if (totalMarketCap <= 0) {
            throw new IllegalArgumentException("Капитализация рынка должна быть положительной");
        }
        return marketCap / totalMarketCap * 100.0;
    }

    public void validateTransaction() {
        System.out.println("Транзакция в сети " + blockchain + " подтверждена механизмом " + consensus);
    }

    public boolean runsOn(String blockchain) {
        return this.blockchain.equalsIgnoreCase(blockchain);
    }

    public String getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(String blockchain) {
        this.blockchain = blockchain;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public String getConsensus() {
        return consensus;
    }

    public void setConsensus(String consensus) {
        this.consensus = consensus;
    }
}

abstract class CurrencyFactory {
    private String factoryName;
    private String operatorName;
    private int createdCount;

    protected CurrencyFactory(String factoryName, String operatorName) {
        this.factoryName = factoryName;
        this.operatorName = operatorName;
        this.createdCount = 0;
    }

    public abstract Currency createCurrency();

    public Currency issueCurrency() {
        Currency currency = createCurrency();
        createdCount++;
        return currency;
    }

    public void log(String action) {
        System.out.println("[LOG " + operatorName + "] " + action);
    }

    public void describeFactory() {
        System.out.println("Фабрика \"" + factoryName + "\", оператор: " + operatorName + ", выпущено валют: " + createdCount);
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public int getCreatedCount() {
        return createdCount;
    }

    public void setCreatedCount(int createdCount) {
        this.createdCount = createdCount;
    }
}

class FiatFactory extends CurrencyFactory {
    private String apiUrl;
    private String apiVersion;
    private boolean online;

    public FiatFactory(String factoryName, String operatorName, String apiUrl, String apiVersion, boolean online) {
        super(factoryName, operatorName);
        this.apiUrl = apiUrl;
        this.apiVersion = apiVersion;
        this.online = online;
    }

    public void connectToApi() {
        if (online) {
            System.out.println("Подключено к " + apiUrl + " (API " + apiVersion + ")");
        } else {
            System.out.println("Нет соединения с " + apiUrl);
        }
    }

    public void checkSystemHealth() {
        System.out.println("Система фиатных операций в норме");
    }

    public void refreshRates() {
        System.out.println("Курсы фиатных валют обновлены из " + apiUrl);
    }

    @Override
    public Currency createCurrency() {
        FiatCurrency currency = new FiatCurrency("Доллар США", "USD", 1.0, "США", "ФРС", true);
        return currency;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}

class CryptoFactory extends CurrencyFactory {
    private String nodeUrl;
    private String walletAddress;
    private boolean synced;

    public CryptoFactory(String factoryName, String operatorName, String nodeUrl, String walletAddress, boolean synced) {
        super(factoryName, operatorName);
        this.nodeUrl = nodeUrl;
        this.walletAddress = walletAddress;
        this.synced = synced;
    }

    public void syncNode() {
        synced = true;
        System.out.println("Синхронизация с нодой " + nodeUrl + " завершена");
    }

    public void printWalletStatus() {
        System.out.println("Кошелёк " + walletAddress + ": синхронизация — " + (synced ? "да" : "нет"));
    }

    public double estimateGasFee() {
        return 0.0005;
    }

    @Override
    public Currency createCurrency() {
        CryptoCurrency currency = new CryptoCurrency("Биткоин", "BTC", 65000.0, "Bitcoin", 1_200_000_000_000.0, "Proof of Work");
        return currency;
    }

    public String getNodeUrl() {
        return nodeUrl;
    }

    public void setNodeUrl(String nodeUrl) {
        this.nodeUrl = nodeUrl;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public boolean isSynced() {
        return synced;
    }

    public void setSynced(boolean synced) {
        this.synced = synced;
    }
}

public class Main {
    public static void main(String[] args) {
        CurrencyFactory fiatFactory = new FiatFactory("FiatBank Factory", "Ivan", "https://api.bank.com", "v2", true);
        Currency usd = fiatFactory.issueCurrency(); 
        
        usd.displayDetails(); 
        System.out.println("Форматирование: " + usd.formatAmount(150.5));
        fiatFactory.describeFactory();

        System.out.println("\n\n");

        CurrencyFactory cryptoFactory = new CryptoFactory("CryptoNode Factory", "Petr", "http://btc-node.net", "1A1zP1...", false);
        Currency btc = cryptoFactory.createCurrency(); 
        
        btc.displayDetails(); 
    }
}