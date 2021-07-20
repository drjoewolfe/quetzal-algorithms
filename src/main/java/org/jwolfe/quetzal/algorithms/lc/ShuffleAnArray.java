package org.jwolfe.quetzal.algorithms.lc;

import java.util.Random;

public class ShuffleAnArray {
    class Solution {
        // Fisher Yates

        int[] original;
        int[] array;

        Random random = new Random();

        public Solution(int[] nums) {
            original = nums.clone();
            array = original.clone();
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            array = original.clone();
            return original;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            int length = array.length;
            for(int i = 0; i < length; i++) {
                int index = getRandomIndex(i, length);
                swap(array, i, index);
            }

            return array;
        }

        private int getRandomIndex(int min, int max) {
            return min + random.nextInt(max - min);
        }

        private void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(nums);
     * int[] param_1 = obj.reset();
     * int[] param_2 = obj.shuffle();
     */

    class Solution_Classic {
        int[] original;
        int[] array;

        public Solution_Classic(int[] nums) {
            original = nums;
            array = original.clone();
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            array = original.clone();
            return array;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            int length = array.length;
            Random random = new Random();

            int[] shuffled = new int[length];
            boolean[] indexes = new boolean[length];
            for(int i = 0; i < length; i++) {
                int index = random.nextInt(length);

                while(indexes[index]) {
                    index = (index + 1) % length;
                }

                indexes[index] = true;
                shuffled[index] = array[i];
            }

            array = shuffled;
            return array;
        }
    }
}

//    384. Shuffle an Array
//    Medium
//    Given an integer array nums, design an algorithm to randomly shuffle the array. All permutations of the array should be equally likely as a result of the shuffling.
//
//    Implement the Solution class:
//
//    Solution(int[] nums) Initializes the object with the integer array nums.
//    int[] reset() Resets the array to its original configuration and returns it.
//    int[] shuffle() Returns a random shuffling of the array.
//
//
//    Example 1:
//
//    Input
//    ["Solution", "shuffle", "reset", "shuffle"]
//    [[[1, 2, 3]], [], [], []]
//    Output
//    [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
//
//    Explanation
//    Solution solution = new Solution([1, 2, 3]);
//    solution.shuffle();    // Shuffle the array [1,2,3] and return its result.
//    // Any permutation of [1,2,3] must be equally likely to be returned.
//    // Example: return [3, 1, 2]
//    solution.reset();      // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
//    solution.shuffle();    // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]
//
//
//
//    Constraints:
//
//    1 <= nums.length <= 200
//    -106 <= nums[i] <= 106
//    All the elements of nums are unique.
//    At most 5 * 104 calls in total will be made to reset and shuffle.