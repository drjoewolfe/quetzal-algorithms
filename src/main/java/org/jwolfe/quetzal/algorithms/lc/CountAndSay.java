package org.jwolfe.quetzal.algorithms.lc;

public class CountAndSay {
    class Solution {
        public String countAndSay(int n) {
            if(n < 0) {
                return "";
            }

            String s = "1";
            for(int i = 2; i <= n; i++) {
                s = getCountAndSayString(s);
            }

            return s;
        }

        private String getCountAndSayString(String s) {
            StringBuilder builder = new StringBuilder();

            char prev = s.charAt(0);
            int count = 1;
            for(int i = 1; i < s.length(); i++) {
                char curr = s.charAt(i);

                if(prev == curr) {
                    count++;
                } else {
                    builder.append(Integer.toString(count) + prev);

                    prev = curr;
                    count = 1;
                }
            }

            builder.append(Integer.toString(count) + prev);
            return builder.toString();
        }
    }

    class Solution_Correct_1 {
        public String countAndSay(int n) {
            if(n <= 0) {
                return "";
            }

            String[] csTerms = new String[n + 1];
            csTerms[1] = "1";

            for(int i = 2; i <= n; i++) {
                String seedTerm = csTerms[i - 1];

                StringBuilder newTerm = new StringBuilder();
                char digit = seedTerm.charAt(0);
                int frequency = 1;
                for(int k = 1; k < seedTerm.length(); k++) {
                    char newDigit = seedTerm.charAt(k);
                    if(digit == newDigit) {
                        frequency++;
                    } else {
                        newTerm.append(frequency + "" + digit);

                        digit = newDigit;
                        frequency = 1;
                    }
                }

                newTerm.append(frequency + "" + digit);
                csTerms[i] = newTerm.toString();
            }

            return csTerms[n];
        }
    }

/*

1.     1
2.     11
3.     21
4.     1211
5.     111221
6.	   312211
7.	   13112221

*/

}

//    38. Count and Say
//    Medium
//    The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
//
//    countAndSay(1) = "1"
//    countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string.
//    To determine how you "say" a digit string, split it into the minimal number of substrings such that each substring contains exactly one unique digit. Then for each substring, say the number of digits, then say the digit. Finally, concatenate every said digit.
//
//    For example, the saying and conversion for digit string "3322251":
//
//
//    Given a positive integer n, return the nth term of the count-and-say sequence.
//
//
//
//    Example 1:
//
//    Input: n = 1
//    Output: "1"
//    Explanation: This is the base case.
//    Example 2:
//
//    Input: n = 4
//    Output: "1211"
//    Explanation:
//    countAndSay(1) = "1"
//    countAndSay(2) = say "1" = one 1 = "11"
//    countAndSay(3) = say "11" = two 1's = "21"
//    countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
//
//
//    Constraints:
//
//    1 <= n <= 30