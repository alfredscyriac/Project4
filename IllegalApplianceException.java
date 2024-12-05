/*
 * Custom arguement exception specifically for the appliance class 
 */
public class IllegalApplianceException extends IllegalArgumentException {
    /*
     * @param message logs to the console/terminal exactly what exception it is..
     * this could be an invalid serial number or invalid selected file
     */
    public IllegalApplianceException(String message) {
        super(message);
    }
}
