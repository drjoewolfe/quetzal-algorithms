package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class RotateImage {
    class Solution {
        public List<List<Integer>> pacificAtlantic(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return null;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            boolean[][] pacific = new boolean[m][n];
            boolean[][] atlantic = new boolean[m][n];

            for(int i = 0; i < m; i++) {
                markOceanAccess(matrix, i, 0, pacific);
                markOceanAccess(matrix, i, n - 1, atlantic);
            }

            for(int j = 0; j < n; j++) {
                markOceanAccess(matrix, 0, j, pacific);
                markOceanAccess(matrix, m - 1, j, atlantic);
            }

            List<List<Integer>> results = new ArrayList<>();
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(pacific[i][j] && atlantic[i][j]) {
                        List<Integer> cell = new ArrayList<>();
                        cell.add(i);
                        cell.add(j);

                        results.add(cell);
                    }
                }
            }

            return results;
        }

        private void markOceanAccess(int[][] matrix, int row, int col, boolean[][] ocean) {
            ocean[row][col] = true;

            if(row > 0 && !ocean[row - 1][col] && matrix[row][col] <= matrix[row - 1][col]) {
                markOceanAccess(matrix, row - 1, col, ocean);
            }

            if(row < matrix.length - 1 && !ocean[row + 1][col] && matrix[row][col] <= matrix[row + 1][col]) {
                markOceanAccess(matrix, row + 1, col, ocean);
            }

            if(col > 0 && !ocean[row][col - 1] && matrix[row][col] <= matrix[row][col - 1]) {
                markOceanAccess(matrix, row, col - 1, ocean);
            }

            if(col < matrix[0].length - 1 && !ocean[row][col + 1] && matrix[row][col] <= matrix[row][col + 1]) {
                markOceanAccess(matrix, row, col + 1, ocean);
            }
        }
    }

    class Solution_TLE {
        public List<List<Integer>> pacificAtlantic(int[][] matrix) {
            List<List<Integer>> results = new ArrayList<>();

            int m = matrix.length;
            int n = matrix[0].length;

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(canReachBothOceans(matrix, i, j, m, n)) {
                        List<Integer> cell = new ArrayList<>();
                        cell.add(i);
                        cell.add(j);

                        results.add(cell);
                    }
                }
            }

            return results;
        }

        private boolean canReachBothOceans(int[][] matrix, int row, int col, int m, int n) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[] {row, col});

            boolean canReachPacific = false;
            boolean canReachAtlantic = false;

            boolean[][] visited = new boolean[m][n];
            while(!queue.isEmpty()) {
                int[] cell = queue.poll();
                int i = cell[0];
                int j = cell[1];
                int height = matrix[i][j];

                visited[i][j] = true;

                if(i == 0 || j == 0) {
                    canReachPacific = true;
                }

                if(i == m - 1 || j == n - 1) {
                    canReachAtlantic = true;
                }

                if(canReachPacific && canReachAtlantic) {
                    return true;
                }

                if(i > 0 && !visited[i - 1][j] && height >= matrix[i - 1][j]) {
                    queue.offer(new int[] {i - 1, j});
                }

                if(i < m - 1 && !visited[i + 1][j] && height >= matrix[i + 1][j]) {
                    queue.offer(new int[] {i + 1, j});
                }

                if(j > 0 && !visited[i][j - 1] && height >= matrix[i][j - 1]) {
                    queue.offer(new int[] {i, j - 1});
                }

                if(j < n - 1 && !visited[i][j + 1] && height >= matrix[i][j + 1]) {
                    queue.offer(new int[] {i, j + 1});
                }
            }

            return false;
        }
    }

    class Solution_Correct_1 {
        public List<List<Integer>> pacificAtlantic(int[][] matrix) {
            List<List<Integer>> result = new ArrayList<>();
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return result;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            boolean[][] pacific = new boolean[m][n];
            boolean[][] atlantic = new boolean[m][n];

            // Pacific - row
            for(int i = 0; i < m; i++) {
                dfs(matrix, m, n, i, 0, pacific, Integer.MIN_VALUE);
            }

            // Pacific - col
            for(int j = 0; j < n; j++) {
                dfs(matrix, m, n, 0, j, pacific, Integer.MIN_VALUE);
            }

            // Atlantic - row
            for(int i = m - 1; i >= 0; i--) {
                dfs(matrix, m, n, i, n - 1, atlantic, Integer.MIN_VALUE);
            }

            // Atlantic - col
            for(int j = n - 1; j >= 0; j--) {
                dfs(matrix, m, n, m - 1, j, atlantic, Integer.MIN_VALUE);
            }

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(pacific[i][j] && atlantic[i][j]) {
                        List<Integer> pair = new ArrayList<>();
                        pair.add(i);
                        pair.add(j);
                        result.add(pair);
                    }
                }
            }

            return result;
        }

        private void dfs(int[][] matrix, int m, int n, int i, int j, boolean[][] ocean, int previous) {
            if(i < 0 || i > m - 1 || j < 0 || j > n - 1) {
                return;
            }

            if(matrix[i][j] < previous || ocean[i][j]) {
                return;
            }

            ocean[i][j] = true;

            dfs(matrix, m, n, i + 1, j, ocean, matrix[i][j]);
            dfs(matrix, m, n, i - 1, j, ocean, matrix[i][j]);
            dfs(matrix, m, n, i, j + 1, ocean, matrix[i][j]);
            dfs(matrix, m, n, i, j - 1, ocean, matrix[i][j]);
        }
    }

    class Solution_Incorrect {
        public List<List<Integer>> pacificAtlantic(int[][] matrix) {
            List<List<Integer>> result = new ArrayList<>();
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return result;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            boolean[][] pacific = new boolean[m][n];
            boolean[][] atlantic = new boolean[m][n];

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(i == 0 || j == 0) {
                        pacific[i][j] = true;
                    } else {
                        if((matrix[i][j] >= matrix[i - 1][j] && pacific[i - 1][j])
                                || (matrix[i][j] >= matrix[i][j - 1] && pacific[i][j - 1])) {
                            pacific[i][j] = true;
                        }
                    }
                }
            }

            for(int i = m - 1; i >= 0; i--) {
                for(int j = n - 1; j >= 0; j--) {
                    if(i == m - 1 || j == n - 1) {
                        atlantic[i][j] = true;
                    } else {
                        if((matrix[i][j] >= matrix[i + 1][j] && atlantic[i + 1][j])
                                || (matrix[i][j] >= matrix[i][j + 1] && atlantic[i][j + 1])) {
                            atlantic[i][j] = true;
                        }
                    }
                }
            }

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(pacific[i][j] && atlantic[i][j]) {
                        List<Integer> pair = new ArrayList<>();
                        pair.add(i);
                        pair.add(j);
                        result.add(pair);
                    }
                }
            }

            return result;
        }
    }

