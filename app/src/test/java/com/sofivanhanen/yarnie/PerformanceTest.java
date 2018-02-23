package com.sofivanhanen.yarnie;

import com.sofivanhanen.yarnie.Data.Pattern;
import com.sofivanhanen.yarnie.Utils.AlgoUtils;

import org.junit.Test;

import java.util.Random;

/**
 * Created by sofvanh on 23/02/18.
 */

public class PerformanceTest {

    @Test
    public void patternKnapsackWeightOnly_performance_size20() {
        runPatternKnapsackWeightOnly(20);
    }

    @Test
    public void patternKnapsackWeightOnly_performance_size50() {
        runPatternKnapsackWeightOnly(50);
    }
/* // This test takes too long to run. It fries my computer
    @Test
    public void patternKnapsackWeightOnly_performance_size100() {
        runPatternKnapsackWeightOnly(100);
    }
*/
    private void runPatternKnapsackWeightOnly(int n) {
        int sizeOfArray = n;
        int maxYardage = n * 10;
        int maxYardageOfPattern = maxYardage / 2;
        AlgoUtils.patternKnapsackWeightOnly(createRandomArray(n, maxYardageOfPattern), maxYardage);
    }

    private Pattern[] createRandomArray(int size, int maxYardageOfPattern) {
        Pattern[] result = new Pattern[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            Pattern pattern = new Pattern();
            pattern.setYardage(random.nextInt(maxYardageOfPattern));
            result[i] = pattern;
        }
        return result;
    }

}
