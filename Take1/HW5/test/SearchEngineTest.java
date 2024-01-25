import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SearchEngineTest {
    // TODO: accuracy tests + 1 efficiency test to make sure both hashtables build in under a minute

    SearchEngine engineOne;

    @Test
    void HashFailConstrTest(){
        Throwable exception = assertThrows(IOException.class, ()->{
           engineOne = new SearchEngine(4);
        });

        Throwable exception1 = assertThrows(IOException.class, ()->{
            engineOne = new SearchEngine(7);
        });
    }

    @Test
    void HashOAConstrTest(){
        {
            try {
                engineOne = new SearchEngine(5);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    SearchEngine engineTwo;

    @Test
    void HashCConstrTest(){
        {
            try {
                engineTwo = new SearchEngine(6);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    void HashOASearchTest(){
        HashOAConstrTest();

        engineOne.search("music");
        engineOne.search("january");
        engineOne.search("geography");
        engineOne.search("land");
        engineOne.search("people");
    }

    @Test
    void HashCSearchTest(){
        HashCConstrTest();

        engineTwo.search("music");
        engineTwo.search("january");
        engineTwo.search("geography");
        engineTwo.search("land");
        engineTwo.search("people");
    }

    @Test
    void efficiencyTest(){
        long HashOAStartT = System.nanoTime();
        HashOAConstrTest();
        long HashOAElapsedTime = System.nanoTime() - HashOAStartT;

        long HashCStartT = System.nanoTime();
        HashCConstrTest();
        long HashCElapsedTime = System.nanoTime() - HashCStartT;

        assertTrue(HashOAElapsedTime > HashCElapsedTime);

        HashOAStartT = System.nanoTime();
        HashOASearchTest();
        HashOAElapsedTime = System.nanoTime() - HashOAStartT;

        HashCStartT = System.nanoTime();
        HashCSearchTest();
        HashCElapsedTime = System.nanoTime() - HashCStartT;

        assertTrue(HashCElapsedTime < HashOAElapsedTime);
    }
}