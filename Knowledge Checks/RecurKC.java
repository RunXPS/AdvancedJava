//import java.util.*;

public class RecurKC {
    public static void main(String[] args) {
        // Questions 1 & 2
        String s = "1234567890";
        // stringRecur(s);
        // Question 3
        // System.out.println(result(6));
        // Question 4
        int[] x = { 6, 42, 0, 8, 60, 14 };
        int n = 3;
        System.out.println(recur(x, n));
        for (int i = 0; i <= n; i++) {
            System.out.print(x[i] + " ");
        }
        // Question 5
        String test = "obtuse";
        // printString(test);
    }

    public static void stringRecur(String s) {
        if (s.length() < 15) {
            System.out.println(s);
            stringRecur(s + "*");
        } /*
           * else {
           * return;
           * }
           */
        //
    }

    public static int result(int n) {
        if (n == 1)
            return 2;
        else
            return 2 * result(n - 1);
    }

    /**
     * @param x an array of n integers
     * @param n a positive integer
     */
    public static int recur(int[] x, int n) {
        int t;
        if (n == 1)
            return x[0];
        else {
            t = recur(x, n - 1);
            if (x[n - 1] > t)
                return x[n - 1];
            else
                return t;
        }
    }

    public static void printString(String s) {
        if (s.length() > 0) {
            printString(s.substring(1));
            System.out.print(s.substring(0, 1));
        }
    }
}
