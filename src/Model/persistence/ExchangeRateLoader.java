package Model.persistence;

import Model.Currency;
import Model.ExchangeRate;
import java.io.IOException;

public interface ExchangeRateLoader {
    
    public ExchangeRate load(Currency from,Currency to) throws IOException;
    
}
