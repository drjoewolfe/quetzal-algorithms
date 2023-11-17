package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    class Solution {
        public List<String> fullJustify(String[] words, int maxWidth) {
            List<String> results = new ArrayList<>();
            if(words == null || words.length == 0 || maxWidth < 1) {
                return results;
            }

            int n = words.length;
            int start = 0;
            int currentLength = 0;
            int wordLength = 0;
            for(int i = 0; i < n; i++) {
                String word = words[i];
                currentLength += word.length();
                if(currentLength > maxWidth) {
                    // Adding current word will increase width
                    // Emit line & start new
                    String line = getFullJustifiedLine(words, start, i - 1, wordLength, maxWidth);
                    results.add(line);

                    start = i;
                    currentLength = word.length();
                    wordLength = 0;
                }

                // Space after word
                currentLength++;
                wordLength += word.length();
            }

            String line = getLeftJustifiedLine(words, start, n - 1, currentLength, maxWidth);
            results.add(line);

            return results;
        }

        private String getFullJustifiedLine(String[] words, int start, int end, int length, int maxWidth) {
            StringBuilder builder = new StringBuilder();
            int lengthDiff = maxWidth -length;
            int currentSpace = 0;

            for(int i = start; i <= end; i++, lengthDiff-=currentSpace) {
                String word = words[i];
                builder.append(word);

                currentSpace = (i == end) ? 0 : (int) Math.ceil((double) lengthDiff / (end - i));
                if(i != end) {
                    for(int j = 0; j < currentSpace; j++) {
                        builder.append(" ");
                    }
                }
            }

            for(int l = builder.length(); l < maxWidth; l++) {
                builder.append(" ");
            }

            return builder.toString();
        }

        private String getLeftJustifiedLine(String[] words, int start, int end, int length, int maxWidth) {
            StringBuilder builder = new StringBuilder();

            for(int i = start; i <= end; i++) {
                String word = words[i];
                builder.append(word);
                if(i != end) {
                    builder.append(" ");
                }
            }

            for(int l = builder.length(); l < maxWidth; l++) {
                builder.append(" ");
            }

            return builder.toString();
        }
    }

// ["This", "is", "an", "example", "of", "text", "justification."]
// 16

// ["What","must","be","acknowledgment","shall","be"]
// 16

// ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"]
// 20
}

//    68. Text Justification
//    Hard
//    Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
//
//    You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
//
//    Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
//
//    For the last line of text, it should be left-justified, and no extra space is inserted between words.
//
//    Note:
//
//    A word is defined as a character sequence consisting of non-space characters only.
//    Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
//    The input array words contains at least one word.
//
//
//    Example 1:
//
//    Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
//    Output:
//    [
//    "This    is    an",
//    "example  of text",
//    "justification.  "
//    ]
//    Example 2:
//
//    Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
//    Output:
//    [
//    "What   must   be",
//    "acknowledgment  ",
//    "shall be        "
//    ]
//    Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
//    Note that the second line is also left-justified because it contains only one word.
//    Example 3:
//
//    Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
//    Output:
//    [
//    "Science  is  what we",
//    "understand      well",
//    "enough to explain to",
//    "a  computer.  Art is",
//    "everything  else  we",
//    "do                  "
//    ]
//
//
//    Constraints:
//
//    1 <= words.length <= 300
//    1 <= words[i].length <= 20
//    words[i] consists of only English letters and symbols.
//    1 <= maxWidth <= 100
//    words[i].length <= maxWidth