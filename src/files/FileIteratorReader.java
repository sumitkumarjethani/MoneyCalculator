package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

public class FileIteratorReader implements Iterable<String>{

    private final BufferedReader reader;

    public FileIteratorReader(BufferedReader reader) {
        this.reader = reader;
    }
    
    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            String current = readLine();
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public String next() {
                String result = current;
                current = readLine();
                return result;
            }

            private String readLine() {
                try {
                    return reader.readLine();
                } catch (IOException ex) {
                    return null;
                }
            }
        };
    }
    
}
