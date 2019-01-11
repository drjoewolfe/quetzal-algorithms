package org.jwolfe.quetzal.algorithms;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

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
	void getDistinctSubsets() {
		List<Integer> list;
		List<List<Integer>> subsets;
		List<List<Integer>> expectedSubsets;

		list = Utilities.constructList(1, 2, 2);
		expectedSubsets = Utilities.constructList(new ArrayList<>(), Utilities.constructList(1), Utilities.constructList(2), Utilities.constructList(1, 2),
				Utilities.constructList(2, 2), Utilities.constructList(1, 2, 2));
		subsets = ListAlgorithms.getDistinctSubsets(list);
		QuetzalAssertions.assertListEquals(expectedSubsets, subsets);
	}
}
