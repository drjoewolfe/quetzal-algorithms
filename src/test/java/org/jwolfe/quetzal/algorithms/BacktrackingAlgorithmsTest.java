package org.jwolfe.quetzal.algorithms;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.utilities.Utilities;
import org.jwolfe.quetzal.test.QuetzalAssertions;

class BacktrackingAlgorithmsTest {

	@Test
	void generateSubsetsWithGivenSum() {
		int[] arr;
		int sum;
		List<Set<Integer>> subsets;
				
		arr = Utilities.constructArray(1, 2, 3);
		sum = 6;
		subsets = BacktrackingAlgorithms.generateSubsetsWithGivenSum(arr, sum);
		assertNotNull(subsets);
		assertEquals(1, subsets.size());
		QuetzalAssertions.assertSetEquals(Utilities.constructSet(1, 2, 3), subsets.get(0));
		
		arr = Utilities.constructArray(1, 2, 3);
		sum = 2;
		subsets = BacktrackingAlgorithms.generateSubsetsWithGivenSum(arr, sum);
		assertNotNull(subsets);
		assertEquals(1, subsets.size());
		QuetzalAssertions.assertSetEquals(Utilities.constructSet(2), subsets.get(0));
				
		arr = Utilities.constructArray(15, 22, 14, 26, 32, 9, 16, 8);
		sum = 53;
		subsets = BacktrackingAlgorithms.generateSubsetsWithGivenSum(arr, sum);
		assertNotNull(subsets);
		assertEquals(3, subsets.size());
		QuetzalAssertions.assertSetEquals(Utilities.constructSet(15, 16, 22), subsets.get(0));
		QuetzalAssertions.assertSetEquals(Utilities.constructSet(8, 14, 15, 16), subsets.get(1));
		QuetzalAssertions.assertSetEquals(Utilities.constructSet(8, 9, 14, 22), subsets.get(2));
	}
}
