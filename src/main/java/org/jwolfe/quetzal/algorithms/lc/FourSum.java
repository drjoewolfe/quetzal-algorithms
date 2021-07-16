package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class FourSum {
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> results = new ArrayList<>();
            if(nums == null || nums.length < 4) {
                return results;
            }

            Arrays.sort(nums);
            int n = nums.length;

            Set<String> set = new HashSet<>();
            for(int i = 0; i < n - 3; i++) {
                int a = nums[i];

                for(int j = i + 1; j < n - 2; j++) {
                    int b = nums[j];

                    int k = j + 1;
                    int l = n - 1;
                    while(k < l) {
                        int c = nums[k];
                        int d = nums[l];
                        int sum = a + b + c + d;

                        // System.out.println(i + ", " + j + ", " + k + ", " + l);
                        // System.out.println(a + ", " + b + ", " + c + ", " + d + " = " + sum);
                        if(sum == target) {
                            String token = a + "-" + b + "-" + c + "-" + d;
                            if(!set.contains(token)) {
                                set.add(token);

                                List<Integer> res = new ArrayList<>();
                                res.add(a);
                                res.add(b);
                                res.add(c);
                                res.add(d);

                                results.add(res);
                            }

                            k++;
                            l--;
                        } else if(sum < target) {
                            k++;
                        } else {
                            l--;
                        }
                    }

                    System.out.println("exit");
                }
            }

            return results;
        }
    }

    class Solution_N3 {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> results = new ArrayList<>();
            if(nums == null || nums.length < 4) {
                return results;
            }

            int n = nums.length;
            Arrays.sort(nums);
            kSum(nums, 0, target, 4, results, new ArrayList<>());

            return results;
        }

        private void kSum(int[] nums, int index, int target, int k, List<List<Integer>> results, List<Integer> currentCombination) {
            if(k == 0) {
                if(target == 0) {
                    results.add(new ArrayList<>(currentCombination));
                }

                return;
            }

            if(k == 2) {
                twoSum(nums, index, target, k, results, currentCombination);
                return;
            }

            for(int i = index; i < nums.length - k + 1; i++) {
                if(i > index && nums[i] == nums[i - 1]) {
                    continue;
                }

                int a = nums[i];
                currentCombination.add(a);
                kSum(nums, i + 1, target - a, k - 1, results, currentCombination);

                currentCombination.remove(currentCombination.size() - 1);
            }
        }

        private void twoSum(int[] nums, int index, int target, int k, List<List<Integer>> results, List<Integer> currentCombination) {
            int left = index;
            int right = nums.length - 1;

            while(left < right) {
                int sum = nums[left] + nums[right];

                if(sum == target) {
                    var result = new ArrayList<>(currentCombination);
                    result.add(nums[left]);
                    result.add(nums[right]);

                    results.add(result);
                    left++;
                    while(left < nums.length && nums[left] == nums[left - 1]) {
                        left++;
                    }
                } else if(sum > target) {
                    right--;
                    while(right > 0 && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else {
                    left++;
                    while(left < nums.length && nums[left] == nums[left - 1]) {
                        left++;
                    }
                }
            }
        }
    }

    class Solution_N4_Recursive {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> results = new ArrayList<>();
            if(nums == null || nums.length < 4) {
                return results;
            }

            int n = nums.length;
            Arrays.sort(nums);
            kSum(nums, 0, target, 4, results, new ArrayList<>());

            return results;
        }

        private void kSum(int[] nums, int index, int target, int k, List<List<Integer>> results, List<Integer> currentCombination) {
            if(k == 0) {
                if(target == 0) {
                    results.add(new ArrayList<>(currentCombination));
                }

                return;
            }

            for(int i = index; i < nums.length - k + 1; i++) {
                if(i > index && nums[i] == nums[i - 1]) {
                    continue;
                }

                int a = nums[i];
                currentCombination.add(a);
                kSum(nums, i + 1, target - a, k - 1, results, currentCombination);

                currentCombination.remove(currentCombination.size() - 1);
            }
        }
    }

    class Solution_N4 {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> results = new ArrayList<>();
            if(nums == null || nums.length < 4) {
                return results;
            }

            int n = nums.length;
            Arrays.sort(nums);

            for(int i = 0; i < n - 3; i++) {
                if(i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                int a = nums[i];
                for(int j = i + 1; j < n - 2; j++) {
                    if(j > i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }

                    int b = nums[j];
                    for(int k = j + 1; k < n - 1; k++) {
                        if(k > j + 1 && nums[k] == nums[k - 1]) {
                            continue;
                        }

                        int c = nums[k];
                        for(int l = k + 1; l < n; l++) {
                            if(l > k + 1 && nums[l] == nums[l - 1]) {
                                continue;
                            }

                            int d = nums[l];
                            if(a + b + c + d == target) {
                                results.add(Arrays.asList(a, b, c, d));
                            }
                        }
                    }
                }
            }

            return results;
        }
    }

    class Solution_Brute {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> results = new ArrayList<>();
            if(nums == null || nums.length < 4) {
                return results;
            }

            Arrays.sort(nums);
            int n = nums.length;
            Set<String> set = new HashSet<>();
            for(int i = 0; i < n - 3; i++) {
                int a = nums[i];
                for(int j = i + 1; j < n - 2; j++) {
                    int b = nums[j];
                    for(int k = j + 1; k < n - 1; k++) {
                        int c = nums[k];
                        for(int l = k + 1; l < n; l++) {
                            int d = nums[l];
                            if(a + b + c + d == target) {
                                String token = "Token-" + a + "-" + b + "-" + c + "-" + d;
                                if(set.contains(token)) {
                                    continue;
                                }

                                set.add(token);
                                List<Integer> res = new ArrayList<>();
                                res.add(a);
                                res.add(b);
                                res.add(c);
                                res.add(d);

                                results.add(res);
                            }
                        }
                    }
                }
            }

            return results;
        }
    }

// [1,0,-1,0,-2,2]
// 0

// [-3,-1,0,2,4,5]
// 0


// [0,0,0,0]
// 0
}

//    18. 4Sum
//    Medium
//
//    Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
//
//    0 <= a, b, c, d < n
//    a, b, c, and d are distinct.
//    nums[a] + nums[b] + nums[c] + nums[d] == target
//    You may return the answer in any order.
//
//
//
//    Example 1:
//
//    Input: nums = [1,0,-1,0,-2,2], target = 0
//    Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//    Example 2:
//
//    Input: nums = [2,2,2,2,2], target = 8
//    Output: [[2,2,2,2]]
//
//
//    Constraints:
//
//    1 <= nums.length <= 200
//    -109 <= nums[i] <= 109
//    -109 <= target <= 109
