
    import java.util.LinkedList;

    public class LinkedListCorruptionDemo {
        private static final int NUM_THREADS = 5;
        private static final int OPERATIONS_PER_THREAD = 1000;

        private static LinkedList<Integer> linkedList = new LinkedList<>();

        public static void main(String[] args) throws InterruptedException {
            Thread[] threads = new Thread[NUM_THREADS];

            for (int i = 0; i < NUM_THREADS; i++) {
                threads[i] = new Thread(() -> {
                    for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                        // Adding an element to the list
                        linkedList.add(j);

                        // Removing an element from the list (if it's not empty)
                        if (!linkedList.isEmpty()) {
                            linkedList.removeFirst();
                        }
                    }
                });
                threads[i].start();
            }

            for (int i = 0; i < NUM_THREADS; i++) {
                threads[i].join();
            }

            // Check the final size of the list
            System.out.println("Final size of the list: " + linkedList.size());
        }
}
