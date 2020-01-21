package controller;

import swing.SwingMoneyDialog;
import swing.SwingMoneyDisplay;

public class ResetCommand implements Command{

    private final SwingMoneyDialog  moneyDialog;
    private final SwingMoneyDisplay moneyDisplay;
    
    public ResetCommand(SwingMoneyDialog moneyDialog, SwingMoneyDisplay moneyDisplay){
        this.moneyDialog = moneyDialog;
        this.moneyDisplay = moneyDisplay;
    }
    
    @Override
    public void toExecute() {
        moneyDisplay.display("");
        moneyDialog.setMoneyTextField("");
    }

    @Override
    public String name() {
        return "Reset";
    }
    
}
