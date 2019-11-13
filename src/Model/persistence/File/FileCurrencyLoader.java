package Model.persistence.File;

import Model.CurrencyList;
import Model.persistence.CurrencyLoader;


public abstract class FileCurrencyLoader implements CurrencyLoader {
    protected String filename;

    
    public FileCurrencyLoader(String filename){
        this.filename = filename;
    }
    
    @Override
    public abstract void load(CurrencyList list);
    
    public String getFilename() {
        return filename;
    }
    
}