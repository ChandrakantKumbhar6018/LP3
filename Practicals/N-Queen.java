import java.util.Scanner;

class NQueens {

  private int[][] board;
  private int N;

  public NQueens(int N, int row, int col) {
    this.N = N;
    this.board = new int[N][N];

    board[row][col] = 1;
  }

  private boolean isSafe(int row, int col) {

    for (int i = 0; i < row; i++) {
      if (board[i][col] == 1)
        return false;
    }

    for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
      if (board[i][j] == 1)
        return false;
    }

    for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
      if (board[i][j] == 1)
        return false;
    }

    return true;
  }

  private boolean solveNQueens(int row) {

    if (row >= N)
      return true;

    for (int col = 0; col < N; col++) {
      if (isSafe(row, col)) {

        board[row][col] = 1;

        if (solveNQueens(row + 1))
          return true;

        board[row][col] = 0;
      }
    }

    return false;
  }

  private void printBoard() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the size of the board (N): ");
    int N = scanner.nextInt();

    System.out.print("Enter the row for the first queen (0-indexed): ");
    int row = scanner.nextInt();
    System.out.print("Enter the column for the first queen (0-indexed): ");
    int col = scanner.nextInt();

    NQueens nQueens = new NQueens(N, row, col);

    if (nQueens.solveNQueens(row + 1)) {
      System.out.println("Solution for " + N + "-Queens:");
      nQueens.printBoard();
    } else {
      System.out.println("No solution exists for the given initial position.");
    }

  }
}
