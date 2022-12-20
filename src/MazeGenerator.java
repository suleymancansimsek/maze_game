import java.util.Random;

// Süleyman Can Şimşek
//Maze Generator
// it is not working now , it will be finished soon

public class MazeGenerator {
    // constants for the dimensions of the maze
    private static final int MAZE_WIDTH = 10;
    private static final int MAZE_HEIGHT = 10;

    // constants for the characters that represent the different parts of the maze
    private static final char WALL = '#';
    private static final char SPACE = ' ';

    // the maze as a 2D char array
    private char[][] maze;

    // a random number generator
    private Random random;

    public MazeGenerator() {
        maze = new char[MAZE_HEIGHT][MAZE_WIDTH];
        random = new Random();
    }

    // generates a random maze
    public void generateMaze() {
        // initialize the maze with walls
        for (int i = 0; i < MAZE_HEIGHT; i++) {
            for (int j = 0; j < MAZE_WIDTH; j++) {
                maze[i][j] = WALL;
            }
        }

        // start at a random position in the maze
        int row = random.nextInt(MAZE_HEIGHT);
        int col = random.nextInt(MAZE_WIDTH);

        // create the maze using the recursive backtracking algorithm
        generateMaze(row, col);
    }

    // recursive method to generate the maze using the recursive backtracking algorithm
    private void generateMaze(int row, int col) {
        // mark the current position as part of the maze
        maze[row][col] = SPACE;

        // shuffle the directions
        int[] directions = {0, 1, 2, 3};
        shuffleArray(directions);

        // try each direction in a random order
        for (int direction : directions) {
            int newRow = row;
            int newCol = col;
            switch (direction) {
                case 0: // try to move up
                    newRow--;
                    break;
                case 1: // try to move right
                    newCol++;
                    break;
                case 2: // try to move down
                    newRow++;
                    break;
                case 3: // try to move left
                    newCol--;
                    break;
            }

            // check if the new position is a valid move
            if (newRow >= 0 && newRow < MAZE_HEIGHT && newCol >= 0 && newCol < MAZE_WIDTH && maze[newRow][newCol] == WALL) {
                // move to the new position and continue generating the maze
                generateMaze(newRow, newCol);
            }
        }
    }

    // shuffle an array in place
    private void shuffleArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    // get the maze as a 2D char array
    public char[][] getMaze() {
        return maze;
    }

    // prints the maze to the console
    private static void printMaze(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

}
