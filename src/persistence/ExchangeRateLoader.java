package persistence;

import model.Currency;
import model.ExchangeRate;
import java.io.IOException;

public interface ExchangeRateLoader {
    
    public ExchangeRate load(Currency from,Currency to) throws IOException;
    
}
