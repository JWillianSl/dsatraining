package com.josewillian.Sorting.Algorithms;

import com.josewillian.Sorting.Sort;
import com.josewillian.Util.Util;

/**
 * This algorithm implements the Sort Interface and uses the Insertion strategy 
 * to sort a given array.
 */
public class InsertionSort<T extends Comparable<T>> implements Sort<T>{

    @Override
    public void sort(T[] arr) {
        
        for(int i = 1; i < arr.length; i++){
            int pivot = i;

            while(pivot > 0){

                if(arr[pivot] != null && arr[pivot-1].compareTo(arr[pivot]) > 0){
                    Util.swap(arr, pivot-1, pivot);
                }

                pivot--;
            }

        }

    }
    
}
