/**
 * Vehicle Abstract Class
 *
 * Defines the general properties of a vehicle
 *
 * @author David Saah
 * @version 1.0
 * @since 2023-04-26
 *
 */
public abstract class Vehicle {
    protected String name; // the name of the vehicle
    protected boolean isPublic; // whether the is public or privately owned
    protected String altName; // the actual name of the vehicle
    protected String fuelType; // the type of fuel used by the car

    /**
     * Vehicle constructor
     *
     * @param name     the name of the type of vehicle
     * @param altName  the actual name of the Vehicle
     * @param isPublic whether the vehicle is publicly or privately owned
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
     * @param name     the name of the type of vehicle
     * @param isPublic whether the vehicle is publicly or privately owned
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
     * Gets the name of the type of vehicle
     *
     * @return the name of the type of vehicle
     *
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the actual name of the vehicle
     *
     * @param altName the actual name of the Vehicle
     *
     */
    public void setAltName(String altName) {
        this.altName = altName;
    }

    /**
     * Gets the actual name of the vehicle
     *
     * @return the actual name of the vehicle
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
