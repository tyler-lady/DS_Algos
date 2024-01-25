import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MazeSolverTest {

    String maze1 = "maze1.txt";
    String maze2 = "maze2.txt";
    String maze3 = "maze3.txt";

    String maze4 = "maze4.txt";

    @Test
    void setMaze_Test() throws IOException {
        var Maze = new MazeSolver();
        Maze.setMaze(maze1);

        assertNotNull(Maze);
    }

    @Test
    void isValid_Test() throws IOException {
        var Maze = new MazeSolver();
        Maze.setMaze(maze1);

        assertNotNull(Maze);

        assertFalse(Maze.isValid(6,6));
        //assertTrue(Maze.isValid(3,2));
        assertFalse(Maze.isValid(8,9));
        assertFalse(Maze.isValid(4,9));
        assertFalse(Maze.isValid(-1,4));
        assertFalse(Maze.isValid(4,-1));

        assertFalse(Maze.isValid(0,0));
        assertFalse(Maze.isValid(0,1));

        assertTrue(Maze.isValid(1,1));
        assertFalse(Maze.isValid(2,1));
        assertTrue(Maze.isValid(1,2));
        assertTrue(Maze.isValid(1,3));
        assertTrue(Maze.isValid(1,4));
    }
}
