import java.util.HashMap;

/**
 * A class that defines the properties of a car.
 *
 */
public class Car extends Vehicle {
    // TODO: What is the emissions factor for public transport?
    // TODO: Most public transportations use diesel fuel. What is the emissions
    // factor for diesel fuel?
    public static final double PUBLIC_EMISSIONS_FACTOR = 120.0; // grams of CO2 per mile

    // TODO: What is the emissions factor for private transport?
    // TODO: It depends on the type of fuel used; whether diesel or gasoline.
    private static final HashMap<String, Double> PRIVATE_EMISSIONS_FACTOR = new HashMap<>() {
        {
            put("diesel", 120.0);
            put("gasoline", 400.0);
        }
    }; // grams of CO2 per mile

    private String fuelType; // the type of fuel used by the car

    /**
     * Car constructor.
     *
     * Sets the name of the vehicle to "Car".
     *
     */
    public Car(String fuelType) {
        super("Car");
        this.fuelType = fuelType;
    }

    /**
     * Gets the type of fuel used by the car.
     *
     */
    public String getFuelType() {
        return this.fuelType;
    }

    /**
     * Gets the emissions factor of the car.
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
