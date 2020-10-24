import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the queensAttack function below.
    // Diagonals are defined by equation
    // r = r0 ± c
    // where r0 = r_q ± r_c

    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
        int queensAttack = 0;
        int limitD = 1;
        int limitU = n;
        int limitL = 1;
        int limitR = n;
        int r0 = r_q - c_q;
        int limitUR = (r0 + 1 > 0) ? n - r0 : n;
        int limitDL = (r0 + 1 > 0) ? 1 : 1 - r0;
        int r0_ = r_q + c_q;
        int limitUL = (r0_ - n > 0) ? r0_ - n : 1;
        int limitDR = (r0_ - n > 0) ? n : r0_ - 1;

        for (int[] coord : obstacles) {
            int r = coord[0];
            int c = coord[1];

            if (r != r_q || c != c_q) {
                if (r == r_q) {                             //  L -- R
                    if (c > c_q) {
                        limitR = Math.min(limitR, c - 1);
                    } else {
                        limitL = Math.max(limitL, c + 1);
                    }
                } else if (c == c_q) {
                    if (r < r_q) {                          // D -- U
                        limitD = Math.max(limitD, r + 1);
                    } else  {
                        limitU = Math.min(limitU, r - 1);
                    }
                } else if (r == r0 + c) {                   // DL -- UR
                    if (c < c_q) {
                        limitDL = Math.max(limitDL, c + 1);
                    }
                    else {
                        limitUR = Math.min(limitUR, c - 1);
                    }
                } else if (r == r0_ - c) {                  // UL -- DR
                    if (c < c_q) {
                        limitUL = Math.max(limitUL, c + 1);
                    }
                    else {
                        limitDR = Math.min(limitDR, c - 1);
                    }
                }
            }
        }

        queensAttack =
                Math.max(0, limitU - limitD)
                        + Math.max(0, limitR - limitL)
                        + Math.max(0, limitDR - limitUL)
                        + Math.max(0, limitUR - limitDL);
        return queensAttack;
    }

//    private static final Scanner scanner = new Scanner(System.in);
    private static final Scanner scanner = new Scanner(Solution.class.getClassLoader().getResourceAsStream("tc7.txt"));

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String[] r_qC_q = scanner.nextLine().split(" ");

        int r_q = Integer.parseInt(r_qC_q[0]);

        int c_q = Integer.parseInt(r_qC_q[1]);

        int[][] obstacles = new int[k][2];

        for (int i = 0; i < k; i++) {
            String[] obstaclesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
                obstacles[i][j] = obstaclesItem;
            }
        }

        int result = queensAttack(n, k, r_q, c_q, obstacles);
        System.out.println(result);
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
