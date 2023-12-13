package org.jwolfe.quetzal.algorithms.lc;

public class IntegerToEnglishWords {
    class Solution {
        private String[] TillTwenty = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

        private String[] TwentyTillHundred = new String[] {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

        private String[] Thousands = new String[] {"", "Thousand", "Million", "Billion"};

        public String numberToWords(int num) {
            if(num < 0) {
                return "";
            }

            if(num == 0) {
                return "Zero";
            }

            StringBuilder builder = new StringBuilder();
            int i = 0;

            while(num > 0) {
                int val = num % 1000;
                if(val != 0) {
                    String fragment = getFragment(val) + Thousands[i] + " ";
                    builder.insert(0, fragment);
                }

                num /= 1000;
                i++;
            }

            return builder.toString().trim();
        }

        private String getFragment(int num) {
            if(num == 0) {
                return "";
            }
            else if(num < 20) {
                return TillTwenty[num] + " ";
            } else if(num < 100) {
                return TwentyTillHundred[num / 10] + " " + getFragment(num % 10);
            } else {
                return TillTwenty[num / 100] + " Hundred " + getFragment(num % 100);
            }
        }
    }

// 123
// 12345
// 1000000
}

//    273. Integer to English Words
//    Hard
//    Convert a non-negative integer num to its English words representation.
//
//
//
//    Example 1:
//
//    Input: num = 123
//    Output: "One Hundred Twenty Three"
//    Example 2:
//
//    Input: num = 12345
//    Output: "Twelve Thousand Three Hundred Forty Five"
//    Example 3:
//
//    Input: num = 1234567
//    Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
//
//
//    Constraints:
//
//    0 <= num <= 231 - 1
