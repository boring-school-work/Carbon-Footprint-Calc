/**
 * Vehicle Abstract Class
 *
 * Defines the general properties of a vehicle
 *
 */
public abstract class Vehicle {
    private String name; // the name of the vehicle
    private boolean isPublic; // whether the is public or privately owned
    private String altName; // the alternative name of the vehicle

    /**
     * Vehicle constructor
     *
     * @param name the name of the vehicle
     *
     */
    public Vehicle(String name) {
        this.name = name;

    }

    /**
     * Sets the type of the vehicle
     *
     * @param isPublic whether the vehicle is public or privately owned
     *
     */
    public void SetType(boolean isPublic) {
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

    public abstract double getEmissionsFactor();

}
