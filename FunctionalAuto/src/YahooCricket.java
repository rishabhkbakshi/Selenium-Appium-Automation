import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class YahooCricket {

    public static void main(String[] args) {
	// TODO Auto-generated method stub

	WebDriver driver = new FirefoxDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get("https://cricket.yahoo.com/postmatch-england-vs-sri-lanka_192396");

	List<WebElement> elem = driver
		.findElements(By.id("currentinnings").xpath("//div[@class='ycric-fs-inningsdethldr']"));

	List<WebElement> lstElem = elem.get(2).findElements(By.tagName("ul")).get(0)
		.findElements(By.xpath("//li[@bt='1'][contains(@id, 'player-')]"));

	for (int i = 0; i < lstElem.size(); i++) {

	    String str1 = lstElem.get(i).findElements(By.tagName("div")).get(0).getText().trim();
	    if (!str1.equalsIgnoreCase("")) {

		// Replace '\n' by '\t\t' to print the text in tabular form
		System.out.println(str1.contains("\n") ? str1.replace("\n", "\t\t") : str1 + "\n");
		lstElem.get(i).findElements(By.tagName("div")).get(0).click();
		List<WebElement> elemList = lstElem.get(i).findElements(By.tagName("div"));
		for (int j = 0; j < elemList.size(); j++) {

		    // Code enter when code get the 'OUT!' in the InnerText of
		    // the DIV and Code will print all the Description of 'How
		    // the batsman was out'
		    if (elemList.get(j).getText().contains("OUT!")) {
			String[] outDesp = elemList.get(j).getText().split("\n");
			System.out.println(outDesp[0] + "\n" + outDesp[1]);
			System.out.println();
			break;
		    }
		}
	    }
	}
	driver.quit();
    }
}
