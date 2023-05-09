import java.util.HashMap;

/**
 * Person class
 *
 * Represents the user who interacts with the program.
 *
 * @author David Saah
 * @version 1.0
 * @since 2023-04-26
 *
 */
public class Person {
    private String name; // the name of the person
    /*
     * The vehicles owned by the person
     * HashMap<Actual name, vehicle object>
     *
     */
    private HashMap<String, Vehicle> vehiclesOwned = new HashMap<>();

    /**
     * Person constructor
     *
     * Sets the name of the person.
     *
     * @param name the name of the person
     *
     */
    public Person(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the person.
     *
     * @return the name of the person
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the vehicles owned by the person.
     *
     * @return the vehicles owned by the person
     *
     */
    public HashMap<String, Vehicle> getVehiclesOwned() {
        return vehiclesOwned;
    }

    /**
     * Adds a new vehicle to the vehicles owned by the person.
     *
     * @param vehicle a new vehicle
     *
     */
    public void addVehicle(Vehicle vehicle) {
        vehiclesOwned.put(vehicle.getAltName(), vehicle);
    }
}
