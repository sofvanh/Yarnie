package com.sofivanhanen.yarnie.Data;

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

    @Override
    public int size() {
        return firstFreeSpot;
    }

    @Override
    public boolean isEmpty() {
        return firstFreeSpot == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (!o.getClass().equals(Pattern.class)) {
            return false;
        } else {
            for (Pattern pattern : patterns) {
                if (pattern.equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }

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
    public boolean add(Pattern pattern) {
        if (firstFreeSpot == patterns.length) {
            expandList();
        }
        patterns[firstFreeSpot] = pattern;
        firstFreeSpot++;
        return true;
    }

    private void expandList() {
        Pattern[] newArray = new Pattern[patterns.length * 2];
        for (int i = 0; i < firstFreeSpot; i++) {
            newArray[i] = patterns[i];
        }
        patterns = newArray;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> collection) {
        for (Object object : collection) {
            if (!contains(object)) return false;
        }
        return true;
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
    public boolean equals(Object o) {
        if (o.getClass() != PatternList.class) return false;
        else {
            return containsAll((PatternList) o);
        }
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Pattern get(int i) {
        return patterns[i];
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
