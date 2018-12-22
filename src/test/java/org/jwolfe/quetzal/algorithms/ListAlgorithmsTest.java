package org.jwolfe.quetzal.algorithms;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.utilities.Utilities;

class ListAlgorithmsTest {

	@Test
	void testGetAllPermutations() {
		List<Integer> list = Utilities.createArrayList(1, 2, 3);
		var allPermutations = ListAlgorithms.getAllPermutations(list);
		Utilities.printListByLines(allPermutations);
	}

}
