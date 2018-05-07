package Appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;

public class Xavier extends TestCase {
    private static AndroidDriver driver;

    @BeforeClass
    public void setUp() throws InterruptedException, MalformedURLException {
	// TODO Auto-generated method stub
	File classpathRoot = new File(System.getProperty("user.dir"));
	File appDir = new File(classpathRoot, "/Apps/");
	File app = new File(appDir, "Xavier_2.0.99.apk");

	DesiredCapabilities capabilities = new DesiredCapabilities();
	//
	capabilities.setCapability("deviceName", "TA622001JQ");
	capabilities.setCapability("platformName", "Android");
	capabilities.setCapability("platformVersion", "4.4.4");
	capabilities.setCapability("app", app.getAbsolutePath());
	capabilities.setCapability("autoDismissAlerts", true);
	Thread.sleep(10000);

	// Click on Shop by Deparment link
	driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	System.out.println("Appilcation Launch");
    }

    private boolean isElementPresent(By by) {
	try {
	    driver.findElement(by);
	    return true;
	} catch (NoSuchElementException e) {
	    return false;
	}
    }

    @Test
    public void test1() {
	driver.findElement(By.xpath("//android.view.View[@content-desc='Events ? Double tap to open']")).click();

	List<WebElement> lst = driver.findElements(By.className("android.widget.LinearLayout"));
	lst.get(0).click();

	driver.findElement(By.name("Save To Calendar")).click();
	driver.findElement(By.name("com.android.calendar")).click();
	driver.findElement(By.name("Done")).click();

	driver.findElementByAndroidUIAutomator("new UiSelector().text(\"3\")").click();

	driver.findElement(By.id("android:id/list")).findElements(By.className("android.view.View")).get(0).click();
	driver.context("NATIVE_APP");
	// Set<String> contextNames = driver.getContextHandles();
	// for (String contextName : contextNames) {
	// System.out.println(contextName);
	// if (contextName.contains("WEBVIEW")) {
	// driver.context(contextName);
	// }
	// }
	driver.navigate().back();
	driver.navigate().back();
	driver.findElement(By.name("Save To Calendar")).click();
	System.out.println();

    }

    @AfterClass
    public void tearDown() {
	driver.quit();
    }
}
