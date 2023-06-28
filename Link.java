
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
    private int weightAdded;
    private Link follower;
    String name;
    int runB=0;

    /**
     * Constructor for objects of class Link
     */
    public Link()
    {

    }

    public Link(String name){
        this.name=name;
    }

    public void addLinkA(Node x){
        this.x=x;
        name=x.getName();
    }

    public void addLinkB(Node y){
        if(runB==0){
            this.y=y;
            name=name+y.getName(); 
        } else {
            this.y=y;
        }
    }

    public String getName(){
        return name;
    }

    public void addWeight(int weight){
        this.weight=weight;
    }

    public void addOnWeight(int on){
        weightAdded=on;
    }

    public int getWeight(){
        return weight+weightAdded;
    }

    public String showLink(){
        System.out.println(x.getName()+" links to "+y.getName()+". weight: "+weight);
        return x+"links to"+y;
    }

    public void addFollower(Link follower){
        this.follower=follower;
    }

    public String getFollowerName(){
        return this.follower.getName();
    }

    public Link getFollower(){
        return this.follower;
    }

    public String getNodeA(){
        return x.getName();
    }

    public String getNodeB(){
        return y.getName();
    }

    public String getOther(String c){
        if(c.equals(getNodeA())){
            return getNodeB();
        } else if(c.equals(getNodeB())){
            return getNodeA();
        }
        return null;
    }
}