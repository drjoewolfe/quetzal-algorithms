package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class WordLadderII {
    class Solution {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            List<List<String>> results = new ArrayList<>();
            if(beginWord == null || endWord == null || wordList == null || wordList.size() == 0) {
                return results;
            }

            Set<String> dictionary = new HashSet<>(wordList);
            if(!dictionary.contains(endWord)) {
                return results;
            }

            Queue<Ladder> queue = new LinkedList<>();
            queue.offer(new Ladder(beginWord));

            Set<String> seen = new HashSet<>();
            seen.add(beginWord);

            while(!queue.isEmpty()) {
                int size = queue.size();
                boolean finished = false;

                Set<String> seenThisLevel = new HashSet<>();

                while(size > 0) {
                    size--;

                    Ladder ladder = queue.poll();
                    String word = ladder.top;


                    StringBuilder wordBuilder = new StringBuilder(word);
                    for(int i = 0; i < word.length(); i++) {
                        char pc = word.charAt(i);
                        for(char c = 'a'; c <= 'z'; c++) {
                            if(pc == c) {
                                continue;
                            }

                            wordBuilder.setCharAt(i, c);
                            String newWord = wordBuilder.toString();

                            if(newWord.equals(endWord)) {
                                ladder.add(newWord);
                                results.add(ladder.ladder);
                                finished = true;
                                continue;
                            } else if(!seen.contains(newWord) && dictionary.contains(newWord)) {
                                seenThisLevel.add(newWord);
                                Ladder newLadder = new Ladder(ladder);
                                newLadder.add(newWord);
                                queue.offer(newLadder);
                            }
                        }

                        wordBuilder.setCharAt(i, pc);
                    }
                }

                if(finished) {
                    break;
                }

                seen.addAll(seenThisLevel);
            }

            return results;
        }
    }

    private static class Ladder {
        public List<String> ladder;
        public String top;

        public Ladder() {
            this.ladder = new ArrayList<>();
        }

        public Ladder(String word) {
            this();
            this.ladder.add(word);
            this.top = word;
        }

        public Ladder(Ladder ladder) {
            this.ladder = new ArrayList<>(ladder.ladder);
            this.top = ladder.top;
        }

        public void add(String word) {
            this.ladder.add(word);
            this.top = word;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(" [");
            for(String str : ladder) {
                builder.append(str + " ");
            }

            builder.append("]");

            return builder.toString();
        }
    }
}

//    126. Word Ladder II
//    Hard
//    A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
//
//    Every adjacent pair of words differs by a single letter.
//    Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
//    sk == endWord
//    Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
//
//
//
//    Example 1:
//
//    Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
//    Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
//    Explanation: There are 2 shortest transformation sequences:
//    "hit" -> "hot" -> "dot" -> "dog" -> "cog"
//    "hit" -> "hot" -> "lot" -> "log" -> "cog"
//    Example 2:
//
//    Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
//    Output: []
//    Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
//
//
//    Constraints:
//
//    1 <= beginWord.length <= 5
//    endWord.length == beginWord.length
//    1 <= wordList.length <= 1000
//    wordList[i].length == beginWord.length
//    beginWord, endWord, and wordList[i] consist of lowercase English letters.
//    beginWord != endWord
//    All the words in wordList are unique.
