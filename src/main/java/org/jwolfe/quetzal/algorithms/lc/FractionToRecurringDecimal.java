package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurringDecimal {
    class Solution {
        public String fractionToDecimal(int numerator, int denominator) {
            if (numerator == 0) {
                return "0";
            }

            StringBuilder answer = new StringBuilder();
            if (numerator < 0 ^ denominator < 0) {
                answer.append("-");
            }

            long dividend = Math.abs(Long.valueOf(numerator));
            long divisor = Math.abs(Long.valueOf(denominator));

            long quotient = dividend / divisor;
            long remainder = dividend % divisor;

            answer.append(quotient);
            if (remainder == 0) {
                return answer.toString();
            }

            answer.append(".");
            Map<Long, Integer> map = new HashMap<>();
            while (remainder != 0) {
                if (map.containsKey(remainder)) {
                    int position = map.get(remainder);
                    answer.insert(position, "(");
                    answer.append(")");

                    return answer.toString();
                }

                map.put(remainder, answer.length());
                dividend = remainder * 10;
                quotient = dividend / divisor;
                remainder = dividend % divisor;

                answer.append(quotient);
            }

            return answer.toString();
        }
    }

    class Solution_Correct_1 {
        public String fractionToDecimal(int numerator, int denominator) {
            if (numerator == 0) {
                return "0";
            }

            StringBuilder answer = new StringBuilder();
            if (numerator < 0 ^ denominator < 0) {
                answer.append("-");
            }

            long lnumerator = Math.abs((long) numerator);
            long ldenominator = Math.abs((long) denominator);
            answer.append(lnumerator / ldenominator);

            lnumerator %= ldenominator;
            if (lnumerator == 0) {
                return answer.toString();
            }

            answer.append(".");
            Map<Long, Integer> map = new HashMap<>();
            while (lnumerator != 0) {
                lnumerator *= 10;
                answer.append(lnumerator / ldenominator);
                lnumerator %= ldenominator;

                if (map.containsKey(lnumerator)) {
                    int index = map.get(lnumerator);
                    answer.insert(index, "(");
                    answer.append(")");

                    break;
                } else {
                    map.put(lnumerator, answer.length());
                }
            }

            return answer.toString();
        }
    }

// 1
// 2

// 4
// 133
}

//    166. Fraction to Recurring Decimal
//    Medium
//    Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
//
//    If the fractional part is repeating, enclose the repeating part in parentheses.
//
//    If multiple answers are possible, return any of them.
//
//    It is guaranteed that the length of the answer string is less than 104 for all the given inputs.
//
//
//
//    Example 1:
//
//    Input: numerator = 1, denominator = 2
//    Output: "0.5"
//    Example 2:
//
//    Input: numerator = 2, denominator = 1
//    Output: "2"
//    Example 3:
//
//    Input: numerator = 4, denominator = 333
//    Output: "0.(012)"
//
//
//    Constraints:
//
//    -231 <= numerator, denominator <= 231 - 1
//    denominator != 0