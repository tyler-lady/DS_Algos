import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Lab9Test {

    @Test
    void bucketSort() {
        //TODO
        int[] arr = {11, 9, 21, 8, 17, 19, 13, 1, 24, 12};
        int[] sorted = {1,8,9,11,12,13,17,19,21,24};
        Lab9.bucketSort(arr, arr.length);
        assertArrayEquals(sorted, arr);

        int[] arr1 = {21, 37, 418, 23, 46, 25};
        int[] sorted1 = {21,23,25,37,46,418};
        Lab9.bucketSort(arr1, arr1.length);
        assertArrayEquals(sorted1, arr1);
    }

    @Test
    void radixSort() {
        //TODO
        int[] arr = {11, 9, 21, 8, 17, 19, 13, 1, 24, 12};
        int[] sorted = {1,8,9,11,12,13,17,19,21,24};
        Lab9.radixSort(arr);
        assertArrayEquals(sorted, arr);

        int[] arr1 = {21, 37, 418, 23, 46, 25};
        int[] sorted1 = {21,23,25,37,46,418};
        Lab9.radixSort(arr1);
        assertArrayEquals(sorted1, arr1);
    }

    @Test
    void max() {
        //TODO
        int[] arr = {1, 7, 18, 5, 4, 25};
        assertEquals(25, Lab9.max(arr));

        int[] arr1 = {21, 37, 418, 23, 46, 25};
        assertEquals(418, Lab9.max(arr1));
    }

    @Test
    void min() {
        //TODO
        int[] arr = {1, 7, 18, 5, 4, 25};
        assertEquals(1, Lab9.min(arr));

        int[] arr1 = {21, 37, 418, 27, 46, 25};
        assertEquals(21, Lab9.min(arr1));
    }
}