import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class irctc {

    public static void main(String[] args) throws InterruptedException {

	WebDriver driver = new FirefoxDriver();
	driver.get("https://www.irctc.co.in/eticketing/loginHome.jsf");
	driver.findElement(By.id("usernameId")).sendKeys("*******************");
	driver.findElement(By.name("j_password")).sendKeys("***************");
	System.out.println("wait time start");
	Thread.sleep(10000);
	System.out.println("wait time end");
	driver.findElement(By.id("loginbutton")).click();
	driver.findElement(By.id("jpform:fromStation")).sendKeys("BKN" + Keys.ARROW_DOWN + Keys.TAB);
	driver.findElement(By.id("jpform:toStation")).sendKeys("MTD" + Keys.ARROW_DOWN + Keys.TAB);
	driver.findElement(By.id("jpform:journeyDateInputDate")).sendKeys("22-05-2016");
	driver.findElement(By.id("jpform:jpsubmit")).click();
	driver.findElement(By.className("ui-datepicker-trigger")).click();

	List<WebElement> lstElement = driver.findElement(By.id("ui-datepicker-div")).findElements(By.tagName("td"));
	for (WebElement webElement : lstElement) {
	    if (webElement.getText().trim() == "24") {
		webElement.click();
		break;
	    }
	}

	List<WebElement> lstTrainRow = driver.findElement(By.id("avlAndFareForm:trainbtwnstns"))
		.findElements(By.tagName("tr"));
	for (WebElement webElement : lstTrainRow) {
	    System.out.println(webElement.getText());
	}

    }
}
