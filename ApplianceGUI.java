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
    
}
