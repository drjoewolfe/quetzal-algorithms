package org.jwolfe.quetzal.algorithms.lc;

public class KokoEatingBananas {
    class Solution {
        public int minEatingSpeed(int[] piles, int H) {
            if(piles == null || piles.length == 0 || piles.length > H || H < 1) {
                return 0;
            }

            int low = 1;
            int high = 1;

            for(int pile : piles) {
                high = Math.max(high, pile);
            }

            while(low < high) {
                int mid = low + (high - low) / 2;

                if(!canEatInHours(piles, H, mid)) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            return low;
        }

        private boolean canEatInHours(int[] piles, int H, int speed) {
            int hours = 0;
            for(int pile : piles) {
                hours += ((pile - 1) / speed) + 1;
            }

            return hours <= H;
        }
    }

    class Solution_Correct_1 {
        public int minEatingSpeed(int[] piles, int H) {
            if(piles == null || piles.length > H) {
                return 0;
            }

            int n = piles.length;

            int low = 1;
            int high = Integer.MAX_VALUE;

            while(low < high) {
                int mid = low + (high - low) / 2;

                if(!canEat(piles, H, mid)) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            return low;
        }

        private boolean canEat(int[] piles, int H, int K) {
            int time = 0;
            for(int p : piles) {
                time += (p - 1) / K + 1;
            }

            return time <= H;
        }
    }

// [3,6,7,11]
// 8
}

//    875. Koko Eating Bananas
//    Medium
//    Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
//
//    Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
//
//    Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
//
//    Return the minimum integer k such that she can eat all the bananas within h hours.
//
//
//
//    Example 1:
//
//    Input: piles = [3,6,7,11], h = 8
//    Output: 4
//    Example 2:
//
//    Input: piles = [30,11,23,4,20], h = 5
//    Output: 30
//    Example 3:
//
//    Input: piles = [30,11,23,4,20], h = 6
//    Output: 23
//
//
//    Constraints:
//
//    1 <= piles.length <= 104
//    piles.length <= h <= 109
//    1 <= piles[i] <= 109