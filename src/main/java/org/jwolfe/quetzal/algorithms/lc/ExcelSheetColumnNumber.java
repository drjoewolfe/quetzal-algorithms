package org.jwolfe.quetzal.algorithms.lc;

public class ExcelSheetColumnNumber {
    class Solution {
        public int titleToNumber(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int exponent = 0;
            int columnNumber = 0;
            int n = s.length();
            for(int i = n - 1; i >= 0; i--) {
                char c = s.charAt(i);
                int d = (c - 'A') + 1;

                columnNumber += Math.pow(26, exponent) * d;
                exponent++;
            }

            return columnNumber;
        }
    }

    class Solution_Correct_1 {
        public int titleToNumber(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int length = s.length();
            int number = 0;
            for(int i = 0; i < length; i++) {
                char c = s.charAt(i);
                int n = c - 'A';

                if(n < 0 || n > 25) {
                    return 0;
                }

                number *= 26;
                number += (n + 1);
            }

            return number;
        }
    }

    class Solution_Classic {
        public int titleToNumber(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int length = s.length();
            int number = 0;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int n = c - 'A';

                if(n < 0 || n > 25) {
                    return 0;
                }

                number += Math.pow(26, --length) * (n + 1);
            }

            return number;
        }
    }
}

//    171. Excel Sheet Column Number
//    Easy
//    Given a string columnTitle that represents the column title as appear in an Excel sheet, return its corresponding column number.
//
//    For example:
//
//    A -> 1
//    B -> 2
//    C -> 3
//    ...
//    Z -> 26
//    AA -> 27
//    AB -> 28
//    ...
//
//
//    Example 1:
//
//    Input: columnTitle = "A"
//    Output: 1
//    Example 2:
//
//    Input: columnTitle = "AB"
//    Output: 28
//    Example 3:
//
//    Input: columnTitle = "ZY"
//    Output: 701
//
//
//    Constraints:
//
//    1 <= columnTitle.length <= 7
//    columnTitle consists only of uppercase English letters.
//    columnTitle is in the range ["A", "FXSHRXW"].
