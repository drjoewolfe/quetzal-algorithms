package org.jwolfe.quetzal.algorithms.lc;

public class DeleteCharactersToMakeFancyString {
    class Solution {
        public String makeFancyString(String s) {
            if (s == null || s.length() < 3) {
                return s;
            }

            StringBuilder builder = new StringBuilder();
            builder.append(s.charAt(0));
            builder.append(s.charAt(1));

            for (int i = 2; i < s.length(); i++) {
                char c = s.charAt(i);
                char pc1 = builder.charAt(builder.length() - 1);
                char pc2 = builder.charAt(builder.length() - 2);

                if (c != pc1 || c != pc2) {
                    builder.append(c);
                }
            }

            return builder.toString();
        }
    }

    class Solution_Correct_1 {
        public String makeFancyString(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }

            StringBuilder builder = new StringBuilder();
            int i = 0;
            int n = s.length();
            while (i < n) {
                char c = s.charAt(i);

                int j = i + 1;
                while (j < n && c == s.charAt(j)) {
                    j++;
                }

                builder.append(c);
                if (j - i >= 2) {
                    builder.append(c);
                }

                i = j;
            }

            return builder.toString();
        }
    }

// "leeetcode"
// "aab"
}

//    1957. Delete Characters to Make Fancy String
//    Easy
//    A fancy string is a string where no three consecutive characters are equal.
//
//    Given a string s, delete the minimum possible number of characters from s to make it fancy.
//
//    Return the final string after the deletion. It can be shown that the answer will always be unique.
//
//
//
//    Example 1:
//
//    Input: s = "leeetcode"
//    Output: "leetcode"
//    Explanation:
//    Remove an 'e' from the first group of 'e's to create "leetcode".
//    No three consecutive characters are equal, so return "leetcode".
//    Example 2:
//
//    Input: s = "aaabaaaa"
//    Output: "aabaa"
//    Explanation:
//    Remove an 'a' from the first group of 'a's to create "aabaaaa".
//    Remove two 'a's from the second group of 'a's to create "aabaa".
//    No three consecutive characters are equal, so return "aabaa".
//    Example 3:
//
//    Input: s = "aab"
//    Output: "aab"
//    Explanation: No three consecutive characters are equal, so return "aab".
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s consists only of lowercase English letters.
