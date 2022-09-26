package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class SatisfiabilityOfEqualityEquations {
    class Solution {
        public boolean equationsPossible(String[] equations) {
            if(equations == null || equations.length == 0) {
                return true;
            }

            DisjointSets sets = new DisjointSets();

            for(String equation : equations) {
                char op = equation.charAt(1);
                if(op == '=') {
                    int u = equation.charAt(0) - 'a';
                    int v = equation.charAt(3) - 'a';
                    sets.union(u, v);
                }
            }

            for(String equation : equations) {
                char op = equation.charAt(1);
                if(op == '!') {
                    int u = equation.charAt(0) - 'a';
                    int v = equation.charAt(3) - 'a';

                    int ur = sets.find(u);
                    int vr = sets.find(v);

                    if(ur == vr) {
                        return false;
                    }
                }
            }

            return true;
        }

        private class DisjointSets {
            int[] parent;

            public DisjointSets() {
                parent = new int[26];
                for(int i = 0; i < 26; i++) {
                    parent[i] = i;
                }
            }

            public void union(int u, int v) {
                int root1 = find(u);
                int root2 = find(v);

                if(root1 != root2) {
                    parent[root2] = root1;
                }
            }

            public int find(int u) {
                while(parent[u] != u) {
                    u = parent[u];
                }

                return u;
            }
        }
    }

    class Solution_Wrong {
        public boolean equationsPossible(String[] equations) {
            if(equations == null || equations.length == 0) {
                return true;
            }

            Map<Character, Integer> map = new HashMap<>();

            int nextVal = 1;
            for(int i = 0; i < equations.length; i++) {
                System.out.println(map);
                String equation = equations[i];

                char c1 = equation.charAt(0);
                char c2 = equation.charAt(3);

                if(equation.charAt(1) == '!') {
                    // !=
                    if(!map.containsKey(c1)) {
                        map.put(c1, nextVal++);
                    }

                    if(!map.containsKey(c2)) {
                        map.put(c2, nextVal++);
                    }

                    if(map.get(c1) == map.get(c2)) {
                        return false;
                    }

                } else {
                    // ==
                    if(map.containsKey(c1) && map.containsKey(c2)) {
                        if(map.get(c1) != map.get(c2)) {
                            return false;
                        }
                    } else {
                        if(!map.containsKey(c1) && !map.containsKey(c2)) {
                            map.put(c1, nextVal);
                            map.put(c2, nextVal);
                            nextVal++;
                        } else if(!map.containsKey(c1)) {
                            map.put(c1, map.get(c2));
                        } else if(!map.containsKey(c2)) {
                            map.put(c2, map.get(c1));
                        }
                    }
                }
            }

            return true;
        }
    }


// ["a==b","b!=a"]
// ["a!=a"]
// ["c==c","f!=a","f==b","b==c"]
// ["a==b","b!=c","c==a"]
}

//    990. Satisfiability of Equality Equations
//    Medium
//    You are given an array of strings equations that represent relationships between variables where each string equations[i] is of length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".Here, xi and yi are lowercase letters (not necessarily different) that represent one-letter variable names.
//
//    Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, or false otherwise.
//
//
//
//    Example 1:
//
//    Input: equations = ["a==b","b!=a"]
//    Output: false
//    Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.
//    There is no way to assign the variables to satisfy both equations.
//    Example 2:
//
//    Input: equations = ["b==a","a==b"]
//    Output: true
//    Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
//
//
//    Constraints:
//
//    1 <= equations.length <= 500
//    equations[i].length == 4
//    equations[i][0] is a lowercase letter.
//    equations[i][1] is either '=' or '!'.
//    equations[i][2] is '='.
//    equations[i][3] is a lowercase letter.