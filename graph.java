
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
    nodeQueue nq;
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
    ArrayList<Link> shortest = new ArrayList<Link>();

    ArrayList<Node> doneN = new ArrayList<Node>();
    ArrayList<Node> todoN = new ArrayList<Node>();

    /**
     * Constructor for objects of class Network
     */
    public graph()
    {
        q = new Queue();
        nq = new nodeQueue();

    }

    public int getNodeNumber(){
        return numberOfNodes;
    }

    public int getLinkNumber(){
        return numberOfLinks;
    }

    public String getNodeName(int i){
        return nodes[i].getName();
    }

    //true is a, false is b
    public String getFromDone(int i,boolean AorB){
        if(AorB){
            return done.get(i).getNodeA();
        } else {
            return done.get(i).getNodeB();
        }
    }

    public String getFromLink(int i,boolean AorB){
        if(AorB){
            return l[i].getNodeA();
        } else {
            return l[i].getNodeB();
        }
    }

    public int doneSize(){
        return done.size();
    }

    public int getLinkWeight(int i){
        return l[i].getWeight();
    }

    /*this method returns the x value from a node if true and the y if false*/
    public int getCoordinate(int number,boolean yes){
        if(yes){
            return nodes[number].getX();
        } else {
            return nodes[number].getY();
        }
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
            nodes[i].setX(Integer.parseInt(read.getData(1,count)));
            nodes[i].setY(Integer.parseInt(read.getData(2,count)));
            nodes[i].setCost(1000);
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

    /*this method takes a string and returns the node with the name of that string*/
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

    /* this method takes in a node name and returns the shortest path
    to each other node */
    public void shortestPath(String Start){
        done.add(new Link()); 
        done.get(0).addWeight(0);
        done.get(0).addLinkA(getNode(Start));
        done.get(0).addLinkB(getNode(Start));
        int num=1;
        int baseWeight=0;

        while (loopy){
            System.out.println("");
            System.out.println("num before: "+num);
            System.out.println("start is: "+Start);
            getLinks(Start); //adds links that have nodes the same as start to an array (??)

            //goes through all the links in the arraylist todo, 
            //checks if they've been processed before, if not, 
            //then enqueues them to my priority 
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
                System.out.println("() adding to done: "+done.get(num).getName());
                System.out.println("() baseweight is "+baseWeight);

            } else {
                Link dummy=q.getFront();
                done.add(findNextFollower(dummy));
                baseWeight=done.get(num).getWeight();
                System.out.println("//() adding to done: "+done.get(num).getName());
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

    /* this method returns true if there is a link with the inputted 
     * name in the queue */
    public boolean checknQueue(Node s){
        Node a =nq.getFront();
        for(int i=0;i<=(nq.len()-1);i++){
            if(s.equals(a)){
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

    //basically a getLinks method but it looks through the done arraylist
    public void findLinksDone(String n){
        todo.clear();
        for(int i=0;i<done.size();i++){
            if(n.equals(done.get(i).getNodeA())|| n.equals(done.get(i).getNodeB())){
                todo.add(done.get(i));
            } 
        }
    }

    /*this method adds all the links with the right name 
     * to an arraylist */
    public void getLinks(String n){
        if(n==null){
            System.out.println("trying to find links for null");
            return;
        }
        if(l.length!=numberOfLinks){
            System.out.println("link number doesn't match");
        }
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

    public boolean checkDoneN(String n){
        System.out.println("check-n is: "+n);
        boolean running=true;
        int counter=0;
        while(running){
            if(n.equals(doneN.get(counter).getName())){

                System.out.println("check-comparing to: "+doneN.get(counter).getName());
                running=false;
                return true;
            } else if(counter>=doneN.size()-1){
                System.out.println("check-false");
                running=false;
            } else if(doneN.get(counter)==null){
                System.out.println("check-null.");
                running=false;
            }
            else {
                counter++;
            }

        }
        return false;
    }

    public int thisCost(String n){
        for(int i=0;i<doneN.size();i++){
            if(n==doneN.get(i).getName()){
                System.out.println("node: "+doneN.get(i).getName()+" cost: "+doneN.get(i).getCost());
                return doneN.get(i).getCost();
            }
        }
        return 0;
    }
    
    

    public void shortest(String starting){
        int num=0;
        baseWeight=0;
        getNode(starting).setCost(0);
        getNode(starting).addPrevious(getNode(starting));
        doneN.add(getNode(starting));
        Node startingNode;
        System.out.println("starting node: "+starting);

        while(loopy){
            System.out.println("");
            System.out.println("num is: "+num);

            if(num>0){
                System.out.println("before dequeue: "+starting);
                startingNode=nq.dequeue();
                starting=startingNode.getName();
                System.out.println("start before check: "+starting);
                while(checkDoneN(starting)){
                    System.out.println("node already done: "+starting);
                    startingNode=nq.dequeue();
                    starting=startingNode.getName();
                }
            }
            System.out.println("real starting: "+starting);

            startingNode=getNode(starting);
            getLinks(starting);

            /*adds the previous node to all the nodes connected to the 'starting' node
            and also adds the weight! and enqueues if it hasn't been processed before*/
            for(int i=0;i<todo.size();i++){
                System.out.println("i is: "+i); 
                todoN.add(todo.get(i).getOtherN(starting));
                System.out.println("todoN adding: "+todoN.get(i).getName());
                if(!checkDoneN(todoN.get(i).getName())){
                    todoN.get(i).addPrevious(startingNode);
                    todoN.get(i).setCost(todo.get(i).getWeight());

                    System.out.println("adding previous node ("+startingNode.getName()+") to "+todoN.get(i).getName());
                    System.out.println("cost: "+todoN.get(i).getCost());
                    System.out.println("adding on: "+thisCost(starting));
                    todoN.get(i).addOnCost(thisCost(starting));

                    if(!checknQueue(todoN.get(i))){
                        nq.doEnqueue(todoN.get(i));
                    } else {
                        
                    }
                    System.out.println("enqueueing: "+todoN.get(i).getName());
                } else {
                    System.out.println(todoN.get(i).getName()+" already added.");
                }
            }
            todoN.clear();
            todo.clear();

            num++;

            if(numberOfNodes-1==doneN.size()){
                loopy=false;
            }
        }
        for(int i=0;i<doneN.size();i++){
            System.out.print("shortest path to: "+doneN.get(i).getName());
            System.out.println(" is from: "+doneN.get(i).getPreviousName());
        }
    }

}