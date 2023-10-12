
/**
 * cara templeton
 * 
 * 18/05
 * 
 * graph class. Does the algorithm.
 */

import java.util.Scanner;
import java.util.ArrayList;
public class graph
{
    // instance variables - replace the example below with your own
    
    //a priority queue of nodes-
    nodeQueue nq;
    
    //array that stores all the links.
    Link[] l;
    
    //array that stores nodes.
    Node[] nodes;
    
    //reads the file.
    filereader read = new filereader();

    int numberOfNodes;
    int numberOfLinks;
    
    //holds the weight number.
    int weight;
    
    //a counter that starts at 1 not 0.
    int num=1;
    int counter;
    
    //stores the name of the node.
    String nodeName;
    
    //the while loop for the algorithm. is true until all nodes have been processed.
    boolean loopy=true;
    
    
    //an arraylist that holds that nodes that need to be processed.
    ArrayList<Link> todo = new ArrayList<Link>();
    
    //an arraylist that holds the nodes which are the shortest path.
    ArrayList<Node> shortest = new ArrayList<Node>();

    //holds the nodes which have been processed.
    ArrayList<Node> doneN = new ArrayList<Node>();

    //becomes true whenever there is an error (like a gap in the file.)
    boolean graphError=false;

    /**
     * Constructor for objects of class Network
     */
    public graph()
    {
        nq = new nodeQueue();

    }

    public boolean hasGraphError(){
        return graphError;
    }

    public boolean hasFileError(){
        return read.hasErrorOccurred();
    }

