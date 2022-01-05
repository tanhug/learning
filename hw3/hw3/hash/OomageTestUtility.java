package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {

        int[] bucketSize = new int[M];
        for (int i = 0; i < M; i++) {
            bucketSize[i] = 0;
        }

        for (Oomage o : oomages) {
            bucketSize[(o.hashCode() & 0x7fffffff) % M] += 1;
        }

        for (int i = 0; i < M; i++) {
            if (bucketSize[i] < oomages.size() / 50 || bucketSize[i] > oomages.size() / 2.5) {
                return false;
            }
        }
        return true;
    }
}
