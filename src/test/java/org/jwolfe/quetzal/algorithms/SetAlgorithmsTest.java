package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.utilities.Utilities;
import org.jwolfe.quetzal.test.QuetzalAssertions;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SetAlgorithmsTest {
    @Test
    void getAllSubSets() {
        Set<Integer> set;
        Set<Set<Integer>> subsets;
        Set<Set<Integer>> expectedSubsets;

        set = Utilities.constructSet(1, 2, 3);
        expectedSubsets = Utilities.constructSet(
                new HashSet<>(),
                Utilities.constructSet(1),
                Utilities.constructSet(1, 2),
                Utilities.constructSet(1, 2, 3),
                Utilities.constructSet(1, 3),
                Utilities.constructSet(2),
                Utilities.constructSet(2, 3),
                Utilities.constructSet(3));
        subsets = SetAlgorithms.getAllSubsets(set);
        QuetzalAssertions.assertSetEquals(expectedSubsets, subsets);
        Utilities.printSetByLines(subsets);
    }
}