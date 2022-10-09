package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class NumberOfDifferentIntegersInAString {
    class Solution {
        public int numDifferentIntegers(String word) {
            if(word == null || word.length() == 0) {
                return 0;
            }

            int n = word.length();
            Set<String> set = new HashSet<>();

            int i = 0;
            while(i < n) {
                char c = word.charAt(i);
                if(!isDigit(c)) {
                    i++;
                } else {
                    StringBuilder builder = new StringBuilder();
                    while(i < n && isDigit(word.charAt(i))) {
                        char cc = word.charAt(i);
                        builder.append(cc);

                        i++;
                    }

                    String part = builder.toString();

                    // strip leading zeros
                    int j = -1;
                    while(j < part.length() - 1 && part.charAt(j + 1) == '0') {
                        j++;
                    }

                    if(j != -1) {
                        set.add(part.substring(j + 1));
                    } else {
                        set.add(part);
                    }
                }
            }

            return set.size();
        }

        private boolean isDigit(char c) {
            return c >= '0' && c <= '9';
        }
    }

    class Solution_Approach_1 {
        public int numDifferentIntegers(String word) {
            if(word == null || word.length() == 0) {
                return 0;
            }

            int n = word.length();

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < n; i++) {
                char c = word.charAt(i);
                if(c >= '0' && c <= '9') {
                    builder.append(c);
                } else {
                    builder.append(" ");
                }
            }

            Set<String> set = new HashSet<>();
            String[] parts = builder.toString().split(" ");
            for(String part : parts) {
                if(part != "") {
                    // strip leading zeros
                    int i = -1;
                    while(i < part.length() - 1 && part.charAt(i + 1) == '0') {
                        i++;
                    }

                    if(i != -1) {
                        String npart = part.substring(i + 1);
                        set.add(npart);
                    } else {
                        set.add(part);
                    }
                }
            }

            return set.size();
        }
    }

// "a123bc34d8ef34"
// "167278959591294"
}

//    1805. Number of Different Integers in a String
//    Easy
//    You are given a string word that consists of digits and lowercase English letters.
//
//    You will replace every non-digit character with a space. For example, "a123bc34d8ef34" will become " 123  34 8  34". Notice that you are left with some integers that are separated by at least one space: "123", "34", "8", and "34".
//
//    Return the number of different integers after performing the replacement operations on word.
//
//    Two integers are considered different if their decimal representations without any leading zeros are different.
//
//
//
//    Example 1:
//
//    Input: word = "a123bc34d8ef34"
//    Output: 3
//    Explanation: The three different integers are "123", "34", and "8". Notice that "34" is only counted once.
//    Example 2:
//
//    Input: word = "leet1234code234"
//    Output: 2
//    Example 3:
//
//    Input: word = "a1b01c001"
//    Output: 1
//    Explanation: The three integers "1", "01", and "001" all represent the same integer because
//    the leading zeros are ignored when comparing their decimal values.
//
//
//    Constraints:
//
//    1 <= word.length <= 1000
//    word consists of digits and lowercase English letters.
