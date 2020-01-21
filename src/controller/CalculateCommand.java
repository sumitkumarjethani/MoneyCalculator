package controller;

import model.Currency;
import model.Money;
import persistence.ExchangeRateLoader;
import swing.SwingMoneyDialog;
import swing.SwingMoneyDisplay;

import java.io.IOException;

public class CalculateCommand implements Command{

    private final SwingMoneyDialog moneyDialog;
    private final SwingMoneyDisplay moneyDisplay;
    private final ExchangeRateLoader rloader;
    
    public CalculateCommand(SwingMoneyDialog moneyDialog, SwingMoneyDisplay moneyDisplay, ExchangeRateLoader rloader){
        this.moneyDialog = moneyDialog;
        this.moneyDisplay = moneyDisplay;
        this.rloader = rloader;
    }
    
    @Override
    public void toExecute() throws IOException{
        Money money = moneyDialog.getMoney();
        Currency to = moneyDialog.getCurrencyTo();
        String result;
        if(money != null){
            result = money.getAmount() + " " + money.getCurrency().getSymbol() + " equivalen a: " + 
                        money.getAmount()* (rloader.load(money.getCurrency(), to)).getRate()
                        + " " + to.getSymbol();
        }else{
            result = "Not a correct Amount";
        }
        moneyDisplay.display(result);
    }

    @Override
    public String name() {
        return "Calculate";
    }
    
}
