package tool;


import java.io.Serializable;

public class HashTable<K, V> implements Serializable{
    private static final int DEFAULT_CAPACITY = 32;
    private Node<K, V>[] table;
    private int size;

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    public HashTable(int capacity) {
        table = new Node[capacity];
        size = 0;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> newNode = new Node<>(key, value);

        if (table[index] == null) {
            table[index] = newNode;
        } else {
            Node<K, V> current = table[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    // Key already exists, update the value
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            // Add the new node to the end of the chain
            current.next = newNode;
        }

        size++;
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        return null; // Key not found
    }

    public void remove(K key) {
        int index = getIndex(key);
        Node<K, V> current = table[index];
        Node<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    // Removing the first node in the chain
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public int size() {
        return size;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        Node<K, V> current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return true; // Key found
            }
            current = current.next;
        }

        return false; // Key not found
    }

    public Set<K> keySet() {
        Set<K> keySet = new Set<>();

        for (Node<K, V> node : table) {
            Node<K, V> current = node;
            while (current != null) {
                keySet.add(current.key);
                current = current.next;
            }
        }
        return keySet;
    }

    public Set<V> values() {
        Set<V> values = new Set<>();

        for (Node<K, V> node : table) {
            Node<K, V> current = node;
            while (current != null) {
                values.add(current.value);
                current = current.next;
            }
        }
        return values;
    }

    private static class Node<K, V> implements Serializable{
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }


}
