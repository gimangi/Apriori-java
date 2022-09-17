import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextReader {

    private final String fileName;
    private List<List<String>> items = new ArrayList();

    public TextReader(String fileName) throws IOException {
        this.fileName = fileName;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null)
            items.add(List.of(line.split(",")));
        br.close();
    }

    public List<List<String>> getItems() {
        return this.items;
    }
}
