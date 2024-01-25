import java.util.*;

public class Lab9 {

    /** RADIX SORT
     *
     * This is NOT a recursive algorithm in the normal sense
     * This is to iterate through the digits of each number
     *
     * This method simply iterates through the number of digits in the max
     * element and calls radixHelper
     *
     * @param arr array to be sorted
     */
    static void radixSort(int[] arr) {
        //TODO
        int max = max(arr);
        //uses int division as middle expression when determining the limit
        //when place value has a digit more than the max value, it rounds to 0
        for(int placeValue = 1; max/placeValue>0; placeValue*=10){
            //call radixHelper for each placevalue
            radixHelper(arr, placeValue);
        }

    }

    /** RADIX HELPER
     *
     * Words do not do it justice. For a visualization see:
     * https://www.cs.usfca.edu/~galles/visualization/RadixSort.html
     *
     * @param arr array to be sorted
     * @param place the placement of the digit you are on
     */
    static void radixHelper(int[] arr, int place) {
        //TODO
        //radixHelper is essentially using a counting sort
        //init temp array to for sorting
        int[] tmp = new int[arr.length + 1];
        //get max value in array
        int max = max(arr);
        //init array to track the count of elements
        int[] count = new int[max+1];
        //set initial values of count array to 0 - starting count for values
        for(int i = 0; i<max; i++){
            count[i] = 0;
        }

        // Get the count of each element
        for (int j : arr) count[(j / place) % 10]++;

        // Calculate the total count
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Sort elements, placing them in tmp array
        for (int i = arr.length - 1; i >= 0; i--) {
            tmp[count[(arr[i] / place) % 10] - 1] = arr[i];
            count[(arr[i] / place) % 10]--;
        }

        System.arraycopy(tmp, 0, arr, 0, arr.length);
    }

    /** BUCKET SORT
     *
     * you are allowed to use Collections.Sort ONLY for sorting the individual buckets
     *
     * bucket sort is a scatter and gather sorting algorithm where you split all the
     * elements into "buckets" that correspond with which range the number lies in.
     *
     * You must first get the maximum and minimum elements and then calculate the ranges
     * the buckets will use.
     *
     * hint: (max - min) / number of buckets
     *
     * You must then disperse the elements into their corresponding buckets
     *
     * Then sort the buckets. (This is where you may use Collections.Sort or copy code
     * from the previous lab idc)
     *
     * Lastly "dump" the buckets back into the arr and you are done!
     *
     * @param arr array to be sorted
     * @param noOfBuckets the number of buckets to be used
     */
    static void bucketSort(int[] arr, int noOfBuckets)
    {
        //TODO

        if(noOfBuckets <= 0)
            return;

        int max = max(arr);
        int min = min(arr);
        int range = (max - min) / noOfBuckets;

        //Init bucket arraylist to how hold buckets
        ArrayList<Integer>[] bucket = new ArrayList[noOfBuckets];

        // Fill arrayList with empty buckets
        for (int i = 0; i < noOfBuckets; i++)
            bucket[i] = new ArrayList<Integer>();

        // Add arr elements into the buckets
        for (int i = 0; i < noOfBuckets; i++) {
            int bucketIndex = arr[i] / range;
            if (bucketIndex >= noOfBuckets) {
                bucketIndex = noOfBuckets - 1;
            } else if (bucketIndex < 0) {
                bucketIndex = 0;
            }
            bucket[bucketIndex].add(arr[i]);
        }

        // Sort the elements of each bucket
        for (int i = 0; i < noOfBuckets; i++) {
            Collections.sort((bucket[i]));
        }

        // Get the sorted array
        int index = 0;
        for (int i = 0; i < noOfBuckets; i++) {
            for (int j = 0, size = bucket[i].size(); j < size; j++) {
                arr[index++] = bucket[i].get(j);
            }
        }
    }

    /** MAX
     * useful for bucket sort and radix sort
     *
     * iterate through the array and return the largest element
     *
     * @param arr array of integers
     * @return the maximum element of the array
     */
    public static int max(int[] arr) {
        //TODO

        int tmp = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > tmp)
                tmp = arr[i];
        }

        return tmp;
    }

    /** MIN
     * useful for bucketsort
     *
     * iterate through the array and return the smallest element
     *
     * @param arr array of integers
     * @return the minimum element of the array
     */
    public static int min(int[] arr) {
        //TODO
        int tmp = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] < tmp)
                tmp = arr[i];
        }

        return tmp;
    }

}
