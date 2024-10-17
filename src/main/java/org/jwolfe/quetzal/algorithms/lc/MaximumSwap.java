package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class MaximumSwap {
    class Solution {
        public int maximumSwap(int num) {
            if (num < 10) {
                return num;
            }

            char[] arr = Integer.toString(num).toCharArray();
            int n = arr.length;

            int maxDigitIndex = -1;
            int swapIndex1 = -1;
            int swapIndex2 = -1;

            for (int i = n - 1; i >= 0; i--) {
                if (maxDigitIndex == -1 || arr[i] > arr[maxDigitIndex]) {
                    maxDigitIndex = i;
                } else if (arr[i] < arr[maxDigitIndex]) {
                    swapIndex1 = i;
                    swapIndex2 = maxDigitIndex;
                }
            }

            if (swapIndex1 != -1 && swapIndex2 != -1) {
                char temp = arr[swapIndex1];
                arr[swapIndex1] = arr[swapIndex2];
                arr[swapIndex2] = temp;
            }

            return Integer.parseInt(new String(arr));
        }
    }

    class Solution_Correct_1 {
        public int maximumSwap(int num) {
            if (num < 10) {
                return num;
            }

            List<Integer> list = new ArrayList<>();
            while (num > 0) {
                int digit = num % 10;
                list.add(digit);
                num /= 10;
            }

            for (int i = list.size() - 1; i > 0; i--) {
                int a = list.get(i);
                int t = a;
                int ti = i;
                for (int j = i - 1; j >= 0; j--) {
                    int b = list.get(j);
                    if (a < b && t <= b) {
                        t = b;
                        ti = j;
                    }
                }

                if (ti != i) {
                    list.set(ti, a);
                    list.set(i, t);
                    break;
                }
            }

            for (int i = list.size() - 1; i >= 0; i--) {
                num *= 10;
                num += list.get(i);
            }

            return num;
        }
    }

// 2736
// 98368
// 1993
}

//    670. Maximum Swap
//    Medium
//    You are given an integer num. You can swap two digits at most once to get the maximum valued number.
//
//    Return the maximum valued number you can get.
//
//
//
//    Example 1:
//
//    Input: num = 2736
//    Output: 7236
//    Explanation: Swap the number 2 and the number 7.
//    Example 2:
//
//    Input: num = 9973
//    Output: 9973
//    Explanation: No swap.
//
//
//    Constraints:
//
//    0 <= num <= 108