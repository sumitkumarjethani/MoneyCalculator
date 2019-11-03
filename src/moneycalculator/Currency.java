package moneycalculator;

public class Currency {
    private final String name;
    private final char symbol;
    private final String code;


    public Currency(String name, char symbol, String code) {
        this.name = name;
        this.symbol = symbol;
        this.code = code;
    }
    
    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getCode() {
        return code;
    }
    
}
