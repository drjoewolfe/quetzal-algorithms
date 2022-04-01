package org.jwolfe.quetzal.algorithms.lc;

public class ReverseString {
    class Solution {
        public void reverseString(char[] s) {
            if(s == null || s.length < 2) {
                return;
            }

            int n = s.length;

            int left = 0;
            int right = n - 1;

            while(left < right) {
                swap(s, left++, right--);
            }
        }

        private void swap(char[] s, int left, int right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
        }
    }

    class Solution_Correct {
        public void reverseString(char[] s) {
            if(s == null || s.length == 0) {
                return;
            }

            int left = 0;
            int right = s.length - 1;

            while(left < right) {
                swap(s, left++, right--);
            }
        }

        private void swap(char[] s, int left, int right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
        }
    }
}

//    344. Reverse String
//    Easy
//    Write a function that reverses a string. The input string is given as an array of characters s.
//
//    You must do this by modifying the input array in-place with O(1) extra memory.
//
//
//
//    Example 1:
//
//    Input: s = ["h","e","l","l","o"]
//    Output: ["o","l","l","e","h"]
//    Example 2:
//
//    Input: s = ["H","a","n","n","a","h"]
//    Output: ["h","a","n","n","a","H"]
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s[i] is a printable ascii character.
