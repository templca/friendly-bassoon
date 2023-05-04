
/**
 * cara templeton
 * 
 * 4/05
 * 
 * this class does queues, it adds nodes to the queue and removes them aswell.
 */
public class queue
{
    // instance variables - replace the example below with your own
    private int x;
    private Node front;
    private Node back;

    /**
     * Constructor for objects of class queue
     */
    public queue()
    {
        // initialise instance variables
    }
    
    public void enqueue(Node newNode){
        queueEmpty();
        if (queueEmpty()){
            front=newNode;
            back=newNode;
        } else {
            back.addFollower(newNode);
            back=newNode;
        }
    }
    
    public Node dequeue(){
        Node remember;
        queueEmpty();
        if (queueEmpty()){
            System.out.println("queue is already empty, cannot remove");
            return null;
        } else if (front==back){
            remember=this.front;
            front=remember.getFollower();
            back=null;
            return remember;
        } else {
            remember=this.front;
            front=remember.getFollower();
        }
        return remember;
    }

    public boolean queueEmpty(){
        if (front==null){
            return true;
        } else {
            return false;
        }
    }
}
