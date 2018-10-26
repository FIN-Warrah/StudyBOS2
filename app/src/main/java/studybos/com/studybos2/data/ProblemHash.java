package studybos.com.studybos2.data;

/**
 * Created by 机械革命 on 2018/10/14.
 */

public class ProblemHash {
    private static int problemHash=0;

    public static int getProblemHash() {
        return problemHash;
    }

    public static void setProblemHash(int problemHash) {
        ProblemHash.problemHash = problemHash;
    }
}
