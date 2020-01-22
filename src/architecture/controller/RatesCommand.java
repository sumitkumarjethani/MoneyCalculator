package architecture.controller;

import architecture.controller.*;
import architecture.view.MoneyDialog;
import architecture.view.MoneyDisplay;
import architecture.view.persistence.RatesLoader;
import java.io.IOException;
import java.util.Map;

public class RatesCommand implements Command {

    private final MoneyDialog moneyDialog;
    private final MoneyDisplay moneyDisplay;
    private final RatesLoader ratesLoader;

    public RatesCommand(MoneyDialog moneyDialog, MoneyDisplay moneyDisplay, RatesLoader ratesLoader){
        this.moneyDialog = moneyDialog;
        this.moneyDisplay = moneyDisplay;
        this.ratesLoader = ratesLoader;
    }
    
    @Override
    public void toExecute() throws IOException {
        moneyDisplay.display("");
        String line = "1 " + moneyDialog.getMoney().getCurrency().getCode() + " EQUIVALE A: \n\n";
        moneyDisplay.display(line);
        for (Map.Entry<String,Double> i : ratesLoader.loadRates(moneyDialog.getMoney().getCurrency()).entrySet()) {
            line = line + i.getKey() + ": " + i.getValue() + "\n";
        }
        moneyDisplay.display(line);
    } 
}
