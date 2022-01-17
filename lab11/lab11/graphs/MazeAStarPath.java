package lab11.graphs;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int vX = maze.toX(v);
        int vY = maze.toY(v);
        int targetX = maze.toX(t);
        int targetY = maze.toY(t);
        return Math.abs(vX - targetX) + Math.abs(vY - targetY);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }


    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        if (s == t) {
            targetFound = true;
            return;
        }


        PriorityQueue<Integer> PQ = new PriorityQueue<Integer>(maze.V(), new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return distTo[i1] - distTo[i2];
            }
        });

        PQ.add(s);
        announce();

        while (!PQ.isEmpty()) {
            int v = PQ.remove();
            marked[v] = true;
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    if (distTo[v] + 1 + h(w) < distTo[w]) {
                        edgeTo[w] = v;
                        announce();
                        distTo[w] = distTo[v] + 1 + h(w);
                        PQ.add(w);
                        if (w == t) {
                            marked[w] = true;
                            targetFound = true;
                            announce();
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

