package main;

import swing.MainFrame;
import swing.SwingMoneyDialog;
import swing.SwingMoneyDisplay;
import controller.CalculateCommand;
import controller.RatesCommand;
import controller.ResetCommand;
import model.CurrencyList;
import persistence.CurrencyLoader;
import persistence.ExchangeRateLoader;
import files.TxtCurrencyLoader;
import persistence.RatesLoader;
import web.WebExchangeRateLoader;
import web.WebRatesLoader;
import java.io.IOException;

public class Main{
    
    public static void main(String[] args) throws IOException{
        //Modelo
        CurrencyList currencies = new CurrencyList();
        CurrencyLoader currencyLoader = new TxtCurrencyLoader("currency.txt");
        currencyLoader.load(currencies);
        ExchangeRateLoader rateLoader = new WebExchangeRateLoader();
        RatesLoader ratesLoader = new WebRatesLoader();
        //Vista y Controller
        SwingMoneyDialog moneyDialog = new SwingMoneyDialog(currencies);
        SwingMoneyDisplay moneyDisplay = new SwingMoneyDisplay();
        MainFrame mainFrame = new MainFrame();
        mainFrame.addMoneyDialog(moneyDialog);
        mainFrame.addMoneyDisplay(moneyDisplay);
        mainFrame.addCommand("Calculate",new CalculateCommand(moneyDialog,moneyDisplay,rateLoader));
        mainFrame.addCommand("Reset",new ResetCommand(moneyDialog,moneyDisplay));
        mainFrame.addCommand("Rates",new RatesCommand(moneyDialog,moneyDisplay,ratesLoader));
        
        mainFrame.execute();
    }
}