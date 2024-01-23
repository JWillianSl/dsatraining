package com.josewillian.Structures.Heap;

import java.util.Arrays;
import java.util.Comparator;

import com.josewillian.Structures.Heap.Comparators.MinHeap;
import com.josewillian.Util.Util;

/**
 * Implements a binary Heap, also called a priority queue.
 * By default, this Heap uses Min Heap comparator to provide smallest elements priority.
 */
public class Heap<E extends Comparable<E>> {

    /**
     * Representing the initial capacity of this Heap.
     */
    private final int INITIAL_CAPACITY = 20;
    
    /**
     * Representing the Comparator to be used in this Heap.
     */
    private Comparator<E> comparator;
    
    /**
     * Heap array to store and control the elements.
     */
    private E[] heap;

    /**
     * Reference to the last element of this Heap.
     */
    private int last;

    /**
     * Constructs a Heap with an initial size of 20 elements.
     */
    public Heap(){
        this(new MinHeap<E>());
    }

    /**
     * Constructs a Heap using a given array.
     * 
     * @param array - given array to build heap
     */
    public Heap(E[] array){
        this(new MinHeap<E>());
        this.buildHeap(array);
    }

    /**
     * Constructs a Heap with an intial size of 20 elements.
     * Uses a given comparator to works.
     * 
     * @param comparator - given comparator for internal actions
     */
    @SuppressWarnings("unchecked")
    public Heap(Comparator<E> comparator){
        this.heap = (E[]) new Comparable[INITIAL_CAPACITY];
        this.comparator = comparator;
        this.last = -1;
    }

    /**
     * Adds a new element in this Heap.
     * If this Heap is full, this method will increase the size.
     * 
     * @param e - a new element to store in this Heap
     * @return true if the addition is successful 
     */
    public boolean add(E e){
        if(isFull()){
            resize();
        }
        
        if(e == null){
            throw new IllegalArgumentException();
        }

        this.heap[++this.last] = e;
        moveUp(this.last);

        return true;
    }

    /**
     * Removes the current element in the root position.
     * Returns a null object, if this Heap is empty.
     * After this extraction, the heap will place the correct element in the root position.
     * 
     * @return the element previously stored in the root position
     */
    public E extractRoot(){
        E out = null;

        if(!isEmpty()){
            out = getRoot();
            this.heap[0] = this.heap[last--];

            heapify(0);
        }

        return out;
    }

    /**
     * Using the current comparator to sort the elements of a given array.
     * If the current comparator is the Min Heap, the elements will be stored in increase order.
     * Replaces all elements previously stored in this Heap.
     * 
     * @param arr - a given array to sort
     * @return an array sorted
     */
    @SuppressWarnings("unchecked")
    public E[] heapSort(E[] arr){
        buildHeap(arr);
        E[] out = (E[]) new Comparable[this.size()];

        for(int i = 0; i < out.length; i++){
            out[i] = extractRoot();
        }

        return out; 
    }

    /**
     * Returns the quantity of elements in this Heap.
     * 
     * @return the quantity of elements stored
     */
    public int size(){
        return (this.last + 1);
    }

    /**
     * Returns the current element in the root position of this Heap.
     * 
     * @return the element with top priority
     */
    public E getRoot(){
        return this.heap[0];
    }

    /**
     * Returns the current comparator.
     * 
     * @return the current comparator
     */
    public Comparator<E> getComparator(){
        return this.comparator;
    }

    /**
     * Replaces the current comparator of this Heap.
     * initially, the comparator of this heap is a Min Heap comparator.
     * 
     * @param newComparator - new comparator to replace the current one
     */
    public void setComparator(Comparator<E> newComparator){
        this.comparator = newComparator;
        buildHeap(heap);
    }

    /**
     * Returns true if this heap is empty.
     * 
     * @return true if this heap is empty
     */
    public boolean isEmpty(){
        return (this.size() == 0);
    }

    /**
     * Returns true if this heap is full.
     * 
     * @return true if this heap is full
     */
    public boolean isFull(){
        return (this.size() == heap.length);
    }

    /**
     * Returns an array of elements.
     * 
     * @return array of heap elements
     */
    @SuppressWarnings("unchecked")
    public E[] toArray(){
        E[] out = (E[]) new Comparable[this.size()];

        for(int i = 0; i <= this.last; i++){
            out[i] = this.heap[i];
        }

        return out;
    }

    /**
     * Uses an array to build this heap.
     * Overwrites any data previously stored in this heap.
     * 
     * @param arr - a given array to build this heap
     */
    private void buildHeap(E[] arr){
        this.heap = Arrays.copyOf(arr, Math.max(arr.length, heap.length));
        lastPositionUpdate();
        
        for(int i = getParent(this.last); i >= 0; i--){
            heapify(i);
        }
    }

    /**
     * Using a given index, this method moves the element down if the element has 
     * low priority compared to its children.
     * 
     * @param index - a valid index
     */
    private void heapify(int index){

        if(!isLeaf(index)){
            int target =  compareChildren(index);

            if(comparator.compare(this.heap[target], this.heap[index]) > 0){
                Util.swap(heap, target, index);
                heapify(target);
            }
        }

    }

    /**
     * Using the left element and the right element, this method returns which is the priority element.
     * If the index element doesn't have a right element, the return will be the left index.
     * 
     * @param index - a given index to check the children
     * @return index representing the element with top priority.
     */
    private int compareChildren(int index){
        int out = getLeft(index);

        if(getRight(index) <= this.last){
            E left = this.heap[getLeft(index)];
            E right = this.heap[getRight(index)];

            if(comparator.compare(right, left) > 0){
                out = getRight(index);
            }
        }

        return out;
    }

    /**
     * Moves the element in a given index up. Compares with the parent element to decide if to move up.
     * 
     * @param index - a given index to compare with its parent
     */
    private void moveUp(int index){
        int parent = getParent(index);

        if(index > 0 && comparator.compare(this.heap[index], this.heap[parent]) > 0){
            Util.swap(heap, index, parent);
            moveUp(parent);
        }

    }

    /**
     * Increases the capacity of the internal array.
     * The new size will be double the current one.
     */
    @SuppressWarnings("unchecked")
    private void resize(){
        E[] temp = (E[]) Arrays.copyOf(heap, heap.length);
        this.heap = (E[]) new Comparable[heap.length * 2];

        buildHeap(temp);
    }

    /**
     * Updates the last pivot position.
     * Used when the build heap method receives a given array.
     */
    private void lastPositionUpdate(){
        int pivot = 0;

        while(pivot < this.heap.length && this.heap[pivot] != null){
            pivot++;
        }

        this.last = pivot - 1;
    }

    /**
     * Checks if the element stored in a given position is a leaf.
     * To be a leaf, the element doesn't have a left element.
     * 
     * @param index - a valid index
     * @return true if the element in the index position is a leaf
     */
    private boolean isLeaf(int index){
        return (getLeft(index) > this.last);
    }

    /**
     * Returns an index representing the parent position of a given index.
     * 
     * @param index - a given index to return the parent index
     * @return the parent index of a given index
     */
    private int getParent(int index){
        return ((index - 1) / 2);
    }

    /**
     * Returns an index representing the left position of a given index.
     * 
     * @param index - a given index to return the left index
     * @return the left index of a given index
     */
    private int getLeft(int index){
        return ((index * 2) + 1);
    }

    /**
     * Returns an index representing the parent position of a given index.
     * 
     * @param index - a given index to return the right index
     * @return the right index of a given index
     */
    private int getRight(int index){
        return ((index * 2) + 2);
    }

}
