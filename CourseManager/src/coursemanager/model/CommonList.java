package coursemanager.model;

import java.nio.file.Path;

public abstract class CommonList<T> {
    protected Node<T> head;
    protected Node<T> tail;

    public CommonList() {
        head = null;
        tail = null;
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return (head == null && tail == null);
    }

    public int size() {
        int count = 0;
        Node<T> current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    public void addFirst(T data) {
        Node<T> node = new Node<>(data);

        if (isEmpty()) {
            head = node;
            tail = node;
            return;
        }

        node.next = head;
        head = node;
    }

    public void addLast(T data) {
        Node<T> node = new Node<>(data);

        if (isEmpty()) {
            head = node;
            tail = node;
            return;
        }

        tail.next = node;
        tail = node;
    }

    public Node<T> getNext(Node<T> node) {
        if (node == null) {
            return null;
        }
        return node.next;
    }

    public Node<T> getPrev(Node<T> node) {
        if (node == head) {
            return null;
        }

        Node<T> pointer = head;
        while (pointer != null && pointer.next != node) {
            pointer = pointer.next;
        }

        return pointer;
    }

    public Node<T> getByIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException();
        }

        int count = 0;
        Node<T> node = head;

        while (node != null) {
            if (count == index) {
                return node;
            }
            count++;
            node = node.next;
        }

        return null;
    }

    public void insertAfter(Node<T> node, T data) {
        if (node.next == null) {
            node.next = new Node<>(data);
            return;
        }

        Node<T> after = node.next;
        Node<T> newNode = new Node<>(data);

        node.next = newNode;
        newNode.next = after;
    }

    public void insertBefore(Node<T> node, T data) {
        if (node == head) {
            addFirst(data);
            return;
        }

        Node<T> prevNode = getPrev(node);
        insertAfter(prevNode, data);
    }

    public void insert(int index, T data) {
        Node<T> node = getByIndex(index);

        if (node == null) {
            return;
        }
        insertBefore(node, data);
    }

    public void deleteFirst() {
        if (isEmpty()) {
            return;
        }

        Node<T> node = head;
        head = head.next;
        node.next = null;
    }

    public void deleteLast() {
        if (isEmpty()) {
            return;
        }

        Node<T> node = head;
        while (node.next != tail) {
            node = node.next;
        }

        node.next = null;
        tail = node;
    }

    public void delete(Node<T> node) {
        if (node == null) {
            return;
        }

        if (node == head) {
            deleteFirst();
            return;
        }

        if (node == tail) {
            deleteLast();
            return;
        }

        Node<T> prev = getPrev(node);
        prev.next = node.next;
        node.next = null;
    }

    public void delete(int index) {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException();
        }

        Node<T> node = getByIndex(index);
        delete(node);
    }

    public void set(Node<T> node, T data) {
        if (node == null) {
            return;
        }
        node.data = data;
    }

    public void set(int index, T data) {
        Node<T> node = getByIndex(index);
        if (node == null) {
            return;
        }
        node.data = data;
    }

    public abstract void display();
    
    public abstract CommonList<T> readFile(Path path);
    public abstract void saveFile(Path path, CommonList<T> data);
}
