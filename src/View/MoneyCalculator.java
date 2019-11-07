package View;

import Model.*;

import java.io.IOException;
import java.util.Scanner;

public class MoneyCalculator {
    
    private Money money;
    private Currency currencyTo;
    private CurrencyList currencies;
    private ExchangeRateList rates;
    
    private void input(){
        currencies = new CurrencyList();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce una cantidad: ");
        double amount = Double.parseDouble(scanner.next());
        
        //Poner excepciones por si el from o to es null ya que 
        //en el mapa no existirán y tambien para amount por si meten una string
        System.out.println("Introduce una divisa origen: ");
        Currency currencyFrom = currencies.get(scanner.next());
        money = new Money(amount,currencyFrom);
        
        System.out.println("Introduce una divisa destino: ");
        Currency aux = currencies.get(scanner.next());
        currencyTo = new Currency(aux.getName(),aux.getSymbol(),aux.getCode());
    }
    
    private void process() throws IOException{
        rates = new ExchangeRateList(money.getCurrency());
    }
    
    private void output(){
        System.out.println(money.getAmount() + " " + money.getCurrency().getSymbol() + " equivalen a: " + 
                           money.getAmount()* rates.get(currencyTo.getCode()).getRate() + " " + currencyTo.getSymbol());
    }
    
    public void control() throws IOException {
        input();
        process();
        output();
    }
}