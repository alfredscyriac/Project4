/**
 * Dishwasher extends the Appliance.java class as it a specific type of appliance
 * The unique variable to the dishwasher class is whether it needs undercounter installation or not
 */
public class Dishwasher extends Appliance {
    private boolean undercounterInstallation;

    /**
     * Constructor for a dishwasher object
     * @param serialNumber is the serial number of the dishwasher 
     * @param price is the price of the dishwasher 
     * @param undercounterInstallation if Y then it needs undercounter installion and it doesn't if its n
     * All passed in parameters set the values for the private instance 
     * variables while retriving serialNumber from the parent class which is appliance
     */
    public Dishwasher(String serialNumber, double price, boolean undercounterInstallation) {
        super(serialNumber,price);
        this.undercounterInstallation = undercounterInstallation;
    }

    /**
     * Returns a string, this toString comes very useful in our GUI. The string concatenates
     * all parts of the object (serial number, price, under counter installation)
     */
    @Override
    public String toString() {
        return "Serial=" + getSerialNumber() + ". Price=$" + getPrice() + 
               ", Undercounter Installation=" + (undercounterInstallation ? "Yes" : "No") + ".";
    }
}
