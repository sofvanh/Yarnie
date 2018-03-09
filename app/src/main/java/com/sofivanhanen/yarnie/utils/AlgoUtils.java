package com.sofivanhanen.yarnie.utils;


import com.sofivanhanen.yarnie.data.Pattern;
import com.sofivanhanen.yarnie.data.PatternList;

/**
 * Created by sofvanh on 31/01/18.
 */

public class AlgoUtils {

    /**
     * Helper method for finding the set with biggest yardage
     * @param a First PatternList to compare
     * @param b Second PatternList to compare
     * @return Returns either a or b, depending which is holding more total yardage
     */
    private static PatternList maxYardage(PatternList a, PatternList b) {
        int aYards = a.getTotalYards();
        int bYards = b.getTotalYards();
        if (aYards >= bYards) return a;
        else return b;
    }

    /**
     *  Helper method for finding the set with biggest value
     * @param a First PatternList to compare
     * @param b Second PatternList to compare
     * @return Returns either a or b, depending which is holding bigger total value
     */
    private static PatternList maxValue(PatternList a, PatternList b) {
        int aValue = a.getTotalValue();
        int bValue = b.getTotalValue();
        if (aValue >= bValue) return a;
        else return b;
    }

    /**
     * Calculates the custom value of a Pattern object
     * @param pattern The pattern whose value we want to calculate
     * @return Newly calculated value
     */
    public static int calculatePatternValue(Pattern pattern) {

        // Final value calculation:
        // yardage * ((5 / 5+(years since published)) * (if free, 1; if not, 0.5) + (number of projects / 50 000))

        int yardage = pattern.getYardage();
        int yearsSincePublish;
        if (pattern.getPublished() != null) {
            long millisecondsInYear = 31556952000l;
            yearsSincePublish = (int) ((System.currentTimeMillis() - pattern.getPublished().getTime()) / millisecondsInYear);
        } else {
            yearsSincePublish = 3; // We don't know actual publishing year
        }
        double free;
        if (pattern.getFree()) free = 1;
            else free = 0.5;
        int numberOfProjects = pattern.getProjects_count();

        double value = ((double)yardage * ((5.0/(5+yearsSincePublish))*(free)+(numberOfProjects/50000.0)));

        return (int) value;

    }

    /**
     * Knapsack alorithm for Pattern objects where value is yardage. Uses the recursive knapsack algorithm in KKKK (Kisakoodarin käsikirja)
     * @param patterns Array of available patterns
     * @param maxYardage Max total yardage of result
     * @return The resulting collection of patterns as PatternList
     */
    public static PatternList patternKnapsackWeightOnly(Pattern[] patterns, int maxYardage) {
        if (patterns.length == 0 || maxYardage <= 0) return new PatternList();
        PatternList list = recursiveKnapsackWeightOnly(patterns, patterns.length-1, maxYardage);
        return list;
    }

    /**
     * Recursive method for patternKnapsackWeightOnly
     * @param patterns Collection of patterns
     * @param maxIndex Index we're at (last index at start)
     * @param maxYards Amount of yards we can still use (max yards - used yards)
     * @return Calculated PatternList result up to maxIndex
     */
    private static PatternList recursiveKnapsackWeightOnly(Pattern[] patterns, int maxIndex, int maxYards) {
        if (maxIndex == -1) {
            return new PatternList();
        }
        PatternList first = recursiveKnapsackWeightOnly(patterns, maxIndex - 1, maxYards);
        if (maxYards < patterns[maxIndex].getYardage()) return first;
        PatternList second = recursiveKnapsackWeightOnly(patterns, maxIndex - 1, maxYards - patterns[maxIndex].getYardage());
        second.add(patterns[maxIndex]);
        return maxYardage(first, second);
    }

    // For when user wants to use meters instead of yards. Rounds down.
    private static final double ONE_METER_IN_YARDS = 1.0936133;
    public static int metersToYards(int meters) {
        return (int)(meters * ONE_METER_IN_YARDS);
    }

    /**
     * Knapsack algorithm for Pattern objects using custom value
     * @param patterns Array of available patterns
     * @param maxYardage Max total yardage of result
     * @return The resulting collection of patterns as PatternList
     */
    public static PatternList patternKnapsack(Pattern[] patterns, int maxYardage) {
        if (patterns.length == 0 || maxYardage <= 0) return new PatternList();
        PatternList list = recursiveKnapsack(patterns, patterns.length-1, maxYardage);
        return list;
    }

    /**
     * Recursive method for patternKnapsack
     * @param patterns Collection of patterns
     * @param maxIndex Index we're at (last index at start)
     * @param maxYards Amount of yards we can still use (max yards - used yards)
     * @return Calculated PatternList result up to maxIndex
     */
    private static PatternList recursiveKnapsack(Pattern[] patterns, int maxIndex, int maxYards) {
        if (maxIndex == -1) {
            return new PatternList();
        }
        PatternList first = recursiveKnapsackWeightOnly(patterns, maxIndex - 1, maxYards);
        if (maxYards < patterns[maxIndex].getYardage()) return first;
        PatternList second = recursiveKnapsackWeightOnly(patterns, maxIndex - 1, maxYards - patterns[maxIndex].getYardage());
        second.add(patterns[maxIndex]);
        return maxValue(first, second);
    }

}
