package org.mconsta000.lrucache;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU Cache implimentation
 */
public final class LRUCache {
    private Map<Integer,Node> nodes = new HashMap<Integer, Node>();
    private Node head = null;
    private Node tail = null;
    private int capacity = 0;
    private int size = 0;
 
    private class Node {
        private Integer key;
        private Integer value;
        private Node previous;
        private Node next;

        Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = nodes.get(key);
        int ret = -1;
        if (node != null) {
            remove(node);
            enqueue(node);
            ret = node.value;
        }
        return ret;
    }
    
    public void put(int key, int value) {
        Node node = nodes.get(key);
        if (node != null) {
            node.value = value;
            remove(node);
            enqueue(node);
        } else {
            node = new Node(key, value);
            if (capacity == size) {
                nodes.remove(tail.key);
                remove(tail);
            }
            enqueue(node);
            nodes.put(node.key, node);
        }
    }

    void remove(Node node) {
        if (node != null) {
            if (node.next != null) {
                node.next.previous = node.previous;
            } else {
                tail = node.previous;
            }

            if (node.previous != null) {
                node.previous.next = node.next;
            } else {
                head = node.next;
            }

            node.next = null;
            node.previous = null;
            
            size--;
        }
    }

    void enqueue(Node node) {
        if (node != null) {
            if (head == null && tail == null) {
                head = node;
                tail = node;
            } else  {
                node.next = head;
                head.previous = node;
                head = node;
            }
            size++;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public int[][] getAscending() {
        int ret[][] = new int[size][2];

        Node current = head;
        int i=0;
        while (current != null){
            ret[i][0] = current.key;
            ret[i][1] = current.value;
            i++;
            current = current.next;
        }
        return ret;
    }

    public int[][] getDescending() {
        int ret[][] = new int[size][2];

        Node current = tail;
        int i=0;
        while (current != null){
            ret[i][0] = current.key;
            ret[i][1] = current.value;
            i++;
            current = current.previous;
        }
        return ret;
    }
}
