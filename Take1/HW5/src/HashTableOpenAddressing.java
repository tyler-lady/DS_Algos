import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

import static java.lang.Math.abs;
import static java.lang.Math.nextUp;

public class HashTableOpenAddressing<K, V> extends Dictionary<K,V>{

    private int capacity;  // size of the table
    private int size;  // number of elements in the table
    private int previousPrime; //store prev prime so that it is not calculated again and again in double hashing.
    private int mode;

    public static int LINEARPROBING = 1;
    public static int QUADRATICPROBING = 2;
    public static int DOUBLEHASHING = 3;
    private double loadFactor;
    private Entry<K, V>[] table;

    public HashTableOpenAddressing() {
        this(DOUBLEHASHING, 11, 0.75);  // default initial capacity of 11
    }

    public HashTableOpenAddressing(int mode) {
        this( mode, 11, 0.75);
    }

    public HashTableOpenAddressing(int capacity, double loadFactor) {
        this(DOUBLEHASHING, capacity, loadFactor);
    }

    /*
    TODO:
    This constructor takes a mode, capacity, loadFactor, and sets those variables + relevant variables
    according to such. Additionally, it will set up the table according to the capacity.
    If the mode is DOUBLEHASHING, please calculate the previousPrime and set it.
     */
    public HashTableOpenAddressing(int mode, int capacity, double loadFactor) {
        this.mode = mode;
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = (Entry<K, V>[]) new Entry[capacity];
        if(this.mode == DOUBLEHASHING){
            this.previousPrime = previousPrime(this.capacity-1);//subtracting one
        }
    }

    private int previousPrime(int number) {
        while( true ) {
            if( isPrime( number ) ) {
                return number;
            }
            number--;
        }
    }


    // TODO:
    //  second hash should be prevPrime - (key % prevPrime)...shouldn't be negative
    //  returns the size of the step
    private int hash2(K key) {
        return this.previousPrime - ((key.hashCode() & 0x7FFFFFFF) % this.previousPrime);
    }


    // TODO: gets the next index given the index and the offset. Please take into account the mode.
    private int getNextIndex(K key, int offset) {
        if(this.mode == LINEARPROBING){
            return (this.hash(key) + offset) % this.capacity;
        } else if(this.mode == QUADRATICPROBING){
            return (this.hash(key) + (int) Math.pow(offset, 2)) % this.capacity;
        } else if(this.mode == DOUBLEHASHING){
            return (this.hash(key) + offset*this.hash2(key)) % this.capacity;
        }
        return -1;
    }

    // TODO:
    //  Put a key, value pair into the table.
    //  If the key already exists/inactive, override it. Else, put it into the table.
    //  Throw a RuntimeException if there is an infinite loops.
    //  An infinite loop here can be defined as seeing repeated indices
    public void put(K key, V value) {

        //create new temporary entry
        Entry<K,V> entry = new Entry<K,V>(key, value);

        //when checking for location
        //set ref to second returned index
        //if we see that index again throw runtime error
        int offset = 0;

        //initial hash the key
        int index = this.getNextIndex(key, offset);
        int firstIndex = index;

        boolean shouldInsert = false;

        while(!shouldInsert){
            //try to get the index - if null nothing there
            if(!this.containsKey(key)){ //if table doesn't contain key
                Object tmp = Array.get(this.table, index); //get object at index
                if(Objects.nonNull(tmp)){ //if not null
                    offset++; //increment offset
                }else {
                    shouldInsert = true;
                    continue;
                }
            } else {
                Object tmp = Array.get(this.table, index); //get object at index
                if(Objects.nonNull(tmp)){ //if not null
                    Entry<K,V> tmpEntry = (Entry<K, V>)  tmp; //cast to entry
                    if(tmpEntry.getKey().equals(key)){ //if entry key == our key
                        tmpEntry.setValue(value);
                        tmpEntry.setActive(true);
                        break; //when we've updated a value, then we want to break from the loop w/o
                        //setting shouldInsert, to avoid the insertion action
                    } else{
                        offset++; //increment offset
                    }
                }else {
                    offset++;
                }
            }

            index = getNextIndex(key, offset);
            if(index == firstIndex){ //then we are seeing repeating values even though we shouldn't
                throw new RuntimeException("Infinite Loop!");
            }
        }

        if(shouldInsert){
            Array.set(this.table, index, entry);
            this.size++;
        }

        //rehash if load factor greater than should be
        if(this.size() > (this.loadFactor*this.capacity))
            this.resize();
    }

