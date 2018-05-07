package Appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.AppiumStartnStop;
import Utility.UnzipUtility;

public class SelendroidApp {
    private static AndroidDriver driver;
    Dimension size;
    static String destDir;
    static DateFormat dateFormat;

    public static String takeScreenShot() throws TesseractException {
	// Set folder name to store screenshots.
	destDir = "screenshots";
	// Capture screenshot.
	File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	// Set date format to set It as screenshot file name.
	dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
	// Create folder under project with name "screenshots" provided to
	// destDir.
	new File(destDir).mkdirs();
	// Set file name using current date time.
	String destFile = dateFormat.format(new Date()) + ".tiff";

	try {
	    // Copy paste file at destination folder location
	    FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	ITesseract instance = new Tesseract1();
	String str = instance.doOCR(new File((new File(destDir + "/" + destFile)).getAbsolutePath()));

	return (new File(destDir + "/" + destFile)).getAbsolutePath();
    }

    public static String image(AppiumDriver<WebElement> driver) {
	File targetFile = null;
	try {
	    File scrFile = driver.getScreenshotAs(OutputType.FILE);
	    String fileName = UUID.randomUUID().toString();
	    targetFile = new File("D:/FINAL_WORK/FunctionalAuto/screenshots/" + fileName + ".png");
	    FileUtils.copyFile(scrFile, targetFile);
	    System.out.println(targetFile.toString());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return targetFile.toString();
    }

    public static String OCR(String ImagePath) {
	String result = null;
	File imageFile = new File(ImagePath);
	System.err.println(imageFile.getAbsolutePath());
	ITesseract instance = new Tesseract();
	instance.setDatapath("D:/My Data/Useful Jars/Tess4J/tessdata");
	try {
	    result = instance.doOCR(imageFile);
	    System.out.println(result);
	} catch (Exception e) {
	    System.err.println(e.getMessage());
	}
	return result;
    }

    public static void irctc() throws IOException {
	WebDriver driver = new FirefoxDriver();
	driver.get("https://www.irctc.co.in/eticketing/loginHome.jsf");
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.manage().window().maximize();

	driver.findElement(By.xpath("//*[@id='refresh']/img")).click();

	WebElement Image = driver.findElement(By.id("cimage"));

	File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	int width = Image.getSize().getWidth();
	int height = Image.getSize().getHeight();
	BufferedImage img = ImageIO.read(screen);
	BufferedImage dest = img.getSubimage(Image.getLocation().getX(), Image.getLocation().getY(), width, height);
	ImageIO.write(dest, "png", screen);
	File file = new File("D:/My Data/Final.png");
	FileUtils.copyFile(screen, file);

	String str = OCR("D:/My Data/Final.png");
    }

    private static void selendroid() throws InterruptedException, IOException {
	// TODO Auto-generated method stub

	AppiumStartnStop APMConfig = new AppiumStartnStop();
	// AppiumServerConfi APMConfig = new AppiumServerConfi();
	try {
	    // Stop appium server If It Is already running.
	    // APMConfig.appiumStop();

	    // Start appium server.
	    // APMConfig.appiumStart();

	    // APMConfig.startAppiumServer();
	    File classpathRoot = new File(System.getProperty("user.dir"));
	    File appDir = new File(classpathRoot, "/Apps/");
	    File app = new File(appDir, "selendroid-test-app-0.17.0.apk");

	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    // capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
	    capabilities.setCapability("deviceName", "TA622001JQ");
	    capabilities.setCapability("platformName", "Android");
	    capabilities.setCapability("platformVersion", "4.4.4");
	    capabilities.setCapability("app", app.getAbsolutePath());
	    capabilities.setCapability("autoDismissAlerts", true);
	    // capabilities.setCapability("appPackage",
	    // "io.selendroid.testapp");
	    // capabilities.setCapability("appActivity", ".HomeScreenActivity");
	    // capabilities.setCapability("autoAcceptAlerts", true);
	    // capabilities.setCapability("autoDismissAlerts", true);

	    Thread.sleep(10000);

	    // Click on Shop by Deparment link
	    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	    driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

	    System.out.println("android App Launch");

	    String str = driver.findElementById("io.selendroid.testapp:id/buttonStartWebview").getAttribute("name");

	    WebElement Image = driver.findElementById("io.selendroid.testapp:id/buttonStartWebview");

	    File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    int width = Image.getSize().getWidth();
	    int height = Image.getSize().getHeight();
	    BufferedImage img = ImageIO.read(screen);
	    BufferedImage dest = img.getSubimage(Image.getLocation().getX(), Image.getLocation().getY(), width, height);
	    ImageIO.write(dest, "png", screen);
	    File file = new File("D:/My Data/FinalSelendroid.png");
	    FileUtils.copyFile(screen, file);

	    File file1 = new File(app.getAbsolutePath()); // handler to your ZIP
	    File file3 = new File(appDir.getAbsolutePath() + "\\Backup");
	    if (!file3.exists()) {
		if (file3.mkdir()) {
		    System.out.println("Directory is created!");
		} else {
		    System.out.println("Failed to create directory!");
		}
	    }

	    FileUtils.copyFileToDirectory(app, file3);
	    File file2 = new File(app.getAbsolutePath().substring(0, app.getAbsolutePath().indexOf(".apk")) + ".zip"); // destination

	    boolean success = file1.renameTo(file2);
	    if (success) {
		System.out.println("Success");
	    }
	    FileUtils.copyFileToDirectory(new File(file3 + "/" + app.getName()), appDir);
	    FileUtils.deleteDirectory((new File(appDir + "/Backup/")));

	    UnzipUtility unzipper = new UnzipUtility();
	    try {
		unzipper.unzip(file2.getAbsolutePath(), appDir.getAbsolutePath() + "\\unzip");
	    } catch (Exception ex) {
		// some errors occurred
		ex.printStackTrace();
	    }

	    driver.findElementById("io.selendroid.testapp:id/spinner_webdriver_test_data").click();
	    driver.findElementByName("xhtmlTestPage").click();
	    driver.findElementByName("Go to home screen").click();
	    driver.findElementByName("Touch Actions").click();

	    driver.findElementById("io.selendroid.testapp:id/LinearLayout1").click();
	    TouchAction action = new TouchAction(driver);
	    action.longPress(driver.findElementById("io.selendroid.testapp:id/LinearLayout1")).release().perform();

	    driver.navigate().back();

	    driver.findElementByName("Displays a Toast").click();

	    driver.findElementByName("Display Popup Window").click();

	    WebDriverWait wait = new WebDriverWait(driver, 300);
	    if (wait.until(ExpectedConditions.alertIsPresent()) == null)
		System.out.println("alert was not present");
	    else {
		driver.switchTo().alert().accept();
		System.out.println("alert was present");
	    }

	    String Text = OCR(image(driver));
	    System.out.println(Text);

	} catch (Exception e) {
	    // APMConfig.stopAppiumServer();
	    e.printStackTrace();
	    APMConfig.appiumStop();
	}

    }

    public static void main(String[] args) throws Exception {
	// irctc();
	selendroid();
    }
}
