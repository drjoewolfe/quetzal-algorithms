package org.jwolfe.quetzal.algorithms.lc;

public class CheckIfAllTheIntegersInARangeAreCovered {
    class Solution {
        public boolean isCovered(int[][] ranges, int left, int right) {
            if(ranges == null || ranges.length == 0) {
                return false;
            }

            int[] line = new int[51];
            for(var range : ranges) {
                for(int val = range[0]; val <= range[1]; val++) {
                    line[val]++;
                }
            }

            for(int val = left; val <= right; val++) {
                if(line[val] == 0) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public boolean isCovered(int[][] ranges, int left, int right) {
            if(ranges == null || ranges.length == 0) {
                return false;
            }

            for(int val = left; val <= right; val++) {
                boolean covered = false;

                for(int[] range : ranges) {
                    if(val >= range[0] && val <= range[1]) {
                        covered = true;
                        break;
                    }
                }

                if(!covered) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    1893. Check if All the Integers in a Range Are Covered
//    Easy
//    You are given a 2D integer array ranges and two integers left and right. Each ranges[i] = [starti, endi] represents an inclusive interval between starti and endi.
//
//    Return true if each integer in the inclusive range [left, right] is covered by at least one interval in ranges. Return false otherwise.
//
//    An integer x is covered by an interval ranges[i] = [starti, endi] if starti <= x <= endi.
//
//
//
//    Example 1:
//
//    Input: ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
//    Output: true
//    Explanation: Every integer between 2 and 5 is covered:
//    - 2 is covered by the first range.
//    - 3 and 4 are covered by the second range.
//    - 5 is covered by the third range.
//    Example 2:
//
//    Input: ranges = [[1,10],[10,20]], left = 21, right = 21
//    Output: false
//    Explanation: 21 is not covered by any range.
//
//
//    Constraints:
//
//    1 <= ranges.length <= 50
//    1 <= starti <= endi <= 50
//    1 <= left <= right <= 50
