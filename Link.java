
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
    private Node x; //one side of the link
    private Node y; //what it links to.
    private int weight; //the cost of this link.
    String name;
    int times;

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
        if(times>0){
            this.y=y;
        } else {
            this.y=y;
            name=name+"/"+y.getName(); 

        }
        times++;
    }

    public String getName(){
        return name;
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

    public Node getOtherN(String c){
        if(c.equals(getNodeA())){
            return y;
        } else if(c.equals(getNodeB())){
            return x;
        }
        return null;
    }

}