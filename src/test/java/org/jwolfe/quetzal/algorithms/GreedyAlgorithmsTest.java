package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.general.Activity;
import org.jwolfe.quetzal.library.general.Pair;

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
}