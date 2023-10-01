package org.jwolfe.quetzal.algorithms.lc;

public class ReverseWordsInAStringIII {
    class Solution {
        public String reverseWords(String s) {
            if(s == null || s.length() < 2) {
                return s;
            }

            int n = s.length();
            int left = 0;
            int right = 0;

            StringBuilder sb = new StringBuilder(s);
            for(; right < n; right++) {
                char c = s.charAt(right);

                if(c == ' ') {
                    reverse(sb, left, right - 1);
                    left = right + 1;
                }
            }

            if(left < right) {
                reverse(sb, left, right - 1);
            }

            return sb.toString();
        }

        private void reverse(StringBuilder sb, int left, int right) {
            while(left < right) {
                swap(sb, left++, right--);
            }
        }

        private void swap(StringBuilder sb, int i, int j) {
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
        }
    }

    class Solution_Correct_2 {
        public String reverseWords(String s) {
            if(s == null || s.length() == 0) {
                return s;
            }

            StringBuilder builder = new StringBuilder(s);
            int left = 0;
            int right = 0;
            for(; right < s.length(); right++) {
                char c = s.charAt(right);
                if(c == ' ') {
                    reverse(builder, left, right - 1);

                    left = right + 1;
                }
            }

            if(left < right) {
                reverse(builder, left, right - 1);
            }

            return builder.toString();
        }

        private void reverse(StringBuilder builder, int left, int right) {
            while(left < right) {
                char temp = builder.charAt(left);
                builder.setCharAt(left, builder.charAt(right));
                builder.setCharAt(right, temp);

                left++;
                right--;
            }
        }
    }

    class Solution_Correct_1 {
        public String reverseWords(String s) {
            if(s == null || s.length() == 0) {
                return s;
            }

            String[] words = s.split(" ");
            StringBuilder builder = new StringBuilder();
            for(String w : words) {
                builder.append(new StringBuilder(w).reverse().toString());
                builder.append(" ");
            }

            builder.deleteCharAt(builder.length() - 1);
            return builder.toString();
        }
    }
}

//    557. Reverse Words in a String III
//    Easy
//    Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
//
//
//
//    Example 1:
//
//    Input: s = "Let's take LeetCode contest"
//    Output: "s'teL ekat edoCteeL tsetnoc"
//    Example 2:
//
//    Input: s = "God Ding"
//    Output: "doG gniD"
//
//
//    Constraints:
//
//    1 <= s.length <= 5 * 104
//    s contains printable ASCII characters.
//    s does not contain any leading or trailing spaces.
//    There is at least one word in s.
//    All the words in s are separated by a single space.