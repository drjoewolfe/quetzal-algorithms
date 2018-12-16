package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchAlgorithmsTest {
    @Test
    void naiveSearch() {
        assertEquals(15, SearchAlgorithms.naiveSearch( "THIS IS A TEST TEST TEXT", "TEST"));
        assertEquals(-1, SearchAlgorithms.naiveSearch( "AABCCAADDEE", "FAA"));
        assertEquals(13, SearchAlgorithms.naiveSearch( "AAAAAAAAAAAAAAAAAA", "AAAAA"));
        assertEquals(13, SearchAlgorithms.naiveSearch( "AAAAAAAAAAAAAAAAAB", "AAAAB"));
    }

    @Test
    void rabinCarpSearch() {
        assertEquals(15, SearchAlgorithms.rabinCarpSearch( "THIS IS A TEST TEST TEXT", "TEST"));
        assertEquals(-1, SearchAlgorithms.rabinCarpSearch( "AABCCAADDEE", "FAA"));
        assertEquals(13, SearchAlgorithms.rabinCarpSearch( "AAAAAAAAAAAAAAAAAA", "AAAAA"));
        assertEquals(13, SearchAlgorithms.rabinCarpSearch( "AAAAAAAAAAAAAAAAAB", "AAAAB"));
    }

    @Test
    void kmpSearch() {
        assertEquals(15, SearchAlgorithms.kmpSearch( "THIS IS A TEST TEST TEXT", "TEST"));
        assertEquals(-1, SearchAlgorithms.kmpSearch( "AABCCAADDEE", "FAA"));
        assertEquals(13, SearchAlgorithms.kmpSearch( "AAAAAAAAAAAAAAAAAA", "AAAAA"));
        assertEquals(13, SearchAlgorithms.kmpSearch( "AAAAAAAAAAAAAAAAAB", "AAAAB"));
    }
}