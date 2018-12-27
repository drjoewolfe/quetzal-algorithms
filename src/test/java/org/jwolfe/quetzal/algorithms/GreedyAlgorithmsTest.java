package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.general.Activity;
import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.LinkedList;
import java.util.List;

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
}