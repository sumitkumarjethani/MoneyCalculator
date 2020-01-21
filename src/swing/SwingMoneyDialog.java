package swing;

import model.Currency;
import model.CurrencyList;
import model.Money;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import view.MoneyDialog;

public class SwingMoneyDialog extends JPanel implements MoneyDialog{
    private final CurrencyList currencies;
    private final JTextField moneyTextField;
    private final JComboBox boxFrom;
    private final JComboBox boxTo;
    
    public SwingMoneyDialog(CurrencyList currencies){
        this.currencies = currencies;
        this.setLayout(new BorderLayout());
        moneyTextField = new JTextField(20);
        boxFrom = addCurrencyToBox();
        boxTo = addCurrencyToBox();
        this.add(up(),BorderLayout.NORTH);
        this.add(center(),BorderLayout.CENTER);
    }
    
    @Override
    public Money getMoney() {
        Currency from = this.getCurrencyFrom();
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
        String selectedCurrency = boxTo.getSelectedItem().toString();
        selectedCurrency = selectedCurrency.substring(0, 3);
        Currency to = currencies.get(selectedCurrency);
        return to;
    }
    
    public void setMoneyTextField(String line){
        moneyTextField.setText(line);
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
    
    private Currency getCurrencyFrom() {
        String selectedCurrency = boxFrom.getSelectedItem().toString();
        selectedCurrency = selectedCurrency.substring(0, 3);
        Currency from = currencies.get(selectedCurrency);
        return from;
    }
    
    
    private JComboBox addCurrencyToBox() {
        JComboBox comboBox = new JComboBox();
        for (HashMap.Entry<String,Currency> i : currencies.getCurrencies().entrySet()) {
            String add = i.getKey() + "-" +i.getValue().getName();
            comboBox.addItem(add);
        }
        return comboBox;
    }
}
