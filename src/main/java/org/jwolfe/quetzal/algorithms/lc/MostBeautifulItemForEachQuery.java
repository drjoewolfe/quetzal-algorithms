package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MostBeautifulItemForEachQuery {
    class Solution {
        public int[] maximumBeauty(int[][] items, int[] queries) {
            if (items == null || items.length == 0 || queries == null || queries.length == 0) {
                return new int[0];
            }

            int n = queries.length;
            int[] results = new int[n];

            Arrays.sort(items, (a, b) -> a[0] - b[0]);
            int maxBeautyTillNow = items[0][1];
            for (int i = 0; i < items.length; i++) {
                maxBeautyTillNow = Math.max(maxBeautyTillNow, items[i][1]);
                items[i][1] = maxBeautyTillNow;
            }

            for (int i = 0; i < n; i++) {
                results[i] = binarySearch(items, queries[i]);
            }

            return results;
        }

        private int binarySearch(int[][] items, int targetPrice) {
            int left = 0;
            int right = items.length - 1;
            int maxBeauty = 0;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int price = items[mid][0];

                if (price > targetPrice) {
                    right = mid - 1;
                } else {
                    int beauty = items[mid][1];
                    maxBeauty = Math.max(maxBeauty, beauty);
                    left = mid + 1;
                }
            }

            return maxBeauty;
        }
    }

// [[1,2],[3,2],[2,4],[5,6],[3,5]]
// [1,2,3,4,5,6]

// [[193,732],[781,962],[864,954],[749,627],[136,746],[478,548],[640,908],[210,799],[567,715],[914,388],[487,853],[533,554],[247,919],[958,150],[193,523],[176,656],[395,469],[763,821],[542,946],[701,676]]
// [885,1445,1580,1309,205,1788,1214,1404,572,1170,989,265,153,151,1479,1180,875,276,1584]
}

//    2070. Most Beautiful Item for Each Query
//    Medium
//    You are given a 2D integer array items where items[i] = [pricei, beautyi] denotes the price and beauty of an item respectively.
//
//    You are also given a 0-indexed integer array queries. For each queries[j], you want to determine the maximum beauty of an item whose price is less than or equal to queries[j]. If no such item exists, then the answer to this query is 0.
//
//    Return an array answer of the same length as queries where answer[j] is the answer to the jth query.
//
//
//
//    Example 1:
//
//    Input: items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
//    Output: [2,4,5,5,6,6]
//    Explanation:
//    - For queries[0]=1, [1,2] is the only item which has price <= 1. Hence, the answer for this query is 2.
//    - For queries[1]=2, the items which can be considered are [1,2] and [2,4].
//    The maximum beauty among them is 4.
//    - For queries[2]=3 and queries[3]=4, the items which can be considered are [1,2], [3,2], [2,4], and [3,5].
//    The maximum beauty among them is 5.
//    - For queries[4]=5 and queries[5]=6, all items can be considered.
//    Hence, the answer for them is the maximum beauty of all items, i.e., 6.
//    Example 2:
//
//    Input: items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
//    Output: [4]
//    Explanation:
//    The price of every item is equal to 1, so we choose the item with the maximum beauty 4.
//    Note that multiple items can have the same price and/or beauty.
//    Example 3:
//
//    Input: items = [[10,1000]], queries = [5]
//    Output: [0]
//    Explanation:
//    No item has a price less than or equal to 5, so no item can be chosen.
//    Hence, the answer to the query is 0.
//
//
//    Constraints:
//
//    1 <= items.length, queries.length <= 105
//    items[i].length == 2
//    1 <= pricei, beautyi, queries[j] <= 109