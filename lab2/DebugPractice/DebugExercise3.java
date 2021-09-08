/**
 * Created by jug on 1/22/18.
 */
public class DebugExercise3 {
    public static double countTurnips(In in) {
        double totalTurnips = 0;
        while (!in.isEmpty()) {
            String vendor = in.readString();
            String foodType = in.readString();
            double cost = in.readDouble();
            double numAvailable = in.readInt();
            if (foodType.equals("turnip")) {
                totalTurnips = totalTurnips + numAvailable;
            }
            in.readLine();
        }
        return totalTurnips;
    }

    public static void main(String[] args) {
        In in = new In("foods.csv");
        System.out.println(countTurnips(in));
    }
}
