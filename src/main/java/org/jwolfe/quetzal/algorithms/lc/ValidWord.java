package org.jwolfe.quetzal.algorithms.lc;

public class ValidWord {
    class Solution {
        public boolean isValid(String word) {
            if (word == null || word.length() < 3) {
                return false;
            }

            boolean hasVowel = false;
            boolean hasConsonant = false;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (Character.isLetter(c)) {
                    char c2 = Character.toLowerCase(c);

                    if (isVowel(c2)) {
                        hasVowel = true;
                    } else {
                        hasConsonant = true;
                    }
                } else if (!Character.isDigit(c)) {
                    return false;
                }
            }

            return hasVowel && hasConsonant;
        }

        private boolean isVowel(char c) {
            return (c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u');
        }
    }

    class Solution_Correct_1 {
        public boolean isValid(String word) {
            if (word == null || word.length() < 3) {
                return false;
            }

            boolean hasVowel = false;
            boolean hasConsonant = false;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                    return false;
                }

                if (isVowel(c)) {
                    hasVowel = true;
                } else {
                    hasConsonant = true;
                }
            }

            return hasVowel && hasConsonant;
        }

        private boolean isVowel(char c) {
            return (c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u')
                    || (c == 'A') || (c == 'E') || (c == 'I') || (c == 'O') || (c == 'U');
        }
    }
}

//    3136. Valid Word
//    Easy
//    A word is considered valid if:
//
//    It contains a minimum of 3 characters.
//    It contains only digits (0-9), and English letters (uppercase and lowercase).
//    It includes at least one vowel.
//    It includes at least one consonant.
//    You are given a string word.
//
//    Return true if word is valid, otherwise, return false.
//
//    Notes:
//
//    'a', 'e', 'i', 'o', 'u', and their uppercases are vowels.
//    A consonant is an English letter that is not a vowel.
//
//
//    Example 1:
//
//    Input: word = "234Adas"
//
//    Output: true
//
//    Explanation:
//
//    This word satisfies the conditions.
//
//    Example 2:
//
//    Input: word = "b3"
//
//    Output: false
//
//    Explanation:
//
//    The length of this word is fewer than 3, and does not have a vowel.
//
//    Example 3:
//
//    Input: word = "a3$e"
//
//    Output: false
//
//    Explanation:
//
//    This word contains a '$' character and does not have a consonant.
//
//
//
//    Constraints:
//
//    1 <= word.length <= 20
//    word consists of English uppercase and lowercase letters, digits, '@', '#', and '$'.
