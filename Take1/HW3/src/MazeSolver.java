import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Feel free to reuse from HW1

public class MazeSolver {
    static char[][] maze;
    static int m, n; // dimensions of the maze

    /*
    TODO: setMaze - sets up the board
    This method will take in a String, file, which is the path of the file we want to look at.
    Using BufferedReader and FileReader, write this method so that it sets the rows, m, and columns, n,
    to the first line of input. Then it fills the maze with the maze from the file.
     */
    public static void setMaze(String file) throws IOException {
        BufferedReader fileReader = null;

        fileReader = new BufferedReader(new FileReader(file));
        //use currentLine to consume first line
        String strCurrentLine;
        strCurrentLine = fileReader.readLine();
        String[] parsedLine = strCurrentLine.split(" ");
        m = Integer.parseInt(parsedLine[0]);
        n = Integer.parseInt(parsedLine[1]);

        maze = new char[m][n]; //[x][y]

        int j = 0;
        while ((strCurrentLine = fileReader.readLine()) != null) {
            parsedLine = strCurrentLine.split("");
            int i = 0;
            while(i < m && j < n){
                maze[j][i] = parsedLine[i].charAt(0);
                i++;
            }
            j++;
        }
    }

    /*
    TODO: isValid - checks if a position on the board has not been visited and is within bounds
     */
    public static boolean isValid(int x, int y) {
        if ( x > m || x <= 0 ){
            return false; //out of bounds
        } else if ( y > n || y <= 0 ){
            return false; //out of bounds
        } else if ( maze[x-1][y-1] == '#' ){
            return false; //is a wall
        }
        return true; //valid (open) space
    }


    /*
    TODO: Using a stack, solve the maze WITHOUT recursion.
    Pseudo:
    1) Push start position onto Stack.
    2) While it's not empty;
        3) Pop from the stack to get the current considered location
        4) If it's already explored ignore
        5) If it's the goal, return true
        6) If it's not the goal, then explore it.
        7) You will need to compute all the possible neighbors. Then push those on the stack
    8) Return false
     */

    public static boolean solveMazeStack(int x, int y)  throws EmptyStackE {
        return false;
    }

    // TODO: Using a queue, solve the maze. Be sure to explain your algorithm for full points.
    public static boolean solveMazeQueue(int x, int y) throws EmptyQueueE{
        return false;
    }

    // TODO: Solve the board. Mode 1 = stack solving. Mode 2 = queue solving.
    // 1: stack
    // 2: queue
    public static boolean solve(String file, int mode) throws IOException, EmptyStackE, EmptyQueueE {
        // find starting point
        // check if the maze can be solved
        boolean solved = false;
        System.out.println("Maze can be solved: " + solved);
        return false;
    }


}