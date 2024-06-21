package org.jwolfe.quetzal.algorithms.lc;

public class GrumpyBookstoreOwner {
    class Solution {
        public int maxSatisfied(int[] customers, int[] grumpy, int X) {
            if (customers == null || grumpy == null || customers.length != grumpy.length || X < 0) {
                return 0;
            }

            int n = customers.length;
            int originalSatisfied = 0;
            int grumpyMinutes = 0;
            for (int i = 0; i < n; i++) {
                if (grumpy[i] == 0) {
                    originalSatisfied += customers[i];
                } else {
                    grumpyMinutes++;
                }
            }

            int left = 0;
            int right = 0;

            int shine = 0;
            int maxShine = 0;

            for (; right < n; right++) {
                if (grumpy[right] == 1) {
                    shine += customers[right];
                }

                int length = right - left + 1;
                if (length == X) {
                    maxShine = Math.max(maxShine, shine);

                    if (grumpy[left] == 1) {
                        shine -= customers[left];
                    }

                    left++;
                }
            }


            return originalSatisfied + maxShine;
        }
    }

    class Solution_Correct_1 {
        public int maxSatisfied(int[] customers, int[] grumpy, int X) {
            if (customers == null || customers.length == 0 || grumpy == null || grumpy.length != customers.length || X < 0) {
                return 0;
            }

            int n = customers.length;
            int satisfied = 0;
            int grumpyMinutes = 0;
            for (int i = 0; i < n; i++) {
                if (grumpy[i] == 0) {
                    satisfied += customers[i];
                } else {
                    grumpyMinutes++;
                }
            }

            if (grumpyMinutes == 0) {
                return satisfied;
            }

            int left = 0;
            int right = 0;
            int shine = 0;
            int maxShine = 0;
            for (; right < n; right++) {
                if (grumpy[right] == 1) {
                    shine += customers[right];
                }

                if (right - left + 1 > X) {
                    if (grumpy[left] == 1) {
                        shine -= customers[left];
                    }

                    left++;
                }

                maxShine = Math.max(maxShine, shine);
            }

            return satisfied + maxShine;
        }
    }

// [1,0,1,2,1,1,7,5]
// [0,1,0,1,0,1,0,1]
// 3

// [2,6,6,9]
// [0,0,1,1]
// 1

// [4,10,10]
// [1,1,0]
// 2
}

//    1052. Grumpy Bookstore Owner
//    Medium
//    There is a bookstore owner that has a store open for n minutes. Every minute, some number of customers enter the store. You are given an integer array customers of length n where customers[i] is the number of the customer that enters the store at the start of the ith minute and all those customers leave after the end of that minute.
//
//    On some minutes, the bookstore owner is grumpy. You are given a binary array grumpy where grumpy[i] is 1 if the bookstore owner is grumpy during the ith minute, and is 0 otherwise.
//
//    When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise, they are satisfied.
//
//    The bookstore owner knows a secret technique to keep themselves not grumpy for minutes consecutive minutes, but can only use it once.
//
//    Return the maximum number of customers that can be satisfied throughout the day.
//
//
//
//    Example 1:
//
//    Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
//    Output: 16
//    Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
//    The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
//    Example 2:
//
//    Input: customers = [1], grumpy = [0], minutes = 1
//    Output: 1
//
//
//    Constraints:
//
//    n == customers.length == grumpy.length
//    1 <= minutes <= n <= 2 * 104
//    0 <= customers[i] <= 1000
//    grumpy[i] is either 0 or 1.
