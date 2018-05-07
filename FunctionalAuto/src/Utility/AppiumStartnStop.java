package Utility;

import java.io.IOException;

public class AppiumStartnStop {
    Process p;
    // Set path of your node.exe file.
    // Progra~1 represents Program Files folder.
    String nodePath = "C:/Program Files (x86)/Appium/node.exe";
    // Set path of your appium.js file.
    String appiumJSPath = "C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js";
    String cmd = nodePath + " " + appiumJSPath;

    // This method Is responsible for starting appium server.
    public void appiumStart() throws IOException, InterruptedException {
	// Execute command string to start appium server.
	p = Runtime.getRuntime().exec(cmd);
	// Provide wait time of 10 mins to start appium server properly.
	// If face any error(Could not start a new session...) then Increase
	// this time to 15 or 20 mins.
	Thread.sleep(10000);
	if (p != null) {
	    System.out.println("Appium server Is started now.");
	}
    }

    // This method Is responsible for stopping appium server.
    public void appiumStop() throws IOException {
	if (p != null) {
	    p.destroy();
	}
	System.out.println("Appium server Is stopped now.");
    }
}
