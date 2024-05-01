package org.jwolfe.quetzal.algorithms.lc;

public class ReversePrefixOfWord {
    class Solution {
        public String reversePrefix(String word, char ch) {
            if (word == null || word.length() < 2) {
                return word;
            }

            int n = word.length();
            int index = -1;

            for (int i = 0; i < n; i++) {
                char c = word.charAt(i);

                if (c == ch) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                return word;
            }

            char[] letters = word.toCharArray();
            reverse(letters, 0, index);

            return new String(letters);
        }

        private void reverse(char[] letters, int left, int right) {
            while (left < right) {
                char temp = letters[left];
                letters[left] = letters[right];
                letters[right] = temp;

                left++;
                right--;
            }
        }
    }

    class Solution_Correct_1 {
        public String reversePrefix(String word, char ch) {
            if (word == null || word.length() == 0) {
                return word;
            }

            int index = -1;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == ch) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                return word;
            }

            StringBuilder builder = new StringBuilder();
            for (int i = index; i >= 0; i--) {
                builder.append(word.charAt(i));
            }

            if (index < word.length()) {
                builder.append(word.substring(index + 1));
            }

            return builder.toString();
        }
    }
}

//    2000. Reverse Prefix of Word
//    Easy
//    Given a 0-indexed string word and a character ch, reverse the segment of word that starts at index 0 and ends at the index of the first occurrence of ch (inclusive). If the character ch does not exist in word, do nothing.
//
//    For example, if word = "abcdefd" and ch = "d", then you should reverse the segment that starts at 0 and ends at 3 (inclusive). The resulting string will be "dcbaefd".
//    Return the resulting string.
//
//
//
//    Example 1:
//
//    Input: word = "abcdefd", ch = "d"
//    Output: "dcbaefd"
//    Explanation: The first occurrence of "d" is at index 3.
//    Reverse the part of word from 0 to 3 (inclusive), the resulting string is "dcbaefd".
//    Example 2:
//
//    Input: word = "xyxzxe", ch = "z"
//    Output: "zxyxxe"
//    Explanation: The first and only occurrence of "z" is at index 3.
//    Reverse the part of word from 0 to 3 (inclusive), the resulting string is "zxyxxe".
//    Example 3:
//
//    Input: word = "abcd", ch = "z"
//    Output: "abcd"
//    Explanation: "z" does not exist in word.
//    You should not do any reverse operation, the resulting string is "abcd".
//
//
//    Constraints:
//
//    1 <= word.length <= 250
//    word consists of lowercase English letters.
//    ch is a lowercase English letter.