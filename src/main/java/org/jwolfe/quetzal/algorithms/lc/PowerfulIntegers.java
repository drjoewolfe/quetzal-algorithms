package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PowerfulIntegers {
    class Solution {
        public List<Integer> powerfulIntegers(int x, int y, int bound) {
            int p = (x==1) ? 1 : (int) Math.ceil(Math.log10(bound) / Math.log10(x));
            int q = (y==1) ? 1 : (int) Math.ceil(Math.log10(bound) / Math.log10(y));
            int r = (int) Math.max(p,q);

            Set<Integer> set = new HashSet<>();
            for(int i = 0; i <= r; i++) {
                for(int j = 0; j <= r; j++) {
                    int v = (int) (Math.pow(x, i) + Math.pow(y, j));
                    if(v <= bound) {
                        set.add(v);
                    }
                }
            }

            return new ArrayList<>(set);
        }
    }

    class Solution_Brute {
        public List<Integer> powerfulIntegers(int x, int y, int bound) {
            if(x < 1 || y < 1 || bound < 2) {
                return null;
            }

            Set<Integer> result = new HashSet<>();
            int i = 0;
            while(true) {
                int j = 0;
                if(Math.pow(x, i) + Math.pow(y, j) > bound) {
                    break;
                }

                while(true) {
                    double val = Math.pow(x, i) + Math.pow(y, j);
                    if(val <= bound) {
                        result.add((int) val);
                    } else {
                        break;
                    }

                    j++;
                }

                i++;
            }

            return new ArrayList<>(result);
        }
    }
}

//    970. Powerful Integers
//    Medium
//    Given three integers x, y, and bound, return a list of all the powerful integers that have a value less than or equal to bound.
//
//    An integer is powerful if it can be represented as xi + yj for some integers i >= 0 and j >= 0.
//
//    You may return the answer in any order. In your answer, each value should occur at most once.
//
//
//
//    Example 1:
//
//    Input: x = 2, y = 3, bound = 10
//    Output: [2,3,4,5,7,9,10]
//    Explanation:
//    2 = 20 + 30
//    3 = 21 + 30
//    4 = 20 + 31
//    5 = 21 + 31
//    7 = 22 + 31
//    9 = 23 + 30
//    10 = 20 + 32
//    Example 2:
//
//    Input: x = 3, y = 5, bound = 15
//    Output: [2,4,6,8,10,14]
//
//
//    Constraints:
//
//    1 <= x, y <= 100
//    0 <= bound <= 106