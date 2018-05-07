package Appium;

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
public class ScrollUntillText {

    @Test
    public void test() throws MalformedURLException {
	File appDir = new File((new File(System.getProperty("user.dir"))), "/App/");
	File app = new File(appDir, "DragSort.apk");

	DesiredCapabilities cap = new DesiredCapabilities();
	cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
	cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
	cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
	cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

	AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
	driver.findElementByName("Drag-Sort Demos").click();
	driver.findElementById("com.mobeta.android.demodslv:id/activity_desc").click();

	// *scroll untill mark turner is found and click on that contact.Here
	// after clicking
	// *nothing will happen since there is no action defined. Used Drag &
	// Drop instead of whatsapp,
	// *due to it limitation with AVD
	driver.scrollTo("Mark Turner");
	driver.findElement(By.name("Mark Turner")).click();
	System.out.println(" 'Mark Turner' text has been found and have been clicked on.");

	driver.quit();

    }

}
