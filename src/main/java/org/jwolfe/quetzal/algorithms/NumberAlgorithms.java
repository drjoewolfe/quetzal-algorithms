package org.jwolfe.quetzal.algorithms;

import java.util.ArrayList;

public class NumberAlgorithms {

    public static int powerWithoutMutiplicationAndDivision(int a, int b) {
        if (b == 0) {
            return 1;
        }

        int increment = a;
        int answer = a;

        for (int i = 1; i < b; i++) {
            for (int j = 1; j < a; j++) {
                answer += increment;
            }

            increment = answer;
        }

        return answer;
    }

    public static int countSetBits(int n) {
        int count = 0;

        while (n > 0) {
            count += (n & 1);
            n >>= 1;
        }

        return count;
    }

    public static void getBinaryRepresentation(int n) {
        if (n > 1) {
            getBinaryRepresentation(n / 2);
        }

        System.out.print(n % 2);
    }

    public static int maxNumberFromDigitsOfNumber(int number) {
        int[] countArray = new int[10];
        String str = Integer.toString(number);

        for (int i = 0; i < str.length(); i++) {
            countArray[str.charAt(i) - '0']++;
        }

        int multiplier = 1;
        int result = 0;

        for (int i = 0; i < countArray.length; i++) {
            while (countArray[i] > 0) {
                result += (multiplier * i);
                countArray[i]--;
                multiplier *= 10;
            }
        }

        return result;
    }

    public static int turnOffBit(int n, int k) {
        return (n & ~(1 << (k - 1)));
    }

    public static int convert0To5(int number) {
        if (number == 0) {
            return 5;
        }

        return convert0To5Helper(number);
    }

    private static int convert0To5Helper(int number) {
        if (number == 0) {
            return 0;
        }

        int lastDigit = number % 10;
        if (lastDigit == 0) {
            lastDigit = 5;
        }

        return convert0To5Helper(number / 10) * 10 + lastDigit;
    }

    public static int swapOddEvenBits(int number) {
        // 0xA = 1010 (10)
        int evenMask = 0xAAAAAAAA;

        // 0x5 = 0101 (5)
        int oddMask = 0x55555555;

        int evenBits = number & evenMask;
        int oddBits = number & oddMask;

        // right shift even & left shift odd
        evenBits >>= 1;
        oddBits <<= 1;

        number = evenBits | oddBits;
        return number;
    }

    public static int addOneWithoutMathOperators(int number) {
        // Approach 1: 2's complement approach; since ~x = -(x+1)
        //return -(~number);

        // Approach 2: Flip all digits after the rightmost 0. Then flip the rightmost 0.
        int m = 1;
        while ((number & m) >= 1) {
            number ^= m;
            m <<= 1;
        }

        number ^= m;
        return number;
    }

    public static int nextSparse(int number) {
        // A number is Sparse if there are no two adjacent 1s in its binary representation.

        // Approach 1: Incrementally check all numbers (isSpare) starting from number. The next sparse number is a max of O(x) distance away.

        // Approach 2:
        ArrayList<Boolean> binary = new ArrayList<>();
        while (number > 0) {
            binary.add((number & 1) == 1 ? true : false);
            number >>= 1;
        }
        binary.add(false);

        int lastSet = 0;
        for (int i = 1; i < binary.size(); i++) {
            if (binary.get(i - 1) == true
                    && binary.get(i) == true
                    && binary.get(i + 1) == false) {

                binary.set(i + 1, true);
                for (int j = i; j >= lastSet; j--) {
                    binary.set(j, false);
                }

                lastSet = i + 1;
            }
        }

        int answer = 0;
        for (int i = 0; i < binary.size(); i++) {
            int value = binary.get(i) ? 1 : 0;
            answer += value * (1 << i);
        }

        return answer;
    }

    public static boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }

        if (n == 1) {
            return true;
        }

        n = divideAsPossible(n, 2);
        n = divideAsPossible(n, 3);
        n = divideAsPossible(n, 5);

        if (n == 1) {
            return true;
        }

        return false;
    }

    private static int divideAsPossible(int n, int d) {
        while (n % d == 0) {
            n /= d;
        }

        return n;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        double root = Math.sqrt(n);
        for (int i = 2; i <= root; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static int getNumberForBinaryRepresentation(String binary) {
        if (!StringAlgorithms.isBinaryString(binary)) {
            return -1;
        }

        int number = 0;
        int length = binary.length();
        for (int i = 0; i < length; i++) {
            char c = binary.charAt(length - i - 1);
            if (c == '1') {
                number += Math.pow(2, i);
            }
        }

        return number;
    }

    public static String getBinaryRepresentationForNumber(int number) {
        if (number < 0) {
            return null;
        }

        if (number == 0) {
            return "0";
        }

        StringBuilder binary = new StringBuilder();
        while (number > 0) {
            binary.insert(0, number % 2);
            number /= 2;
        }

        return binary.toString();
    }
}
