import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    private static final String INPUT_DIR = "input";
    public static void main(String[] args) {
        try {
            Apriori apriori = new Apriori(2);
            TextReader tr = new TextReader(INPUT_DIR + File.separator + "test1.txt");
            for (List<String> col : tr.getItems()) {
                apriori.putColumn(col);
            }

            apriori.run();
            apriori.print();
        } catch (IOException e) {
            System.out.println("Can not read file.");
        }

    }
}
