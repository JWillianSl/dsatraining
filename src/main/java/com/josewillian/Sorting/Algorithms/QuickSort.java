package com.josewillian.Sorting.Algorithms;

import com.josewillian.Sorting.Sort;
import com.josewillian.Util.Util;

/**
 * An algorithm that uses the index control strategy to sort a given array
 * of generic type. 
 */
public class QuickSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] arr) {
        sort(arr, 0, arr.length-1);
    }

    /**
     * Uses a recursive strategy to make virtual divisions and call the partition method.
     * 
     * @param arr - a given array to sort
     * @param ini - index representing the start of partion
     * @param end - index representing the end of partition
     */
    private void sort(T[] arr, int ini, int end){

        if(ini < end){
            int pivot = partition(arr, ini, end);

            sort(arr, ini, pivot - 1);
            sort(arr, pivot + 1, end);
        }

    }

    /**
     * Choose a random pivot and move elements smaller than the pivot to the left.
     * 
     * @param arr - a given array of generic type 
     * @param ini - index representing the start of partition
     * @param end - index representing the end of partition
     * @return the final position of random pivot
     */
    private int partition(T[] arr, int ini, int end) {
        int range = (end - ini) + 1;
        int randomPos = (int)(Math.random() * range) + ini;

        Util.swap(arr, ini, randomPos);
        
        T pivot = arr[ini];
        int swapItem = ini;

        for(int i = ini + 1; i <= end; i++){
            if(arr[i].compareTo(pivot) <= 0){
                swapItem++;
                Util.swap(arr, swapItem, i);
            }
        }

        Util.swap(arr, ini, swapItem);

        return swapItem;
    }
    
}
