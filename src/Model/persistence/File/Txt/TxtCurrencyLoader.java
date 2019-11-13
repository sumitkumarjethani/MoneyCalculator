package Model.persistence.File.Txt;

import Model.Currency;
import Model.CurrencyList;
import Model.persistence.File.FileIteratorReader;
import Model.persistence.File.FileCurrencyLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import com.google.gson.JsonObject; 
import com.google.gson.JsonParser; 
import com.google.gson.JsonPrimitive;
import java.io.IOException;

public class TxtCurrencyLoader extends FileCurrencyLoader {
    
    public TxtCurrencyLoader(String filename){
        super(filename);
    }
    
    @Override
    public void load(CurrencyList list){
        Map<String,Currency> currencies = list.getCurrencies();
        String line = convertFileToString();
        JsonParser parser = new JsonParser(); 
        JsonObject gsonObj = parser.parse(line).getAsJsonObject();
        for(Object key: gsonObj.keySet()){
            String base = (String) key;
            JsonObject info = gsonObj.get(base).getAsJsonObject();
            JsonPrimitive namePrimitive = info.getAsJsonPrimitive("name");
            String name = namePrimitive.getAsString();
            JsonPrimitive symbolPrimitive  = info.getAsJsonPrimitive("symbol");
            String symbol = symbolPrimitive.getAsString();
            Currency add = new Currency(name,symbol,base);
            currencies.put(base, add);
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
