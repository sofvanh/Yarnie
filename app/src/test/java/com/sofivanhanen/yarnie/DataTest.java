package com.sofivanhanen.yarnie;

import com.sofivanhanen.yarnie.data.Pattern;
import com.sofivanhanen.yarnie.data.PatternList;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by sofvanh on 21/02/18.
 */

public class DataTest {

    // Contains tests for classes in the Data package

    @Test
    public void canCreateAndAddToPatternList() {
        PatternList pl = new PatternList();
        for (int i = 0; i < 25; i++) {
            pl.add(new Pattern());
        }
        assertEquals(25, pl.size());
    }

    @Test
    public void canComparePatternLists1() {
        PatternList pl1 = new PatternList();
        PatternList pl2 = new PatternList();
        Pattern p1 = new Pattern();
        p1.setYardage(100);
        Pattern p2 = new Pattern();
        p2.setYardage(200);
        pl1.add(p1);
        pl2.add(p2);
        assertNotEquals(pl1, pl2);
    }

    @Test
    public void canComparePatternLists2() {
        PatternList pl1 = new PatternList();
        PatternList pl2 = new PatternList();
        Pattern p1 = new Pattern();
        p1.setYardage(100);
        pl1.add(p1);
        pl2.add(p1);
        assertEquals(pl1, pl2);
    }

    @Test
    public void patternListCompareAllWorks1() {
        Pattern p1 = new Pattern();
        p1.setYardage(300);
        Pattern p2 = new Pattern();
        p2.setYardage(500);

        PatternList pl = new PatternList();
        pl.add(p1);
        pl.add(p2);

        ArrayList<Pattern> arrayList = new ArrayList<>();
        arrayList.add(p2);
        arrayList.add(p1);

        assertTrue(pl.containsAll(arrayList));
    }

    @Test
    public void patternListCompareAllWorks2() {
        Pattern p1 = new Pattern();
        p1.setYardage(300);
        Pattern p2 = new Pattern();
        p2.setYardage(500);

        PatternList pl = new PatternList();
        pl.add(p1);

        ArrayList<Pattern> arrayList = new ArrayList<>();
        arrayList.add(p2);

        assertFalse(pl.containsAll(arrayList));
    }

    @Test
    public void patternListIteratorWorks() {
        PatternList pl = new PatternList();
        pl.add(new Pattern());
        pl.add(new Pattern());
        for (Pattern pattern : pl) {
            pattern.setYardage(10);
        }
    }

    @Test
    public void patternListGetWorks() {
        PatternList pl = new PatternList();
        Pattern p = new Pattern();
        p.setYardage(800);
        pl.add(p);
        assertEquals(p, pl.get(0));
    }

    @Test
    public void patternListIsEmptyWorks() {
        PatternList pl = new PatternList();
        assertTrue(pl.isEmpty());
        pl.add(new Pattern());
        pl.add(new Pattern());
        assertFalse(pl.isEmpty());
    }

    @Test
    public void arrayToList_test() {
        Pattern p1 = new Pattern();
        p1.setYardage(18);
        Pattern p2 = new Pattern();
        p2.setYardage(19);

        Pattern[] patterns = new Pattern[2];
        patterns[0] = p1;
        patterns[1] = p2;

        PatternList correctResult = new PatternList();
        correctResult.add(p1);
        correctResult.add(p2);

        assertEquals(correctResult, PatternList.PatternListFromArray(patterns));
    }

    @Test
    public void arrayToList_notSame() {
        Pattern p1 = new Pattern();
        p1.setYardage(18);
        Pattern p2 = new Pattern();
        p2.setYardage(19);
        Pattern p3 = new Pattern();
        p3.setYardage(20);

        Pattern[] patterns = new Pattern[2];
        patterns[0] = p1;
        patterns[1] = p2;

        PatternList incorrectResult = new PatternList();
        incorrectResult.add(p1);
        incorrectResult.add(p3);

        assertNotEquals(incorrectResult, PatternList.PatternListFromArray(patterns));
    }

    @Test
    public void listToArray_test() {
        Pattern p1 = new Pattern();
        p1.setYardage(18);
        Pattern p2 = new Pattern();
        p2.setYardage(19);

        PatternList patterns = new PatternList();
        patterns.add(p1);
        patterns.add(p2);

        Pattern[] correctResult = new Pattern[2];
        correctResult[0] = p1;
        correctResult[1] = p2;

        assertEquals(correctResult, patterns.returnAsArray());
    }

    @Test
    public void listToArray_notSame() {
        Pattern p1 = new Pattern();
        p1.setYardage(18);
        Pattern p2 = new Pattern();
        p2.setYardage(19);
        Pattern p3 = new Pattern();
        p3.setYardage(20);

        PatternList patterns = new PatternList();
        patterns.add(p1);
        patterns.add(p2);

        Pattern[] incorrectResult = new Pattern[2];
        incorrectResult[0] = p1;
        incorrectResult[1] = p3;

        assertNotEquals(incorrectResult, patterns.returnAsArray());
    }

    @Test
    public void patternTest() {
        Pattern pattern = new Pattern();
        pattern.setId(200);
        pattern.setYardage(400);
        pattern.setName("hat");
        pattern.setFree(true);
        String expectedValuesAsString = "200400hattrue";
        String realValuesAsString = pattern.getId() + "" + pattern.getYardage()
                + pattern.getName() + pattern.getFree();
        assertEquals(expectedValuesAsString, realValuesAsString);
    }

}