// [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]

    class Solution_Classic {
        public List<List<Integer>> pacificAtlantic(int[][] matrix) {
            List<List<Integer>> result = new ArrayList<>();
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return result;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            for(int i =0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(canReachPacificAndAtlantic(matrix, m, n, i, j)) {
                        List<Integer> pair = new ArrayList<>();
                        pair.add(i);
                        pair.add(j);
                        result.add(pair);
                    }
                }
            }

            return result;
        }

        private boolean canReachPacificAndAtlantic(int[][] matrix, int m, int n, int i, int j) {
            boolean reachPacific = false;
            boolean reachAtlantic = false;

            Point point = new Point(i, j);

            Queue<Point> queue = new LinkedList<>();
            queue.offer(point);

            Set<Point> visited = new HashSet<>();

            while(!queue.isEmpty()) {
                point = queue.poll();
                visited.add(point);

                int u = point.i;
                int v = point.j;

                if(u == 0 || v == 0) {
                    reachPacific = true;
                }

                if(u == m - 1 || v == n - 1) {
                    reachAtlantic = true;
                }

                if(reachPacific && reachAtlantic) {
                    return true;
                }

                if(u > 0) {
                    Point top = new Point(u - 1, v);
                    if(!visited.contains(top) && matrix[top.i][top.j] <= matrix[u][v]) {
                        queue.offer(top);
                    }
                }

                if(v > 0) {
                    Point left = new Point(u, v - 1);
                    if(!visited.contains(left) && matrix[left.i][left.j] <= matrix[u][v]) {
                        queue.offer(left);
                    }
                }

                if(u < m - 1) {
                    Point bottom = new Point(u + 1, v);
                    if(!visited.contains(bottom) && matrix[bottom.i][bottom.j] <= matrix[u][v]) {
                        queue.offer(bottom);
                    }
                }


                if(v < n - 1) {
                    Point right = new Point(u, v + 1);
                    if(!visited.contains(right) && matrix[right.i][right.j] <= matrix[u][v]) {
                        queue.offer(right);
                    }
                }
            }

            return false;
        }

        class Point {
            int i;
            int j;

            public Point(int i, int j) {
                this.i = i;
                this.j = j;
            }

            @Override
            public boolean equals(Object o) {
                if(o instanceof Point) {
                    Point other = (Point) o;
                    return this.i == other.i && this.j == other.j;
                }

                return false;
            }

            @Override
            public int hashCode() {
                int hash = 7;

                hash *= 31;
                hash += this.i;

                hash *= 31;
                hash += this.j;

                return hash;
            }
        }
    }

