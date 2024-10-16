package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class LongestHappyString {
    class Solution {
        public String longestDiverseString(int a, int b, int c) {
            PriorityQueue<CharacterCount> maxHeap = new PriorityQueue<>((x, y) -> y.count - x.count);

            if (a > 0) {
                maxHeap.offer(new CharacterCount('a', a));
            }

            if (b > 0) {
                maxHeap.offer(new CharacterCount('b', b));
            }

            if (c > 0) {
                maxHeap.offer(new CharacterCount('c', c));
            }

            StringBuilder builder = new StringBuilder();
            while (!maxHeap.isEmpty()) {
                var element = maxHeap.poll();

                if (builder.length() >= 2
                        && builder.charAt(builder.length() - 1) == element.c
                        && builder.charAt(builder.length() - 2) == element.c) {
                    // cannot add current element
                    if (maxHeap.isEmpty()) {
                        return builder.toString();
                    }

                    var nextElement = maxHeap.poll();
                    builder.append(nextElement.c);
                    nextElement.count--;

                    if (nextElement.count > 0) {
                        maxHeap.offer(nextElement);
                    }
                } else {
                    // add current element
                    builder.append(element.c);
                    element.count--;
                }

                if (element.count > 0) {
                    maxHeap.offer(element);
                }
            }

            return builder.toString();
        }

        private class CharacterCount {
            char c;
            int count;

            public CharacterCount(char c, int count) {
                this.c = c;
                this.count = count;
            }
        }
    }
}

//    1405. Longest Happy String
//    Medium
//    A string s is called happy if it satisfies the following conditions:
//
//    s only contains the letters 'a', 'b', and 'c'.
//    s does not contain any of "aaa", "bbb", or "ccc" as a substring.
//    s contains at most a occurrences of the letter 'a'.
//    s contains at most b occurrences of the letter 'b'.
//    s contains at most c occurrences of the letter 'c'.
//    Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy strings, return any of them. If there is no such string, return the empty string "".
//
//    A substring is a contiguous sequence of characters within a string.
//
//
//
//    Example 1:
//
//    Input: a = 1, b = 1, c = 7
//    Output: "ccaccbcc"
//    Explanation: "ccbccacc" would also be a correct answer.
//    Example 2:
//
//    Input: a = 7, b = 1, c = 0
//    Output: "aabaa"
//    Explanation: It is the only correct answer in this case.
//
//
//    Constraints:
//
//    0 <= a, b, c <= 100
//    a + b + c > 0
