package View;

import Model.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.JsonObject; 
import com.google.gson.JsonParser; 
import com.google.gson.JsonPrimitive;
import java.io.IOException;



public class TxtCurrencyLoader extends FileCurrencyLoader {
    
    public TxtCurrencyLoader(String filepath){
        super(filepath);
    }
    
    @Override
    public void load(Map<String,Currency> map){
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
            map.put(base, add);
        }
    }
    
    private String convertFileToString(){
        String result = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File(this.filepath)));
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
