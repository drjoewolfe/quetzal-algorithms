package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumTastinessOfCandyBasket {
    class Solution {
        public int maximumTastiness(int[] price, int k) {
            if(price == null || price.length < k) {
                return 0;
            }

            Arrays.sort(price);
            int n = price.length;

            int left = 0;
            int right = price[n - 1] - price[0];

            while(right - left > 1) {
                int mid = left + (right - left) / 2;
                int count = countForSequenceElementsWithDiff(price, mid);

                if(count >= k) {
                    // there are k or more elements with diff of mid
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }

            if(countForSequenceElementsWithDiff(price, right) >= k) {
                return right;
            }

            return left;
        }

        private int countForSequenceElementsWithDiff(int[] arr, int diff) {
            int last = arr[0];
            int count = 1;
            for(int i = 1; i < arr.length; i++) {
                if(arr[i] - last >= diff) {
                    last = arr[i];
                    count++;
                }
            }

            return count;
        }
    }

    class Solution_TLE {
        public int maximumTastiness(int[] price, int k) {
            if(price == null || price.length < k) {
                return 0;
            }

            return maximumTastiness(price, 0, k, new ArrayList<>());
        }

        private int maximumTastiness(int[] price, int index, int k, List<Integer> basket) {
            if(k == 0) {
                int tastiness = Integer.MAX_VALUE;
                for(int i = 0; i < basket.size() - 1; i++) {
                    for(int j = i + 1; j < basket.size(); j++) {
                        tastiness = Math.min(tastiness, Math.abs(basket.get(i) - basket.get(j)));
                    }
                }

                return tastiness;
            }

            if(index == price.length
                    || index + k > price.length) {
                return 0;
            }

            // Include this candy
            basket.add(price[index]);
            int tastiness = maximumTastiness(price, index + 1, k - 1, basket);
            basket.remove(basket.size() - 1);

            // Exclude this candy
            tastiness = Math.max(tastiness,
                    maximumTastiness(price, index + 1, k, basket));

            return tastiness;
        }
    }

// [13,5,1,8,21,2]
// 3

// [1,3,1]
// 2

// [7,7,7,7]
// 2

// [144,69,103,148,184,50,129,154,2]
// 4
}

//    2517. Maximum Tastiness of Candy Basket
//    Medium
//    You are given an array of positive integers price where price[i] denotes the price of the ith candy and a positive integer k.
//
//    The store sells baskets of k distinct candies. The tastiness of a candy basket is the smallest absolute difference of the prices of any two candies in the basket.
//
//    Return the maximum tastiness of a candy basket.
//
//
//
//    Example 1:
//
//    Input: price = [13,5,1,8,21,2], k = 3
//    Output: 8
//    Explanation: Choose the candies with the prices [13,5,21].
//    The tastiness of the candy basket is: min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8.
//    It can be proven that 8 is the maximum tastiness that can be achieved.
//    Example 2:
//
//    Input: price = [1,3,1], k = 2
//    Output: 2
//    Explanation: Choose the candies with the prices [1,3].
//    The tastiness of the candy basket is: min(|1 - 3|) = min(2) = 2.
//    It can be proven that 2 is the maximum tastiness that can be achieved.
//    Example 3:
//
//    Input: price = [7,7,7,7], k = 2
//    Output: 0
//    Explanation: Choosing any two distinct candies from the candies we have will result in a tastiness of 0.
//
//
//    Constraints:
//
//    1 <= price.length <= 105
//    1 <= price[i] <= 109
//    2 <= k <= price.length