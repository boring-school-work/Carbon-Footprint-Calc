import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * GetUserData
 */
public class GetUserData {
    /**
     * Get the vehicle option from the user
     *
     * @param getInput the scanner object
     *
     * @return the vehicle option
     *
     */
    private static String GetVehicleOption(Scanner getInput) {
        String vehicleOption;

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
     * Get the fuel type from the user
     *
     * @param getInput the scanner object
     *
     * @return the fuel type
     *
     */
    private static String GetFuelType(Scanner getInput) {
        String fuel;

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
     * Adds vehicles to the user's list of vehicles
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

                LoadDB.vehicleData.put(user.getName(), new HashMap<String, ArrayList<String>>() {
                    {
                        put(vehicle1, new ArrayList<String>() {
                            {
                                add("Car");
                                add(fuelType1);
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
                                add("Plane");
                                add("kerosene");
                            }
                        });
                    }
                });
                break;
        }
    }

    public static void AddNewVehicle(Person user, Scanner getInput) {
        switch (GetVehicleOption(getInput).toLowerCase()) {
            case "car":
                System.out.print("What is the name of your Car?: ");
                String vehicle1 = getInput.nextLine();
                String fuelType1 = GetFuelType(getInput);
                user.addVehicle(new Car(vehicle1, fuelType1, false));

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