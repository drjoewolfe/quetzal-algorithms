package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNumber {
    class Solution {
        public List<String> letterCombinations(String digits) {
            List<String> results = new ArrayList<>();

            if(digits == null || digits.length() == 0) {
                return results;
            }

            for(int i = 0; i < digits.length(); i++) {
                char d = digits.charAt(i);
                if(d < '2' || d > '9') {
                    return results;
                }
            }

            Map<Character, List<Character>> phoneLetters = new HashMap<>();
            phoneLetters.put('2', new ArrayList<>(List.of('a', 'b', 'c')));
            phoneLetters.put('3', new ArrayList<>(List.of('d', 'e', 'f')));
            phoneLetters.put('4', new ArrayList<>(List.of('g', 'h', 'i')));
            phoneLetters.put('5', new ArrayList<>(List.of('j', 'k', 'l')));
            phoneLetters.put('6', new ArrayList<>(List.of('m', 'n', 'o')));
            phoneLetters.put('7', new ArrayList<>(List.of('p', 'q', 'r', 's')));
            phoneLetters.put('8', new ArrayList<>(List.of('t', 'u', 'v')));
            phoneLetters.put('9', new ArrayList<>(List.of('w', 'x', 'y', 'z')));


            generateLetterCombinations(digits, 0, digits.length(), phoneLetters, new StringBuilder(), results);
            return results;
        }

        private void generateLetterCombinations(String digits, int index, int length, Map<Character, List<Character>> phoneLetters, StringBuilder currentString, List<String> results) {
            if(index == length) {
                results.add(currentString.toString());
                return;
            }

            char d = digits.charAt(index);
            List<Character> characters = phoneLetters.get(d);
            for(char c : characters) {
                currentString.append(c);
                generateLetterCombinations(digits, index + 1, length, phoneLetters, currentString, results);
                currentString.deleteCharAt(currentString.length() - 1);
            }
        }
    }
}

//    17. Letter Combinations of a Phone Number
//    Medium
//    Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
//
//    A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
//
//
//
//    Example 1:
//
//    Input: digits = "23"
//    Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
//    Example 2:
//
//    Input: digits = ""
//    Output: []
//    Example 3:
//
//    Input: digits = "2"
//    Output: ["a","b","c"]
//
//
//    Constraints:
//
//    0 <= digits.length <= 4
//    digits[i] is a digit in the range ['2', '9'].
