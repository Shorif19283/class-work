

    import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

    public class ConcurrentStack<T> {
        private Node<T> top;
        private Lock lock = new ReentrantLock();

        private static class Node<T> {
            T data;
            Node<T> next;

            Node(T data) {
                this.data = data;
            }
        }

        // Push operation - adds an element to the top of the stack
        public void push(T item) {
            Node<T> newNode = new Node<>(item);
            lock.lock();
            try {
                newNode.next = top;
                top = newNode;
            } finally {
                lock.unlock();
            }
        }

        // Pop operation - removes and returns the element from the top of the stack
        public T pop() {
            lock.lock();
            try {
                if (isEmpty()) {
                    throw new IllegalStateException("Stack is empty");
                }
                T item = top.data;
                top = top.next;
                return item;
            } finally {
                lock.unlock();
            }
        }

        // Check if the stack is empty
        public boolean isEmpty() {
            return top == null;
        }
}
