
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
    private int linkNumber=0;
    int[] nodeCenterX;
    int[] nodeCenterY;
    int[] otherNodeX;
    int[] otherNodeY;
    int x1;
    int y1;

    filereader file = new filereader();

    public void actionPerformed(ActionEvent e) {
        String cmd=e.getActionCommand();
        switch (cmd) {
            case "add node": 
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
        int circleSize=100;
        int b=1;

        for(int i=0;i<=(nodeNumber-1);i++){
            x=Integer.parseInt(file.getData(1,b));
            y=Integer.parseInt(file.getData(2,b));
            g2.drawOval(x,y,circleSize,circleSize);
            x1=x+50;
            nodeCenterX[i]=x1;
            y1=y+50;
            nodeCenterY[i]=y1;
            g2.drawString(file.getData(0,b),nodeCenterX[i],nodeCenterY[i]);

            b++;
        }
        for(int j=0;j<=(linkNumber-1);j++){
            String test=file.getData(1,nodeNumber+j+1);
            int numberX=findNode("x",test)+50;
            int numberY=findNode("y",test)+50;
            System.out.println("node: "+file.getData(0,j+nodeNumber+1));
            System.out.println("test: "+test);
            System.out.println("number x: "+numberX);
            System.out.println("number y: "+numberY);

            Line2D lin = new Line2D.Float(nodeCenterX[j],nodeCenterY[j],numberX,numberY);
            g2.draw(lin);
        }

    }

    public int findNode(String coordinate, String a){
        if(coordinate=="x"){
            for(int i=0;i<=(linkNumber-1);i++){
                System.out.println(file.getData(1,nodeNumber+i)+" link");
                System.out.println(file.getData(0,1+i)+" node");

                if(file.getData(1,nodeNumber+i).equals(file.getData(0,1+i))){
                    return Integer.parseInt(file.getData(1,i));
                } 
            }
        }else {
            for(int i=0;i<=(linkNumber-1);i++){
                if(file.getData(1,nodeNumber+i).equals(file.getData(0,1+i))){
                    return Integer.parseInt(file.getData(2,i));
                } 
            }
        }

        return 1;
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
        nodeNumber=Integer.parseInt(file.getData(0,0));
        linkNumber=(file.getLines()-(nodeNumber+1));
        nodeCenterX= new int[nodeNumber];
        nodeCenterY= new int[nodeNumber];
        otherNodeX= new int[linkNumber];
        otherNodeY= new int[linkNumber];

        setTitle("djikstra");
        this.getContentPane().setPreferredSize(new Dimension(700,700));
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.toFront();
        this.setVisible(true);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400,400));
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
