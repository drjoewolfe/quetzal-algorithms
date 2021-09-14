package org.jwolfe.quetzal.algorithms.lc;

public class ReverseOnlyLetters {
    class Solution {
        public String reverseOnlyLetters(String S) {
            if(S == null || S.length() == 0) {
                return S;
            }

            StringBuilder sb = new StringBuilder(S);
            int n = S.length();
            int left = 0;
            int right = S.length() - 1;
            while(left < right) {
                while(left < right && !Character.isLetter(sb.charAt(left))) {
                    left++;
                }

                while(left < right && !Character.isLetter(sb.charAt(right))) {
                    right--;
                }

                swap(sb, left++, right--);
            }

            return sb.toString();
        }

        private void swap(StringBuilder sb, int x, int y) {
            char c = sb.charAt(x);
            sb.setCharAt(x, sb.charAt(y));
            sb.setCharAt(y, c);
        }
    }

    class Solution_Correct_2 {
        public String reverseOnlyLetters(String s) {
            if(s == null || s.length() == 0) {
                return s;
            }

            StringBuilder builder = new StringBuilder(s);
            int left = 0;
            int right = s.length() - 1;

            while(left < right) {
                while(left < right
                        && !Character.isLetter(s.charAt(left))) {
                    left++;
                }

                while(left < right
                        && !Character.isLetter(s.charAt(right))) {
                    right--;
                }

                char temp = s.charAt(left);
                builder.setCharAt(left, s.charAt(right));
                builder.setCharAt(right, temp);

                left++;
                right--;
            }

            return builder.toString();
        }
    }
}

//    917. Reverse Only Letters
//    Easy
//    Given a string s, reverse the string according to the following rules:
//
//    All the characters that are not English letters remain in the same position.
//    All the English letters (lowercase or uppercase) should be reversed.
//    Return s after reversing it.
//
//
//
//    Example 1:
//
//    Input: s = "ab-cd"
//    Output: "dc-ba"
//    Example 2:
//
//    Input: s = "a-bC-dEf-ghIj"
//    Output: "j-Ih-gfE-dCba"
//    Example 3:
//
//    Input: s = "Test1ng-Leet=code-Q!"
//    Output: "Qedo1ct-eeLg=ntse-T!"
//
//
//    Constraints:
//
//    1 <= s.length <= 100
//    s consists of characters with ASCII values in the range [33, 122].
//    s does not contain '\"' or '\\'.
