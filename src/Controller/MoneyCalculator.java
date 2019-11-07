package Controller;

import Model.*;
import com.google.gson.JsonObject; 
import com.google.gson.JsonParser; 
import com.google.gson.JsonPrimitive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.Scanner;

public class MoneyCalculator {
    
    private Money money;
    private ExchangeRate exchangeRate;
    private Currency currencyTo;
    private CurrencyList currencies;
    
    public MoneyCalculator(){
        currencies = new CurrencyList();
    }
    
    private void input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce una cantidad: ");
        double amount = Double.parseDouble(scanner.next());
        
        System.out.println("Introduce una divisa origen: ");
        Currency currencyFrom = currencies.get(scanner.next());
        money = new Money(amount,currencyFrom);
        
        System.out.println("Introduce una divisa destino: ");
        Currency aux = currencies.get(scanner.next());
        currencyTo = new Currency(aux.getName(),aux.getSymbol(),aux.getCode());
    }
    
    private void process() throws IOException{
        exchangeRate = getExchangeRate(money.getCurrency(),currencyTo);
    }
    
    private void output(){
        System.out.println(money.getAmount() + " " + money.getCurrency().getSymbol() + " equivalen a: " + 
                           money.getAmount()*exchangeRate.getRate() + " " + currencyTo.getSymbol());
    }
    
    private void control() throws IOException {
        input();
        process();
        output();
    }
    
    private static ExchangeRate getExchangeRate(Currency from, Currency to) throws IOException{
        URL url = new URL("https://api.exchangeratesapi.io/latest?base=" + from.getCode());
        URLConnection connection = url.openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            ExchangeRate result = null;
            String line = reader.readLine();
            JsonParser parser = new JsonParser(); 
            JsonObject gsonObj = parser.parse(line).getAsJsonObject();
            JsonObject x = gsonObj.getAsJsonObject("rates");
            double rate = 0.0;
            for(Object key: x.keySet()){
                String codeTo = (String) key;
                if(codeTo.equals(to.getCode())){
                    JsonPrimitive ratePrimitive = x.getAsJsonPrimitive(codeTo);
                    rate = ratePrimitive.getAsDouble();
                    break;
                }
            }
            JsonPrimitive datePrimitive = gsonObj.getAsJsonPrimitive("date");
            String date =  datePrimitive.getAsString();
            result = new ExchangeRate(rate,from,to,LocalDate.of(Integer.parseInt(date.substring(0, 4)), 
                                      Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10))));
            return result;
        }
    }
    
    public static void main(String[] args) throws IOException{
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.control();
    }
}