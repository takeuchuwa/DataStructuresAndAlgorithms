package com.horban.study;

public class LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int length;

    public LinkedList(T value) {
        createFirst(value);
    }

    public void append(T value) {
        if (length == 0) {
            createFirst(value);
        } else {
            Node<T> newNode = new Node<>(value);
            tail.next = newNode;
            tail = newNode;
            length++;
        }
    }

    private void createFirst(T value) {
        head = new Node<>(value);
        tail = head;
        length = 1;
    }

    public void prepend(T value) {
        if (length == 0) {
            createFirst(value);
        } else {
            Node<T> newNode = new Node<>(value);
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    public Node<T> removeFirst() {
        Node<T> removedNode = head;
        if (length == 0) {
            return null;
        } else if (length == 1) {
            head = null;
            tail = null;
        } else {
            head = removedNode.next;
            removedNode.next = null;
        }
        length--;
        return removedNode;
    }

    public Node<T> removeLast() {
        Node<T> removedNode = tail;
        if (length == 0) {
            return null;
        } else if (length == 1) {
            head = null;
            tail = null;
        } else {
            Node<T> newTail = findElementBeforeTail();
            newTail.next = null;
            tail = newTail;
        }
        length--;
        return removedNode;
    }

    public Node<T> get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        } else if (index >= length) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> nodeAtIndex = head;
        int currentElement = 0;
        while (index != currentElement) {
            nodeAtIndex = nodeAtIndex.next;
            currentElement++;
        }
        return nodeAtIndex;
    }

    public boolean set(int index, T value) {
        Node<T> tNode = get(index);
        tNode.value = value;
        return true;
    }

    public boolean insert(int index, T value) {
        if (index < 0) {
            throw new IllegalArgumentException();
        } else if (index > length) {
            throw new IndexOutOfBoundsException();
        }

        if (index == length) {
            append(value);
        } else if (index == 0) {
            prepend(value);
        } else {
            Node<T> prevNode = get(index - 1);
            Node<T> newNode = new Node<>(value);
            newNode.next = prevNode.next;
            prevNode.next = newNode;
            length++;
        }
        return true;
    }

    public Node<T> remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        } else if (index >= length) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return removeFirst();
        } else if (index == length - 1) {
            return removeLast();
        }
        Node<T> prevNode = get(index - 1);
        Node<T> removedNode = prevNode.next;
        prevNode.next = removedNode.next;
        removedNode.next = null;
        length--;
        return removedNode;
    }

    public void reverse() {
        Node<T> temp = head;
        head = tail;
        tail = temp;

        Node<T> prevNode = null;
        for (int i = 0; i < length; i++) {
            Node<T> nextNode = temp.next;
            temp.next = prevNode;
            prevNode = temp;
            temp = nextNode;
        }
    }

    private Node<T> findElementBeforeTail() {
        Node<T> currentNode = head;
        while (currentNode.next != tail) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    public void printList() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void getHead() {
        if (head == null) {
            System.out.println("Head: null");
        } else {
            System.out.println("Head: " + head.value);
        }
    }

    public void getTail() {
        if (head == null) {
            System.out.println("Tail: null");
        } else {
            System.out.println("Tail: " + tail.value);
        }
    }

    public void getLength() {
        System.out.println("Length: " + length);
    }

    static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

}