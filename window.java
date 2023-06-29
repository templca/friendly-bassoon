
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
    int x1;
    int y1;
    int fontSize=25;
    
    filereader file = new filereader();
    graph data=new graph();

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
        int centering=5;
        int b=1;
        Font stringFont = new Font("SansSerif", Font.PLAIN, fontSize );
        g2.setFont( stringFont );
        
        //draws the lines
        for(int j=0;j<=(linkNumber-1);j++){
            g2.setColor(Color.BLUE);
            int startX=findNode("x",file.getData(0,nodeNumber+j+1));
            int startY=findNode("y",file.getData(0,nodeNumber+j+1));
            int endX=findNode("x",file.getData(1,nodeNumber+j+1));
            int endY=findNode("y",file.getData(1,nodeNumber+j+1));

            int lineWeight=Integer.parseInt(file.getData(2,nodeNumber+j+1));
            g2.setStroke(new BasicStroke(lineWeight));
            Line2D lin = new Line2D.Float(startX+(circleSize/2),startY+(circleSize/2),endX+(circleSize/2),endY+(circleSize/2));
            g2.draw(lin);
        }

        //draws nodes and gives them the names
        for(int i=0;i<=(nodeNumber-1);i++){
            g2.setColor(Color.BLACK);
            x=Integer.parseInt(file.getData(1,b));
            y=Integer.parseInt(file.getData(2,b));
            g2.fillOval(x,y,circleSize,circleSize);
            x1=x+(circleSize/2)-centering;
            y1=y+(circleSize/2);
            g2.setColor(Color.WHITE);
            g2.drawString(file.getData(0,b),x1,y1+centering);
            
            b++;
        }

    }
    
    public int findNode(String coordinate, String a){
        if(coordinate=="x"){
            for(int i=0;i<=(nodeNumber);i++){
                if(a.equals(file.getData(0,i))){
                    return Integer.parseInt(file.getData(1,i));
                }
            }
        }else if(coordinate=="y"){
            for(int i=0;i<=(nodeNumber);i++){
                if(a.equals(file.getData(0,i))){
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