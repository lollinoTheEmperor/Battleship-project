import java.util.Comparator;

public class winsComparator implements Comparator<String[]> {

    @Override
    public int compare(String[] o1, String[] o2) {
        Integer a=Integer.parseInt(o1[1]), b=Integer.parseInt(o2[1]);
        return b.compareTo(a);
    }
}