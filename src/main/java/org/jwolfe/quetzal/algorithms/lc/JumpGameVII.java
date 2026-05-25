package org.jwolfe.quetzal.algorithms.lc;

public class JumpGameVII {
    class Solution {
        public boolean canReach(String s, int minJump, int maxJump) {
            if (s == null || s.length() == 0) {
                return true;
            }

            if (s.charAt(s.length() - 1) == '1') {
                return false;
            }

            int n = s.length();
            int[] dp = new int[n];
            dp[0] = 1;


            int wayCount = 0;
            for (int i = 1; i < n; i++) {
                int start = i - maxJump;
                int end = i - minJump;

                // Adjust Sliding Window
                if (start - 1 >= 0) {
                    wayCount -= dp[start - 1];
                }

                if (end >= 0) {
                    wayCount += dp[end];
                }

                if (wayCount > 0 && s.charAt(i) == '0') {
                    dp[i] = 1;
                }
            }

            return dp[n - 1] > 0;
        }
    }

    class Solution_DP_TLE {
        public boolean canReach(String s, int minJump, int maxJump) {
            if (s == null || s.length() == 0) {
                return true;
            }

            if (s.charAt(s.length() - 1) == '1') {
                return false;
            }

            int n = s.length();
            boolean[] dp = new boolean[n];
            dp[n - 1] = true;

            for (int i = n - 2; i >= 0; i--) {
                if (s.charAt(i) == '1') {
                    continue;
                }

                int from = i + minJump;
                int to = i + maxJump;

                if (from >= n) {
                    continue;
                }

                if (to >= n) {
                    to = n - 1;
                }

                for (int nextIndex = from; nextIndex <= to; nextIndex++) {
                    if (dp[nextIndex]) {
                        dp[i] = true;
                        continue;
                    }
                }
            }

            return dp[0];
        }
    }

    class Solution_Memoized_TLE {
        public boolean canReach(String s, int minJump, int maxJump) {
            if (s == null || s.length() == 0) {
                return true;
            }

            if (s.charAt(s.length() - 1) == '1') {
                return false;
            }

            Boolean[] memo = new Boolean[s.length() - 1];
            return canReach(s, 0, minJump, maxJump, memo);
        }

        private boolean canReach(String s, int index, int minJump, int maxJump, Boolean[] memo) {
            if (index == s.length() - 1) {
                return true;
            }

            if (memo[index] != null) {
                return memo[index];
            }

            int from = index + minJump;
            if (from >= s.length()) {
                return memo[index] = false;
            }

            int to = index + maxJump;
            if (to >= s.length()) {
                to = s.length() - 1;
            }

            for (int nextIndex = from; nextIndex <= to; nextIndex++) {
                if (canReach(s, nextIndex, minJump, maxJump, memo)
                        && s.charAt(nextIndex) == '0') {
                    return memo[index] = true;
                }
            }

            return memo[index] = false;
        }
    }

    class Solution_TLE {
        public boolean canReach(String s, int minJump, int maxJump) {
            if (s == null || s.length() == 0) {
                return true;
            }

            if (s.charAt(s.length() - 1) == '1') {
                return false;
            }

            return canReach(s, 0, minJump, maxJump);
        }

        private boolean canReach(String s, int index, int minJump, int maxJump) {
            if (index == s.length() - 1) {
                return true;
            }

            int from = index + minJump;
            if (from >= s.length()) {
                return false;
            }

            int to = index + maxJump;
            if (to >= s.length()) {
                to = s.length() - 1;
            }

            for (int nextIndex = from; nextIndex <= to; nextIndex++) {
                if (canReach(s, nextIndex, minJump, maxJump)
                        && s.charAt(nextIndex) == '0') {
                    return true;
                }
            }

            return false;
        }
    }
}

//    1871. Jump Game VII
//    Medium
//    You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:
//
//    i + minJump <= j <= min(i + maxJump, s.length - 1), and
//    s[j] == '0'.
//    Return true if you can reach index s.length - 1 in s, or false otherwise.
//
//
//
//    Example 1:
//
//    Input: s = "011010", minJump = 2, maxJump = 3
//    Output: true
//    Explanation:
//    In the first step, move from index 0 to index 3.
//    In the second step, move from index 3 to index 5.
//    Example 2:
//
//    Input: s = "01101110", minJump = 2, maxJump = 3
//    Output: false
//
//
//    Constraints:
//
//    2 <= s.length <= 105
//    s[i] is either '0' or '1'.
//    s[0] == '0'
//    1 <= minJump <= maxJump < s.length