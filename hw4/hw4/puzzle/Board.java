package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private int[][] board;
    private int N;
    private static final int BLANK = 0;
    private int estiDis = -1;

    /** Constructs a board from an N-by-N array of tiles where
      * tiles[i][j] = tile at row i, column j */
    public Board(int[][] tiles) {
        N = tiles.length;

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = tiles[i][j];
            }
        }

    }

    /** Returns value of tile at row i, column j (or 0 if blank) */
    public int tileAt(int i, int j) {
        if ((i < N && i >= 0) && (j < N && j >= 0)) {
            return board[i][j];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /** Returns the board size N */
    public int size() {
        return N;
    }

    /** Returns the neighbors of the current board */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /** Hamming estimate */
    public int hamming() {
        int errors = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == BLANK) {
                    continue;
                }
                if (board[i][j] != N * i + j + 1) {
                    errors += 1;
                }
            }
        }
        return errors;
    }

    /** Manhattan estimate */
    public int manhattan() {
        int errors = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == BLANK) {
                    continue;
                }
                int v = board[i][j];
                if (v != N * i + j + 1) {
                    errors += Math.abs((v - 1) / N - i) + Math.abs((v - 1) % N - j);
                }
            }
        }
        return errors;
    }

    /** Estimated distance to goal. This method should
      * simply return the results of manhattan() when submitted to
      * Gradescope. */
    @Override
    public int estimatedDistanceToGoal() {
        if (estiDis == -1) {
            estiDis = manhattan();
        }
        return estiDis;
    }

    /** Returns true if this board's tile values are the same
      * position as y's */
    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || getClass() != y.getClass()) {
            return false;
        }

        Board b = (Board) y;

        if (b.size() != N) {
            return false;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != b.tileAt(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = board != null ? board.hashCode() : 0;
        return result;
    }

    /** Returns the string representation of the board.
      * Uncomment this method. */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
