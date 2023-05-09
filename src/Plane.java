/**
 * A class that defines the properties of a plane.
 *
 */
public class Plane extends Vehicle {
    private static final double PUBLIC_EMISSIONS_FACTOR = 50.0; // grams of CO2 per mile
    private static final double PRIVATE_EMISSIONS_FACTOR = 50.0; // grams of CO2 per mile

    /**
     * Plane constructor
     *
     * @param altName  the alternative name of the vehicle
     * @param isPublic whether the plane is public or privately owned
     *
     */
    public Plane(String altName, boolean isPublic) {
        super("Plane", altName, isPublic);
        this.fuelType = "kerosene";
    }

    /**
     * Second plane constructor
     *
     * @param isPublic whether the plane is public or privately owned
     *
     */
    public Plane(boolean isPublic) {
        super("Plane", isPublic);
        this.fuelType = "jet fuel";
    }

    /**
     * Gets the emissions factor of the plane.
     *
     */
    public double getEmissionsFactor() {
        if (this.getType()) {
            return PUBLIC_EMISSIONS_FACTOR;
        } else {
            return PRIVATE_EMISSIONS_FACTOR;
        }
    }
}
