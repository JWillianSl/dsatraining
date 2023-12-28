package com.josewillian.Structures.Lists.LinkedList;

/**
 * Implementation of a LinkedList. Uses the doubly linked Nodes to work.
 */
public class LinkedList<E> extends AbstractList<E> {

    /**
     * Constructs a LinkedList.
     */
    public LinkedList(){
        Node<E> initialNode = new Node<E>();
        initialNode.setPrevious(new Node<E>());

        this.head = initialNode;
        this.tale = head;
        this.size = 0;
    }

    @Override
    protected E get(int target, int control, Node<E> pivot) {
        E result = pivot.getData();

        if(control < target){
            result = get(target, control + 1, pivot.getNext());
        }

        return result;
    }

    @Override
    protected E set(E e, int target, int control, Node<E> pivot) {
        E result = null;

        if(control < target){
            result = set(e, target, control + 1, pivot.getNext());
        }

        else if(control == target){
            result = pivot.getData();
            pivot.setData(e);
        }

        return result;
    }

    @Override
    protected void add(Node<E> node, int target, int control, Node<E> pivot) {
        if(control == target){
            Node<E> prev = pivot.getPrevious();

            node.setNext(pivot);
            node.setPrevious(prev);
            prev.setNext(node);
            pivot.setPrevious(node);
        }

        else if(control < size()){
            add(node, target, control + 1, pivot.getNext());
        }
    }

    @Override
    protected boolean remove(E e, Node<E> pivot) {
        boolean result = false;

        if(pivot.getData().equals(e)){
            unlink(pivot);
            this.size--;
            result = true;
        }

        else if(!(pivot.getNext().isNil())){
            result = remove(e, pivot.getNext());
        }

        return result;
    }

    @Override
    protected E remove(int target, int control, Node<E> pivot) {
        E result = null;

        if(control == target){
            result = pivot.getData();
            this.size--;
            unlink(pivot);
        }

        else if(control < size()){
            result = remove(target, control + 1, pivot.getNext());
        }

        return result;
    }

    @Override
    protected int indexOf(E e, int control, Node<E> pivot) {
        int result = -1;

        if(pivot.getData().equals(e)){
            result = control;
        }

        else if(control < size() - 1){
            result = indexOf(e, control + 1, pivot.getNext());
        }

        return result;
    }

    @Override
    protected boolean contains(E e, int control, Node<E> pivot) {
        boolean result = false;

        if(pivot.getData().equals(e)){
            result = true;
        }

        else if(control < size() - 1){
            result = contains(e, control + 1, pivot.getNext());
        }

        return result;
    }

    @Override
    protected void toArray(E[] a, int control, Node<E> pivot) {
        if(control < size() - 1){
            toArray(a, control + 1, pivot.getNext());
        }

        a[control] = pivot.getData();
    }

    /**
     * Unlinks a given Node of this LinkedList.
     * Connect the previous Node and the next Node, previously connected
     * with this pivot.
     * 
     * @param pivot - given Node for unlink of this List
     */
    private void unlink(Node<E> pivot){
        Node<E> prev = pivot.getPrevious();
        Node<E> next = pivot.getNext();
        
        
        prev.setNext(next);
        next.setPrevious(prev);
        
        pivot.setNext(null);
        pivot.setPrevious(null);

        if(pivot.getData().equals(peekFirst())){
            this.head = next;
        }
    }

}
