/**
 * Microwaves extends the Appliance.java class as it a specific type of appliance
 * The unique variable to the microwave class is its number of watts 
 */
public class Microwave extends Appliance {
    private int watts;
    private double price;

    /**
     * Constructor for a microwave object
     * @param serialNumber is the serial number of the microwave
     * @param price is the price of the microwave
     * @param watts is the number of watts of the microwave
     * All passed in parameters set the values for the private instance 
     * variables while retriving serialNumber from the parent class which is appliance
     */
    public Microwave(String serialNumber, double price, int watts) {
        super(serialNumber);
        this.price = price;
        this.watts = watts;
    }

    /** 
     * @return a string that concatenates all the components of the microwave class for our GUI
     * (serial number, price, watts)
     */
    @Override
    public String toString() {
        return "Serial=" + getSerialNumber() + ". Price=$" + price + ". Watts=" + watts + ".";
    }
}
