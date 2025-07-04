package org.jwolfe.quetzal.algorithms.lc;

public class FindTheKthCharacterInStringGameII {
    class Solution {
        public char kthCharacter(long k, int[] operations) {
            if (k < 0 || operations == null || operations.length == 0) {
                return 'a';
            }

            int shifts = 0;
            int t;

            while (k != 1) {
                t = 63 - Long.numberOfLeadingZeros(k);
                if ((1L << t) == k) {
                    t--;
                }

                k = k - (1L << t);
                if (operations[t] != 0) {
                    shifts++;
                }
            }

            return (char) ('a' + (shifts % 26));
        }
    }

    class Solution_TLE {
        public char kthCharacter(long k, int[] operations) {
            if (k < 0 || operations == null || operations.length == 0) {
                return 'a';
            }

            StringBuilder builder = new StringBuilder();
            builder.append('a');

            for (int operation : operations) {
                if (operation == 0) {
                    builder.append(builder.toString());
                } else {
                    StringBuilder builder2 = new StringBuilder();
                    for (int i = 0; i < builder.length(); i++) {
                        char c = builder.charAt(i);
                        char nc = (c < 'z') ? (char) (c + 1) : 'a';
                        builder2.append(nc);
                    }

                    builder.append(builder2.toString());
                }
            }

            return builder.charAt((int) k - 1);
        }
    }
}

//    3307. Find the K-th Character in String Game II
//    Hard
//    Alice and Bob are playing a game. Initially, Alice has a string word = "a".
//
//    You are given a positive integer k. You are also given an integer array operations, where operations[i] represents the type of the ith operation.
//
//    Now Bob will ask Alice to perform all operations in sequence:
//
//    If operations[i] == 0, append a copy of word to itself.
//    If operations[i] == 1, generate a new string by changing each character in word to its next character in the English alphabet, and append it to the original word. For example, performing the operation on "c" generates "cd" and performing the operation on "zb" generates "zbac".
//    Return the value of the kth character in word after performing all the operations.
//
//    Note that the character 'z' can be changed to 'a' in the second type of operation.
//
//
//
//    Example 1:
//
//    Input: k = 5, operations = [0,0,0]
//
//    Output: "a"
//
//    Explanation:
//
//    Initially, word == "a". Alice performs the three operations as follows:
//
//    Appends "a" to "a", word becomes "aa".
//    Appends "aa" to "aa", word becomes "aaaa".
//    Appends "aaaa" to "aaaa", word becomes "aaaaaaaa".
//    Example 2:
//
//    Input: k = 10, operations = [0,1,0,1]
//
//    Output: "b"
//
//    Explanation:
//
//    Initially, word == "a". Alice performs the four operations as follows:
//
//    Appends "a" to "a", word becomes "aa".
//    Appends "bb" to "aa", word becomes "aabb".
//    Appends "aabb" to "aabb", word becomes "aabbaabb".
//    Appends "bbccbbcc" to "aabbaabb", word becomes "aabbaabbbbccbbcc".
//
//
//    Constraints:
//
//    1 <= k <= 1014
//    1 <= operations.length <= 100
//    operations[i] is either 0 or 1.
//    The input is generated such that word has at least k characters after all operations.