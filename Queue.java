
/**
 * Simple queue class that works with generic Links.
 *
 * @author Bill Viggers
 * @version 21-May-2020
 * 13-June-2023 - added features for priority
 */
public class Queue<O>
{
    private Link front;  // Link at the front of the queue
    private Link end;    // Link at the end of the queue

    /**
     * Constructor for objects of class Queue
     */
    public Queue()
    {
        this.front=null;
        this.end=null;
    }

    /*
     * Make a new Link with the Item in it.
     * Add it to the end of the queue
     */

    /*void enqueue(O item){
        Link box = new Link(item); // Wrap our item up in a box to put in the queue
        doEnqueue(box);
    }  enqueue

    void enqueue (O item, int pri){
        Link box = new Link(item,pri);
        doEnqueue(box); }
    */

    void doEnqueue(Link box){
        // first check special cases where this.front needs updating.
        if (this.front == null) { // empty queue
            this.front=box;
            this.end=box;
        } else  if (this.front.getWeight() > box.getWeight())  {// insert at the front of the queue
            box.addFollower(this.front);
            this.front=box;
        } else insert(this.front, box); // recursive insert
    }

    // insert into the queue in a recursive manner.
    private void insert (Link after, Link toInsert){
        if (after.getFollower() == null){ // end of queue
            after.addFollower(toInsert);
            this.end=toInsert;
        } // insert at end of queue 
        else  // check to see if we insert directly between after and its follower
        if (after.getFollower().getWeight() > toInsert.getWeight()){ // we need to insert it   
            toInsert.addFollower(after.getFollower());
            after.addFollower(toInsert);
        } // insert directly after "after"
        else insert(after.getFollower(),toInsert);  // kick the problem further down the queueu.

    } // recursive insert

    /*
     * Remove the front item from the queue
     */
    Link dequeue(){
        Link hold=this.front;
        front=this.front.getFollower();
        if (front==null) end=null;
        return hold;
    } // dequeue

    /*
     * Says if the queue is empty
     */

    public boolean queueEmpty(){
        return this.front==null;
    }

    // Method to produce current length of list
    public int len(){
        int sofar=0; // counter for how many things we have gone through
        Link current = this.front;  // Link we are adding.
        while (current != null){
            sofar++;
            current=current.getFollower();
        }
        return sofar;
    }

    // Recursive version of length.
    public int lenREntry(){
        return lenR(this.front);
    }
    // recursive method to get length of list.  Needs an entry method
    public int lenR(Link current){
        if (current==null) return 0;
        else return lenR(current.getFollower())+1;
    }
    
    public Link getFront(){
        return front;
    }
    
    

}
