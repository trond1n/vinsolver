package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.*;

public class CarData {
    private static Map<String, Map<String, List<String>>> carData = new HashMap<>();

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = CarData.class.getResourceAsStream("/carData.json");
            carData = mapper.readValue(is, new TypeReference<Map<String, Map<String, List<String>>>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isValid(String brand, String model, String engine) {
        if (!carData.containsKey(brand)) return false;
        Map<String, List<String>> models = carData.get(brand);
        if (!models.containsKey(model)) return false;
        return models.get(model).contains(engine);
    }

    public static Map<String, Map<String, List<String>>> getAll() {
        return carData;
    }
}
