import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Defines methods for getting user's data for the program
 *
 * @author David Saah
 * @version 1.0
 * @since 2023-04-26
 *
 */
public class GetUserData {
    /**
     * Gets the vehicle option from the user; whether car or plane
     *
     * @param getInput the scanner object
     *
     * @return the vehicle option
     *
     */
    private static String GetVehicleOption(Scanner getInput) {
        String vehicleOption;

        // keep prompting the user for input until they enter a valid option
        do {
            System.out.print("Which of these vehicles do you own? (Car or Plane): ");
            vehicleOption = getInput.nextLine();

            // check if the input is valid: is either car, plane, or both
            if (vehicleOption.equalsIgnoreCase("car") || vehicleOption.equalsIgnoreCase("plane")) {
                break;
            } else {
                System.out.println("\nInvalid input. Please try again.");
            }
        } while (true);

        return vehicleOption;
    }

    /**
     * Gets the fuel type from the user
     *
     * @param getInput the scanner object
     *
     * @return the fuel type option
     *
     */
    private static String GetFuelType(Scanner getInput) {
        String fuel;

        // keep prompting the user for input until they enter a valid option
        do {
            System.out.print("What type of fuel does your car use? (Diesel or Gasoline): ");
            fuel = getInput.nextLine();

            // check if the input is valid: is either diesel or gasoline
            if (fuel.equalsIgnoreCase("diesel") || fuel.equalsIgnoreCase("gasoline")) {
                break;
            } else {
                System.out.println("\nInvalid input. Please try again.");
            }
        } while (true);

        return fuel;
    }

    /**
     * Add a new vehicle for user on sign up
     *
     * @param user     the user object
     * @param getInput the scanner object
     *
     */
    public static void AddVehicle(Person user, Scanner getInput) {

        switch (GetVehicleOption(getInput).toLowerCase()) {
            case "car":
                System.out.print("What is the name of your Car?: ");
                String vehicle1 = getInput.nextLine();
                String fuelType1 = GetFuelType(getInput);
                user.addVehicle(new Car(vehicle1, fuelType1, false));

                /*
                 * NOTE: the key for the vehicle should be the altName.
                 * Using the vehicle name will allow duplicate keys to be present in the
                 * HashMap. That makes updating user vehicles troublesome. It overrides, rather
                 * than update. Setting the key to altName mitigates that risk.
                 */

                LoadDB.vehicleData.put(user.getName(), new HashMap<String, ArrayList<String>>() {
                    {
                        put(vehicle1, new ArrayList<String>() {
                            {
                                add("Car"); // the name of the vehicle
                                add(fuelType1); // the fuel type of the vehicle
                            }
                        });
                    }
                });
                break;

            case "plane":
                System.out.print("What is the name of your Plane?: ");
                String vehicle2 = getInput.nextLine();
                user.addVehicle(new Plane(vehicle2, false));

                LoadDB.vehicleData.put(user.getName(), new HashMap<String, ArrayList<String>>() {
                    {
                        put(vehicle2, new ArrayList<String>() {
                            {
                                add("Plane"); // the name of the vehicle
                                add("kerosene"); // the fuel type of the vehicle
                            }
                        });
                    }
                });
                break;
        }
    }

    /*
     * NOTE: Why separate the addition of vehicles into separate methods?
     * The key for the vehicle data is the user's name. The first class
     * (AddVehicle) creates a new key with the user's name and add the data.
     * As a opposed to the second method (AddNewVehicle), which adds new
     * vehicle data by getting the key of the `vehicles data` (the user's name)
     * and updating the values to add the additional vehicles.
     *
     * NOTE: It is essential to separate operational concerns.
     *
     */

    /**
     * Adds subsequent vehicles for the user
     *
     * @param user     the user object
     * @param getInput the scanner object
     *
     */
    public static void AddNewVehicle(Person user, Scanner getInput) {
        switch (GetVehicleOption(getInput).toLowerCase()) {
            case "car":
                System.out.print("What is the name of your Car?: ");
                String vehicle1 = getInput.nextLine();
                String fuelType1 = GetFuelType(getInput);
                user.addVehicle(new Car(vehicle1, fuelType1, false));

                // get the data belonging to the user and make the necessary update
                LoadDB.vehicleData.get(user.getName()).put(vehicle1, new ArrayList<String>() {
                    {
                        add("Car");
                        add(fuelType1);
                    }
                });
                break;

            case "plane":
                System.out.print("What is the name of your Plane?: ");
                String vehicle2 = getInput.nextLine();
                user.addVehicle(new Plane(vehicle2, false));

                // get the data belonging to the user and make the necessary update
                LoadDB.vehicleData.get(user.getName()).put(vehicle2, new ArrayList<String>() {
                    {
                        add("Plane");
                        add("kerosene");
                    }
                });
                break;
        }
    }
}
