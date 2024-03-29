
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
import javax.swing.JButton;

public class window extends JFrame implements ActionListener
{
    // instance variables - replace the example below with your own
    //menu stuff.
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    JButton myButton;
    TextArea area;
    private String windowName;
    
    //number of links/nodes..
    private int nodeNumber=0;
    private int linkNumber=0;
    
    //stores the coordinates for lines.
    int x1;
    int y1;
    private int y=100;
    private int x=-1000;
    
    //size of text.
    int fontSize=25;
    
    //tells when to paint the graphics.
    boolean paintPath=false;
    boolean graphImported=false;
    
    //where the text input from dialogue box gets stored.
    String remember;
    
    //stores the start and end nodes for the algorithm.
    String startNode;
    String endNode;
    
    //start of the error mesage.
    String errorMessage="Error: ";
    
    //any text for in the dialogue box.
    String dialogText;
    String dialogTitle;

    graph data=new graph();
    public void actionPerformed(ActionEvent e) {
        String cmd=e.getActionCommand(); 
        switch (cmd) {
            case "Quit": System.exit(0);
                break;
            case "Shortest Path":
                data.resetGraphError();
                dialogTitle="start node?";
                InDialogBox();
                startNode=remember;
                System.out.print("From: "+startNode);

                dialogTitle="end node?";
                InDialogBox();
                endNode=remember;
                System.out.println(" to: "+endNode);
                data.checkingAlgorithm(startNode);
                if(data.hasGraphError()){
                    dialogTitle="Error";
                    dialogText=errorMessage+"Starting node not recognised";
                    DialogBox();
                } else{
                    paintPath=true;
                    repaint();
                }
                break;
            case "Import File": 
                data.resetGraphError();
                dialogTitle="File Name?";
                InDialogBox();
                data.setFileName(remember);
                if(data.hasGraphError()){
                    dialogTitle="Error";
                    dialogText=errorMessage+"Problem with graph.";
                    DialogBox();
                } else if (data.hasFileError()){
                    dialogTitle="Error";
                    dialogText=errorMessage+"Problem with file reader.";
                    DialogBox();
                }
                System.out.println("File name set to: "+remember);
                nodeNumber=data.getNodeNumber();
                linkNumber=data.getLinkNumber();
                graphImported=true;
                repaint();
                break;

            
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
        int circleSize=80;
        int centering=5;
        int b=1;
        Font stringFont = new Font("SansSerif", Font.PLAIN, fontSize );
        Font numberFont = new Font("SansSerif", Font.PLAIN, fontSize-5 );
        g2.setFont( stringFont );

        if(graphImported && !data.hasGraphError()){
            System.out.println("Importing graph..");
            //draws the lines
            for(int j=0;j<=(linkNumber-1);j++){
                g2.setColor(Color.DARK_GRAY);
                int startX=findNodeCoordinate(true,data.getFromLink(j,true));
                int startY=findNodeCoordinate(false,data.getFromLink(j,true));
                int endX=findNodeCoordinate(true,data.getFromLink(j,false));
                int endY=findNodeCoordinate(false,data.getFromLink(j,false));

                
                int lineWeight=data.getLinkWeight(j);
                g2.setStroke(new BasicStroke(lineWeight));
                Line2D lin = new Line2D.Float(startX+(circleSize/2),startY+(circleSize/2),endX+(circleSize/2),endY+(circleSize/2));
                g2.draw(lin);

                g2.setFont( numberFont );

                
                g2.setColor(Color.GRAY);
                String weightText=Integer.toString(data.getLinkWeight(j));
                float y=findCenter(startY,endY);
                float x=findCenter(startX,endX);
                g2.drawString(weightText,x-7,y+13);

            }

            //draws nodes and gives them the names
            for(int i=0;i<=(nodeNumber-1);i++){
                g2.setFont( stringFont );
                g2.setColor(Color.BLACK);
                x=data.getCoordinate(i,true);
                y=data.getCoordinate(i,false);
                g2.fillOval(x,y,circleSize,circleSize);
                x1=x+(circleSize/2)-centering;
                y1=y+(circleSize/2);
                g2.setColor(Color.WHITE);
                g2.drawString(data.getNodeName(i),x1,y1+centering);

                b++;
            }

            graphImported=false;
        }
        if(paintPath){
            data.checkingPath(startNode,endNode);
            if(data.hasGraphError()){
                dialogTitle="Error";
                dialogText=errorMessage+"End node not recognised";
                DialogBox();
            }

            //draws the lines grey
            for(int j=0;j<=(linkNumber-1);j++){
                g2.setColor(Color.DARK_GRAY);
                int startX=findNodeCoordinate(true,data.getFromLink(j,true));
                int startY=findNodeCoordinate(false,data.getFromLink(j,true));
                int endX=findNodeCoordinate(true,data.getFromLink(j,false));
                int endY=findNodeCoordinate(false,data.getFromLink(j,false));

                int lineWeight=data.getLinkWeight(j);
                g2.setStroke(new BasicStroke(lineWeight));
                Line2D lin = new Line2D.Float(startX+(circleSize/2),startY+(circleSize/2),endX+(circleSize/2),endY+(circleSize/2));
                g2.draw(lin);

                g2.setFont( numberFont );
                g2.setColor(Color.GRAY);
                String weightText=Integer.toString(data.getLinkWeight(j));
                float y=findCenter(startY,endY);
                float x=findCenter(startX,endX);
                g2.drawString(weightText,x-7,y+13);
            }

            //drawing lines red
            for(int i=0;i<data.shortestSize();i++){
                int startX =findNodeCoordinate(true,data.getFromShortest(i));
                int startY =findNodeCoordinate(false,data.getFromShortest(i));

                int endX =findNodeCoordinate(true,data.getPFShortest(i));
                int endY =findNodeCoordinate(false,data.getPFShortest(i));

                Link linkNumber=data.findLink(data.getNodeShortest(i));
                int lineWeight=linkNumber.getWeight();
                g2.setColor(Color.RED);
                g2.setStroke(new BasicStroke(lineWeight));
                Line2D lin = new Line2D.Float(startX+(circleSize/2),startY+(circleSize/2),endX+(circleSize/2),endY+(circleSize/2));
                g2.draw(lin);


            }

            //draws nodes and gives them the names
            for(int i=0;i<=(nodeNumber-1);i++){
                g2.setColor(Color.BLACK);
                x=data.getCoordinate(i,true);
                y=data.getCoordinate(i,false);
                g2.fillOval(x,y,circleSize,circleSize);
                x1=x+(circleSize/2)-centering;
                y1=y+(circleSize/2);
                if(data.checkShortest(data.getNodeName(i))){
                    g2.setColor(Color.WHITE);
                    g2.drawString(data.getNodeName(i),x1,y1+centering);
                } else {
                    g2.setColor(Color.WHITE);
                    g2.drawString(data.getNodeName(i),x1,y1+centering);
                }

                b++;
            }

            for(int i=0;i<data.shortest.size();i++){
                String text;
                text=data.shortest.get(i).getName()+" comes from "+data.shortest.get(i).getPreviousName()+" with a cost of "+data.shortest.get(i).getCost();

            }
            paintPath=false;
        }

        System.out.println("");
    }

    public float findCenter(int start, int end){
        float number=end-start;
        number=number/2;

        return start+number+50;
    }

    public void DialogBox(){
        int length=dialogText.length()*5+50;
        JDialog box = new JDialog(this);
        box.setBounds(400,400,length,100);

        TextArea area = new TextArea(dialogText);
        box.add(area);
        area.setEditable(false);

        box.toFront();
        box.setVisible(true);
        box.setTitle(dialogTitle);

    }

    public void InDialogBox(){
        intputDialog box = new intputDialog(dialogTitle);
        box.setLocationRelativeTo(this);
        box.setVisible(true);
        remember=box.getText();
    }

    public int findNodeCoordinate(boolean X, String a){
        /*if the boolean is true, then it is the x coordinate. 
         * if its false then its the y coordinate
           */
        if(X){
            for(int i=0;i<=(nodeNumber);i++){
                if(a.equals(data.getNodeName(i))){
                    return data.getCoordinate(i,true);
                }
            }
        }else {
            for(int i=0;i<=(nodeNumber);i++){
                if(a.equals(data.getNodeName(i))){
                    return data.getCoordinate(i,false);
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

        menuItem=new JMenuItem("Import File");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem=new JMenuItem("Quit");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        
        menu = new JMenu("Play");
        menuBar.add(menu);

        menuItem=new JMenuItem("Shortest Path");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        this.pack();
        repaint();
    }
}