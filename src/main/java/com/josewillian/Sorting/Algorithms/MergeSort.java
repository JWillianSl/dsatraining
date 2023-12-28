package com.josewillian.Sorting.Algorithms;

import java.util.Arrays;

import com.josewillian.Sorting.Sort;

/**
 * This algorithm implements Sort Interface using the idea of divide and conquer.
 * Also called virtual divisions, this idea works by index control.
 */
public class MergeSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] arr) {
        sort(arr, 0, arr.length-1);
    }

    /**
     * Helper method that uses a recursive implementation and index control to make 
     * virtual divisions in the array.
     * 
     * @param arr - given array of generic type to sort
     * @param ini - index representing the start of the virtual partition
     * @param end - index representing the end of the virtual partition
     */
    private void sort(T[] arr, int ini, int end){

        if(ini < end){
            int middle = (ini+end) / 2;

            sort(arr, ini, middle);
            sort(arr, middle + 1, end);

            merge(arr, ini, middle, end);
        }

    }

    /**
     * Using a copy of original array and the indices representing the virtual divisions,
     * merges the first half and the second half of the original array. 
     * 
     * @param arr - given array of generic type to sort
     * @param ini - index representing the start of the virtual partition
     * @param middle - index representing the middle of the virtual partition
     * @param end - index representing the end of the virtual partition
     */
    private void merge(T[] arr, int ini, int middle, int end){
        T[] aux = Arrays.copyOf(arr, arr.length);

        int i = ini;
        int j = middle + 1;
        int k = ini;

        while(i <= middle && j <= end){

            if(aux[i].compareTo(aux[j]) < 0){
                arr[k++] = aux[i++];
            }
            else{
                arr[k++] = aux[j++];
            }

        }

        while(i <= middle){
            arr[k++] = aux[i++];
        }

        while(j <= end){
            arr[k++] = aux[j++];
        }

    }
    
}
