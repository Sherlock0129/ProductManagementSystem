package tool;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<E> implements Iterable<E>, Serializable {
    private Node<E> head;   // linkedListHeaderNode
    private Node<E> tail;   // linkedListTrailingNode
    private int size;   // theSizeOfTheLinkedList

    public Node<E> getHead() {
        return head;
    }

    public void setHead(Node<E> head) {
        this.head = head;
    }


    // Add an element to the tail of the linked list
    public void add(E element) {
        Node<E> newNode = new Node<>(element);  // Create a new node
        if (head == null) { // ifTheLinkedListIsEmpty
            head = newNode; // The new node becomes the head node
            tail = newNode; // The new node also becomes the tail node
        } else {
            tail.next = newNode;    // The next node of the current tail node points to the new node
            newNode.prev = tail;    // The previous node of the new node points to the current tail node
            tail = newNode; // The new node becomes the new tail node
        }
        size++; // The linked list size is plus one
    }

    // Gets the element at the specified index location
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<E> current = head; // Start with the head node
        for (int i = 0; i < index; i++) {
            current = current.next; // Nodes that are moved to the specified index location
        }
        return current.data;    // Returns data for that node
    }


    public void set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<E> current = head; // Start with the head node
        for (int i = 0; i < index; i++) {
            current = current.next; // Nodes that are moved to the specified index location
        }

        current.data = element; // Set the data for the node to a new value
    }

    // Deletes the specified element from the linked list
    public void remove(E element) {
        Node<E> current = head; // Start with the head node
        while (current != null) {
            if (current.data.equals(element)) { // Find the matching element
                if (current == head) {  // If the head node is matched
                    head = current.next;    // Update the head node
                    if (head != null) {
                        head.prev = null;
                    }
                } else if (current == tail) {   // If the trail node is matched
                    tail = current.prev;    // Update the tail node
                    if (tail != null) {
                        tail.next = null;
                    }
                } else {
                    current.prev.next = current.next;   // Update the next node of the previous node
                    current.next.prev = current.prev;   // Updates the previous node of the next node
                }
                size--; // The linked list size is minus one
                break;
            }
            current = current.next; // Continue looking for the next node
        }
    }

    // Get the size of the linked list
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E[] toArray(E[] array) {
        if (array.length < size) {
            array = (E[]) new Object[size];
        }

        Node<E> current = head; // Start with the head node

        for (int i = 0; i < size; i++) {
            array[i] = current.data; // Put the data of the current node into an array
            current = current.next;   // Move to the next node
        }

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    public boolean contains(String element) {
        Node<E> current = head; // Start with the head node
        while (current != null) {
            if (current.data.equals(element)) { // Find the matching element
                System.out.println("The element is in the list");
                break;
            }
            current = current.next; // Continue looking for the next node
        }
        return false;
    }

    // 内部类实现迭代器
    private class ListIterator implements Iterator<E> {
        private Node<E> current = head; // The current position of the iterator

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E data = current.data; // Get the data of the current node
            current = current.next; // Move to the next node
            return data;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }
}

