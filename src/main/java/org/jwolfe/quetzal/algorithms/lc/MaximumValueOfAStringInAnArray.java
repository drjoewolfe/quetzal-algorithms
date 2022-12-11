package org.jwolfe.quetzal.algorithms.lc;

public class MaximumValueOfAStringInAnArray {
    class Solution {
        public int maximumValue(String[] strs) {
            if(strs == null || strs.length == 0) {
                return 0;
            }

            int maxValue = 0;
            for(String str : strs) {
                if(isNumeric(str)) {
                    int val = getNumericValue(str);
                    maxValue = Math.max(maxValue, val);
                } else {
                    maxValue = Math.max(maxValue, str.length());
                }
            }

            return maxValue;
        }

        private boolean isNumeric(String str) {
            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if(c < '0' || c > '9') {
                    return false;
                }
            }

            return true;
        }

        private int getNumericValue(String str) {
            int val = 0;
            for(int i = 0; i < str.length(); i++) {
                val *= 10;
                val += (str.charAt(i) - '0');
            }

            return val;
        }
    }

// ["alic3","bob","3","4","00000"]
// ["1","01","001","0001"]
}

//    2496. Maximum Value of a String in an Array
//    Easy
//    The value of an alphanumeric string can be defined as:
//
//    The numeric representation of the string in base 10, if it comprises of digits only.
//    The length of the string, otherwise.
//    Given an array strs of alphanumeric strings, return the maximum value of any string in strs.
//
//
//
//    Example 1:
//
//    Input: strs = ["alic3","bob","3","4","00000"]
//    Output: 5
//    Explanation:
//    - "alic3" consists of both letters and digits, so its value is its length, i.e. 5.
//    - "bob" consists only of letters, so its value is also its length, i.e. 3.
//    - "3" consists only of digits, so its value is its numeric equivalent, i.e. 3.
//    - "4" also consists only of digits, so its value is 4.
//    - "00000" consists only of digits, so its value is 0.
//    Hence, the maximum value is 5, of "alic3".
//    Example 2:
//
//    Input: strs = ["1","01","001","0001"]
//    Output: 1
//    Explanation:
//    Each string in the array has value 1. Hence, we return 1.
//
//
//    Constraints:
//
//    1 <= strs.length <= 100
//    1 <= strs[i].length <= 9
//    strs[i] consists of only lowercase English letters and digits.