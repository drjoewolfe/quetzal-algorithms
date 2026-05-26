package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class MinimumJumpsToReachEndViaPrimeTeleportation {
    class Solution {
        public int minJumps(int[] nums) {
            if (nums == null || nums.length < 2) {
                return 0;
            }

            int n = nums.length;
            int maxElement = getMax(nums);
            boolean[] primeSieve = getPrimeSieve(maxElement);

            Map<Integer, List<Integer>> elementIndexMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int val = nums[i];
                if (!elementIndexMap.containsKey(val)) {
                    elementIndexMap.put(val, new ArrayList<>());
                }

                elementIndexMap.get(val).add(i);
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(0);
            int level = 0;

            boolean[] visited = new boolean[n];
            visited[0] = true;

            Set<Integer> seenPrimes = new HashSet<>();

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int index = queue.poll();

                    if (index == n - 1) {
                        return level;
                    }

                    addIfNotVisited(queue, n, index - 1, visited);
                    addIfNotVisited(queue, n, index + 1, visited);

                    int val = nums[index];
                    if (primeSieve[val] && !seenPrimes.contains(val)) {
                        seenPrimes.add(val);

                        for (int multiple = val; multiple <= maxElement; multiple += val) {
                            if (elementIndexMap.containsKey(multiple)) {
                                List<Integer> teleportTargets = elementIndexMap.get(multiple);
                                for (int teleportIndex : teleportTargets) {
                                    addIfNotVisited(queue, n, teleportIndex, visited);
                                }
                            }
                        }

                    }
                }

                level++;
            }

            return -1;
        }

        private int getMax(int[] nums) {
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                max = Math.max(max, nums[i]);
            }

            return max;
        }

        private boolean[] getPrimeSieve(int max) {
            boolean[] sieve = new boolean[max + 1];
            Arrays.fill(sieve, true);

            sieve[0] = false;
            sieve[1] = false;

            for (int i = 2; i * i <= max; i++) {
                if (sieve[i]) {
                    for (int j = i * i; j <= max; j += i) {
                        sieve[j] = false;
                    }
                }
            }

            return sieve;
        }

        private void addIfNotVisited(Queue<Integer> queue, int size, int index, boolean[] visited) {
            if (index < 0 || index >= size) {
                return;
            }

            if (!visited[index]) {
                visited[index] = true;
                queue.offer(index);
            }
        }
    }

    class Solution_TLE {
        public int minJumps(int[] nums) {
            if (nums == null || nums.length < 2) {
                return 0;
            }

            int n = nums.length;
            int maxElement = getMax(nums);
            boolean[] primeSieve = getPrimeSieve(maxElement);

            Map<Integer, List<Integer>> elementIndexMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int val = nums[i];
                if (!elementIndexMap.containsKey(val)) {
                    elementIndexMap.put(val, new ArrayList<>());
                }

                elementIndexMap.get(val).add(i);
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(0);
            int level = 0;

            boolean[] visited = new boolean[n];
            visited[0] = true;

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int index = queue.poll();

                    if (index == n - 1) {
                        return level;
                    }

                    addIfNotVisited(queue, n, index - 1, visited);
                    addIfNotVisited(queue, n, index + 1, visited);

                    int val = nums[index];
                    if (primeSieve[val]) {
                        for (int multiple = val; multiple <= maxElement; multiple += val) {
                            if (elementIndexMap.containsKey(multiple)) {
                                List<Integer> teleportTargets = elementIndexMap.get(multiple);
                                for (int teleportIndex : teleportTargets) {
                                    addIfNotVisited(queue, n, teleportIndex, visited);
                                }
                            }
                        }

                    }
                }

                level++;
            }

            return -1;
        }

        private int getMax(int[] nums) {
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                max = Math.max(max, nums[i]);
            }

            return max;
        }

        private boolean[] getPrimeSieve(int max) {
            boolean[] sieve = new boolean[max + 1];
            Arrays.fill(sieve, true);

            sieve[0] = false;
            sieve[1] = false;

            for (int i = 2; i * i <= max; i++) {
                if (sieve[i]) {
                    for (int j = i * i; j <= max; j += i) {
                        sieve[j] = false;
                    }
                }
            }

            return sieve;
        }

        private void addIfNotVisited(Queue<Integer> queue, int size, int index, boolean[] visited) {
            if (index < 0 || index >= size) {
                return;
            }

            if (!visited[index]) {
                visited[index] = true;
                queue.offer(index);
            }
        }
    }
}

//    3629. Minimum Jumps to Reach End via Prime Teleportation
//    Medium
//    You are given an integer array nums of length n.
//
//    You start at index 0, and your goal is to reach index n - 1.
//
//    From any index i, you may perform one of the following operations:
//
//    Adjacent Step: Jump to index i + 1 or i - 1, if the index is within bounds.
//    Prime Teleportation: If nums[i] is a prime number p, you may instantly jump to any index j != i such that nums[j] % p == 0.
//    Return the minimum number of jumps required to reach index n - 1.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,4,6]
//
//    Output: 2
//
//    Explanation:
//
//    One optimal sequence of jumps is:
//
//    Start at index i = 0. Take an adjacent step to index 1.
//    At index i = 1, nums[1] = 2 is a prime number. Therefore, we teleport to index i = 3 as nums[3] = 6 is divisible by 2.
//    Thus, the answer is 2.
//
//    Example 2:
//
//    Input: nums = [2,3,4,7,9]
//
//    Output: 2
//
//    Explanation:
//
//    One optimal sequence of jumps is:
//
//    Start at index i = 0. Take an adjacent step to index i = 1.
//    At index i = 1, nums[1] = 3 is a prime number. Therefore, we teleport to index i = 4 since nums[4] = 9 is divisible by 3.
//    Thus, the answer is 2.
//
//    Example 3:
//
//    Input: nums = [4,6,5,8]
//
//    Output: 3
//
//    Explanation:
//
//    Since no teleportation is possible, we move through 0 → 1 → 2 → 3. Thus, the answer is 3.
//
//
//    Constraints:
//
//    1 <= n == nums.length <= 105
//    1 <= nums[i] <= 106