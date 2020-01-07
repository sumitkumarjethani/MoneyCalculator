package View;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MyMoneyDisplay extends JPanel implements MoneyDisplay {
    
    private final JTextArea resultArea;
    
    public MyMoneyDisplay(){
        resultArea = new JTextArea(8,42);
        resultArea.setEditable(false);
        JScrollPane scroll = new JScrollPane (resultArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                                              JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(scroll);
    }
    
    @Override
    public void display(String line) {
        this.resultArea.setText(line);
    }
}
