package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class SequentialDigits {
    class Solution {
        public List<Integer> sequentialDigits(int low, int high) {
            List<Integer> results = new ArrayList<>();
            if(low < 10 || high > 1_000_000_000 || low > high) {
                return results;
            }

            int startLength = getLength(low);
            int endLength = getLength(high);

            for(int length = startLength; length  <= endLength; length++) {
                for(int a = 1; a <= (9 - length + 1); a++) {
                    int sequentialNumber = getSequentialNumber(length, a);
                    if(sequentialNumber > high) {
                        break;
                    }

                    if(sequentialNumber >= low) {
                        results.add(sequentialNumber);
                    }
                }
            }

            return results;
        }

        private int getLength(int number) {
            int length = 0;

            while(number > 0) {
                length++;
                number /= 10;
            }

            return length;
        }

        private int getSequentialNumber(int length, int a) {
            int number = 0;
            for(int i = 0; i < length; i++) {
                number *= 10;
                number += a;
                a++;
            }

            return number;
        }
    }

    class Solution_Correct_1 {
        public List<Integer> sequentialDigits(int low, int high) {
            List<Integer> answer = new ArrayList<>();
            if(low < 10 || high > 1_000_000_000 || low > high) {
                return answer;
            }

            int startLength = getLength(low);
            int endLength = getLength(high);

            for(int length = startLength; length <= endLength; length++) {
                for(int a = 1; a <= (9 - length + 1); a++) {
                    int number = getSequenceNumber(a, length);
                    if(number >= low && number <= high) {
                        answer.add(number);
                    }
                }
            }

            return answer;
        }

        private int getLength(int a) {
            int length = 0;
            while(a > 0) {
                length++;
                a /= 10;
            }

            return length;
        }

        private int getSequenceNumber(int a, int length) {
            int number = a;
            while(length > 1) {
                a++;
                number *= 10;
                number += a;
                length--;
            }

            return number;
        }
    }

// 100
// 300

// 1000
// 13000
}

//    1291. Sequential Digits
//    Medium
//    An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
//
//    Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
//
//
//
//    Example 1:
//
//    Input: low = 100, high = 300
//    Output: [123,234]
//    Example 2:
//
//    Input: low = 1000, high = 13000
//    Output: [1234,2345,3456,4567,5678,6789,12345]
//
//
//    Constraints:
//
//    10 <= low <= high <= 10^9