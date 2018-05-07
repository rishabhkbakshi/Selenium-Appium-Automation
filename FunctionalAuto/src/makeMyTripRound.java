import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class makeMyTripRound {

    public static void main(String[] args) {
	boolean val = true;
	WebDriver driver = new FirefoxDriver();
	driver.manage().window().maximize();
	driver.get("https://www.makemytrip.com/");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.xpath("//span[contains(@class,' tab_txt')]")).click();

	WebElement origin = driver.findElement(By.xpath("//*[@id='from_typeahead1']"));
	origin.click();
	origin.clear();
	origin.sendKeys("Mumbai, India (BOM)");
	origin.sendKeys(Keys.RETURN);
	WebElement destination = driver.findElement(By.xpath("//*[@id='to_typeahead1']"));
	destination.click();
	destination.clear();
	destination.sendKeys("New Delhi, India (DEL)");
	destination.sendKeys(Keys.ENTER);

	// drop.selectByVisibleText("Mumbai, India (BOM)");

	driver.findElement(By.xpath("//*[@id='start_date_sec']")).click();
	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	List<WebElement> table1 = driver
		.findElements(By.xpath("//table[contains(@class,'ui-datepicker-calendar')]").tagName("tbody"));

	List<WebElement> column = table1.get(1).findElements(By.tagName("td"));
	for (WebElement list : column) {
	    if (list.getText().equals("5")) {
		list.click();
		break;
	    }
	}

	driver.findElement(By.xpath("//*[@id='return_date_sec']")).click();
	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

	List<WebElement> table2 = driver
		.findElements(By.xpath("//table[contains(@class,'ui-datepicker-calendar')]").tagName("tbody"));

	List<WebElement> col = table2.get(0).findElements(By.tagName("td"));
	for (WebElement list1 : col) {
	    if (list1.getText().equals("6")) {
		list1.click();
		break;
	    }
	}
	driver.findElement(By.xpath("//*[@id='flights_submit']")).click();

	boolean value = false;
	int count;
	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	List<WebElement> seats = driver.findElements(By.xpath("//span[contains(@class,'light_gray ng-binding')]"));
	String lefts = seats.get(0).getText();
	int leftcount = Integer.parseInt(lefts.replaceAll("[\\D]", ""));
	System.out.println(leftcount);
	String rights = seats.get(1).getText();
	int rightscount = Integer.parseInt(rights.replaceAll("[\\D]", ""));
	System.out.println(rightscount);
	count = leftcount + rightscount;

	// System.out.println(listitem.get(20).getAttribute("data-flt-index"));
	int i = 0;
	do {
	    JavascriptExecutor jse = (JavascriptExecutor) driver;
	    jse.executeScript("scroll(0,300000);");
	    List<WebElement> listitem = driver
		    .findElements(By.xpath("//*[@class='listing_section_list radius_for_left clearfix']"));
	    int match = listitem.size();

	    if (match < (count - 2)) {
		value = false;
	    } else {
		break;
	    }

	} while (value == false);

	int var = 0, var1 = 0;
	List<WebElement> airlist = driver
		.findElements(By.xpath("//*[@class='listing_section_list radius_for_left clearfix']"));
	for (WebElement list1 : airlist) {
	    List<WebElement> flightprice = driver.findElements(By.xpath("//p[@class='splt_orgnl_prce ng-binding']"));
	    List<WebElement> data = driver.findElements(By.xpath(
		    "//span[@class='block splt_flght_name ng-binding ng-scope' or @class='block splt_flght_name multi-al ng-scope']"));

	    if (var1 <= flightprice.size()) {
		System.out.println("filght price:" + flightprice.get(var1).getText());
		var1++;
		if (var <= data.size()) {
		    System.out.println("flight name:" + data.get(var).getText());
		}
		var++;
	    } else {
		break;
	    }
	    System.out.println();
	}
	driver.quit();
    }
}