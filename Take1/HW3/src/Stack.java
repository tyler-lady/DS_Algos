class EmptyStackE extends Exception{}

public class Stack<E>{
    private DoublyLinkedList<E> st;
    private int size;

    // TODO: default constructor
    public Stack(){
        st = new DoublyLinkedList<>();
        this.size = 0;
    }

    // TODO: Push the element to the top of stack
    public void push(E elem){
        //add element to front of list
        st.insertAtHead(elem);
        //increase size
        this.size++;
    }

    // TODO: Pop the element off the top of the stack. If nothing to pop, throw EmptyStackE
    public E pop() throws EmptyStackE {
        E val;
        //try catch for Exception handling, throw this method's error if DLL method throws exception
        try {
            val = st.deleteAtHead();
            this.size--;
        }catch(EmptyListE e){
            throw new EmptyStackE();
        }
        return val;
    }

    // TODO: Without affecting the stack, return the element at the top of the stack
    public E peek() throws IndexOutOfBoundsException{
        E val;
        try{
            val = st.get(0);
        } catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException();
        }
        return val;
    }

    public int size() {
        return this.size;
    }

    // TODO: Check if some other object is the same Stack
    public boolean equals(Object o){
        if(o instanceof Stack){
            Stack other = (Stack) o;
            if(this.size() == other.size()){
                return this.st.equals(other.st);
            }
        }
        return false;
    }

    public String toString(){
        return st.toString();
    }

}
