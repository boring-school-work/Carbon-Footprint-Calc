import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;

/**
 * Test1
 */
public class Test3 {
    public final static String FILE_PATH = "./test.txt";

    public static void main(String[] args) {
        Map<String, Map<String, Double>> usersData = new HashMap<>();
        Map<String, Double> vehicleInfo = new HashMap<>();

        vehicleInfo.put("Car", 20.0);
        vehicleInfo.put("Plane", 35.0);

        usersData.put("John", vehicleInfo);

        BufferedWriter bfWriter = null;

        try {
            File file = new File(FILE_PATH);

            // create new BufferedWriter for the output file
            bfWriter = new BufferedWriter(new FileWriter(file));

            for (Map.Entry<String, Map<String, Double>> keyEntry : usersData.entrySet()) {

                // put key and value separated by a colon
                bfWriter.write(keyEntry.getKey() + ":");

                for(Map.Entry<String, Double> valEntry : keyEntry.getValue().entrySet()) {
                    bfWriter.write(valEntry.getKey() + ":" + valEntry.getValue() + ",");
                }

                // add new line
                bfWriter.newLine();
            }

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
}
