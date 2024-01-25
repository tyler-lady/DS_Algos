import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable {
    private final int INITIAL_CAPACITY = 11;
    private int capacity = INITIAL_CAPACITY;
    private int size = 0;
    private List<LinkedList<Entry>> entries;

    public HashTable() {
        this.entries = new ArrayList<>(this.capacity);

        for(int i = 0; i < this.capacity; i++){
            this.entries.add(i, new LinkedList<>());
        }
    }

    public List<LinkedList<Entry>> getEntries() {
        return entries;
    }

    public int getSize() {
        return size;
    }

    private int hash(String key) {
        //currently it seems the second time this is called,
        //the hashcode is different?
        int val = key.hashCode();
        //using the following bitwise operation
        // in our modulus prevents us returning any negative indexes
        return (key.hashCode() & 0x7FFFFFFF) % this.capacity;
    }


    public void put(String key, String value) {
        int steps = 0;

        //use function to find index
        int index = this.hash(key);

        //calculate future load factor
        double futLoadFactor = (double) this.size/this.capacity;

        //rehash if load factor greater than 0.5
        if(futLoadFactor > 0.5)
            this.rehash();

        //get the linked list at index
        LinkedList<Entry> list = this.entries.get(index);

        //create new temporary entry
        Entry entry = new Entry(key, value);

        //insertion of element
        if(this.get(key) == null){
            steps++;
            list.add(entry);
            this.size++;
        }else{
            //if entry already in list, get the entry and update its value
            for (Entry tmp : list) {
                steps++;
                //from our linkedlist, extract each element
                //compare extracted key to the one we are looking for
                if (tmp.key.equals(key)) {
                    //if match return the extracted value
                    tmp.value = value;
                }
            }
        }
        System.out.println("Put completed in: " + steps + " steps");
    }

    public String get(String key) {
        //return value for a given key

        int steps = 0;

        //use hash to find the index
        int index = this.hash(key);

        //this tells us index of the linked list
        LinkedList<Entry> list = this.entries.get(index);
        //check linked list - this traverses the list
        for (Entry tmp : list) {
            //from our linkedlist, extract each element
            //compare extracted key to the one we are looking for
            steps ++;
            if (tmp.key.equals(key)) {
                System.out.println("Get completed in: " + steps + " steps");
                //if match return the extracted value
                return tmp.value;
            }
        }
        steps++;
        System.out.println("Get completed in: " + steps + " steps");
        //if key not in table return null
        return null;
    }

    public void remove(String key) {
        //use hash to find the index
        int index = this.hash(key);
        //this tells us index of the linked list
        LinkedList<Entry> list = this.entries.get(index);
        //check linked list - this traverses the list
        for(int i = 0; i < list.size(); i++){
            //from our linkedlist, extract each element
            Entry tmp = list.get(i);
            //compare extracted key to the one we are looking for
            if(tmp.key.equals(key)){
                //if match return the extracted value
                list.remove(i);
                //update size
                this.size--;
                //because 'put' code negates duplicate keys
                //I want to break out of this loop to prevent unnecessary usage
                break;
            }
        }
    }

    private void rehash() {
        //make size twice as much as it was
        //insert all entries into the new hash;
        // -this.ls = Arrays.copyOf(this.ls,this.capacity);
        int oldCap = this.capacity;

        //double capacity
        this.capacity *= 2;

        //store ref to old list of entries
        List<LinkedList<Entry>> oldEntries = this.getEntries();

        //now, reinit entries
        this.entries = new ArrayList<>(this.capacity);
        for(int i = 0; i < this.capacity; i++){
            this.entries.add(i, new LinkedList<>());
        }

        //reinit size, as this end up doubled after the rehash
        this.size = 0;

        //insert all old entries into new entries
        //for loop to move thru oldEntries linked lists
        for(int i = 0; i < oldCap; i++){
            //get each list
            LinkedList<Entry> list = oldEntries.get(i);
            //if the list is empty, no elements to add
            if(!list.isEmpty()){
                //if list not empty, add each entry
                for (Entry tmp : list) {
                    //from our linkedlist, extract each element
                    //put extracted element into new table
                    this.put(tmp.key, tmp.value);
                }
            }
        }

    }
}
