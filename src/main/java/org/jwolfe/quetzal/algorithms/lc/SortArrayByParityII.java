package org.jwolfe.quetzal.algorithms.lc;

public class SortArrayByParityII {
    class Solution {
        public int[] sortArrayByParityII(int[] A) {
            if(A == null || A.length < 2) {
                return A;
            }

            int n = A.length;
            int evenPointer = 0;
            int oddPointer = 1;

            while(evenPointer < n && oddPointer < n) {

                while(evenPointer < n && A[evenPointer] % 2 == 0) {
                    evenPointer += 2;
                }

                while(oddPointer < n && A[oddPointer] % 2 != 0) {
                    oddPointer += 2;
                }

                if(evenPointer < n && oddPointer < n) {
                    int temp = A[evenPointer];
                    A[evenPointer] = A[oddPointer];
                    A[oddPointer] = temp;
                }
            }

            return A;
        }
    }

    class Solution_Correct_2 {
        public int[] sortArrayByParityII(int[] nums) {
            if(nums == null || nums.length == 0 || nums.length % 2 != 0) {
                return new int[0];
            }

            int n = nums.length;
            int evenIndex = 0;
            int oddIndex = 1;

            while(evenIndex < n && oddIndex < n) {

                while(evenIndex < n && nums[evenIndex] % 2 == 0) {
                    evenIndex += 2;
                }

                while(oddIndex < n && nums[oddIndex] % 2 != 0) {
                    oddIndex += 2;
                }

                if(evenIndex < n && oddIndex < n) {
                    int temp = nums[evenIndex];
                    nums[evenIndex] = nums[oddIndex];
                    nums[oddIndex] = temp;

                    evenIndex += 2;
                    oddIndex += 2;
                }
            }

            return nums;
        }
    }
}

//    922. Sort Array By Parity II
//    Easy
//    Given an array of integers nums, half of the integers in nums are odd, and the other half are even.
//
//    Sort the array so that whenever nums[i] is odd, i is odd, and whenever nums[i] is even, i is even.
//
//    Return any answer array that satisfies this condition.
//
//
//
//    Example 1:
//
//    Input: nums = [4,2,5,7]
//    Output: [4,5,2,7]
//    Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
//    Example 2:
//
//    Input: nums = [2,3]
//    Output: [2,3]
//
//
//    Constraints:
//
//    2 <= nums.length <= 2 * 104
//    nums.length is even.
//    Half of the integers in nums are even.
//    0 <= nums[i] <= 1000
//
//
//    Follow Up: Could you solve it in-place?