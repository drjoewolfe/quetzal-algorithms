package org.jwolfe.quetzal.algorithms.lc;

public class ReverseVowelsOfAString {
    class Solution {
        public String reverseVowels(String s) {
            if(s == null || s.length() < 2) {
                return s;
            }

            int left = 0;
            int right = s.length() - 1;

            StringBuilder builder = new StringBuilder(s);
            while(left < right) {
                while(left < right && !isVowel(s, left)) {
                    left++;
                }

                while(left < right && !isVowel(s, right)) {
                    right--;
                }

                swap(builder, left++, right--);
            }

            return builder.toString();
        }

        private void swap(StringBuilder builder, int left, int right) {
            char temp = builder.charAt(left);
            builder.setCharAt(left, builder.charAt(right));
            builder.setCharAt(right, temp);
        }

        private boolean isVowel(String s, int index) {
            char c = s.charAt(index);

            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                    || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                return true;
            }

            return false;
        }
    }

    class Solution_Correct_1 {
        public String reverseVowels(String s) {
            if(s == null || s.length() < 2) {
                return s;
            }

            int left = 0;
            int right = s.length() - 1;
            StringBuilder builder = new StringBuilder(s);

            while(left < right) {
                while(left < right
                        && !isVowel(s.charAt(left))) {
                    left++;
                }

                while(left < right
                        && !isVowel(s.charAt(right))) {
                    right--;
                }

                swap(builder, left++, right--);
            }

            return builder.toString();
        }

        private void swap(StringBuilder builder, int i, int j) {
            char c = builder.charAt(i);
            builder.setCharAt(i, builder.charAt(j));
            builder.setCharAt(j, c);
        }

        private boolean isVowel(char c) {
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                    || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                return true;
            }

            return false;
        }
    }
}

//    345. Reverse Vowels of a String
//    Easy
//    Given a string s, reverse only all the vowels in the string and return it.
//
//    The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
//
//
//
//    Example 1:
//
//    Input: s = "hello"
//    Output: "holle"
//    Example 2:
//
//    Input: s = "leetcode"
//    Output: "leotcede"
//
//
//    Constraints:
//
//    1 <= s.length <= 3 * 105
//    s consist of printable ASCII characters.