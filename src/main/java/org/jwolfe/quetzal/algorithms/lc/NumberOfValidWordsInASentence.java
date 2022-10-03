package org.jwolfe.quetzal.algorithms.lc;

public class NumberOfValidWordsInASentence {
    class Solution {
        public int countValidWords(String sentence) {
            if(sentence == null || sentence.length() == 0) {
                return 0;
            }

            int count = 0;
            String[] words = sentence.split(" ");
            for(String word : words) {
                if(word.trim() == "") {
                    continue;
                }

                if(isValidWord(word)) {
                    count++;
                }
            }

            return count;
        }

        private boolean isValidWord(String word) {
            int hyphenCount = 0;
            int n = word.length();

            for(int i = 0; i < n; i++) {
                char c = word.charAt(i);

                if(Character.isDigit(c)) {
                    return false;
                }

                if(c == '-') {
                    if(hyphenCount == 1) {
                        return false;
                    }

                    if(i == 0 || i == n - 1) {
                        return false;
                    }

                    if(!Character.isLowerCase(word.charAt(i - 1)) || ! Character.isLowerCase(word.charAt(i + 1))) {
                        return false;
                    }

                    hyphenCount++;
                }

                if(c == '!' || c == '.' || c == ',') {
                    if(i != n - 1) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

// "cat and  dog"
// "!this  1-s b8d!"
}

//    2047. Number of Valid Words in a Sentence
//    Easy
//    A sentence consists of lowercase letters ('a' to 'z'), digits ('0' to '9'), hyphens ('-'), punctuation marks ('!', '.', and ','), and spaces (' ') only. Each sentence can be broken down into one or more tokens separated by one or more spaces ' '.
//
//    A token is a valid word if all three of the following are true:
//
//    It only contains lowercase letters, hyphens, and/or punctuation (no digits).
//    There is at most one hyphen '-'. If present, it must be surrounded by lowercase characters ("a-b" is valid, but "-ab" and "ab-" are not valid).
//    There is at most one punctuation mark. If present, it must be at the end of the token ("ab,", "cd!", and "." are valid, but "a!b" and "c.," are not valid).
//    Examples of valid words include "a-b.", "afad", "ba-c", "a!", and "!".
//
//    Given a string sentence, return the number of valid words in sentence.
//
//
//
//    Example 1:
//
//    Input: sentence = "cat and  dog"
//    Output: 3
//    Explanation: The valid words in the sentence are "cat", "and", and "dog".
//    Example 2:
//
//    Input: sentence = "!this  1-s b8d!"
//    Output: 0
//    Explanation: There are no valid words in the sentence.
//    "!this" is invalid because it starts with a punctuation mark.
//    "1-s" and "b8d" are invalid because they contain digits.
//    Example 3:
//
//    Input: sentence = "alice and  bob are playing stone-game10"
//    Output: 5
//    Explanation: The valid words in the sentence are "alice", "and", "bob", "are", and "playing".
//    "stone-game10" is invalid because it contains digits.
//
//
//    Constraints:
//
//    1 <= sentence.length <= 1000
//    sentence only contains lowercase English letters, digits, ' ', '-', '!', '.', and ','.
//    There will be at least 1 token.