package moneycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MoneyCalculator {
    
    private double amount;
    private double exchangeRate;
    String currency;
    
    private void input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce una cantidad: ");
        amount = Double.parseDouble(scanner.next());
        System.out.println("Introduce una Divisa: ");
        currency = scanner.next();
    }
    
    private void process() throws IOException{
        this.exchangeRate = getExchangeRate(currency,"EUR");
    }
    
    private void output(){
        System.out.println(this.amount + " " + currency + " equivalen a: " + amount*exchangeRate + " EUR");
    }
    
    private void control() throws IOException {
        input();
        process();
        output();
    }
    
    private static double getExchangeRate(String from, String to) throws IOException{
        URL url = new URL("http://free.currencyconverterapi.com/api/v5/convert?q=" + from + "_" + to + "&compact=y&apiKey=1eb50c98f4a50de7d844");
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        URLConnection connection = url.openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            String line = reader.readLine();
            String line1 = line.substring(line.indexOf(to) + 12, line.indexOf("}"));
            return Double.parseDouble(line1);
        }
    }
    
    public static void main(String[] args) throws IOException {
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.control();
    }
}