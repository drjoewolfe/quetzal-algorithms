package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class IteratorForCombination {
    class CombinationIterator {
        String characters;
        int size;
        int combinationLength;
        int mask;
        boolean hasNextCombination;

        public CombinationIterator(String characters, int combinationLength) {
            this.characters = characters;
            this.size = characters.length();
            this.combinationLength = combinationLength;

            mask = 0;
            for(int i = 0; i < combinationLength; i++) {
                mask |= (1 << (size - i - 1));
            }

            hasNextCombination = true;
        }

        public String next() {
            StringBuilder builder = new StringBuilder();
            for(int i = size - 1; i >= 0; i--) {
                if((mask & (1 << i)) > 0) {
                    builder.append(characters.charAt(size - i - 1));
                }
            }

            generateNextMask();
            return builder.toString();
        }

        public boolean hasNext() {
            return hasNextCombination;
        }

        private void generateNextMask() {
            int rightMostBit = getRightMostBit(mask);
            if(rightMostBit > 1) {
                // The last bit is not set. Just right shift
                mask ^= rightMostBit;
                mask |= (rightMostBit >> 1);
            } else {
                // Last bit is set
                if((mask & (mask + 1)) == 0) {
                    // no more combinations
                    hasNextCombination = false;
                    return;
                }

                int trailingClusterSize = 0;
                int position = 0;
                while((mask & (1 << position)) > 0) {
                    // Unset set trailing cluster bit
                    mask ^= (1 << position);

                    trailingClusterSize++;
                    position++;
                }

                rightMostBit = getRightMostBit(mask);

                // Right shift rightmost set bit
                mask ^= rightMostBit;
                rightMostBit >>= 1;
                mask |= rightMostBit;

                while(trailingClusterSize > 0) {
                    rightMostBit >>= 1;
                    mask |= rightMostBit;

                    trailingClusterSize--;
                }
            }
        }

        private int getRightMostBit(int n) {
            return n & ~(n - 1);
        }

        private String getCurrentString() {
            StringBuilder builder = new StringBuilder();
            for(int i = size - 1; i >= 0; i--) {
                if((mask & (1 << i)) > 0) {
                    builder.append(characters.charAt(size - i - 1));
                }
            }

            return builder.toString();
        }

        private void printMask() {
            for(int i = size - 1; i >= 0; i--) {
                if((mask & (1 << i)) > 0) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }

            System.out.print(" (" + getCurrentString() + ")");
            System.out.println();
        }
    }

    class CombinationIterator_GenerateCombinations {
        List<String> combinations;
        int index;

        public CombinationIterator_GenerateCombinations(String characters, int combinationLength) {
            combinations = new ArrayList<>();
            generateCombinations(characters, combinationLength);
        }

        public String next() {
            return combinations.get(index++);
        }

        public boolean hasNext() {
            return index < combinations.size();
        }

        private void generateCombinations(String characters, int combinationLength) {
            combinations.clear();
            StringBuilder builder = new StringBuilder();

            generateCombinations(characters, combinationLength, 0, builder);
        }

        private void generateCombinations(String characters, int combinationLength, int index, StringBuilder builder) {
            if(builder.length() == combinationLength) {
                combinations.add(builder.toString());
                return;
            }

            for(int i = index; i < characters.length(); i++) {
                char c = characters.charAt(i);
                builder.append(c);
                generateCombinations(characters, combinationLength, i + 1, builder);

                builder.deleteCharAt(builder.length() - 1);
            }
        }
    }

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}

//    1286. Iterator for Combination
//    Medium
//    Design the CombinationIterator class:
//
//    CombinationIterator(string characters, int combinationLength) Initializes the object with a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
//    next() Returns the next combination of length combinationLength in lexicographical order.
//    hasNext() Returns true if and only if there exists a next combination.
//
//
//    Example 1:
//
//    Input
//    ["CombinationIterator", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
//    [["abc", 2], [], [], [], [], [], []]
//    Output
//    [null, "ab", true, "ac", true, "bc", false]
//
//    Explanation
//    CombinationIterator itr = new CombinationIterator("abc", 2);
//    itr.next();    // return "ab"
//    itr.hasNext(); // return True
//    itr.next();    // return "ac"
//    itr.hasNext(); // return True
//    itr.next();    // return "bc"
//    itr.hasNext(); // return False
//
//
//    Constraints:
//
//    1 <= combinationLength <= characters.length <= 15
//    All the characters of characters are unique.
//    At most 104 calls will be made to next and hasNext.
//    It's guaranteed that all calls of the function next are valid.