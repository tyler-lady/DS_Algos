import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {

    Queue q = new Queue<>();
    Queue q2 = new Queue<>();
    Queue q3 = new Queue<>();


    @Test
    void enqueueTest(){
        q.enqueue(5);
        q.enqueue(24);
        q.enqueue(8);

        assertEquals(3, q.size());
    }

    @Test
    void enqueue2Test(){
        q2.enqueue(5);
        q2.enqueue(24);
        q2.enqueue(8);

        assertEquals(3, q2.size());
    }

    @Test
    void enqueue3Test(){
        q3.enqueue(5);
        q3.enqueue(28);

        //assertEquals(2, q3.size());
    }

    @Test
    void enqueue4Test(){
        q3.enqueue(15);

        //assertEquals(1, q3.size());
    }

    @Test
    void dequeueTest() throws EmptyQueueE {
        Throwable exception = assertThrows(EmptyQueueE.class,()->{
            q.dequeue();
        });

        enqueueTest();

        assertEquals(3, q.size());

        q.dequeue();
        assertEquals(2, q.size());
        q.dequeue();
        assertEquals(1, q.size());
        q.dequeue();
        assertEquals(0, q.size());
        Throwable exception1 = assertThrows(EmptyQueueE.class,()->{
            q.dequeue();
        });
    }

    @Test
    void peekTest(){
        Throwable exception1 = assertThrows(IndexOutOfBoundsException.class,()->{
            q.peek();
        });
        enqueueTest();
        assertEquals(5, q.peek());
    }

    @Test
    void equalsTest(){
        enqueueTest();
        enqueue2Test();

        assertTrue(q.equals(q2));

        enqueue3Test();
        assertFalse(q.equals(q3));
        enqueue4Test();
        assertFalse(q.equals(q3));
    }

    @Test
    void printTest(){
        System.out.println(q.toString());
    }

}
