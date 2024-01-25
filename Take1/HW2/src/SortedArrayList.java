import java.lang.reflect.Array;
import java.util.Arrays;

public class SortedArrayList<E extends Comparable> extends List<E> {

    private int size;
    private int capacity;
    private Object[] ls;

    // default: should create a sortedarraylist that is capable of holding 10 element
    public SortedArrayList(){
        this.size = 0;
        this.capacity = 10;
        this.ls = new Object[this.capacity];
    }

    // second constructor - should create a sortedarraylist that is capable of holding x element that size
    public SortedArrayList(int capacity){
        this.size = 0;
        this.capacity = capacity;
        this.ls = new Object[this.capacity];
    }

    public int size(){
        return this.size;
    }

    public E get(int index) throws IndexOutOfBoundsException{
        if(index >= this.size){
            throw new IndexOutOfBoundsException();
        }
        return (E) this.ls[index];
    }

    // inserts element while maintaining the sorted order of the contents; resize to double capacity if no space
    public void add(E value) {
        if(this.size == this.capacity){
            this.capacity *= 2;
            this.ls = Arrays.copyOf(this.ls,this.capacity);
        }
        /*
        if(this.size() == 0){
            this.ls[0] = value;
        } else if (this.size() > 0) {
            // Creating new array with increased size
            Object new_array[] = new Object[this.size-1];

            // Copying elements from one
            // array to another as in approach 2
            int i = 0, j = 0;
            for (i = 0; i < this.size(); i++) {
                if (this.get(i).compareTo(value) > 0) {
                    new_array[i] = value;
                    break;
                }
                else
                    new_array[i] = this.get(j++);
            }

            // copy the remaining elements
            for (int k = i + 1; k < new_array.length; k++)
                new_array[k] = this.get(j++);

            this.ls = new_array;
        }
        */


        this.ls[size] = value;
        this.size++;
        this.quickSort(0,this.size-1);
    }


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
        Object pivot = pivotSelection(lo, hi); //last element as pivot

        int i = lo;
        int j = hi;

        while (i < j){
            while(get(i).compareTo(pivot) <= 0 && i<j){ //move left point right until it needs to be swapped
                i++;
            }
            while(get(j).compareTo(pivot) >= 0 && i<j){ //move right point left until it needs to be swapped
                j--;
            }
            this.swapValuesAt(i,j); //swaps the two values during partitioning
        }

        this.swapValuesAt(i, hi); //swap pivot to interior position

        return i; //return pivot index
    }

    //HelperMethod:
    private void swapValuesAt(int i, int j){
        Object temp = this.ls[i];

        this.ls[i] = this.ls[j];
        this.ls[j] = temp;
    }

    //Supposedly, pivot selection using the following method is more performant with larger arrays, and more performant than random pivot selection
    //Bentley, Jon L.; McIlroy, M. Douglas (1993)
    //"Engineering a sort function"
    //Reimplemented to return an object, and instead of directly comparing the values - as with ints - we use x.compareTo(y), and if the returned value is less than 0
    //x is less than y (the method used for sorting the int values)
    private Object pivotSelection(int lo, int hi){
        int mid = Math.floorDiv(lo+hi,2);

        if(get(hi).compareTo(get(lo)) < 0){
            swapValuesAt(lo,hi);
        }
        if(get(mid).compareTo(get(hi)) < 0){
            swapValuesAt(mid, hi);
        }
        return get(hi);
    }


    // delete - deletes an element at said index; moves elements such that there are no gaps in between them
    public void delete(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index > (this.size-1)){
            throw new IndexOutOfBoundsException();
        }

        Object[] tmpArr = new Object[this.capacity];

        for (int i=0, k=0; i < this.size; i++){
            if(i != index){
                tmpArr[k] = this.ls[i];
                k++;
            }
        }

        this.ls = tmpArr;
        this.size--;
    }

    // search - binary search O(log(n)) for the element; returns -1 if not found
    public int search(E value){
        int lo = 0;
        int hi = this.size-1;
        int mid;
        int index = -1;

        //iterative implementation
        while(lo<=hi){
            mid = (lo+hi)/2; //seen various methods for returning a midpoint w/ little discussion as to differences
            int comp = get(mid).compareTo(value);
            if(comp == 0){
                index = mid;
                return index;
            } else if(comp > 0){
                hi = mid-1;
            } else {
                lo = mid+1;
            }
        }
        return index;
    }

    // given some other sortedarraylist, compare it to see if it has the same contents (also in same order)
    public boolean equals(Object o){
        SortedArrayList List = (SortedArrayList) o;
        boolean comp = false;
        if(this.size == List.size){
            int index = 0;
            comp = true;
            while(index < this.size){
                if(this.get(index).compareTo(List.get(index)) != 0){
                    comp = false;
                }
                index++;
            }
        }
        return comp;
    }

    // helper
    public String toString(){
        String ret = "";
        for(int i = 0; i < this.capacity; i++){
            ret += i + ": "+ this.ls[i] + "\n";
        }
        return ret;
    }
}
