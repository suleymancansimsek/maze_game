import java.util.Scanner;

// Simple Maze game coded by Süleyman Can Şimşek
public class MazeGame {
    // constants for the dimensions of the maze
    private static final int MAZE_WIDTH = 10;
    private static final int MAZE_HEIGHT = 10;

    // constants for the characters that represent the different parts of the maze
    private static final char WALL = '#';
    private static final char PLAYER = 'P';
    private static final char EXIT = 'E';
    private static final char SPACE = ' ';

    public static void main(String[] args) {
        // create the maze
        char[][] maze = createMaze();

        // print the initial maze
        printMaze(maze);

        // create a scanner to read input from the console
        Scanner scanner = new Scanner(System.in);

        // game loop
        while (true) {
            // get the player's next move
            System.out.print("Enter a move (WASD): ");
            String move = scanner.nextLine();

            // move the player in the specified direction
            int[] playerPos = findPlayer(maze);
            int playerRow = playerPos[0];
            int playerCol = playerPos[1];
            switch (move) {
                case "W":
                    playerRow--;
                    break;
                case "A":
                    playerCol--;
                    break;
                case "S":
                    playerRow++;
                    break;
                case "D":
                    playerCol++;
                    break;
                default:
                    // invalid move
                    continue;
            }

            // check if the player has won or lost
            if (maze[playerRow][playerCol] == EXIT) {
                // player has won
                System.out.println("You won!");
                break;
            } else if (maze[playerRow][playerCol] == WALL) {
                // player has lost
                System.out.println("You lost!");
                break;
            }

            // update the maze with the player's new position
            maze[playerPos[0]][playerPos[1]] = SPACE;
            maze[playerRow][playerCol] = PLAYER;

            // print the updated maze
            printMaze(maze);
        }
    }

    // creates the maze as a 2D char array
    private static char[][] createMaze() {
        char[][] maze = new char[MAZE_HEIGHT][MAZE_WIDTH];

        // initialize the maze with walls and empty spaces
        for (int i = 0; i < MAZE_HEIGHT; i++) {
            for (int j = 0; j < MAZE_WIDTH; j++) {
                if (i == 0 || i == MAZE_HEIGHT - 1 || j == 0 || j == MAZE_WIDTH - 1) {
                    maze[i][j] = WALL;
                } else {
                    maze[i][j] = SPACE;
                }
            }
        }

        // add the player and exit to the maze
        maze[1][1] = PLAYER;
        maze[MAZE_HEIGHT - 2][MAZE_WIDTH - 2] = EXIT;

        return maze;
    }

    // prints the maze to the console
    private static void printMaze(char[][] maze) {
        for (int i = 0; i < MAZE_HEIGHT; i++) {
            for (int j = 0; j < MAZE_WIDTH; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    // finds the position of the player in the maze
    private static int[] findPlayer(char[][] maze) {
        for (int i = 0; i < MAZE_HEIGHT; i++) {
            for (int j = 0; j < MAZE_WIDTH; j++) {
                if (maze[i][j] == PLAYER) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }
}


