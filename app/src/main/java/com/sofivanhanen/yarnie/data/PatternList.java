package com.sofivanhanen.yarnie.data;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by sofvanh on 19/02/18.
 */

public class PatternList implements List<Pattern> {

    // Custom list that holds only patterns.
    // Deleting objects not supported.

    private Pattern[] patterns;
    private int firstFreeSpot;

    public PatternList() {
        patterns = new Pattern[20];
        firstFreeSpot = 0;
    }

    /**
     * Returns contained patterns as an array
     * @return The array of objects, null patterns removed
     */
    // Can't return the 'patterns' array as it contaisn empty spaces
    public Pattern[] returnAsArray() {
        Pattern[] result = new Pattern[firstFreeSpot];
        for (int i = 0; i < firstFreeSpot; i++) {
            result[i] = patterns[i];
        }
        return result;
    }

    /**
     * Creates a PatternList object out of an array
     * @param patterns Array of patterns
     * @return A PatternList object
     */
    public static PatternList PatternListFromArray(Pattern[] patterns) {
        PatternList list = new PatternList();
        for (Pattern pattern : patterns) {
            list.add(pattern);
        }
        return list;
    }

    // TODO: Cache these

    /**
     * Calculates total yards contained
     * @return Number of yards as int
     */
    public int getTotalYards() {
        int yards = 0;
        for (Pattern pattern : patterns) {
            if (pattern != null) {
                yards += pattern.getYardage();
            }
        }
        return yards;
    }

    /**
     * Calculates total value of contained patterns
     * @return Total value as int
     */
    public int getTotalValue() {
        int value = 0;
        for (Pattern pattern : patterns) {
            if (pattern != null) {
                value += pattern.getValue();
            }
        }
        return value;
    }

    /**
     * Get size of the list
     * @return Number of contained patterns as int
     */
    @Override
    public int size() {
        return firstFreeSpot;
    }

    /**
     * Get if empty
     * @return True if empty, false if at least one pattern contained
     */
    @Override
    public boolean isEmpty() {
        return firstFreeSpot == 0;
    }

    /**
     * Check if list contains a specific Pattern object
     * @param o Pattern we're looking for
     * @return true if contained, false otherwise
     */
    @Override
    public boolean contains(Object o) {
        if (!o.getClass().equals(Pattern.class)) {
            return false;
        } else {
            for (Pattern pattern : patterns) {
                if (pattern == null) return false;
                if (pattern.equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get an iterator of the contained Pattern objects
     * @return An iterator of patterns
     */
    @NonNull
    @Override
    public Iterator<Pattern> iterator() {
        return new Iterator<Pattern>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                if (patterns.length <= currentIndex) return false;
                else if (patterns[currentIndex] == null) return false;
                else return true;
            }

            @Override
            public Pattern next() {
                Pattern toReturn = patterns[currentIndex];
                currentIndex++;
                return toReturn;
            }
        };
    }

    /**
     * Add a pattern
     * @param pattern Pattern to add
     * @return Always true
     */
    @Override
    public boolean add(Pattern pattern) {
        if (firstFreeSpot == patterns.length) {
            expandList();
        }
        patterns[firstFreeSpot] = pattern;
        firstFreeSpot++;
        return true;
    }

    /**
     * For when array is full
     */
    private void expandList() {
        Pattern[] newArray = new Pattern[patterns.length * 2];
        for (int i = 0; i < firstFreeSpot; i++) {
            newArray[i] = patterns[i];
        }
        patterns = newArray;
    }

    /**
     * Check if this PatternList contains everything in the given collection
     * @param collection Items to check
     * @return True if all items are contained, false otherwise
     */
    @Override
    public boolean containsAll(@NonNull Collection<?> collection) {
        for (Object object : collection) {
            if (!contains(object)) return false;
        }
        return true;
    }

    /**
     * Checks if contains same objects as the defined PatternList
     * @param o Object we check
     * @return True if o is a PatternList containing all the same patterns, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o.getClass() != PatternList.class) return false;
        else {
            if (containsAll((PatternList) o)) {
                return ((PatternList)o).containsAll(PatternList.this);
            }
            return false;
        }
    }

    /**
     * Get Pattern at index
     * @param i Index to check
     * @return Pattern object specified, or possibly null if index not filled
     */
    @Override
    public Pattern get(int i) {
        return patterns[i];
    }

    @NonNull
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] ts) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends Pattern> collection) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean addAll(int i, @NonNull Collection<? extends Pattern> collection) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> collection) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> collection) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Pattern set(int i, Pattern pattern) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void add(int i, Pattern pattern) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Pattern remove(int i) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public ListIterator<Pattern> listIterator() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @NonNull
    @Override
    public ListIterator<Pattern> listIterator(int i) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @NonNull
    @Override
    public List<Pattern> subList(int i, int i1) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
