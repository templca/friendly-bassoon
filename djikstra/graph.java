
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

    private int size;

    /**
     * Constructor for objects of class Network
     */
    public graph()
    {

    }
    public void add(){
        Node NodeA = new Node("A");
        Node NodeB = new Node("B");
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

    public void make(int size){
        char n='a';
        this.size=size;
        Node[] nodes = new Node[size];
        for(int i=0;i>=size;i++){
            String n1=String.valueOf(n);
            nodes[i].addName(n1);
            nodes[i].getName();
            n++;
        }
        nodes[0].getName();
    }

    public void newLink(Node first, Node second, int weight){
        Link A = new Link();
        A.addLinkA(first);
        A.addLinkB(second);
        A.addWeight(weight);
    }
}
