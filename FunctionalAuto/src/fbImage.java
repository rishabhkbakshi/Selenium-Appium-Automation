import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class fbImage {
    public static void main(String[] args) throws InterruptedException, AWTException, IOException {
	FileUtils.deleteDirectory(new File("D:\\New folder\\Image"));

	WebDriver driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.get("https://www.facebook.com/");
	// Enter Email Id
	driver.findElement(By.id("email")).clear();
	driver.findElement(By.id("email")).sendKeys("*************@gmail.com");

	// Enter Password
	driver.findElement(By.id("pass")).clear();
	driver.findElement(By.id("pass")).sendKeys("*************");
	driver.findElement(By.id("loginbutton")).click();

	driver.findElement(By.xpath("//*[@id='pagelet_welcome_box']/ul/li[1]/div/a")).click();

	driver.findElement(By.xpath("//a[@data-tab-key='photos']")).click();
	Thread.sleep(2200);
	driver.findElement(By.name("Your Photos")).click();

	Boolean check = true;
	List<String> lstImageURL = new ArrayList<String>();
	List<WebElement> lstElem = null;
	do {
	    try {
		WebElement movie = driver.findElement(By.xpath("//*[@id='medley_header_movies']/a"));
		System.out.println(movie.getText());

		if (movie.getText().equalsIgnoreCase("Films")) {
		    check = true;
		    break;
		} else {
		    continue;
		}
	    } catch (Exception e) {

		System.out.println("Nhi mila element movie wala wapas se loop me ghoomiye Birju Rupi Code");
		check = false;
		lstElem = new ArrayList<WebElement>();
		lstElem = driver.findElements(
			By.tagName("div").id("pagelet_timeline_medley_photos").className("uiMediaThumbImg"));

		String selectAll = Keys.chord(Keys.CONTROL, Keys.END);
		driver.findElement(By.xpath(".//*[@id='pagelet_timeline_medley_photos']")).sendKeys(selectAll);
		Thread.sleep(2000);
	    }
	} while (check == false);

	System.out.println("Total Image => " + lstElem.size());
	new File("D:\\FINAL_WORK\\FunctionalAuto\\UploadedImage").mkdirs();

	for (WebElement webElement : lstElem) {
	    String str = webElement.getAttribute("style").replace("background-image: url(\"", "").replace("\");", "");

	    lstImageURL.add(str);
	}

	for (int i = 0; i < lstImageURL.size(); i++) {

	    System.out.println("Image URL => \"" + lstImageURL.get(i) + "\"");
	    driver.get(lstImageURL.get(i));

	    // Rihgt click on Image using contextClick() method.
	    Actions action = new Actions(driver);
	    action.contextClick(driver.findElement(By.tagName("img"))).build().perform();

	    Thread.sleep(1000);
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_V);

	    Thread.sleep(2000);

	    setClipboardData("D:\\FINAL_WORK\\FunctionalAuto\\UploadedImage\\image" + i + ".JPG");

	    Robot robot2 = new Robot();
	    robot2.keyPress(KeyEvent.VK_DELETE);
	    robot2.keyRelease(KeyEvent.VK_DELETE);
	    robot2.delay(100);
	    robot2.keyPress(KeyEvent.VK_CONTROL);
	    robot2.keyPress(KeyEvent.VK_V);
	    robot2.delay(100);
	    robot2.keyRelease(KeyEvent.VK_V);
	    robot2.keyRelease(KeyEvent.VK_CONTROL);
	    robot2.delay(100);
	    robot2.keyPress(KeyEvent.VK_ENTER);
	    robot2.keyRelease(KeyEvent.VK_ENTER);

	    System.out.println("Save");
	}
    }

    public static void setClipboardData(String string) {
	StringSelection stringSelection = new StringSelection(string);
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

	System.out.println("Copy to clipboard");
    }
}
