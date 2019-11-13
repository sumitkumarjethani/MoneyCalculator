package Model.persistence;

import Model.Currency;
import Model.CurrencyList;
import Model.ExchangeRate;
import java.io.IOException;

public interface ExchangeRateLoader {
    
    public ExchangeRate load(Currency from,Currency to,CurrencyList currencies) throws IOException;
}
