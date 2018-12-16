package org.jwolfe.quetzal.algorithms;

import org.jwolfe.quetzal.library.general.Activity;
import org.jwolfe.quetzal.library.utilities.ActivityFinishComparator;
import org.jwolfe.quetzal.library.general.Pair;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class GreedyAlgorithms {

    public static List<Activity> selectActivities(int[] startTimes, int[] finishTimes) {
        int length = startTimes.length;
        if(length != finishTimes.length) {
            return null;
        }

        List<Activity> activities = new LinkedList<>();
        for(int i = 0; i<startTimes.length; i++) {
            Activity activity = new Activity(startTimes[i], finishTimes[i]);
            activities.add(activity);
        }

        return selectActivities(activities);
    }

    public static List<Activity> selectActivities(List<Activity> activities) {
        if (activities == null
                || activities.size() == 0) ;

        List<Activity> selectedActivities = new LinkedList<>();
        activities.sort(new ActivityFinishComparator());

        var previousActivity = activities.get(0);
        selectedActivities.add(previousActivity);
        for (int i = 1; i < activities.size(); i++) {
            Activity activity = activities.get(i);
            if(activity.getStart() > previousActivity.getFinish()) {
                previousActivity = activity;
                selectedActivities.add(activity);
            }
        }

        return selectedActivities;
    }

    public static double fractionalKnapSack(int knapsackWeight, List<Pair<Integer, Integer>> items) {
        // Each pair is of the form (value, weight)

        if(items == null
                || items.size() == 0) {
            return 0;
        }

        Collections.sort(items, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                return (p2.getFirst() / p2.getSecond()) - (p1.getFirst() / p1.getSecond());
            }
        });

        double value = 0;
        int weight = 0;
        for (int i = 0; i < items.size(); i++) {
            var item = items.get(i);
            if(weight + item.getSecond() <= knapsackWeight ) {
                weight += item.getSecond();
                value += item.getFirst();
            }
            else {
                int remainingWeight = knapsackWeight - weight;
                value += item.getFirst() * remainingWeight / item.getSecond();
            }
        }

        return value;
    }
}
