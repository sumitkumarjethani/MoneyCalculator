package moneycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MoneyCalculator {
    
    private static double getExchangeRate(String from, String to) throws IOException{
        URL url = new URL("http://free.currencyconverterapi.com/api/v5/convert?q=" + from + "_" + to + "&compact=y&apiKey=1eb50c98f4a50de7d844");
        URLConnection connection = url.openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            String line = reader.readLine();
            String line1 = line.substring(line.indexOf(to) + 12, line.indexOf("}"));
            return Double.parseDouble(line1);
        }
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println("Introduzca una cantidad en Dollares: ");
        Scanner scanner = new Scanner(System.in);
        double amount = Double.parseDouble(scanner.next());
        double exchangeRate = getExchangeRate("USD","EUR");
        System.out.println(amount + " USD equivale a " + amount*exchangeRate + " EUR");
    }
    
}
