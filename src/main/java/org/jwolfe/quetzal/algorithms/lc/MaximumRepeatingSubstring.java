package org.jwolfe.quetzal.algorithms.lc;

public class MaximumRepeatingSubstring {
    class Solution {
        public int maxRepeating(String sequence, String word) {
            if(sequence == null || word == null || sequence.length() < word.length()) {
                return 0;
            }

            int k = 0;
            StringBuilder builder = new StringBuilder();
            while(true) {
                builder.append(word);
                String pattern = builder.toString();

                if(sequence.indexOf(pattern) < 0) {
                    break;
                }

                k++;
            }

            return k;
        }
    }

    class Solution_Incorrect {
        public int maxRepeating(String sequence, String word) {
            if(sequence == null || sequence.length() == 0 || word == null || word.length() == 0 || word.length() > sequence.length()) {
                return 0;
            }

            int j = 0;
            int l = word.length();

            int maxRun = 0;
            int currentRun = 0;
            for(int i = 0; i < sequence.length(); i++) {
                char a = sequence.charAt(i);
                char b = word.charAt(j);

                System.out.println(a + ", " + b);
                if(a == b) {
                    j++;
                    if(j == l) {
                        j = 0;

                        currentRun++;
                        maxRun = Math.max(maxRun, currentRun);
                    }
                } else {
                    j = 0;
                    currentRun = 0;
                }
            }

            return maxRun;
        }
    }
}

//    1668. Maximum Repeating Substring
//    Easy
//    For a string sequence, a string word is k-repeating if word concatenated k times is a substring of sequence. The word's maximum k-repeating value is the highest value k where word is k-repeating in sequence. If word is not a substring of sequence, word's maximum k-repeating value is 0.
//
//    Given strings sequence and word, return the maximum k-repeating value of word in sequence.
//
//
//
//    Example 1:
//
//    Input: sequence = "ababc", word = "ab"
//    Output: 2
//    Explanation: "abab" is a substring in "ababc".
//    Example 2:
//
//    Input: sequence = "ababc", word = "ba"
//    Output: 1
//    Explanation: "ba" is a substring in "ababc". "baba" is not a substring in "ababc".
//    Example 3:
//
//    Input: sequence = "ababc", word = "ac"
//    Output: 0
//    Explanation: "ac" is not a substring in "ababc".
//
//
//    Constraints:
//
//    1 <= sequence.length <= 100
//    1 <= word.length <= 100
//    sequence and word contains only lowercase English letters.
