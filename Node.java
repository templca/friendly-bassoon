
/**
 * cara templeton
 * 
 * 18/05
 * 
 * is a node
 */
public class Node
{
    // instance variables - replace the example below with your own
    private String name;
    private int x; //x coordinate.
    private int y; //y coordinate.
    
    private int cost; //cost to get to this node from previous node.
    private Node previousNode; //the other side of the link
    
    private Node follower; //when it is in a queue, it'll store what is behind it.
    
    private boolean complete=false; //whether this node has been processed.


    /**
     * Constructor for objects of class Node
     */
    public Node()
    {
        // initialise instance variables
        
    }
    
    public Node(String name){
        this.name=name;
    }
    
    public void addName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
    
    public void setX(int number){
        x=number;
    }
    
    public void setY(int number){
        y=number;
    }
    
    public int getX(){
        return x;
    }
    
    
    public int getY(){
        return y;
    }
    
    public void addFollower(Node follower){
        this.follower=follower;
    }

    public String getFollowerName(){
        return this.follower.getName();
    }

    public Node getFollower(){
        return this.follower;
    }
    
    public void setCost(int cost){
        this.cost=cost;
    }
    
    public int getCost(){
        return this.cost;
    }
    
    public void addOnCost(int baseWeight){
        cost=cost+baseWeight;
    }
    
    public void addPrevious(Node previous){
        this.previousNode=previous;
    }

    public String getPreviousName(){
        return this.previousNode.getName();
    }

    public Node getPrevious(){
        return this.previousNode;
    }
    
    public void isComplete(){
        complete=true;
    }
    
    public void resetComplete(){
        complete=false;
    }
    
    public boolean checkComplete(){
        return complete;
    }
}