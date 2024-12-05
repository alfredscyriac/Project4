import java.util.TreeMap;
public class Project4 {
    public static void main(String[] args) {
        String filePath = args[0];
        TreeMap<String, Refrigerator> refrigerators = new TreeMap<>(new ApplianceComparator());
        TreeMap<String, Dishwasher> dishwashers = new TreeMap<>(new ApplianceComparator());
        TreeMap<String, Microwave> microwaves = new TreeMap<>(new ApplianceComparator());
        try{
            TextFileInput fileInput = new TextFileInput(filePath);
            String line;
            while ((line = fileInput.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) continue;

                String serial = parts[0];
                double price = Double.parseDouble(parts[1]);
            }
        }
        catch(Exception e){
            System.out.println("Error processing file: " + e.getMessage());
        }
    }
}