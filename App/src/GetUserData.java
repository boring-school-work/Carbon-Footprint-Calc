import java.util.Scanner;

/**
 * GetUserData
 */
public class GetUserData {
    public static Person user;

    public static String GetVehicleOption(Scanner getInput) {
        String vehicleOption;
        do {
            System.out.print("Which of these vehicles do you own? (Car, Plane, or Both): ");
            vehicleOption = getInput.nextLine();
            if (vehicleOption.equalsIgnoreCase("car") || vehicleOption.equalsIgnoreCase("plane")
                    || vehicleOption.equalsIgnoreCase("both")) {
                break;
            } else {
                System.out.println("\nInvalid input. Please try again.");
            }
        } while (vehicleOption != null);

        return vehicleOption;
    }

    public static String GetFuelType(Scanner getInput) {
        String fuel;

        do {
            System.out.print("What type of fuel does your car use? (Diesel or Gasoline): ");
            fuel = getInput.nextLine();
            if (fuel.equalsIgnoreCase("diesel") || fuel.equalsIgnoreCase("gasoline")) {
                break;
            } else {
                System.out.println("\nInvalid input. Please try again.");
            }
        } while (fuel != null);

        return fuel;

    }

    public static void main(String[] args) {
        Scanner getInput = new Scanner(System.in); // a scanner object to get user input
        System.out.println("Welcome to Transportation Optimization System\n");

        // get user data
        System.out.print("Enter your name: ");
        user = new Person(getInput.nextLine());

        // add the vehicle to the user's list of vehicles
        switch (GetVehicleOption(getInput).toLowerCase()) {
            case "car":
                user.addVehicle(new Car(GetFuelType(getInput)));
                System.out.printf("What is the name of your Car?: ");
                user.getVehiclesOwned().get(0).setAltName(getInput.nextLine());
                break;

            case "plane":
                user.addVehicle(new Plane());
                System.out.printf("What is the name of your Plane?: ");
                user.getVehiclesOwned().get(0).setAltName(getInput.nextLine());
                break;

            case "both":
                user.addVehicle(new Car(GetFuelType(getInput)));
                System.out.printf("What is the name of your Car?: ");
                user.getVehiclesOwned().get(0).setAltName(getInput.nextLine());
                user.addVehicle(new Plane());
                System.out.printf("What is the name of your Plane?: ");
                user.getVehiclesOwned().get(1).setAltName(getInput.nextLine());
                break;
        }

        // close the scanner
        getInput.close();
    }

}