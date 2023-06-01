
/**
 * cara templeton
 * 
 * 
 * 12/05
 * makes a queue
 */
public class queue
{

    private Element front;
    private Element back;

    /**
     * Constructor for objects of class queue
     */
    public queue()
    {

    }

    public void enqueue(Element person){
        queueEmpty();
        if (queueEmpty()==true){
            front=person;
            back=person;
        } else {
            back.addFollower(person);
            back=person;
        }
    }

    public Element dequeue(){
        Element remember;
        queueEmpty();
        if (queueEmpty()==true){
            System.out.println("queue is empty, can't remove");
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
        if (front==null) {
            return true;
        } else {
            return false;
        }
    }
    
}
