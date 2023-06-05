
/**
 * cara templeton
 * 
 * 4/05
 * 
 * this class handles the Elements which are the items in the queue
 */
public class Element
{
    // instance variables - replace the example below with your own
    private int x;
    private String name;
    private Element follower;
    
    private Link l;

    /**
     * Constructor for objects of class Element
     */
    public Element()
    {
        name="unavailable";
    }

    public Element(String name){
        this.name=name;
    }
    
    public void addName(String name){
        this.name=name;
    }
    
    public String getName(){
        return name;
    }
    
    public void addFollower(Element follower){
        this.follower=follower;
    }
    
    public Element getFollower(){
        return this.follower;
    }
    
    public void addLink(Link l){
        this.l=l;
    }
    
    public Link getLink(Link l){
        return l;
    }
    
    public String getLinkName(){
        return l.getName();
    }
    
    public int getCost(){
        return l.getWeight();
    }
}