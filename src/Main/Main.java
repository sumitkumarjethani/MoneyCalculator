package Main;

import Controller.CalculateCommand;
import Controller.RatesCommand;
import Controller.ResetCommand;
import Model.CurrencyList;
import Model.persistence.CurrencyLoader;
import Model.persistence.ExchangeRateLoader;
import Model.persistence.File.Txt.TxtCurrencyLoader;
import Model.persistence.RatesLoader;
import Model.persistence.Web.WebExchangeRateLoader;
import Model.persistence.Web.WebRatesLoader;
import View.*;
import java.io.IOException;

public class Main{
    
    public static void main(String[] args) throws IOException{
        CurrencyList currencies = new CurrencyList();
        CurrencyLoader currencyLoader = new TxtCurrencyLoader("currency.txt");
        currencyLoader.load(currencies);
        ExchangeRateLoader rateLoader = new WebExchangeRateLoader();
        RatesLoader ratesLoader = new WebRatesLoader();
        MyMoneyDialog moneyDialog = new MyMoneyDialog(currencies);
        MyMoneyDisplay moneyDisplay = new MyMoneyDisplay();
        
        MainFrame mainFrame = new MainFrame();
        mainFrame.addMoneyDialog(moneyDialog);
        mainFrame.addMoneyDisplay(moneyDisplay);
        mainFrame.addCommand("Calculate",new CalculateCommand(moneyDialog,moneyDisplay,rateLoader));
        mainFrame.addCommand("Reset",new ResetCommand(moneyDialog,moneyDisplay));
        mainFrame.addCommand("Rates",new RatesCommand(moneyDialog,moneyDisplay,ratesLoader));
        
        mainFrame.execute();
    }
}