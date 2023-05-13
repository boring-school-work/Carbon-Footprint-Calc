import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class for the Transportation Optimization System.
 * 
 * @author David Saah
 * @version 1.0
 * @since 2023-04-26
 * 
 */
public class App {

    // declare field variables
    private static double publicTransportEmissions; // the emissions from public transport
    private static double privateTransportEmissions; // the emissions from private transport
    private static double distance; // the distance to travel
    private static final Car PUBLIC_CAR = new Car("diesel", true); // a public car
    private static final Plane PUBLIC_PLANE = new Plane(true); // a public plane
    private static boolean successState = false; // the success state of an operation
    private static Person user; // the user object

    /**
     * Displays the results of transportation choice.
     *
     * @param user the user object
     *
     */
    private static void displayResults(Person user) {
        System.out.println("\n");

        // display the emissions information
        System.out.printf("%38s\n", "Emission Information");
        System.out.println("-".repeat(60));
        System.out.printf("Distance to travel: %.2f miles\n", distance);
        System.out.printf("Public Transport Emissions: %.2f grams of CO2\n", publicTransportEmissions);
        System.out.printf("Private Transport Emissions: %.2f grams of CO2\n", privateTransportEmissions);

        // make a decision to choose between public and private transport
        if (publicTransportEmissions < privateTransportEmissions) {
            System.out.println("\nDecision: You should use public transport.");
        } else {
            System.out.println("\nDecision: You should use private transport.");
        }

    }

    /**
     * Display the user's vehicles info
     *
     * @param user the user object
     *
     */
    public static void displayVehiclesInfo(Person user) {
        System.out.printf("\n\n%38s\n", user.getName() + "'s " + "Vehicles");
        System.out.printf("%-25s %-15s %-15s\n", "Vehicle name (type)", "Fuel Type", "Emissions Factor");
        System.out.println("-".repeat(60)); // add a line

        for (Map.Entry<String, Vehicle> vehicle : user.getVehiclesOwned().entrySet()) {
            System.out.printf("%-25s %-15s %-15s\n",
                    vehicle.getValue().getAltName() + " (" + vehicle.getValue().getName() + ")",
                    vehicle.getValue().getFuelType(), vehicle.getValue().getEmissionsFactor());
        }
    }

    /**
     * Calculate the transportation emissions
     *
     * @param vehicle the vehicle the user wants to use
     *
     */
    private static void calculateEmissions(Vehicle vehicle) {
        // emission = distance * emissions factor

        // calculate emissions for public transport
        publicTransportEmissions = (vehicle.getName() == "Car") ? distance * PUBLIC_CAR.getEmissionsFactor()
                : distance * PUBLIC_PLANE.getEmissionsFactor();

        privateTransportEmissions = distance * vehicle.getEmissionsFactor();
    }

    /**
     * Sets the distance to travel
     *
     * @param getInput a scanner object to get user input
     *
     */
    public static void setDistance(Scanner getInput) {
        String temp; // a temporary variable to store the user's input

        // get the distance to travel
        do {
            System.out.print("How far do you want to travel? (in miles): ");
            temp = getInput.nextLine();

            // check if the input is valid: has no letters and is not negative
            if (!(temp.matches(".*[a-zA-Z].*") || Double.valueOf(temp) < 0)) {
                break;
            } else {
                System.out.println("\nInvalid input. Please try again.");
            }

        } while (true);

        App.distance = Double.valueOf(temp);
    }

