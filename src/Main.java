import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static String fileName;
    private static final String INPUT_DIR = "input";
    public static void main(String[] args) {

        System.out.print("input file name. \n >> ");
        fileName = sc.next();

        try {
            Apriori apriori = new Apriori(2);
            TextReader tr = new TextReader(INPUT_DIR + File.separator + fileName);
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
