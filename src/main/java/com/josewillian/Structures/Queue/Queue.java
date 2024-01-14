package com.josewillian.Structures.Queue;

/**
 * Defines an interface that provides control of the elements using the First-in-first-out (FIFO) strategy.
 */
public interface Queue<E> {
    
    /**
     * Adds a new element in the last position of this Queue.
     * 
     * @param e - element to add in this queue
     * @return true if the addition is successful
     */
    public boolean enqueue(E e);
    
    /**
     * Removes the oldest element present in this Queue.
     * 
     * @return data of the element previosly stored
     */
    public E dequeue();
    
    /**
     * Returns the element stored in the Queue head, but doesn't modify it. 
     * If this Queue is empty, the method returns a null value.
     * 
     * @return data of the element of this Queue or null if the Queue is empty
     */
    public E peek();

    /**
     * Returns the number of elements in this Queue.
     * 
     * @return number of elements in this Queue.
     */
    public int size();

    /**
     * Returns an array with all elements of this queue.
     * The array contains all elements in dequeue order.
     * 
     * @return array with elements of this queue
     */
    public E[] toArray();
    
    /**
     * Returns if the Queue has no element.
     * 
     * @return true if this Queue contains no element
     */
    public boolean isFull();
    
    /**
     * Returns if this Queue is full.
     * 
     * @return true if this Queue is full
     */
    public boolean isEmpty();

}
