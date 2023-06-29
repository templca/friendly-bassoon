
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
    Queue q;
    Link[] l;
    Node[] nodes;
    filereader read = new filereader();
    int numberOfNodes;
    int numberOfLinks;
    int weight;
    int num=1;
    String nodeName;
    boolean loopy=true;
    Link dummy;
    int baseWeight;
    int counter;
    String next;
    ArrayList<Link> done = new ArrayList<Link>();
    ArrayList<Link> todo = new ArrayList<Link>();
    
    /**
     * Constructor for objects of class Network
     */
    public graph()
    {
        q = new Queue();

    }

    //reads from file
    public void initialise(){
        numberOfNodes=Integer.parseInt(read.getData(0,0));
        numberOfLinks=(read.getLines()-(numberOfNodes+1));
        l = new Link[numberOfLinks];
        nodes = new Node[numberOfNodes];
        int count=1;
        for (int i=0;i<=(numberOfNodes-1);i++){
            
            nodes[i]=new Node(read.getData(0,count));
            count++;
        }

        for (int i=0;i<=(numberOfLinks-1);i++){
            l[i]=new Link();
        }
        num=1+numberOfNodes;

        for (int i=0;i<=(numberOfLinks-1);i++){
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

    /* this method takes in a node name and returns the shortest path
       to each other node */
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

    /*finds the shortest path to each node from the one you pick*/
    public void shortestPath(String Start){
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
            /*checks if the front of the queue is already in the done 
               arraylist. if it is then adds it to done, if it isn't
               it keeps looking at the followers until there's one not in done*/
            if(!checkLink(done,q.getFront().getName())){
                done.add(q.getFront());
                baseWeight=q.getFront().getWeight();
                System.out.println("(dequeue) adding to done: "+done.get(num).getName());
                System.out.println("(dequeue) baseweight is "+baseWeight);

            } else {
                Link dummy=q.getFront();
                done.add(findNextFollower(dummy));
                baseWeight=done.get(num).getWeight();
                System.out.println("//(dequeue) adding to done: "+done.get(num).getName());
            }
            System.out.println("baseweight now: "+baseWeight);
            System.out.println("next before: "+Start);
            Start=done.get(done.size()-1).getOther(Start);
            System.out.println("next after: "+Start);
            num++;
            System.out.println("num after: "+num);

            for (int i=0;i<=(done.size()-1);i++){
                System.out.println("DONE: "+done.get(i).getNodeA()+" links to "+done.get(i).getNodeB()+" with weight: "+done.get(i).getWeight());
            }

            /* looks through the done array and checks if all the nodes are
               there. if they are then the loop stops.*/
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
                System.out.println("DONE: "+done.get(i).getNodeA()+" links to "+done.get(i).getNodeB()+" with weight: "+done.get(i).getWeight());
            }
    }

    /*this method checks if a link is in done, 
    if its not then it looks at its follower */
    public Link findNextFollower(Link lin){
        if(!checkLink(done,lin.getFollower().getName())){
            return lin.getFollower();
        } else{
            return findNextFollower(lin.getFollower());
        }
    }

    /* this method returns true if there is a link with the inputted 
     * name in the queue */
    public boolean checkQueue(String s){
        Link a =q.getFront();
        for(int i=0;i<=(q.len()-1);i++){
            if(s.equals(a.getName())){
                return true;
            } else {
                a=a.getFollower();
            }
        }
        return false;
    }

    /*this method returns true if there's a link with the inputted 
     * name is in the selected array*/
    public boolean checkLink(ArrayList<Link> a, String s){
        for(int i=0;i<=(a.size()-1);i++){
            if(s.equals(a.get(i).getName())){
                return true;
            } else if (a.get(i)==null){
                return false;
            }
        }
        return false;
    }

       /*this method returns true if there's a node with the inputted 
     * name is in the selected array*/
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

    /*this method adds all the links with the right name 
     * to an arraylist */
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

}