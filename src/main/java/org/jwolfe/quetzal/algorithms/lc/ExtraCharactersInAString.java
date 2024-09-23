package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ExtraCharactersInAString {
    class Solution {
        public int minExtraChar(String s, String[] dictionary) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            Set<String> dictionarySet = new HashSet<>(Arrays.asList(dictionary));
            int[] dp = new int[n + 1];

            for (int start = n - 1; start >= 0; start--) {
                dp[start] = dp[start + 1] + 1;

                for (int end = start; end < n; end++) {
                    String str = s.substring(start, end + 1);
                    if (dictionarySet.contains(str)) {
                        dp[start] = Math.min(dp[start], dp[end + 1]);
                    }
                }
            }

            return dp[0];
        }
    }

    class Solution_Correct_1 {
        public int minExtraChar(String s, String[] dictionary) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            Integer[] memo = new Integer[n];
            Set<String> dictionarySet = new HashSet<>(Arrays.asList(dictionary));
            return minExtraChar(s, 0, n, dictionarySet, memo);
        }

        private int minExtraChar(String s, int start, int n, Set<String> dictionarySet, Integer[] memo) {
            if (start == n) {
                return 0;
            }

            if (memo[start] != null) {
                return memo[start];
            }

            int result = minExtraChar(s, start + 1, n, dictionarySet, memo) + 1;
            for (int end = start; end < n; end++) {
                String str = s.substring(start, end + 1);
                if (dictionarySet.contains(str)) {
                    result = Math.min(result,
                            minExtraChar(s, end + 1, n, dictionarySet, memo));
                }
            }

            memo[start] = result;
            return result;
        }
    }

    class Solution_TLE {
        public int minExtraChar(String s, String[] dictionary) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            Set<String> dictionarySet = new HashSet<>(Arrays.asList(dictionary));
            return minExtraChar(s, 0, n, dictionarySet);
        }

        private int minExtraChar(String s, int start, int n, Set<String> dictionarySet) {
            if (start == n) {
                return 0;
            }

            int result = minExtraChar(s, start + 1, n, dictionarySet) + 1;
            for (int end = start; end < n; end++) {
                String str = s.substring(start, end + 1);
                if (dictionarySet.contains(str)) {
                    result = Math.min(result,
                            minExtraChar(s, end + 1, n, dictionarySet));
                }
            }

            return result;
        }
    }

// "leetscode"
// ["leet","code","leetcode"]

// "enknouowgowcipfipojlrpuowgoiogiiebfjiafwksaigjyd"
// ["gw","lq","yzqch","sah","giieb","kfqczw","qxqz","jb","ucxmpe","hpwr","y","vzlhe","i","kn","ip","iafwk","zl","dw","yhxeqi","egktb","xasq","f","c","vrllz","p","uowgo","pgxd","gnjgkm","rnug","sa","vfccq","j"]
}

//    2707. Extra Characters in a String
//    Medium
//    You are given a 0-indexed string s and a dictionary of words dictionary. You have to break s into one or more non-overlapping substrings such that each substring is present in dictionary. There may be some extra characters in s which are not present in any of the substrings.
//
//    Return the minimum number of extra characters left over if you break up s optimally.
//
//
//
//    Example 1:
//
//    Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
//    Output: 1
//    Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8. There is only 1 unused character (at index 4), so we return 1.
//
//    Example 2:
//
//    Input: s = "sayhelloworld", dictionary = ["hello","world"]
//    Output: 3
//    Explanation: We can break s in two substrings: "hello" from index 3 to 7 and "world" from index 8 to 12. The characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence, we return 3.
//
//
//    Constraints:
//
//    1 <= s.length <= 50
//    1 <= dictionary.length <= 50
//    1 <= dictionary[i].length <= 50
//    dictionary[i] and s consists of only lowercase English letters
//    dictionary contains distinct words