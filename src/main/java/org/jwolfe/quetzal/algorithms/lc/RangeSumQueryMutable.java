package org.jwolfe.quetzal.algorithms.lc;

public class RangeSumQueryMutable {
    class NumArray {
        int[] segmentTree;
        int n;

        public NumArray(int[] nums) {
            constructTree(nums);
        }

        public void update(int index, int val) {
            updateTree(index, val);
        }

        public int sumRange(int left, int right) {
            return getSum(left, right);
        }

        private void constructTree(int[] nums) {
            n = nums.length;
            segmentTree = new int[2 * n];

            for(int i = n, j = 0; j < n; i++, j++) {
                segmentTree[i] = nums[j];
            }

            for(int i = n - 1; i >= 0; i--) {
                segmentTree[i] = segmentTree[2 * i] + segmentTree[2 * i + 1];
            }
        }

        private void updateTree(int index, int val) {
            index += n;
            segmentTree[index] = val;

            while(index > 0) {
                int left = index;
                int right = index;

                if(index % 2 == 0) {
                    right = index + 1;
                } else {
                    left = index - 1;
                }

                segmentTree[index / 2] = segmentTree[left] + segmentTree[right];
                index /= 2;
            }
        }

        private int getSum(int left, int right) {
            left += n;
            right += n;

            int sum = 0;
            while(left <= right) {
                if(left % 2 == 1) {
                    sum += segmentTree[left];
                    left++;
                }

                if(right % 2 == 0) {
                    sum += segmentTree[right];
                    right--;
                }

                left /= 2;
                right /= 2;
            }

            return sum;
        }
    }

    class NumArray_Brute {
        int[] nums;

        public NumArray_Brute(int[] nums) {
            this.nums = nums;
        }

        public void update(int index, int val) {
            if(index < 0 || index >= nums.length) {
                return;
            }

            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            if(left > right) {
                return 0;
            }

            if(left < 0) {
                left = 0;
            }

            if(right >= nums.length) {
                right = nums.length - 1;
            }

            int sum = 0;
            for(int i = left; i <= right; i++) {
                sum += nums[i];
            }

            return sum;
        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */

// ["NumArray","sumRange","update","sumRange"]
// [[[1,3,5]],[0,2],[1,2],[0,2]]
}

//    307. Range Sum Query - Mutable
//    Medium
//    Given an integer array nums, handle multiple queries of the following types:
//
//    Update the value of an element in nums.
//    Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
//    Implement the NumArray class:
//
//    NumArray(int[] nums) Initializes the object with the integer array nums.
//    void update(int index, int val) Updates the value of nums[index] to be val.
//    int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
//
//
//    Example 1:
//
//    Input
//    ["NumArray", "sumRange", "update", "sumRange"]
//    [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
//    Output
//    [null, 9, null, 8]
//
//    Explanation
//    NumArray numArray = new NumArray([1, 3, 5]);
//    numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
//    numArray.update(1, 2);   // nums = [1, 2, 5]
//    numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
//
//
//    Constraints:
//
//    1 <= nums.length <= 3 * 104
//    -100 <= nums[i] <= 100
//    0 <= index < nums.length
//    -100 <= val <= 100
//    0 <= left <= right < nums.length
//    At most 3 * 104 calls will be made to update and sumRange.