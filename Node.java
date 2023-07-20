
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
    private int x;
    private int y;
    
    private int cost;
    private Node previousNode;
    
    private Node follower;


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
}
