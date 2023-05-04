
/**
 * cara templeton
 * 
 * 4/05
 * 
 * this class handles the nodes which are the items in the queue
 */
public class Node
{
    // instance variables - replace the example below with your own
    private int x;
    private String name;
    private Node follower;
    private int connectionWeight;

    /**
     * Constructor for objects of class Node
     */
    public Node()
    {
        name="unavailable";
    }

    public Node(String name){
        this.name=name;
    }
    
    public void addFollower(Node follower){
        this.follower=follower;
    }
    
    public void newFollower(Node follower){
        
    }
    
    public Node getFollower(){
        return this.follower;
    }
    
    public void setWeight(int weight){
        weight=connectionWeight;
    }
}
