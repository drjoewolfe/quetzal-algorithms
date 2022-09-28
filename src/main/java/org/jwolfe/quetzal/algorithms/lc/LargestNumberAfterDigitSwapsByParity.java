package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class LargestNumberAfterDigitSwapsByParity {
    class Solution {
        public int largestInteger(int num) {
            if(num <= 0) {
                return num;
            }

            PriorityQueue<Integer> evenHeap = new PriorityQueue<>();
            PriorityQueue<Integer> oddHeap = new PriorityQueue<>();

            int val = num;
            while(val > 0) {
                int digit = val % 10;
                if(digit % 2 == 0) {
                    evenHeap.offer(digit);
                } else {
                    oddHeap.offer(digit);
                }

                val /= 10;
            }

            int result = 0;
            int place = 1;
            val = num;
            while(val > 0) {
                int digit = val % 10;
                if(digit % 2 == 0) {
                    result += (evenHeap.poll() * place);
                } else {
                    result += (oddHeap.poll() * place);
                }

                place *= 10;
                val /= 10;
            }

            return result;
        }
    }

    class Solution_Correct_1 {
        public int largestInteger(int num) {
            if(num <= 0) {
                return num;
            }

            String s = Integer.toString(num);

            List<Integer> oddIndexes = new ArrayList<>();
            List<Integer> evenIndexes = new ArrayList<>();

            List<Integer> oddValues = new ArrayList<>();
            List<Integer> evenValues = new ArrayList<>();

            for(int i = 0; i < s.length(); i++) {
                int val = s.charAt(i) - '0';

                if(val % 2 == 0) {
                    evenIndexes.add(i);
                    evenValues.add(val);
                } else {
                    oddIndexes.add(i);
                    oddValues.add(val);
                }
            }

            Collections.sort(oddValues, Collections.reverseOrder());
            Collections.sort(evenValues, Collections.reverseOrder());

            char[] arr = new char[s.length()];
            for(int i = 0; i < oddIndexes.size(); i++) {
                arr[oddIndexes.get(i)] = (char) (oddValues.get(i) + '0');
            }

            for(int i = 0; i < evenIndexes.size(); i++) {
                arr[evenIndexes.get(i)] = (char) (evenValues.get(i) + '0');
            }

            return Integer.valueOf(String.valueOf(arr));
        }
    }
}

//    2231. Largest Number After Digit Swaps by Parity
//    Easy
//    You are given a positive integer num. You may swap any two digits of num that have the same parity (i.e. both odd digits or both even digits).
//
//    Return the largest possible value of num after any number of swaps.
//
//
//
//    Example 1:
//
//    Input: num = 1234
//    Output: 3412
//    Explanation: Swap the digit 3 with the digit 1, this results in the number 3214.
//    Swap the digit 2 with the digit 4, this results in the number 3412.
//    Note that there may be other sequences of swaps but it can be shown that 3412 is the largest possible number.
//    Also note that we may not swap the digit 4 with the digit 1 since they are of different parities.
//    Example 2:
//
//    Input: num = 65875
//    Output: 87655
//    Explanation: Swap the digit 8 with the digit 6, this results in the number 85675.
//    Swap the first digit 5 with the digit 7, this results in the number 87655.
//    Note that there may be other sequences of swaps but it can be shown that 87655 is the largest possible number.
//
//
//    Constraints:
//
//    1 <= num <= 109