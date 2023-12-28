package com.josewillian.Sorting.Algorithms;

import com.josewillian.Sorting.Sort;
import com.josewillian.Util.Util;

/**
 * This algorithm implements Sort interface and uses a simple selection strategy. 
 * Places the first smallest element in the first position and selects the next smallest element for the second position, and so on.
 */
public class SelectionSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] arr) {
        
        for(int i = 0; i < arr.length; i++){

            int smallest = selection(arr, i);
            Util.swap(arr, i, smallest);

        }

    }

    /**
     * Finds the smallest element from a given position to the end of the array.
     * Uses a recursive strategy to do this operation.
     * 
     * @param arr - given generic array to find the smallest elements
     * @param pivot - reference position to find the smallest element for this position
     * @return a number that represents the index of smallest element
     */
    private int selection(T[] arr, int pivot) {
        int result = pivot;

        if (pivot < arr.length - 1) {
            result = selection(arr, pivot + 1);
        }

        if(arr[pivot].compareTo(arr[result]) <= 0) {
            result = pivot;
        }

        return result;
    }

}
