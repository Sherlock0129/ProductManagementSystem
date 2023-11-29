package tool;



import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Set<E> implements Serializable,Iterable<E> {
    // Store elements in arrays
    private E[] elements;
    private int size;

    private static final int DEFAULT_SIZE = 16;

    // Constructor, initializing the array
    public Set() {
        elements = (E[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    // Constructor, initializing the array
    public Set(int capacity) {
        elements = (E[]) new Object[capacity];
        size = 0;
    }

    // Add elements to the collection

    public void add(E element) {
        if (size == elements.length) {
            resizeArray();
        }else if (contains(element)){
            return;
        }
        elements[size++] = element;
    }

    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] array = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = (E) elements[i];
        }
        return array;
    }

    // Deletes an element from the collection

    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                // 如果找到要删除的元素，缩小数组并返回true
                for (int j = i + 1; j < size; j++) {
                    elements[j - 1] = elements[j];
                }
                size--;
                return true;
            }
        }
        return false; // If you can't find the element you want to delete, return false
    }

    // Check if the specified elements are included in the collection

    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return true; // If a matching element is found, true is returned
            }
        }
        return false; // If no matching element is found, false is returned
    }

    private void resizeArray() {
        int newCapacity = elements.length * 2;
        Object[] newElements = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = (E[]) newElements;
    }


    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[index++];
            }
        };
    }

    // 返回集合的大小

    public int size() {
        return size; //Returns the collection size
    }


}

