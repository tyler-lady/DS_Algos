class EmptyQueueE extends Exception{}

public class Queue<E> {
    private DoublyLinkedList<E> q;
    private int size;

    // TODO: default constructor
    public Queue(){
        q = new DoublyLinkedList<>();
        this.size = 0;
    }

    // TODO: Put element at end of queue
    public void enqueue(E elem){
        //insert the element at the end of the queue
        q.insertAtTail(elem);
        //increase size
        this.size++;
    }

    // TODO: Return the head of the queue; If there's nothing to return then throw EmptyQueueE
    public E dequeue() throws EmptyQueueE {
        //Init return value
        E val;
        //try catch for Exception handling, throw this method's error if DLL method throws exception
        try {
            val = q.deleteAtHead();
            this.size--;
        }catch(EmptyListE e){
            throw new EmptyQueueE();
        }
        return val;
    }

    // TODO: Without affecting the queue, return the element at the top of the queue
    public E peek() throws IndexOutOfBoundsException{
        E val;
        try{
            val = q.get(0);
        } catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException();
        }
        return val;
    }

    public int size() {
        return this.size;
    }

    // TODO: Checks if inputted is the same Queue
    public boolean equals(Object o){
        if(o instanceof Queue){
            Queue other = (Queue) o;
            if(this.size() == other.size()){
                return this.q.equals(other.q);
            }
        }
        return false;
    }

    public String toString(){
        return "" + q.size();
    }
}
