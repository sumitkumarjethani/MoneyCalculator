package architecture.view.persistence;

import architecture.model.Currency;
import java.util.Map;

public interface RatesLoader {
    
    public Map<String,Double> loadRates(Currency from);

}
