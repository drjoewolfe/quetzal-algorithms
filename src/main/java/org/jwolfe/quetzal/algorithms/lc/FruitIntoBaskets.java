package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {
    class Solution {
        public int totalFruit(int[] fruits) {
            if (fruits == null) {
                return 0;
            }

            int n = fruits.length;
            if (n < 3) {
                return n;
            }

            int maxLength = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int left = 0, right = 0; right < n; right++) {
                int rightType = fruits[right];
                map.put(rightType, map.getOrDefault(rightType, 0) + 1);

                while (map.size() > 2) {
                    int leftType = fruits[left];
                    map.put(leftType, map.get(leftType) - 1);
                    if (map.get(leftType) == 0) {
                        map.remove(leftType);
                    }

                    left++;
                }

                int length = right - left + 1;
                maxLength = Math.max(maxLength, length);
            }

            return maxLength;
        }
    }

    class Solution_Correct_2 {
        public int totalFruit(int[] tree) {
            if (tree == null || tree.length == 0) {
                return 0;
            }

            int n = tree.length;
            if (n < 3) {
                return n;
            }

            Map<Integer, Integer> map = new HashMap<>();
            map.put(tree[0], 1);

            int maxLength = 2;

            int left = 0;
            for (int right = 1; right < n; right++) {
                int type = tree[right];
                map.put(type, map.getOrDefault(type, 0) + 1);

                while (map.size() > 2 && left < right) {
                    int prevType = tree[left++];
                    if (map.get(prevType) == 1) {
                        map.remove(prevType);
                    } else {
                        map.put(prevType, map.get(prevType) - 1);
                    }
                }

                maxLength = Math.max(maxLength, right - left + 1);
            }

            return maxLength;
        }
    }

    class Solution_Correct_1 {
        public int totalFruit(int[] tree) {
            if (tree == null) {
                return 0;
            }

            int firstType = -1;
            int secondType = -1;
            int leftAnchor = 0;
            int maxLength = 0;

            for (int i = 0; i < tree.length; i++) {
                int type = tree[i];

                if (type == firstType || type == secondType) {
                    continue;
                }

                if (firstType == -1) {
                    firstType = type;
                } else if (secondType == -1) {
                    secondType = type;
                } else {
                    int length = i - leftAnchor;
                    maxLength = Math.max(maxLength, length);
                    if (tree[i - 1] == firstType) {
                        secondType = type;
                    } else {
                        firstType = type;
                    }

                    int j = i;
                    while (j > 0 && (tree[j - 1] == firstType || tree[j - 1] == secondType)) {
                        j--;
                    }

                    leftAnchor = j;
                }
            }

            maxLength = Math.max(maxLength, tree.length - leftAnchor);

            return maxLength;
        }
    }

// [1,2,1]
// [1,2,3,2,2]
}

//    904. Fruit Into Baskets
//    Medium
//    You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
//
//    You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
//
//    You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
//    Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
//    Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
//    Given the integer array fruits, return the maximum number of fruits you can pick.
//
//
//
//    Example 1:
//
//    Input: fruits = [1,2,1]
//    Output: 3
//    Explanation: We can pick from all 3 trees.
//    Example 2:
//
//    Input: fruits = [0,1,2,2]
//    Output: 3
//    Explanation: We can pick from trees [1,2,2].
//    If we had started at the first tree, we would only pick from trees [0,1].
//    Example 3:
//
//    Input: fruits = [1,2,3,2,2]
//    Output: 4
//    Explanation: We can pick from trees [2,3,2,2].
//    If we had started at the first tree, we would only pick from trees [1,2].
//
//
//    Constraints:
//
//    1 <= fruits.length <= 105
//    0 <= fruits[i] < fruits.length