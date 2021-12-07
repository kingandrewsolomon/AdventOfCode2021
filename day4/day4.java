import java.io.*;

public class day4 {

    private static final int BOARD_CAP = 128;
    private static final int INPUT_CAP = 128;

    public static Board[] boards = new Board[BOARD_CAP];
    public static int boards_count = 0;

    public static int[] inputs = new int[INPUT_CAP];
    public static int input_count = 0;

    public static void main(String[] args) {
        // Part1();
        Part2();
    }

    static public class Board {
        public static final int BOARD_SIZE = 5;

        public int[][] s = new int[BOARD_SIZE][BOARD_SIZE];
        public boolean[][] status = new boolean[BOARD_SIZE][BOARD_SIZE];
    }

    public static void Part1() {

        for (int i = 0; i < BOARD_CAP; i++) {
            boards[i] = new Board();
        }

        InstantiateGame(boards, inputs);

        for (int i = 0; i < input_count; i++) {
            for (int x = 0; x < boards_count; x++) {
                CrossBoard(boards[x], inputs[i]);
                if (BoardWon(boards[x])) {
                    println(inputs[i] * sumOfBoard(boards[x]));
                    return;
                }

            }
        }
    }

    public static void Part2() {
        for (int i = 0; i < BOARD_CAP; i++) {
            boards[i] = new Board();
        }

        InstantiateGame(boards, inputs);
        boolean[] boardsLeftToWin = new boolean[boards_count];

        for (int i = 0; i < input_count; i++) {
            for (int x = 0; x < boards_count; x++) {
                CrossBoard(boards[x], inputs[i]);
                if (BoardWon(boards[x]) && BoardsLeft(x, boardsLeftToWin) == 0) {
                    println(inputs[i] * sumOfBoard(boards[x]));
                    return;
                }
            }
        }
    }

    private static int BoardsLeft(int x, boolean[] boardsLeftToWin) {
        int result = 0;
        boardsLeftToWin[x] = true;
        for (int i = 0; i < boardsLeftToWin.length; i++) {
            result += (boardsLeftToWin[i]) ? 0 : 1;
        }
        return result;
    }

    private static int sumOfBoard(Board board) {
        int sum = 0;
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                if (!board.status[i][j])
                    sum += board.s[i][j];
            }
        }
        return sum;
    }

    public static void printBoard(Board board) {
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                print(String.format("%02d ", board.s[i][j]));
            }
            println("");
        }
        println("");
    }

    private static boolean BoardWon(Board board) {
        int j;
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            j = 0;
            while (j < Board.BOARD_SIZE && board.status[i][j])
                ++j;
            if (j >= Board.BOARD_SIZE)
                return true;

            j = 0;
            while (j < Board.BOARD_SIZE && board.status[j][i])
                ++j;
            if (j >= Board.BOARD_SIZE)
                return true;
        }
        return false;
    }

    private static void CrossBoard(Board board, int value) {
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                if (board.s[i][j] == value)
                    board.status[i][j] = true;
            }
        }
    }

    public static void InstantiateGame(Board[] boards, int[] inputs) {
        try {
            File file = new File("/Volumes/shared/AdventOfCode/day4/day4_data.txt");
            FileReader fr = new FileReader(file);

            BufferedReader br = new BufferedReader(fr);

            // bingo inputs
            String line = br.readLine();
            String[] strLine = line.split(",");
            for (int i = 0; i < strLine.length; i++) {
                inputs[i] = Integer.parseInt(strLine[i]);
                input_count++;
            }

            br.readLine();

            int i = 0;
            while ((line = br.readLine()) != null) {
                // until new board is found
                if (!line.equals("")) {
                    // remove unnecessary white spaces
                    line = line.replaceAll("  ", " ").trim();

                    // separate into board cols
                    strLine = line.split(" ");

                    Board b = boards[boards_count];
                    // print(x);
                    for (int j = 0; j < Board.BOARD_SIZE; j++) {
                        b.s[i][j] = Integer.parseInt(strLine[j]);
                    }
                    i++;
                } else {
                    i = 0;
                    boards_count++;
                }
            }
            boards_count++;

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void print(Object p) {
        System.out.print(p);
    }

    public static void println(Object p) {
        System.out.println(p);
    }
}