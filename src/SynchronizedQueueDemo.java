

import java.util.LinkedList;
import java.util.Queue;

    public class SynchronizedQueueDemo {
        private static final int MAX_SIZE = 10;
        private static final int NUM_ITERATIONS = 100;

        private static Queue<String> queue = new LinkedList<>();

        public static void main(String[] args) {
            Thread producerThread = new Thread(() -> {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    produce();
                }
            });

            Thread consumerThread = new Thread(() -> {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    consume();
                }
            });

            producerThread.start();
            consumerThread.start();

            try {
                producerThread.join();
                consumerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private synchronized static void produce() {
            while (queue.size() >= MAX_SIZE) {
                try {
                    System.out.println("Producer is waiting. Queue is full.");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String timestamp = new java.util.Date().toString();
            queue.add(timestamp);
            System.out.println("Produced: " + timestamp);
            notify();
        }

        private synchronized static void consume() {
            while (queue.isEmpty()) {
                try {
                    System.out.println("Consumer is waiting. Queue is empty.");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String item = queue.poll();
            System.out.println("Consumed: " + item);
            notify();
        }

}
