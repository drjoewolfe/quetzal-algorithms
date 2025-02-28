package org.jwolfe.quetzal.algorithms.lc;

public class ShortestCommonSupersequence {
    class Solution {
        public String shortestCommonSupersequence(String str1, String str2) {
            if ((str1 == null && str2 == null) || (str1.length() == 0 && str2.length() == 0)) {
                return "";
            }

            int n1 = str1.length();
            int n2 = str2.length();

            String[] prev = new String[n2 + 1];
            for (int j = 0; j <= n2; j++) {
                prev[j] = str2.substring(0, j);
            }

            for (int i = 1; i <= n1; i++) {
                String[] curr = new String[n2 + 1];
                curr[0] = str1.substring(0, i);

                char c1 = str1.charAt(i - 1);
                for (int j = 1; j <= n2; j++) {
                    char c2 = str2.charAt(j - 1);

                    if (c1 == c2) {
                        curr[j] = prev[j - 1] + c1;
                    } else {
                        String o1 = prev[j];
                        String o2 = curr[j - 1];

                        curr[j] = (o1.length() < o2.length())
                                ? o1 + c1
                                : o2 + c2;
                    }
                }

                prev = curr;
            }

            return prev[n2];
        }

        private void print(String[] arr) {
            for (String str : arr) {
                System.out.print(str + " ");
            }

            System.out.println();
        }
    }
}

//    1092. Shortest Common Supersequence
//    Hard
//    Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.
//
//    A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.
//
//
//
//    Example 1:
//
//    Input: str1 = "abac", str2 = "cab"
//    Output: "cabac"
//    Explanation:
//    str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
//    str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
//    The answer provided is the shortest such string that satisfies these properties.
//    Example 2:
//
//    Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
//    Output: "aaaaaaaa"
//
//
//    Constraints:
//
//    1 <= str1.length, str2.length <= 1000
//    str1 and str2 consist of lowercase English letters.