package controller;

import persistence.RatesLoader;
import view.SwingMoneyDialog;
import view.SwingMoneyDisplay;
import java.io.IOException;
import java.util.Map;

public class RatesCommand implements Command {

    private final SwingMoneyDialog moneyDialog;
    private final SwingMoneyDisplay moneyDisplay;
    private final RatesLoader ratesLoader;

    public RatesCommand(SwingMoneyDialog moneyDialog, SwingMoneyDisplay moneyDisplay, RatesLoader ratesLoader){
        this.moneyDialog = moneyDialog;
        this.moneyDisplay = moneyDisplay;
        this.ratesLoader = ratesLoader;
    }
    
    @Override
    public void toExecute() throws IOException {
        moneyDisplay.display("");
        Map<String, Double> rates = ratesLoader.loadRates(moneyDialog.getMoney().getCurrency());
        String line = "";
        for (Map.Entry<String,Double> i : rates.entrySet()) {
            line = line + i.getKey() + ": " + i.getValue() + "\n";
        }
        moneyDisplay.display(line);
    }

    @Override
    public String name() {
        return "Rates";
    }
     
}
