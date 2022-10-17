package org.jwolfe.quetzal.algorithms.lc;

import java.util.stream.Collectors;

public class CheckIfTheSentenceIsPangram {
    class Solution {
        public boolean checkIfPangram(String sentence) {
            if(sentence == null || sentence.length() < 26) {
                return false;
            }

            return sentence.chars().boxed().collect(Collectors.toSet()).size() == 26;
        }
    }

    class Solution_Correct_3 {
        public boolean checkIfPangram(String sentence) {
            if(sentence == null || sentence.length() < 26) {
                return false;
            }

            return sentence.chars().distinct().count() == 26;
        }
    }

    class Solution_Correct_2 {
        public boolean checkIfPangram(String sentence) {
            if(sentence == null || sentence.length() < 26) {
                return false;
            }

            int[] frequencies = new int[26];
            for(int i = 0; i < sentence.length(); i++) {
                frequencies[sentence.charAt(i) - 'a']++;
            }

            for(int i = 0; i < 26; i++) {
                if(frequencies[i] == 0) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public boolean checkIfPangram(String sentence) {
            if(sentence == null || sentence.length() < 26) {
                return false;
            }

            int[] frequencies = new int[26];
            for(int i = 0; i < sentence.length(); i++) {
                frequencies[sentence.charAt(i) - 'a']++;
            }

            for(int i = 0; i < 26; i++) {
                if(frequencies[i] == 0) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    1832. Check if the Sentence Is Pangram
//    Easy
//    A pangram is a sentence where every letter of the English alphabet appears at least once.
//
//    Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.
//
//
//
//    Example 1:
//
//    Input: sentence = "thequickbrownfoxjumpsoverthelazydog"
//    Output: true
//    Explanation: sentence contains at least one of every letter of the English alphabet.
//    Example 2:
//
//    Input: sentence = "leetcode"
//    Output: false
//
//
//    Constraints:
//
//    1 <= sentence.length <= 1000
//    sentence consists of lowercase English letters.
