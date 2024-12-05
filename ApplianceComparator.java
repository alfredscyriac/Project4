import java.util.Comparator;

public class ApplianceComparator implements Comparator<String> {
    @Override
    public int compare(String serial1, String serial2) {
        return serial1.compareTo(serial2); 
    }
}