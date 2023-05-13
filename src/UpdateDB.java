import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;

/**
 * Handles database updates
 *
 * @author David Saah
 * @version 1.0
 * @since 2023-04-26
 *
 */
public class UpdateDB {

    /**
     * Update the authentication database
     *
     * @param username the username
     * @param password the password hash
     *
     */
    public static void UpdateAuthData(String username, String password) {
        BufferedWriter bfWriter = null;

        try {
            File file = new File(LoadDB.AUTH_FILE_PATH);

            // create new BufferedWriter for the output file
            // add true to append the content to the file
            bfWriter = new BufferedWriter(new FileWriter(file, true));

            // append new data
            bfWriter.write(username + ":" + password);

            // add new line
            bfWriter.newLine();

            // flush to database
            bfWriter.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // close BufferedWriter
                bfWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Update vehicle database
     *
     */
    public static void UpdateVehicleData() {
        BufferedWriter bfWriter = null;

        try {
            File file = new File(LoadDB.VEHICLE_FILE_PATH);

            // create new BufferedWriter for the output file
            // add true to append the content to the file
            bfWriter = new BufferedWriter(new FileWriter(file));

            for (Map.Entry<String, Map<String, ArrayList<String>>> data : LoadDB.vehicleData.entrySet()) {
                bfWriter.write(data.getKey() + ":");

                for (Map.Entry<String, ArrayList<String>> vehicle : data.getValue().entrySet()) {
                    bfWriter.write(vehicle.getKey() + ":" + vehicle.getValue().get(0) + ","
                            + vehicle.getValue().get(1) + "&");
                }

                // add new line
                bfWriter.newLine();
            }

            // flush to database
            bfWriter.flush();

        } catch (

        Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // close BufferedWriter
                bfWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
