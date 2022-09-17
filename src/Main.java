import java.util.List;

public class Main {

    public static void main(String[] args) {
        Apriori apriori = new Apriori(2);

        apriori.putColumn(List.of(new String[]{"Bread", "Jelly", "PeanutButter"}));
        apriori.putColumn(List.of(new String[]{"Bread", "PeanutButter"}));
        apriori.putColumn(List.of(new String[]{"Bread", "Milk", "PeanutButter"}));
        apriori.putColumn(List.of(new String[]{"Beer", "Bread"}));
        apriori.putColumn(List.of(new String[]{"Beer", "Milk"}));
        apriori.run();
        apriori.print();
    }
}
