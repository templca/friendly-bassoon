
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

    public String findFollower(int nnam){
        return todo.get(nnam).getFollowerName();
    }

    public void shortestPath(String Start){
        initialise(); //reads from file
        done.add(new Link()); 
        done.get(0).addWeight(0);
        done.get(0).addLinkA(getNode(Start));
        done.get(0).addLinkB(getNode(Start));
        int num=1;
        int baseWeight=0;

        while (loopy){
            System.out.println("num before: "+num);
            getLinks(Start); //adds links that have nodes the same as start to an array (??)

            //goes through all the links in the arraylist todo, 
            //checks if they've been processed before, if not, 
            //then enqueues them to my priority queue
            //and adds the base weight to it
            for (int j=0;j<=(todo.size()-1);j++){ 
                if (!checkQueue(todo.get(j).getName())){ 
                    q.doEnqueue(todo.get(j));
                    System.out.println("enqueueing: "+todo.get(j).getName());
                    System.out.println("current weight: "+todo.get(j).getWeight()+"adding on: "+baseWeight);
                    todo.get(j).addOnWeight(baseWeight);
                } else {
                    System.out.println(todo.get(j).getName()+"(enqueue) already added.");
                }
            } 
            todo.clear(); 
            //Link dummy=q.dequeue();
            if(!checkLink(done,q.getFront().getName())){
                done.add(q.getFront());
                baseWeight=q.getFront().getWeight();
                System.out.println("(dequeue) adding to done: "+done.get(num).getName());
                System.out.println("(dequeue) baseweight is "+baseWeight);

            } else {
                Link dummy=q.getFront();
                done.add(findNextFollower(dummy));
                System.out.println("//(dequeue) adding to done: "+done.get(num).getName());
            }
            System.out.println("baseweight now: "+baseWeight);

            //checks if link is al
            /*if(!checkLink(done,dummy.getName())){
            done.add(dummy);
            System.out.println("(dequeue) adding to done: "+done.get(num-1).getName());
            } else {
            System.out.println("(dequeue) already added.");
            }
             */
            System.out.println("next before: "+Start);
            Start=done.get(done.size()-1).getOther(Start);
            System.out.println("next after: "+Start);
            num++;
            System.out.println("num after: "+num);

            for (int i=0;i<=(done.size()-1);i++){
                System.out.println("DONE: "+done.get(i).getNodeA()+" links to "+done.get(i).getNodeB()+" with weight: "+done.get(i).getWeight());
            }

            counter=0;
            for (int j=0;j<=(nodes.length-1);j++){
                if (checkNode(done, nodes[j].getName())){
                    System.out.println(nodes[j].getName()+checkNode(done,nodes[j].getName()));
                    counter++;
                }                
            } 
            System.out.println("counter: "+counter);
            if(counter==numberOfNodes){
                loopy=false;
            }
        }
        for (int i=0;i<=(done.size()-1);i++){
            System.out.println(done.get(i).getNodeA()+" links to "+done.get(i).getNodeB());
        }
    }

    public boolean andOr(boolean first, boolean second){
        if(first && second){
            return true;
        } else if (first || second){
            return true;
        }
        return false;
    }

    public Link findNextFollower(Link lin){
        if(!checkLink(done,lin.getFollower().getName())){
            return lin.getFollower();
        } else{
            return findNextFollower(lin.getFollower());
        }
    }

    public void test(){
        for (int i=0;i<=4;i++){
            q.doEnqueue(l[i]);
        }
    }

    public boolean checkQueue(String s){
        Link a =q.getFront();
        for(i=0;i<=(q.len()-1);i++){
            if(s.equals(a.getName())){
                return true;
            } else {
                a=a.getFollower();
            }
        }
        return false;
    }

    public boolean checkLink(ArrayList<Link> a, String s){
        for(i=0;i<=(a.size()-1);i++){
            if(s.equals(a.get(i).getName())){
                return true;
            } else if (a.get(i)==null){
                return false;
            }
        }
        return false;
    }

    public boolean checkNode(ArrayList<Link> aar, String s){
        boolean yes=true;
        int x=0;
        while(yes){
            if(s.equals(aar.get(x).getNodeA())||s.equals(aar.get(x).getNodeB())){
                yes=false;
                return true;
            } else if(x>=(aar.size()-1)){
                yes=false;
                return false;
            } else if(aar.get(x)==null){
                yes=false;
                return false;
            } else {
                x++;
            }
        }
        return false; 
    }

    public void getLinks(String n){
        for(int i=0;i<=(numberOfLinks-1);i++){
            if(n.equals(l[i].getNodeA())||n.equals(l[i].getNodeB())){
                if(todo.size()>0 && !checkLink(todo,l[i].getName())){
                    System.out.println("(get) adding: "+l[i].getName());
                    todo.add(l[i]);
                }else if(todo.size()==0){
                    System.out.println("(get) adding: "+l[i].getName());
                    todo.add(l[i]);
                } else {
                    System.out.println("(get) already added: "+l[i].getName());
                }

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