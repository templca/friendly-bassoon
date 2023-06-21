
/**
 * cara templeton
 * 
 * 18/05
 * 
 * netowork
 */

import java.util.Scanner;
public class graph
{
    // instance variables - replace the example below with your own

    private char n='a';
    Queue q;
    int lin=4;
    String nam;
    Link[] l;
    Link[] yes;
    Link[] finished;
    Node[] nodes;
    filereader read = new filereader();
    int numberOfNodes;
    int numberOfLinks;
    int weight;
    int num=1;
    String nodeName;
    Node Starting;
    boolean loopy=true;
    Link dummy;

    int i; //for loop counter
    /**
     * Constructor for objects of class Network
     */
    public graph()
    {

        q = new Queue();

    }

    public void initialise(){
        numberOfNodes=Integer.parseInt(read.getData(0,0));
        numberOfLinks=(read.getLines()-1);
        l = new Link[numberOfLinks];
        nodes = new Node[numberOfNodes];
        for (i=0;i<=(numberOfNodes-1);i++){
            nodes[i]=new Node(intoString(n));
            n++;
        }

        for (i=0;i<=(numberOfLinks-1);i++){
            l[i]=new Link();
        }

        for (i=0;i<=(numberOfLinks-1);i++){
            weight=Integer.parseInt(read.getData(2,num));
            l[i].addWeight(weight);

            nodeName=read.getData(0,num);
            for (int j=0;j<=(numberOfNodes-1);j++){
                l[i].addLinkA(getNode(nodeName));
            }
            String nodeName1=read.getData(1,num);
            for (int j=0;j<=(numberOfNodes-1);j++){
                l[i].addLinkB(getNode(nodeName1));
            }

            num++;
        }

    }

    public Node getNode(String n){
        boolean yes=true;
        int x=0;
        while(yes){
            if (n.equals(nodes[x].getName())){
                yes=false;
            } else if(x>=(numberOfNodes-1)){
                yes=false;
                return null;
            } else {
                x++;
            }
        }
        return nodes[x];
    }

    public void shortestPath(String Start){
        finished = new Link[numberOfLinks];
        finished[0].addLinkA(getNode(Start));
        finished[0].addLinkB(getNode(Start));
        finished[0].addWeight(0);
        int num=1;

        find(Start);
        while (loopy){
            for (int j=0;j<=(numberOfLinks-1);j++){
                q.doEnqueue(yes[j]);
            } 
            dummy=q.dequeue();
            finished[i]=dummy;
            dummy.getOther(Start);
            num++;
        }
    } 

    public void find(String n){
        int num=0;
        for (i=0;i<=(numberOfLinks-1);i++){
            if (n.equals(l[i].getNodeA()) || n.equals(l[i].getNodeB())){
                num++;
            } 
        }
        yes = new Link[num];
        for (i=0;i<=(num-1);i++){
            if (n.equals(l[i].getNodeA()) || n.equals(l[i].getNodeB())){
                yes[i]=l[i];
            } 
        }
    }

    public String intoString(char letter){
        String n1=String.valueOf(letter);
        return n1;
    }

}