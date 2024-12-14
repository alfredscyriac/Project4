import java.util.Comparator;
public class ApplianceComparator implements Comparator<String> {
    /**
     * Compares two serial numbers to sort their ordering in the TreeMap
     * @param serial1 The first serial number to be compared
     * @param serial2 The second serial number to be compared
     * @return A negative integer if serial1 is less than serial2,
     * a positive integer if serial1 is greater than serial2,
     * or 0 if serial1 and serial2 are equal
     */
    @Override
    public int compare(String serial1, String serial2) {
        return serial1.compareTo(serial2); 
    }
}