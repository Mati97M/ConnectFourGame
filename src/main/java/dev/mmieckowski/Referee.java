package dev.mmieckowski;

import static dev.mmieckowski.ConnectFourBackend.COLS;
import static dev.mmieckowski.ConnectFourBackend.ROWS;
import java.util.ArrayList;
import java.util.LinkedList;

public class Referee {
    public static final int TARGET = 4;//down-left, up-right
    static final int[][] SECONDARY_DIAGONAL_DIRECTIONS = new int[][]{{1, -1}, {-1, 1}}; //down-left, up-right
    //down-right, up-left
    static final int[][] PRIMARY_DIAGONAL_DIRECTIONS = new int[][]{{1, 1}, {-1, -1}}; //down-right, up-left
    //left, right
    static final int[][] HORIZONTAL_DIRECTIONS = new int[][]{{0, -1}, {0, 1}}; //left, right
    static final int[][] VERTICAL_DIRECTIONS = new int[][]{{-1, 0}, {1, 0}}; //up, down
    private boolean[][] visited;
    private final char[][] board;
    private ArrayList<int[]> victoryIndexes = new ArrayList<>(4);

    Referee(char[][] board) {
        this.board = board;
    }

    boolean isGameOver(final int row, final int col) {
        visited = new boolean[ROWS][COLS];
        return checkVerticallyBFS(row, col) || checkHorizontallyBFS(row, col) || checkDiagonallyBFS(row, col);
    }

    boolean isOutOfArrayRange(int neighbourRow, int neighbourCol) {
        return neighbourRow < 0 || neighbourCol < 0 || neighbourRow >= ROWS || neighbourCol >= COLS;
    }

    void reset() {
        victoryIndexes = new ArrayList<>(TARGET);
    }

    ArrayList<int[]> getVictoryIndexes() {
        return new ArrayList<>(victoryIndexes);
    }

    private boolean checkDiagonallyBFS(int row, int col) {
        return checkPrimaryDiagonal(row, col) || checkSecondaryDiagonal(row, col);
    }

    private boolean checkSecondaryDiagonal(final int row, final int col) {
        return bfs(row, col, SECONDARY_DIAGONAL_DIRECTIONS);
    }

    private boolean checkPrimaryDiagonal(final int row, final int col) {
        return bfs(row, col, PRIMARY_DIAGONAL_DIRECTIONS);
    }

    private boolean checkHorizontallyBFS(final int row, final int col) {
        return bfs(row, col, HORIZONTAL_DIRECTIONS);
    }

    private boolean checkVerticallyBFS(final int row, final int col) {
        return bfs(row, col, VERTICAL_DIRECTIONS);
    }

    private boolean bfs(final int row, final int col, final int[][] directions) {
        char symbol = board[row][col];
        int count = 0;
        LinkedList<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] currCell = q.poll();
                addNextCellsToQueueFromDirections(directions, currCell, symbol, q);
                count++;
                if (count >= TARGET) {
                    return true;
                }
            }
        }
        visited[row][col] = false;
        victoryIndexes.clear();
        return false;
    }

    private void addNextCellsToQueueFromDirections(int[][] directions, int[] currCell, char symbol, LinkedList<int[]> q) {
        for (int[] dir : directions) {
            int neighbourRow = dir[0] + currCell[0];
            int neighbourCol = dir[1] + currCell[1];
            if (isOutOfArrayRange(neighbourRow, neighbourCol) || board[neighbourRow][neighbourCol] != symbol || visited[neighbourRow][neighbourCol]) {
                continue;
            }
            q.offer(new int[]{neighbourRow, neighbourCol});
        }
        visited[currCell[0]][currCell[1]] = true;
        victoryIndexes.add(currCell);
    }

}
