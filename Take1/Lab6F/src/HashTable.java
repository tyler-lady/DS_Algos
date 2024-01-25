import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable {
    private final int INITIAL_CAPACITY = 11;
    private List<Entry> entries;
    private int size;
    private int capacity = INITIAL_CAPACITY;

    public HashTable() {
        // TODO
        this.entries = new ArrayList<>(capacity);
        this.size = 0;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public int getSize() {
        return size;
    }

    /**
     * Generates the hash (index) for the given key and the number of collisions encountered. This should be computed
     * using double hashing; please implement the function according to the following requirements:
     *  - generate the previous prime from the capacity (given) and call it 'prevPrime'
     *  - 'hash1' is an int that is the hashcode of the key
     *  - 'hash2' is an int that is: 'prevPrime' - (hash1 % 'prevPrime')
     *  - the returned hash is ('hash1' + collisions * 'hash2') % capacity
     */
    private int hash(String key, int collisions) {
        // TODO
        int prevPrime = previousPrime(this.capacity);
        int hash1 = key.hashCode();
        int hash2 = prevPrime - (hash1 % prevPrime);

        return (hash1 + collisions * hash2) % this.capacity;
    }

    /**
     * Finds the prime immediately before the given number.
     */
    private int previousPrime(int number) {
        //https://stackoverflow.com/a/62823875
        for(int i = number - 1; i >= 2; i--) {
            boolean prime = true;
            for(int j = 2; j <= Math.sqrt(i); j++ ) {
                if(i % j == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime == true) {
                return i;
            }
        }
        return 2;
    }

    /**
     * Inserts the given key-value pair into the HashTable or updates the value of the current Entry if the key is
     * already stored.
     */
    public void put(String key, String value) {
        // TODO

        //use function to find index
        int index = this.hash(key, 0);

        //create new temporary entry
        Entry entry = new Entry(key, value);

        //insertion of element
        if(this.get(key) == null){
            //list.add(entry);
            this.size++;
        }else{
            //if entry already in list, get the entry and update its value
            Entry tmp = this.getEntries().get(index);
                    this.get(key);
            for (Entry tmp : list) {
                //from our linkedlist, extract each element
                //compare extracted key to the one we are looking for
                if (tmp.key.equals(key)) {
                    //if match return the extracted value
                    tmp.value = value;
                }
            }
        }
    }



    /**
     * Gets the value from the Entry in the HashTable containing the given key and returns it. Returns null if the key
     * was not found.
     */
    public String get(String key) {
        // TODO
        //return value for a given key

        //use hash to find the index
        int index = this.hash(key, 0);

        for(int i = 0; i < this.getSize(); i++){
            if(i == index){
                Entry entry = this.getEntries().get(index);
                return entry.getValue();
            }
        }

        //if key not in table return null
        return null;
    }

    /**
     * If the key is found in the HashTable, marks the corresponding entry as a deleted (a tombstone).
     */
    public void remove(String key) {
        // TODO
    }

    /**
     * Increase the capacity of the 'entries' ArrayList (double the capacity then find the next prime) and puts the
     * key-value pairs of the smaller 'entries' into the
     */
    private void rehash() {
        // TODO
        //make size twice as much as it was
        //insert all entries into the new hash;
        // -this.ls = Arrays.copyOf(this.ls,this.capacity);
        int oldCap = this.capacity;

        //double capacity
        this.capacity *= 2;

        //store ref to old list of entries
        List<Entry> oldEntries = this.getEntries();

        //now, reinit entries
        this.entries = new ArrayList<>(this.capacity);

        //reinit size, as this end up doubled after the rehash
        this.size = 0;

        //insert all old entries into new entries
        //for loop to move thru oldEntries
        for(int i = 0; i < oldCap; i++){
            //get entry
            Entry tmp = oldEntries.get(i);

            //if not null, then needs to be put in new entries
            if(tmp != null){
                this.put(tmp.getKey(), tmp.getValue());
            }
        }

    }

    /**
     * Finds the prime immediately following the given number.
     */
    private int nextPrime(int number) {
        // https://stackoverflow.com/a/57904191
        BigInteger b = new BigInteger(String.valueOf(number));
        return (int) Long.parseLong(b.nextProbablePrime().toString());
    }
}
