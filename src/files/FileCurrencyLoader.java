package files;

import architecture.model.CurrencyList;
import architecture.view.persistence.CurrencyLoader;


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
