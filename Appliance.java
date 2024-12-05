import java.util.regex.Pattern;

public class Appliance {
    /**
     * This is the constructor for the Appliance class and sets the serial number of an appliance
     * The constructor validates the serial number using the isValid method 
     * If the serial number is not valid we throw an exception we created ourselves in..
     * IllegalApplianceException.java
     * If an inValid serial number is found it is printed to the console/terminal
     *
     * @param serialNumber is the serial number of the appliance
     * @throws IllegalApplianceException If the serial number is invalid using the isValid method
     */
    public Appliance(String serialNumber) throws IllegalApplianceException{
        if (!isValid(serialNumber)) {
            throw new IllegalApplianceException("Invalid serial number: " + serialNumber);
        }
        this.serialNumber = serialNumber;
    }
    /**
     * Checks whether a given serial number is valid for an appliance or not
     * A valid serial number must start with 'R', 'D', or 'M', followed by 11 capital letters A-Z or 
     * numerbers 0-9. We check this pattern using a regex pattern for which we had to 
     * import java.util.regex.Pattern
     * @param serialNumber is a string which is the serial number to validate
     * @return true if the serial number is valid, false otherwise
     */
    public static boolean isValid(String serialNumber) {
        return Pattern.matches("^[RDM][A-Z0-9]{11}$", serialNumber);
    }
    
    /**
     * Private instance variable for the serial number that uniquely identifies this appliance
     */
    private String serialNumber; 

    /**
     * @return a string which is the serial number of a given appliance
     */
    public String getSerialNumber () {  
        return serialNumber;
    }

    /**
     * Changes an appliance's existing serial number with a new one 
     * @param serialNumber, the new serial number to be set for an appliance 
     */
    public void setSerialNumber (String serialNumber) {  
        this.serialNumber = serialNumber;
    }

    /**
     * @return the first character of a serial number representing the appliance type
     * R represents refrigerator 
     * M represents microwave 
     * D represents dishwasher 
     */
    public char getApplianceType () {   
        return serialNumber.charAt(0);  
    }
    
    /**
     * @return a string which is serial number 
     * toString is used in Dishwasher.java, Microwave.java, Refrigerator.java, and ApplianceGUI.java
     * @return the serial number as a String
     */
    public String toString() { 
        return serialNumber;
    }

    /**
     * Compares an appliance's serial number with another appliance's serial number.
     * @param other the other Appliance to compare with
     * @return a negative integer, zero, or a positive integer as this serial number is
     * less than, equal to, or greater than the specified appliance's serial number
     */
    public int compareTo (Appliance other) {  
        return this.serialNumber.compareTo(other.serialNumber);
    }

    /**
     * Checks an appliance has the same serial number as another appliance
     * @param other the reference Appliance to compare with
     * @return true if the specified appliance has the same serial number, false otherwise
     */
    public boolean equals (Appliance other) {
        return this.serialNumber.equals(other.serialNumber); 
    }
}