package org.jwolfe.quetzal.algorithms.lc;

public class RangeSumQueryImmutable {
    class NumArray {
        int[] sums;

        public NumArray(int[] nums) {
            int n = nums.length;
            sums = new int[n];


            if(nums.length > 0) {
                sums[0] = nums[0];
                for(int i = 1; i < n; i++) {
                    sums[i] = sums[i - 1] + nums[i];
                }
            }
        }

        public int sumRange(int i, int j) {
            return sums[j] - (i > 0 ? sums[i - 1] : 0);
        }

        private void print(int[] nums) {
            for(int a : nums) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class NumArray_Correct_2 {
        int[] sums;

        public NumArray_Correct_2(int[] nums) {
            int n = nums.length;

            sums = new int[n];
            sums[0] = nums[0];
            for(int i = 1; i < n; i++) {
                sums[i] = sums[i - 1] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            int low = left > 0 ? sums[left - 1] : 0;
            int high = sums[right];

            return high - low;
        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
}

//    303. Range Sum Query - Immutable
//    Easy
//    Given an integer array nums, handle multiple queries of the following type:
//
//    Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
//    Implement the NumArray class:
//
//    NumArray(int[] nums) Initializes the object with the integer array nums.
//    int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
//
//
//    Example 1:
//
//    Input
//    ["NumArray", "sumRange", "sumRange", "sumRange"]
//    [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
//    Output
//    [null, 1, -1, -3]
//
//    Explanation
//    NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
//    numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
//    numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
//    numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
//
//
//    Constraints:
//
//    1 <= nums.length <= 104
//    -105 <= nums[i] <= 105
//    0 <= left <= right < nums.length
//    At most 104 calls will be made to sumRange.
