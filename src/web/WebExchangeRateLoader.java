package web;

import architecture.model.Currency;
import architecture.model.ExchangeRate;
import architecture.view.persistence.ExchangeRateLoader;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;


public class WebExchangeRateLoader implements ExchangeRateLoader{
    
    @Override
    public ExchangeRate load(Currency from, Currency to){
        ExchangeRate result;
        try {
            URL url = new URL("https://api.exchangeratesapi.io/latest?base=" + from.getCode());
            URLConnection connection = url.openConnection();
            
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                String line = reader.readLine();
                JsonObject gsonObj = new JsonParser().parse(line).getAsJsonObject();
                String date = gsonObj.getAsJsonPrimitive("date").getAsString();
                double rate = 0;
                JsonObject x = gsonObj.getAsJsonObject("rates");
                for(Object key: x.keySet()){
                    if(key.toString().equals(to.getCode())){
                        rate = x.getAsJsonPrimitive(key.toString()).getAsDouble();
                        break;
                    }
                }
                result = new ExchangeRate(rate,from,to,LocalDate.of(Integer.parseInt(date.substring(0, 4)),
                            Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10))));
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
        return null;
    }
}
