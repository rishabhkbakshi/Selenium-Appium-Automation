package Appium;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Organizer {
    private static AndroidDriver driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
	// get the path of your Path from the file system
	File classpathRoot = new File(System.getProperty("user.dir"));
	File appDir = new File(classpathRoot, "/App/");
	File app = new File(appDir, "simpleorganizer.apk");

	// Set all the DesireCapability for the Appium Automation
	DesiredCapabilities capabilities = new DesiredCapabilities();
	// capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
	capabilities.setCapability("deviceName", "Micromax Q380");
	capabilities.setCapability("platformVersion", "4.4.2");
	capabilities.setCapability("platformName", "Android");
	capabilities.setCapability("app", app.getAbsolutePath());
	capabilities.setCapability("appPackage", "com.cubereflect.simple.organizer");
	// capabilities.setCapability("appActivity", "Launcher");

	Thread.sleep(10000);

	// Launch the App in the Mobile
	driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

	System.out.println("android App Launch");

	// Initialize Simple organizer for creating new event
	driver.findElementByName("Events").click();
	driver.findElementById("com.cubereflect.simple.organizer:id/eventListCreateButton").click();
	driver.findElementById("com.cubereflect.simple.organizer:id/eventCreateTitle").clear();
	driver.findElementById("com.cubereflect.simple.organizer:id/eventCreateTitle").sendKeys("New Event");
	driver.findElementById("com.cubereflect.simple.organizer:id/eventCreateLocation").clear();
	driver.findElementById("com.cubereflect.simple.organizer:id/eventCreateLocation").sendKeys("New Test Location");

	// set event from date
	driver.findElementById("com.cubereflect.simple.organizer:id/eventFromDate").click();
	driver.findElementById("android:id/pickers").click();
	driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Increase month']")).click();
	driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Increase month']")).click();
	driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Increase day']")).click();
	driver.findElementById("android:id/button1").click();

	// set event from time
	driver.findElementById("com.cubereflect.simple.organizer:id/eventFromTime").click();
	driver.findElementById("android:id/timePicker").click();
	driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Increase hour']")).click();
	driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Increase minute']")).click();
	driver.findElementById("android:id/button1").click();

	// set event to date
	driver.findElementById("com.cubereflect.simple.organizer:id/eventToDate").click();
	driver.findElementById("android:id/pickers").click();
	driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Increase month']")).click();
	driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Increase day']")).click();
	driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Increase year']")).click();
	driver.findElementById("android:id/button1").click();

	// set event to time
	driver.findElementById("com.cubereflect.simple.organizer:id/eventFromTime").click();
	driver.findElementById("android:id/timePicker").click();
	driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Increase hour']")).click();
	driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Increase minute']")).click();
	driver.findElementById("android:id/button1").click();

	driver.findElementById("com.cubereflect.simple.organizer:id/eventSaveButton").click();

	driver.quit();

    }
}
