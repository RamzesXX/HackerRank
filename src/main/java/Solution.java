public class Solution {
    static String biggerIsGreater(String w) {
        if (w.length() < 2) {
            return "no answer";
        }

        int i, j;
        for (i = w.length() - 2; i > 0 && w.charAt(i + 1) <= w.charAt(i); i--);

        if (w.charAt(i + 1) <= w.charAt(i)) {
            return "no answer";
        }
        for (j = w.length() - 1; j > 0 && w.charAt(i) >= w.charAt(j); j--);

        StringBuilder stringBuilder = new StringBuilder(w.substring(0, i+1));
        for (int k = w.length() - 1; k > i; k--) {
            stringBuilder.append(w.charAt(k));
        };
        stringBuilder.setCharAt(i, w.charAt(j));
        stringBuilder.setCharAt(i + (w.length() - j), w.charAt(i));

        return stringBuilder.toString();
    }

}
