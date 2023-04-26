/**
 * A class that defines the properties of a plane.
 *
 */
public class Plane extends Vehicle {
    // QUESTION: Is the 50 the actual value?
    public final double EMISSIONS_FACTOR = 50.0; // grams of CO2 per mile

    /**
     * Plane constructor
     *
     * sets the name of the vehicle to "Plane"
     *
     */
    public Plane() {
        super("Plane");
    }
}
