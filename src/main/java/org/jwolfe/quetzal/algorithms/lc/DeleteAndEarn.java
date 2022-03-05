package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class DeleteAndEarn {
    class Solution {
        public int deleteAndEarn(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            Map<Integer, Integer> pointMap = new HashMap<>();
            int maxNumber = 0;
            for(int val : nums) {
                pointMap.put(val, pointMap.getOrDefault(val, 0) + val);
                maxNumber = Math.max(maxNumber, val);
            }

            int twoPrev = 0;
            int prev = pointMap.getOrDefault(1, 0);

            for(int val = 2; val <= maxNumber; val++) {
                int points = pointMap.getOrDefault(val, 0);

                int curr = Math.max(
                        prev,
                        twoPrev + points
                );

                twoPrev = prev;
                prev = curr;
            }

            return prev;
        }
    }

    class Solution_BottomUpDP {
        public int deleteAndEarn(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            Map<Integer, Integer> pointMap = new HashMap<>();
            int maxNumber = 0;
            for(int val : nums) {
                pointMap.put(val, pointMap.getOrDefault(val, 0) + val);
                maxNumber = Math.max(maxNumber, val);
            }

            int[] dp = new int[maxNumber + 1];
            dp[1] = pointMap.getOrDefault(1, 0);

            for(int val = 2; val <= maxNumber; val++) {
                int points = pointMap.getOrDefault(val, 0);

                dp[val] = Math.max(
                        dp[val - 1],
                        dp[val - 2] + points
                );
            }

            return dp[maxNumber];
        }
    }


    class Solution_TopDownDP {
        public int deleteAndEarn(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            Map<Integer, Integer> pointMap = new HashMap<>();
            int maxNumber = 0;
            for(int val : nums) {
                pointMap.put(val, pointMap.getOrDefault(val, 0) + val);
                maxNumber = Math.max(maxNumber, val);
            }

            return deleteAndEarn(maxNumber, pointMap, new HashMap<>());
        }

        private int deleteAndEarn(int num, Map<Integer, Integer> pointMap, Map<Integer, Integer> memo) {
            if(num == 0) {
                return 0;
            }

            if(num == 1) {
                return pointMap.getOrDefault(1, 0);
            }

            if(memo.containsKey(num)) {
                return memo.get(num);
            }

            int points = pointMap.getOrDefault(num, 0);
            int maxPoints = Math.max(
                    deleteAndEarn(num - 1, pointMap, memo),
                    deleteAndEarn(num - 2, pointMap, memo) + points
            );

            memo.put(num, maxPoints);
            return maxPoints;
        }
    }

    class Solution_TopDown {
        public int deleteAndEarn(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            Map<Integer, Integer> pointMap = new HashMap<>();
            int maxNumber = 0;
            for(int val : nums) {
                pointMap.put(val, pointMap.getOrDefault(val, 0) + val);
                maxNumber = Math.max(maxNumber, val);
            }

            return deleteAndEarn(maxNumber, pointMap);
        }

        private int deleteAndEarn(int num, Map<Integer, Integer> pointMap) {
            if(num == 0) {
                return 0;
            }

            if(num == 1) {
                return pointMap.getOrDefault(1, 0);
            }

            int points = pointMap.getOrDefault(num, 0);
            return Math.max(
                    deleteAndEarn(num - 1, pointMap),
                    deleteAndEarn(num - 2, pointMap) + points
            );
        }
    }

    class Solution_Try03_TLE {
        public int deleteAndEarn(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            List<Integer> list = new ArrayList<>();
            Map<Integer, Integer> map = new HashMap<>();
            for(int v : nums) {
                if(!map.containsKey(v)) {
                    list.add(v);
                }

                map.put(v, map.getOrDefault(v, 0) + 1);
            }

            return deleteAndEarn(list, 0, 0, new HashSet<>(), map, new HashMap<>());
        }

        private int deleteAndEarn(List<Integer> nums, int index, int points, Set<Integer> seen, Map<Integer, Integer> map, Map<String, Integer> memo) {
            if(index == nums.size()) {
                return points;
            }

            String memento = map.toString();
            if(memo.containsKey(memento)) {
                // System.out.println(memento + ", " + memo.get(memento));
                return memo.get(memento);
            }

            int maxPoints = 0;
            int v = nums.get(index);
            if(!seen.contains(v)) {
                seen.add(v);
                // System.out.println(index + ", " + v + ", " + (points + map.get(v) * v) + ", " + seen + ", " + map);
                int pv = v - 1;
                int nv = v + 1;

                int pvc = 0;
                if(map.containsKey(pv)) {
                    pvc = map.get(pv);
                    map.put(pv, 0);
                }

                int nvc = 0;
                if(map.containsKey(nv)) {
                    nvc = map.get(nv);
                    map.put(nv, 0);
                }

                maxPoints = deleteAndEarn(nums, index + 1, points + map.get(v) * v, seen, map, memo);
                if(map.containsKey(pv)) {
                    map.put(pv, pvc);
                }

                if(map.containsKey(nv)) {
                    map.put(nv, nvc);
                }

                seen.remove(v);
                maxPoints = Math.max(maxPoints,
                        deleteAndEarn(nums, index + 1, points, seen, map, memo));
            } else {
                maxPoints = deleteAndEarn(nums, index + 1, points, seen, map, memo);
            }

            memo.put(memento, maxPoints);
            return maxPoints;
        }
    }

    class Solution_Try02_TLE {
        int maxPoints;

        public int deleteAndEarn(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            List<Integer> list = new ArrayList<>();
            Map<Integer, Integer> map = new HashMap<>();
            for(int v : nums) {
                if(!map.containsKey(v)) {
                    list.add(v);
                }

                map.put(v, map.getOrDefault(v, 0) + 1);
            }

            maxPoints = 0;
            deleteAndEarn(list, 0, 0, new HashSet<>(), map);

            return maxPoints;
        }

        private void deleteAndEarn(List<Integer> nums, int index, int points, Set<Integer> seen, Map<Integer, Integer> map) {
            if(index == nums.size()) {
                maxPoints = Math.max(maxPoints, points);
                return;
            }

            int v = nums.get(index);
            if(!seen.contains(v)) {
                seen.add(v);
                // System.out.println(index + ", " + v + ", " + (points + map.get(v) * v) + ", " + seen + ", " + map);
                int pv = v - 1;
                int nv = v + 1;

                int pvc = 0;
                if(map.containsKey(pv)) {
                    pvc = map.get(pv);
                    map.put(pv, 0);
                }

                int nvc = 0;
                if(map.containsKey(nv)) {
                    nvc = map.get(nv);
                    map.put(nv, 0);
                }

                deleteAndEarn(nums, index + 1, points + map.get(v) * v, seen, map);
                if(map.containsKey(pv)) {
                    map.put(pv, pvc);
                }

                if(map.containsKey(nv)) {
                    map.put(nv, nvc);
                }

                seen.remove(v);
                deleteAndEarn(nums, index + 1, points, seen, map);
            } else {
                deleteAndEarn(nums, index + 1, points, seen, map);
            }
        }
    }


    class Solution_Try01_TLE {
        int maxPoints;

        public int deleteAndEarn(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            Map<Integer, Integer> map = new HashMap<>();
            for(int v : nums) {
                map.put(v, map.getOrDefault(v, 0) + 1);
            }

            maxPoints = 0;
            deleteAndEarn(nums, 0, 0, new HashSet<>(), map);

            return maxPoints;
        }

        private void deleteAndEarn(int[] nums, int index, int points, Set<Integer> seen, Map<Integer, Integer> map) {
            if(index == nums.length) {
                maxPoints = Math.max(maxPoints, points);
                return;
            }

            int v = nums[index];
            if(!seen.contains(v)) {
                seen.add(v);
                // System.out.println(index + ", " + v + ", " + (points + map.get(v) * v) + ", " + seen + ", " + map);
                int pv = v - 1;
                int nv = v + 1;

                int pvc = 0;
                if(map.containsKey(pv)) {
                    pvc = map.get(pv);
                    map.put(pv, 0);
                }

                int nvc = 0;
                if(map.containsKey(nv)) {
                    nvc = map.get(nv);
                    map.put(nv, 0);
                }

                deleteAndEarn(nums, index + 1, points + map.get(v) * v, seen, map);
                if(map.containsKey(pv)) {
                    map.put(pv, pvc);
                }

                if(map.containsKey(nv)) {
                    map.put(nv, nvc);
                }

                seen.remove(v);
                deleteAndEarn(nums, index + 1, points, seen, map);
            } else {
                deleteAndEarn(nums, index + 1, points, seen, map);
            }
        }
    }

// [3,4,2]
// [1,8,5,9,6,9,4,1,7,3,3,6,3,3,8,2,6,3,2,2,1,2,9,8,7,1,1,10,6,7,3,9,6,10,5,4,10,1,6,7,4,7,4,1,9,5,1,5,7,5]
// [12,32,93,17,100,72,40,71,37,92,58,34,29,78,11,84,77,90,92,35,12,5,27,92,91,23,65,91,85,14,42,28,80,85,38,71,62,82,66,3,33,33,55,60,48,78,63,11,20,51,78,42,37,21,100,13,60,57,91,53,49,15,45,19,51,2,96,22,32,2,46,62,58,11,29,6,74,38,70,97,4,22,76,19,1,90,63,55,64,44,90,51,36,16,65,95,64,59,53,93]

}

//    740. Delete and Earn
//    Medium
//    You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:
//
//    Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
//    Return the maximum number of points you can earn by applying the above operation some number of times.
//
//
//
//    Example 1:
//
//    Input: nums = [3,4,2]
//    Output: 6
//    Explanation: You can perform the following operations:
//    - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
//    - Delete 2 to earn 2 points. nums = [].
//    You earn a total of 6 points.
//    Example 2:
//
//    Input: nums = [2,2,3,3,3,4]
//    Output: 9
//    Explanation: You can perform the following operations:
//    - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
//    - Delete a 3 again to earn 3 points. nums = [3].
//    - Delete a 3 once more to earn 3 points. nums = [].
//    You earn a total of 9 points.
//
//
//    Constraints:
//
//    1 <= nums.length <= 2 * 104
//    1 <= nums[i] <= 104