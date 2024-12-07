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
    public ApplianceGUI(TreeMap<String, Refrigerator> refrigerators,
                        TreeMap<String, Dishwasher> dishwashers,
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

        JPanel appliancePanel = new JPanel(new GridLayout(1, 3));
        appliancePanel.add(new JScrollPane(fridgeArea)); 
        appliancePanel.add(new JScrollPane(dishWasherArea)); 
        appliancePanel.add(new JScrollPane(microwaveArea)); 

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchAppliances());


        frame.add(appliancePanel, BorderLayout.CENTER);
        frame.add(searchButton, BorderLayout.SOUTH);

        frame.setSize(900, 400);
        frame.setVisible(true);

        updateDisplay();
    }
    private void updateDisplay(){
        fridgeArea.setText(""); 
        dishWasherArea.setText("");
        microwaveArea.setText("");
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

        var fridgeIterator = refrigerators.entrySet().iterator();
        while(fridgeIterator.hasNext()){
            var entry = fridgeIterator.next();
            fridgeArea.append("Key: " + entry.getKey() + " - " + entry.getValue().toString() + "\n");
        }

    }
    
}
