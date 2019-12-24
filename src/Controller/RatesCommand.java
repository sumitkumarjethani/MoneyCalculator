package Controller;

import Model.persistence.RatesLoader;
import View.MyMoneyDialog;
import View.MyMoneyDisplay;
import java.io.IOException;
import java.util.Map;

public class RatesCommand implements Command {

    private final MyMoneyDialog myMoneyDialog;
    private final MyMoneyDisplay myMoneyDisplay;
    private final RatesLoader ratesLoader;

    public RatesCommand(MyMoneyDialog myMoneyDialog, MyMoneyDisplay myMoneyDisplay, RatesLoader ratesLoader){
        this.myMoneyDialog = myMoneyDialog;
        this.ratesLoader = ratesLoader;
        this.myMoneyDisplay = myMoneyDisplay;
    }
    
    @Override
    public void toExecute() throws IOException {
        myMoneyDisplay.display("");
        Map<String, Double> rates = ratesLoader.loadRates(myMoneyDialog.getCurrencyFrom());
        String line = "";
        for (Map.Entry<String,Double> i : rates.entrySet()) {
            line = line + i.getKey() + ": " + i.getValue() + "\n";
        }
        myMoneyDisplay.display(line);
    }

    @Override
    public String name() {
        return "Rates";
    }
     
}
