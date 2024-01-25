public class Search {
    public static int find_first_true(boolean[] A, int begin, int end) {
        if(A.length < 1 || end < 1)
            return end;
        for(int i = begin; i < end; i++){
            if(A[i])
                return i;
        }
        return end;
    }

    public static int find_first_equal(int[] A, int x) {
        for(int i = 0; i < A.length; i++){
            if(A[i] == x)
                return i;
        }
        return A.length;
    }

    public static int find_first_true_sorted(boolean[] A, int begin, int end) {
        if(A.length<1 || end < 1)
            return end;
        int mid = end/2;
        int index = -1;

        if(A[mid])
            index = find_first_true(A, begin, mid); // First half
        else
            index = find_first_true(A, mid, end); // Second Half

        return index;
    }
}
