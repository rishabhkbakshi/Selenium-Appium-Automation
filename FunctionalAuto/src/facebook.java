import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class facebook {
	static WebDriver driver = new FirefoxDriver();

	public static void main(String[] args) {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("https://www.facebook.com/");

			// Enter Email Id
			driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("email")).sendKeys("***********@gmail.com");

			// Enter Password
			driver.findElement(By.id("pass")).clear();
			driver.findElement(By.id("pass")).sendKeys("***********");
			driver.findElement(By.id("loginbutton")).click();

			driver.findElement(By.xpath("//*[@id='pagelet_welcome_box']/ul/li[1]/div/a")).click();

			// Click of Friends
			driver.findElement(By.cssSelector("span._gs6")).click();

			Boolean check = true;
			List<WebElement> lstTotalFriend = null;
			List<WebElement> lstUL = null;

			/*
			 * When we click on Friends Link Button. Check the Film (Which you
			 * liked) container for scrolling. Loop will execute till the page
			 * end
			 */
			do {
				try {
					WebElement movie = driver.findElement(By.xpath("//*[@id='medley_header_movies']/span"));
					System.out.println(movie.getText());

					if (movie.getText().equalsIgnoreCase("films")) {
						check = true;
						break;
					} else {
						continue;
					}
				} catch (Exception e) {
					check = false;
					lstUL = new ArrayList<WebElement>();
					WebElement elemFriendList = driver
							.findElement(By.xpath("//*[@id='pagelet_timeline_medley_friends']/div[2]"));
					lstUL = elemFriendList.findElements(By.tagName("ul"));

					// Press Ctrl+End keys combinations
					String selectAll = Keys.chord(Keys.CONTROL, Keys.END);
					driver.findElement(By.xpath("//*[@id='pagelet_timeline_medley_friends']/div[2]"))
							.sendKeys(selectAll);
				}
			} while (check == false);

			int count = 0;
			List<WebElement> lstLi = new ArrayList<WebElement>();
			for (int i = 0; i < lstUL.size(); i++) {
				lstLi = lstUL.get(i).findElements(By.tagName("li"));
				List<WebElement> lstFriendButton = new ArrayList<WebElement>();
				for (int j = 0; j < lstLi.size(); j++) {
					try {
						// Friend those have Profile Pic
						System.out.println(lstLi.get(j).findElements(By.tagName("a")).get(2).getText());
					} catch (Exception e) {
						// Friend those does not have Profile Pic
						System.out.println(lstLi.get(j).findElements(By.tagName("a")).get(0).getText());
					}
					lstFriendButton = lstLi.get(j).findElements(By.className("FriendButton"));
				}
				count = count + lstLi.size();
			}
			System.out.println();

			// Print All the count of Total Friend of Yours
			System.out.println("Total Friends :- " + (count - 1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}
