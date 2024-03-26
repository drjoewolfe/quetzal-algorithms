package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FirstMissingPositive {
    class Solution {
        public int firstMissingPositive(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 1;
            }

            int n = nums.length;
            boolean hasOne = false;
            for(int i = 0; i < n; i++) {
                int a = nums[i];

                if(a == 1) {
                    hasOne = true;
                } else if(a < 1 || a > n) {
                    nums[i] = 1;
                }
            }

            if(!hasOne) {
                return 1;
            }

            for(int i = 0; i < n; i++) {
                int a = Math.abs(nums[i]);

                if(a == n) {
                    nums[0] = (-1) * Math.abs(nums[0]);
                } else {
                    nums[a] = (-1) * Math.abs(nums[a]);
                }
            }

            for(int i = 1; i < n; i++) {
                if(nums[i] > 0) {
                    return i;
                }
            }

            if(nums[0] > 0) {
                return n;
            }

            return n + 1;
        }

        private void print(int[] nums) {
            for(int n : nums) {
                System.out.print(n + " ");
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
        public int firstMissingPositive(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 1;
            }

            int positiveCount = moveNegativesToEnd(nums);

            int largest = 0;
            for(int i = 0; i < positiveCount; i++) {
                int a = Math.abs(nums[i]);

                largest = Math.max(largest, a);
                if(a > 0 && a <= nums.length && nums[a - 1] > 0) {
                    nums[a - 1] *= -1;
                }
            }

            for(int i = 0; i < nums.length; i++) {
                int a = nums[i];
                if(a > 0) {
                    return i + 1;
                }
            }

            return largest + 1;
        }

        private int moveNegativesToEnd(int[] nums) {
            int length = nums.length;

            if(nums.length == 1) {
                return nums[0] > 0 ? 1 : 0;
            }

            int left = 0;
            int right = nums.length - 1;

            while(left < right) {
                while(left < length && nums[left] > 0) {
                    left++;
                }

                while(right > 0 && nums[right] <= 0) {
                    right--;
                }

                if(left < length && left < right) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                }
            }

            return left;
        }

        private void printArray(int[] nums) {
            for(int n : nums) {
                System.out.print(n + " ");
            }

            System.out.println();
        }
    }


// TC
//  [1,2,0]
//  [3,4,-1,1]
//  [2, 3, -7, 6, 8, 1, -10, 15]
//  [0]
//  [1]
//  [-5]
//  [1,2,2,1,3,1,0,4,0]
//  [0,2,2,4,0,1,0,1,3]

    class Solution_Sort {
        public int firstMissingPositive(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 1;
            }

            int length = nums.length;

            Arrays.sort(nums);
            int largest = nums[length - 1];

            int index = 0;
            while(index < length && nums[index] <= 0) {
                index++;
            }

            int n = 1;
            for(; n < largest && index < length; n++) {
                int v = nums[index];
                if(v != n) {
                    return n;
                }

                while(index < length && nums[index] == v) {
                    index++;
                }
            }

            if(n != largest) {
                return n;
            }

            return largest + 1;
        }
    }

    class Solution_Space {
        public int firstMissingPositive(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 1;
            }

            Set<Integer> set = new HashSet<>();
            int largest = 0;
            for(int n : nums) {
                largest = Math.max(largest, n);
                set.add(n);
            }

            for(int n = 1; n < largest; n++) {
                if(!set.contains(n)) {
                    return n;
                }
            }

            return largest + 1;
        }
    }
}

//    41. First Missing Positive
//    Hard
//    Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
//
//    You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,0]
//    Output: 3
//    Explanation: The numbers in the range [1,2] are all in the array.
//    Example 2:
//
//    Input: nums = [3,4,-1,1]
//    Output: 2
//    Explanation: 1 is in the array but 2 is missing.
//    Example 3:
//
//    Input: nums = [7,8,9,11,12]
//    Output: 1
//    Explanation: The smallest positive integer 1 is missing.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    -231 <= nums[i] <= 231 - 1