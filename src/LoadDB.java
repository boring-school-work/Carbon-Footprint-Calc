import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileReader;

/**
 * Loads the database from the text files into variables
 *
 * @author David Saah
 * @version 1.0
 * @since 2023-04-26
 *
 */
public class LoadDB {
    public final static String DATA_DIR = "./.data/"; // directory to store program data
    public final static String VEHICLE_FILE_PATH = DATA_DIR + "vehicleData.txt"; // location for vehicle data
    public final static String AUTH_FILE_PATH = DATA_DIR + "authData.txt"; // location for authentication data

    /*
     * vehicleData
     *
     * Map<username, Map<actual name of vehicle, ArrayList<>() {
     * add(name of vehicle);
     * add(fuel type of vehicle);
     * }>>
     *
     */
    public static Map<String, Map<String, ArrayList<String>>> vehicleData = new HashMap<>();

    /*
     * authData
     *
     * Map<username, password hash>
     *
     */
    public static Map<String, String> authData = new HashMap<>();

    /**
     * Loads the vehicle data
     *
     * @return the vehicle data
     *
     */
    public static Map<String, Map<String, ArrayList<String>>> LoadVehicleData() {
        Map<String, Map<String, ArrayList<String>>> vehicleData = new HashMap<>(); // variable to store vehicle data
        BufferedReader bfReader = null;

        try {
            File file = new File(VEHICLE_FILE_PATH);

            // create a new file if it does not exist
            if (!file.exists()) {
                file.createNewFile();
            }

            bfReader = new BufferedReader(new FileReader(file));

            String line = null;

            // read the file line by line until the end of the file is reached
            while ((line = bfReader.readLine()) != null) {
                // the username is the string before the first occurence of ":"
                String username = line.substring(0, line.indexOf(":"));

                // the rest of the data are vehicle information
                String vehiclesData = line.substring(line.indexOf(":") + 1);

                // store the individual vehicles in an array
                String[] parts = vehiclesData.split("&");

                // insert data into HashMap if content is not empty
                if (!username.isEmpty() && !vehiclesData.isEmpty()) {
                    Map<String, ArrayList<String>> vehicleInfo = new HashMap<>(); // store vehicle's info

                    /*
                     * NOTE: vehicleInfo =
                     * Map<actual name of vehicle, ArrayList<>() {
                     * add(name of vehicle);
                     * add(fuel type of vehicle);
                     * }>>
                     *
                     */

                    for (String part : parts) {
                        vehicleInfo.put(part.split(":")[0],
                                new ArrayList<>(Arrays.asList(part.split(":")[1].split(","))));
                    }

                    vehicleData.put(username, vehicleInfo); // store individual records into the main record
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bfReader.close(); // close BufferedReader
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return vehicleData;
    }

    /**
     * Loads the Authentication data
     *
     * @return authentication data
     *
     */
    public static Map<String, String> LoadAuthData() {
        Map<String, String> authData = new HashMap<>();
        BufferedReader bfReader = null;

        try {
            File file = new File(AUTH_FILE_PATH);

            // create new file if it does not exist
            if (!file.exists()) {
                file.createNewFile();
            }

            bfReader = new BufferedReader(new FileReader(file));

            String line = null;

            // read the file line by line until the end of the file is reached
            while ((line = bfReader.readLine()) != null) {
                String username = line.substring(0, line.indexOf(":"));
                String passwordHash = line.substring(line.indexOf(":") + 1);
                authData.put(username, passwordHash);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bfReader.close(); // close BufferedReader
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return authData;
    }

    /**
     * Load Vehicle and authData
     *
     */
    public static void main() {
        File dir = new File(DATA_DIR);

        // create the data directory if it does not exist
        if (!dir.exists()) {
            dir.mkdir();
        }

        vehicleData = LoadVehicleData();
        authData = LoadAuthData();
    }
}
