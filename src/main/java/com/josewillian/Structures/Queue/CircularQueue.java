package com.josewillian.Structures.Queue;

import com.josewillian.Exceptions.StorageOverflowException;
import com.josewillian.Exceptions.StorageUnderflowException;

/**
 * Implements Queue interface and uses circular access to store elements.
 */
public class CircularQueue<E> implements Queue<E> {

    /**
     * Representing the position of the first element in this Queue.
     */
    private int head;
    
    /**
     * Representing the position of the last element in this Queue.
     */
    private int tale;
    
    /**
     * Number of elements stored in this Queue.
     */
    private int size;
    
    /**
     * Array that stores the elements.
     */
    private E[] storage;
    
    /**
     * Limit of elements to be stored in this Queue.
     */
    private int capacity;
    
    /**
     * Custructs a Queue.
     * Using a default capacity of 20 elements.
     */
    public CircularQueue(){
        this(20);
    }

    /**
     * Constructs a Queue using a given value that represents the maximum capacity.
     * 
     * @param capacity - maximum capacity of this Queue
     */
    @SuppressWarnings("unchecked")
    public CircularQueue(int capacity){
        this.storage = (E[]) new Object[capacity];
        this.capacity = capacity;
        this.head = -1;
        this.tale = -1;
    }

    @Override
    public boolean enqueue(E e) {
        if(isFull()){
            throw new StorageOverflowException("Queue is Full.");
        }

        else if(isEmpty()){
            this.head++;
        }

        this.tale = (this.tale + 1) % this.capacity;
        this.storage[this.tale] = e;
        this.size++;

        return true;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new StorageUnderflowException("Queue is Empty.");
        }

        E out = this.storage[this.head];
        this.head = (this.head + 1) % this.capacity;
        this.size--;

        if(isEmpty()){
            this.head = -1;
            this.tale = -1;
        }

        return out;
    }

    @Override
    public E peek() {
        E out = null;

        if(!isEmpty()){
            out = this.storage[this.head];
        }

        return out;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] out = (E[]) new Object[this.size()];
        Queue<E> temp = new CircularQueue<E>();

        for(int i = 0; i < out.length; i++){
            E element = this.dequeue();

            out[i] = element;
            temp.enqueue(element);
        }

        while(!temp.isEmpty()){
            this.enqueue(temp.dequeue());
        }

        return out;
    }

    @Override
    public boolean isFull() {
        return ((this.tale + 1) % this.capacity == this.head);
    }

    @Override
    public boolean isEmpty() {
        return (this.size() == 0);
    }

}
