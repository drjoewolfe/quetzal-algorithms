package org.jwolfe.quetzal.algorithms.lc;

public class CheckIfStringsCanBeMadeEqualWithOperationsII {
    class Solution {
        public boolean checkStrings(String s1, String s2) {
            if (s1 == null && s2 == null) {
                return true;
            }

            if (s1.length() == 0 && s2.length() == 0) {
                return true;
            }

            if (s1 == null || s2 == null || s1.length() != s2.length()) {
                return false;
            }

            int[] evenFrequencies = new int[26];
            int[] oddFrequencies = new int[26];

            for (int i = 0; i < s1.length(); i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(i);

                if (i % 2 == 0) {
                    evenFrequencies[c1 - 'a']++;
                    evenFrequencies[c2 - 'a']--;
                } else {
                    oddFrequencies[c1 - 'a']++;
                    oddFrequencies[c2 - 'a']--;
                }
            }

            for (int i = 0; i < 26; i++) {
                if (evenFrequencies[i] != 0 || oddFrequencies[i] != 0) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    2840. Check if Strings Can be Made Equal With Operations II
//    Medium
//    You are given two strings s1 and s2, both of length n, consisting of lowercase English letters.
//
//    You can apply the following operation on any of the two strings any number of times:
//
//    Choose any two indices i and j such that i < j and the difference j - i is even, then swap the two characters at those indices in the string.
//    Return true if you can make the strings s1 and s2 equal, and false otherwise.
//
//
//
//    Example 1:
//
//    Input: s1 = "abcdba", s2 = "cabdab"
//    Output: true
//    Explanation: We can apply the following operations on s1:
//    - Choose the indices i = 0, j = 2. The resulting string is s1 = "cbadba".
//    - Choose the indices i = 2, j = 4. The resulting string is s1 = "cbbdaa".
//    - Choose the indices i = 1, j = 5. The resulting string is s1 = "cabdab" = s2.
//    Example 2:
//
//    Input: s1 = "abe", s2 = "bea"
//    Output: false
//    Explanation: It is not possible to make the two strings equal.
//
//
//    Constraints:
//
//    n == s1.length == s2.length
//    1 <= n <= 105
//    s1 and s2 consist only of lowercase English letters.