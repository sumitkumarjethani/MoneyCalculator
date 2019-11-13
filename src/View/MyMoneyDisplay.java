package View;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MyMoneyDisplay extends JPanel implements MoneyDisplay {
    
    private final JTextArea resultArea;
    
    public MyMoneyDisplay(){
        resultArea = new JTextArea(5,20);
        resultArea.setEditable(false);
        this.add(resultArea);
    }
    
    @Override
    public void display(String line) {
        this.resultArea.setText(line);
    }

    public JPanel getPanel() {
        return this;
    }
    
}
