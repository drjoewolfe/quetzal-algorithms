package org.jwolfe.quetzal.algorithms.lc;

public class ComplexNumberMultiplication {
    class Solution {
        public String complexNumberMultiply(String num1, String num2) {
            if(num1 == null || num2 == null || num1.length() < 4 || num2.length() < 4) {
                return "";
            }

            int[] parts1 = parseComplex(num1);
            int[] parts2 = parseComplex(num2);

            int r1 = parts1[0];
            int i1 = parts1[1];

            int r2 = parts2[0];
            int i2 = parts2[1];

            int pr = (r1 * r2) + (i1 * i2 * -1);
            int pi = (r1 * i2) + (r2 * i1);

            String p = retrieveComplex(pr, pi);
            return p;
        }

        private int[] parseComplex(String complex) {
            int i = complex.indexOf("+");
            String realPart = complex.substring(0, i);
            String imaginaryPart = complex.substring(i + 1, complex.length() - 1);

            return new int[] {Integer.valueOf(realPart), Integer.valueOf(imaginaryPart)};
        }

        private String retrieveComplex(int real, int imaginary) {
            return Integer.toString(real)
                    + "+"
                    + Integer.toString(imaginary)
                    + "i";
        }
    }

// "1+1i"
// "1+1i"

// "1+-1i"
// "0+0i"
}

//    537. Complex Number Multiplication
//    Medium
//    A complex number can be represented as a string on the form "real+imaginaryi" where:
//
//    real is the real part and is an integer in the range [-100, 100].
//    imaginary is the imaginary part and is an integer in the range [-100, 100].
//    i2 == -1.
//    Given two complex numbers num1 and num2 as strings, return a string of the complex number that represents their multiplications.
//
//
//
//    Example 1:
//
//    Input: num1 = "1+1i", num2 = "1+1i"
//    Output: "0+2i"
//    Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
//    Example 2:
//
//    Input: num1 = "1+-1i", num2 = "1+-1i"
//    Output: "0+-2i"
//    Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
//
//
//    Constraints:
//
//    num1 and num2 are valid complex numbers.