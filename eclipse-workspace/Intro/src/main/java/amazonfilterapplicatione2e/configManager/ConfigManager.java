package main.java.amazonfilterapplicatione2e.configManager;
import java.io.FileInputStream; 
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;

public final class ConfigManager {
	
	
	// using the priority-based configuration resolution here
//	    private static final Logger log = LogManager.getLogger(ConfigManager.class);
//	    private static final Properties props = new Properties();
//	    private static final String RESOURCE = "UtilData.properties";
//
//	    static {
//	        try {
//	            String external = System.getProperty("UtilData.file");
//
//	            if (external != null && !external.isBlank()) {
//	                try (FileInputStream fis = new FileInputStream(external)) {
//	                    props.load(fis);
//	                    log.info("Loaded config from external file: {}", external);
//	                }
//	            } else {
//	                try (InputStream in = ConfigManager.class.getClassLoader().getResourceAsStream(RESOURCE)) {
//	                    if (in != null) {
//	                        props.load(in);
//	                        log.info("Loaded config from classpath: {}", RESOURCE);
//	                    } else {
//	                        log.warn("Config not found on classpath: {}", RESOURCE);
//	                    }
//	                }
//	            }
//	        } catch (IOException e) {
//	            throw new RuntimeException("Failed to load config", e);
//	        }
//	    }
	
	
	
	private static final Logger log = LogManager.getLogger(ConfigManager.class);
	private static final Properties props = new Properties();
	private static final String RESOURCE = "UtilData.properties";

	static {
	    loadProperties();
	}

	private static void loadProperties() {
	    try {
	        String external = System.getProperty("UtilData.file");

	        if (external != null && !external.isBlank()) {
	            try (FileInputStream fis = new FileInputStream(external)) {
	                props.load(fis);
	                log.info("Loaded config from external file: {}", external);
	                return;
	            }
	        }

	        try (InputStream in = ConfigManager.class.getClassLoader()
	                .getResourceAsStream(RESOURCE)) {
	            if (in != null) {
	                props.load(in);
	                log.info("Loaded config from classpath: {}", RESOURCE);
	            } else {
	                log.warn("Config not found: {}", RESOURCE);
	            }
	        }

	    } catch (IOException e) {
	        throw new RuntimeException("Failed to load config", e);
	    }
	}

	    private ConfigManager() {} // prevent instantiation

	    /** system property → env → file */
//	    public static String get(String key) {
//	        String sys = System.getProperty(key);
//	        if (sys != null) return sys;
//
//	        String env = System.getenv(key.toUpperCase().replace('.', '_'));
//	        if (env != null) return env;
//
//	        return props.getProperty(key);
//	    }
//
//	    public static String get(String key, String defaultValue) {
//	        String v = get(key);
//	        return v != null ? v : defaultValue;
//	    }
//
//	    public static boolean getBoolean(String key, boolean defaultValue) {
//	        String v = get(key);
//	        return (v == null || v.isBlank()) ? defaultValue : Boolean.parseBoolean(v);
//	    }
//
//	    public static int getInt(String key, int defaultValue) {
//	        String v = get(key);
//	        try { 
//	            return (v == null || v.isBlank()) ? defaultValue : Integer.parseInt(v.trim()); 
//	        } catch (NumberFormatException e) {
//	            return defaultValue;
//	        }
//	    }
//	    
//	    
//	    
//	    public static int getInt(String key) {
//	        String v = get(key);
//	        try { 
//	            return Integer.parseInt(v.trim()); 
//	        } catch (NumberFormatException e) {
//	            return 0;
//	        }
//	    }
	    
	    
	    /** Resolution order: system property → env variable → file */
	    public static String get(String key) {
	        String value = System.getProperty(key);

	        if (value == null || value.isBlank()) {
	            value = System.getenv(key.toUpperCase().replace('.', '_'));
	        }

	        if (value == null || value.isBlank()) {
	            value = props.getProperty(key);
	        }

	        return value;
	    }

	    public static String get(String key, String defaultValue) {
	        String value = get(key);
	        return (value == null || value.isBlank()) ? defaultValue : value;
	    }

	    public static boolean getBoolean(String key, boolean defaultValue) {
	        String value = get(key);
	        return (value == null || value.isBlank())
	                ? defaultValue
	                : Boolean.parseBoolean(value.trim());
	    }

	    public static int getInt(String key, int defaultValue) {
	        String value = get(key);
	        try {
	            return (value == null || value.isBlank())
	                    ? defaultValue
	                    : Integer.parseInt(value.trim());
	        } catch (NumberFormatException e) {
	            return defaultValue;
	        }
	    }

	    public static int getInt(String key) {
	        return getInt(key, 0);
	    }
	    
	
	    
	    
	    
	    
	}

 



