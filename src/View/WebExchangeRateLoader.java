package View;

import Model.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class WebExchangeRateLoader implements ExchangeRateLoader{
    
    @Override
    public Map<String, ExchangeRate> load(Currency from){
        Map<String,ExchangeRate> result = new HashMap<String,ExchangeRate>();
        try {
            CurrencyList currencies = new CurrencyList();
            URL url = new URL("https://api.exchangeratesapi.io/latest?base=" + from.getCode());
            URLConnection connection = url.openConnection();
            
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                String line = reader.readLine();
                JsonParser parser = new JsonParser();
                JsonObject gsonObj = parser.parse(line).getAsJsonObject();
                
                JsonPrimitive datePrimitive = gsonObj.getAsJsonPrimitive("date");
                String date =  datePrimitive.getAsString();
                
                double rate;
                JsonObject x = gsonObj.getAsJsonObject("rates");
                for(Object key: x.keySet()){
                    String codeTo = (String) key;
                    JsonPrimitive ratePrimitive = x.getAsJsonPrimitive(codeTo);
                    rate = ratePrimitive.getAsDouble();
                    Currency to = currencies.get(codeTo);
                    result.put(codeTo, new ExchangeRate(rate,from,to,LocalDate.of(Integer.parseInt(date.substring(0, 4)),
                            Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10)))));
                }
                reader.close();
                return result;
            }catch (IOException ex) {
                System.out.println("ERROR BufferedReader IOException: " + ex);
            }
        }catch (MalformedURLException ex) {
            System.out.println("ERROR MalformedURLException: " + ex);
        } catch (IOException ex) {
            System.out.println("ERROR OpenConnection IOException: " + ex);
        }
        return result;
    }
    
}
