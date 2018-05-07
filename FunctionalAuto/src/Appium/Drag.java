package Appium;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Drag {

    private static AndroidDriver driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

	// get the path of your Path from the file system
	File classpathRoot = new File(System.getProperty("user.dir"));
	File appDir = new File(classpathRoot, "/App/");
	File app = new File(appDir, "Drag-SortDemos_0.5.0.apk");

	// Set all the DesireCapability for the Appium Automation
	DesiredCapabilities capabilities = new DesiredCapabilities();
	// capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
	capabilities.setCapability("deviceName", "Micromax Q380");
	capabilities.setCapability("platformVersion", "4.4.2");
	capabilities.setCapability("platformName", "Android");
	capabilities.setCapability("app", app.getAbsolutePath());
	capabilities.setCapability("appPackage", "com.mobeta.android.demodslv");
	capabilities.setCapability("appActivity", "Launcher");

	Thread.sleep(10000);

	// Launch the App in the Mobile
	driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

	System.out.println("android App Launch");

	driver.findElementByName("Basic usage playground").click();
	driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='More options']")).click();

	driver.findElementByName("Start-drag modes").click();

	driver.findElementByName("OK").click();

	List<WebElement> lstElem = driver.findElementsByClassName("android.widget.ImageView");

	// Drag An dDrop Operation
	WebElement l_dragable = lstElem.get(0);

	WebElement l_droppable = lstElem.get(3);

	TouchAction dragNDrop = new TouchAction((MobileDriver) driver).press(l_dragable).moveTo(l_droppable).release();
	dragNDrop.perform();

	System.out.println("Drop Successfully");

	// This is to kill the Android driver
	driver.quit();

    }
}