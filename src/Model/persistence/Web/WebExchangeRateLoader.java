package Model.persistence.Web;

import Model.Currency;
import Model.CurrencyList;
import Model.ExchangeRate;
import Model.persistence.ExchangeRateLoader;
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


public class WebExchangeRateLoader implements ExchangeRateLoader{
    
    @Override
    public ExchangeRate load(Currency from, Currency to,CurrencyList currencies){
        ExchangeRate result;
        try {
            URL url = new URL("https://api.exchangeratesapi.io/latest?base=" + from.getCode());
            URLConnection connection = url.openConnection();
            
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                String line = reader.readLine();
                JsonParser parser = new JsonParser();
                JsonObject gsonObj = parser.parse(line).getAsJsonObject();
                JsonPrimitive datePrimitive = gsonObj.getAsJsonPrimitive("date");
                String date =  datePrimitive.getAsString();
                double rate = 0;
                JsonObject x = gsonObj.getAsJsonObject("rates");
                for(Object key: x.keySet()){
                    String codeTo = (String) key;
                    if(codeTo.equals(to.getCode())){
                        JsonPrimitive ratePrimitive = x.getAsJsonPrimitive(codeTo);
                        rate = ratePrimitive.getAsDouble();
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
