package com.josewillian.Structures.Queue;

import com.josewillian.Exceptions.StorageOverflowException;
import com.josewillian.Exceptions.StorageUnderflowException;
import com.josewillian.Structures.Lists.LinkedList.LinkedList;

/**
 * Implements Queue interface and uses LinkedList to store elements.
 */
public class LinkedQueue<E> implements Queue<E>{

    /**
     * Representing the maximum number of elements to be stored in this Queue.
     */
    private int capacity;

    /**
     * LinkedList instance that stores all elements of this Queue.
     */
    private LinkedList<E> storage;

    /**
     * Constructs a Queue.
     * Using a default capacity of 20 elements.
     */
    public LinkedQueue(){
        this(20);
    }
    
    /**
     * Constructs a Queue using a given value that represents the maximum capacity.
     * 
     * @param capacity - maximum capacity of this Queue
     */
    public LinkedQueue(int capacity){
        this.storage = new LinkedList<E>();
        this.capacity = capacity;
    }

    @Override
    public boolean enqueue(E e) {
        if(isFull()){
            throw new StorageOverflowException("Queue is Full.");
        }
        
        return this.storage.add(e);
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new StorageUnderflowException("Queue is Empty.");
        }

        return this.storage.removeFirst();
    }

    @Override
    public E peek() {
        return this.storage.peekFirst();
    }

    @Override
    public int size() {
        return this.storage.size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] out = (E[]) new Object[this.size()];
        Queue<E> temp = new LinkedQueue<E>(this.size());

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
        return (this.storage.size() == capacity);
    }

    @Override
    public boolean isEmpty() {
        return (this.storage.size() == 0);
    }

}
