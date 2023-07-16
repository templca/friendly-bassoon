
/**
 * cara templeton
 * 
 * 17/07
 * 
 * makes a dialog box that does text input
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class intputDialog extends JDialog
{
    // instance variables - replace the example below with your own
    private String remember;

    /**
     * Constructor for objects of class intputDialog
     */
    public intputDialog(String question)
    {
        // initialise instance variables
        super(new JFrame(question),question);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setMinimumSize(new Dimension(question.length()*7,100));
        JTextField reply=new JTextField();
        JButton clickMe = new JButton("enter");
        clickMe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                remember=reply.getText();
                close();
            }
        }); //action listener
        this.setLayout(new GridLayout(2,1,5,5));
        this.add(reply);
        this.add(clickMe);
        this.pack();
        setModal(true);
    }
    
    private void close(){this.dispose();}
    
    public String getText(){ return remember;}
}
