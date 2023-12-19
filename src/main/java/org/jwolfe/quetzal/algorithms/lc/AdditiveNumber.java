package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class AdditiveNumber {
    class Solution {
        public boolean isAdditiveNumber(String num) {
            if(num == null || num.length() < 3) {
                return false;
            }

            int n = num.length();
            for(int i = 0; i < Math.min(n - 2, 19); i++) {
                long a = getNumber(num, 0, i);
                if(i > 0 && num.charAt(0) == '0') {
                    return false;
                }

                for(int j = i + 1; j < Math.min(n - 1, i + 18); j++) {
                    if(j > i + 1 && num.charAt(i + 1) == '0') {
                        break;
                    }

                    long b = getNumber(num, i + 1, j);

                    if(isAdditiveNumber(num, j + 1, a, b)) {
                        return true;
                    }
                }
            }

            return false;
        }

        private boolean isAdditiveNumber(String num, int index, long a, long b) {
            if(index == num.length()) {
                return true;
            }

            for(int i = index; i < Math.min(num.length(), index + 18); i++) {
                if( i > index && num.charAt(index) == '0') {
                    return false;
                }

                long c = getNumber(num, index, i);

                if(c == a + b) {
                    return isAdditiveNumber(num, i + 1, b, c);
                }
            }

            return false;
        }

        private long getNumber(String n, int start, int end) {
            String str = n.substring(start, end + 1);
            return Long.valueOf(str);
        }

    }

    class Solution_Incorrect {
        public boolean isAdditiveNumber(String num) {
            if(num == null || num.length() < 3) {
                return false;
            }

            int maxDigits = num.length() / 3 + 1;
            return isAdditiveNumber(num, maxDigits, 0, new ArrayList<>());
        }

        private boolean isAdditiveNumber(String num, int maxDigits, int index, List<Long> numbers) {
            if(index >= num.length()) {
                if(numbers.size() > 2) {
                    return true;
                }

                return false;
            }

            //  System.out.println("***");
            // System.out.println("Index: " + index);
            print(numbers);
            // System.out.println("---");

            for(int i = 1; i <= maxDigits; i++) {
                int j = index + i;
                if(j > num.length()) {
                    return false;
                }

                String s = num.substring(index, j);
                if(s.length() > 1 && s.charAt(0) == '0') {
                    return false;
                }

                long v = getNumber(s);
                // System.out.println(index + ", " + j + ", " + s + ", " + v);

                numbers.add(v);
                int n = numbers.size();

                if(n > 2) {
                    if(v == numbers.get(n - 2) + numbers.get(n - 3)) {

                        if(isAdditiveNumber(num, maxDigits, j, numbers)) {
                            return true;
                        }


                    }
                } else {
                    if(isAdditiveNumber(num, maxDigits, j, numbers)) {
                        return true;
                    }
                }

                numbers.remove(n - 1);
            }

            // System.out.println("~~~");
            return false;
        }

        private long getNumber(String s) {
            long v = 0;
            for(int i = 0; i < s.length(); i++) {
                int d = s.charAt(i) - '0';

                v *= 10;
                v += d;
            }

            return v;
        }

        private void print(List<Long> numbers) {
            for(long a : numbers) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }


// "112358"
// "111"
// "1023"
// "101"
// "199100199"
// "000"
// "198019823962"
// "11111111111011111111111"
// "1999999999999999910000000000000000"
}

//    306. Additive Number
//    Medium
//    An additive number is a string whose digits can form an additive sequence.
//
//    A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
//
//    Given a string containing only digits, return true if it is an additive number or false otherwise.
//
//    Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
//
//
//
//    Example 1:
//
//    Input: "112358"
//    Output: true
//    Explanation:
//    The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
//    1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
//    Example 2:
//
//    Input: "199100199"
//    Output: true
//    Explanation:
//    The additive sequence is: 1, 99, 100, 199.
//    1 + 99 = 100, 99 + 100 = 199
//
//
//    Constraints:
//
//    1 <= num.length <= 35
//    num consists only of digits.
//
//
//    Follow up: How would you handle overflow for very large input integers?
