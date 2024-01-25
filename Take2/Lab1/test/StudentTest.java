import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    Search s = new Search();

    @Test
    public void test(){
        find_first_true();
        find_first_equal();
        find_first_true_sorted();
    }

    @org.junit.jupiter.api.Test
    void find_first_true() {
        boolean[] arr = {false, false, true, false, true};
        boolean[]arr_1 = {true, false, true, false, true};
        boolean[] arr_5 = {false, true, false};
        boolean[] arr_2 = {false, true};
        boolean[] arr_3 = {false};
        boolean[] arr_4 = {};

        assertEquals(2, Search.find_first_true(arr, 0, arr.length));
        assertEquals(2, Search.find_first_true(arr_1, 1, 3));
        assertEquals(1, Search.find_first_true(arr_2, 0, arr_2.length));
        assertEquals(1, Search.find_first_true(arr_3, 0, arr_3.length));
        assertEquals(0, Search.find_first_true(arr_4, 0, arr_4.length));
        assertEquals(1, Search.find_first_true(arr_5, 0, arr_5.length));
        assertEquals(0, Search.find_first_true(arr_2, 0, 0));
        assertEquals(2, Search.find_first_true(arr, 1, 2));
    }

    @org.junit.jupiter.api.Test
    void find_first_equal() {
        int[] arr = {32, 11, 4, 5, 99, 5, 32, 75};
        int[] arr1 = {32, 11, 4, 15, 99, 5};
        int[] arr2 = {32};
        int[] arr3 = {32, 11, 4, 99, 32, 75, 100};
        int[] arr4 = {3, 1, 4, 5, 9, 5, 30, 0};
        int[] arr5 = {};
        int[] arr6 = {32, 32};

        assertEquals(3, Search.find_first_equal(arr, 5));
        assertEquals(5, Search.find_first_equal(arr1, 5));
        assertEquals(1, Search.find_first_equal(arr2, 25));
        assertEquals(0, Search.find_first_equal(arr3, 32));
        assertEquals(4, Search.find_first_equal(arr4, 9));
        assertEquals(0, Search.find_first_equal(arr5, 66));
        assertEquals(0, Search.find_first_equal(arr6, 32));
        assertEquals(2, Search.find_first_equal(arr6, 33));
    }

    @org.junit.jupiter.api.Test
    void find_first_true_sorted() {
        boolean[] arr = {false, false, true, true, true, true, true};
        boolean[] arr_1 = {false, false, true};
        boolean[] arr_2 = {false, true};
        boolean[] arr_3 = {false};
        boolean[] arr_4 = {};
        boolean[] arr_5 = {false, false, false, false, true, true, true, true, true};

        assertEquals(2, Search.find_first_true_sorted(arr, 0, arr.length));
        assertEquals(2, Search.find_first_true_sorted(arr_1, 0, arr_1.length));
        assertEquals(1, Search.find_first_true_sorted(arr_2, 0, arr_2.length));
        assertEquals(1, Search.find_first_true_sorted(arr_3, 0, arr_3.length));
        assertEquals(0, Search.find_first_true_sorted(arr_4, 0, arr_4.length));
        assertEquals(3, Search.find_first_true_sorted(arr_5, 0, 3));
    }
}