import java.util.Arrays;

public class SortedArrayList {

    private int size;
    private int capacity;
    private int[] ls;

    // TODO: default constructor - store 10 elements with nothing in the array
    public SortedArrayList(){
        this.size = 0;
        this.capacity = 10;
        this.ls = new int[this.capacity];
    }

    // TODO: secondary constructor - store some capacity elements with nothing in the array
    public SortedArrayList(int capacity){
        this.size = 0;
        this.capacity = capacity;
        this.ls = new int[this.capacity];
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public int[] getLs() {
        return ls;
    }

    // TODO: insertSorted - inserts the value but the array should be sorted; resize to double capacity if needed
    public void insertSorted(int value){
        if(this.size == this.capacity){
            this.capacity *= 2;
            this.ls = Arrays.copyOf(this.ls, this.capacity);
        }
        this.ls[size] = value;
        //Arrays.sort(this.ls); //resets values to zero
        this.size++;
        this.quickSort(0, this.size-1);
    }

    public void printAll(){
        //System.out.println(this.size);
        System.out.println(Arrays.toString(this.ls));
    }

    //HelperMethod: Implementation of quicksort
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
            while(this.ls[i] <= pivot && i<j){ //move left point right until it needs to be swapped
                i++;
            }
            while(this.ls[j] >= pivot && i<j){ //move right point left until it needs to be swapped
                j--;
            }
            this.swapValuesAt(i,j); //swaps the two values during partitioning
        }

        this.swapValuesAt(i, hi); //swap pivot to interior position

        return i; //return pivot index
    }

    //HelperMethod:
    private void swapValuesAt(int i, int j){
        int temp = this.ls[i];

        this.ls[i] = this.ls[j];
        this.ls[j] = temp;
    }

    //Supposedly, pivot selection using the following method is more performant with larger arrays, and more performant than random pivot selection
    //Bentley, Jon L.; McIlroy, M. Douglas (1993)
    //"Engineering a sort function"
    private int pivotSelection(int lo, int hi){
        int mid = Math.floorDiv(lo+hi,2);

        if(this.ls[hi] < this.ls[lo]){
            swapValuesAt(lo,hi);
        }
        if(this.ls[mid] < this.ls[hi]){
            swapValuesAt(mid, hi);
        }
        return this.ls[hi];
    }

    // TODO: delete - deletes a value at the index; if index is not possible throw the exception
    // No empty spaces in the array should be left between elements
    public void delete(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index > (this.size-1)){
            throw new IndexOutOfBoundsException();
        }

        int[] tmpArr = new int[this.getCapacity()];

        //this.ls[index] = null;
        for (int i=0, k=0; i < this.size; i++){
            if(i != index){
                tmpArr[k] = this.ls[i];
                k++;
            }
        }

        this.ls = tmpArr;
        this.size--;
    }

    // TODO: get - returns the value at the index; if index not possible throw exception
    public int get(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index > (this.size-1)){
            throw new IndexOutOfBoundsException();
        } else return this.ls[index];
    }

    // TODO: search - searches for the value and returns its found index; if not found return -1
    public int search(int value){
        int index = 0;

        while(index < (this.size-1)){
            if(this.ls[index] != value){
                index++;
            } else {return index;}
        }
        return -1;
    }
}
