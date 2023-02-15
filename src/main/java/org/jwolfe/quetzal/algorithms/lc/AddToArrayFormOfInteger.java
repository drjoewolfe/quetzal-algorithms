package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddToArrayFormOfInteger {
    class Solution {
        public List<Integer> addToArrayForm(int[] A, int K) {
            List<Integer> result = new ArrayList<>();

            int sum = 0;
            int carry = 0;

            int n = A.length;
            int index = n - 1;
            while(index >= 0 || K > 0) {
                sum = carry;
                if(index >= 0) {
                    sum += A[index];
                    index--;
                }

                if(K > 0) {
                    int digit = K % 10;
                    K /= 10;

                    sum += digit;
                }

                carry = sum / 10;
                sum %= 10;

                result.add(sum);
            }

            if(carry > 0) {
                result.add(carry);
            }

            Collections.reverse(result);
            return result;
        }
    }

    class Solution_Correct_1 {
        public List<Integer> addToArrayForm(int[] A, int K) {
            List<Integer> sumArray = new ArrayList<>();
            if(A == null || A.length == 0 || K < 0) {
                return sumArray;
            }

            int sum = 0;
            int carry = 0;

            int n = A.length;
            int i = n - 1;
            while(i >= 0 || K > 0) {
                sum += carry;
                if(i >= 0) {
                    int a = A[i];
                    sum += a;
                    i--;
                }

                if(K > 0) {
                    int b = K % 10;
                    sum += b;
                    K /= 10;
                }

                carry = sum / 10;
                sumArray.add(0, sum % 10);
                sum = 0;
            }

            if(carry > 0) {
                sumArray.add(0, carry);
            }

            return sumArray;
        }
    }
}

//    989. Add to Array-Form of Integer
//    Easy
//    The array-form of an integer num is an array representing its digits in left to right order.
//
//    For example, for num = 1321, the array form is [1,3,2,1].
//    Given num, the array-form of an integer, and an integer k, return the array-form of the integer num + k.
//
//
//
//    Example 1:
//
//    Input: num = [1,2,0,0], k = 34
//    Output: [1,2,3,4]
//    Explanation: 1200 + 34 = 1234
//    Example 2:
//
//    Input: num = [2,7,4], k = 181
//    Output: [4,5,5]
//    Explanation: 274 + 181 = 455
//    Example 3:
//
//    Input: num = [2,1,5], k = 806
//    Output: [1,0,2,1]
//    Explanation: 215 + 806 = 1021
//
//
//    Constraints:
//
//    1 <= num.length <= 104
//    0 <= num[i] <= 9
//    num does not contain any leading zeros except for the zero itself.
//    1 <= k <= 104