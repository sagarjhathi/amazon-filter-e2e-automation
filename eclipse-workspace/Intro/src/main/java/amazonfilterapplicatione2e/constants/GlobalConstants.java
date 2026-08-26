package main.java.amazonfilterapplicatione2e.constants;

import main.java.amazonfilterapplicatione2e.configManager.ConfigManager;

public class GlobalConstants {

	public static void main(String args[]) {
		System.out.println(ConfigManager.get("browser"));
	}
}
