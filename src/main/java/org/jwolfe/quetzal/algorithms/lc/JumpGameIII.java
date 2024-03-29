package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class JumpGameIII {
    class Solution {
        public boolean canReach(int[] arr, int start) {
            if(arr == null || arr.length == 0) {
                return false;
            }

            return canReachZero(arr, arr.length, start, new HashSet<>());
        }

        private boolean canReachZero(int[] arr, int n, int index, Set<Integer> visitedIndexes) {
            if(index < 0 || index >= n || visitedIndexes.contains(index)) {
                return false;
            }

            visitedIndexes.add(index);
            int val = arr[index];

            if(val == 0) {
                return true;
            }

            return canReachZero(arr, n, index + val, visitedIndexes)
                    || canReachZero(arr, n, index - val, visitedIndexes);
        }
    }

    class Solution_Correct_1 {
        public boolean canReach(int[] arr, int start) {
            if(arr == null || arr.length == 0 || start < 0 || start >= arr.length) {
                return false;
            }

            int n = arr.length;
            boolean[] visited = new boolean[n];
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);
            while(!queue.isEmpty()) {
                int index = queue.poll();
                visited[index] = true;

                int val = arr[index];
                if(val == 0) {
                    return true;
                }

                int left = index - val;
                if(left >= 0 && !visited[left]) {
                    queue.offer(left);
                }

                int right = index + val;
                if(right < n && !visited[right]) {
                    queue.offer(right);
                }
            }

            return false;
        }
    }
}


//    1306. Jump Game III
//    Medium
//
//    965
//
//    30
//
//    Add to List
//
//    Share
//    Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
//
//    Notice that you can not jump outside of the array at any time.
//
//
//
//    Example 1:
//
//    Input: arr = [4,2,3,0,3,1,2], start = 5
//    Output: true
//    Explanation:
//    All possible ways to reach at index 3 with value 0 are:
//    index 5 -> index 4 -> index 1 -> index 3
//    index 5 -> index 6 -> index 4 -> index 1 -> index 3
//    Example 2:
//
//    Input: arr = [4,2,3,0,3,1,2], start = 0
//    Output: true
//    Explanation:
//    One possible way to reach at index 3 with value 0 is:
//    index 0 -> index 4 -> index 1 -> index 3
//    Example 3:
//
//    Input: arr = [3,0,2,1,2], start = 2
//    Output: false
//    Explanation: There is no way to reach at index 1 with value 0.
//
//
//    Constraints:
//
//    1 <= arr.length <= 5 * 104
//    0 <= arr[i] < arr.length
//    0 <= start < arr.length