package architecture.controller;

import architecture.model.Currency;
import architecture.model.Money;
import architecture.view.*;
import architecture.view.persistence.ExchangeRateLoader;

import java.io.IOException;

public class CalculateCommand implements Command{

    private final MoneyDialog moneyDialog;
    private final MoneyDisplay moneyDisplay;
    private final ExchangeRateLoader rloader;
    
    public CalculateCommand(MoneyDialog moneyDialog, MoneyDisplay moneyDisplay, ExchangeRateLoader rloader){
        this.moneyDialog = moneyDialog;
        this.moneyDisplay = moneyDisplay;
        this.rloader = rloader;
    }
    
    @Override
    public void toExecute() throws IOException{
        Money money = moneyDialog.getMoney();
        Currency to = moneyDialog.getCurrencyTo();
        Money result = new Calculator().calculate(money, to, rloader.load(money.getCurrency(), to));
        moneyDisplay.display(money.getAmount() + " " + money.getCurrency().getSymbol() + 
                             " equivalen a: " + result.getAmount() + " " + result.getCurrency().getSymbol());
    }
}
