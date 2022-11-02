package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinimumGeneticMutation {
    class Solution {
        public int minMutation(String start, String end, String[] bank) {
            if(start == null || start.length() != 8 || end == null || end.length() != 8) {
                return -1;
            }

            if(start.equals(end)) {
                return 0;
            }

            if(bank == null || bank.length == 0) {
                return -1;
            }

            Set<String> bankSet = new HashSet<>();
            for(String gene : bank) {
                bankSet.add(gene);
            }

            Queue<String> queue = new LinkedList<>();
            queue.offer(start);

            Set<String> seen = new HashSet<>();
            seen.add(start);

            char[] bases = new char[] {'A', 'C', 'G', 'T'};

            int steps = 0;
            while(!queue.isEmpty()) {
                int size = queue.size();

                for(int j = 0; j < size; j++) {
                    String gene = queue.poll();
                    if(gene.equals(end)) {
                        return steps;
                    }

                    StringBuilder builder = new StringBuilder(gene);

                    for(int i = 0; i < 8; i++) {
                        char c = gene.charAt(i);

                        for(char nc : bases) {
                            if(c != nc) {
                                builder.setCharAt(i, nc);
                                String newGene = builder.toString();
                                if(bankSet.contains(newGene)
                                        && !seen.contains(newGene)) {
                                    seen.add(newGene);
                                    queue.offer(newGene);
                                }

                                builder.setCharAt(i, c);
                            }
                        }
                    }
                }

                steps++;
            }

            return -1;
        }
    }

    class Solution_Correct_1 {
        public int minMutation(String start, String end, String[] bank) {
            if(start == null || start.length() != 8 || end == null || end.length() != 8) {
                return -1;
            }

            if(start.equals(end)) {
                return 0;
            }

            if(bank == null || bank.length == 0) {
                return -1;
            }

            Set<String> geneBank = new HashSet<>();
            for(String bankGene : bank) {
                geneBank.add(bankGene);
            }

            Queue<String> queue = new LinkedList<>();
            queue.offer(start);

            Set<String> visited = new HashSet<>();
            visited.add(start);

            int iterations = 0;
            while(!queue.isEmpty()) {
                int size = queue.size();
                for(int i = 0; i < size; i++) {
                    String gene = queue.poll();
                    if(gene.equals(end)) {
                        return iterations;
                    }

                    StringBuilder geneBuilder = new StringBuilder(gene);
                    for(int j = 0; j < 8; j++) {
                        mutateAndProcessGene(geneBuilder, j, 'A', queue, visited, geneBank);
                        mutateAndProcessGene(geneBuilder, j, 'C', queue, visited, geneBank);
                        mutateAndProcessGene(geneBuilder, j, 'G', queue, visited, geneBank);
                        mutateAndProcessGene(geneBuilder, j, 'T', queue, visited, geneBank);
                    }
                }

                iterations++;
            }

            return -1;
        }

        private void mutateAndProcessGene(StringBuilder geneBuilder, int i, char c, Queue<String> geneQueue, Set<String> visitedGenes, Set<String> geneBank) {
            char oldC = geneBuilder.charAt(i);
            geneBuilder.setCharAt(i, c);
            String gene = geneBuilder.toString();
            if(!visitedGenes.contains(gene)
                    && geneBank.contains(gene)) {
                geneQueue.offer(gene);
                visitedGenes.add(gene);
            }

            geneBuilder.setCharAt(i, oldC);
        }
    }
}

//    433. Minimum Genetic Mutation
//    Medium
//    A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
//
//    Suppose we need to investigate a mutation from a gene string start to a gene string end where one mutation is defined as one single character changed in the gene string.
//
//    For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
//    There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
//
//    Given the two gene strings start and end and the gene bank bank, return the minimum number of mutations needed to mutate from start to end. If there is no such a mutation, return -1.
//
//    Note that the starting point is assumed to be valid, so it might not be included in the bank.
//
//
//
//    Example 1:
//
//    Input: start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
//    Output: 1
//    Example 2:
//
//    Input: start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
//    Output: 2
//    Example 3:
//
//    Input: start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
//    Output: 3
//
//
//    Constraints:
//
//    start.length == 8
//    end.length == 8
//    0 <= bank.length <= 10
//    bank[i].length == 8
//    start, end, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].