package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence {
    class Solution {
        public int lengthOfLIS(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;

            List<Integer> lis = new ArrayList<>();
            lis.add(nums[0]);

            for(int i = 1; i < n; i++) {
                int val = nums[i];

                if(val > lis.get(lis.size() - 1)) {
                    lis.add(val);
                } else {
                    int index = getNextGreaterIndex(lis, val);
                    lis.set(index, val);
                }
            }

            return lis.size();
        }

        private int getNextGreaterIndex(List<Integer> lis, int target) {
            int left = 0;
            int right = lis.size() - 1;

            while(left < right) {
                int mid = left + (right - left) / 2;

                int val = lis.get(mid);
                if(val == target) {
                    return mid;
                } else if(val > target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }

    class Solution_Correct_4 {
        public int lengthOfLIS(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int[] lis = new int[n];
            Arrays.fill(lis, 1);

            int maxLength = 1;
            for(int i = 1; i < n; i++) {
                for(int j = i - 1; j >= 0; j--) {
                    if(nums[j] < nums[i]
                            && lis[j] + 1 > lis[i]) {
                        lis[i] = lis[j] + 1;
                        maxLength = Math.max(maxLength, lis[i]);
                    }
                }
            }

            return maxLength;
        }
    }

    class Solution_Correct_3 {
        public int lengthOfLIS(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            List<Integer> lis = new ArrayList<>();
            lis.add(nums[0]);

            for(int i = 1; i < n; i++) {
                int val = nums[i];

                if(val > lis.get(lis.size() - 1)) {
                    lis.add(val);
                } else {
                    int j = searchForFirstGreater(lis, val);
                    lis.set(j, val);
                }
            }

            return lis.size();
        }

        private int searchForFirstGreater(List<Integer> lis, int val) {
            int left = 0;
            int right = lis.size() - 1;

            while(left < right) {
                int mid = left + (right - left) / 2;

                if(lis.get(mid) == val) {
                    return mid;
                } else if(lis.get(mid) < val) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }
    }

    class Solution_Classic {
        public int lengthOfLIS(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int[] lis = new int[n];
            Arrays.fill(lis, 1);
            for(int i = 1; i < n; i++) {
                for(int j = 0; j < i; j++) {
                    if(nums[j] < nums[i]
                            && lis[i] < lis[j] + 1) {
                        lis[i] = lis[j] + 1;
                    }
                }
            }

            int longestLength = 0;
            for(int length : lis) {
                longestLength = Math.max(longestLength, length);
            }

            return longestLength;
        }

        private void print(int[] nums) {
            Arrays.stream(nums).forEach(n -> System.out.print(n + " " ));
            System.out.println();
        }
    }


    class Solution_Correct_2 {
        public int lengthOfLIS(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int[] lis = new int[n];
            Arrays.fill(lis, 1);
            int maxLength = 1;
            for(int i = 1; i < n; i++) {
                for(int j = 0; j < i; j++) {
                    if(nums[i] > nums[j]
                            && lis[i] < lis[j] + 1) {
                        lis[i] = lis[j] + 1;
                        maxLength = Math.max(maxLength, lis[i]);
                    }
                }
            }

            return maxLength;
        }
    }

// [10,9,2,5,3,7,101,18]
// [4,10,4,3,8,9]
}

//    300. Longest Increasing Subsequence
//    Medium
//    Given an integer array nums, return the length of the longest strictly increasing subsequence.
//
//    A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
//
//
//
//    Example 1:
//
//    Input: nums = [10,9,2,5,3,7,101,18]
//    Output: 4
//    Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
//    Example 2:
//
//    Input: nums = [0,1,0,3,2,3]
//    Output: 4
//    Example 3:
//
//    Input: nums = [7,7,7,7,7,7,7]
//    Output: 1
//
//
//    Constraints:
//
//    1 <= nums.length <= 2500
//    -104 <= nums[i] <= 104
//
//
//    Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