    /**
     * Gives the user the option to choose between public and private transport
     *
     * @param user     the user object
     * @param getInput a scanner object to get user input
     *
     */
    public static void chooseTransport(Person user, Scanner getInput) {
        String vehicleName; // the name of the vehicle the user wants to use

        // set the distance to travel
        setDistance(getInput);

        // check if vehicle name is valid
        do {
            System.out.print("What is the name of the vehicle you want to use?: ");
            vehicleName = getInput.nextLine();
        } while (!user.getVehiclesOwned().containsKey(vehicleName));

        calculateEmissions(user.getVehiclesOwned().get(vehicleName));

        // display the results
        displayResults(user);

        // sleep for 3 secs
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Handles user sign up. It creates a new user object and adds it to the
     * database.
     * 
     * @param getInput a scanner object to get user input
     * 
     * @return whether the user signed up successfully
     * 
     */
    public static boolean signUp(Scanner getInput) {
        String username;
        String password;

        System.out.println("\nCreating a new account ...");

        // get the username
        do {
            System.out.print("Enter a username: ");
            username = getInput.nextLine();

            // check if the username already exists
            if (LoadDB.authData.containsKey(username)) {
                System.out.println("Username already exists, please try again.\n");
            } else {
                successState = true;
            }
        } while (!successState);

        user = new Person(username);

        // get the password
        System.out.print("Enter a password: ");
        password = Authenticate.hash(getInput.nextLine().toCharArray());

        // Get user's vehicles
        GetUserData.AddVehicle(user, getInput);

        System.out.println("Successfully signed up.");

        // add the user's data to auth database
        UpdateDB.UpdateAuthData(username, password);

        return true;
    }

    /**
     * Handles user login. It loads the user's data from the database.
     * 
     * @param getInput a scanner object to get user input
     * 
     * @return whether the user logged in successfully
     * 
     */
    public static boolean logIn(Scanner getInput) {
        System.out.println("\nLogging in ...");
        System.out.print("Enter your username: ");
        String username = getInput.nextLine();
        String password, token;

        // check if the username exists
        if (LoadDB.authData.containsKey(username)) {
            token = LoadDB.authData.get(username);
            System.out.print("Enter your password: ");
            password = getInput.nextLine();

            // check if the password is correct
            if (Authenticate.authenticate(password.toCharArray(), token)) {
                System.out.println("\nSuccessfully logged in.");

                // set user
                user = new Person(username);

            } else {
                System.out.println("\n[!] Login error: Incorrect password.\n");
                return false;
            }
        } else {
            System.err.println("\n[!] Login error: Username does not exist.\n");
            return false;
        }

        // add vehicles to the user's list of vehicles
        for (Map.Entry<String, ArrayList<String>> data : LoadDB.vehicleData.get(username).entrySet()) {
            if (data.getValue().get(0).equals("Car")) {
                user.addVehicle(new Car(data.getKey(), data.getValue().get(1), false));
            } else {
                user.addVehicle(new Plane(data.getKey(), false));
            }
        }

        return true;

    }

    /**
     * Handles user authentication choice
     * 
     * @param option   the user's choice
     * @param getInput a scanner object to get user input
     * 
     * @return whether the user signed up or logged in successfully
     * 
     */
    public static boolean auth(String option, Scanner getInput) {
        if (option.equals("1")) {
            return signUp(getInput);
        } else {
            return logIn(getInput);
        }
    }

    /**
     * The main method
     * 
     * @param args
     * 
     */
    public static void main(String[] args) {
        LoadDB.main(); // load the database

        System.out.printf("%38s\n\n", "Welcome to Transportation Optimization System");
        Scanner getInput = new Scanner(System.in); // a scanner object to get user input
        String option = "";

        // show auth menu
        do {
            System.out.println("Selection an option");
            System.out.println("1. Sign up");
            System.out.println("2. Log in");
            System.out.println("3. Exit");

            System.out.print("\nEnter your option: ");
            option = getInput.nextLine();

            if (option.equals("3")) {
                System.out.println("Exiting...");
                System.exit(0);
            } else if (!(option.equals("1") || option.equals("2"))) {
                System.out.println("\nInvalid option: Try again.\n");
            } else if (auth(option, getInput)) {
                break;
            }

        } while (true);

        successState = false; // reset the success state
        option = ""; // reset the option

        // show main menu
        while (true) {
            System.out.println();

            System.out.println("Select an option");
            System.out.println("1. Check Transportation Emissions");
            System.out.println("2. Add a Vehicle");
            System.out.println("3. Display Vehicles Info");
            System.out.println("4. Exit");

            System.out.print("\nEnter your option: ");
            option = getInput.nextLine();

            if (option.equals("1")) {
                displayVehiclesInfo(user);
                System.out.println();
                chooseTransport(user, getInput);
            } else if (option.equals("2")) {
                GetUserData.AddNewVehicle(user, getInput);
            } else if (option.equals("3")) {
                displayVehiclesInfo(user);
            } else if (option.equals("4")) {
                System.out.println("Exiting...");
                break;
            }
        }

        getInput.close(); // close the scanner

        // update the vehicle database
        UpdateDB.UpdateVehicleData();

    }
}
