package org.duffy.leet_code;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class Node {
        int key, value;
        Node next;
        Node prev;
    }

    private int size;
    private int capacity;
    private Map<Integer, Node> cache = new HashMap<>();
    private Node head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        head = new Node();
        head.next = null;

        tail = new Node();
        tail.prev = null;

        head.prev= tail;
        tail.next = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;

        refresh(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            Node newNode = new Node();
            newNode.value = value;
            newNode.key = key;

            this.cache.put(key, newNode);
            this.addNode(newNode);
            size++;

            if (capacity < size) {
                Node tail = popTail();
                this.cache.remove(tail.key);
                size--;
            }
        }
        else {
            node.value = value;
            this.refresh(node);
        }
    }
    private Node popTail() {
        Node tail = this.tail.next;
        this.removeNode(tail);
        return tail;
    }

    private void refresh(Node node) {
        this.removeNode(node);
        this.addNode(node);
    }
    private void removeNode(Node node) {
        Node next = node.next;
        Node prev = node.prev;

        next.prev = prev;
        prev.next = next;
    }
    private void addNode(Node node) {
        node.next = head;
        node.prev = head.prev;

        head.prev.next = node;
        head.prev = node;
    }
}
