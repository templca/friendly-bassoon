
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
    
}
