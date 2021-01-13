package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class BoatsToSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        if(people == null || people.length == 0 || limit < 1) {
            return 0;
        }

        int boats = 0;
        Arrays.sort(people);

        int i = 0;
        int j = people.length - 1;
        while(i <= j) {
            int a = people[j];
            int b = people[i];

            if(b + a <= limit) {
                i++;
                j--;
            } else {
                j--;
            }

            boats++;
        }

        return boats;
    }
}