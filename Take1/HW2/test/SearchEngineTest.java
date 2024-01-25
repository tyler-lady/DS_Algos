import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SearchEngineTest {
    /*
    TODO: Write an efficiency test which asserts that the SortedArrayTest is faster than the ArrayList. Be sure to test for edge cases.
    Also be sure to write tests that check whether your SearchEngine has accurate results.
     */

    SearchEngine engineOne;

    {
        try {
            engineOne = new SearchEngine(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
/*
    SearchEngine engineTwo;

    {
        try {
            engineTwo = new SearchEngine(2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
*/

    @Test
    void get_Test(){

    }

    @Test
    void search_Test(){
        List<String> results = engineOne.search("january");

    }
}
