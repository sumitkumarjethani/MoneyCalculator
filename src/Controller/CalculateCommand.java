package Controller;

import Model.Currency;
import Model.CurrencyList;
import Model.Money;
import Model.persistence.ExchangeRateLoader;
import View.MyMoneyDialog;
import View.MyMoneyDisplay;

import java.io.IOException;

public class CalculateCommand implements Command{

    private final MyMoneyDialog myMoneyDialog;
    private final MyMoneyDisplay myMoneyDisplay;
    private final ExchangeRateLoader rloader;
    private final CurrencyList currencies;
    
    public CalculateCommand(MyMoneyDialog myMoneyDialog, MyMoneyDisplay myMoneyDisplay, ExchangeRateLoader rloader,CurrencyList currencies){
        this.myMoneyDialog = myMoneyDialog;
        this.myMoneyDisplay = myMoneyDisplay;
        this.rloader = rloader;
        this.currencies = currencies;
    }
    
    @Override
    public void toExecute() throws IOException{
        Money money = myMoneyDialog.getMoney();
        Currency to = myMoneyDialog.getCurrencyTo();
        String result = "";
        if(money != null){
            result = money.getAmount() + " " + money.getCurrency().getSymbol() + " equivalen a: " + 
                        money.getAmount()* (rloader.load(money.getCurrency(), to, currencies)).getRate()
                        + " " + to.getSymbol();
        }else{
            result = "Not a correct Amount";
        }
        myMoneyDisplay.display(result);
    }

    @Override
    public String name() {
        return "Calculate";
    }
    
}
