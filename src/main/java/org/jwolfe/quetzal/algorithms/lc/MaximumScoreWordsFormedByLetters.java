package org.jwolfe.quetzal.algorithms.lc;

public class MaximumScoreWordsFormedByLetters {
    class Solution {
        int maxScore = 0;

        public int maxScoreWords(String[] words, char[] letters, int[] score) {
            if(words == null || words.length == 0 || letters == null || letters.length == 0 || score == null || score.length != 26) {
                return 0;
            }

            maxScore = 0;
            int[] letterFrequencies = getFrequencies(letters);
            maxScoreWords(words, 0, letterFrequencies, score, 0);

            return maxScore;
        }

        private void maxScoreWords(String[] words, int index, int[] letterFrequencies, int[] score, int currentScore) {
            if(index == words.length) {
                maxScore = Math.max(maxScore, currentScore);
                return;
            }

            String word = words[index];
            int[] wordFrequencies = getFrequencies(word);

            // Exclude
            maxScoreWords(words, index + 1, letterFrequencies, score, currentScore);

            // Include
            if(!isValid(wordFrequencies, letterFrequencies)) {
                return;
            }

            int wordScore = getWordScore(wordFrequencies, score);
            removeFrequencies(letterFrequencies, wordFrequencies);
            maxScoreWords(words, index + 1, letterFrequencies, score, currentScore + wordScore);
            addFrequencies(letterFrequencies, wordFrequencies);
        }

        private boolean isValid(int[] wordFrequencies, int[] letterFrequencies) {
            for(int i = 0; i < 26; i++) {
                if(wordFrequencies[i] > letterFrequencies[i]) {
                    return false;
                }
            }

            return true;
        }

        private int getWordScore(int[] wordFrequencies, int[] score) {
            int wordScore = 0;
            for(int i = 0; i < 26; i++) {
                wordScore += (wordFrequencies[i] * score[i]);
            }

            return wordScore;
        }

        private void removeFrequencies(int[] frequencies, int[] frequenciesToRemove) {
            for(int i = 0; i < 26; i++) {
                frequencies[i] -= frequenciesToRemove[i];
            }
        }

        private void addFrequencies(int[] frequencies, int[] frequenciesToAdd) {
            for(int i = 0; i < 26; i++) {
                frequencies[i] += frequenciesToAdd[i];
            }
        }

        private int[] getFrequencies(String word) {
            int[] frequencies = new int[26];
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                frequencies[index]++;
            }

            return frequencies;
        }

        private int[] getFrequencies(char[] letters) {
            int[] frequencies = new int[26];
            for(char c : letters) {
                int index = c - 'a';
                frequencies[index]++;
            }

            return frequencies;
        }

        private void print(int[] arr) {
            for(int val : arr) {
                System.out.print(val + " ");
            }

            System.out.println();
        }
    }

    class Solution_Incorrect {
        public int maxScoreWords(String[] words, char[] letters, int[] score) {
            if(words == null || words.length == 0 || letters == null || letters.length == 0 || score == null || score.length != 26) {
                return 0;
            }

            int maxScore = 0;
            int[] letterFrequencies = getFrequencies(letters);
            for(String word : words) {
                int[] wordFrequencies = getFrequencies(word);
                if(isValid(wordFrequencies, letterFrequencies)) {
                    int wordScore = 0;
                    for(int i = 0; i < 26; i++) {
                        wordScore += (wordFrequencies[i] * score[i]);
                    }

                    maxScore = Math.max(maxScore, wordScore);
                }

            }

            return maxScore;
        }

        private boolean isValid(int[] wordFrequencies, int[] letterFrequencies) {
            for(int i = 0; i < 26; i++) {
                if(wordFrequencies[i] > letterFrequencies[i]) {
                    return false;
                }
            }

            return true;
        }

        private int[] getFrequencies(String word) {
            int[] frequencies = new int[26];
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                frequencies[index]++;
            }

            return frequencies;
        }

        private int[] getFrequencies(char[] letters) {
            int[] frequencies = new int[26];
            for(char c : letters) {
                int index = c - 'a';
                frequencies[index]++;
            }

            return frequencies;
        }

        private void print(int[] arr) {
            for(int val : arr) {
                System.out.print(val + " ");
            }

            System.out.println();
        }
    }
}

//    1255. Maximum Score Words Formed by Letters
//    Hard
//    Given a list of words, list of  single letters (might be repeating) and score of every character.
//
//    Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two or more times).
//
//    It is not necessary to use all characters in letters and each letter can only be used once. Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.
//
//
//
//    Example 1:
//
//    Input: words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
//    Output: 23
//    Explanation:
//    Score  a=1, c=9, d=5, g=3, o=2
//    Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
//    Words "dad" and "dog" only get a score of 21.
//    Example 2:
//
//    Input: words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"], score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
//    Output: 27
//    Explanation:
//    Score  a=4, b=4, c=4, x=5, z=10
//    Given letters, we can form the words "ax" (4+5), "bx" (4+5) and "cx" (4+5) with a score of 27.
//    Word "xxxz" only get a score of 25.
//    Example 3:
//
//    Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"], score = [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
//    Output: 0
//    Explanation:
//    Letter "e" can only be used once.
//
//
//    Constraints:
//
//    1 <= words.length <= 14
//    1 <= words[i].length <= 15
//    1 <= letters.length <= 100
//    letters[i].length == 1
//    score.length == 26
//    0 <= score[i] <= 10
//    words[i], letters[i] contains only lower case English letters.