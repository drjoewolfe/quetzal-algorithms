package org.jwolfe.quetzal.algorithms.lc;

public class FractionAdditionAndSubtraction {
    class Solution {
        public String fractionAddition(String expression) {
            if (expression == null || expression.length() == 0) {
                return expression;
            }

            int n = expression.length();
            int numerator = 0;
            int denominator = 1;
            int i = 0;

            while (i < n) {
                boolean isNegative = false;
                int nextNumerator = 0;
                int nextDenominator = 0;

                if (expression.charAt(i) == '-' || expression.charAt(i) == '+') {
                    if (expression.charAt(i) == '-') {
                        isNegative = true;
                    }

                    i++;
                }

                while (i < n && Character.isDigit(expression.charAt(i))) {
                    int val = expression.charAt(i) - '0';
                    nextNumerator = nextNumerator * 10 + val;
                    i++;
                }

                if (isNegative) {
                    nextNumerator *= -1;
                }

                i++;

                while (i < n && Character.isDigit(expression.charAt(i))) {
                    int val = expression.charAt(i) - '0';
                    nextDenominator = nextDenominator * 10 + val;
                    i++;
                }

                numerator = (numerator * nextDenominator) + (nextNumerator * denominator);
                denominator = denominator * nextDenominator;
            }

            int gcd = Math.abs(findGCD(numerator, denominator));
            numerator /= gcd;
            denominator /= gcd;

            return numerator + "/" + denominator;
        }

        private int findGCD(int a, int b) {
            if (a == 0) {
                return b;
            }

            return findGCD(b % a, a);
        }
    }
}

//    592. Fraction Addition and Subtraction
//    Medium
//
//    569
//
//    591
//
//    Add to List
//
//    Share
//    Given a string expression representing an expression of fraction addition and subtraction, return the calculation result in string format.
//
//    The final result should be an irreducible fraction. If your final result is an integer, change it to the format of a fraction that has a denominator 1. So in this case, 2 should be converted to 2/1.
//
//
//
//    Example 1:
//
//    Input: expression = "-1/2+1/2"
//    Output: "0/1"
//    Example 2:
//
//    Input: expression = "-1/2+1/2+1/3"
//    Output: "1/3"
//    Example 3:
//
//    Input: expression = "1/3-1/2"
//    Output: "-1/6"
//
//
//    Constraints:
//
//    The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
//    Each fraction (input and output) has the format ±numerator/denominator. If the first input fraction or the output is positive, then '+' will be omitted.
//    The input only contains valid irreducible fractions, where the numerator and denominator of each fraction will always be in the range [1, 10]. If the denominator is 1, it means this fraction is actually an integer in a fraction format defined above.
//    The number of given fractions will be in the range [1, 10].
//    The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int.