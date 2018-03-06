package com.sofivanhanen.yarnie;

import com.sofivanhanen.yarnie.data.Pattern;
import com.sofivanhanen.yarnie.data.PatternList;
import com.sofivanhanen.yarnie.utils.AlgoUtils;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by sofvanh on 02/02/18.
 */

public class AlgoUtilsTest {

    @Test
    public void patternValue_correct() throws Exception {
        Pattern pattern = new Pattern();
        pattern.setFree(true);
        pattern.setYardage(278);
        pattern.setPublished(new Date(System.currentTimeMillis()-32556952000l)); // A bit over a year ago
        pattern.setProjects_count(4000);

        int correctValue = 237;

        assertEquals(correctValue, AlgoUtils.calculatePatternValue(pattern));
    }

    @Test
    public void patternValue_incorrect() throws Exception {
        Pattern pattern = new Pattern();
        int correctValue = -1;
        assertEquals(correctValue, AlgoUtils.calculatePatternValue(pattern));
    }

    // Testing the simplest algorithm - n = 3, x = 10
    @Test
    public void patternKnapsackWeightOnly_easy() throws Exception {
        int maxYards = 10;

        Pattern one = new Pattern();
        one.setYardage(4);
        Pattern two = new Pattern();
        two.setYardage(7);
        Pattern three = new Pattern();
        three.setYardage(5);

        Pattern[] array = new Pattern[3];
        array[0] = one;
        array[1] = two;
        array[2] = three;

        PatternList correctResult = new PatternList();
        correctResult.add(one);
        correctResult.add(three);

        PatternList result = AlgoUtils.patternKnapsackWeightOnly(array, maxYards);
        assertEquals(correctResult, result);
    }

    // Testing the simplest algorithm - n = 10, x = 100
    @Test
    public void patternKnapsackWeightOnly_medium() throws Exception {
        int maxYards = 100;
        PatternList correctResult = new PatternList();
        Pattern[] array = new Pattern[10];
        int[] yardage = {
                40, // index 0 - part of result
                39,
                15, // index 2 - part of result
                15, // index 3 - part of result
                14,
                20,
                0,
                30, // index 7 - part of result
                89,
                101
        };

        for (int i = 0; i < array.length; i++) {
            array[i] = new Pattern();
            array[i].setYardage(yardage[i]);
            if (i == 0||i == 2||i == 3||i == 7) {
                correctResult.add(array[i]);
            }
        }

        PatternList result = AlgoUtils.patternKnapsackWeightOnly(array, maxYards);
        assertEquals(correctResult, result);
    }

    @Test
    public void metersToYardsTest() throws Exception {
        int meters = 1234;
        int correctYards = 1349; // rounded down
        assertEquals(correctYards, AlgoUtils.metersToYards(meters));
    }

    // Testing regular knapsack algorithm - n = 3, x = 10
    @Test
    public void patternKnapsack_easy() throws Exception {
        int maxYards = 10;

        Pattern one = new Pattern();
        one.setYardage(10);
        one.setValue(10);
        Pattern two = new Pattern();
        two.setYardage(7);
        two.setValue(5);
        Pattern three = new Pattern();
        three.setYardage(2);
        three.setValue(18);

        Pattern[] array = new Pattern[3];
        array[0] = one;
        array[1] = two;
        array[2] = three;

        PatternList correctResult = new PatternList();
        correctResult.add(two);
        correctResult.add(three);

        PatternList result = AlgoUtils.patternKnapsack(array, maxYards);
        assertEquals(correctResult, result);
    }

}
