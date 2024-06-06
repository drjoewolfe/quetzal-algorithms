package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HandOfStraights {
    class Solution {
        public boolean isNStraightHand(int[] hand, int W) {
            if(hand == null || hand.length == 0 || W < 1) {
                return false;
            }

            int n = hand.length;
            if(n % W != 0) {
                return false;
            }

            Map<Integer, Integer> map = new HashMap<>();
            for(int card : hand) {
                map.put(card, map.getOrDefault(card, 0) + 1);
            }

            for(int card : hand) {
                int startCard = card;

                while(map.getOrDefault(startCard - 1, 0) > 0) {
                    startCard--;
                }

                while(startCard <= card) {

                    while(map.getOrDefault(startCard, 0) > 0) {

                        for(int nextCard = startCard; nextCard < startCard + W; nextCard++) {
                            if(map.getOrDefault(nextCard, 0) == 0) {
                                return false;
                            }

                            map.put(nextCard, map.get(nextCard) - 1);
                        }

                    }

                    startCard++;
                }

            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public boolean isNStraightHand(int[] hand, int W) {
            if(hand == null || hand.length == 0) {
                return false;
            }

            if(hand.length % W != 0) {
                return false;
            }

            TreeMap<Integer, Integer> countMap = new TreeMap<>();
            for(int i = 0; i < hand.length; i++) {
                countMap.put(hand[i], countMap.getOrDefault(hand[i], 0) + 1);
            }

            while(countMap.size() > 0) {
                int cardNumber = countMap.firstKey();
                for(int i = cardNumber; i < cardNumber + W; i++) {
                    if(!countMap.containsKey(i)) {
                        return false;
                    }

                    int count = countMap.get(i);
                    count--;

                    if(count > 0) {
                        countMap.replace(i, count);
                    } else {
                        countMap.remove(i);
                    }
                }
            }

            return true;
        }
    }
}

//    846. Hand of Straights
//    Medium
//    Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize, and consists of groupSize consecutive cards.
//
//    Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize, return true if she can rearrange the cards, or false otherwise.
//
//
//
//    Example 1:
//
//    Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
//    Output: true
//    Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
//    Example 2:
//
//    Input: hand = [1,2,3,4,5], groupSize = 4
//    Output: false
//    Explanation: Alice's hand can not be rearranged into groups of 4.
//
//
//
//    Constraints:
//
//    1 <= hand.length <= 104
//    0 <= hand[i] <= 109
//    1 <= groupSize <= hand.length
//
//
//    Note: This question is the same as 1296: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/