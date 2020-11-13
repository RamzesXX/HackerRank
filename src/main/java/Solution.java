//https://www.hackerrank.com/contests/projecteuler/challenges/euler254/problem

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Solution {
    private final static Map<Long, Long> factorials = new HashMap<>();
    private final static Map<Long, Long> g2nMapping = new HashMap<>();
    private static long maxProcessedN = 0L;

    static {
        factorials.put(0L, 1L);
        long factorial = 1;
        for (long i = 1; i < 10; i++) {
            factorial *= i;
            factorials.put(i, factorial);
        }
    }

    static long sumOfDigits(long number, Function<Long, Long> func) {
        long sumOfDigits = 0;
        while (number > 0) {
            sumOfDigits += func.apply(number % 10);
            number /= 10;
        }
        return sumOfDigits;
    }

    static long s_f(long number) {
        long f = sumOfDigits(number, factorials::get);
        return sumOfDigits(f, Function.identity());
    }

    static long g(long x) {
        if (g2nMapping.containsKey(x)) {
            return g2nMapping.get(x);
        }
        long value;

        while (maxProcessedN <= Long.MAX_VALUE-1) {
            maxProcessedN++;
            value = s_f(maxProcessedN);
            g2nMapping.putIfAbsent(value, maxProcessedN);
            if (value == x) {
                return maxProcessedN;
            }
        }
        throw new RuntimeException("Unable find g(x) for x=" + x);
    }

    static long s_g(long number) {
        return sumOfDigits(g(number), Function.identity());
    }

    static long solve(long n, long m) {
        long sum_s_g = 0;
        for (int i = 1; i <= n; i++) {
            sum_s_g += s_g(i);
        }
        return sum_s_g % m;
    }

    static long calls = 0;
    static void decompose(int n, String left) {
        calls++;
        if (n==0) {
            System.out.println(left);
            return;
        }

        for (int i = 0; i < (n+1) / 2; i++) {
            System.out.println(i + " + "+ (n-i) + " + " + left);
            decompose(i, n-i + " + " + left);
        }
    }
    public static void main(String[] args) {
//        Scanner scanner =  new Scanner(System.in);
//        int q = scanner.nextInt();
//        long m, n;
//        for (int i = 0; i < q; i++) {
//            n = scanner.nextLong();
//            m = scanner.nextLong();
//
//            System.out.println(solve(n, m));
//        }
//        scanner.close();
        decompose(1000, "");
        System.out.println(calls);
    }
}
