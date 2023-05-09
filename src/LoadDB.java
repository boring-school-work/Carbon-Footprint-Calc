import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileReader;

/**
 * Load the database from the text files.
 */
public class LoadDB {
    public final static String VEHICLE_FILE_PATH = "./data/vehicleData.txt";
    public final static String AUTH_FILE_PATH = "./data/authData.txt";
    public static Map<String, Map<String, ArrayList<String>>> vehicleData = new HashMap<>();
    public static Map<String, String> authData = new HashMap<>();

    public static Map<String, Map<String, ArrayList<String>>> LoadVehicleData() {
        Map<String, Map<String, ArrayList<String>>> vehicleData = new HashMap<>();
        BufferedReader bfReader = null;

        try {
            File file = new File(VEHICLE_FILE_PATH);

            // create new file if it does not exist
            if (!file.exists()) {
                file.createNewFile();
            }

            bfReader = new BufferedReader(new FileReader(file));

            String line = null;

            // read line by line until the end of the file is reached
            while ((line = bfReader.readLine()) != null) {
                String username = line.substring(0, line.indexOf(":"));
                String vehiclesData = line.substring(line.indexOf(":") + 1);

                // get the individual vehicles
                String[] parts = vehiclesData.split("&");

                // insert data into HashMap is content is not empty
                if (!username.isEmpty() && !vehiclesData.isEmpty()) {
                    Map<String, ArrayList<String>> vehicleInfo = new HashMap<>();

                    for (String part : parts) {
                        vehicleInfo.put(part.split(":")[0],
                                new ArrayList<>(Arrays.asList(part.split(":")[1].split(","))));
                    }

                    vehicleData.put(username, vehicleInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // close BufferedReader
                bfReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return vehicleData;
    }

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

            // read line by line until the end of the file is reached
            while ((line = bfReader.readLine()) != null) {
                String username = line.substring(0, line.indexOf(":"));
                String passwordHash = line.substring(line.indexOf(":") + 1);
                authData.put(username, passwordHash);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // close BufferedReader
                bfReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return authData;
    }

    public static void main(String args[]) {
        vehicleData = LoadVehicleData();
        authData = LoadAuthData();
    }
}