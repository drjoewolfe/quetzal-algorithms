package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    class Solution {
        public List<String> summaryRanges(int[] nums) {
            List<String> ranges = new ArrayList<>();

            int n = nums.length;
            for(int i = 0; i < n; i++) {
                int start = nums[i];

                while(i + 1 < n && nums[i] + 1 == nums[i + 1]) {
                    i++;
                }

                if(start != nums[i]) {
                    ranges.add(start + "->" + nums[i]);
                } else {
                    ranges.add(String.valueOf(start));
                }
            }

            return ranges;
        }
    }

    class Solution_Correct_1 {
        public List<String> summaryRanges(int[] nums) {
            List<String> ranges = new ArrayList<>();
            if(nums == null || nums.length == 0) {
                return ranges;
            }

            int n = nums.length;
            int rangeStart = 0;
            for(int i = 1; i < n; i++) {
                if(nums[i - 1] + 1 < nums[i]) {
                    if(rangeStart == i - 1) {
                        ranges.add(Integer.toString(nums[rangeStart]));
                    } else {
                        ranges.add(nums[rangeStart] + "->" + nums[i - 1]);
                    }

                    rangeStart = i;
                }
            }

            if(rangeStart == n - 1) {
                ranges.add(Integer.toString(nums[rangeStart]));
            } else {
                ranges.add(nums[rangeStart] + "->" + nums[n - 1]);
            }

            return ranges;
        }
    }
}

//    228. Summary Ranges
//    Easy
//    You are given a sorted unique integer array nums.
//
//    A range [a,b] is the set of all integers from a to b (inclusive).
//
//    Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
//
//    Each range [a,b] in the list should be output as:
//
//    "a->b" if a != b
//    "a" if a == b
//
//
//    Example 1:
//
//    Input: nums = [0,1,2,4,5,7]
//    Output: ["0->2","4->5","7"]
//    Explanation: The ranges are:
//    [0,2] --> "0->2"
//    [4,5] --> "4->5"
//    [7,7] --> "7"
//    Example 2:
//
//    Input: nums = [0,2,3,4,6,8,9]
//    Output: ["0","2->4","6","8->9"]
//    Explanation: The ranges are:
//    [0,0] --> "0"
//    [2,4] --> "2->4"
//    [6,6] --> "6"
//    [8,9] --> "8->9"
//
//
//    Constraints:
//
//    0 <= nums.length <= 20
//    -231 <= nums[i] <= 231 - 1
//    All the values of nums are unique.
//    nums is sorted in ascending order.