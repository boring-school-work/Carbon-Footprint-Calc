import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Test
 */
public class Test {

    public static Map<String, Map<String, ArrayList<String>>> vehicleData = new HashMap<>();

    public static void main(String[] args) {
        vehicleData.put("key", new HashMap<String, ArrayList<String>>() {
            {
                put("key1", new ArrayList<String>() {
                    {
                        add("v1");
                        add("v2");
                    }
                });
                put("key2", new ArrayList<String>() {
                    {
                        add("v3");
                        add("v4");
                    }
                });
            }
        });

        vehicleData.get("key").put("key3", new ArrayList<String>() {
            {
                add("v1");
                add("v2");
            }
        });

        vehicleData.get("key").put("key4", new ArrayList<String>() {
            {
                add("v3");
                add("v4");
            }
        });

        for (Map.Entry<String, Map<String, ArrayList<String>>> entry : vehicleData.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}