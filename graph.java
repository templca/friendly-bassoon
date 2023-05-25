
/**
 * cara templeton
 * 
 * 18/05
 * 
 * netowork
 */
public class graph
{
    // instance variables - replace the example below with your own

    private int size=4;
    Node[] graph = new Node[size];
    /**
     * Constructor for objects of class Network
     */
    public graph()
    {


    }

    public void add(){
        Node NodeA = new Node("A");
        Node NodeB = new Node("B");
        NodeA.addOrigin(NodeB);
        Link AB = new Link();
        AB.addLinkA(NodeA);
        AB.addLinkB(NodeB);
        AB.addWeight(3);
        AB.showLink();
    }

    public int getSize(){
        return size;
    }

    public void addSize(int size){
        this.size=size;
    }

}
