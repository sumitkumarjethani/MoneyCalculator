package View;

import Controller.Command;
import Model.CurrencyList;
import Model.persistence.ExchangeRateLoader;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener{
    private final CurrencyList currencies;

    private final ExchangeRateLoader rloader;
    private final Map<String,Command> commands;
    private final MyMoneyDialog myMoneyDialog;
    private final MyMoneyDisplay myMoneyDisplay;
    private final JButton calculate;
    private final JButton reset;
    private final JButton cancel;
    
    public MainFrame(CurrencyList currencies,ExchangeRateLoader rloader){
        super("Money Calculator");
        commands = new HashMap();
        this.currencies = currencies;
        this.rloader = rloader;
        myMoneyDialog = new MyMoneyDialog(this.currencies);
        myMoneyDisplay = new MyMoneyDisplay();
        calculate = new JButton("Calculate");
        reset = new JButton("Reset");
        cancel = new JButton("Cancel");
        calculate.addActionListener(this);
        reset.addActionListener(this);
        cancel.addActionListener(this);
        
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(myMoneyDialog.getPanel(),BorderLayout.NORTH);
        this.add(myMoneyDisplay.getPanel(),BorderLayout.CENTER);
        
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(calculate);
        buttons.add(reset);
        buttons.add(cancel);
        this.add(buttons,BorderLayout.SOUTH);
        
        this.pack();
    }
    
    public void execute(){
        this.setVisible(true);
    }
    
    public void addCommand(String name,Command command){
        commands.put(name, command);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == calculate){
            try {
                commands.get("Calculate").toExecute();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(ae.getSource() == reset){
            try {
                commands.get("Reset").toExecute();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(ae.getSource() == cancel){
            try {
                commands.get("Cancel").toExecute();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public CurrencyList getCurrencies() {
        return currencies;
    }

    public ExchangeRateLoader getRloader() {
        return rloader;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public MyMoneyDialog getMyMoneyDialog() {
        return myMoneyDialog;
    }

    public MyMoneyDisplay getMyMoneyDisplay() {
        return myMoneyDisplay;
    }

    public JButton getCalculate() {
        return calculate;
    }

    public JButton getReset() {
        return reset;
    }

    public JButton getCancel() {
        return cancel;
    }
}