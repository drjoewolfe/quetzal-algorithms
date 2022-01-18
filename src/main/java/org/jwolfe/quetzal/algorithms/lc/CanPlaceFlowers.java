package org.jwolfe.quetzal.algorithms.lc;

public class CanPlaceFlowers {
    class Solution {
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            if(n == 0) {
                return true;
            }

            if(flowerbed == null || flowerbed.length < n) {
                return false;
            }

            for(int i = 0; i < flowerbed.length; i++) {
                if(flowerbed[i] == 1) {
                    continue;
                }

                if(canPlace(flowerbed, i)) {
                    flowerbed[i] = 1;
                    n--;

                    if(n == 0) {
                        return true;
                    }
                }
            }

            if(n == 0) {
                return true;
            }

            return false;
        }

        private boolean canPlace(int[] flowerbed, int i) {
            if(i == 0) {
                return (i == flowerbed.length - 1) || flowerbed[i + 1] == 0;
            }

            if(i == flowerbed.length - 1) {
                return flowerbed[i - 1] == 0;
            }

            return flowerbed[i - 1] == 0
                    && flowerbed[i + 1] == 0;
        }
    }

    class Solution_Correct_1 {
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            if(flowerbed == null) {
                return false;
            }

            if(n == 0) {
                return true;
            }

            int m = flowerbed.length;
            if(m < n) {
                return false;
            }

            int validFlowerPots = 0;

            int i = 0;
            while(i < m) {
                if(flowerbed[i] == 0
                        && (i == 0 || flowerbed[i - 1] == 0)
                        && (i == m - 1 || flowerbed[i + 1] == 0)) {
                    flowerbed[i] = 1;
                    validFlowerPots++;
                }

                if(validFlowerPots >= n) {
                    return true;
                }


                i++;
            }

            return false;
        }
    }


    class Solution_Old {
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            if(n == 0) {
                return true;
            }

            if(flowerbed == null || flowerbed.length == 0) {
                return false;
            }

            if(flowerbed.length == 1 && flowerbed[0] == 0 && n == 1) {
                return true;
            }

            int consecutiveEmptyPots = 1;
            for(int i = 0; i < flowerbed.length; i++) {
                if(flowerbed[i] == 0) {
                    consecutiveEmptyPots++;
                } else {
                    consecutiveEmptyPots = 0;
                }

                if(consecutiveEmptyPots >= 3) {
                    n--;
                    consecutiveEmptyPots = 1;
                }
            }

            if(consecutiveEmptyPots >= 2) {
                n--;
            }

            return n <= 0;
        }
    }

// [1,0,0,0,1]
// 1
}

//    605. Can Place Flowers
//    Easy
//    You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
//
//    Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.
//
//
//
//    Example 1:
//
//    Input: flowerbed = [1,0,0,0,1], n = 1
//    Output: true
//    Example 2:
//
//    Input: flowerbed = [1,0,0,0,1], n = 2
//    Output: false
//
//
//    Constraints:
//
//    1 <= flowerbed.length <= 2 * 104
//    flowerbed[i] is 0 or 1.
//    There are no two adjacent flowers in flowerbed.
//    0 <= n <= flowerbed.length
