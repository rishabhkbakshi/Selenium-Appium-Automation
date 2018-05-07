package Appium;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

@SuppressWarnings("unused")
public class mobileDragDrop {

    @Test
    public void test() throws MalformedURLException {

	File appDir = new File((new File(System.getProperty("user.dir"))), "/App/");
	File app = new File(appDir, "DragSort.apk");

	// set desired capabilities
	DesiredCapabilities cap = new DesiredCapabilities();
	cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
	cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
	cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
	cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
	AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

	// Initialize drag & sort app
	driver.findElementByName("Drag-Sort Demos").click();
	driver.findElementById("com.mobeta.android.demodslv:id/activity_desc").click();

	// element(Miles) from list to drag.
	MobileElement abc = (MobileElement) driver.findElement(By.name("Miles Davis"));
	driver.findElementByName("Basic usage").click();
	MobileElement Mob1 = (MobileElement) driver.findElementsById("com.mobeta.android.demodslv:id/drag_handle")
		.get(1);

	// element to drop been dragged.

	MobileElement Mob2 = (MobileElement) driver.findElementsById("com.mobeta.android.demodslv:id/drag_handle")
		.get(6);

	// drag and drop done using TouchAction class.

	TouchAction action = new TouchAction((MobileDriver) driver);

	System.out.println("Element item dragged.");

	// Hold tap from base element and move to target position and then
	// release tap.

	action.longPress(Mob1).moveTo(Mob2).release().perform();

	System.out.println("Element item dragged successfully.");

	driver.quit();

    }

}
