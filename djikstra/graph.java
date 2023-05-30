
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
    private char n='a';
    String hello;
    Node NodeS;
    Node NodeA;
    Node NodeB;
    Link SA;
    Link SB;
    Link AB;
    
    

    /**
     * Constructor for objects of class Network
     */
    public graph()
    {
        NodeS = new Node("S");
        NodeA = new Node("A");
        NodeB = new Node("B");
        SA = new Link();
        SB = new Link();
        AB = new Link();

        SA.addLinkA(NodeS);
        SA.addLinkB(NodeA);
        SA.addWeight(2);

        SB.addLinkA(NodeS);
        SB.addLinkB(NodeB);
        SB.addWeight(5);

        AB.addLinkA(NodeA);
        AB.addLinkB(NodeB);
        AB.addWeight(2);
    }

    public String shortestPath(Node Start){
        NodeS=Start;

        return hello;
    }

    public int getSize(){
        return size;
    }

    public void addSize(int size){
        this.size=size;
    }

    public void make(int size){
        this.size=size;
        Node[] nodes = new Node[size];
        for(int i=0;i>(size-1);i++){
            nodes[i]=new Node(intoString(n));
            nodes[i].addName(intoString(n));
            System.out.println(nodes[i].getName());
            n++;
        }
        System.out.println(nodes[2].getName());
    }

    public String intoString(char letter){
        String n1=String.valueOf(letter);
        return n1;
    }

    public void newLink(Node first, Node second, int weight){
        Link A = new Link();
        A.addLinkA(first);
        A.addLinkB(second);
        A.addWeight(weight);
    }
}
