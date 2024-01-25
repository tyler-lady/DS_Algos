import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchEngineTest {
    // TODO: efficiency test and accuracy test
    SearchEngine engineOne;

    @Test
    void TreeFailConstrTest(){
        Throwable exception = assertThrows(IOException.class, ()->{
            engineOne = new SearchEngine(2);
        });

        Throwable exception1 = assertThrows(IOException.class, ()->{
            engineOne = new SearchEngine(5);
        });
    }

    @Test
    void BSTConstrTest(){
        {
            try {
                engineOne = new SearchEngine(3);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    SearchEngine engineTwo;

    @Test
    void AVLConstrTest(){
        {
            try {
                engineTwo = new SearchEngine(4);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    void BSTSearchTest(){
        BSTConstrTest();

        engineOne.search("music");
        engineOne.search("january");
        engineOne.search("geography");
        engineOne.search("land");
        engineOne.search("people");
    }

    @Test
    void AVLSearchTest(){
        AVLConstrTest();

        engineTwo.search("music");
        engineTwo.search("january");
        engineTwo.search("geography");
        engineTwo.search("land");
        engineTwo.search("people");
    }

    @Test
    void efficiencyTest(){
        long BSTStartT = System.nanoTime();
        BSTConstrTest();
        long BSTElapsedTime = System.nanoTime() - BSTStartT;

        long AVLStartT = System.nanoTime();
        AVLConstrTest();
        long AVLElapsedTime = System.nanoTime() - AVLStartT;

        assertTrue(BSTElapsedTime < AVLElapsedTime);

        BSTStartT = System.nanoTime();
        BSTSearchTest();
        BSTElapsedTime = System.nanoTime() - BSTStartT;

        AVLStartT = System.nanoTime();
        AVLSearchTest();
        AVLElapsedTime = System.nanoTime() - AVLStartT;

        assertTrue(AVLElapsedTime < BSTElapsedTime);
    }

}
