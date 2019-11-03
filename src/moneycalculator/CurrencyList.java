package moneycalculator;

import java.util.HashMap;
import java.util.Map;

public class CurrencyList {
    private Map<String,Currency> currencies;
    
    public CurrencyList(Currency[] data){
        currencies = new HashMap<String,Currency>();
        for (int i = 0; i < data.length; i++) {
            add(data[i]);        
        }
    }
    
    private void add(Currency currency){
        currencies.put(currency.getCode(), currency);
    }
    
    public Currency get(String code){
        return currencies.get(code.toUpperCase());
    }
    
}
