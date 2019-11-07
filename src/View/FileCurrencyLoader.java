package View;

import Model.*;
import java.util.Map;

public abstract class FileCurrencyLoader implements CurrencyLoader {
    protected String filepath;
    
    public FileCurrencyLoader(String filepath){
        this.filepath = filepath;
    }
    
    @Override
    public abstract void load(Map<String,Currency> map);
}
