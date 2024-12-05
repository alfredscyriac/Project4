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
                if (Appliance.isValid(serial)) {
                    if (serial.charAt(0) == 'R') {
                        int cubicFeet = Integer.parseInt(parts[2]);
                        refrigerators.put(serial, new Refrigerator(serial, price, cubicFeet));
                    } else if (serial.charAt(0) == 'M') {
                        int watts = Integer.parseInt(parts[2]);
                        microwaves.put(serial, new Microwave(serial, price, watts));
                    } else if (serial.charAt(0) == 'D') {
                        boolean undercounterInstallation = parts[2].equalsIgnoreCase("Y");
                        dishwashers.put(serial, new Dishwasher(serial, price, undercounterInstallation));
                    }
                }
            }
            fileInput.close();
        }
        catch(Exception e){
            System.out.println("Error processing file: " + e.getMessage());
        }
        new ApplianceGUI(refrigerators, dishwashers, microwaves);
    }
}