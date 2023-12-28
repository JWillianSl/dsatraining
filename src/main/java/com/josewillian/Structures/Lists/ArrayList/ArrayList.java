package com.josewillian.Structures.Lists.ArrayList;

import com.josewillian.Structures.Lists.List;

/**
 * Array-based structure, the ArrayList class implements the List interface and provides control over the
 * stored items.
 * This structure has an initial size and uses the resizing strategy to store a large number of elements.
 */
public class ArrayList<E> implements List<E>{

    /**
     * Default capacity of the internal array that stores the elements in the ArrayList.
     */
    private final int DEFAULT_CAPACITY = 20;

    /**
     * Array that stores the elements in the ArrayList.
     * It has a dynamic size that increases according to demand.
     */
    private E[] elements;

    /**
     * Represents the number of elements stored in the ArrayList.
     */
    private int size;

    /**
     * Constructs an empty ArrayList with an initial capacity of twenty.
     */
    @SuppressWarnings("unchecked")
    public ArrayList(){
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public E get(int index) {
        if(!checkIndex(index)){
            throw new IndexOutOfBoundsException();
        }

        return this.elements[index];
    }

    @Override
    public E set(E e, int index) {
        if(!checkIndex(index)){
            throw new IndexOutOfBoundsException();
        }

        E out = this.elements[index];
        this.elements[index] = e;

        return out;
    }

    @Override
    public void add(E e, int index) {
        if(!checkIndex(index)){
            throw new IndexOutOfBoundsException();
        }

        ensureCapacity(this.size + 1);
        shiftRight(index);

        this.elements[index] = e;
        this.size++;
    }

    @Override
    public boolean add(E e) {
        ensureCapacity(this.size + 1);
        this.elements[this.size++] = e;

        return true;
    }

    @Override
    public boolean addAll(E[] a){
        ensureCapacity(this.size + a.length);

        for(E item : a)
            this.add(item);

        return true;
    }

    @Override
    public boolean remove(E e) {
        int firstOccurrence = indexOf(e);

        if(firstOccurrence == -1 || e == null){
            return false;
        }

        shiftLeft(firstOccurrence);
        this.size--;

        return true;
    }

    @Override
    public E remove(int index) {
        if(!checkIndex(index)){
            throw new IndexOutOfBoundsException();
        }

        E out = this.elements[index];
        shiftLeft(index);
        this.size--;

        return out;
    }

    @Override
    public int indexOf(E e) {
        int index = -1;
        int i = 0;

        while(i < this.size && index == -1){
            if(this.elements[i].equals(e)){
                index = i;
            }

            i++;
        }

        return index;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public boolean contains(E e) {
        boolean out = false;
        int i = 0;

        while(i < this.size && out == false){            
            if(this.elements[i].equals(e)){
                out = true;
            }

            i++;
        }

        return out;
    }

    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] out = (E[]) new Object[this.size];

        for(int i = 0; i < this.size; i++){
            out[i] = this.elements[i];
        }

        return out;
    }

    /**
     * Increases the capacity of this ArrayList if the current capacity is less than the given value.
     * 
     * @param minValue - minimum value for capacity.
     */
    private void ensureCapacity(int minValue){
        if(minValue > this.elements.length){
            resize(Math.max(this.elements.length * 2, minValue));
        }
    }

    /**
     * Increases the capacity of internal array that stores all elements of this ArrayList.
     * Copy all original elements to the new array.
     * 
     * @param newSize - new capacity for internal array
     */
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        E[] newArray = (E[]) new Object[newSize];

        for(int i = 0; i < this.size; i++)
            newArray[i] = this.elements[i];

        this.elements = newArray;
    }

    /**
     * Moves an element stored at the given index and any subsequent element to right.
     * 
     * @param index - valid position in ArrayList to move 
     */
    private void shiftRight(int index){
        for(int i = this.size - 1; i <= index; i--)
            this.elements[i + 1] = this.elements[i];
    }

    /**
     * Moves an element stored at the given index and any subsequent element to left.
     * 
     * @param index - valid position in ArrayList to move
     */
    private void shiftLeft(int index){
        for(int i = index; i < this.size; i++)
            this.elements[i] = this.elements[i + 1];
    }

    /**
     * Checks whether a given index is a valid value.
     * 
     * @param index - value that represents an index in this ArrayList
     * @return true if the given index is a valid value
     */
    private boolean checkIndex(int index){
        return (index >= 0 && index < this.size);
    }
    
}
