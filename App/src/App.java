import java.util.Scanner;

/**
 * App
 */
public class App {
    public static void main(String[] args) {
        Scanner getInput = new Scanner(System.in); // a scanner object to get user input
        double distance; // the distance to travel
        double publicTransportEmissions; // the emissions from public transport
        double privateTransportEmissions; // the emissions from private transport
        Person user = GetUserData.user; // the user

        // get the user's data
        GetUserData.main(args);

        // get the distance to travel
        System.out.print("How far do you want to travel? (in miles): ");
        distance = getInput.nextDouble();

        // print the results
        System.out.println("\nResults:");
        System.out.printf("Distance: %.2f miles\n", distance);

        if (user.getVehiclesOwned().size() == 1) {
            if (user.getVehiclesOwned().get(0).getName() == "Car") {
                publicTransportEmissions = distance * user.getVehiclesOwned().get(0).getEmissionsFactor();
            } else {
                
            }
        }

        getInput.close();
    }
}
