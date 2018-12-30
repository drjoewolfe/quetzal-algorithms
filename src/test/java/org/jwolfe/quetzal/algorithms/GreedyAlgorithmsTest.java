package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.general.Activity;
import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.general.Triplet;
import org.jwolfe.quetzal.library.utilities.Utilities;
import org.jwolfe.quetzal.test.QuetzalAssertions;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GreedyAlgorithmsTest {

    @Test
    void selectActivities() {
        int[] startTimes =  {1, 3, 0, 5, 8, 5};
        int[] finishTimes =  {2, 4, 6, 7, 9, 9};

        var selectedActivities = GreedyAlgorithms.selectActivities(startTimes, finishTimes);
        for(int i = 0; i<selectedActivities.size(); i++) {
            Activity activity = selectedActivities.get(i);
            System.out.println(i + ": " + activity.getStart() + ", " + activity.getFinish());
        }
    }

    @Test
    void fractionalKnapSack() {
        List<Pair<Integer, Integer>> items = new LinkedList<>();
        items.add(new Pair<>(60, 10));
        items.add(new Pair<>(100, 20));
        items.add(new Pair<>(120, 30));

        double maxValue = GreedyAlgorithms.fractionalKnapSack(50, items);
        System.out.println("Max value: " + maxValue);
        assertEquals(240, maxValue);
    }

    @Test
    void assignMiceToHoles() {
        int[] micePositions;
        int[] holePositions;
        int totalTime;

        micePositions = Utilities.constructArray(4, -4, 2);
        holePositions = Utilities.constructArray(4, 0, 5);
        totalTime = GreedyAlgorithms.assignMiceToHoles(micePositions, holePositions);
        assertEquals(4, totalTime);

        micePositions = Utilities.constructArray(-10, -79, -79, 67, 93, -85, -28, -94);
        holePositions = Utilities.constructArray(-2, 9, 69, 25, -31, 23, 50, 78);
        totalTime = GreedyAlgorithms.assignMiceToHoles(micePositions, holePositions);
        assertEquals(102, totalTime);
    }
    
    @Test
    void getThievesCaughtByPolice() {
        char[] arr;
        int theivesCaught;

        arr = Utilities.constructArray('P', 'T', 'T', 'P', 'T');
        theivesCaught = GreedyAlgorithms.getThievesCaughtByPolice(arr, 1);
        assertEquals(2, theivesCaught);
        
        arr = Utilities.constructArray('T', 'T', 'P', 'P', 'T', 'P');
        theivesCaught = GreedyAlgorithms.getThievesCaughtByPolice(arr, 2);
        assertEquals(3, theivesCaught);
        
        arr = Utilities.constructArray('P', 'T', 'P', 'T', 'T', 'P');
        theivesCaught = GreedyAlgorithms.getThievesCaughtByPolice(arr, 3);
        assertEquals(3, theivesCaught);
    }
    
    @Test
    void fitWallWithShelvesForMinimumRemainingWidth() {
    	Triplet<Integer, Integer, Integer> result;
    	
		result = GreedyAlgorithms.fitWallWithShelvesForMinimumRemainingWidth(24, 5, 3);
		assertNotNull(result);
		assertEquals(3, (int) result.getFirst());
		assertEquals(3, (int) result.getSecond());
		assertEquals(0, (int) result.getThird());
		
		result = GreedyAlgorithms.fitWallWithShelvesForMinimumRemainingWidth(29, 9, 3);
		assertNotNull(result);
		assertEquals(3, (int) result.getFirst());
		assertEquals(0, (int) result.getSecond());
		assertEquals(2, (int) result.getThird());
    }

    @Test
    void getSubsetCover() {
        Set<Integer> universe;
        Map<Set<Integer>, Integer> setCosts;
        Set<Set<Integer>> cover;
        Set<Set<Integer>> expectedCover;

        // Optimal solution
        universe = Utilities.constructSet(1,2,3,4,5);
        var setA1 = Utilities.constructSet(4,1,3);
        var setA2 = Utilities.constructSet(2,5);
        var setA3 = Utilities.constructSet(1,4,3,2);
        setCosts = new HashMap<>();
        setCosts.put(setA1, 5);
        setCosts.put(setA2, 10);
        setCosts.put(setA3, 3);
        expectedCover = Utilities.constructSet(setA2, setA3);
        cover = GreedyAlgorithms.getSubsetCover(universe, setCosts);
        assertNotNull(cover);
        QuetzalAssertions.assertSetEquals(expectedCover, cover);

        // Approximate solution
        universe = Utilities.constructSet(1,2,3,4,5, 6, 7, 8, 9, 10, 11, 12, 13);
        var setB1 = Utilities.constructSet(1, 2);
        var setB2 = Utilities.constructSet(2, 3, 4, 5);
        var setB3 = Utilities.constructSet(6, 7, 8, 9, 10, 11, 12, 13);
        var setB4 = Utilities.constructSet(1, 3, 5, 7, 9, 11, 13);
        var setB5 = Utilities.constructSet(2, 4, 6, 8, 10, 12, 13);
        setCosts = new HashMap<>();
        setCosts.put(setB1, 5);
        setCosts.put(setB2, 5);
        setCosts.put(setB3, 5);
        setCosts.put(setB4, 5);
        setCosts.put(setB5, 5);
        expectedCover = Utilities.constructSet(setB3, setB2, setB4);
        cover = GreedyAlgorithms.getSubsetCover(universe, setCosts);
        assertNotNull(cover);
        QuetzalAssertions.assertSetEquals(expectedCover, cover);
    }

    @Test
    void getMinimumSwapsForBracketBalancings() {
        assertEquals(2, GreedyAlgorithms.getMinimumSwapsForBracketBalancing("[]][]["));
        assertEquals(0, GreedyAlgorithms.getMinimumSwapsForBracketBalancing("[[][]]"));
    }

    @Test
    void getMinimumSwapsForBracketBalancingA2() {
        assertEquals(2, GreedyAlgorithms.getMinimumSwapsForBracketBalancingA2("[]][]["));
        assertEquals(0, GreedyAlgorithms.getMinimumSwapsForBracketBalancingA2("[[][]]"));
    }
}