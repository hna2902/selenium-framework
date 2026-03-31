package framework.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonReader {
    public static List<UserData> readUsers(String relativePath) throws IOException {
        String fullPath = System.getProperty("user.dir") + File.separator + relativePath;
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(fullPath), new TypeReference<List<UserData>>() {});
    }
}