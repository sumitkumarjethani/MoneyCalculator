package View;

import Model.*;
import java.io.IOException;
import java.util.Map;

public interface ExchangeRateLoader {
    
    public Map<String,ExchangeRate> load(Currency from) throws IOException;
}
