package Model;

import java.util.HashMap;
import java.util.Map;
import View.FileCurrencyLoader;
import View.TxtCurrencyLoader;

public class CurrencyList {
    private Map<String,Currency> currencies;
    private FileCurrencyLoader loader;
    
    public CurrencyList(){
        currencies = new HashMap<String,Currency>();
        loader = new TxtCurrencyLoader("currency.txt");
        loader.load(currencies);
    }
    
    private void add(Currency currency){
        currencies.put(currency.getCode(), currency);
    }
    
    public Currency get(String code){
        return currencies.get(code.toUpperCase());
    }
    
}
