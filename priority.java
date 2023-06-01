
/**
 * cara
 * 31/03
 * uses the queue class to make a priority queue
 */
public class priority
{
    // instance variables - replace the example below with your own
    private queue lowP;
    private queue highP;

    /**
     * Constructor for objects of class priority
     */
    public priority()
    {
        lowP = new queue();
        highP = new queue();
    }
    
    public void enqueue(Element name, boolean high){
        if (high){
            highP.enqueue(name);
        } else lowP.enqueue(name);
    }
    
    Element dequeue(){
        if(highP.queueEmpty()){
            return lowP.dequeue();
        } else 
            return highP.dequeue();
    }
    
    boolean queueEmpty(){
        if(highP.queueEmpty() && lowP.queueEmpty())
            return true;
            else return false;
    }
    
    public boolean more(int num){
        num=
    }
}

