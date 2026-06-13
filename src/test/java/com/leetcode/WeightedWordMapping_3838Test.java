package com.leetcode;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class WeightedWordMapping_3838Test {

    @Test
    void testBasicMapping() {
        WeightedWordMapping_3838 solver = new WeightedWordMapping_3838();

        // Let's build weights where every char maps to its alphabetical 0-based index
        // weights[0] = 0 (a), weights[1] = 1 (b), ...
        int[] weights = new int[26];
        for (int i = 0; i < 26; i++) {
            weights[i] = i;
        }

        // Test with "a" -> weight 0 -> 0 % 26 = 0 -> maps to 'z'
        assertThat(solver.mapWordWeights(new String[]{"a"}, weights)).isEqualTo("z");

        // Test with "b" -> weight 1 -> 1 % 26 = 1 -> maps to 'y'
        assertThat(solver.mapWordWeights(new String[]{"b"}, weights)).isEqualTo("y");

        // Test with "z" -> weight 25 -> 25 % 26 = 25 -> maps to 'a'
        assertThat(solver.mapWordWeights(new String[]{"z"}, weights)).isEqualTo("a");

        // Test with "ab" -> weight 0 + 1 = 1 -> 1 % 26 = 1 -> maps to 'y'
        assertThat(solver.mapWordWeights(new String[]{"ab"}, weights)).isEqualTo("y");

        // Test with multiple words: ["a", "b", "z", "ab"] -> "zyay"
        assertThat(solver.mapWordWeights(new String[]{"a", "b", "z", "ab"}, weights)).isEqualTo("zyay");
    }

    @Test
    void testEmptyAndNull() {
        WeightedWordMapping_3838 solver = new WeightedWordMapping_3838();
        int[] weights = new int[26];

        assertThat(solver.mapWordWeights(null, weights)).isEmpty();
        assertThat(solver.mapWordWeights(new String[]{}, null)).isEmpty();
        assertThat(solver.mapWordWeights(new String[]{"abc"}, new int[10])).isEmpty();
    }

    @Test
    void testNegativeAndEdgeCases() {
        WeightedWordMapping_3838 solver = new WeightedWordMapping_3838();

        // 1. Negative weights
        int[] weights = new int[26];
        weights[0] = -1;  // 'a'
        weights[25] = -25; // 'z'
        // Test "a" -> weight -1 -> -1 % 26 = -1 -> -1 + 26 = 25 -> 'z' - 25 = 'a'
        assertThat(solver.mapWordWeights(new String[]{"a"}, weights)).isEqualTo("a");
        // Test "z" -> weight -25 -> -25 % 26 = -25 -> -25 + 26 = 1 -> 'z' - 1 = 'y'
        assertThat(solver.mapWordWeights(new String[]{"z"}, weights)).isEqualTo("y");

        // Reset weights for other tests
        for (int i = 0; i < 26; i++) {
            weights[i] = i;
        }

        // 2. Non-lowercase and mixed characters
        // "a1B!" -> only counts 'a' (0) -> maps to 'z'
        // " a " -> only counts 'a' (0) -> maps to 'z'
        // "B" -> counts nothing -> sum = 0 -> maps to 'z'
        assertThat(solver.mapWordWeights(new String[]{"a1B!", " a ", "B"}, weights)).isEqualTo("zzz");

        // 3. Empty strings inside words array
        assertThat(solver.mapWordWeights(new String[]{""}, weights)).isEqualTo("z");
        assertThat(solver.mapWordWeights(new String[]{}, weights)).isEqualTo("");

        // 4. Larger weights array (should not throw IndexOutOfBounds)
        int[] largeWeights = new int[30];
        for (int i = 0; i < 30; i++) {
            largeWeights[i] = i;
        }
        assertThat(solver.mapWordWeights(new String[]{"a", "z"}, largeWeights)).isEqualTo("za");
    }
}

