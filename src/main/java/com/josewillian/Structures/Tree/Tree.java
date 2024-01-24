package com.josewillian.Structures.Tree;

/**
 * Defines an interface that represents a Tree.
 */
public interface Tree<E extends Comparable<E>> {
    
    /**
     * Adds a new element in this Tree.
     * 
     * @param e - new element to add in this tree
     * @return true if the addition is successful
     */
    public boolean add(E e);

    /**
     * Removes a given element from this Tree.
     * 
     * @param e - given element to be removed
     * @return true if the element was removed
     */
    public boolean remove(E e);

    /**
     * Checks if a given element is stored in this Tree.
     * 
     * @param e - element to be checked
     * @return true if this Tree contains the element
     */
    public boolean contains(E e);
    
    /**
     * Returns the total height of this Tree.
     * 
     * @return the height of this Tree
     */
    public int height();

    /**
     * Returns an element representing the predecessor of a given element.
     * 
     * @param e - element to return the predecessor
     * @return the predecessor of a given element
     */
    public E predecessor(E e);

    /**
     * Returns an element representing the successor of a given element.
     * 
     * @param e - element to return the successor
     * @return the successor of a given element
     */
    public E successor(E e);

    /**
     * Returns the root element, or null if this Tree is empty.
     * 
     * @return the element stored in the root
     */
    public E getRoot();

    /**
     * Returns the maximum element of this Tree.
     * 
     * @return the maximum element
     */
    public E max();

    /**
     * Returns the minimum element of this Tree.
     * 
     * @return the minimum element
     */
    public E min();

    /**
     * Returns an array of Tree elements.
     * 
     * @return an array of elements stored in this Tree
     */
    public E[] toArray();

}
