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
        //Cambiarlo
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File(this.filepath)));
            Scanner scanner = new Scanner(reader);
            while(scanner.hasNextLine()){
                result = result + scanner.nextLine();
            }
            result = result.replaceAll(" ","");
            result = result.replaceAll("\n", "");
            scanner.close();
            return result;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TxtCurrencyLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
