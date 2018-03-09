package com.sofivanhanen.yarnie;

import com.sofivanhanen.yarnie.data.Pattern;
import com.sofivanhanen.yarnie.utils.AlgoUtils;

import org.junit.Test;

import java.util.Random;

/**
 * Created by sofvanh on 23/02/18.
 */

public class PerformanceTest {

    @Test
    public void patternKnapsackWeightOnly_performance_size20_100times() {
        for (int i = 0; i < 100; i++) {
            patternKnapsackWeightOnly_performance_size20();
        }
    }

    @Test
    public void patternKnapsackWeightOnly_performance_size35_75times() {
        for (int i = 0; i < 75; i++) {
            patternKnapsackWeightOnly_performance_size35();
        }
    }

    @Test
    public void patternKnapsackWeightOnly_performance_size50_50times() {
        for (int i = 0; i < 50; i++) {
            patternKnapsackWeightOnly_performance_size50();
        }
    }

    @Test
    public void patternKnapsack_performance_size20_100times() {
        for (int i = 0; i < 100; i++) {
            patternKnapsack_performance_size20();
        }
    }

    @Test
    public void patternKnapsack_performance_size35_75times() {
        for (int i = 0; i < 75; i++) {
            patternKnapsack_performance_size35();
        }
    }

    @Test
    public void patternKnapsack_performance_size50_50times() {
        for (int i = 0; i < 50; i++) {
            patternKnapsack_performance_size50();
        }
    }

    @Test
    public void patternKnapsackWeightOnly_performance_size20() {
        runPatternKnapsackWeightOnly(20);
    }

    @Test
    public void patternKnapsackWeightOnly_performance_size35() { runPatternKnapsackWeightOnly(35); }

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

    @Test
    public void patternKnapsack_performance_size20() { runPatternKnapsack(20); }

    @Test
    public void patternKnapsack_performance_size35() { runPatternKnapsack(35); }

    @Test
    public void patternKnapsack_performance_size50() { runPatternKnapsack(50); }

    private void runPatternKnapsackWeightOnly(int n) {
        int sizeOfArray = n;
        int maxYardage = n * 10;
        int maxYardageOfPattern = maxYardage / 2;
        AlgoUtils.patternKnapsackWeightOnly(createRandomArray(n, maxYardageOfPattern), maxYardage);
    }

    private void runPatternKnapsack(int n) {
        int sizeOfArray = n;
        int maxYardage = n * 10;
        int maxYardageOfPattern = maxYardage / 2;
        AlgoUtils.patternKnapsack(createRandomArrayWithValues(n, maxYardageOfPattern), maxYardage);
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

    private Pattern[] createRandomArrayWithValues(int size, int maxYardageOfPattern) {
        Pattern[] result = new Pattern[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            Pattern pattern = new Pattern();
            pattern.setYardage(random.nextInt(maxYardageOfPattern));
            pattern.setFree(random.nextInt(2) == 1);
            result[i] = pattern;
        }
        return result;
    }

}
