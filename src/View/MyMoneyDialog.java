package View;

import Model.Currency;
import Model.CurrencyList;
import Model.Money;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyMoneyDialog extends JPanel implements MoneyDialog{
    private final JLabel moneyLabel;
    private final JTextField moneyTextField;
    private final JLabel fromLabel;
    private final JComboBox currenciesFromBox;
    private final JLabel toLabel;
    private final JComboBox currenciesToBox;
    private final CurrencyList currencies;
    
    public MyMoneyDialog(CurrencyList currencies){
        this.currencies = currencies;
        moneyLabel = new JLabel("Amount");
        moneyTextField = new JTextField(20);
        fromLabel = new JLabel("Currency From");
        toLabel = new JLabel("Currency To");
        currenciesFromBox = new JComboBox();
        currenciesToBox = new JComboBox();
        addCurrenciesBox();
        this.setLayout(new BorderLayout());
        
        JPanel up = new JPanel(new FlowLayout());
        up.add(moneyLabel);
        up.add(moneyTextField);
        up.add(fromLabel);
        up.add(currenciesFromBox);
        this.add(up,BorderLayout.NORTH);
        
        JPanel center = new JPanel(new FlowLayout());
        center.add(toLabel);
        center.add(currenciesToBox);
        this.add(center,BorderLayout.CENTER);
        
    }

    @Override
    public Money getMoney() {
        Currency from = currencies.get(currenciesFromBox.getSelectedItem().toString());
        Money result;
        if(moneyTextField.getText().matches("^[+]?([0-9]+(?:[\\.][0-9]*)?|\\.[0-9]+)$")){
             result = new Money(Double.parseDouble(moneyTextField.getText()),from);
        }else{
            result = null;
        }
        return result;
    }

    @Override
    public Currency getCurrencyTo() {
        Currency to = currencies.get(currenciesToBox.getSelectedItem().toString());
        return to;
    }

    private void addCurrenciesBox() {
        for (HashMap.Entry<String,Currency> i : currencies.getCurrencies().entrySet()) {
            currenciesFromBox.addItem(i.getKey());
            currenciesToBox.addItem(i.getKey());
        }
    }
    
    public JPanel getPanel(){
        return this;
    }

    public JLabel getMoneyLabel() {
        return moneyLabel;
    }

    public JTextField getMoneyTextField() {
        return moneyTextField;
    }
    
    public void setMoneyTextField(String line){
        moneyTextField.setText(line);
    }

    public JLabel getFromLabel() {
        return fromLabel;
    }

    public JComboBox getCurrenciesFromBox() {
        return currenciesFromBox;
    }

    public JLabel getToLabel() {
        return toLabel;
    }

    public JComboBox getCurrenciesToBox() {
        return currenciesToBox;
    }

    public CurrencyList getCurrencies() {
        return currencies;
    }
    
    
}
