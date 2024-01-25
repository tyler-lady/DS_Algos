import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AutoCompleteTest {
    // TODO: accuracy tests

    AutoComplete a;

    @BeforeEach
    public void createAC() throws IOException {
        a = new AutoComplete("dataset.txt");
    }
    @Test
    public void ACTest() {
        ArrayList<Entry> ls = a.autoComplete("th");
        assertEquals(6, ls.size());
        ls = a.autoComplete(" ");
        assertEquals(0, ls.size());
        ls = a.autoComplete(".");
        assertEquals(0, ls.size());
    }

    @Test
    public void bubbleSortAcc(){
        ArrayList<Entry> ls = new ArrayList<Entry>();
        ArrayList<Entry> sortedls = new ArrayList<Entry>();
        Entry e = new Entry(2, "car");
        Entry e1 = new Entry(5, "car");
        Entry e2 = new Entry(1, "car");
        Entry e3 = new Entry(8, "car");
        Entry e4 = new Entry(45, "car");
        Entry e5 = new Entry(33, "car");
        Entry e6 = new Entry(20, "car");
        Entry e7 = new Entry(12, "car");

        ls.add(e);
        ls.add(e1);
        ls.add(e2);
        ls.add(e3);
        ls.add(e4);
        ls.add(e5);
        ls.add(e6);
        ls.add(e7);

        sortedls.add(e4);
        sortedls.add(e5);
        sortedls.add(e6);
        sortedls.add(e7);
        sortedls.add(e3);
        sortedls.add(e1);
        sortedls.add(e);
        sortedls.add(e2);

        ls = a.bubbleSort(ls);

        assertArrayEquals(sortedls.toArray(), ls.toArray());
    }
}
