
/**
 * cara templeton
 * 
 * 18/05
 * 
 * handles the links between nodes
 */
public class Link
{
    // instance variables - replace the example below with your own
    private Node x;
    private Node y;
    private int weight;

    /**
     * Constructor for objects of class Link
     */
    public Link()
    {
        
    }
    
    public void addLinkA(Node x){
        this.x=x;
    }
    
    public void addLinkB(Node y){
        this.y=y;
    }
    
    public void addWeight(int weight){
        this.weight=weight;
    }
    
    public int getWeight(){
        return weight;
    }
    
    public String showLink(){
        System.out.println(x.getName()+" links to "+y.getName()+". weight: "+weight);
        return x+"links to"+y;
    }
}
