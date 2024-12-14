import javax.swing.*;
import java.awt.*;
import java.util.TreeMap;

public class ApplianceGUI {
    private TreeMap<String, Refrigerator> refrigerators; 
    private TreeMap<String, Dishwasher> dishwashers; 
    private TreeMap<String, Microwave> microwaves; 
    private JTextArea fridgeArea;
    private JTextArea dishWasherArea;
    private JTextArea microwaveArea;
    private JTextArea resultsArea;
    public ApplianceGUI(TreeMap<String, Refrigerator> refrigerators, TreeMap<String, Dishwasher> dishwashers,
                        TreeMap<String, Microwave> microwaves) {
        this.refrigerators = refrigerators;
        this.dishwashers = dishwashers;
        this.microwaves = microwaves;
        JFrame frame = new JFrame("Project4 -- Appliance Management with TreeMap");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        fridgeArea = new JTextArea();
        dishWasherArea = new JTextArea();
        microwaveArea = new JTextArea();
        resultsArea = new JTextArea();

        JPanel appliancePanel = new JPanel(new GridLayout(1, 3));
        appliancePanel.add(new JScrollPane(fridgeArea)); 
        appliancePanel.add(new JScrollPane(dishWasherArea)); 
        appliancePanel.add(new JScrollPane(microwaveArea)); 

        JPanel southPanel = new JPanel(new GridLayout(1,1));
        southPanel.add(new JScrollPane(resultsArea));
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem searchMenuItem = new JMenuItem("Search");
        searchMenuItem.addActionListener(e -> searchAppliances());
        fileMenu.add(searchMenuItem);
        JMenuItem quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(quitMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        frame.add(appliancePanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);

        frame.setSize(1600, 400);
        frame.setVisible(true);

        updateDisplay();
    }
    private void updateDisplay(){
        fridgeArea.setText(""); 
        dishWasherArea.setText("");
        microwaveArea.setText("");
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
        resultsArea.append("Search Results: \n");
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
        resultsArea.append("Search Results: \n");
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
