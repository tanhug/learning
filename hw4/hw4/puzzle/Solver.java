package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.HashMap;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Stack;

public class Solver {
    private ArrayList<WorldState> sol = new ArrayList<>();

    public Solver(WorldState initial) {
        if (initial.isGoal()) {
            sol.add(initial);
            return;
        }

        Stack<WorldState> solStack = new Stack<>();
        WorldState curState = initial;
        HashMap<WorldState, Integer> disTo = new HashMap<>();
        HashMap<WorldState, WorldState> edgeTo = new HashMap<>();
        MinPQ<WorldState> movePQ = new MinPQ<>(1, new Comparator<WorldState>() {
            @Override
            public int compare(WorldState o1, WorldState o2) {
                return disTo.get(o1) + o1.estimatedDistanceToGoal()
                        - disTo.get(o2) - o2.estimatedDistanceToGoal();
            }
        });

        movePQ.insert(curState);
        disTo.put(curState, 0);
        edgeTo.put(curState, null);

        while (!movePQ.isEmpty()) {
            curState = movePQ.delMin();

            for (WorldState neiState : curState.neighbors()) {
                if (!neiState.equals(edgeTo.get(curState))) {
                    int curDis = disTo.get(curState) + 1;

                    if (disTo.containsKey(neiState)) {
                        if (disTo.get(neiState) > curDis) {
                            edgeTo.put(neiState, curState);
                            disTo.put(neiState, curDis);
                            movePQ.insert(neiState);
                        }
                    } else {
                        edgeTo.put(neiState, curState);
                        disTo.put(neiState, curDis);
                        movePQ.insert(neiState);
                    }

                    if (neiState.isGoal()) {
                        solStack.push(neiState);
                        WorldState lastState = edgeTo.get(neiState);
                        solStack.push(lastState);
                        while (!lastState.equals(initial)) {
                            lastState = edgeTo.get(lastState);
                            solStack.push(lastState);
                        }
                        while (!solStack.isEmpty()) {
                            sol.add(solStack.pop());
                        }
                        return;
                    }
                }
            }
        }
    }
    public int moves() {
        return sol.size() - 1;
    }

    public Iterable<WorldState> solution() {
        return sol;
    }
}
