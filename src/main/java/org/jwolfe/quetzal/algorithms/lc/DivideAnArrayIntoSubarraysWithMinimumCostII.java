package org.jwolfe.quetzal.algorithms.lc;

import java.util.TreeMap;

public class DivideAnArrayIntoSubarraysWithMinimumCostII {
    class Solution {
        public long minimumCost(int[] nums, int k, int dist) {
            if (nums == null || nums.length == 0 || k < 1 || dist < 1) {
                return -1L;
            }

            Container container = new Container(k - 2);
            for (int i = 1; i < k - 1; i++) {
                int x = nums[i];
                container.add(x);
            }

            int n = nums.length;
            long suffixSum = container.sum() + nums[k - 1];
            for (int i = k; i < n; i++) {
                int j = i - dist - 1;
                if (j > 0) {
                    int x = nums[j];
                    container.remove(x);
                }

                int y = nums[i - 1];
                container.add(y);
                suffixSum = Math.min(suffixSum, container.sum() + nums[i]);
            }

            return nums[0] + suffixSum;
        }

        private class Container {
            int size;

            TreeMap<Integer, Integer> map1;
            TreeMap<Integer, Integer> map2;

            int map1Size;
            int map2Size;

            long sum;

            public Container(int size) {
                this.size = size;

                this.map1 = new TreeMap<>();
                this.map2 = new TreeMap<>();
            }

            public void add(int x) {
                if (!map2.isEmpty() && x >= map2.firstKey()) {
                    addOne(map2, x);
                    map2Size++;
                } else {
                    addOne(map1, x);
                    map1Size++;
                    sum += x;
                }

                adjust();
            }

            public void remove(int x) {
                if (map1.containsKey(x)) {
                    removeOne(map1, x);
                    map1Size--;
                    sum -= x;
                } else if (map2.containsKey(x)) {
                    removeOne(map2, x);
                    map2Size--;
                }

                adjust();
            }

            public long sum() {
                return this.sum;
            }

            private void addOne(TreeMap<Integer, Integer> map, int key) {
                map.put(key, map.getOrDefault(key, 0) + 1);
            }

            private void removeOne(TreeMap<Integer, Integer> map, int key) {
                int count = map.get(key);
                if (count == 1) {
                    map.remove(key);
                } else {
                    map.put(key, map.get(key) - 1);
                }
            }

            private void adjust() {
                while (!map2.isEmpty() && map1Size < size) {
                    int x = map2.firstKey();
                    addOne(map1, x);
                    map1Size++;
                    sum += x;

                    removeOne(map2, x);
                    map2Size--;
                }

                while (map1Size > size) {
                    int x = map1.lastKey();
                    addOne(map2, x);
                    map2Size++;

                    removeOne(map1, x);
                    map1Size--;
                    sum -= x;
                }
            }
        }
    }
}

//    3013. Divide an Array Into Subarrays With Minimum Cost II
//    Hard
//    You are given a 0-indexed array of integers nums of length n, and two positive integers k and dist.
//
//    The cost of an array is the value of its first element. For example, the cost of [1,2,3] is 1 while the cost of [3,4,1] is 3.
//
//    You need to divide nums into k disjoint contiguous subarrays, such that the difference between the starting index of the second subarray and the starting index of the kth subarray should be less than or equal to dist. In other words, if you divide nums into the subarrays nums[0..(i1 - 1)], nums[i1..(i2 - 1)], ..., nums[ik-1..(n - 1)], then ik-1 - i1 <= dist.
//
//    Return the minimum possible sum of the cost of these subarrays.
//
//
//
//    Example 1:
//
//    Input: nums = [1,3,2,6,4,2], k = 3, dist = 3
//    Output: 5
//    Explanation: The best possible way to divide nums into 3 subarrays is: [1,3], [2,6,4], and [2]. This choice is valid because ik-1 - i1 is 5 - 2 = 3 which is equal to dist. The total cost is nums[0] + nums[2] + nums[5] which is 1 + 2 + 2 = 5.
//    It can be shown that there is no possible way to divide nums into 3 subarrays at a cost lower than 5.
//    Example 2:
//
//    Input: nums = [10,1,2,2,2,1], k = 4, dist = 3
//    Output: 15
//    Explanation: The best possible way to divide nums into 4 subarrays is: [10], [1], [2], and [2,2,1]. This choice is valid because ik-1 - i1 is 3 - 1 = 2 which is less than dist. The total cost is nums[0] + nums[1] + nums[2] + nums[3] which is 10 + 1 + 2 + 2 = 15.
//    The division [10], [1], [2,2,2], and [1] is not valid, because the difference between ik-1 and i1 is 5 - 1 = 4, which is greater than dist.
//    It can be shown that there is no possible way to divide nums into 4 subarrays at a cost lower than 15.
//    Example 3:
//
//    Input: nums = [10,8,18,9], k = 3, dist = 1
//    Output: 36
//    Explanation: The best possible way to divide nums into 4 subarrays is: [10], [8], and [18,9]. This choice is valid because ik-1 - i1 is 2 - 1 = 1 which is equal to dist.The total cost is nums[0] + nums[1] + nums[2] which is 10 + 8 + 18 = 36.
//    The division [10], [8,18], and [9] is not valid, because the difference between ik-1 and i1 is 3 - 1 = 2, which is greater than dist.
//    It can be shown that there is no possible way to divide nums into 3 subarrays at a cost lower than 36.
//
//
//    Constraints:
//
//    3 <= n <= 105
//    1 <= nums[i] <= 109
//    3 <= k <= n
//    k - 2 <= dist <= n - 2