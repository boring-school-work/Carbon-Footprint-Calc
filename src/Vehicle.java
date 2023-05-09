/**
 * Vehicle Abstract Class
 *
 * Defines the general properties of a vehicle
 *
 */
public abstract class Vehicle {
    protected String name; // the name of the vehicle
    protected boolean isPublic; // whether the is public or privately owned
    protected String altName; // the alternative name of the vehicle
    protected String fuelType; // the type of fuel used by the car

    /**
     * Vehicle constructor
     *
     * @param name the name of the vehicle
     * @param altName
     * @param isPublic
     *
     */
    public Vehicle(String name, String altName, boolean isPublic) {
        this.name = name;
        this.isPublic = isPublic;
        this.altName = altName;
    }

    /**
     * Second constructor that sets the name and type
     *
     * @param name
     * @param isPublic
     *
     */
    public Vehicle(String name, boolean isPublic) {
        this.name = name;
        this.isPublic = isPublic;
    }

    /**
     * Gets the type of the vehicle
     *
     * @return whether the vehicle is public or privately owned
     *
     */
    public boolean getType() {
        return this.isPublic;

    }

    /**
     * Gets the name of the vehicle
     *
     * @return the name of the vehicle
     *
     */
    public String getName() {
        return this.name;

    }

    /**
     * Sets the alternative name of the vehicle
     *
     * @param altName the alternative name of the vehicle
     *
     */
    public void setAltName(String altName) {
        this.altName = altName;
    }

    /**
     * Gets the alternative name of the vehicle
     *
     */
    public String getAltName() {
        return this.altName;
    }

    /**
     * Gets the emissions factor of the vehicle
     *
     * @return the emissions factor of the vehicle
     *
     */
    public abstract double getEmissionsFactor();

    /**
     * Gets the type of fuel used by the vehicle.
     *
     * @return the type of fuel used by the vehicle
     *
     */
    public String getFuelType() {
        return this.fuelType;
    }
}
