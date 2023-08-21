
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
    ArrayList<Link> todo = new ArrayList<Link>();
    ArrayList<Node> shortest = new ArrayList<Node>();

    ArrayList<Node> doneN = new ArrayList<Node>();

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
        System.out.println("from s, node: "+shortest.get(n).getName());
        return shortest.get(n).getName();
    }

    public Node getNodeShortest(int n){
        return shortest.get(n);
    }

    public String getPFShortest(int n){
        System.out.println("from p, node: "+shortest.get(n).getPreviousName());
        return shortest.get(n).getPreviousName();
    }

    public boolean checkShortest(String n){
        for(int i=0;i<shortest.size();i++){
            if(n.equals(shortest.get(i).getName())){
                return true;
            }
        }
        return false;
    }

    public void test(){
        setFileName("data2.csv");
        checkingAlgorithm("s");
        shortestPath("s","d");
        Link n=findLink(shortest.get(0));
        System.out.println("link found is: "+n.getName());
    }

    public int getSCost(int n){
        return shortest.get(n).getCost();
    }

    public void setFileName(String name){
        read.setFile(name);
        if(read.hasErrorOccurred()){
            System.out.println("error has occured in file");
        } else {
            initialise();
        }
    }

    public Link findLink(Node n){
        System.out.println("node name is: "+n.getName());
        System.out.println("previous is is: "+n.getPreviousName());
        for(int i=0;i<l.length;i++){
            if(n.getName().equals(l[i].getNodeA()) || n.getName().equals(l[i].getNodeB())){
                System.out.println("node matches.");
                System.out.println("node  A is: "+l[i].getNodeA());
                System.out.println("node  B is: "+l[i].getNodeB());
                if(n.getPreviousName().equals(l[i].getNodeA()) || n.getPreviousName().equals(l[i].getNodeB())){
                    System.out.println("node  A is: "+l[i].getNodeA());
                    System.out.println("node  B is: "+l[i].getNodeB());
                    System.out.println("previous matches");
                    return l[i];
                }
            }
            System.out.println("not a match");
        }
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
        System.out.println("number of nodes: "+numberOfNodes);
        numberOfLinks=Integer.parseInt(read.getData(1,0));
        System.out.println("number of links: "+numberOfLinks);
        int x=numberOfNodes+numberOfLinks;
        System.out.println("nodes+links: "+x);
        System.out.println("lines: "+(read.getLines()-3));
        if(numberOfNodes+numberOfLinks != read.getLines()-3){

            graphError=true;
            System.out.println("error occurred");
        } else {
            l = new Link[numberOfLinks];
            nodes = new Node[numberOfNodes];
            int count=1;
            for (int i=0;i<numberOfNodes;i++){

                if(read.getData(0,count)=="end nodes"){
                    i=numberOfNodes;
                } else {
                    System.out.println("0: "+read.getData(0,count));
                    System.out.println("2: "+read.getData(2,count));
                    System.out.println("1: "+read.getData(1,count));
                    //if there is a gap in the file being read, then 
                    if(read.getData(0,count)=="" || read.getData(2,count)=="" || read.getData(1,count)==""){
                        graphError=true;
                        System.out.println("graph error");
                    }else {
                        nodes[i]=new Node(read.getData(0,count));
                        System.out.println("addincg node: "+nodes[i].getName());
                        nodes[i].setX(Integer.parseInt(read.getData(1,count)));
                        nodes[i].setY(Integer.parseInt(read.getData(2,count)));
                        System.out.println("coordinates: "+nodes[i].getX()+" , "+nodes[i].getY());
                        nodes[i].setCost(1000);

                    }
                    count++;
                }
            }

            System.out.println(read.getData(0,count));
            System.out.println("end nodes");
            if(read.getData(0,count).equals("end nodes")){
                System.out.println("nodes are correct");
            } else {
                System.out.println("error occurred with end nodes");
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
                        System.out.println("problem reading links");
                    } else {
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
                    }

                    num++;
                }

                System.out.println(read.getData(0,num));
                System.out.println("end links");
                if(read.getData(0,num).equals("end links")){
                    System.out.println("links are correct");
                } else {
                    System.out.println("error occurred with end links");
                    graphError=true;
                }
            }
        }

        System.out.println("graph error is: "+hasGraphError());

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
                System.out.println("returning true");
                return true;
            } else {
                a=a.getFollower();
            }
        }
        System.out.println("node not found in queue.");
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
            } else {
                System.out.println("node: "+nodes[i].getName()+" complete!");
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

    public boolean checkNode(String s){
        for(int i=0;i<nodes.length;i++){
            if(s.equals(nodes[i].getName())){
                return true;
            }
        }
        return false;
    }

    public void checkingAlgorithm(String start){
        if(checkNode(start)){
            algorithm(start);
        } else{
            System.out.println("--node not found!");
            graphError=true;
        }
    }

    public void algorithm(String starting){
        resetNodes();
        int num=0;
        baseWeight=0;
        Node startingNode=getNode(starting);
        startingNode.setCost(0);
        startingNode.addPrevious(startingNode);

        System.out.println("starting node: "+startingNode.getName());
        System.out.println("starting cost: "+startingNode.getCost());

        while(loopy){
            System.out.println("");
            System.out.println("num is: "+num);

            //this checks just in case there are nodes with no links attached
            System.out.println("..before queue: "+nq.queueEmpty());
            if(nq.queueEmpty() && !everythingComplete()){
                for(int i=0;i<nodes.length;i++){
                    System.out.println("node is: "+nodes[i].getName());
                    if(!nodes[i].checkComplete()){
                        System.out.println("not complete");
                        getLinks(nodes[i].getName());
                        System.out.println("todo size becomes: "+todo.size());
                        if(todo.size()==0){
                            nodes[i].isComplete();
                            System.out.println("node: "+nodes[i].getName()+" has no links. completed.");
                        }
                        System.out.println("todo cleared");
                        todo.clear();
                    }
                }
            }
            if(num>0 || !nq.queueEmpty()){
                startingNode=nq.dequeue();
                System.out.println("startingNode is: "+startingNode.getName());
                while(startingNode.checkComplete() && !nq.queueEmpty()){
                    startingNode=startingNode.getFollower();
                    System.out.println("already complete.");
                }
            }
            System.out.println("now starting with: "+startingNode.getName());

            getLinks(startingNode.getName());

            /*adds the previous node to all the nodes connected to the 'starting' node
            and also adds the weight! and enqueues if it hasn't been processed before*/
            for(int i=0;i<todo.size();i++){
                int currentCost=startingNode.getCost();
                System.out.println("current cost: "+currentCost);

                System.out.println("node a: "+todo.get(i).getNodeA());
                System.out.println("node b: "+todo.get(i).getNodeB());
                int nextCost=currentCost+todo.get(i).getWeight();
                System.out.println("next cost is: "+nextCost);

                Node otherNode=todo.get(i).getOtherN(startingNode.getName());
                System.out.println("other node: "+otherNode.getName());
                int otherCost=otherNode.getCost();
                System.out.println("other cost: "+otherCost);

                if(nextCost<otherCost){
                    if(checknQueue(otherNode)){
                        nq.remove(otherNode);
                        System.out.println("removing..: "+otherNode.getName()+" cost; "+otherNode.getCost());
                    }
                    otherNode.setCost(nextCost);
                    System.out.println("new other cost: "+otherNode.getCost());
                    otherNode.addPrevious(startingNode);
                    System.out.println("adding previous ("+otherNode.getPreviousName()+") to "+otherNode.getName());
                    System.out.println("enqueueing: "+otherNode.getName());
                    printQueue();
                    nq.doEnqueue(otherNode);
                    System.out.println("---");
                    printQueue();
                } else if (nextCost==otherCost){
                    System.out.println("path is the same length.");
                } else {
                    System.out.println("already added.");
                }
            }
            System.out.println(startingNode.getName()+" is now complete");
            startingNode.isComplete();
            doneN.add(startingNode);
            todo.clear();

            for(int i=0;i<doneN.size();i++){
                System.out.print("---shortest path to: "+doneN.get(i).getName());
                System.out.println(" is from: "+doneN.get(i).getPreviousName());
            }

            num++;

            if(everythingComplete()){
                loopy=false;
            }

        }
        for(int i=0;i<doneN.size();i++){
            System.out.print("shortest path to "+doneN.get(i).getName());
            System.out.print(" is from "+doneN.get(i).getPreviousName());
            System.out.println(" with cost: "+doneN.get(i).getCost());
        }
    }

    public int findNodesDone(Node s){
        for(int i=0;i<doneN.size();i++){
            if(s==doneN.get(i)){
                System.out.println(i+" number in array");
                return i;
            }
        }
        System.out.println("no node found.");
        return 0;
    }

    public void checkingPath(String start,String end){
        if(checkNode(start) && checkNode(end)){
            shortestPath(start, end);
        } else{
            System.out.println("node entered does not match");
            graphError=true;
        }
    }

    public void shortestPath(String start,String end){
        shortest.clear();
        boolean running=true;
        shortest.add(doneN.get((findNodesDone(getNode(end)))));
        Node endNode=shortest.get(0);
        System.out.println("adding: "+endNode.getName());
        while(running){
            shortest.add(endNode.getPrevious());
            System.out.println(".adding: "+endNode.getName());
            endNode=endNode.getPrevious();
            System.out.println("new end: "+endNode.getName());

            if(endNode==getNode(start)){
                System.out.println("---");
                running=false;
            }

        }

        for(int i=0;i<shortest.size();i++){
            System.out.println("END: "+shortest.get(i).getName());
        }

    }
}