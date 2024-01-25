import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    Stack st = new Stack<>();
    Stack st2 = new Stack<>();
    Stack st3 = new Stack<>();


    @Test
    void pushTest(){
        st.push(5);
        st.push(24);
        st.push(8);

        assertEquals(3, st.size());
    }

    @Test
    void push2Test(){
        st2.push(5);
        st2.push(24);
        st2.push(8);

        assertEquals(3, st2.size());
    }

    @Test
    void push3Test(){
        st3.push(5);
        st3.push(28);

        //assertEquals(2, q3.size());
    }

    @Test
    void push4Test(){
        st3.push(15);

        //assertEquals(1, q3.size());
    }

    @Test
    void popTest() throws EmptyStackE {
        Throwable exception = assertThrows(EmptyStackE.class,()->{
            st.pop();
        });

        pushTest();

        assertEquals(3, st.size());

        st.pop();
        assertEquals(2, st.size());
        st.pop();
        assertEquals(1, st.size());
        st.pop();
        assertEquals(0, st.size());
        Throwable exception1 = assertThrows(EmptyStackE.class,()->{
            st.pop();
        });
    }

    @Test
    void peekTest(){
        Throwable exception1 = assertThrows(IndexOutOfBoundsException.class,()->{
            st.peek();
        });
        pushTest();
        //Peek varies from queue
        assertEquals(8, st.peek());
    }

    @Test
    void equalsTest(){
        pushTest();
        push2Test();

        assertTrue(st.equals(st2));

        push3Test();
        assertFalse(st.equals(st3));
        push4Test();
        assertFalse(st.equals(st3));
    }

    @Test
    void printTest(){
        System.out.println(st.toString());
    }
}
