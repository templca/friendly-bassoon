
/**
 * cara templeton
 * 
 * 
 * 12/05
 * makes a queue that uses links instead of elements
 */
public class linkqueue
{

    private Link front;
    private Link back;

    /**
     * Constructor for objects of class linkqueue
     */
    public linkqueue()
    {

    }

    public void enqueue(Link nlink){
        queueEmpty();
        if (queueEmpty()==true){
            front=nlink;
            back=nlink;
        } else {
            back.addFollower(nlink);
            back=nlink;
        }
    }
    
    public Link findFollower(Link y, Link n){
        Link x;
        x=y.getFollower();
        if(n.getWeight()<x.getWeight()){
            return x;
        } else if (back==x){
            return null;
        } else {
            return findFollower(x,n);
        }
    }

    public Link dequeue(){
        Link remember;
        queueEmpty();
        if (queueEmpty()==true){
            System.out.println("linkqueue is empty, can't remove");
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
    
    public int getCostfront(){
        return front.getWeight();
    }
    
    public Link getfront(){
        return front;
    }
}