class EmptyListE extends Exception{}

public class DoublyLinkedList<E> {

    private NodeDL<E> head;
    private NodeDL<E> tail;
    private int size;

    // TODO: default constructor
    public DoublyLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // TODO: secondary constructor
    public DoublyLinkedList(NodeDL<E> head, NodeDL<E> tail){
        this.head = head;
        this.tail = tail;
        if(this.head == null && this.tail == null){
            this.size = 0;
        }else if(this.head != this.tail){
            this.size = 2;
        }else this.size = 1;
    }

    public int size() {
        return this.size;
    }

    // TODO: Insert elem at the start of the DoublyLinkedList
    void insertAtHead(E elem){
        //Start with new node containing the element
        NodeDL node = new NodeDL<>(elem);
        //Prepare the node to be the head of the list
        node.next = this.head;
        node.prev = null;
        //Null check to prevent runtime errors
        if(this.head != null){
            //Prepare head node to be the second node in list
            this.head.prev = node;
        }
        //Reset head pointer
        this.head = node;
        this.size++;
    }

    // TODO: Insert elem at the end of the DoublyLinkedList
    void insertAtTail(E elem){
        //Start with new node containing the element
        NodeDL node = new NodeDL<>(elem);

        //Prepare node to be the new tail node
        node.next = null;

        //Check for empty list, if so make the new node the head
        if(this.head == null){
            node.prev = null;
            this.head = node;
            this.size++;
            //Kick us out of the function if this code has been executed
            return;
        }
        //used as starting point for moving thru the list
        NodeDL finalNode = this.head;

        //Otherwise we need to reach the last node
        while(finalNode.next != null){
            finalNode = finalNode.next;
        }
        finalNode.next = node;
        node.prev = finalNode;
        this.tail = node;
        this.size ++;
    }

    // TODO: Delete the element from the start of the DoublyLinkedList. Throw an EmptyListE if there's nothing to delete
    E deleteAtHead() throws EmptyListE{
        //empty list check
        if(this.head == null){
            throw new EmptyListE();
        }
        //set ref for return value
        NodeDL retVal = this.head;

        //set head pointer to second in list
        this.head = this.head.next;
        if(this.head != null){
            //clear new head pointer's previous value
            this.head.prev = null;
        }
        //decrease size
        this.size--;
        //return the node's payload
        return (E) retVal.data;
        //As I understand it, the now island node should be garbage collected(?)
    }


    // TODO: Delete the element from the end of the DoublyLinkedList. Throw an EmptyListE if there's nothing to delete
    E deleteAtTail() throws EmptyListE{
        //empty list check
        if(this.head == null){
            throw new EmptyListE();
        }
        //set ref for return value
        NodeDL retVal = this.tail;

        //create reference to future tail node
        NodeDL node = this.tail.prev;
        if(node != null){
            //sever the connection
            this.tail.prev = null;
            node.next = null;
        }
        //set future tail node as the tail node
        this.tail = node;

        //decrease size
        this.size--;
        //return the node's payload
        return (E) retVal.data;
    }

    // TODO: Get the element at some position. If it's not possible, throw an IndexOutOfBoundsException
    E get (int index) throws IndexOutOfBoundsException{
        //handle for empty list and if index is too large
        if(this.head == null){
            throw new IndexOutOfBoundsException();
        }else if(index+1 > this.size){
            throw new IndexOutOfBoundsException();
        }
        int i = 0;

        //used as starting point for moving thru the list
        NodeDL node = this.head;

        //Otherwise we need to reach the last node
        while(node.next != null){
            if(i == index){
                break;
            }
            //move thru list
            node = node.next;
            i++;
        }
        //not found
        return (E) node.data;
    }

    // TODO: Search the DoublyLinkedList for elem. If not found, return -1;
    public int search(E elem){
        NodeDL node = new NodeDL<>(elem);

        NodeDL temp = this.head;
        int i = 0;
        //while loop to simply traverse the list
        while(temp.next != null){
            //check our nodes using equal function which compares data
            if(temp.equals(node)){
                //return index if match
                return i;
            }
            //move thru the list
            temp = temp.next;
            i++;
        }
        //if not found return -1
        return -1;
    }

    // TODO: When passed some object, return true if it's a DoublyLinkedList, has the same elements in the same order.
    public boolean equals(Object o){
        if (o instanceof DoublyLinkedList) {
            DoublyLinkedList other = (DoublyLinkedList) o;

            if(this.size() == other.size()){
                //used as starting point for moving thru the lists; we need two in this case
                NodeDL node = this.head;
                NodeDL otherNode = other.head;

                //Otherwise we need to reach the last node
                while(node.next != null){
                    if (!node.equals(otherNode)) {
                        return false;
                    }

                    //move thru both lists
                    node = node.next;
                    otherNode = otherNode.next;

                }
                return true;

            }else return false;
        }
        else return false;
    }

    public String toString(){
        String ret = "";
        NodeDL<E> temp = head;
        for(int i = 0; i < size; i++){
            ret += temp.data + " ";
            temp = temp.next;
        }
        return ret;
    }
}
