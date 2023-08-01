import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MazeSolver {
    static char[][] maze;
    static int m, n; // dimensions of the maze; row and col

    public MazeSolver() {
    }

    /*
    TODO: setMaze - sets up the board
    This method will take in a String, file, which is the path of the file we want to look at.
    Using BufferedReader and FileReader, write this method so that it sets the rows, m, and columns, n,
    to the first line of input. Then it fills the maze with the maze from the file.
     */
    public static void setMaze(String file) throws IOException {
        //so let's find the dimensions first
        BufferedReader readerDimensions = new BufferedReader(new FileReader(file));
        String line = readerDimensions.readLine(); //reads first line with dimensions
        String columns = "";
        String rows = "";
        //so this will give the string version of cols and rows

        boolean colDone = false;

        //once rows are done, then we can leave the while loop
        for (int i = 0; i < line.length(); i++) {
            //if the columns are done, then we must proceed to the number of rows
            if (line.charAt(i) == ' ' && columns != null) {
                colDone = true;
            }
            else if (line.charAt(i) != ' ') {
                //if there is a value (aka number), add it to columns/rows
                if (colDone) {
                    rows += String.valueOf(line.charAt(i));
                }
                else {
                    columns += String.valueOf(line.charAt(i));
                }
            }
        }

        m = Integer.parseInt(columns);
        n = Integer.parseInt(rows);
        maze = new char[m][n];

        readerDimensions.close();

        //Okay, now we're opening a new BufferedReader to read the remaining lines
        BufferedReader mazeReader = new BufferedReader(new FileReader(file));
        //skip the first line
        mazeReader.readLine();

        line = mazeReader.readLine();
        //second line is read through

        int row = 0; //temp number to check rows
        try {
            while (line != null) { //while line isn't empty

                for (int i = 0; i < line.length(); i++) { //goes through each character in a line
                    if (line.charAt(i) == 'S') {
                        maze[row][i] = 'S';
                    }
                    else if (line.charAt(i) == 'G') {
                        maze[row][i] = 'G';
                    }
                    else if (line.charAt(i) == '#') {
                        maze[row][i] = '#';
                    }
                    else if (line.charAt(i) == '.') {
                        maze[row][i] = '.';
                    }
                }
                row++; //row increases
                line = mazeReader.readLine(); //reads next line
            }
            mazeReader.close();

        }catch(IOException e){
            e.printStackTrace();

        }
    }

    /*
    TODO: isValid - checks if a position on the board has not been visited and is within bounds
     */
    public static boolean isValid(int x, int y) {
        if (x < 0 || y < 0) {
            return false;
        }
        if (x >= m || y >= n) {
            return false;
        }
        if (maze[x][y] == '#') {
            return false; //not valid
        }
        else {
            return true; //valid, can go
        }
    }

    /*
    TODO: solveMaze - recursive function which will traverse the maze and find whether it's solveable S -> G
    The input is the index that we want to check: x = row, y = column
    ------ Hint -------
    Cell(i,j) -> if it’s a wall return false
    Cell(i,j) is ‘G’ return true
    Otherwise, mark cell(i,j) as visited (maybe make it a wall) and
    return if you can find a way out from the top, bottom, left, or right…
     */
    public static boolean solveMaze(int x, int y) {
        // modify
        //BASE CASES:

        ArrayList<Integer> allPaths = new ArrayList<>();
        //number of paths you can take

        int LEFT_SIDE = 0;
        int RIGHT_SIDE = 1;
        int TOP_SIDE = 2;
        int BOTTOM_SIDE = 3;

        if (maze[x][y] == 'G') { //if made it to end, return true;
            return true;
        }
        else { //recursion
            if (isValid(x, y-1)) { //if it's on the left
                maze[x][y] = '#'; //turn current cell to a wall; already visited

                allPaths.add(LEFT_SIDE);
                System.out.println("Left added.");
                //return solveMaze(x, y-1);
            }
            if (isValid(x, y + 1)) { //if it's on the right
                maze[x][y] = '#'; //turn current cell to a wall; already visited

                allPaths.add(RIGHT_SIDE);
                System.out.println("Right added.");
                //return solveMaze(x, y+1);
            }
            if (isValid(x - 1, y)) { //if it's on top
                maze[x][y] = '#'; //turn current cell to a wall; already visited

                allPaths.add(TOP_SIDE);
                System.out.println("Top added.");
                //return solveMaze(x - 1, y);
            }
            if (isValid(x + 1, y)) { //if it's on bottom
                maze[x][y] = '#'; //turn current cell to a wall; already visited

                allPaths.add(BOTTOM_SIDE);
                System.out.println("Down added");
                //return solveMaze(x + 1, y);
            }

            //This is the part where we need to identify which paths we can go through or not

            //if ALL 4 paths are available, return all four
            if (allPaths.contains(LEFT_SIDE) && allPaths.contains(RIGHT_SIDE) && allPaths.contains(TOP_SIDE) && allPaths.contains(BOTTOM_SIDE)) {
                return (solveMaze(x, y-1) || solveMaze(x, y+1) || solveMaze(x - 1, y) || solveMaze(x + 1, y));
            }

            //the following contain three paths

            //left, right, and top
            else if (allPaths.contains(LEFT_SIDE) && allPaths.contains(RIGHT_SIDE) && allPaths.contains(TOP_SIDE)) {
                return (solveMaze(x, y-1) || solveMaze(x, y+1) || solveMaze(x - 1, y));
            }

            //left, right, and bottom
            else if (allPaths.contains(LEFT_SIDE) && allPaths.contains(RIGHT_SIDE) && allPaths.contains(BOTTOM_SIDE)) {
                return (solveMaze(x, y-1) || solveMaze(x, y+1) || solveMaze(x + 1, y));
            }

            //left, top, and bottom
            else if (allPaths.contains(LEFT_SIDE) && allPaths.contains(TOP_SIDE) && allPaths.contains(BOTTOM_SIDE)) {
                return (solveMaze(x, y-1) || solveMaze(x - 1, y) || solveMaze(x + 1, y));
            }

            //right, top, and bottom
            else if (allPaths.contains(RIGHT_SIDE) && allPaths.contains(TOP_SIDE) && allPaths.contains(BOTTOM_SIDE)) {
                return (solveMaze(x, y+1) || solveMaze(x - 1, y) || solveMaze(x + 1, y));
            }

            //alright now for two pathways

            //left and right
            else if (allPaths.contains(LEFT_SIDE) && allPaths.contains(RIGHT_SIDE)) {
                return (solveMaze(x, y-1) || solveMaze(x, y+1));
            }
            //left and top
            else if (allPaths.contains(LEFT_SIDE) && allPaths.contains(TOP_SIDE)) {
                return (solveMaze(x, y-1) || solveMaze(x - 1, y));
            }
            //left and bottom
            else if (allPaths.contains(LEFT_SIDE) && allPaths.contains(BOTTOM_SIDE)) {
                return (solveMaze(x, y-1) || solveMaze(x + 1, y));
            }
            //right and top
            else if (allPaths.contains(RIGHT_SIDE) && allPaths.contains(TOP_SIDE)) {
                return (solveMaze(x, y+1) || solveMaze(x - 1, y));
            }
            //right and bottom
            else if (allPaths.contains(RIGHT_SIDE) && allPaths.contains(BOTTOM_SIDE)) {
                return (solveMaze(x, y+1) || solveMaze(x + 1, y));
            }
            //bottom and top
            else if (allPaths.contains(TOP_SIDE) && allPaths.contains(BOTTOM_SIDE)) {
                return (solveMaze(x - 1, y) || solveMaze(x + 1, y));
            }

            //Okay now we're down to one path, and one path only

            else if (allPaths.contains(LEFT_SIDE)) {
                return (solveMaze(x, y-1));
            }
            else if (allPaths.contains(RIGHT_SIDE)) {
                return (solveMaze(x, y+1));
            }
            else if (allPaths.contains(TOP_SIDE)) {
                return (solveMaze(x-1, y));
            }
            else if (allPaths.contains(BOTTOM_SIDE)) {
                return (solveMaze(x+1, y));
            }
            else {
                return false;
            }
        }

    }

    /*
    TODO: solve - sets the maze up, solves the board, print whether it can be solved or not, returns whether its solvable or not
     */
    public static boolean solve(String file) throws IOException {
        //modify
        boolean solved = false;
        setMaze(file);
        //sets up the maze given the file

        //Now that the maze is set up, we need to try to find where S is
        //We are under the assumption that S can be anywhere from (0,0) to the
        //bottom right of the 2D array maze

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'S') {
                    solved = solveMaze(i, j);
                    break;
                }
            }
        }

        return solved;
    }


}