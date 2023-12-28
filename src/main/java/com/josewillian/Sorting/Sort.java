package com.josewillian.Sorting;

/**
 * Interface that defines the sort method for algorithms implementations.
 * Classes that implement this method have different strategies to sort the items of a given Array.
 */
public interface Sort<T> {

    /**
     * A method that sorts a given array an increase order.
     * 
     * @param arr - array of generic type elements
     */
    public void sort(T arr[]);
}
