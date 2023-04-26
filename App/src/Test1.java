import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileReader;

/**
 * Test1
 */
public class Test1 {
    public final static String FILE_PATH = "./test.txt";

    public static Map<String, Map<String, Double>> GetUsersData() {
        Map<String, Map<String, Double>> usersData = new HashMap<>();
        BufferedReader bfReader = null;

        try {
            File file = new File(FILE_PATH);

            bfReader = new BufferedReader(new FileReader(file));
            String line = null;

            // read line by line until the end of the file is reached
            while ((line = bfReader.readLine()) != null) {
                String username = line.substring(0, line.indexOf(":"));
                String vehicleData = line.substring(line.indexOf(":") + 1);

                String[] parts = vehicleData.split(",");

                // insert data into HashMap is content is not empty
                if (!username.equals("") && !vehicleData.equals("")) {
                    Map<String, Double> vehicleInfo = new HashMap<>();

                    for (String part : parts) {
                        vehicleInfo.put(part.split(":")[0], Double.parseDouble(part.split(":")[1]));
                    }

                    usersData.put(username, vehicleInfo);
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

        return usersData;
    }

    public static void main(String[] args) {
        Map<String, Map<String, Double>> data = GetUsersData();

        for (Map.Entry<String, Map<String, Double>> keyEntry : data.entrySet()) {
            // print the username
            System.out.print(keyEntry.getKey() + " -> ");

            // write the user data
            for (Map.Entry<String, Double> valEntry : keyEntry.getValue().entrySet()) {
                System.out.print(valEntry.getKey() + " : " + valEntry.getValue() + ", ");
            }

            System.out.println();
        }

        System.out.println(data.get("David")); // returns null if key does not exist
    }
}
