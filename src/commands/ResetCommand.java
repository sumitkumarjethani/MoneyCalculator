package commands;

import architecture.controller.*;
import architecture.view.MoneyDialog;
import architecture.view.MoneyDisplay;

public class ResetCommand implements Command{

    private final MoneyDialog  moneyDialog;
    private final MoneyDisplay moneyDisplay;
    
    public ResetCommand(MoneyDialog moneyDialog, MoneyDisplay moneyDisplay){
        this.moneyDialog = moneyDialog;
        this.moneyDisplay = moneyDisplay;
    }
    
    @Override
    public void toExecute() {
        moneyDisplay.display("");
        moneyDialog.setMoneyText("");
    }
    
}
