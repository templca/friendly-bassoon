
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
    Link SS;
    linkqueue q;
    int lin=4;
    String nam;
    Link[] links = new Link[lin];
    Node[] nodes;

    int i; //for loop counter
    /**
     * Constructor for objects of class Network
     */
    public graph()
    {
        NodeS = new Node("S");
        NodeA = new Node("A");
        NodeB = new Node("B");
        SS = new Link();
        SA = new Link();
        SB = new Link();
        AB = new Link();
        q = new linkqueue();

        SA.addLinkA(NodeS);
        SA.addLinkB(NodeA);
        SA.addWeight(2);

        SB.addLinkA(NodeS);
        SB.addLinkB(NodeB);
        SB.addWeight(5);

        AB.addLinkA(NodeA);
        AB.addLinkB(NodeB);
        AB.addWeight(2);

        SS.addLinkA(NodeS);
        SS.addLinkB(NodeS);
        SS.addWeight(0);

        links[0]=SS;
        links[1]=SA;
        links[2]=SB;
        links[3]=AB;

    }

    public Link shortestPath(Node Start){
        for (i=0;i<=(lin-1);i++){
            q.enqueue(links[i]);
        }
        
        
        return q.dequeue();
    }

    public int getSize(){
        return size;
    }

    public void addSize(int size){
        this.size=size;
    }

    public void make(int size){ 
        //this makes an arrya of nodes and then theorisucita,lly give ma,e to ehachj node int leter and then pritns the name oiut but it doesn't and it just give m esa a nbyll error
        this.size=size;
        nodes = new Node[size];
        for( i=0;i>(size-1);i++){
            nodes[i]= new Node(intoString(n));
            System.out.println(nodes[i].getName());
            n++;
        }


    }

    public String intoString(char letter){
        String n1=String.valueOf(letter);
        return n1;
    }
}