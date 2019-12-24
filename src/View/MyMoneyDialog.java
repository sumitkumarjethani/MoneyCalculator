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
    private final CurrencyList currencies;
    private final JTextField moneyTextField;
    private final JComboBox boxFrom;
    private final JComboBox boxTo;
    
    public MyMoneyDialog(CurrencyList currencies){
        this.currencies = currencies;
        this.setLayout(new BorderLayout());
        moneyTextField = new JTextField(20);
        boxFrom = addCurrencyBox();
        boxTo = addCurrencyBox();
        this.add(up(),BorderLayout.NORTH);
        this.add(center(),BorderLayout.CENTER);
    }
    
    private JPanel up() {
        JPanel up = new JPanel();
        up.setLayout(new FlowLayout());
        up.add(new JLabel("Amount"));
        up.add(moneyTextField);
        up.add(new JLabel("Currency From"));
        up.add(boxFrom);
        return up;
    }
    
    private JPanel center() {
        JPanel center = new JPanel();
        center.setLayout(new FlowLayout());
        center.add(new JLabel("Currency To"));
        center.add(boxTo);
        return center;
    }
    
    private JComboBox addCurrencyBox() {
        JComboBox comboBox = new JComboBox();
        for (HashMap.Entry<String,Currency> i : currencies.getCurrencies().entrySet()) {
            comboBox.addItem(i.getKey());
        }
        return comboBox;
    }

    @Override
    public Money getMoney() {
        Currency from = currencies.get(boxFrom.getSelectedItem().toString());
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
        Currency to = currencies.get(boxTo.getSelectedItem().toString());
        return to;
    }
    
    public Currency getCurrencyFrom() {
        Currency from = currencies.get(boxFrom.getSelectedItem().toString());
        return from;
    }
    
    
    public void setMoneyTextField(String line){
        moneyTextField.setText(line);
    }
    
    public JPanel getPanel(){
        return this;
    }
}
