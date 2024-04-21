package Model;

public class MessagingQueue {
    Node front, rear;
    int size, capacity;

    public MessagingQueue(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        front = null;
        rear = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Message peek() {
        if(isEmpty()) {
            return null;
        }
        return this.front.message;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean add(Message message) {
        if(isFull()) {
            return false;
        }
        Node node = new Node(message);
        if(front == null) {
            front = node;
        }else {
            rear.next = node;
        }
        rear = node;
        size++;
        return true;
    }

    public Message poll() {
        if(isEmpty()) {
            return null;
        }

        Node frontNode = front;
        if(front == rear) {
            front = null;
            rear = null;
        }else{
            front = front.next;
        }
        return frontNode.message;
    }

    public void setSize(int size) {
        System.out.println("Capacity of Queue is increased");
        this.capacity = size;
    }
}
