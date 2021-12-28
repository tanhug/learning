package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private Percolation perc;
    private int[] x;

    /**
     * perform T independent experiments on an N-by-N grid.
     * @param N
     * @param T
     * @param pf
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        x = new int[T];
        for (int i = 0; i < T; i++) {
            x[i] = 0;
        }

        perc = pf.make(N);
        for (int i = 0; i < T; i++) {
            while (!perc.percolates()) {
                int randomRow = (int) StdRandom.uniform() * N;
                int randomCol = (int) StdRandom.uniform() * N;
                perc.open(randomRow, randomCol);
                x[i] += 1;
            }
        }
    }

    /**
     * sample mean of percolation threshold.
     * @return mean
     */
    public double mean() {
        return StdStats.mean(x);
    }

    /**
     * sample standard deviation of percolation threshold.
     * @return
     */
    public double stddev() {
        return StdStats.stddev(x);
    }

    /**
     * low endpoint of 95% confidence interval.
     * @return
     */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(x.length);
    }

    /**
     * high endpoint of 95% confidence interval.
     * @return
     */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(x.length);
    }
}
