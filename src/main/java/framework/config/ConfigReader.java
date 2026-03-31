package framework.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties props = new Properties();
    private static ConfigReader instance;

    private ConfigReader() {
        String env = System.getProperty("env", "dev");
        String path = "src/test/resources/config-" + env + ".properties";
        String fullPath = System.getProperty("user.dir") + File.separator + path;
        try (FileInputStream fis = new FileInputStream(fullPath)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Loi Config: " + fullPath);
        }
    }

    public static ConfigReader getInstance() {
        if (instance == null) instance = new ConfigReader();
        return instance;
    }

    public String getBaseUrl() { return props.getProperty("base.url"); }
    public int getRetryCount() { return Integer.parseInt(props.getProperty("retry.count", "1")); }

    public String getUsername() {
        String username = System.getenv("APP_USERNAME");
        if (username == null || username.isBlank()) {
            username = props.getProperty("app.username");
        }
        return username;
    }

    public String getPassword() {
        String password = System.getenv("APP_PASSWORD");
        if (password == null || password.isBlank()) {
            password = props.getProperty("app.password");
        }
        return password;
    }
}