package com.josewillian.Structures.Lists;

/**
 * Defines an interface that has precise control over each inserted item.
 * Structures that implement this interface store all items in a sequence and can use valid indexes to control specific items.
 */
public interface List<E> {
    
    /**
     * Given an index, this method returns the element present at that position.
     * 
     * @param index - position of the element to be returned
     * @return element at index position
     */
    public E get(int index);

    /**
     * Given an element and an index, this method replaces the element present in the List.
     * 
     * @param e - element to be stored in the determined position
     * @param index - index of the element to be replaced
     * @return element previously stored in List
     */
    public E set(E e, int index);

    /**
     * Inserts a given element at the valid position. 
     * Shifts the current element in position and any subsequent element to the right.
     * 
     * @param e - element to be inserted
     * @param index - position to insert a new element
     */
    public void add(E e, int index);

    /**
     * Adds a given element to the end of this List.
     * 
     * @param e - element to be added
     * @return true if the addition is successful
     */
    public boolean add(E e);

    /**
     * Adds all elements from a given array to the end of this List.
     * 
     * @param a - given array of elements to add in this List 
     * @return true if the addition is successful
     */
    public boolean addAll(E[] a);

    /**
     * Removes an element of this List.
     * 
     * @param e - element to be removed
     * @return true if the element was stored in this List
     */
    public boolean remove(E e);

    /**
     * Removes an element that is stored at index position in this List.
     * 
     * @param index - position to remove an element
     * @return the element previously stored in that position
     */
    public E remove(int index);

    /**
     * Finds the first occurrence of this element in this List.
     * 
     * @param e - element to search for
     * @return valid index representing the first occurrence of this element in List
     */
    public int indexOf(E e);

    /**
     * Quantity of elements in this List.
     * 
     * @return the number of elements in this List
     */
    public int size();

    /**
     * Removes the all elements presents in List.
     */
    public void clear();

    /**
     * Checks the existence of an element. This method uses equals (an object method) to check if the element exist in this List. 
     * 
     * @param e - element to be checked
     * @return true if the element is in the List
     */
    public boolean contains(E e);

    /**
     * Returns if the List has no element.
     * 
     * @return true if this List contains no element
     */
    public boolean isEmpty();

    /**
     * Returns an array of List elements.
     * 
     * @return an array with all elements
     */
    public E[] toArray();

}
