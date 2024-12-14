import java.util.TreeMap;
public class Project4 {
    public static void main(String[] args) {
        /*
         * This program takes in an input file using args[0]
         * p2input.txt from project 2 is attached to the folder thus after compiling: javac Project4.java 
         * to run the program using p2input.txt can be done using the following command line:
         * java Project4 p2input.txt
         */
        String filePath = args[0];
        /*
         * TreeMaps consists of a string which is the serial number and the appliance 
         * I opted for this as it made the ApplianceComparator much easier 
         * Alternate routes for this would be to have the Appliance Object followed by its price
         * Having the price is cruicial for this assignment but I updated Appliance.java 
         * to have setters and getters for double price and passed them in through super to all 
         * classes that extends appliance.java (this includes: Dishwasher, Refrigerator, Microwave)
         */
        TreeMap<String, Refrigerator> refrigerators = new TreeMap<>(new ApplianceComparator());
        TreeMap<String, Dishwasher> dishwashers = new TreeMap<>(new ApplianceComparator());
        TreeMap<String, Microwave> microwaves = new TreeMap<>(new ApplianceComparator());
        /*
         * We read in the input file using TextFileInput per usual but using try catch to catch 
         * any improper input files. We are using the method .put() to add data to the TreeMaps
         */
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
        /*
         * Passing all 3 of the TreeMaps that have data to the GUI 
         */
        new ApplianceGUI(refrigerators, dishwashers, microwaves);
    }
}