package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MergeTwo2DArraysBySummingValues {
    class Solution {
        public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
            if (nums1 == null || nums1.length == 0) {
                return nums2;
            }

            if (nums2 == null || nums2.length == 0) {
                return nums1;
            }

            List<int[]> merged = new ArrayList<>();
            int i1 = 0;
            int n1 = nums1.length;

            int i2 = 0;
            int n2 = nums2.length;

            while (i1 < n1 && i2 < n2) {
                if (nums1[i1][0] == nums2[i2][0]) {
                    merged.add(new int[]{nums1[i1][0], nums1[i1][1] + nums2[i2][1]});
                    i1++;
                    i2++;
                } else if (nums1[i1][0] < nums2[i2][0]) {
                    merged.add(nums1[i1++]);
                } else {
                    merged.add(nums2[i2++]);
                }
            }

            while (i1 < n1) {
                merged.add(nums1[i1++]);
            }

            while (i2 < n2) {
                merged.add(nums2[i2++]);
            }

            int n = merged.size();
            int[][] results = new int[n][2];
            for (int i = 0; i < n; i++) {
                results[i] = merged.get(i);
            }

            return results;
        }
    }

    class Solution_Correct_1 {
        public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
            if (nums1 == null || nums1.length == 0) {
                return nums2;
            }

            if (nums2 == null || nums2.length == 0) {
                return nums1;
            }

            Map<Integer, Integer> map = new TreeMap<>();
            for (int[] pair : nums1) {
                map.put(pair[0], pair[1]);
            }

            for (int[] pair : nums2) {
                int id = pair[0];

                if (map.containsKey(id)) {
                    map.put(id, map.get(id) + pair[1]);
                } else {
                    map.put(id, pair[1]);
                }
            }

            int n = map.size();
            int i = 0;
            int[][] results = new int[n][2];
            for (var entry : map.entrySet()) {
                results[i++] = new int[]{entry.getKey(), entry.getValue()};
            }

            return results;
        }
    }
}

//    2570. Merge Two 2D Arrays by Summing Values
//    Easy
//    You are given two 2D integer arrays nums1 and nums2.
//
//    nums1[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
//    nums2[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
//    Each array contains unique ids and is sorted in ascending order by id.
//
//    Merge the two arrays into one array that is sorted in ascending order by id, respecting the following conditions:
//
//    Only ids that appear in at least one of the two arrays should be included in the resulting array.
//    Each id should be included only once and its value should be the sum of the values of this id in the two arrays. If the id does not exist in one of the two arrays then its value in that array is considered to be 0.
//    Return the resulting array. The returned array must be sorted in ascending order by id.
//
//
//
//    Example 1:
//
//    Input: nums1 = [[1,2],[2,3],[4,5]], nums2 = [[1,4],[3,2],[4,1]]
//    Output: [[1,6],[2,3],[3,2],[4,6]]
//    Explanation: The resulting array contains the following:
//    - id = 1, the value of this id is 2 + 4 = 6.
//    - id = 2, the value of this id is 3.
//    - id = 3, the value of this id is 2.
//    - id = 4, the value of this id is 5 + 1 = 6.
//    Example 2:
//
//    Input: nums1 = [[2,4],[3,6],[5,5]], nums2 = [[1,3],[4,3]]
//    Output: [[1,3],[2,4],[3,6],[4,3],[5,5]]
//    Explanation: There are no common ids, so we just include each id with its value in the resulting list.
//
//
//    Constraints:
//
//    1 <= nums1.length, nums2.length <= 200
//    nums1[i].length == nums2[j].length == 2
//    1 <= idi, vali <= 1000
//    Both arrays contain unique ids.
//    Both arrays are in strictly ascending order by id.