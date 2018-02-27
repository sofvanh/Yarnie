package com.sofivanhanen.yarnie;

import com.sofivanhanen.yarnie.Data.Pattern;
import com.sofivanhanen.yarnie.Data.PatternList;
import com.sofivanhanen.yarnie.Utils.AlgoUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sofvanh on 02/02/18.
 */

public class AlgoUtilsTest {

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

}
