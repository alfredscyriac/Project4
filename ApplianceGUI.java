import javax.swing.*;
import java.awt.*;
import java.util.TreeMap;
/*
 * Key features of this GUI: 
 * 1) like previous projects there is a Panel with 3 columns that display the sorted appliances 
 * 2) There is a JMenuBar with two JMenuItems: [1] Search, which opens up a 
 * JOptionPane input where user can enter the first character of an appliance type followed by a comma 
 * and a price and with that search field a panel underneath the panel that has all the sorted appliances 
 * will show all appliances that fit the critera meaning they are of the users entered appliance type and 
 * the price is less than or equal to the price the user entered. If no appliances fit the criteria 
 * then a message will shown to alert the user of that and if the user enters in an invalid input
 * there is a regex pattern in Appliance.java that will check it and also throw an error if invalid. 
 * While the window is open the user can you use the search feature as many times as needed. 
 * The second JMenuItem is [2]Quit which closes the window as a whole. I opted for this as it 
 * seemed a bit more advanced that console logging the proper search critera and allowed me to 
 * practice and implement features from previous projects
 */
public class ApplianceGUI {
    private TreeMap<String, Refrigerator> refrigerators; 
    private TreeMap<String, Dishwasher> dishwashers; 
    private TreeMap<String, Microwave> microwaves; 
    private JTextArea fridgeArea;
    private JTextArea dishWasherArea;
    private JTextArea microwaveArea;
    private JTextArea resultsArea;
    /**
     * Constructor to create and initialize the ApplianceGUI
     * @param refrigerators TreeMap containing Refrigerator objects with serial numbers as keys
     * @param dishwashers   TreeMap containing Dishwasher objects with serial numbers as keys
     * @param microwaves    TreeMap containing Microwave objects with serial numbers as keys
     */
    public ApplianceGUI(TreeMap<String, Refrigerator> refrigerators, TreeMap<String, Dishwasher> dishwashers,
                        TreeMap<String, Microwave> microwaves) {
        this.refrigerators = refrigerators;
        this.dishwashers = dishwashers;
        this.microwaves = microwaves;

        // Creates the main frame 
        JFrame frame = new JFrame("Project4 -- Appliance Management with TreeMap");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Intializes all text areas 
        fridgeArea = new JTextArea();
        dishWasherArea = new JTextArea();
        microwaveArea = new JTextArea();
        resultsArea = new JTextArea();

        // Creates the first panel which is split into 3 columns to display the sorted appliances 
        JPanel appliancePanel = new JPanel(new GridLayout(1, 3));
        appliancePanel.add(new JScrollPane(fridgeArea)); 
        appliancePanel.add(new JScrollPane(dishWasherArea)); 
        appliancePanel.add(new JScrollPane(microwaveArea)); 

        // Creates the second panel which will display the results of the users entered search
        JPanel southPanel = new JPanel(new GridLayout(1,1));
        southPanel.add(new JScrollPane(resultsArea));
        
        // Creates a menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        // Add a "Search" menu item to the menu bar. This menu item takes in user input and is 
        // used to filter which appliances to display in the resultsArea 
        JMenuItem searchMenuItem = new JMenuItem("Search");
        searchMenuItem.addActionListener(e -> searchAppliances());
        fileMenu.add(searchMenuItem);

        // Add a "Quit" menu item to the menu bar that simply quits the program 
        JMenuItem quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(quitMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        // Add both panels to the frame 
        frame.add(appliancePanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);

        frame.setSize(1600, 400);
        frame.setVisible(true);

        // Calls a method that populates all the appliance text areas with data 
        updateDisplay();
    }
    /**
     * Updates the display areas with the appliances stored in the TreeMaps by traversing/iterating through
     */
    private void updateDisplay(){
        // Clear all text areas 
        fridgeArea.setText(""); 
        dishWasherArea.setText("");
        microwaveArea.setText("");

        // Use the entrySet and iterator methods that allow us to traverse through a TreeMap until we reach 
        // a null value. At each iteration append the value of the Appliance Object which is 
        // esstentially it's toString method to the proper text area 
        var fridgeIterator = refrigerators.entrySet().iterator();
        while(fridgeIterator.hasNext()){
            var entry = fridgeIterator.next();
            fridgeArea.append(entry.getValue() + "\n");
        }
        var dishwasherIterator = dishwashers.entrySet().iterator();
        while (dishwasherIterator.hasNext()) {
            var entry = dishwasherIterator.next();
            dishWasherArea.append(entry.getValue() + "\n");
        }
        var microwaveIterator = microwaves.entrySet().iterator();
        while (microwaveIterator.hasNext()) {
            var entry = microwaveIterator.next();
            microwaveArea.append(entry.getValue() + "\n");
        }
        // The reason for the excessive \n was that the size of the resultsArea was being determined 
        // before the user enters in a search field, thus just having Search Results made it a very small
        // section and once the data was properply added there, the user would have to drag and expand the
        // window to see the updated data. Although this isn't very dynamic, it serves as a placeholder 
        // of space in the GUI where the resultsArea can be displayed 
        resultsArea.append("Search Results: \n\n\n\n\n\n\n\n\n");
    }
    private void searchAppliances(){
        String input = JOptionPane.showInputDialog(null,"Enter search criteria (format: R/D/M,price):");
        if(input==null||!Appliance.isValidInput(input)){
            JOptionPane.showMessageDialog(null,"Invalid input.");
            return; 
        }
        String[] parts = input.split(",");
        char type = parts[0].charAt(0); 
        int price = Integer.parseInt(parts[1]);
        resultsArea.setText("");
        if(type=='R'){
            var fridgeIterator = refrigerators.values().iterator();
            while (fridgeIterator.hasNext()) {
                var fridge = fridgeIterator.next();
                if (fridge.getPrice() <= price) {
                    resultsArea.append(fridge.toString() + "\n");
                }
            }
        }
        else if(type=='D'){
            var dishwasherIterator = dishwashers.values().iterator();
            while (dishwasherIterator.hasNext()) {
                var dishwasher = dishwasherIterator.next();
                if (dishwasher.getPrice() <= price) {
                    resultsArea.append(dishwasher.toString() + "\n");
                }
            }
        }
        else if(type=='M'){
            var microwaveIterator = microwaves.values().iterator();
            while (microwaveIterator.hasNext()) {
                var microwave = microwaveIterator.next();
                if (microwave.getPrice() <= price) {
                    resultsArea.append(microwave.toString() + "\n");
                }
            }
        }
        if (resultsArea.getText().isEmpty()) {
            resultsArea.setText("No appliances found matching the criteria.");
        }
    }
}
