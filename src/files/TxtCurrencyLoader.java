package files;

import architecture.model.Currency;
import architecture.model.CurrencyList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import com.google.gson.JsonObject; 
import com.google.gson.JsonParser; 
import java.io.IOException;

public class TxtCurrencyLoader extends FileCurrencyLoader {
    
    public TxtCurrencyLoader(String filename){
        super(filename);
    }
    
    @Override
    public void load(CurrencyList list){
        Map<String,Currency> currencies = list.getCurrencies();
        String line = convertFileToString();
        JsonObject gsonObj = new JsonParser().parse(line).getAsJsonObject();
        for(Object key: gsonObj.keySet()){
            JsonObject info = gsonObj.get(key.toString()).getAsJsonObject();
            String name = info.getAsJsonPrimitive("name").getAsString();
            String symbol  = info.getAsJsonPrimitive("symbol").getAsString();
            Currency add = new Currency(name,symbol,key.toString());
            currencies.put(key.toString(), add);
        }
    }
    
    private String convertFileToString(){
        String result = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File(this.filename)));
            FileIteratorReader iteratorReader = new FileIteratorReader(reader);
            for (String line : iteratorReader) result = result + line;
            result = result.replaceAll(" ","");
            result = result.replaceAll("\n", "");
            reader.close();
            return result;
        } catch (FileNotFoundException ex) {
            System.out.println("BufferedReader Exception : File Not Found: " + ex);
        } catch (IOException ex){
            System.out.println("ERROR TxtCurrencyLoader: IOException " + ex);
        }
        return result;
    }
}
