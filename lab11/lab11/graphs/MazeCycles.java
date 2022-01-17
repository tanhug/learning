package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s = 0;
    private int spV;
    private boolean cyclesFound = false;
    private Maze maze;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    @Override
    public void solve() {
        solveHelper(s);
    }

    public void solveHelper(int v) {
        marked[v] = true;
        announce();

        if (cyclesFound) {
            return;
        }

        for (int w : maze.adj(v)) {
            if (marked[w] && w != edgeTo[v]) {
                spV = edgeTo[w];
                edgeTo[w] = v;
                cyclesFound = true;
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                announce();
                distTo[w] = distTo[v] + 1;
                solveHelper(w);
                if (cyclesFound) {
                    if (spV != 0) {
                        int tem = edgeTo[spV];
                        edgeTo[spV] = Integer.MAX_VALUE;
                        spV = tem;
                    }
                    announce();
                    return;
                }
            }
        }
    }
}

