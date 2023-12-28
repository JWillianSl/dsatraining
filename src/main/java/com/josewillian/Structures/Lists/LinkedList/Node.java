package com.josewillian.Structures.Lists.LinkedList;

/**
 * Each Node present in a LinkedList store the data of internal elements.
 * Provides access to the previous Node and the next Node, such access occurs with reference points.
 * A Node with null data is a Nil Node (sentinel Node). Inaccessible Node representing the end of the LinkedList.
 */
public class Node<T> {

    /**
     * Data representing an element stored in this Node.
     */
    private T data;

    /**
     * Access reference to the previous Node.
     */
    private Node<T> previous;

    /**
     * Access reference to the next Node.
     */
    private Node<T> next;

    /**
     * Constructs a Nil Node, sentinel Node.
     */
    public Node(){
    }

    /**
     * Constructs a Ready Node with basic information to prepare it.
     * 
     * @param data - element data to be stored
     * @param previous - previous Node of this
     * @param next - next Node of this
     */
    public Node(T data, Node<T> previous, Node<T> next){
        this.data = data;
        this.previous = previous;
        this.next = next;
    }
    
    /**
     * Returns the next Node of this.
     * 
     * @return a Node representing the next one
     */
    public Node<T> getNext(){
        return next;
    }

    /**
     * Returns the previous Node of this.
     * 
     * @return a Node representing the previous one
     */
    public Node<T> getPrevious(){
        return previous;
    }

    /**
     * Returns the Data of this Node.
     * 
     * @return the Data stored in this Node
     */
    public T getData(){
        return data;
    }

    /**
     * Checks whether this is a Nil Node.
     * 
     * @return true if this Node is a Nil Node
     */
    public boolean isNil(){
        return (data == null);
    }

    /**
     * Defines a new Data for this Node. The old data is returned.
     * 
     * @param data - new Data to replace
     * @return the old Data of this node
     */
    public T setData(T data){
        T old = this.data;
        this.data = data;

        return old;
    }

    /**
     * Defines a new Previous Node for this Node. The old Node object is returned.
     * 
     * @param node - new Previous Node
     * @return a Node object
     */
    public Node<T> setPrevious(Node<T> node){
        Node<T> old = this.previous;
        this.previous = node;

        return old;
    }

    /**
     * Defines a new Next Node for this Node. The old Node object is returned.
     * 
     * @param node - new Next Node
     * @return a Node object
     */
    public Node<T> setNext(Node<T> node){
        Node<T> old = this.next;
        this.next = node;

        return old;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        return result;
    }
  

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if(!(obj instanceof Node) || obj == null){
            return false;
        }

        Node<T> newRef = (Node<T>) obj;
        
        if(newRef.getData() == null)
            return false;
        if(newRef.getData().equals(this.getData()))
            return false;

        return true;
    }

}
