
    import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

    public class WordCount {
        public static void main(String[] args) {
            if (args.length == 0) {
                System.out.println("Usage: java WordCount file1.txt file2.txt ...");
                return;
            }

            for (String filename : args) {
                Thread thread = new Thread(() -> {
                    try {
                        int wordCount = countWords(filename);
                        System.out.println(filename + ": " + wordCount);
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + filename);
                    }
                });

                thread.start();
            }
        }

        private static int countWords(String filename) throws IOException {
            int wordCount = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split("\\s+");
                    wordCount += words.length;
                }
            }
            return wordCount;
        }


}
