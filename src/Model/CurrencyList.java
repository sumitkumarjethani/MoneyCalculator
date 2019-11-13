package Model;

import java.util.HashMap;
import java.util.Map;

public class CurrencyList {
    private Map<String,Currency> currencies;

    public CurrencyList(){
        currencies = new HashMap<String,Currency>();
    }
    
    public Map<String, Currency> getCurrencies() {
        return currencies;
    }
    
    public Currency get(String code){
        return currencies.get(code.toUpperCase());
    }
    
}
