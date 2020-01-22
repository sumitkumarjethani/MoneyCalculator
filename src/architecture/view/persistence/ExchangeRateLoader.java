package architecture.view.persistence;

import architecture.model.Currency;
import architecture.model.ExchangeRate;
import java.io.IOException;

public interface ExchangeRateLoader {
    
    public ExchangeRate load(Currency from,Currency to) throws IOException;
    
}
