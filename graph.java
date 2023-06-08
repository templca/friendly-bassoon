
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
    Link[] l;
    Node[] nodes;
    filereader read = new filereader();
    int numberOfNodes;
    int numberOfLinks;
    int weight;
    int num=1;
    String nodeName;
    int hh;
    int b;

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
    
    public void initialise(){
        numberOfNodes=Integer.parseInt(read.getData(0,0));
        numberOfLinks=(read.getLines()-1);
        l = new Link[numberOfLinks];
        nodes = new Node[numberOfNodes];
        for (i=0;i<=(numberOfNodes-1);i++){
            nodes[i]=new Node(intoString(n));
            n++;
        }
        
        for (i=0;i<=(numberOfLinks-1);i++){
            l[i]=new Link();
        }
        
        for (i=0;i<=(numberOfLinks-1);i++){
            weight=Integer.parseInt(read.getData(2,num));
            l[i].addWeight(weight);
            
            nodeName=read.getData(0,num);
            
            num++;
        }
        
        
        
    }
    
    public int intoInt(){
        return Integer.parseInt(read.getData(2, 1));
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

    public String intoString(char letter){
        String n1=String.valueOf(letter);
        return n1;
    }
    
}