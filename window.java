
/**
 * cara templeton
 * 
 * 19/05
 * 
 * this is the graphical user interface class
 */

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.awt.event.*; //listener
import java.awt.geom.*; //geometry

public class window extends JFrame implements ActionListener
{
    // instance variables - replace the example below with your own
    private int x=-1000;
    private String windowName;
    private int y=100;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    private int nodeNumber=0;

    Canvas myGraphic;
    final String fileName="redcircle100x100.png";
    ImageIcon image = new ImageIcon(fileName);

    private int width=100;
    private int height=100;

    public void actionPerformed(ActionEvent e) {
        String cmd=e.getActionCommand();
        switch (cmd) {
            case "add node": 

            if(nodeNumber==0){
                x=50;

            } else if (nodeNumber==1){
                x=x+200;
            }
            else if (isEven(nodeNumber)){
                y=y+100;
                x=x-200;
            } else { 
                x=x+200; } 
            nodeNumber++;
            repaint();
            break;
            case "quit": System.exit(0);
        }
    }

    public boolean isEven(int number){
        if(number%2 ==0){
            return true;
        } else return false;
    }

    public void paint (Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        image.paintIcon(this,g,x, y);

        }
        public void mouseExited(MouseEvent e) {System.out.println("exit");}
    public void mouseEntered(MouseEvent e) {System.out.println("enter");}
    public void mouseReleased(MouseEvent e) {System.out.println("release");}
    public void mousePressed(MouseEvent e) {System.out.println("press");}
    public void mouseClicked(MouseEvent e) {System.out.println("click!");}
    /**
     * Constructor for objects of class window
     */
    public window()
    {
        // initialise instance variables
        setTitle("djikstra");
        this.getContentPane().setPreferredSize(new Dimension(700,700));
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.toFront();
        this.setVisible(true);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400,400));
        myGraphic = new Canvas();
        panel.add(myGraphic);

        menuBar=new JMenuBar();
        this.setJMenuBar (menuBar);

        menu = new JMenu("File");
        menuBar.add(menu);

        menuItem=new JMenuItem("add node");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem=new JMenuItem("quit");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        this.pack();
    }
}
