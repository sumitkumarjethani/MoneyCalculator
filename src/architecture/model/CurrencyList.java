package architecture.model;

import java.util.HashMap;
import java.util.Map;

public class CurrencyList {
    private final Map<String,Currency> currencies;

    public CurrencyList(){
        currencies = new HashMap<>();
    }
    
    public Map<String, Currency> getCurrencies() {
        return currencies;
    }
    
    public Currency get(String code){
        return currencies.get(code.toUpperCase());
    }
    
}
