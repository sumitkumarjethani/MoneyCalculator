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

public class MainFrame extends JFrame{
    private Map<String,Command> commands;
    private Map<String,String> labels;
    private final MyMoneyDialog myMoneyDialog;
    private final MyMoneyDisplay myMoneyDisplay;
    
    public MainFrame(MyMoneyDialog moneyDialog, MyMoneyDisplay moneyDisplay){
        setTitle("Money Calculator");
        setSize(500,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        commands = new HashMap();
        labels = new HashMap();
        myMoneyDialog = moneyDialog;
        myMoneyDisplay = moneyDisplay;
        initLabels();
        this.add(toolbar(),BorderLayout.SOUTH);
        this.add(myMoneyDialog.getPanel(),BorderLayout.NORTH);
        this.add(myMoneyDisplay.getPanel(),BorderLayout.CENTER);
        this.setResizable(false);
    }


    /*
    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        try {
            g.drawImage(awtImage(), 0, 0, this);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private Image awtImage() throws IOException {
        return ImageIO.read(new File("background.jpg"));
    }
    */
    
    public void execute(){
        this.setVisible(true);
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