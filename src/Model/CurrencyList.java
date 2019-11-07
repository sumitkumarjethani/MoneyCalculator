package Model;

import java.util.Map;
import View.FileCurrencyLoader;
import View.TxtCurrencyLoader;

public class CurrencyList {
    private Map<String,Currency> currencies;
    private FileCurrencyLoader loader;
    
    public CurrencyList(){
        loader = new TxtCurrencyLoader("currency.txt");
        currencies = loader.load();
    }
    
    public Currency get(String code){
        return currencies.get(code.toUpperCase());
    }
    
}
