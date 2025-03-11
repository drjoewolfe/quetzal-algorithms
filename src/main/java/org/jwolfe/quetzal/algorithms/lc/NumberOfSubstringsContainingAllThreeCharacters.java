package org.jwolfe.quetzal.algorithms.lc;

public class NumberOfSubstringsContainingAllThreeCharacters {
    class Solution {
        public int numberOfSubstrings(String s) {
            if (s == null || s.length() < 3) {
                return 0;
            }

            int count = 0;

            int ac = 0;
            int bc = 0;
            int cc = 0;

            int n = s.length();
            int left = 0;
            for (int right = 0; right < n; right++) {
                char rc = s.charAt(right);
                if (rc == 'a') {
                    ac++;
                } else if (rc == 'b') {
                    bc++;
                } else {
                    cc++;
                }

                while (left <= right
                        && ac > 0 && bc > 0 && cc > 0) {
                    count += (n - right);
                    int lc = s.charAt(left);
                    if (lc == 'a') {
                        ac--;
                    } else if (lc == 'b') {
                        bc--;
                    } else {
                        cc--;
                    }

                    left++;
                }
            }

            return count;
        }
    }

    class Solution_Correct_1 {
        public int numberOfSubstrings(String s) {
            if (s == null || s.length() < 3) {
                return 0;
            }

            int n = s.length();
            int count = 0;

            int left = 0;
            int right = 0;

            int ac = 0;
            int bc = 0;
            int cc = 0;

            while (right < n) {
                char c = s.charAt(right);
                if (c == 'a') {
                    ac++;
                } else if (c == 'b') {
                    bc++;
                } else if (c == 'c') {
                    cc++;
                }

                if (ac > 0 && bc > 0 && cc > 0) {
                    count += (n - right);

                    while (left < right) {
                        c = s.charAt(left);

                        if (c == 'a') {
                            ac--;
                        } else if (c == 'b') {
                            bc--;
                        } else if (c == 'c') {
                            cc--;
                        }

                        left++;

                        if (ac == 0 || bc == 0 || cc == 0) {
                            break;
                        }

                        count += (n - right);
                    }

                }

                right++;
            }

            return count;
        }
    }

    class Solution_N2 {
        public int numberOfSubstrings(String s) {
            if (s == null || s.length() < 3) {
                return 0;
            }

            int n = s.length();
            int count = 0;
            for (int i = 0; i < n - 2; i++) {
                int ac = 0;
                int bc = 0;
                int cc = 0;

                int j = i;
                for (; j < n; j++) {
                    char c = s.charAt(j);
                    if (c == 'a') {
                        ac++;
                    } else if (c == 'b') {
                        bc++;
                    } else if (c == 'c') {
                        cc++;
                    }

                    if (ac > 0 && bc > 0 && cc > 0) {
                        break;
                    }
                }

                if (ac > 0 && bc > 0 && cc > 0) {
                    count += (n - j);
                }
            }

            return count;
        }
    }

// "abcabc"
// "aaacb"
// "acbbcac"
}

//    1358. Number of Substrings Containing All Three Characters
//    Medium
//    Given a string s consisting only of characters a, b and c.
//
//    Return the number of substrings containing at least one occurrence of all these characters a, b and c.
//
//
//
//    Example 1:
//
//    Input: s = "abcabc"
//    Output: 10
//    Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
//    Example 2:
//
//    Input: s = "aaacb"
//    Output: 3
//    Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
//    Example 3:
//
//    Input: s = "abc"
//    Output: 1
//
//
//    Constraints:
//
//    3 <= s.length <= 5 x 10^4
//    s only consists of a, b or c characters.