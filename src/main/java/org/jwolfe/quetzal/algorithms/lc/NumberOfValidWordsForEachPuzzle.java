package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class NumberOfValidWordsForEachPuzzle {
    class Solution {
        public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
            List<Integer> results = new ArrayList<>();
            if(words == null || words.length == 0 || puzzles == null || puzzles.length == 0) {
                return results;
            }

            Map<Integer, Integer> wordCounts = new HashMap<>();
            for(String word : words) {
                Integer mask = generateBitmask(word, 0);
                wordCounts.put(mask, wordCounts.getOrDefault(mask, 0) + 1);
            }

            for(int i = 0; i < puzzles.length; i++) {
                String puzzle = puzzles[i];
                char puzzleFirstChar = puzzle.charAt(0);

                Integer puzzleFirstMask = (1 << (puzzleFirstChar - 'a'));
                Integer puzzleRemainderMask = generateBitmask(puzzle, 1);

                int count = wordCounts.getOrDefault(puzzleFirstMask, 0);
                for(int submask = puzzleRemainderMask; submask > 0; submask = puzzleRemainderMask & (submask - 1)) {
                    int newMask = submask | puzzleFirstMask;

                    count += wordCounts.getOrDefault(newMask, 0);
                }

                results.add(count);
            }

            return results;
        }

        private Integer generateBitmask(String str, int fromIndex) {
            int mask = 0;

            for(int i = fromIndex; i < str.length(); i++) {
                char c = str.charAt(i);
                int index = c - 'a';

                mask = mask | (1 << index);
            }

            return mask;
        }
    }

    class Solution_Masking {
        public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
            List<Integer> results = new ArrayList<>();
            if(words == null || words.length == 0 || puzzles == null || puzzles.length == 0) {
                return results;
            }

            List<Integer> puzzleBitmasks = new ArrayList<>();
            List<Integer> wordBitmasks = new ArrayList<>();

            for(String puzzle : puzzles) {
                puzzleBitmasks.add(generateBitmask(puzzle));
            }

            for(String word : words) {
                wordBitmasks.add(generateBitmask(word));
            }

            for(int i = 0; i < puzzles.length; i++) {
                String puzzle = puzzles[i];
                Integer puzzleMask = puzzleBitmasks.get(i);

                char firstPuzzleChar = puzzle.charAt(0);

                int count = 0;
                for(int j = 0; j < words.length; j++) {
                    String word = words[j];
                    Integer wordMask = wordBitmasks.get(j);

                    if(containsChar(wordMask, firstPuzzleChar)
                            && isValid(puzzleMask, wordMask)) {
                        count++;
                    }
                }

                results.add(count);
            }

            return results;
        }

        private Integer generateBitmask(String str) {
            int mask = 0;

            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                int index = c - 'a';

                mask = mask | (1 << index);
            }

            return mask;
        }

        private boolean containsChar(Integer mask, char c) {
            int index = c - 'a';

            return (mask & (1 << index)) > 0;
        }

        private boolean isValid(Integer puzzleMask, Integer wordMask) {
            for(int i = 0; i < 32; i++) {
                int mask = (1 << i);
                if((wordMask & mask) > 0) {
                    if((puzzleMask & mask) == 0) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    class Solution_Standard {
        public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
            List<Integer> results = new ArrayList<>();
            if(words == null || words.length == 0 || puzzles == null || puzzles.length == 0) {
                return results;
            }

            List<Set<Character>> puzzleSets = new ArrayList<>();
            for(String puzzle : puzzles) {
                Set<Character> set = new HashSet<>();
                puzzleSets.add(set);

                for(int i = 0; i < puzzle.length(); i++) {
                    char c = puzzle.charAt(i);
                    set.add(c);
                }
            }

            List<Set<Character>> wordSets = new ArrayList<>();
            for(String word : words) {
                Set<Character> set = new HashSet<>();
                wordSets.add(set);

                for(int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    set.add(c);
                }
            }

            for(int i = 0; i < puzzles.length; i++) {
                String puzzle = puzzles[i];
                if(puzzle == null || puzzle.length() == 0) {
                    results.add(0);
                    continue;
                }

                Set<Character> puzzleSet = puzzleSets.get(i);
                char firstPuzzleChar = puzzle.charAt(0);

                int count = 0;
                for(int j = 0; j < words.length; j++) {
                    String word = words[j];
                    Set<Character> wordSet = wordSets.get(j);

                    if(!wordSet.contains(firstPuzzleChar)) {
                        continue;
                    }

                    boolean isValid = true;
                    for(Character c : wordSet) {
                        if(!puzzleSet.contains(c)) {
                            isValid = false;
                            break;
                        }
                    }

                    if(isValid) {
                        count++;
                    }
                }

                results.add(count);
            }

            return results;
        }
    }

// ["aaaa","asas","able","ability","actt","actor","access"]
// ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]


// ["apple","pleas","please"]
// ["aelwxyz","aelpxyz","aelpsxy","saelpxy","xaelpsy"]
}

//    1178. Number of Valid Words for Each Puzzle
//    Hard
//    With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
//    word contains the first letter of puzzle.
//    For each letter in word, that letter is in puzzle.
//    For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage", while
//    invalid words are "beefed" (does not include 'a') and "based" (includes 's' which is not in the puzzle).
//    Return an array answer, where answer[i] is the number of words in the given word list words that is valid with respect to the puzzle puzzles[i].
//
//
//    Example 1:
//
//    Input: words = ["aaaa","asas","able","ability","actt","actor","access"], puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
//    Output: [1,1,3,2,4,0]
//    Explanation:
//    1 valid word for "aboveyz" : "aaaa"
//    1 valid word for "abrodyz" : "aaaa"
//    3 valid words for "abslute" : "aaaa", "asas", "able"
//    2 valid words for "absoryz" : "aaaa", "asas"
//    4 valid words for "actresz" : "aaaa", "asas", "actt", "access"
//    There are no valid words for "gaswxyz" cause none of the words in the list contains letter 'g'.
//    Example 2:
//
//    Input: words = ["apple","pleas","please"], puzzles = ["aelwxyz","aelpxyz","aelpsxy","saelpxy","xaelpsy"]
//    Output: [0,1,3,2,0]
//
//
//    Constraints:
//
//    1 <= words.length <= 105
//    4 <= words[i].length <= 50
//    1 <= puzzles.length <= 104
//    puzzles[i].length == 7
//    words[i] and puzzles[i] consist of lowercase English letters.
//    Each puzzles[i] does not contain repeated characters.