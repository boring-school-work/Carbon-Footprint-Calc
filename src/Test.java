import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Test
 */
public class Test {

    public static void main(String[] args) {
        String data = "John:Car:Ford,diesel&Plane:Jet leg,fuel&";
        String username = data.substring(0, data.indexOf(":")).toLowerCase();
        System.out.println("username: " + username);
        String vehiclesData = data.substring(data.indexOf(":") + 1).toLowerCase();

        String[] parts = vehiclesData.split("&");

        Map<String, ArrayList<String>> vehicleInfo = new HashMap<>();

        for (String part : parts) {
            vehicleInfo.put(part.split(":")[0],
                    new ArrayList<>(Arrays.asList(part.split(":")[1].split(","))));
        }

        for (String key : vehicleInfo.keySet()) {
            System.out.println(key + ": " + vehicleInfo.get(key));
        }
    }
}