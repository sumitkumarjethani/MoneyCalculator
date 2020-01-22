package web;

import architecture.model.Currency;
import architecture.view.persistence.RatesLoader;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class WebRatesLoader implements RatesLoader {

    @Override
    public Map<String, Double> loadRates(Currency from) {
        try {
            Map<String,Double> rates = new HashMap<>();
            URL url = new URL("https://api.exchangeratesapi.io/latest?base=" + from.getCode());
            URLConnection connection = url.openConnection();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                String line = reader.readLine();
                JsonObject x = new JsonParser().parse(line).getAsJsonObject().getAsJsonObject("rates");
                for(Object key: x.keySet()){
                    rates.put(key.toString(), x.getAsJsonPrimitive(key.toString()).getAsDouble());
                }
            }
            return rates;
        } catch (MalformedURLException ex) {
            System.out.println("Fallo en la url");
        } catch (IOException ex) {
            System.out.println("Fallo en la conexión.Intente de nuevo");
        }
        return null;
    }
    
}
