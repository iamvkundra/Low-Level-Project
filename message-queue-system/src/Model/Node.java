package Model;

public class Node {
    Message message;
    Node next;
    public Node(Message message) {
        this.message = message;
        this.next = null;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
