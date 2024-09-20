package org.jwolfe.quetzal.algorithms.lc;

public class ShortestPalindrome {
    class Solution {
        public String shortestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }

            int n = s.length();
            int left = 0;
            for (int right = n - 1; right >= 0; right--) {
                char lc = s.charAt(left);
                char rc = s.charAt(right);

                if (lc == rc) {
                    left++;
                }
            }

            if (left == n) {
                return s;
            }

            String nonPalindrome = s.substring(left);
            String candidate = s.substring(0, left);
            String reverseNonPalindrome = reverse(nonPalindrome);

            String result = reverseNonPalindrome + shortestPalindrome(candidate) + nonPalindrome;
            return result;
        }

        private String reverse(String s) {
            StringBuilder builder = new StringBuilder();
            for (int i = s.length() - 1; i >= 0; i--) {
                builder.append(s.charAt(i));
            }

            return builder.toString();
        }
    }

    class Solution_Correct_2 {
        public String shortestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }

            int n = s.length();
            String r = reverse(s);
            for (int i = 0; i < n; i++) {
                String sf = s.substring(0, n - i);
                String rf = r.substring(i);

                if (sf.equals(rf)) {
                    return r.substring(0, i) + s;
                }
            }

            return "";
        }

        private String reverse(String s) {
            StringBuilder builder = new StringBuilder();
            for (int i = s.length() - 1; i >= 0; i--) {
                builder.append(s.charAt(i));
            }

            return builder.toString();
        }
    }

    class Solution_Correct_LPS_1 {
        public String shortestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }

            int n = s.length();
            String r = reverse(s);

            String str = s + "#" + r;
            int[] lps = getLps(str);

            int i = lps[str.length() - 1];
            String t = s.substring(i);
            String rt = reverse(t);

            return rt + s;
        }

        private int[] getLps(String s) {
            int[] lps = new int[s.length()];

            int i = 0;
            int j = 1;

            while (j < s.length()) {
                char a = s.charAt(i);
                char b = s.charAt(j);

                if (a == b) {
                    i++;
                    lps[j] = i;
                    j++;
                } else {
                    if (i == 0) {
                        j++;
                    } else {
                        i = lps[i - 1];
                    }
                }
            }

            return lps;
        }

        private String reverse(String s) {
            StringBuilder builder = new StringBuilder(s);
            builder.reverse();

            return builder.toString();
        }
    }

    class Solution_Incorrect {
        public String shortestPalindrome(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }

            String r = reverse(s);
            int n = s.length();

            int i = 0;
            for (; i < n; i++) {
                int j = n - i - 1;

                String str1 = s.substring(0, n - i);
                String str2 = r.substring(i);

                if (str1.equals(str2)) {
                    break;
                }
            }

            if (i == n) {
                return s;
            }

            return r.substring(0, i) + s;
        }

        private String reverse(String s) {
            StringBuilder builder = new StringBuilder();
            for (int i = s.length() - 1; i >= 0; i--) {
                builder.append(s.charAt(i));
            }

            return builder.toString();
        }
    }

    class Solution_TLE {
        public String shortestPalindrome(String s) {
            if (s == null || s.length() < 2 || isPalindrome(s)) {
                return s;
            }

            int n = s.length();
            for (int i = n - 1; i > 0; i++) {
                char c = s.charAt(i);

                StringBuilder builder = new StringBuilder();
                builder.append(c);
                builder.append(s);

                String str = builder.toString();
                if (isPalindrome(str)) {
                    return str;
                }
            }

            return s + s;
        }

        private boolean isPalindrome(String s) {
            int i = 0;
            int j = s.length() - 1;

            while (i < j) {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
            }

            return true;
        }
    }

// "aacecaaa"
// "abcd"
}

//    214. Shortest Palindrome
//    Hard
//    You are given a string s. You can convert s to a palindrome by adding characters in front of it.
//
//    Return the shortest palindrome you can find by performing this transformation.
//
//
//
//    Example 1:
//
//    Input: s = "aacecaaa"
//    Output: "aaacecaaa"
//    Example 2:
//
//    Input: s = "abcd"
//    Output: "dcbabcd"
//
//
//    Constraints:
//
//    0 <= s.length <= 5 * 104
//    s consists of lowercase English letters only.