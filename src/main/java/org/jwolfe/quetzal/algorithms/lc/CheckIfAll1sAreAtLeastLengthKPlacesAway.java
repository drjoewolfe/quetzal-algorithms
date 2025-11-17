package org.jwolfe.quetzal.algorithms.lc;

public class CheckIfAll1sAreAtLeastLengthKPlacesAway {
    class Solution {
        public boolean kLengthApart(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return true;
            }

            int prevOneIndex = -1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 1) {
                    if (prevOneIndex != -1) {
                        int length = i - prevOneIndex - 1;
                        if (length < k) {
                            return false;
                        }
                    }

                    prevOneIndex = i;
                }
            }

            return true;
        }
    }

    class Solution_Correct_2 {
        public boolean kLengthApart(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return true;
            }

            int i = 0;
            while (i < nums.length && nums[i] != 1) {
                i++;
            }

            i++;
            int emptySlots = 0;
            for (; i < nums.length; i++) {
                if (nums[i] == 1) {
                    if (emptySlots < k) {
                        return false;
                    }

                    emptySlots = 0;
                } else {
                    emptySlots++;
                }
            }

            return true;
        }
    }

    class Solution_Correct_3 {
        public boolean kLengthApart(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 0) {
                return false;
            }

            int prev = -1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 1) {
                    if (prev != -1 && !((i - prev - 1) >= k)) {
                        return false;
                    }

                    prev = i;
                }
            }

            return true;
        }
    }
}

//    1437. Check If All 1's Are at Least Length K Places Away
//    Given an array nums of 0s and 1s and an integer k, return True if all 1's are at least k places away from each other, otherwise return False.
//
//    Example 1:
//
//    Input: nums = [1,0,0,0,1,0,0,1], k = 2
//    Output: true
//    Explanation: Each of the 1s are at least 2 places away from each other.
//    Example 2:
//
//
//    Input: nums = [1,0,0,1,0,1], k = 2
//    Output: false
//    Explanation: The second 1 and third 1 are only one apart from each other.
//    Example 3:
//
//    Input: nums = [1,1,1,1,1], k = 0
//    Output: true
//    Example 4:
//
//    Input: nums = [0,1,0,1], k = 1
//    Output: true
//
//
//    Constraints:
//    1 <= nums.length <= 105
//    0 <= k <= nums.length
//    nums[i] is 0 or 1