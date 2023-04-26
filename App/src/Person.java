import java.util.ArrayList;

/**
 * Person class
 *
 * Represents a person who interacts with the program.
 *
 */
public class Person {
    private String name; // the name of the person
    private ArrayList<Vehicle> vehiclesOwned = new ArrayList<>(); // the vehicles owned by the person

    /**
     * Person constructor
     *
     * Sets the name of the person.
     *
     */
    public Person(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the person.
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the vehicles owned by the person.
     *
     */
    public ArrayList<Vehicle> getVehiclesOwned() {
        return vehiclesOwned;
    }

    /**
     * Adds a vehicle to the vehicles owned by the person.
     *
     */
    public void addVehicle(Vehicle vehicle) {
        vehiclesOwned.add(vehicle);
    }
}

