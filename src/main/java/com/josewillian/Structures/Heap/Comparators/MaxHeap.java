package com.josewillian.Structures.Heap.Comparators;

import java.util.Comparator;

public class MaxHeap<E extends Comparable<E>> implements Comparator<E> {

    @Override
    public int compare(E arg0, E arg1) {
        return arg0.compareTo(arg1);
    }
    
}
