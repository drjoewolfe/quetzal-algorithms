package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DistinctSubsequences {
    class Solution {
        public int numDistinct(String s, String t) {
            if(s == null || t == null || s.length() < t.length()) {
                return 0;
            }

            int m = s.length();
            int n = t.length();

            int[] dp = new int[n + 1];
            dp[0] = 1;

            for(int i = 1; i <= m; i++) {
                char sc = s.charAt(i - 1);

                for(int j = n; j > 0; j--) {
                    char tc = t.charAt(j - 1);

                    if(sc == tc) {
                        dp[j] = dp[j - 1] + dp[j];
                    }
                }
            }

            return dp[n];
        }
    }

    class Solution_DP_2 {
        public int numDistinct(String s, String t) {
            if(s == null || t == null || s.length() < t.length()) {
                return 0;
            }

            int m = s.length();
            int n = t.length();
            int[] prev = new int[n + 1];
            int[] curr = new int[n + 1];

            prev[0] = 1;
            curr[0] = 1;

            for(int i = 1; i <= m; i++) {
                char sc = s.charAt(i - 1);

                for(int j = 1; j <= n; j++) {
                    char tc = t.charAt(j - 1);

                    if(sc == tc) {
                        curr[j] = prev[j - 1] + prev[j];
                    } else {
                        curr[j] = prev[j];
                    }
                }

                prev = curr.clone();
            }

            return curr[n];
        }
    }

    class Solution_DP {
        public int numDistinct(String s, String t) {
            if(s == null || t == null || s.length() < t.length()) {
                return 0;
            }

            int m = s.length();
            int n = t.length();
            int[][] dp = new int[m + 1][n + 1];

            for(int i = 0; i < m; i++) {
                dp[i][0] = 1;
            }

            for(int i = 1; i <= m; i++) {
                char sc = s.charAt(i - 1);

                for(int j = 1; j <= n; j++) {
                    char tc = t.charAt(j - 1);

                    if(sc == tc) {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            return dp[m][n];
        }

        private void print(int[][] arr) {
            for(int i = 0; i < arr.length; i++) {
                for(int j = 0; j < arr[0].length; j++) {
                    System.out.print(arr[i][j] + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Memo_3 {
        public int numDistinct(String s, String t) {
            if(s == null || t == null || s.length() < t.length()) {
                return 0;
            }

            int m = s.length();
            int n = t.length();
            int[][] dp = new int[m][n];
            for(int i = 0; i < m; i++) {
                Arrays.fill(dp[i], -1);
            }

            return countDistinctSubsequences(s, t, 0, 0, dp);
        }

        private int countDistinctSubsequences(String s, String t, int si, int ti, int[][] dp) {
            if(ti == t.length()) {
                return 1;
            }

            if(si == s.length()) {
                return 0;
            }

            if(dp[si][ti] != -1) {
                return dp[si][ti];
            }

            char sc = s.charAt(si);
            char tc = t.charAt(ti);

            int count;
            if(sc == tc) {
                dp[si][ti] = countDistinctSubsequences(s, t, si + 1, ti + 1, dp)
                        + countDistinctSubsequences(s, t, si + 1, ti, dp);
            } else {
                dp[si][ti] = countDistinctSubsequences(s, t, si + 1, ti, dp);
            }

            return dp[si][ti];
        }
    }

    class Solution_Memo_2 {
        public int numDistinct(String s, String t) {
            if(s == null || t == null || s.length() < t.length()) {
                return 0;
            }

            return countDistinctSubsequences(s, t, 0, 0, new HashMap<>());
        }

        private int countDistinctSubsequences(String s, String t, int si, int ti, Map<String, Integer> memo) {
            if(ti == t.length()) {
                return 1;
            }

            if(si == s.length()) {
                return 0;
            }

            String memento = si + "-" + ti;
            if(memo.containsKey(memento)) {
                return memo.get(memento);
            }

            char sc = s.charAt(si);
            char tc = t.charAt(ti);

            int count;
            if(sc == tc) {
                count = countDistinctSubsequences(s, t, si + 1, ti + 1, memo)
                        + countDistinctSubsequences(s, t, si + 1, ti, memo);
            } else {
                count = countDistinctSubsequences(s, t, si + 1, ti, memo);
            }

            memo.put(memento, count);
            return count;
        }
    }

    class Solution_Memo_1 {
        public int numDistinct(String s, String t) {
            if(s == null || t == null || s.length() < t.length()) {
                return 0;
            }

            return countDistinctSubsequences(s, t, 0, 0, new StringBuilder(), new HashMap<>());
        }

        private int countDistinctSubsequences(String s, String t, int si, int ti, StringBuilder builder, Map<String, Integer> memo) {
            if(builder.length() == t.length()) {
                return 1;
            }

            if(si == s.length()) {
                return 0;
            }

            String memento = si + "-" + ti;
            if(memo.containsKey(memento)) {
                return memo.get(memento);
            }

            char sc = s.charAt(si);
            char tc = t.charAt(ti);

            int count = 0;
            if(sc == tc) {
                builder.append(sc);
                count += countDistinctSubsequences(s, t, si + 1, ti + 1, builder, memo);
                builder.deleteCharAt(builder.length() - 1);

                count += countDistinctSubsequences(s, t, si + 1, ti, builder, memo);
            } else {
                count += countDistinctSubsequences(s, t, si + 1, ti, builder, memo);
            }

            memo.put(memento, count);
            return count;
        }
    }

    class Solution_Brute_3 {
        public int numDistinct(String s, String t) {
            if(s == null || t == null || s.length() < t.length()) {
                return 0;
            }

            return countDistinctSubsequences(s, t, 0, 0, new StringBuilder());
        }

        private int countDistinctSubsequences(String s, String t, int si, int ti, StringBuilder builder) {
            if(builder.length() == t.length()) {
                return 1;
            }

            if(si == s.length()) {
                return 0;
            }

            char sc = s.charAt(si);
            char tc = t.charAt(ti);

            int count = 0;
            if(sc == tc) {
                builder.append(sc);
                count += countDistinctSubsequences(s, t, si + 1, ti + 1, builder);
                builder.deleteCharAt(builder.length() - 1);

                count += countDistinctSubsequences(s, t, si + 1, ti, builder);
            } else {
                count += countDistinctSubsequences(s, t, si + 1, ti, builder);
            }

            return count;
        }
    }

    class Solution_Brute_2 {
        int count;

        public int numDistinct(String s, String t) {
            if(s == null || t == null || s.length() < t.length()) {
                return 0;
            }

            count = 0;
            countDistinctSubsequences(s, t, 0, 0, new StringBuilder());

            return count;
        }

        private void countDistinctSubsequences(String s, String t, int si, int ti, StringBuilder builder) {
            if(builder.length() == t.length()) {
                count++;
                return;
            }

            if(si == s.length()) {
                return;
            }

            char sc = s.charAt(si);
            char tc = t.charAt(ti);

            if(sc == tc) {
                builder.append(sc);
                countDistinctSubsequences(s, t, si + 1, ti + 1, builder);
                builder.deleteCharAt(builder.length() - 1);

                countDistinctSubsequences(s, t, si + 1, ti, builder);
            } else {
                countDistinctSubsequences(s, t, si + 1, ti, builder);
            }


        }
    }

    class Solution_Brute_1 {
        int count;

        public int numDistinct(String s, String t) {
            if(s == null || t == null || s.length() < t.length()) {
                return 0;
            }

            count = 0;
            countDistinctSubsequences(s, t, 0, new StringBuilder());

            return count;
        }

        private void countDistinctSubsequences(String s, String t, int index, StringBuilder builder) {
            if(builder.length() == t.length()) {
                if(builder.toString().equals(t)) {
                    count++;
                }

                return;
            }

            if(index == s.length()) {
                return;
            }

            builder.append(s.charAt(index));
            countDistinctSubsequences(s, t, index + 1, builder);
            builder.deleteCharAt(builder.length() - 1);

            countDistinctSubsequences(s, t, index + 1, builder);
        }
    }

// "rabbbit"
// "rabbit"

// "dbaaadcddccdddcadacbadbadbabbbcad"
// "dadcccbaab"

// "adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc"
// "bcddceeeebecbc"
}

//    115. Distinct Subsequences
//    Hard
//    Given two strings s and t, return the number of distinct subsequences of s which equals t.
//
//    A string's subsequence is a new string formed from the original string by deleting some (can be none) of the characters without disturbing the remaining characters' relative positions. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).
//
//    The test cases are generated so that the answer fits on a 32-bit signed integer.
//
//
//
//    Example 1:
//
//    Input: s = "rabbbit", t = "rabbit"
//    Output: 3
//    Explanation:
//    As shown below, there are 3 ways you can generate "rabbit" from S.
//    rabbbit
//    rabbbit
//    rabbbit
//    Example 2:
//
//    Input: s = "babgbag", t = "bag"
//    Output: 5
//    Explanation:
//    As shown below, there are 5 ways you can generate "bag" from S.
//    babgbag
//    babgbag
//    babgbag
//    babgbag
//    babgbag
//
//
//    Constraints:
//
//    1 <= s.length, t.length <= 1000
//    s and t consist of English letters.