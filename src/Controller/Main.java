package Controller;

import Model.CurrencyList;
import Model.persistence.CurrencyLoader;
import Model.persistence.ExchangeRateLoader;
import Model.persistence.File.Txt.TxtCurrencyLoader;
import Model.persistence.Web.WebExchangeRateLoader;
import View.*;
import java.io.IOException;

public class Main{
    
    public static void main(String[] args) throws IOException{
        CurrencyList currencies = new CurrencyList();
        CurrencyLoader cloader = new TxtCurrencyLoader("currency.txt");
        cloader.load(currencies);
        ExchangeRateLoader rloader = new WebExchangeRateLoader();
        MainFrame mainFrame = new MainFrame(currencies,rloader);
        mainFrame.addCommand("Calculate",new CalculateCommand(mainFrame.getMyMoneyDialog(),mainFrame.getMyMoneyDisplay(),
                                                              mainFrame.getRloader(),mainFrame.getCurrencies()));
        mainFrame.addCommand("Reset",new ResetCommand(mainFrame.getMyMoneyDialog(),mainFrame.getMyMoneyDisplay()));
        mainFrame.execute();
        
        //MoneyCalculator moneyCalculator = new MoneyCalculator();
        //moneyCalculator.control();
    }
}