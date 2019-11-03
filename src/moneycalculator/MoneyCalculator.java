package moneycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

public class MoneyCalculator {
    
    private Money money;
    private ExchangeRate exchangeRate;
    private Currency currencyTo;
    private CurrencyList currencies;
    
    public MoneyCalculator(){
        Currency[] data = new Currency[2];
        data[0] = new Currency("Dollar",'$',"USD");
        data[1] = new Currency("Euro",'€',"EUR");
        currencies = new CurrencyList(data);
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
        URL url = new URL("http://free.currencyconverterapi.com/api/v5/convert?q=" + from.getCode() + "_" + to.getCode() + "&compact=y&apiKey=1eb50c98f4a50de7d844");
        URLConnection connection = url.openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            ExchangeRate result;
            String line = reader.readLine();
            String line1 = line.substring(line.indexOf(to.getCode()) + 12, line.indexOf("}"));
            result = new ExchangeRate(Double.parseDouble(line1),from,to,LocalDate.of(2019, Month.NOVEMBER, 3));
            return result;
        }
    }
    
    public static void main(String[] args) throws IOException {
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.control();
    }
}