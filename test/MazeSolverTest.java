import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class MazeSolverTest {
    /*
    TODO - write JUnit tests testing the boards we gave you on the assignment
     */

    MazeSolver ms = new MazeSolver();

    @Test
    public void mazeTest() throws IOException {

        String file = "C:\\Users\\Elizabeth\\Documents\\C343Labs\\HW1\\maze1.txt";

        ms.setMaze(file);
        assertEquals(6, ms.m);
        assertEquals(6, ms.n);

        //so the dimensions are all set up, let's check if each value is in the 2D array

        //ROW 1
        assertEquals('S', ms.maze[0][0]);
        assertEquals('.', ms.maze[0][1]);
        assertEquals('.', ms.maze[0][2]);
        assertEquals('.', ms.maze[0][3]);
        assertEquals('.', ms.maze[0][4]);
        assertEquals('.', ms.maze[0][5]);

        assertEquals('#', ms.maze[1][0]);
        assertEquals('#', ms.maze[1][1]);
        assertEquals('#', ms.maze[1][2]);
        assertEquals('.', ms.maze[1][3]);
        assertEquals('#', ms.maze[1][4]);
        assertEquals('#', ms.maze[1][5]);

        assertEquals('#', ms.maze[2][0]);
        assertEquals('#', ms.maze[2][1]);
        assertEquals('#', ms.maze[2][2]);
        assertEquals('.', ms.maze[2][3]);
        assertEquals('#', ms.maze[2][4]);
        assertEquals('#', ms.maze[2][5]);

        assertEquals('#', ms.maze[3][0]);
        assertEquals('.', ms.maze[3][1]);
        assertEquals('.', ms.maze[3][2]);
        assertEquals('.', ms.maze[3][3]);
        assertEquals('.', ms.maze[3][4]);
        assertEquals('.', ms.maze[3][5]);

        assertEquals('#', ms.maze[4][0]);
        assertEquals('.', ms.maze[4][1]);
        assertEquals('#', ms.maze[4][2]);
        assertEquals('#', ms.maze[4][3]);
        assertEquals('#', ms.maze[4][4]);
        assertEquals('#', ms.maze[4][5]);

        assertEquals('.', ms.maze[5][0]);
        assertEquals('.', ms.maze[5][1]);
        assertEquals('.', ms.maze[5][2]);
        assertEquals('.', ms.maze[5][3]);
        assertEquals('G', ms.maze[5][4]);
        assertEquals('#', ms.maze[5][5]);

        //now that all of the values are put into the maze, check if isValid works or not
        //we'll only test it for the last row

        //Notes from Corrections Made in setMaze:
        //I failed to find the dimensions because the file was in String format
        //Therefore, I needed to find a way to find the dimensions, and then convert them to integer
        //Afterwards, I would have to set it to variables m and n.

        assertTrue(ms.isValid(5, 0));
        assertTrue(ms.isValid(5, 1));
        assertTrue(ms.isValid(5, 2));
        assertTrue(ms.isValid(5, 3));
        assertTrue(ms.isValid(5, 4));

        assertFalse(ms.isValid(5, 5));

        //Notes from Corrections Made in isValid:
        //I did not take into consideration the indexOutOfBounds errors, so
        //this time I added two if cases on whether the index was less than 0
        //or more than the dimensions of the 2D array

        //With isValid working, the focus on testing solveMaze

        assertEquals(true, ms.solveMaze(5, 4));
        //This is for G

        assertEquals(true, ms.solveMaze(0, 0));
        //Checking if the solveMaze works under the assumption that we know where S is

        //Notes from Corrections Made in MazeSolver:
        //I realize that I needed an arraylist to show all the paths that are available to run through, and a way to track them
        //I made else if cases for cases where 4, 3, 2, or 1 path was shown
        //If none were shown, then return false


        assertEquals(true, ms.solve(file));

        //Notes from Corrections made in Solve:
        //I actually have no idea what I was thinking I wanted to find out the position of where
        //S is, so I accidentally set up the maze in the solve function
        //All I had to do, was set the maze, find the S index GIVEN that the maze was set up
        //And then solve it

        //Okay now let's test out the three test cases

        assertTrue(MazeSolver.solve("C:\\Users\\Elizabeth\\Documents\\C343Labs\\HW1\\maze1.txt"));

        assertTrue(MazeSolver.solve("C:\\Users\\Elizabeth\\Documents\\C343Labs\\HW1\\maze2.txt"));

        assertFalse(MazeSolver.solve("C:\\Users\\Elizabeth\\Documents\\C343Labs\\HW1\\maze3.txt"));
    }


}
