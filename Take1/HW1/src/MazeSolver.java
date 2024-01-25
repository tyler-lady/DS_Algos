import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MazeSolver {
    static char[][] maze;
    static int m, n; // dimensions of the maze; row and col

    /*
        S start point, G goal, . valid path, # wall
        Make visited points a wall to prevent returning
    */

    /*
    TODO: setMaze - sets up the board
    This method will take in a String, file, which is the path of the file we want to look at.
    Using BufferedReader and FileReader, write this method so that it sets the rows, m, and columns, n,
    to the first line of input. Then it fills the maze with the maze from the file.
     */
    public static void setMaze(String file) throws IOException {
        // modify
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
            //System.out.println(strCurrentLine);
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
        // modify
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
    TODO: solveMaze - recursive function which will traverse the maze and find whether it's solveable S -> G
    The input is the index that we want to check: x = row, y = column
    ------ Hint -------
    Cell(i,j) -> if it’s a wall return false X
    Cell(i,j) is ‘G’ return true X
    Otherwise, mark cell(i,j) as visited (maybe make it a wall) X and
    return if you can find a way out from the top, bottom, left, or right… X
     */
    public static boolean solveMaze(int x, int y) {
        // modify
        //check u,d,l,r
        int i = x-1;
        int j = y-1;

        if ( isValid(x,y) ){
            if ( maze[i][j] == 'G' ){
                //System.out.println(maze[i][j]);
                return true;
            } else {
                boolean solveable = false; // init local bool for reuse
                //System.out.println(maze[i][j]); // prechange
                maze[i][j] = '#';
                //System.out.println(maze[i][j]); // postchange
                solveable = solveMaze(x - 1, y); // up
                if ( !solveable )
                    solveable = solveMaze(x + 1, y); // down
                if ( !solveable )
                    solveable = solveMaze(x, y - 1); // left
                if ( !solveable )
                    solveable = solveMaze(x, y + 1); // right
                return solveable;
            }
        }
        return false;
    }

    /*
    TODO: solve - sets the maze up, solves the board, print whether it can be solved or not, returns whether its solvable or not
     */
    public static boolean solve(String file) throws IOException {
        // modify
        setMaze(file);
        boolean solved = solveMaze(1,1);

        System.out.println("Maze can be solved: " + solved);
        return solved;
    }



}