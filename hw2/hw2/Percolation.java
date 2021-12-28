package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf1;
    private WeightedQuickUnionUF uf2;
    private int[][] grid;
    private int N;
    private int numOfOpen;
    private final int OPEN = 1;
    private final int BLOCK = 0;
    private int TOP;
    private int BOTTOM;

    private int transformIndex(int row, int col) {
        return row * N + col;
    }

    /**
     * create N-by-N grid, with all sites initially blocked.
     * @param N
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        numOfOpen = 0;
        uf1 = new WeightedQuickUnionUF(N * N + 2);
        uf2 = new WeightedQuickUnionUF(N * N + 1);

        this.N = N;
        TOP = N * N;
        BOTTOM = N * N + 1;
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = BLOCK;
            }
        }
    }

    /**
     * open the site (row, col) if it is not open already
     * @param row
     * @param col
     */
    public void open(int row, int col) {
        if ((row < 0 || row > N - 1) || (col < 0 || col > N - 1)) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        if (row == 0) {
            uf1.union(transformIndex(row, col), TOP);
            uf2.union(transformIndex(row, col), TOP);
        }

        if (row == N - 1) {
            uf1.union(transformIndex(row, col), BOTTOM);
        }

        if (grid[row][col] == BLOCK) {
            grid[row][col] = OPEN;
            numOfOpen += 1;
        }

        if (row - 1 >= 0) {
            if (isOpen(row - 1, col)) {
                uf1.union(transformIndex(row - 1, col), transformIndex(row, col));
                uf2.union(transformIndex(row - 1, col), transformIndex(row, col));
            }
        }

        if (row + 1 < N) {
            if (isOpen(row + 1, col)) {
                uf1.union(transformIndex(row + 1, col), transformIndex(row, col));
                uf2.union(transformIndex(row + 1, col), transformIndex(row, col));
            }
        }

        if (col - 1 >= 0) {
            if (isOpen(row, col - 1)) {
                uf1.union(transformIndex(row, col - 1), transformIndex(row, col));
                uf2.union(transformIndex(row, col - 1), transformIndex(row, col));
            }
        }

        if (col + 1 < N) {
            if (isOpen(row, col + 1)) {
                uf1.union(transformIndex(row, col + 1), transformIndex(row, col));
                uf2.union(transformIndex(row, col + 1), transformIndex(row, col));
            }
        }
    }

    /**
     * is the site (row, col) open?
     * @param row
     * @param col
     * @return true or false
     */
    public boolean isOpen(int row, int col) {
        if ((row < 0 || row > N - 1) || (col < 0 || col > N - 1)) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        if (grid[row][col] == OPEN) {
            return true;
        }

        return false;
    }

    /**
     * is the site (row, col) full?
     * @param row
     * @param col
     * @return true or false
     */
    public boolean isFull(int row, int col) {
        return uf2.find(transformIndex(row, col)) == uf2.find(TOP);
    }

    /**
     * number of open sites
     * @return numOfOpen
     */
    public int numberOfOpenSites() {
        return numOfOpen;
    }

    /**
     * does the system percolate?
     * @return true or false
     */
    public boolean percolates() {
        return uf1.find(TOP) == uf1.find(BOTTOM);
    }

    public static void main(String[] args) {

    }   // use for unit testing (not required)


}
