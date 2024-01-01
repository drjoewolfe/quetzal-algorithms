package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.PriorityQueue;

public class AssignCookies {
    class Solution {
        public int findContentChildren(int[] g, int[] s) {
            if(g == null || g.length == 0 || s == null || s.length == 0) {
                return 0;
            }

            int gn = g.length;
            int sn = s.length;

            Arrays.sort(g);
            Arrays.sort(s);

            int gi = 0;
            int si = 0;

            int contentChildren = 0;
            while(gi < gn) {
                while(si < sn
                        && g[gi] > s[si]) {
                    si++;
                }

                if(si < sn) {
                    contentChildren++;
                    si++;
                }

                gi++;
            }

            return contentChildren;
        }
    }

    class Solution_Correct_1 {
        public int findContentChildren(int[] g, int[] s) {
            if(g == null || g.length == 0 || s == null || s.length == 0) {
                return 0;
            }

            Arrays.sort(g);
            Arrays.sort(s);

            int gi = g.length - 1;
            int si = s.length - 1;
            int contentChildren = 0;

            while(gi >= 0 && si >= 0) {
                int size = s[si];
                while(gi >= 0) {
                    int greedFactor = g[gi];
                    gi--;

                    if(size >= greedFactor) {
                        contentChildren++;
                        break;
                    }
                }

                si--;
            }

            return contentChildren;
        }
    }

    class Solution_Heap {
        public int findContentChildren(int[] g, int[] s) {
            if(g == null || g.length == 0 || s == null || s.length == 0) {
                return 0;
            }

            PriorityQueue<Integer> greedHeap = new PriorityQueue<>((a, b) -> b - a);
            PriorityQueue<Integer> sizeHeap = new PriorityQueue<>((a, b) -> b - a);

            for(int v : g) {
                greedHeap.offer(v);
            }

            for(int v : s) {
                sizeHeap.offer(v);
            }

            int contentChildren = 0;
            while(!greedHeap.isEmpty() && !sizeHeap.isEmpty()) {
                int size = sizeHeap.poll();
                while(!greedHeap.isEmpty()) {
                    int greedFactor = greedHeap.poll();
                    if(size >= greedFactor) {
                        contentChildren++;
                        break;
                    }
                }
            }

            return contentChildren;
        }
    }
}

//    455. Assign Cookies
//    Easy
//    Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.
//
//    Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.
//
//
//
//    Example 1:
//
//    Input: g = [1,2,3], s = [1,1]
//    Output: 1
//    Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.
//    And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
//    You need to output 1.
//    Example 2:
//
//    Input: g = [1,2], s = [1,2,3]
//    Output: 2
//    Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2.
//    You have 3 cookies and their sizes are big enough to gratify all of the children,
//    You need to output 2.
//
//
//    Constraints:
//
//    1 <= g.length <= 3 * 104
//    0 <= s.length <= 3 * 104
//    1 <= g[i], s[j] <= 231 - 1