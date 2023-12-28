package com.josewillian.Structures.Lists.LinkedList;

import com.josewillian.Exceptions.ListUnderflowException;
import com.josewillian.Structures.Lists.List;


/**
 * Defines an AbstractList that must be implemented by LinkedList algorithm.
 */
abstract class AbstractList<E> implements List<E>{

    /**
     * Stores the reference of the first valid Node.
     */
    protected Node<E> head;

    /**
     * Stores the reference of the last Node, an invalid Nil Node.
     * It is always above the last Nil Node after the last element.
     */
    protected Node<E> tale;

    /**
     * Represents the number of elements stored in this List.
     */
    protected int size;

    @Override
    public E get(int index) {
        validateIndex(index);
        return get(index, 0, this.head);
    }

    @Override
    public E set(E e, int index) {
        validateIndex(index);
        return set(e, index, 0, this.head);
    }

    @Override
    public void add(E e, int index) {
        validateIndex(index);

        Node<E> newNode = new Node<E>();
        newNode.setData(e);
        
        add(newNode, index, 0, this.head);
    }

    @Override
    public boolean add(E e) {
        boolean out = false;

        if(e != null){
            Node<E> newTale = new Node<E>();

            newTale.setPrevious(this.tale);
            this.tale.setNext(newTale);
            this.tale.setData(e);
            this.tale = newTale;

            this.size++;
            out = true;
        }

        return out;
    }

    @Override
    public boolean addAll(E[] a) {
        for(E item : a)
            add(item);

        return true;
    }

    @Override
    public boolean remove(E e) {
        return remove(e, this.head);
    }

    @Override
    public E remove(int index) {
        validateIndex(index);

        return remove(index, 0, this.head);
    }

    /**
     * Removes the last element stored in this List.
     * 
     * @return the element previously stored in the last position
     */
    public E removeLast(){
        if(isEmpty()){
            throw new ListUnderflowException("The list is empty.");
        }
        E out = this.tale.getPrevious().getData();
        this.size--;

        this.tale = this.tale.getPrevious();
        this.tale.getNext().setPrevious(null);
        this.tale.setNext(null);
        this.tale.setData(null);

        return out;
    }

    /**
     * Removes the first element stored in this List.
     * 
     * @return the element previously stored in the first position
     */
    public E removeFirst(){
        if(isEmpty()){
            throw new ListUnderflowException(null);
        }
        E out = this.head.getData();
        this.size--;

        this.head.setPrevious(null);
        this.head.setData(null);
        this.head = this.head.getNext();

        return out;
    }

    @Override
    public int indexOf(E e) {
        return indexOf(e, 0, this.head);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.head = new Node<E>();
        this.tale = head;
        this.size = 0;
    }

    @Override
    public boolean contains(E e) {
        return contains(e, 0, this.head);
    }

    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] out = (E[]) new Comparable[this.size];
        toArray(out, 0, this.head);

        return out;
    }

    /**
     * Returns the first element, but doesn't modify it.
     * 
     * @return element stored in the first position
     */
    public E peekFirst(){
        return this.head.getData();
    }

    /**
     * Returns the last element, but doesn't modify it.
     * 
     * @return element stored in the last position
     */
    public E peekLast(){
        return this.tale.getData();
    }

    /**
     * Validates a given index.
     * Uses the size of this List to datermine the validation.
     * 
     * @param index a given position to check the validation
     */
    private void validateIndex(int index){
        if(!(index >= 0 && index < this.size)){
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Uses a recursive strategy to find an element in this List.
     * The Nodes in each recursive step are represented by a number (control).
     * 
     * @param target - valid number to get an element
     * @param control - number representing each recursive step
     * @param pivot - current Node of each recursive step
     * @return target Node data
     */
    protected abstract E get(int target, int control, Node<E> pivot);

    /**
     * Uses a recursive strategy to set an element in this List.
     * The Nodes in each recursive step are represented by a number (control).
     * 
     * @param e - element to be stored in this List
     * @param target - valid number to set an element
     * @param control - number representing each recursive step
     * @param pivot - current Node of each recursive step
     * @return previously stored data Node
     */
    protected abstract E set(E e, int target, int control, Node<E> pivot);

    /**
     * Uses a recursive strategy to add an element in this List.
     * The Nodes in each recursive step are represented by a number (control).
     * Case the target is between two Nodes, this method will insert the new Node and update all 
     * Node references.
     * 
     * @param node - ready Node to be stored in this List
     * @param target - valid number to add an element
     * @param control - number representing each recursive step
     * @param pivot - current Node of each recursive step
     */
    protected abstract void add(Node<E> node, int target, int control, Node<E> pivot);

    /**
     * Uses a recursive strategy to remove an element in this List.
     * To find the element in this List, this method compares the data of a Node with a given data.
     * 
     * @param e - element to remove in this List
     * @param pivot - current Node of each recursive step
     * @return true if the element was stored in this List
     */
    protected abstract boolean remove(E e, Node<E> pivot);

    /**
     * Uses a recursive strategy to remove an element in this List.
     * The Nodes in each recursive step are represented by a number (control).
     * Case the target is between two Nodes, this method will unlink the Node and connect the
     * other Nodes.
     * 
     * @param target - valid number to remove in this List
     * @param control - number representing each recursive step
     * @param pivot - current Node of each recursive step
     * @return data previously stored in pivot Node
     */
    protected abstract E remove(int target, int control, Node<E> pivot);

    /**
     * Returns the index of first occurs of a given element. Uses a recursive strategy
     * to find the element.
     * The Nodes in each recursive step are represented by a number (control).
     * 
     * @param e - element to search for
     * @param control - number representing each recursive step
     * @param pivot - current Node of each recursive step
     * @return number representing a valid index
     */
    protected abstract int indexOf(E e, int control, Node<E> pivot);

    /**
     * Returns if this List stored a given element.
     * Uses a recursive strategy.
     * The Nodes in each recursive step are represented by a number (control).
     * 
     * @param e - element to search for
     * @param control - number representing each recursive step
     * @param pivot - current Node of each recursive step
     * @return true if the element is stored in this List
     */
    protected abstract boolean contains(E e, int control, Node<E> pivot);

    /**
     * Fill a given array using all data Nodes. 
     * Uses a number (control) in each recursive step to store the elements in the array.
     * 
     * @param a - array to fill with data Nodes
     * @param control - number representing each recursive step
     * @param pivot - current Node of each recursive step
     */
    protected abstract void toArray(E[] a, int control, Node<E> pivot);
    
}
