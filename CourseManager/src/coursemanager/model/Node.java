package coursemanager.model;

public class Node<T> {
    protected T info;
    protected Node<T> next;

    public Node() {}

    public Node(T info, Node<T> next) {
        this.info = info;
        this.next = next;
    }

    public Node(T info) {
        this(info, null);
    }
}
