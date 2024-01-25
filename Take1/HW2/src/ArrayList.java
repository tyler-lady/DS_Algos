import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayList<E extends Comparable> extends List<E> {
    private int size;
    private int capacity;
    private Object[] ls;

    // default: should create an arraylist that is capable of holding 10 element
    public ArrayList(){
        this.size = 0;
        this.capacity = 10;
        this.ls = new Object[this.capacity];
    }

    // second constructor - should create an arraylist that is capable of holding x element
    public ArrayList(int capacity){
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

    // insert --> takes some element and inserts it at the end of the arraylist, resizes to double capacity if no space
    public void add(E value){
        if(this.size == this.capacity){
            this.capacity *= 2;
            this.ls = Arrays.copyOf(this.ls,this.capacity);
        }
        this.ls[size] = value;
        this.size++;
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

    // searches one by one to find the element, if it doesn't exist then return -1
    public int search(E value){
        int front = 0;
        int back = this.size-1;

        while(front <= back){
            if(this.get(front).compareTo(value) == 0){
                return front;
            }else if(this.get(back).compareTo(value) == 0){
                return back;
            }
            front ++;
            back --;
        }
        return -1;


        /*
        int index = 0;

        while(index < (this.size-1)){
            if(this.ls[index] != value){
                index++;
            } else {return index;}
        }
        return -1;
        */
    }

    // given some other arraylist, compare it to see if it has the same contents
    public boolean equals(Object o){
        ArrayList List = (ArrayList) o;
        boolean comp = false;
        if(this.size == List.size()){
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

    // to help you
    public String toString(){
        String ret = "";
        for(int i = 0; i < this.size; i++){
            ret += i + ": "+ this.ls[i] + "\n";
        }
        return ret;
    }

}
