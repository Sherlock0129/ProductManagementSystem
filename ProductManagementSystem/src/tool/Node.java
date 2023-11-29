package tool;

import java.io.Serializable;

public  class Node<E> implements Serializable {
    E data;  // Node data
    Node<E> prev;   // PREVIOUS NODE
    Node<E> next;   // Next node

    Node(E data) {
        this.data = data;
    }

    Node() {}

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getPrev() {
        return prev;
    }

    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}


