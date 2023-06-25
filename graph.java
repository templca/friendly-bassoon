
/**
 * cara templeton
 * 
 * 18/05
 * 
 * netowork
 */

import java.util.Scanner;
import java.util.ArrayList;
public class graph
{
    // instance variables - replace the example below with your own

    private char n='a';
    Queue q;
    int lin=4;
    String nam;
    Link[] l;
    Link[] yes;
    Link[] sameNode;
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
    int baseWeight;
    int counter;
    String next;
    ArrayList<Link> done = new ArrayList<Link>();
    ArrayList<Link> todo = new ArrayList<Link>();

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

    public void bruh(String Start){

        done.add(new Link());
        int num=1;
        int baseWeight=0;
        //System.out.println("length of finsihed: " +done.size);
        //System.out.println("length of finished: " +done.length);
    }

    public void shortestPath(String Start){
        initialise();
        done.add(new Link());
        done.get(0).addWeight(0);
        done.get(0).addLinkA(nodes[0]);
        done.get(0).addLinkB(nodes[0]);
        int num=1;
        int baseWeight=0;
        get(Start);

        while (loopy){
            System.out.println("num before: "+num);
            if(num>1){
                get(next);
            }
            for (int j=0;j<=(todo.size()-1);j++){
                todo.get(j).addOnWeight(baseWeight);
                q.doEnqueue(todo.get(j));
                System.out.println("enqueueing: "+todo.get(j).getName());
            } 
            //dummy=q.dequeue();
            done.add(q.dequeue());
            baseWeight=baseWeight+done.get(done.size()-1).getWeight();
            System.out.println("next before: "+next);
            next=done.get(done.size()-1).getOther(Start);
            System.out.println("next after: "+next);
            num++;
            System.out.println("num after: "+num);

            counter=0;
            for (int j=0;j<=(nodes.length-1);j++){
                if (check(nodes[j].getName())){
                    counter++;
                }                
            } 
            System.out.println(counter);
            if(counter==finished.length){
                loopy=false;
            }
        }
        for (int i=0;i<=(finished.length-1);i++){
            System.out.println(finished[i].getNodeA()+" links to "+finished[i].getNodeB());
        }
    } 

    public boolean check(String s){
        boolean yes=true;
        int x=0;
        while(yes){
            if(s.equals(done.get(0).getNodeA())||s.equals(finished[x].getNodeB())){
                yes=false;
                return true;
            } else if(x>=(finished.length-1)){
                yes=false;
                return false;
            } else if(finished[x]==null){
                yes=false;
                return false;
            } else {
                x++;
            }
        }
        return false; 
    }

    public void get(String n){
        for(int i=0;i<=(numberOfLinks-1);i++){
            if(n.equals(l[i].getNodeA())||n.equals(l[i].getNodeB())){
                todo.add(l[i]);
            }
        }

    }

    public String intoString(char letter){
        String n1=String.valueOf(letter);
        return n1;
    }

    public char intoChar(String word){
        char c;
        return c=word.charAt(0);
    }

}