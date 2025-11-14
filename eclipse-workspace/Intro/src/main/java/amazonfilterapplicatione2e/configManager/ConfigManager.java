package main.java.amazonfilterapplicatione2e.configManager;
import java.io.FileInputStream; 
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;

public final class ConfigManager {
	 private static  final Logger log = LogManager.getLogger(ConfigManager.class);
	 
    private static final Properties props = new Properties();
    private static final String RESOURCE = "UtilData.properties";
    private static ConfigManager instance;

    static {
        // 1) External file override (useful for CI): -Dconfig.file=/path/UtilData.properties
        String external = System.getProperty("UtilData.file");
        if (external != null && !external.isBlank()) {
            try (FileInputStream fis = new FileInputStream(external)) {
                props.load(fis);
                System.out.println("Loaded config from external file: " + external);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load external config: " + external, e);
            }
        } else {
            // 2) Try classpath resource (src/test/resources/UtilData.properties)
            try (InputStream in = ConfigManager.class.getClassLoader().getResourceAsStream(RESOURCE)) {
                if (in != null) {
                    props.load(in);
                    System.out.println("Loaded config from classpath: " + RESOURCE);
                } else {
                    System.out.println("Config not found on classpath: " + RESOURCE);
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to load config from classpath: " + RESOURCE, e);
            }
        }
    }

    public ConfigManager() { /* utility class */ }

    /** Simple get: system property -> env var -> properties file */
    public static String get(String key) {
        String sys = System.getProperty(key);
        if (sys != null) return sys;
        String env = System.getenv(key.toUpperCase().replace('.', '_'));
        if (env != null) return env;
        return props.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        String v = get(key);
        return v != null ? v : defaultValue;
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String v = get(key);
        return v == null || v.isBlank() ? defaultValue : Boolean.parseBoolean(v.trim());
    }

    public static int getInt(String key, int defaultValue) {
        String v = get(key);
        if (v == null || v.isBlank()) return defaultValue;
        try { return Integer.parseInt(v.trim()); } catch (NumberFormatException e) { return defaultValue; }
    }
    
    
    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }
    
    
    
    private String resolveValue(String key) {
        String val = props.getProperty(key);
        if (val != null && !val.isEmpty()) {
            log.debug("Resolved key '{}' = '{}'", key, val);
            return val;
        } else {
            log.debug("Key '{}' not found in properties file.", key);
            return null;
        }
    }

    // --- Public getters ---

    public String getString(String key) {
        return resolveValue(key);
    }

    public String getString(String key, String defaultValue) {
        String v = resolveValue(key);
        if (v == null) {
            log.debug("Key '{}' missing, using default '{}'", key, defaultValue);
        }
        return v != null ? v : defaultValue;
    }
    
    
 
}


