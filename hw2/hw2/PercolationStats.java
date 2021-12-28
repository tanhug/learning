package hw2;

import edu.princeton.cs.algs4.StdRandom;

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

        for (int i = 0; i < T; i++) {
            perc = pf.make(N);
            while (!perc.percolates()) {
                int randomRow = (int) (StdRandom.uniform() * N);
                int randomCol = (int) (StdRandom.uniform() * N);
                if (!perc.isOpen(randomRow, randomCol)) {
                    perc.open(randomRow, randomCol);
                    x[i] += 1;
                }
            }
        }
    }

    /**
     * sample mean of percolation threshold.
     * @return mean
     */
    public double mean() {
        double mean = 0;
        for (int i = 0; i < x.length; i++) {
            mean += x[i] / x.length;
        }
        return mean;
    }

    /**
     * sample standard deviation of percolation threshold.
     * @return stddev
     */
    public double stddev() {
        double var = 0;
        for (int i = 0; i < x.length; i++) {
            var += Math.pow(x[i] - mean(), 2) / (x.length - 1);
        }
        return Math.sqrt(var);
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
