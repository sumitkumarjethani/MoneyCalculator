package architecture.main;

import swing.*;
import commands.*;
import architecture.model.CurrencyList;
import architecture.view.*;
import architecture.view.persistence.*;
import files.TxtCurrencyLoader;
import web.*;
import java.io.IOException;

public class Main{
    
    public static void main(String[] args) throws IOException{
        //Model
        CurrencyList currencies = new CurrencyList();
        CurrencyLoader currencyLoader = new TxtCurrencyLoader("currency.txt");
        currencyLoader.load(currencies);
        ExchangeRateLoader exchangeRateLoader = new WebExchangeRateLoader();
        RatesLoader ratesLoader = new WebRatesLoader();
        
        //View
        MoneyDialog moneyDialog = new SwingMoneyDialog(currencies);
        MoneyDisplay moneyDisplay = new SwingMoneyDisplay();
        

        //MainFrame
        MainFrame mainFrame = new MainFrame();
        mainFrame.addMoneyDialog(moneyDialog);
        mainFrame.addMoneyDisplay(moneyDisplay);
        mainFrame.addCommand("Calculate",new CalculateCommand(moneyDialog,moneyDisplay,exchangeRateLoader));
        mainFrame.addCommand("Reset",new ResetCommand(moneyDialog,moneyDisplay));
        mainFrame.addCommand("Rates",new RatesCommand(moneyDialog,moneyDisplay,ratesLoader));
        mainFrame.execute();
    }
}