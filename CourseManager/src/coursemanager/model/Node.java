package coursemanager.model;

public class Node<T> {
    protected T data;
    protected Node<T> next;

    public Node() {}

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public Node(T data) {
        this(data, null);
    }
}
