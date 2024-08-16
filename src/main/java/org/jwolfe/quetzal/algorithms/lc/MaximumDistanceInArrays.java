package org.jwolfe.quetzal.algorithms.lc;

import java.util.List;

public class MaximumDistanceInArrays {
    class Solution {
        public int maxDistance(List<List<Integer>> arrays) {
            if (arrays == null || arrays.size() < 2) {
                return 0;
            }

            int n = arrays.size();

            var firstArray = arrays.get(0);
            int smallest = firstArray.get(0);
            int largest = firstArray.get(firstArray.size() - 1);

            int maxDistance = 0;

            for (int i = 1; i < n; i++) {
                var array = arrays.get(i);

                int smallestInArray = array.get(0);
                int largestInArray = array.get(array.size() - 1);

                int distance1 = Math.abs(smallestInArray - largest);
                int distance2 = Math.abs(largestInArray - smallest);

                maxDistance = Math.max(maxDistance,
                        Math.max(distance1, distance2));

                smallest = Math.min(smallest, smallestInArray);
                largest = Math.max(largest, largestInArray);
            }

            return maxDistance;
        }
    }
}

//    624. Maximum Distance in Arrays
//    Medium
//    You are given m arrays, where each array is sorted in ascending order.
//
//    You can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a - b|.
//
//    Return the maximum distance.
//
//
//
//    Example 1:
//
//    Input: arrays = [[1,2,3],[4,5],[1,2,3]]
//    Output: 4
//    Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
//    Example 2:
//
//    Input: arrays = [[1],[1]]
//    Output: 0
//
//
//    Constraints:
//
//    m == arrays.length
//    2 <= m <= 105
//    1 <= arrays[i].length <= 500
//    -104 <= arrays[i][j] <= 104
//    arrays[i] is sorted in ascending order.
//    There will be at most 105 integers in all the arrays.
