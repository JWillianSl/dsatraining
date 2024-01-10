package com.josewillian.Structures.Stack;

import com.josewillian.Exceptions.StorageOverflowException;
import com.josewillian.Exceptions.StorageUnderflowException;
import com.josewillian.Structures.Lists.LinkedList.LinkedList;

/**
 * The Stack class uses Last-in-first-out (LIFO) strategy to store elements.
 * When a Stack is created, it provides a default capacity of 20 elements.
 */
public class Stack<E> {

    /**
     * Representing the maximum number of elements to be stored in this Stack.
     */
    private int capacity;

    /**
     * LinkedList instance that stores all elements of this Stack.
     */
    private LinkedList<E> storage;
    
    /**
     * Constructs a Stack.
     * The maximum capacity is 20 elements.
     */
    public Stack(){
        this(20);
    }

    /**
     * Constructs a Stack using a given value that represents the maximum capacity.
     * 
     * @param capacity - maximum capacity of this stack
     */
    public Stack(int capacity){
        this.storage = new LinkedList<E>();
        this.capacity = capacity;
    }

    /**
     * Adds a new element in the last position of this Stack.
     * 
     * @param e - new element to add in this Stack
     * @return true if the addition is successful
     */
    public boolean push(E e){
        if(isFull()){
            throw new StorageOverflowException("Stack is Full");
        }

        return this.storage.add(e);
    }

    /**
     * Removes the last element of this Stack.
     * 
     * @return the element previously stored in the last position of this Stack
     */
    public E pop(){
        if(isEmpty()){
            throw new StorageUnderflowException("Stack is Empty");
        }

        return this.storage.removeLast();
    }

    /**
     * Returns the element stored in the last position, but doesn't modify it. 
     * If this Stack is empty, the method returns a null value.
     * 
     * @return data of the last element of this Stack or null if the Stack is empty
     */
    public E peek(){
        return this.storage.peekLast();
    }

    /**
     * Checks if a given element exists in this Stack.
     * 
     * @param e - element to check if this Stack contains it
     * @return true if the element is stored in this Stack
     */
    public boolean contains(E e){
        boolean out = false;
        LinkedList<E> temp = new LinkedList<E>();

        while(!isEmpty() && !out){
            E elementPop = pop();

            if(elementPop.equals(e)){
                out = true;
            }

            temp.add(elementPop);
        }

        while(!temp.isEmpty()){
            push(temp.removeLast());
        }

        return out;
    }

    /**
     * Returns an array of Stack elements.
     * 
     * @return an array with all elements
     */
    @SuppressWarnings("unchecked")
    public E[] toArray(){
        E[] out = (E[]) new Object[this.storage.size()];
        LinkedList<E> temp = new LinkedList<E>();

        for(int i = out.length - 1; i >= 0; i--){
            E element = pop();
            out[i] = element;
            temp.add(element);
        }

        while(!temp.isEmpty()){
            push(temp.removeLast());
        }
        
        return out;
    }

    /**
     * Returns the number of elements stored.
     * 
     * @return the number of elements in this Stack
     */
    public int size(){
        return this.storage.size();
    }

    /**
     * Returns if this Stack is empty.
     * 
     * @return true if this Stack is empty
     */
    public boolean isEmpty(){
        return (this.storage.size() == 0);
    }

    /**
     * Returns if this Stack is full.
     * 
     * @return true if this Stack is full
     */
    public boolean isFull(){
        return (this.storage.size() == capacity);
    }

}
