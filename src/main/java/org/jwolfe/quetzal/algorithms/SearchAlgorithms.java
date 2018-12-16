package org.jwolfe.quetzal.algorithms;

public class SearchAlgorithms {
    public static int naiveSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        int result = -1;

        for(int i = 0; i <= n-m; i++) {
            int j;

            for(j=0; j < m; j++) {
                if(text.charAt(i+j) != pattern.charAt(j))
                    break;
            }

            if(j == m) {
                System.out.println("Pattern found at index " + i);
                result = i;
            }
        }

        return result;
    }

    public static int rabinCarpSearch(String text, String pattern) {
        // hash( txt[s+1 .. s+m] ) = ( d ( hash( txt[s .. s+m-1]) – txt[s]*h ) + txt[s + m] ) mod q
        // hash( txt[s .. s+m-1] ) : Hash value at shift s.
        // hash( txt[s+1 .. s+m] ) : Hash value at next shift (or shift s+1)
        // d: Number of characters in the alphabet
        // q: A prime number
        // h: d^(m-1)

        int n = text.length();
        int m = pattern.length();
        int result = -1;

        int p = 0; // pattern hash
        int t = 0; // text hash

        int d = 256;
        int q = 101; // a prime number

        // Calculate h
        int h = 1;
        for(int i = 0; i < m - 1; i++) {
            h = (h*d)%q;
        }

        // Hash of pattern & first text window
        for(int i = 0; i<m; i++) {
            p = (d*p + pattern.charAt(i)) % q;
            t = (d*t + text.charAt(i)) % q;
        }

        for(int i = 0; i<= n-m; i++) {

            if(p == t) {
                int j;
                for (j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j))
                        break;
                }

                if (j == m) {
                    System.out.println("Pattern found at index " + i);
                    result = i;
                }
            }

            if(i < n-m) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % q;

                if (t < 0)
                    t = t + q;
            }
        }

        return result;
    }

    public static int kmpSearch(String text, String pattern) {
        int result = -1;

        int n = text.length();
        int m = pattern.length();

        int[] lps = computeLPS(pattern);
        int i=0;
        int j=0;

        while(i < n) {
            if(text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if(j == m) {
                System.out.println("Pattern found at index " + (i-m));
                result = i-m;
                j = lps[j-1];
            }
            else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if(j != 0) {
                    j = lps[j - 1];
                }
                else {
                    i++;
                }
            }
        }

        return  result;
    }

    private static int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];

        int len = 0;

        lps[0] = 0;
        int i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}

