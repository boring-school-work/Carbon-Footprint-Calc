import java.util.HashSet;

/**
 * Person class
 *
 * Represents a person who interacts with the program.
 *
 */
public class Person {
    private String name; // the name of the person
    private HashSet<String> vehiclesOwned = new HashSet<>(); // the vehicles owned by the person

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
    public HashSet<String> getVehiclesOwned() {
        return vehiclesOwned;
    }

    /**
     * Adds a vehicle to the vehicles owned by the person.
     *
     */
    public void addVehicle(String vehicle) {
        vehiclesOwned.add(vehicle);
    }
}

