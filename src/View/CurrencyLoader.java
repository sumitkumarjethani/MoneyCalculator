package View;

import Model.*;
import java.util.Map;

public interface CurrencyLoader {
    
    public Map<String,Currency> load();
}
