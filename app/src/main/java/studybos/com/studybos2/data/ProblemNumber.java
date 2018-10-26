package studybos.com.studybos2.data;

/**
 * Created by 机械革命 on 2018/10/14.
 */

public class ProblemNumber {

    private static int problemNumber=0;

    public static int getProblemNumber() {
        return problemNumber;
    }

    public static void setProblemNumber(int problemNumber) {
        ProblemNumber.problemNumber = problemNumber;
    }
}
