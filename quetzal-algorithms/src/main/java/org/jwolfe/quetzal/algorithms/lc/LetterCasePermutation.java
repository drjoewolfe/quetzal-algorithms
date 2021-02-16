package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {
    public List<String> letterCasePermutation(String S) {
        List<String> permutations = new ArrayList<>();
        if(S == null || S.length() == 0) {
            return permutations;
        }

        generateLetterCasePermutations(S, 0, new StringBuilder(), permutations);
        return permutations;
    }

    private void generateLetterCasePermutations(String S, int index, StringBuilder current, List<String> permutations) {
        if(index == S.length()) {
            permutations.add(current.toString());
            return;
        }

        char c = S.charAt(index);
        if(Character.isDigit(c)) {
            current.append(c);
            generateLetterCasePermutations(S, index + 1, current, permutations);
            current.deleteCharAt(current.length() - 1);
        } else {
            // Lowercase
            current.append(Character.toLowerCase(c));
            generateLetterCasePermutations(S, index + 1, current, permutations);
            current.deleteCharAt(current.length() - 1);

            // Uppercase
            current.append(Character.toUpperCase(c));
            generateLetterCasePermutations(S, index + 1, current, permutations);
            current.deleteCharAt(current.length() - 1);
        }
    }
}
