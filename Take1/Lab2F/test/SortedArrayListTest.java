import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class SortedArrayListTest {
    /*
    TODO: tests
    - Make sure you have 100% code coverage '
    - Make sure you test the full functionality of this class...think edge cases
    - Use JUnit
    - Feel free to add more test methods
     */

    Random rand = new Random();
    int[] nums = new int[8];

    SortedArrayList arrayList = new SortedArrayList();

    public void numGen(){
        for (int i = 0; i < nums.length; i++){
            nums[i] = rand.nextInt(100);
        }
    }

    @Test
    public void Constructor_Test(){
        SortedArrayList arrayList_3 = new SortedArrayList();
        assertEquals(0, arrayList_3.getSize());
        assertEquals(10, arrayList_3.getCapacity());
        assertNotNull(arrayList_3.getLs());

        SortedArrayList arrayList_2 = new SortedArrayList(15);
        assertEquals(0, arrayList_2.getSize());
        assertEquals(15, arrayList_2.getCapacity());
        assertNotNull(arrayList_2.getLs());
    }

    @Test
    public void Insert_Test(){
        numGen();
        assertEquals(10, arrayList.getCapacity());
        for (int num : nums) {
            arrayList.insertSorted(num);
        }
        int k = rand.nextInt(100);
        arrayList.insertSorted(k);
        k = rand.nextInt(100);
        arrayList.insertSorted(k);
        k = rand.nextInt(100);
        arrayList.insertSorted(k);
        arrayList.printAll();
    }

    @Test
    public void Delete_Test(){
        Insert_Test();
        //assertThrows(IndexOutOfBoundsException.class);
        arrayList.delete(3);

        Throwable exception = assertThrows(IndexOutOfBoundsException.class,()->{
            arrayList.delete(arrayList.getSize()+1);
        });
    }

    @Test
    public void Get_Test(){
        Insert_Test();

        int k = rand.nextInt(10);
        assertEquals(arrayList.get(k), arrayList.getLs()[k]);

        Throwable exception = assertThrows(IndexOutOfBoundsException.class,()->{
            arrayList.get(arrayList.getSize()+1);
        });
    }

    @Test
    public void Search_Test(){
        Insert_Test();
        int k = rand.nextInt(10);

        assertEquals(k,arrayList.search(arrayList.get(k)));
        assertEquals(-1, arrayList.search(-8));
    }
}