    // TODO:
    //  Retrieves the value of a key in the table.
    //  If there is an infinite loop, throw a RuntimeException.
    //  Return null if not there.
    public V get(K key) {
        //use hash to find the index
        int offset = 0;

        int index = this.getNextIndex(key, offset);
        int firstIndex = index;

        boolean validReturn = false;

        Entry<K,V> entry = null;

        while(!validReturn){
            Object tmp = Array.get(this.table, index);
            if(Objects.nonNull(tmp)){
                entry = (Entry<K, V>) tmp;
                if (entry.getKey().equals(key)) {
                    //if match return the extracted value
                    validReturn = true;
                    continue;
                } else {
                    offset++;
                }
            } else {
                break;
            }
            index = getNextIndex(key, offset);
            if(index == firstIndex){ //then we are seeing repeating values even though we shouldn't
                throw new RuntimeException("Infinite Loop!");
            }
        }
        if(validReturn /*&& entry.getActive()*/) //so if there's a valid return, we now check if it's active, if not we'll return null
            return entry.getValue();
        //if key not in table return null
        return null;
    }

    // TODO: Searches the table to see if the key exists or not.
    public boolean containsKey(K key) {
        return (this.get(key) != null);
    }

    // TODO:
    //  Set the key as inactive if it exists in the table. Return true.
    //  If there is no key, return false.
    //  If there's an infinite loop, throw a RuntimeException.
    public boolean remove(K key) {
        int offset = 0;

        int index = this.getNextIndex(key, offset);
        int firstIndex = index;

        boolean shouldDelete = false;

        Entry<K,V> entry = null;

        while(!shouldDelete){
            Object tmp = Array.get(this.table, index);
            if(Objects.nonNull(tmp)){
                entry = (Entry<K, V>) tmp;
                if (entry.getKey().equals(key)) {
                    //if match return the extracted value
                    shouldDelete = true;
                    continue;
                } else {
                    offset++;
                }
            } else {
                break;
            }
            index = getNextIndex(key, offset);
            if(index == firstIndex){ //then we are seeing repeating values even though we shouldn't
                throw new RuntimeException("Infinite Loop!");
            }
        }
        if(shouldDelete /*&& entry.getActive()*/) { //so if there's a valid return, we now check if it's active, if not we'll return null
            entry.setActive(false);
            //this.size--;
            return true;
        }
        //if key not in table return null
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // TODO:
    //  Calculate the absolute hash of the key. Do not overthink this.
    private int hash(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % this.capacity;
    }


    private boolean isPrime(int number) {
        for( int i = 2; i <= number / 2; i++ ) {
            if( number % i == 0 ) {
                return false;
            }
        }
        return true;
    }

    private int nextPrime(int number) {
        while( true ) {
            if( isPrime( number ) ) {
                return number;
            }
            number++;
        }
    }

    // TODO:
    //  Set the capacity to the nextPrime of the capacity doubled.
    //  Calculate the previousPrime and set up the new table with the old tables'
    //  contents now hashed to the new.
    private void resize() {
        // set old capacity ref
        int oldCap = this.capacity;
        //set new capacity
        this.capacity = this.nextPrime(this.capacity*2);
        //get oldTable ref
        Entry<K, V>[] oldTable = this.table;
        //init new empty table at new capacity
        this.table = (Entry<K, V>[]) new Entry[this.capacity];
        //reinit size
        this.size = 0;
        //calculate the previousPrime (if necessary)
        if(this.mode == DOUBLEHASHING)
            this.previousPrime = this.previousPrime(this.capacity-1);

        //insert all old entries into newTable
        //for loop to move thru oldTable
        for (Entry<K,V> tmp : oldTable) {
            //from our table, extract each element
            //put extracted element into new table
            if(tmp != null)
                this.put(tmp.getKey(), tmp.getValue());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int index = 0;

        for (Entry<K, V> entry : table) {
            sb.append(index + ": ");
            index++;
            if (entry != null) {
                sb.append(entry.getKey() + "=" + entry.getValue() + ",");
            }
            sb.append(";");
        }

        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }

    private class Entry<K, V> {
        private K key;
        private V value;

        private boolean isActive;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            isActive = true;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public boolean getActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }
    }

    public static void main(String []args ) {
        HashTableOpenAddressing<Integer, Integer> hashTable = new HashTableOpenAddressing<>(QUADRATICPROBING, 10, 1);

        hashTable.put(2,2);
        System.out.println(hashTable);
        for (int i = 0; i < 280; i += 10) {
            hashTable.put(i, i);
            hashTable.remove(0);
            System.out.println(hashTable.get(i));
        }
    }

}
