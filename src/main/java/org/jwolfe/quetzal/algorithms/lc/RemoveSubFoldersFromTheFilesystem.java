package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class RemoveSubFoldersFromTheFilesystem {
    class Solution {
        public List<String> removeSubfolders(String[] folder) {
            List<String> filteredFolders = new ArrayList<>();
            if (folder == null || folder.length == 0) {
                return filteredFolders;
            }

            TrieNode root = new TrieNode();

            for (String path : folder) {
                TrieNode current = root;
                String[] components = path.split("/");

                for (String component : components) {
                    if (component.equals("")) {
                        continue;
                    }

                    if (!current.children.containsKey(component)) {
                        current.children.put(component, new TrieNode());
                    }

                    current = current.children.get(component);
                }

                current.isEndOfFolder = true;
            }

            for (String path : folder) {
                TrieNode current = root;
                String[] components = path.split("/");
                int length = components.length;

                boolean isSubFolder = false;
                for (int i = 0; i < length; i++) {
                    String component = components[i];
                    if (component.equals("")) {
                        continue;
                    }

                    current = current.children.get(component);
                    if (current.isEndOfFolder && i != length - 1) {
                        isSubFolder = true;
                        break;
                    }
                }

                if (!isSubFolder) {
                    filteredFolders.add(path);
                }
            }

            return filteredFolders;
        }

        class TrieNode {
            Map<String, TrieNode> children;
            boolean isEndOfFolder;

            public TrieNode() {
                children = new HashMap<>();
                isEndOfFolder = false;
            }
        }
    }

    class Solution_Correct_1 {
        public List<String> removeSubfolders(String[] folder) {
            List<String> filteredFolders = new ArrayList<>();
            if (folder == null || folder.length == 0) {
                return filteredFolders;
            }

            Arrays.sort(folder);
            Set<String> foldersToIgnore = new HashSet<>();
            for (int i = 0; i < folder.length; i++) {
                String f1 = folder[i];
                if (foldersToIgnore.contains(f1)) {
                    continue;
                }

                f1 = f1 + "/";
                for (int j = 1 + i; j < folder.length; j++) {
                    String f2 = folder[j];
                    if (f2.startsWith(f1)) {
                        foldersToIgnore.add(f2);
                    }
                }
            }

            for (int i = 0; i < folder.length; i++) {
                String f = folder[i];
                if (!foldersToIgnore.contains(f)) {
                    filteredFolders.add(f);
                }
            }

            return filteredFolders;
        }
    }

// ["/a","/a/b","/c/d","/c/d/e","/c/f"]

}

//    1233. Remove Sub-Folders from the Filesystem
//    Medium
//    Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.
//
//    If a folder[i] is located within another folder[j], it is called a sub-folder of it. A sub-folder of folder[j] must start with folder[j], followed by a "/". For example, "/a/b" is a sub-folder of "/a", but "/b" is not a sub-folder of "/a/b/c".
//
//    The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.
//
//    For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.
//
//
//    Example 1:
//
//    Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
//    Output: ["/a","/c/d","/c/f"]
//    Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
//    Example 2:
//
//    Input: folder = ["/a","/a/b/c","/a/b/d"]
//    Output: ["/a"]
//    Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".
//    Example 3:
//
//    Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
//    Output: ["/a/b/c","/a/b/ca","/a/b/d"]
//
//
//    Constraints:
//
//    1 <= folder.length <= 4 * 104
//    2 <= folder[i].length <= 100
//    folder[i] contains only lowercase letters and '/'.
//    folder[i] always starts with the character '/'.
//    Each folder name is unique.