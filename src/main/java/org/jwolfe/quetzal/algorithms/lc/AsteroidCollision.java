package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AsteroidCollision {
    class Solution {
        public int[] asteroidCollision(int[] asteroids) {
            if(asteroids == null || asteroids.length == 0) {
                return new int[0];
            }

            Stack<Integer> stack = new Stack<>();
            for(int a : asteroids) {
                if(stack.isEmpty()) {
                    stack.push(a);
                } else {
                    boolean crushed = false;
                    while(!stack.isEmpty()
                            && willCollide(stack.peek(), a)) {
                        int b = stack.peek();

                        int aa = Math.abs(a);
                        int ab = Math.abs(b);

                        if(aa > ab) {
                            // new will prevail
                            stack.pop();
                        } else if(aa < ab) {
                            // curr will prevail
                            crushed = true;
                            break;
                        } else {
                            // both will eliminate each other
                            stack.pop();
                            crushed = true;
                            break;
                        }
                    }

                    if(!crushed) {
                        stack.push(a);
                    }
                }
            }

            int n = stack.size();
            int[] configuration = new int[n];
            int index = n - 1;
            while(!stack.isEmpty()) {
                configuration[index--] = stack.pop();
            }

            return configuration;
        }

        private boolean willCollide(int asteroid1, int asteroid2) {
            if(asteroid1 < 0 || asteroid2 > 0) {
                return false;
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public int[] asteroidCollision(int[] asteroids) {
            if(asteroids == null || asteroids.length < 2) {
                return asteroids;
            }

            Stack<Integer> stack = new Stack<>();

            for(int a : asteroids) {
                if(stack.isEmpty()) {
                    stack.push(a);
                } else {
                    boolean crushed = false;
                    while(!stack.isEmpty() && inCollisionCourse(a, stack.peek())) {
                        int b = stack.peek();

                        int aa = Math.abs(a);
                        int ab = Math.abs(b);
                        if(aa > ab) {
                            stack.pop();
                        } else if(aa < ab) {
                            crushed = true;
                            break;
                        } else {
                            stack.pop();
                            crushed = true;
                            break;
                        }
                    }

                    if(!crushed) {
                        stack.push(a);
                    }
                }
            }

            int n = stack.size();
            int[] results = new int[n];
            for(int i = n - 1; i >= 0; i--) {
                results[i] = stack.pop();
            }

            return results;
        }

        private boolean inCollisionCourse(int a, int b) {
            if(b < 0 && a > 0) {
                return false;
            }

            if((a > 0 && b < 0)
                    || ( a < 0 && b > 0)) {
                return true;
            }

            return false;
        }
    }

    class Solution_Standard {
        public int[] asteroidCollision(int[] asteroids) {
            if(asteroids == null || asteroids.length < 2) {
                return asteroids;
            }

            int n = asteroids.length;
            List<Integer> list = new ArrayList<>();
            for(int a : asteroids) {
                list.add(a);
            }

            boolean flag = false;

            while(!flag) {
                flag = true;
                for(int i = 0; i < list.size() - 1; i++) {
                    int a = list.get(i);
                    int b = list.get(i + 1);

                    if((a < 0) || (a > 0 && b > 0)) {
                        continue;
                    }

                    // System.out.println(a + " : " + b);
                    flag = false;

                    int aa = Math.abs(a);
                    int ab = Math.abs(b);
                    if(aa > ab) {
                        list.remove(i + 1);
                    } else if(ab > aa) {
                        list.remove(i);
                    } else {
                        list.remove(i + 1);
                        list.remove(i);
                    }

                    // System.out.println(list);
                }
            }

            int[] results = new int[list.size()];
            for(int i = 0; i < list.size(); i++) {
                results[i] = list.get(i);
            }

            return results;
        }
    }

// [5,10,-5]
// [8,-8]
// [-2,-1,1,2]
}

//    735. Asteroid Collision
//    Medium
//    We are given an array asteroids of integers representing asteroids in a row.
//
//    For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
//
//    Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
//
//
//
//    Example 1:
//
//    Input: asteroids = [5,10,-5]
//    Output: [5,10]
//    Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
//    Example 2:
//
//    Input: asteroids = [8,-8]
//    Output: []
//    Explanation: The 8 and -8 collide exploding each other.
//    Example 3:
//
//    Input: asteroids = [10,2,-5]
//    Output: [10]
//    Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
//
//
//    Constraints:
//
//    2 <= asteroids.length <= 104
//    -1000 <= asteroids[i] <= 1000
//    asteroids[i] != 0