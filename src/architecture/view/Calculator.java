package architecture.view;

import architecture.model.Currency;
import architecture.model.ExchangeRate;
import architecture.model.Money;

public class Calculator {
    
    public Money calculate(Money money, Currency to, ExchangeRate exchangeRate){
        Money result = new Money(money.getAmount() * exchangeRate.getRate(),to);
        return result;
    }
    
}
