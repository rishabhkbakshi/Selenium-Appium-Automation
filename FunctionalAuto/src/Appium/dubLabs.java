package Appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;

public class dubLabs extends TestCase {
    private static AndroidDriver driver;

    @BeforeClass
    public void setUp() throws InterruptedException, MalformedURLException {
	// TODO Auto-generated method stub
	File classpathRoot = new File(System.getProperty("user.dir"));
	File appDir = new File(classpathRoot, "/Apps/");
	File app = new File(appDir, "DubLabs_2.0.29.apk");

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
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

	try {
	    if (isElementPresent(By.id("android:id/parentPanel"))) {
		if (isElementPresent(By.name("Remind Later"))) {
		    driver.findElement(By.name("Remind Later")).click();
		} else {
		    driver.findElement(By.name("No")).click();
		}
		if (isElementPresent(By.name("No, thanks"))) {
		    driver.findElement(By.name("No, thanks")).click();
		}
	    } else {
		driver.findElement(By.id("com.dub.app.dublabs:id/sidebar_header_button_left_id")).click();
	    }
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	System.out.println("Final");

	driver.findElement(By.name("Courses")).click();

	if (isElementPresent(By.name("Login"))) {
	    driver.findElement(By.id("com.dub.app.dublabs:id/login_text_user_id")).sendKeys("sales");
	    driver.findElement(By.id("com.dub.app.dublabs:id/login_text_password_id")).sendKeys("dub123");
	    driver.findElement(By.id("com.dub.app.dublabs:id/login_yes_button_id")).click();
	}

	driver.findElement(By.name("Spring 2016")).click();
	driver.findElement(By.id("com.dub.app.dublabs:id/header_overflow_view_id")).click();
	// driver.findElement(By.name("View Map")).click();

	TouchAction t = new TouchAction(driver);
	t.tap(driver.findElement(By.id("com.dub.app.dublabs:id/header_overflow_view_id")).getSize().getHeight(),
		driver.findElement(By.id("com.dub.app.dublabs:id/header_overflow_view_id")).getSize().getWidth())
		.perform();

	List<WebElement> lstMap = driver.findElements(By.className("android.view.View"));
	for (int i = 0; i < lstMap.size(); i++) {
	    System.out.println(lstMap.get(i).getAttribute("content-desc").toString());
	}

	System.out.println();
	List<Object> lstDistinct = new ArrayList<Object>();
	// Print all the Classess of one Class
	List<WebElement> lst = driver.findElements(By.className("android.widget.LinearLayout"));
	lst.get(0).findElement(By.id("com.dub.app.dublabs:id/arrow")).click();

	List<WebElement> lstListView = driver.findElement(By.id("android:id/list"))
		.findElements(By.id("com.dub.app.dublabs:id/courses_list_item_text1_id"));

	for (WebElement webElement : lstListView) {
	    if (!webElement.getText().trim().equalsIgnoreCase("")) {
		lstDistinct.add(webElement.getText());
	    }
	}

	Dimension size = driver.manage().window().getSize();
	int y_start = (int) (size.height * 0.60);
	int y_end = (int) (size.height * 0.30);
	int x = size.width / 2;
	driver.swipe(x, y_start, x, y_end, 4000);

	lstListView = driver.findElement(By.id("android:id/list"))
		.findElements(By.id("com.dub.app.dublabs:id/courses_list_item_text1_id"));

	for (WebElement webElement : lstListView) {
	    if (!webElement.getText().trim().equalsIgnoreCase("")) {
		lstDistinct.add(webElement.getText());
	    }
	}

	lstDistinct = lstDistinct.stream().distinct().collect(Collectors.toList());

	driver.findElement(By.name("Roster")).click();
	driver.findElement(By.name("Grades")).click();
	driver.findElement(By.name("Announcements")).click();
	driver.findElement(By.name("Assignments")).click();

	driver.findElement(By.id("com.dub.app.dublabs:id/header_icon_view_id")).click();
	driver.findElement(By.id("com.dub.app.dublabs:id/sidebar_header_button_left_id")).click();
    }

    @AfterClass
    public void tearDown() {
	driver.findElement(By.id("com.dub.app.dublabs:id/sidebar_header_button_left_id")).click();
	driver.scrollTo("Logout");
	driver.findElement(By.name("Logout")).click();
	driver.findElement(By.name("Yes")).click();
	driver.quit();
    }
}
