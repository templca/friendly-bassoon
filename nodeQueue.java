
/**
 * Simple queue class that works with generic Nodes.
 *
 * @author Bill Viggers
 * @version 21-May-2020
 * 13-June-2023 - added features for priority
 */
public class nodeQueue<O>
{
    private Node front;  // Node at the front of the queue
    private Node end;    // Node at the end of the queue

    private Node r;

    /**
     * Constructor for objects of class nodeQueue
     */
    public nodeQueue()
    {
        this.front=null;
        this.end=null;
    }

    /*
     * Make a new Node with the Item in it.
     * Add it to the end of the queue
     */

    /*void enqueue(O item){
    Node box = new Node(item); // Wrap our item up in a box to put in the queue
    doEnqueue(box);
    }  enqueue

    void enqueue (O item, int pri){
    Node box = new Node(item,pri);
    doEnqueue(box); }
     */

    void doEnqueue(Node box){
        // first check special cases where this.front needs updating.
        if (this.front == null) { // empty queue
            this.front=box;
            this.end=box;
        } else  if (this.front.getCost() > box.getCost())  {// insert at the front of the queue
            
            box.addFollower(this.front);
            this.front=box;
        } else {
           
            insert(this.front, box); // recursive insert
        }
    }

    // insert into the queue in a recursive manner.
    private void insert (Node after, Node toInsert){
        if (after.getFollower() == null){ // end of queue
            after.addFollower(toInsert);
            this.end=toInsert;
        } // insert at end of queue 
        else  // check to see if we insert directly between after and its follower
        if (after.getFollower().getCost() > toInsert.getCost()){ // we need to insert it   
            toInsert.addFollower(after.getFollower());
            after.addFollower(toInsert);
        } // insert directly after "after"
        else insert(after.getFollower(),toInsert);  // kick the problem further down the queueu.

    } // recursive insert

    /*
     * Remove the front item from the queue
     */
    Node dequeue(){
        Node hold=this.front;
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
        Node current = this.front;  // Node we are adding.
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
    public int lenR(Node current){
        if (current==null) return 0;
        else return lenR(current.getFollower())+1;
    }

    public Node getFront(){
        return front;
    }

    public Node findInFront(Node a){
        r=front;
        return find(a);
    }

    public Node find(Node a){
        if(a==front){
            System.out.println(".."+a.getName());
            return a;
        }
        if(this.r.getFollower()==a){
            System.out.println("..."+this.r.getName());
            return this.r;
        }else {
            this.r=this.r.getFollower();
            return find(a);
        }
    }

    public void remove(Node removee){
        if(removee==front){
            dequeue();
        }else if (removee==end || removee.getFollower()==null){
            findInFront(removee).addFollower(null);
        } else {

            findInFront(removee).addFollower(removee.getFollower());
        }

    }

}