
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
            for (int j=0;j<=(numberOfNodes-1);j++){
                l[i].addLinkA(getNode(nodeName));
            }
            String nodeName1=read.getData(1,num);
            for (int j=0;j<=(numberOfNodes-1);j++){
                l[i].addLinkB(getNode(nodeName1));
            }

            num++;
        }

    }

    public Node getNode(String n){
        boolean yes=true;
        int x=0;
        while(yes){
            if (n.equals(nodes[x].getName())){
                yes=false;
            } else if(x>=(numberOfNodes-1)){
                yes=false;
                return null;
            } else {
                x++;
            }
        }
        return nodes[x];
    }

    public Link shortestPath(){
        for (i=0;i<=(numberOfLinks-1);i++){
            
            q.enqueue(l[i]);
        }

        return q.dequeue();
    }
    
    public Link sWeight(){
        boolean running=true;
        int x=0;
        while(running){
            if(l[x].getWeight()<l[x++].getWeight()){
                running=false;
            } else if(x++>=(numberOfLinks-1)){
                return 
            }
            }
            else {
                
            }
        }
        return l[x];
    }

    public String intoString(char letter){
        String n1=String.valueOf(letter);
        return n1;
    }

}