// [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
// [[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37],[75,74,73,72,71,70,69,68,67,66,65,64,63,62,61,60,59,58,57,56,55,54,53,52,51,50,49,48,47,46,45,44,43,42,41,40,39,38],[76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113],[151,150,149,148,147,146,145,144,143,142,141,140,139,138,137,136,135,134,133,132,131,130,129,128,127,126,125,124,123,122,121,120,119,118,117,116,115,114],[152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189],[227,226,225,224,223,222,221,220,219,218,217,216,215,214,213,212,211,210,209,208,207,206,205,204,203,202,201,200,199,198,197,196,195,194,193,192,191,190],[228,229,230,231,232,233,234,235,236,237,238,239,240,241,242,243,244,245,246,247,248,249,250,251,252,253,254,255,256,257,258,259,260,261,262,263,264,265],[303,302,301,300,299,298,297,296,295,294,293,292,291,290,289,288,287,286,285,284,283,282,281,280,279,278,277,276,275,274,273,272,271,270,269,268,267,266],[304,305,306,307,308,309,310,311,312,313,314,315,316,317,318,319,320,321,322,323,324,325,326,327,328,329,330,331,332,333,334,335,336,337,338,339,340,341],[379,378,377,376,375,374,373,372,371,370,369,368,367,366,365,364,363,362,361,360,359,358,357,356,355,354,353,352,351,350,349,348,347,346,345,344,343,342],[380,381,382,383,384,385,386,387,388,389,390,391,392,393,394,395,396,397,398,399,400,401,402,403,404,405,406,407,408,409,410,411,412,413,414,415,416,417],[455,454,453,452,451,450,449,448,447,446,445,444,443,442,441,440,439,438,437,436,435,434,433,432,431,430,429,428,427,426,425,424,423,422,421,420,419,418],[456,457,458,459,460,461,462,463,464,465,466,467,468,469,470,471,472,473,474,475,476,477,478,479,480,481,482,483,484,485,486,487,488,489,490,491,492,493],[531,530,529,528,527,526,525,524,523,522,521,520,519,518,517,516,515,514,513,512,511,510,509,508,507,506,505,504,503,502,501,500,499,498,497,496,495,494],[532,533,534,535,536,537,538,539,540,541,542,543,544,545,546,547,548,549,550,551,552,553,554,555,556,557,558,559,560,561,562,563,564,565,566,567,568,569],[607,606,605,604,603,602,601,600,599,598,597,596,595,594,593,592,591,590,589,588,587,586,585,584,583,582,581,580,579,578,577,576,575,574,573,572,571,570],[608,609,610,611,612,613,614,615,616,617,618,619,620,621,622,623,624,625,626,627,628,629,630,631,632,633,634,635,636,637,638,639,640,641,642,643,644,645],[683,682,681,680,679,678,677,676,675,674,673,672,671,670,669,668,667,666,665,664,663,662,661,660,659,658,657,656,655,654,653,652,651,650,649,648,647,646],[684,685,686,687,688,689,690,691,692,693,694,695,696,697,698,699,700,701,702,703,704,705,706,707,708,709,710,711,712,713,714,715,716,717,718,719,720,721],[759,758,757,756,755,754,753,752,751,750,749,748,747,746,745,744,743,742,741,740,739,738,737,736,735,734,733,732,731,730,729,728,727,726,725,724,723,722],[760,761,762,763,764,765,766,767,768,769,770,771,772,773,774,775,776,777,778,779,780,781,782,783,784,785,786,787,788,789,790,791,792,793,794,795,796,797],[835,834,833,832,831,830,829,828,827,826,825,824,823,822,821,820,819,818,817,816,815,814,813,812,811,810,809,808,807,806,805,804,803,802,801,800,799,798],[836,837,838,839,840,841,842,843,844,845,846,847,848,849,850,851,852,853,854,855,856,857,858,859,860,861,862,863,864,865,866,867,868,869,870,871,872,873],[911,910,909,908,907,906,905,904,903,902,901,900,899,898,897,896,895,894,893,892,891,890,889,888,887,886,885,884,883,882,881,880,879,878,877,876,875,874],[912,913,914,915,916,917,918,919,920,921,922,923,924,925,926,927,928,929,930,931,932,933,934,935,936,937,938,939,940,941,942,943,944,945,946,947,948,949],[987,986,985,984,983,982,981,980,979,978,977,976,975,974,973,972,971,970,969,968,967,966,965,964,963,962,961,960,959,958,957,956,955,954,953,952,951,950],[988,989,990,991,992,993,994,995,996,997,998,999,1000,1001,1002,1003,1004,1005,1006,1007,1008,1009,1010,1011,1012,1013,1014,1015,1016,1017,1018,1019,1020,1021,1022,1023,1024,1025],[1063,1062,1061,1060,1059,1058,1057,1056,1055,1054,1053,1052,1051,1050,1049,1048,1047,1046,1045,1044,1043,1042,1041,1040,1039,1038,1037,1036,1035,1034,1033,1032,1031,1030,1029,1028,1027,1026]]
}

//    48. Rotate Image
//    Medium
//    You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
//
//    You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
//
//
//
//    Example 1:
//
//
//    Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
//    Output: [[7,4,1],[8,5,2],[9,6,3]]
//    Example 2:
//
//
//    Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
//    Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
//    Example 3:
//
//    Input: matrix = [[1]]
//    Output: [[1]]
//    Example 4:
//
//    Input: matrix = [[1,2],[3,4]]
//    Output: [[3,1],[4,2]]
//
//
//    Constraints:
//
//    matrix.length == n
//    matrix[i].length == n
//    1 <= n <= 20
//    -1000 <= matrix[i][j] <= 1000