    public void resetGraphError(){
        graphError=false;
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

    public String getFromShortest(int n){
        return shortest.get(n).getName();
    }

    public Node getNodeShortest(int n){
        return shortest.get(n);
    }

    public String getPFShortest(int n){
        return shortest.get(n).getPreviousName();
    }

    /* returns true if there is a node with the name of the string in the 
    'shortest' arraylist. */
    public boolean checkShortest(String n){
        for(int i=0;i<shortest.size();i++){
            if(n.equals(shortest.get(i).getName())){
                return true;
            }
        }
        return false;
    }

    public int getSCost(int n){
        return shortest.get(n).getCost();
    }

    /* checks beforehand if theres an error in the file reader class,
    so if there is, then the initialise method doesn't run. */
    public void setFileName(String name){
        read.setFile(name);
        if(read.hasErrorOccurred()){
            System.out.println("Error Occurred: Problem with file.");
        } else {
            initialise();
        }
    }

    /* goes through all the links and returns the link that has the right
    nodes attached. (same node name and previous node name) */
    public Link findLink(Node n){
        for(int i=0;i<l.length;i++){
            if(n.getName().equals(l[i].getNodeA()) || n.getName().equals(l[i].getNodeB())){
                if(n.getPreviousName().equals(l[i].getNodeA()) || n.getPreviousName().equals(l[i].getNodeB())){

                    return l[i];
                }
            }

        }
        System.out.println("No matches found.");
        return null;
    }

    public String getFromLink(int i,boolean A){
        if(A){
            return l[i].getNodeA();
        } else {
            return l[i].getNodeB();
        }
    }

    public int shortestSize(){
        return shortest.size();
    }

    public int getLinkWeight(int n){
        return l[n].getWeight();
    }

    public int getNodeCost(int c){
        return nodes[c].getCost();
    }

    /*this method returns the x value from a node if true and the y if false*/
    public int getCoordinate(int number,boolean X){
        if(X){
            return nodes[number].getX();
        } else {
            return nodes[number].getY();
        }
    }

    public void resetInitialise(){
        l=null;
        nodes=null;
    }

    //reads from file
    public void initialise(){

        graphError=false;
        resetInitialise();
        numberOfNodes=Integer.parseInt(read.getData(0,0));
        System.out.println("Number of Nodes: "+numberOfNodes);
        numberOfLinks=Integer.parseInt(read.getData(1,0));
        System.out.println("Number of Links: "+numberOfLinks);
        int x=numberOfNodes+numberOfLinks;
        if(numberOfNodes+numberOfLinks != read.getLines()-3){

            graphError=true;
            System.out.println("Error occurred: Nodes/Links do not match.");
        } else {
            l = new Link[numberOfLinks];
            nodes = new Node[numberOfNodes];
            int count=1;
            for (int i=0;i<numberOfNodes;i++){

                if(read.getData(0,count)=="end nodes"){
                    i=numberOfNodes;
                } else {
                    //if there is a gap in the file being read, then 
                    if(read.getData(0,count)=="" || read.getData(2,count)=="" || read.getData(1,count)==""){
                        graphError=true;
                        System.out.println("Error occurred: Gap in nodes.");
                    }else {
                        nodes[i]=new Node(read.getData(0,count));
                        System.out.println("Adding Node: "+nodes[i].getName());
                        try{
                            nodes[i].setX(Integer.parseInt(read.getData(1,count)));
                            nodes[i].setY(Integer.parseInt(read.getData(2,count)));
                        } catch(Exception e) {
                            graphError=true;
                            System.out.println("Error occurred: Coordinates are not integers.");
                        }
                        nodes[i].setCost(1000);

                    }
                    count++;
                }
            }
            System.out.println("");

            System.out.println(read.getData(0,count));
            if(read.getData(0,count).equals("end nodes")){
                System.out.println("Nodes are correct!");
            } else {
                System.out.println("Error occurred: Node number incorrect.");
                graphError=true;
            }

            if(read.hasErrorOccurred()){
                graphError=true;
            }
            if(!graphError){

                for (int i=0;i<=(numberOfLinks-1);i++){
                    l[i]=new Link();
                }

                num=numberOfNodes+2;

                for (int i=0;i<numberOfLinks;i++){
                    if(read.getData(2,num)==null||getNode(read.getData(0,num))==null||getNode(read.getData(1,num))==null){
                        graphError=true;
                        System.out.println("Error occurred: Gap in Links.");
                    } else {
                        try{
                            weight=Integer.parseInt(read.getData(2,num));
                        } catch(Exception e){
                            graphError=true;
                            System.out.println("Error occurred: Weight is not an integer.");
                        }
                        l[i].addWeight(weight);

                        nodeName=read.getData(0,num);
                        l[i].addLinkA(getNode(nodeName));

                        String nodeName1=read.getData(1,num);
                        l[i].addLinkB(getNode(nodeName1));
                    }

                    num++;
                }

                System.out.println(read.getData(0,num));
                if(read.getData(0,num).equals("end links")){
                    System.out.println("Links are correct!");
                } else {
                    System.out.println("Error occurred: Link number incorrect.");
                    graphError=true;
                }
            }
        }
        System.out.println("");

        if(hasGraphError()){
            System.out.println("Error Occurred: Problem with graph");
        }
        System.out.println("");
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

    /*this method adds all the links with the right name 
     * to an arraylist */
    public void getLinks(String n){
        if(n==null){
            System.out.println("Error Occurred: Links are null.");
            return;
        }
        if(l.length!=numberOfLinks){
            System.out.println("Error Occurred: Link number doesn't match.");
        }
        for(int i=0;i<=(numberOfLinks-1);i++){
            if(n.equals(l[i].getNodeA())||n.equals(l[i].getNodeB())){
                if(todo.size()>0 && !checkLink(todo,l[i].getName())){
                    todo.add(l[i]);
                }else if(todo.size()==0){
                    todo.add(l[i]);
                }

            }
        }

    }

    public void printQueue(){
        if(nq.queueEmpty()){
            System.out.println("empty!");
        }
        Node n=nq.getFront();
        for(int i=0;i<nq.len();i++){
            System.out.print("QUEUE "+i+" : "+n.getName());
            System.out.println(" with a cost of "+n.getCost());
            if(i==nq.len()){
                System.out.println("end.");
            } else {
                n=n.getFollower();
            }
        }
    }

    public boolean everythingComplete(){
        int counter=0;
        for(int i=0;i<nodes.length;i++){
            if(!nodes[i].checkComplete()){
                counter++;
            } 
        }
        if(counter==0){
            return true;
        }
        return false;
    }

    public void resetNodes(){
        graphError=false;
        for(int i=0;i<nodes.length;i++){
            nodes[i].setCost(1000);
            nodes[i].resetComplete();
            nodes[i].addFollower(null);
        }
        doneN.clear();
        todo.clear();

        loopy=true;
    }

    /*returns true if a node with the same name as the passed in string 
    is in the nodes array*/
    public boolean checkNode(String s){
        for(int i=0;i<nodes.length;i++){
            if(s.equals(nodes[i].getName())){
                return true;
            }
        }
        return false;
    }

    /*  checks if the inputted start node actually exists, if it does then 
    the algorithm method runs.*/
    public void checkingAlgorithm(String start){
        if(checkNode(start)){
            algorithm(start);
        } else{
            System.out.println("Error Occurred: Node not found.");
            graphError=true;
        }
    }

    public void algorithm(String starting){
        resetNodes();
        int num=0;
        Node startingNode=getNode(starting);
        startingNode.setCost(0);
        startingNode.addPrevious(startingNode);

        while(loopy){
            //this checks just in case there are nodes with no links attached
            System.out.println("Checking for Nodes with no links.");
            if(nq.queueEmpty() && !everythingComplete()){
                for(int i=0;i<nodes.length;i++){
                    System.out.print(nodes[i].getName()+" is ");
                    if(!nodes[i].checkComplete()){
                        System.out.println(" not complete");
                        getLinks(nodes[i].getName());
                        if(todo.size()==0){
                            nodes[i].isComplete();
                            System.out.println(nodes[i].getName()+" has no links. Completed.");
                        }
                        todo.clear();
                    }
                }
            }

            if(nq.queueEmpty()){
                System.out.println("Queue Empty. Continuing.");
            }
            // checks if node is already complete so it doesn't process it twice.
            if(num>0 || !nq.queueEmpty()){
                startingNode=nq.dequeue();
                while(startingNode.checkComplete() && !nq.queueEmpty()){
                    startingNode=startingNode.getFollower();
                    System.out.println(startingNode.getName()+": Completed.");
                }
            }
            System.out.println("Now starting with: "+startingNode.getName());
            System.out.println("");
            getLinks(startingNode.getName());

            /*adds the previous node to all the nodes connected to the 'starting' node
            and also adds the weight! and enqueues if it hasn't been processed before*/
            for(int i=0;i<todo.size();i++){
                int currentCost=startingNode.getCost();
                System.out.print("Current cost to "+startingNode.getName());
                System.out.println(" is: "+currentCost);

                int nextCost=currentCost+todo.get(i).getWeight();

                Node otherNode=todo.get(i).getOtherN(startingNode.getName());
                int otherCost=otherNode.getCost();

                System.out.print("Cost to "+otherNode.getName()+" from "+startingNode.getName());
                System.out.println(" is: "+nextCost);
                System.out.println("Current cost to "+otherNode.getName()+" is: "+otherCost);

                if(nextCost<otherCost){
                    if(checknQueue(otherNode)){
                        System.out.println("Node already in queue. Removing..");
                        nq.remove(otherNode);
                    }
                    otherNode.setCost(nextCost);
                    otherNode.addPrevious(startingNode);
                    System.out.print("New shortest path to: "+otherNode.getName()+" is from "+startingNode.getName());
                    System.out.println(" with a cost of: "+otherNode.getCost());
                    nq.doEnqueue(otherNode);
                } else if (nextCost==otherCost){
                    System.out.println("Path is the same length.");
                } else {
                    System.out.println("Shortest path already found.");
                }
                System.out.println("");
            }
            System.out.println(startingNode.getName()+" is now processed.");
            startingNode.isComplete();
            doneN.add(startingNode);
            todo.clear();
            System.out.println("Everything complete? "+everythingComplete());
            System.out.println("");
            System.out.println("");
            num++;

            if(everythingComplete()){
                loopy=false;
            }

        }
    }

    public int findNodesDone(Node s){
        for(int i=0;i<doneN.size();i++){
            if(s==doneN.get(i)){
                return i;
            }
        }
        System.out.println("Error Occurred: no node found.");
        return 0;
    }

    /*checks if the start and end nodes exist in the nodes array before 
     * running the methods so there are no errors*/
    public void checkingPath(String start,String end){
        if(checkNode(start) && checkNode(end)){
            shortestPath(start, end);
        } else{
            System.out.println("Error Occurred: node entered does not match.");
            graphError=true;
        }
    }

    /* the algorithm method finds the shortest path to every node from the
    starting one. the shortest path method takes a end string and finds the
    shortest path from the inputted start node to the end node. */
    public void shortestPath(String start,String end){
        shortest.clear();
        boolean running=true;
        shortest.add(doneN.get((findNodesDone(getNode(end)))));
        Node endNode=shortest.get(0);
        while(running){
            shortest.add(endNode.getPrevious());
            endNode=endNode.getPrevious();

            if(endNode==getNode(start)){
                running=false;
            }

        }
        int n=shortest.size()-1;
        for(int i=0;i<shortest.size();i++){
            System.out.print("Shortest path to "+shortest.get(n).getName());
            System.out.print(" is from "+shortest.get(n).getPreviousName());
            System.out.println(" with cost: "+shortest.get(n).getCost());
            n=n-1;
        }

    }
}