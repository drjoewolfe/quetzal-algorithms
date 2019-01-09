package org.jwolfe.quetzal.algorithms;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.utilities.Utilities;
import org.jwolfe.quetzal.test.QuetzalAssertions;

class ListAlgorithmsTest {

	@Test
	void getAllPermutations() {
		List<Integer> list = Utilities.createArrayList(1, 2, 3);
		var allPermutations = ListAlgorithms.getAllPermutations(list);
		Utilities.printListByLines(allPermutations);
	}

	@Test
	void getAllSubsets() {
		List<Integer> list = Utilities.createArrayList(1, 2, 3);
		var allSubsets = ListAlgorithms.getAllSubsets(list);
		Utilities.printListByLines(allSubsets);
	}

	@Test
	void getAllSubsetsSorted() {
		List<Integer> list = Utilities.createArrayList(1, 2, 3);
		var allSubsets = ListAlgorithms.getAllSubsetsSorted(list);
		Utilities.printListByLines(allSubsets);
	}

	@Test
	void getAllSubSets() {
		Set<Integer> set;
		Set<Set<Integer>> subsets;
		Set<Set<Integer>> expectedSubsets;

		set = Utilities.constructSet(1, 2, 3);
		expectedSubsets = Utilities.constructSet(
				new TreeSet<>(),
				Utilities.constructSet(1),
				Utilities.constructSet(1, 2),
				Utilities.constructSet(1, 2, 3),
				Utilities.constructSet(1, 3),
				Utilities.constructSet(2),
				Utilities.constructSet(2, 3),
				Utilities.constructSet(3));
		subsets = ListAlgorithms.getAllSubsets(set);
		QuetzalAssertions.assertSetEquals(expectedSubsets, subsets);
		Utilities.printSetByLines(subsets);
	}
}
