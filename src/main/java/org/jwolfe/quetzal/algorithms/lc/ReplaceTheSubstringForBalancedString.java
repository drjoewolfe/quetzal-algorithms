package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class ReplaceTheSubstringForBalancedString {
    class Solution {
        public int balancedString(String s) {
            if(s == null || s.length() % 4 != 0) {
                return Integer.MAX_VALUE;
            }

            int n = s.length();
            Map<Character, Integer> frequency = new HashMap<>();
            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);
                frequency.put(c, frequency.getOrDefault(c, 0) + 1);
            }

            int rc = n / 4;
            boolean allEqual = true;
            for(var entry : frequency.entrySet()) {
                int count = entry.getValue();
                if(count != rc) {
                    allEqual = false;
                }

                count = Math.max(count - rc, 0);
                frequency.put(entry.getKey(), count);
            }

            if(allEqual) {
                return 0;
            }

            int minLength = n;
            int left = 0;
            for(int right = 0; right < n; right++) {
                char c = s.charAt(right);
                frequency.put(c, frequency.get(c) - 1);

                while(isBalanced(frequency)) {
                    minLength = Math.min(minLength, right - left + 1);

                    char sc = s.charAt(left);
                    frequency.put(sc, frequency.get(sc) + 1);
                    left++;
                }
            }

            return minLength;
        }

        private boolean isBalanced(Map<Character, Integer> map) {
            for(var entry : map.entrySet()) {
                if(entry.getValue() > 0) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution_Incorrect {
        public int balancedString(String s) {
            if(s == null || s.length() % 4 != 0) {
                return Integer.MAX_VALUE;
            }

            int n = s.length();
            int maxFrequency = n / 4;

            Map<Character, Integer> frequency = new HashMap<>();

            int left = 0;
            for( ;left < n; left++) {
                char c = s.charAt(left);
                if(frequency.containsKey(c)) {
                    if(frequency.get(c) + 1 > maxFrequency) {
                        break;
                    } else {
                        frequency.put(c, frequency.get(c) + 1);
                    }
                } else {
                    frequency.put(c, 1);
                }
            }

            if(left == n) {
                return 0;
            }

            int right = n - 1;
            for(; right > left; right--) {
                char c = s.charAt(right);
                if(frequency.containsKey(c)) {
                    if(frequency.get(c) + 1 > maxFrequency) {
                        break;
                    } else {
                        frequency.put(c, frequency.get(c) + 1);
                    }
                } else {
                    frequency.put(c, 1);
                }
            }

            System.out.println(left + ", " + right);
            return right - left + 1;
        }
    }

    class Solution_Wrong {
        public int balancedString(String s) {
            if(s == null || s.length() % 4 != 0) {
                return Integer.MAX_VALUE;
            }

            int n = s.length();
            int f = n / 4;

            int qf = 0;
            int wf = 0;
            int ef = 0;
            int rf = 0;

            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if(c == 'Q') {
                    qf++;
                } else if(c == 'W') {
                    wf++;
                } else if(c == 'E') {
                    ef++;
                } else {
                    rf++;
                }
            }

            int minLength = 0;
            if(qf > f) {
                minLength += qf - f;
            }

            if(wf > f) {
                minLength += wf - f;
            }

            if(ef > f) {
                minLength += ef - f;
            }

            if(rf > f) {
                minLength += rf - f;
            }

            return minLength;
        }
    }

// "QWER"
// "WWEQERQWQWWRWWERQWEQ"
}

//    1234. Replace the Substring for Balanced String
//    Medium
//    You are given a string s of length n containing only four kinds of characters: 'Q', 'W', 'E', and 'R'.
//
//    A string is said to be balanced if each of its characters appears n / 4 times where n is the length of the string.
//
//    Return the minimum length of the substring that can be replaced with any other string of the same length to make s balanced. If s is already balanced, return 0.
//
//
//
//    Example 1:
//
//    Input: s = "QWER"
//    Output: 0
//    Explanation: s is already balanced.
//    Example 2:
//
//    Input: s = "QQWE"
//    Output: 1
//    Explanation: We need to replace a 'Q' to 'R', so that "RQWE" (or "QRWE") is balanced.
//    Example 3:
//
//    Input: s = "QQQW"
//    Output: 2
//    Explanation: We can replace the first "QQ" to "ER".
//
//
//    Constraints:
//
//    n == s.length
//    4 <= n <= 105
//    n is a multiple of 4.
//    s contains only 'Q', 'W', 'E', and 'R'.