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

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchAppliances());

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(new JScrollPane(resultsArea), BorderLayout.CENTER);
        southPanel.add(searchButton, BorderLayout.SOUTH);
        
        frame.add(appliancePanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);

        frame.setSize(900, 400);
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
