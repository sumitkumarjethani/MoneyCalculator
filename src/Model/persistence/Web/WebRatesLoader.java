package Model.persistence.Web;

import Model.Currency;
import Model.persistence.RatesLoader;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebRatesLoader implements RatesLoader {

    @Override
    public Map<String, Double> loadRates(Currency from) {
        try {
            Map<String,Double> rates = new HashMap<>();
            URL url = new URL("https://api.exchangeratesapi.io/latest?base=" + from.getCode());
            URLConnection connection = url.openConnection();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                String line = reader.readLine();
                JsonParser parser = new JsonParser();
                JsonObject gsonObj = parser.parse(line).getAsJsonObject();
                JsonObject x = gsonObj.getAsJsonObject("rates");
                for(Object key: x.keySet()){
                    String codeTo = (String) key;
                    JsonPrimitive ratePrimitive = x.getAsJsonPrimitive(codeTo);
                    rates.put(codeTo, ratePrimitive.getAsDouble());
                }
            }
            return rates;
        } catch (MalformedURLException ex) {
            Logger.getLogger(WebRatesLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WebRatesLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
