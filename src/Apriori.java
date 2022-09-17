import java.util.*;

public class Apriori {

    private final int minSup;
    private final List<Set<String>> dataSet = new ArrayList<>();
    private List<List<Set<String>>> largeSetList = new ArrayList<>();

    /**
     * Apriori class를 초기화한다.
     * 한번 생성된 이후에 <code>minSup</code>는 변경할 수 없다.
     * minSup 미만인 항목은 제거된다.
     *
     * @author gimangi
     * @param minSup min support
     */
    public Apriori(int minSup) {
        this.minSup = minSup;
    }

    public void putColumn(List<String> col) {
        Set cSet = new HashSet();
        this.dataSet.add(cSet);
        for (String item : col) {
            cSet.add(item);
        }
    }

    /**
     * 주어진 data set 으로부터 Apriori-Gen 반복.
     * 연관관계를 얻기 위한 전처리 과정.
     * @see Apriori#getFirstLargeSet()
     * @see Apriori#getNextLargeSet()
     *
     */
    public void init() {
        largeSetList.add(getFirstLargeSet());

        List<Set<String>> cSet;
        while (!(cSet = getNextLargeSet()).isEmpty())
            largeSetList.add(cSet);

    }

    /**
     *
     * @return 입력된 아이템으로부터 추출된 1번째 빈발 아이템 집합.
     */
    private List<Set<String>> getFirstLargeSet() {
        HashMap<String, Integer> candidates = new HashMap<>();
        for (Set<String> col : this.dataSet) {
            for (String item : col) {
                Integer cur = candidates.get(item);
                cur = cur == null ? 0 : cur;
                candidates.put(item, ++cur);
            }
        }
        List<String> delete = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
            if (entry.getValue() < minSup)
                delete.add(entry.getKey());
        }
        for (String item : delete)
            candidates.remove(item);

        List<Set<String>> ret = new ArrayList<>();
        for (String item : candidates.keySet()) {
            Set set = new HashSet<>();
            set.add(item);
            ret.add(set);
        }

        return ret;
    }

    /**
     *
     * @return i번째 빈발 아이템 집합으로부터 추출된 i+1번째 빈발 아이템 집합.
     *
     */
    private List<Set<String>> getNextLargeSet() {
        if (largeSetList.size() == 0) return new ArrayList<>();

        List<Set<String>> cSet = largeSetList.get(largeSetList.size() - 1);
        Set<Set<String>> temp = new HashSet<>();

        for (int i = 0; i < cSet.size(); i++) {
            for (int j = i + 1; j < cSet.size(); j++) {

                Set<String> nCol = new HashSet<>();
                nCol.addAll(cSet.get(i));
                nCol.addAll(cSet.get(j));

                temp.add(nCol);
            }
        }

        List<Set<String>> setList = new ArrayList<>();
        for (Set<String> set : temp) {
            int cnt = 0;
            for (Set<String> dSet : dataSet)
                if (dSet.containsAll(set))
                    cnt++;

            if (cnt >= minSup)
                setList.add(set);
        }

        return setList;
    }

}
