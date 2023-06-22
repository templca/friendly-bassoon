
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
        
        finished = new Link[2];
        finished[0]=new Link();
        finished[0].addLinkA(getNode(Start));
        finished[0].addLinkB(getNode(Start));
        finished[0].addWeight(0);
        int num=1;
        int baseWeight=0;
        System.out.println("length of finsihed: " +finished.length);
        extend(6);
        System.out.println("length of finished: " +finished.length);
    }

    public void shortestPath(String Start){
        initialise();
        finished = new Link[2];
        finished[0]=new Link();
        finished[0].addLinkA(getNode(Start));
        finished[0].addLinkB(getNode(Start));
        finished[0].addWeight(0);
        int num=1;
        int baseWeight=0;
        get(Start);

        while (loopy){
            System.out.println("num before: "+num);
            if(num>1){
                get(next);
            }
            for (int j=0;j<=(sameNode.length-1);j++){
                sameNode[j].addOnWeight(baseWeight);
                q.doEnqueue(sameNode[j]);
                System.out.println("enqueueing: "+sameNode[j].getName());
            } 
            dummy=q.dequeue();
            extend(1);
            finished[num]=dummy;
            baseWeight=baseWeight+dummy.getWeight();
            System.out.println("dummy before: "+dummy.getName());
            System.out.println("next before: "+next);
            next=dummy.getOther(Start);
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
    
    public void extend(int base){
        Link[] stored = new Link[finished.length];
        for(int i=0;i<=(finished.length-1);i++){
            stored[i]=finished[i];
        }
        int old= finished.length;
        finished = new Link[(finished.length-1)+base];
        for(int i=0;i<=(old-1);i++){
            finished[i]=stored[i];
            stored[i]=null;
        }
    }

    public boolean check(String s){
        boolean yes=true;
        int x=0;
        while(yes){
            if(s.equals(finished[x].getNodeA())||s.equals(finished[x].getNodeB())){
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
        int num=0;
        for(int i=0;i<=(numberOfLinks-1);i++){
            if(n.equals(l[i].getNodeA())||n.equals(l[i].getNodeB())){
                num++;
            }
        }
        if(sameNode==null){
        sameNode = new Link[num];
    } else {
        sameNode = new Link[sameNode.length+num];
    }
        int count=0;
        for(int i=0;i<=(sameNode.length-1);i++){
            if(n.equals(l[i].getNodeA())||n.equals(l[i].getNodeB())){
                sameNode[count]=l[i];
                count++;
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