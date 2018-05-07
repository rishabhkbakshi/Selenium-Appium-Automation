import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class makeMyTripOne {
    static WebDriver driver;

    public static void main(String[] args) {
	try {
	    // TODO Auto-generated method stub
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    driver.get("http://www.makemytrip.com/");
	    driver.findElement(By.xpath("//*[@id='widget_row']/div[1]/div/div[2]/ul/li[2]/a")).click();
	    driver.findElement(By.id("one_way_button1")).click();

	    driver.findElement(By.id("from_typeahead1")).clear();

	    // PRess tab key after enter the textbox to ensure the exception
	    // will not come
	    driver.findElement(By.id("from_typeahead1")).sendKeys("New Delhi, India (DEL)" + Keys.TAB);

	    driver.findElement(By.id("to_typeahead1")).clear();
	    driver.findElement(By.id("to_typeahead1")).sendKeys("Mumbai, India (BOM)" + Keys.TAB);

	    Thread.sleep(2000);

	    // driver.findElement(By.xpath("//*[@id='start_date_sec']/span[3]"))
	    // .click();

	    // Date Selection
	    driver.findElement(By.linkText("28")).click();

	    driver.findElement(By.id("flights_submit")).click();

	    driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div[3]/div[2]/div[3]/div/div/div/p[2]/a"))
		    .click();

	    String StrCount = driver
		    .findElement(By.xpath("//*[@id='content']/div/div[1]/div[3]/div[2]/div[3]/div/div/div/p[2]/span"))
		    .getText();

	    System.out.println(StrCount);

	    /*
	     * Get the message of total available flights and get the
	     * Integer(no. of flights) part from the STring
	     */
	    Pattern p = Pattern.compile("(\\d+)");
	    Matcher m = p.matcher(StrCount);
	    Integer j = null;
	    if (m.find()) {
		j = Integer.valueOf(m.group(1));
	    }

	    Boolean check = true;

	    List<WebElement> lstElem = null;

	    // Loop will execute till the page end
	    do {
		lstElem = driver.findElements(By.className("listing_top"));
		if (lstElem.size() < j) {
		    // Press Ctrl+End keys combinations
		    String selectAll = Keys.chord(Keys.CONTROL, Keys.END);
		    driver.findElement(
			    By.xpath("//*[@id='content']/div/div[1]/div[3]/div[2]/div[3]/div/div/div/p[2]/span"))
			    .sendKeys(selectAll);
		    check = false;
		} else {
		    break;
		}
	    } while (check == false);

	    // Print All the Flights and their fare
	    for (int i = 0; i < lstElem.size(); i++) {
		System.out.println();
		System.out.println("Fight Name :- " + lstElem.get(i).findElements(By.tagName("div")).get(0).getText());
		System.out.println("Price :- " + lstElem.get(i).findElements(By.tagName("div")).get(6).getText());
	    }

	    driver.quit();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }
}
