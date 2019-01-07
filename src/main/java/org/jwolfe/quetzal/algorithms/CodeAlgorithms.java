package org.jwolfe.quetzal.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CodeAlgorithms {
    public static List<String> getGrayCodes(int numBits) {
        // Algorithm:
        // n-bit gray codes can be generated from (n-1) bit codes
        //  1) Let L1 be the n-1 bit gray codes
        //  2) L2 = reverse(L1)
        //  3) Prepend 0 to all elements of L1 -> L1'
        //  4) Prepend 1 to all elements of L2 -> L2'
        //  5) Concatenate L1' & L2' for the n-bit gray codes

        if (numBits <= 0) {
            return null;
        }

        List<String> grayCodes = new ArrayList<>();
        grayCodes.add("0");
        grayCodes.add("1");

        for (int i = 2; i <= numBits; i++) {
            int length = grayCodes.size();
            for (int j = length - 1; j >= 0; j--) {
                grayCodes.add(grayCodes.get(j));
            }

            // Prepend "0" to the first half
            for (int j = 0; j < length; j++) {
                grayCodes.set(j, "0" + grayCodes.get(j));
            }

            // Prepend "1" to the second half
            for (int j = length; j < 2 * length; j++) {
                grayCodes.set(j, "1" + grayCodes.get(j));
            }
        }

        return grayCodes;
    }

    public static List<Integer> getGrayCodeValues(int numBits) {
        // Back-tracking implementation

        if (numBits <= 0) {
            return null;
        }

        List<Integer> grayCodes = new ArrayList<>();
        generateGrayCodeValues(numBits, new AtomicInteger(0), grayCodes);
        return grayCodes;
    }

    public static void generateGrayCodeValues(int n, AtomicInteger number, List<Integer> grayCodes) {
        if (n == 0) {
            grayCodes.add(number.intValue());

            return;
        }

        // Without inverting nth bit
        generateGrayCodeValues(n - 1, number, grayCodes);

        // Inverting nth bit
        int value = number.intValue();
        value = value ^ (1 << (n - 1));
        number.set(value);
        generateGrayCodeValues(n - 1, number, grayCodes);
    }

    public static String grayToBinary(String grayCode) {
        // Algorithm:
        //  1) MSB of binary & gray codes are the same
        //  2) For each bit post the MSB, generate the gray code bit by checking the binary code bit
        //      a) If gray is "0", binary is the previous bit
        //      b) If gray is "1", binary is the inverse of the previous bit

        if (!StringAlgorithms.isBinaryString(grayCode)) {
            return null;
        }

        StringBuilder binaryCode = new StringBuilder();
        binaryCode.append(grayCode.charAt(0));
        for (int i = 1; i < grayCode.length(); i++) {
            char g = grayCode.charAt(i);
            char pb = binaryCode.charAt(i - 1);

            if (g == '0') {
                binaryCode.append(pb);
            } else {
                binaryCode.append(pb == '0' ? '1' : '0');
            }
        }

        return binaryCode.toString();
    }

    public static String binaryToGray(String binaryCode) {
        // Algorithm:
        //  1) MSB of binary & gray codes are the same
        //  2) For each bit post the MSB, the gray bit is the XOR of the current & previous binary bits

        if (!StringAlgorithms.isBinaryString(binaryCode)) {
            return null;
        }

        StringBuilder grayCode = new StringBuilder();
        grayCode.append(binaryCode.charAt(0));

        for (int i = 1; i < binaryCode.length(); i++) {
            char b = binaryCode.charAt(i);
            char pb = binaryCode.charAt(i - 1);

            grayCode.append(b ^ pb);
        }

        return grayCode.toString();
    }

    public static int grayToNumber(String grayCode) {
        String binaryCode = grayToBinary(grayCode);
        if (binaryCode == null) {
            return -1;
        }

        return NumberAlgorithms.getNumberForBinaryRepresentation(binaryCode);
    }

    public static String numberToGray(int number) {
        String binaryCode = NumberAlgorithms.getBinaryRepresentationForNumber(number);
        return binaryToGray(binaryCode);
    }

    public static int grayValueToNumber(int grayValue) {
        if (grayValue <= 0) {
            return 0;
        }

        int number = 0;
        while (grayValue > 0) {
            number ^= grayValue;
            grayValue = grayValue >> 1;
        }

        return number;
    }

    public static int numberToGrayValue(int number) {
        if (number <= 0) {
            return 0;
        }

        return number ^ (number >> 1);
    }
}