package architecture.view;

import architecture.model.Currency;
import architecture.model.Money;

public interface MoneyDialog{
    
    public Money getMoney();
    
    public void setMoneyText(String line);
    
    public Currency getCurrencyTo();
    
}
