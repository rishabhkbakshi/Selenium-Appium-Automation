package Appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class StartApplication {

    private static AndroidDriver driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

	File classpathRoot = new File(System.getProperty("user.dir"));
	File appDir = new File(classpathRoot, "/Apps/");
	File app = new File(appDir, "AmazonShopping_8.1.0.300.apk");

	DesiredCapabilities capabilities = new DesiredCapabilities();
	// capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
	capabilities.setCapability("deviceName", "Coolpad 8298-l00");
	capabilities.setCapability("platformVersion", "5.1");
	capabilities.setCapability("platformName", "Android");
	capabilities.setCapability("app", app.getAbsolutePath());
	capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
	capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");

	Thread.sleep(10000);

	// Click on Shop by Department link
	driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

	System.out.println("android App Launch");

	//
	// // Click on Shop by Deparment link
	// driver.findElementById(
	// "in.amazon.mShop.android.shopping:id/web_home_shop_by_department_label")
	// .click();
	//
	// driver.findElementByClassName("android.widget.ImageView").click();
	//
	// // Click on Home link under Main menu
	// driver.findElement(By.name("Home")).click();
	// // Click on Sign In link on the Home Screen

	// driver.findElementByName("Sign inHello. Link").click();

	if (driver.findElementById("in.amazon.mShop.android.shopping:id/sign_in_button").isDisplayed()) {
	    driver.findElementById("in.amazon.mShop.android.shopping:id/skip_sign_in_button").click();
	}

	System.out.println("Click on Skip Button");
	try {
	    do {
		driver.findElement(By.xpath("//android.view.View[@content-desc='Sign in Hello. ']")).click();
	    } while (driver.findElement(By.xpath("//android.view.View[@content-desc='Sign in Hello. ']"))
		    .isDisplayed());
	} catch (Exception e) {
	    System.out.println("Continue");
	}
	System.out.println("Click on Sign in button");

	// Entering UserName using Parent node strategy
	WebElement parentElement = driver.findElement(By.name("Amazon Sign In"));

	if (!parentElement.isDisplayed()) {
	    driver.findElement(By.xpath("//android.view.View[@content-desc='Sign in Hello. ']")).click();
	}

	List<WebElement> childElements = parentElement.findElements(By.className("android.view.View"));
	WebElement mainElement = childElements.get(4);
	mainElement.findElement(By.className("android.widget.EditText")).sendKeys("************");

	System.out.println("Type UserName");

	Thread.sleep(1000);

	// Entering Password using Xpath & Sibling strategy

	driver.findElement(By.xpath("//android.widget.CheckBox[@content-desc='Show password']")).click();

	System.out.println("Uncheck Show Password");

	driver.findElementByXPath("//android.view.View[@content-desc='Amazon password']").sendKeys("**************");

	System.out.println("Type Password");

	// Click on Sign In button
	driver.findElementByXPath("//android.widget.Button[@resource-id='signInSubmit']").click();

	driver.findElementById("in.amazon.mShop.android.shopping:id/search_edit_text").click();

	driver.findElementById("in.amazon.mShop.android.shopping:id/rs_search_src_text").sendKeys("Parker");

	((WebElement) driver.findElementsByName("parker pen").get(0)).click();

	((WebElement) driver.findElementsByClassName("android.widget.FrameLayout").get(1)).click();

	driver.scrollTo("//android.widget.Spinner[@content-desc='Qty:1']");

	driver.findElementByXPath("//android.widget.Spinner[@content-desc='Qty:1']").click();

	driver.findElementByXPath("//android.view.View[@content-desc='2']");

	driver.findElementById("buy-now-button").click();

	driver.findElementById("existingCvvNum").clear();
	driver.findElementById("existingCvvNum").sendKeys("182");

	driver.findElementById("continueButton").click();

	driver.findElementByXPath(
		"//android.widget.Button[@content-desc='Place Your Order and Pay Place Your Order and Pay']").click();

	driver.findElementById("in.amazon.mShop.android.shopping:id/action_bar_burger_icon").click();
	driver.scrollTo("Not Rishabh Bakshi? Sign out");

	driver.findElementByName("Not Rishabh Bakshi? Sign out").click();

	driver.findElementByName("Sign Out").click();

	Thread.sleep(1000);

	// This is to kill the Android driver
	driver.quit();

    }
}