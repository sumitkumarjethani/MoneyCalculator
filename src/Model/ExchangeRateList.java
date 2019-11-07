package Model;

import View.*;
import java.util.Map;

public class ExchangeRateList {
    private Map<String,ExchangeRate> rates;
    private WebExchangeRateLoader loader;
    
    public ExchangeRateList(Currency from){
        loader = new WebExchangeRateLoader();
        rates = loader.load(from);
    }
    
    public ExchangeRate get(String to){
        return rates.get(to.toUpperCase());
    }
}
