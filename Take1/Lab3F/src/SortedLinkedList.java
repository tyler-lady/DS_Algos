public class SortedLinkedList {

    /**
     * The 0th indexed Node in the list, the nth indexed Node in the list.
     */
    Node head, tail;

    /**
     * Creates a SortedLinkedList.
     */
    public SortedLinkedList() {
        head = null;
        tail = null;
    }

    /**
     * Inserts the given data at a location that maintains sorted order (ascending).
     * @param data The value to be inserted into the list.
     */
    public void insertSorted(int data) {
        Node tmpNode = new Node(data, null);

        if(head == null) {
            head = tmpNode;
            tail = tmpNode;
        } else {
            Node cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = tmpNode;
        }
        quickSort(0, getLength() - 1);
    }

    //HelperMethod: Return the number of elements in the linked list
    public int getLength(){
        Node cur = head;
        int count = 0;

        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    //HelperMethod: Implementation of quicksort ; Same implementation from previous lab
    public void quickSort(int lo, int hi){
        //check our indices
        if(lo>=hi){
            return;
        }
        //partitions and returns our pivot point
        int p = partition(lo, hi);
        //sort the two partitions; to the left, and to the right of the pivot
        quickSort(lo, p-1);
        quickSort(p+1, hi);
    }
    //HelperMethod: Divides array into two partitions
    private int partition(int lo, int hi){
        int pivot = pivotSelection(lo, hi); //last element as pivot
        int i = lo;
        int j = hi;
        while (i < j){
            while(getNode(i).data <= pivot && i<j){ //move left point right until it
                //needs to be swapped
                i++;
            }
            while(getNode(j).data >= pivot && i<j){ //move right point left until it
                //needs to be swapped
                j--;
            }
            this.swapValuesAt(i,j); //swaps the two values during partitioning
        }
        this.swapValuesAt(i, hi); //swap pivot to interior position
        return i; //return pivot index
    }
    //HelperMethod:
    private void swapValuesAt(int i, int j){
        int temp = getNode(i).data;
        getNode(i).data = getNode(j).data;
        getNode(j).data = temp;
    }
    //Supposedly, pivot selection using the following method is more performant
    //with larger arrays, and more performant than random pivot selection
    //Bentley, Jon L.; McIlroy, M. Douglas (1993)
    //"Engineering a sort function"
    private int pivotSelection(int lo, int hi){
        int mid = Math.floorDiv(lo+hi,2);
        if(getNode(hi).data < getNode(lo).data){
            swapValuesAt(lo,hi);
        }
        if(getNode(mid).data < getNode(hi).data){
            swapValuesAt(mid, hi);
        }
        return getNode(hi).data;
    }

    /**
     * Deletes the Node at the given index.
     * @param data Index of the int to be deleted.
     * @return If the data was deleted.
     */
    public boolean delete(int data) {

        Node cur = head;
        int count = 0;

        if(data == 0){
            if(head != null){
                head = head.next;
                return true;
            }
        } else {
            while (cur != null) {
                if(count==data-1){
                    Node tmpNode = cur.next.next;
                    cur.next.next = null;
                    cur.next = tmpNode;
                    return true;
                }
                count++;
                cur = cur.next;
            }
        }

        return false;
    }

    /**
     * Gets the int at the given index. Throws an IndexOutOfBoundException if index
     * is negative or too large.
     * @param idx Index of the int to be return.
     * @return The int at the given index
     */
    public int get(int idx) {
        //If out of bounds throw error
        if(idx < 0 || idx >= getLength()){
            throw new IndexOutOfBoundsException();
        }

        Node cur = head;
        int count = 0;
        int value = 0;

        while (cur != null) {
            if(count==idx){
                value = cur.data;
            }
            count++;
            cur = cur.next;
        }

        return value;
    }

    //Helper Method:
    public Node getNode(int idx){
        //If out of bounds throw error
        if(idx < 0 || idx >= getLength()){
            throw new IndexOutOfBoundsException();
        }

        Node cur = head;
        int count = 0;
        Node value = null;

        while (cur != null) {
            if(count==idx){
                value = cur;
            }
            count++;
            cur = cur.next;
        }

        return value;
    }

    /**
     * Searches for the given int and returns its index if found. If the int is not
     * found, returns -1.
     * @param data The int to search for.
     * @return The data at the given index
     */
    public int search(int data) {

        Node cur = head;
        int idx = 0;

        while (cur != null) {
            if(getNode(idx).data==data){
                return idx;
            }
            idx++;
            cur = cur.next;
        }

        return -1;
    }

    @Override
    public String toString() {
        return printList();
    }

    /**
     * A recursive helper for toString that generates a String representation of this.
     * @return A String representation of the this.
     */
    private String printList() {
        String listStr = "";
        String delimiter = ", ";
        Node cur = head;
        while (cur != null) {
            listStr += cur.data + delimiter;
            cur = cur.next;
        }

        return listStr.substring(0, listStr.length()-delimiter.length());
    }
}
