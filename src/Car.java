import java.util.HashMap;

/**
 * A class that defines the properties of a car.
 *
 * @author David Saah
 * @version 1.0
 * @since 2023-04-26
 *
 */
public class Car extends Vehicle {
    private static final double PUBLIC_EMISSIONS_FACTOR = 120.0; // grams of CO2 per mile
    private static final HashMap<String, Double> PRIVATE_EMISSIONS_FACTOR = new HashMap<>() {
        {
            put("diesel", 120.0);
            put("gasoline", 400.0);
        }
    }; // grams of CO2 per mile

    /**
     * Car constructor.
     *
     * @param altName  the actual name of the Vehicle
     * @param fuelType the type of fuel used by the car
     * @param isPublic whether the car is public or privately owned
     *
     */
    public Car(String altName, String fuelType, boolean isPublic) {
        super("Car", altName, isPublic);
        this.fuelType = fuelType;
    }

    /**
     * Second Car constructor.
     *
     * @param fuelType the type of fuel used by the car
     * @param isPublic whether the car is publicly or privately owned
     *
     */
    public Car(String fuelType, boolean isPublic) {
        super("Car", isPublic);
        this.fuelType = fuelType;
    }

    /**
     * Gets the emissions factor of the car.
     *
     * @return the emissions factor of the car
     *
     */
    public double getEmissionsFactor() {
        if (this.getType()) {
            return PUBLIC_EMISSIONS_FACTOR;
        } else {
            return PRIVATE_EMISSIONS_FACTOR.get(this.fuelType);
        }
    }
}
