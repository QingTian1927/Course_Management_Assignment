package coursemanager.model;

public class Node<T> {
    public T data;
    protected Node<T> next;

    public Node() {}

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public Node(T data) {
        this(data, null);
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }

    public String toDataString() {
        return data + ", " + next + "\n";
    }
}
