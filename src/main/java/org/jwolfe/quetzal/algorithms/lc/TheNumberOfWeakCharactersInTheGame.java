package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class TheNumberOfWeakCharactersInTheGame {
    class Solution {
        public int numberOfWeakCharacters(int[][] properties) {
            if(properties == null || properties.length == 0) {
                return 0;
            }

            int count = 0;

            Arrays.sort(properties, (a, b) -> {
                if(a[0] == b[0]) {
                    return b[1] - a[1];
                }

                return a[0] - b[0];
            });

            int maxDefenceTillNow = Integer.MIN_VALUE;

            int n = properties.length;
            for(int i = n - 1; i >= 0; i--) {
                int[] character = properties[i];
                if(character[1] < maxDefenceTillNow) {
                    count++;
                } else {
                    maxDefenceTillNow = character[1];
                }
            }

            return count;
        }
    }

    class Solution_Brute {
        public int numberOfWeakCharacters(int[][] properties) {
            if(properties == null || properties.length == 0) {
                return 0;
            }

            int count = 0;
            for(int i = 0; i < properties.length; i++) {
                int[] character1 = properties[i];

                boolean isWeak = false;
                for(int j = 0; j < properties.length; j++) {
                    if(i == j) {
                        continue;
                    }

                    int[] character2 = properties[j];
                    if(character1[0] < character2[0] && character1[1] < character2[1]) {
                        isWeak = true;
                        break;
                    }
                }

                if(isWeak) {
                    count++;
                }
            }

            return count;
        }
    }

// [[5,5],[6,3],[3,6]]
// [[1,5],[10,4],[4,3]]
}

//    1996. The Number of Weak Characters in the Game
//    Medium
//    You are playing a game that contains multiple characters, and each of the characters has two main properties: attack and defense. You are given a 2D integer array properties where properties[i] = [attacki, defensei] represents the properties of the ith character in the game.
//
//    A character is said to be weak if any other character has both attack and defense levels strictly greater than this character's attack and defense levels. More formally, a character i is said to be weak if there exists another character j where attackj > attacki and defensej > defensei.
//
//    Return the number of weak characters.
//
//
//
//    Example 1:
//
//    Input: properties = [[5,5],[6,3],[3,6]]
//    Output: 0
//    Explanation: No character has strictly greater attack and defense than the other.
//    Example 2:
//
//    Input: properties = [[2,2],[3,3]]
//    Output: 1
//    Explanation: The first character is weak because the second character has a strictly greater attack and defense.
//    Example 3:
//
//    Input: properties = [[1,5],[10,4],[4,3]]
//    Output: 1
//    Explanation: The third character is weak because the second character has a strictly greater attack and defense.
//
//
//    Constraints:
//
//    2 <= properties.length <= 105
//    properties[i].length == 2
//    1 <= attacki, defensei <= 105