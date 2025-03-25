package Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtils {
    public static String getJsonValue(String key) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(new File("credentials.json"));
            return jsonNode.get(key).asText();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
