package main;

import architecture.controller.*;
import architecture.view.*;
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
import swing.*;

public class MainFrame extends JFrame{
    private final Map<String,Command> commands;
    private final Map<String,String> labels;
    
    public MainFrame(){
        setTitle("Money Calculator");
        setSize(600,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        setLayout(new BorderLayout());
        commands = new HashMap();
        labels = new HashMap();
        initLabels();
        this.add(toolbar(),BorderLayout.SOUTH);
    }
    
    public void execute(){
        this.setVisible(true);
    }
    
    public void addMoneyDialog(MoneyDialog moneyDialog){
        this.add((SwingMoneyDialog)moneyDialog,BorderLayout.NORTH);
    }
    
    public void addMoneyDisplay(MoneyDisplay moneyDisplay){
        this.add((SwingMoneyDisplay)moneyDisplay,BorderLayout.CENTER);
    }
    
    public void addCommand(String name,Command command){
        commands.put(name, command);
    }

    private void initLabels() {
        labels.put("Calculate", "Calculate");
        labels.put("Reset", "Reset");
        labels.put("Rates", "Rates");
    }

    private JPanel toolbar() {
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolbar.add(button("Calculate"));
        toolbar.add(button("Reset"));
        toolbar.add(button("Rates"));
        return toolbar;
    }

    private JButton button(String id) {
        JButton button = new JButton(labels.get(id));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    commands.get(id).toExecute();
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return button;
    }
}