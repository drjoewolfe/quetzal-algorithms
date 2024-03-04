package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BagOfTokens {
    class Solution {
        public int bagOfTokensScore(int[] tokens, int P) {
            if(tokens == null || tokens.length == 0 || P < 0) {
                return 0;
            }

            int score = 0;
            int maxScore = 0;

            Arrays.sort(tokens);
            int low = 0;
            int high = tokens.length - 1;
            int power = P;

            while(low <= high) {
                // Face-up
                while(low <= high && power >= tokens[low]) {
                    power -= tokens[low];

                    score++;
                    low++;
                }

                maxScore = Math.max(maxScore, score);

                // Face-down
                if(low <= high && score > 0) {
                    power += tokens[high];
                    score--;
                    high--;
                } else {
                    break;
                }
            }

            return maxScore;
        }
    }

    class Solution_Correct_1 {
        public int bagOfTokensScore(int[] tokens, int P) {
            if(tokens == null || tokens.length == 0 || P < 0) {
                return 0;
            }

            Arrays.sort(tokens);

            int maxScore = 0;

            int low = 0;
            int high = tokens.length - 1;

            int power = P;
            int score = 0;

            // Greedy
            while(low <= high) {
                // Up
                while(low <= high && power >= tokens[low]) {
                    power -= tokens[low];
                    score++;
                    low++;
                }

                maxScore = Math.max(maxScore, score);

                // Down
                if(low <= high && score > 0) {
                    power += tokens[high];
                    score--;
                    high--;
                } else {
                    break;
                }
            }

            return maxScore;
        }
    }

    class Solution_TLE {
        int maxScore;

        public int bagOfTokensScore(int[] tokens, int P) {
            if(tokens == null || tokens.length == 0 || P < 0) {
                return 0;
            }

            Set<Integer> tokensLeft = new HashSet<>();
            for(int i = 0; i < tokens.length; i++) {
                tokensLeft.add(i);
            }

            maxScore = 0;
            play(tokens, P, 0, tokensLeft);

            return maxScore;
        }

        private void play(int[] tokens, int power, int score, Set<Integer> tokensLeft) {
            if(score > maxScore) {
                maxScore = score;
            }

            if(tokensLeft.size() == 0) {
                return;
            }

            // "Up"
            Set<Integer> powerTokens = getPowerTokens(tokens, tokensLeft, power);
            for(int i : powerTokens) {
                tokensLeft.remove(i);
                play(tokens, power - tokens[i], score + 1, tokensLeft);

                tokensLeft.add(i);
            }

            // "Down"
            if(score > 0) {
                for(int i : new HashSet<>(tokensLeft)) {
                    tokensLeft.remove(i);
                    play(tokens, power + tokens[i], score - 1, tokensLeft);

                    tokensLeft.add(i);
                }
            }
        }

        private Set<Integer> getPowerTokens(int[] tokens, Set<Integer> tokensLeft, int power) {
            Set<Integer> powerTokens = new HashSet<>();
            for(int i : tokensLeft) {
                if(tokens[i] <= power) {
                    powerTokens.add(i);
                }
            }

            return powerTokens;
        }
    }

    class Solution_Incorrect {
        public int bagOfTokensScore(int[] tokens, int P) {
            if(tokens == null || tokens.length == 0) {
                return 0;
            }

            int score = 0;
            int maxScore = 0;

            while(true) {
                int minTokenIndex = getMinTokenIndexLessThanOrEqualToPower(tokens, P);
                if(minTokenIndex != -1) {

                    int token = tokens[minTokenIndex];
                    tokens[minTokenIndex] = -1;

                    P -= token;
                    score++;
                    maxScore = Math.max(maxScore, score);
                } else {
                    int maxTokenIndex = getMaxTokenIndex(tokens);
                    if(maxTokenIndex == -1) {
                        break;
                    }

                    int token = tokens[maxTokenIndex];
                    tokens[maxTokenIndex] = -1;

                    P += token;
                    score--;
                }
            }

            return maxScore;
        }

        private int getMaxTokenIndex(int[] tokens) {
            int max = Integer.MIN_VALUE;
            int maxIndex = -1;

            for(int i = 0; i < tokens.length; i++) {
                if(tokens[i] == -1) {
                    continue;
                }

                if(tokens[i] > max) {
                    max = tokens[i];
                    maxIndex = i;
                }
            }

            return maxIndex;
        }

        private int getMinTokenIndexLessThanOrEqualToPower(int[] tokens, int power) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;

            for(int i = 0; i < tokens.length; i++) {
                if(tokens[i] == -1 || tokens[i] > power) {
                    continue;
                }

                if(min > tokens[i]) {
                    min = tokens[i];
                    minIndex = i;
                }
            }

            return minIndex;
        }
    }

// [100]
// 50

// [100,200]
// 150

// [100,200,300,400]
// 200

// [52,65,35,88,28,1,4,68,56,95]
// 94
}

//    948. Bag of Tokens
//    Medium
//    You have an initial power of power, an initial score of 0, and a bag of tokens where tokens[i] is the value of the ith token (0-indexed).
//
//    Your goal is to maximize your total score by potentially playing each token in one of two ways:
//
//    If your current power is at least tokens[i], you may play the ith token face up, losing tokens[i] power and gaining 1 score.
//    If your current score is at least 1, you may play the ith token face down, gaining tokens[i] power and losing 1 score.
//    Each token may be played at most once and in any order. You do not have to play all the tokens.
//
//    Return the largest possible score you can achieve after playing any number of tokens.
//
//
//
//    Example 1:
//
//    Input: tokens = [100], power = 50
//    Output: 0
//    Explanation: Playing the only token in the bag is impossible because you either have too little power or too little score.
//    Example 2:
//
//    Input: tokens = [100,200], power = 150
//    Output: 1
//    Explanation: Play the 0th token (100) face up, your power becomes 50 and score becomes 1.
//    There is no need to play the 1st token since you cannot play it face up to add to your score.
//    Example 3:
//
//    Input: tokens = [100,200,300,400], power = 200
//    Output: 2
//    Explanation: Play the tokens in this order to get a score of 2:
//    1. Play the 0th token (100) face up, your power becomes 100 and score becomes 1.
//    2. Play the 3rd token (400) face down, your power becomes 500 and score becomes 0.
//    3. Play the 1st token (200) face up, your power becomes 300 and score becomes 1.
//    4. Play the 2nd token (300) face up, your power becomes 0 and score becomes 2.
//
//
//    Constraints:
//
//    0 <= tokens.length <= 1000
//    0 <= tokens[i], power < 104