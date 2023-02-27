package org.jwolfe.quetzal.algorithms.lc;

public class ConstructQuadTree {
    /*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
};
*/

    class Solution {
        public Node construct(int[][] grid) {
            if (grid == null || grid.length == 0 || grid.length != grid[0].length) {
                return null;
            }

            int n = grid.length;
            return constructQuadTree(grid, 0, 0, n - 1, n - 1);
        }

        private Node constructQuadTree(int[][] grid, int top, int left, int bottom, int right) {
            Node node = new Node();

            int element = grid[top][left];
            boolean allSame = true;
            for (int i = top; i <= bottom; i++) {
                for (int j = left; j <= right; j++) {
                    if (grid[i][j] != element) {
                        allSame = false;
                        break;
                    }
                }

                if (!allSame) {
                    break;
                }
            }

            if (allSame) {
                node.isLeaf = true;
                node.val = (element == 1);
            } else {
                int half = (bottom - top) / 2;

                node.isLeaf = false;
                node.val = true;

                node.topLeft = constructQuadTree(grid, top, left, top + half, left + half);
                node.topRight = constructQuadTree(grid, top, left + half + 1, top + half, right);
                node.bottomLeft = constructQuadTree(grid, top + half + 1, left, bottom, left + half);
                node.bottomRight = constructQuadTree(grid, top + half + 1, left + half + 1, bottom, right);
            }

            return node;
        }
    }

    class Solution_Correct_1 {
        public Node construct(int[][] grid) {
            if (grid == null || grid.length == 0 || grid.length != grid[0].length) {
                return null;
            }

            int n = grid.length;
            return construct(grid, 0, 0, n - 1, n - 1);
        }

        private Node construct(int[][] grid, int top, int left, int bottom, int right) {
            int element = grid[top][left];
            boolean allSame = true;
            for (int i = top; i <= bottom; i++) {
                for (int j = left; j <= right; j++) {
                    if (element != grid[i][j]) {
                        allSame = false;
                        break;
                    }
                }

                if (!allSame) {
                    break;
                }
            }

            if (allSame) {
                Node node = new Node((element == 1) ? true : false, true);
                return node;
            }

            int height = bottom - top + 1;
            int width = right - left + 1;

            Node topLeft = construct(grid, top, left, top + height / 2 - 1, left + width / 2 - 1);
            Node topRight = construct(grid, top, left + width / 2, top + height / 2 - 1, right);
            Node bottomLeft = construct(grid, top + height / 2, left, bottom, left + width / 2 - 1);
            Node bottomRight = construct(grid, top + height / 2, left + width / 2, bottom, right);

            return new Node((element == 1) ? true : false, false, topLeft, topRight, bottomLeft, bottomRight);
        }
    }

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}

//    427. Construct Quad Tree
//    Medium
//
//    970
//
//    1337
//
//    Add to List
//
//    Share
//    Given a n * n matrix grid of 0's and 1's only. We want to represent the grid with a Quad-Tree.
//
//    Return the root of the Quad-Tree representing the grid.
//
//    Notice that you can assign the value of a node to True or False when isLeaf is False, and both are accepted in the answer.
//
//    A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:
//
//    val: True if the node represents a grid of 1's or False if the node represents a grid of 0's.
//    isLeaf: True if the node is leaf node on the tree or False if the node has the four children.
//    class Node {
//    public boolean val;
//    public boolean isLeaf;
//    public Node topLeft;
//    public Node topRight;
//    public Node bottomLeft;
//    public Node bottomRight;
//    }
//    We can construct a Quad-Tree from a two-dimensional area using the following steps:
//
//    If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
//    If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
//    Recurse for each of the children with the proper sub-grid.
//
//    If you want to know more about the Quad-Tree, you can refer to the wiki.
//
//    Quad-Tree format:
//
//    The output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a path terminator where no node exists below.
//
//    It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].
//
//    If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[0,1],[1,0]]
//    Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
//    Explanation: The explanation of this example is shown below:
//    Notice that 0 represnts False and 1 represents True in the photo representing the Quad-Tree.
//
//    Example 2:
//
//
//
//    Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
//    Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
//    Explanation: All values in the grid are not the same. We divide the grid into four sub-grids.
//    The topLeft, bottomLeft and bottomRight each has the same value.
//    The topRight have different values so we divide it into 4 sub-grids where each has the same value.
//    Explanation is shown in the photo below:
//
//
//
//    Constraints:
//
//    n == grid.length == grid[i].length
//    n == 2x where 0 <= x <= 6