package com.josewillian.Structures.Tree.bst;

/**
 * Each Node present in a Tree store the data of internal elements.
 * Provides access to the left Node, to the right Node, to the parent Node, such access occurs with reference points.
 * A Node with null data is a Nil Node (sentinel Node). Inaccessible Node representing the end of the LinkedList.
 * If a Node has a left Node equals to Nil and a right Node equals to Nil, then it is a Leaf. 
 */
public class BSTNode<T extends Comparable<T>> {

    /**
     * Data representing an element stored in this Node.
     */
    private T data;

    /**
     * Access reference to the left Node.
     */
    private BSTNode<T> left;

    /**
     * Access reference to the right Node.
     */
    private BSTNode<T> right;

    /**
     * Access reference to the parent Node.
     */
    private BSTNode<T> parent;
    
    /**
     * Constructs a Nil Node, sentinel Node.
     */
    public BSTNode(){
    }

    /**
     * Constructs a Ready Node with basic information to prepare it.
     * 
     * @param data - element data to be stored
     * @param left - left Node of this
     * @param right - right Node of this
     * @param parent - parent Node of this
     */
    public BSTNode(T data, BSTNode<T> left, BSTNode<T> right, BSTNode<T> parent){
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    /**
     * Returns the Left Node of this.
     * 
     * @return a Node representing the left Node
     */
    public BSTNode<T> getLeft(){
        return this.left;
    }

    /**
     * Returns the Right Node of this.
     * 
     * @return a Node representing the Right Node
     */
    public BSTNode<T> getRight(){
        return this.right;
    }

    /**
     * Returns the Parent Node of this.
     * 
     * @return a Node representing the Parent Node
     */
    public BSTNode<T> getParent(){
        return this.parent;
    }

    /**
     * Returns the Data of this Node.
     * 
     * @return the Data stored in this Node
     */
    public T getData(){
        return this.data;
    }

    /**
     * Sets a new Left Node for this Node.
     * 
     * @param node - new Left Node
     * @return previous Left Node
     */
    public BSTNode<T> setLeft(BSTNode<T> node){
        BSTNode<T> old = this.left;
        this.left = node;

        return old;
    }

    /**
     * Sets a new Right Node for this Node.
     * 
     * @param node - new Right Node
     * @return previous Right Node
     */
    public BSTNode<T> setRight(BSTNode<T> node){
        BSTNode<T> old = this.right;
        this.right = node;

        return old;
    }

    /**
     * Sets a new Parent Node for this Node.
     * 
     * @param node - new Parent Node
     * @return previous Parent Node
     */
    public BSTNode<T> setParent(BSTNode<T> node){
        BSTNode<T> old = this.parent;
        this.parent = node;

        return old;
    }

    /**
     * Checks if this Node is a Nil Node.
     * To be a Nil Node, this Node must have data equals to null.
     * 
     * @return true if this is a Nil Node
     */
    public boolean isNil(){
        return (this.data == null);
    }

    /**
     * Checks if this Node is a Leaf.
     * 
     * @return true if this is a Leaf Node. 
     */
    public boolean isLeaf(){
        return (this.left.isNil() && this.right.isNil());
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
        if(!(obj instanceof BSTNode) || obj == null){
            return false;
        }

        BSTNode<T> newRef = (BSTNode<T>) obj;

        if(newRef.getData() == null)
            return false;
        if(newRef.getData().equals(this.getData()))
            return false;

        return true;
    }

}
