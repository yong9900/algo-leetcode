package com.leetcode;

public class WeightedWordMapping_3838 {
    public String mapWordWeights(String[] words, int[] weights) {
        if (words == null || weights == null || weights.length < 26) {
            return "";
        }

        StringBuilder result = new StringBuilder(words.length);
        for (String word : words) {
            int sum = 0;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    sum += weights[c - 'a'];
                }
            }
            // Map 0 to 'z', 1 to 'y', ..., 25 to 'a'
            int rem = sum % 26;
            if (rem < 0) {
                rem += 26;
            }
            char mappedChar = (char) ('z' - rem);
            result.append(mappedChar);
        }

        return result.toString();
    }
}
