package org.jwolfe.quetzal.algorithms;

import java.util.ArrayList;
import java.util.List;

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
}
