package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class SuccessfulPairsOfSpellsAndPotions {
    class Solution_Correct_2 {
        class Solution {
            public int[] successfulPairs(int[] spells, int[] potions, long success) {
                if (spells == null || spells.length == 0 || potions == null || potions.length == 0 || success < 1) {
                    return new int[0];
                }

                Arrays.sort(potions);
                int n = spells.length;
                int m = potions.length;

                int[] ans = new int[n];
                for (int i = 0; i < n; i++) {
                    int spell = spells[i];
                    int potionIndex = getFirstPotionForSuccess(potions, spell, success);
                    if (potionIndex > -1) {
                        int potionCount = m - potionIndex;
                        ans[i] = potionCount;
                    }
                }

                return ans;
            }

            private int getFirstPotionForSuccess(int[] potions, int spell, long success) {
                int index = -1;
                int left = 0;
                int right = potions.length - 1;

                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    int potion = potions[mid];
                    long product = 1L * spell * potion;
                    if (product >= success) {
                        index = mid;
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }

                return index;
            }
        }

        public int[] successfulPairs(int[] spells, int[] potions, long success) {
            if (spells == null || potions == null || spells.length == 0 || potions.length == 0 || success < 1) {
                return new int[0];
            }

            int n = spells.length;
            int[] pairs = new int[n];

            Arrays.sort(potions);
            for (int i = 0; i < n; i++) {
                int count = binarySearch(potions, spells[i], success);
                pairs[i] = count;
            }

            return pairs;
        }

        private int binarySearch(int[] potions, int spell, long success) {
            int n = potions.length;

            int left = 0;
            int right = n - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                int potion = potions[mid];
                long product = 1L * spell * potion;

                if (product >= success) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return n - left;
        }
    }

    class Solution_Correct_1 {
        public int[] successfulPairs(int[] spells, int[] potions, long success) {
            if (spells == null || spells.length == 0 || potions == null || potions.length == 0 || success < 1) {
                return new int[0];
            }

            Arrays.sort(potions);

            int n = spells.length;
            int m = potions.length;

            int[] results = new int[n];

            for (int i = 0; i < n; i++) {
                double target = 1d * success / spells[i];
                int index = binarySearchForEqualOrGreater(potions, target);

                results[i] = m - index;
            }

            return results;
        }

        private int binarySearchForEqualOrGreater(int[] arr, double target) {
            int left = 0;
            int right = arr.length - 1;

            int index = arr.length;
            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (arr[mid] >= target) {
                    index = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return index;
        }
    }

// [5,1,3]
// [1,2,3,4,5]
// 7

// [1,2,3,4,5,6,7]
// [1,2,3,4,5,6,7]
// 25
}

//    2300. Successful Pairs of Spells and Potions
//    Medium
//    You are given two positive integer arrays spells and potions, of length n and m respectively, where spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.
//
//    You are also given an integer success. A spell and potion pair is considered successful if the product of their strengths is at least success.
//
//    Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.
//
//
//
//    Example 1:
//
//    Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
//    Output: [4,0,3]
//    Explanation:
//    - 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
//    - 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
//    - 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
//    Thus, [4,0,3] is returned.
//    Example 2:
//
//    Input: spells = [3,1,2], potions = [8,5,8], success = 16
//    Output: [2,0,2]
//    Explanation:
//    - 0th spell: 3 * [8,5,8] = [24,15,24]. 2 pairs are successful.
//    - 1st spell: 1 * [8,5,8] = [8,5,8]. 0 pairs are successful.
//    - 2nd spell: 2 * [8,5,8] = [16,10,16]. 2 pairs are successful.
//    Thus, [2,0,2] is returned.
//
//
//    Constraints:
//
//    n == spells.length
//    m == potions.length
//    1 <= n, m <= 105
//    1 <= spells[i], potions[i] <= 105
//    1 <= success <= 1010