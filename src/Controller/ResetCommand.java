package Controller;

import View.MyMoneyDialog;
import View.MyMoneyDisplay;

public class ResetCommand implements Command{

    private final MyMoneyDialog  myMoneyDialog;
    private final MyMoneyDisplay myMoneyDisplay;
    
    public ResetCommand(MyMoneyDialog myMoneyDialog, MyMoneyDisplay myMoneyDisplay){
        this.myMoneyDialog = myMoneyDialog;
        this.myMoneyDisplay = myMoneyDisplay;
    }
    
    @Override
    public void toExecute() {
        myMoneyDisplay.display("");
        myMoneyDialog.setMoneyTextField("");
    }

    @Override
    public String name() {
        return "Reset";
    }
    
}
