package org.jwolfe.quetzal.algorithms.lc;

public class LargestOddNumberInString {
    class Solution {
        public String largestOddNumber(String num) {
            if(num == null || num.length() == 0) {
                return "";
            }

            int n = num.length();
            for(int i = n - 1; i >= 0; i--) {
                int val = num.charAt(i) - '0';
                if(val % 2 == 1) {
                    // Odd
                    return num.substring(0, i + 1);
                }
            }

            return "";
        }
    }

    class Solution_Correct_1 {
        public String largestOddNumber(String num) {
            if(num == null || num.length() == 0) {
                return "";
            }

            int i = num.length() - 1;
            for(; i >= 0; i--) {
                int val = num.charAt(i) - '0';
                if(val % 2 != 0) {
                    // odd digit
                    break;
                }
            }

            if(i >= 0) {
                return num.substring(0, i + 1);
            }

            return "";
        }
    }
}

//    1903. Largest Odd Number in String
//    Easy
//    You are given a string num, representing a large integer. Return the largest-valued odd integer (as a string) that is a non-empty substring of num, or an empty string "" if no odd integer exists.
//
//    A substring is a contiguous sequence of characters within a string.
//
//
//
//    Example 1:
//
//    Input: num = "52"
//    Output: "5"
//    Explanation: The only non-empty substrings are "5", "2", and "52". "5" is the only odd number.
//    Example 2:
//
//    Input: num = "4206"
//    Output: ""
//    Explanation: There are no odd numbers in "4206".
//    Example 3:
//
//    Input: num = "35427"
//    Output: "35427"
//    Explanation: "35427" is already an odd number.
//
//
//    Constraints:
//
//    1 <= num.length <= 105
//    num only consists of digits and does not contain any leading zeros.
