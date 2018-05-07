
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Review {

	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		// Create a new instance of the Firefox driver
		driver = new FirefoxDriver();

		// Add wait in order to wait for while to load all the elements
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Open url
		driver.get("https://wallethub.com/profile/test_insurance_company/");

		// Click on login button to login.
		driver.findElement(By.className("login")).click();

		// Type Username,password and then click on login button to login
		driver.findElement(By.name("em")).clear();
		driver.findElement(By.name("em")).sendKeys("suneem.thakur@gmail.com");
		driver.findElement(By.name("pw")).clear();
		driver.findElement(By.name("pw")).sendKeys("Toddler17*");
		driver.findElement(By.xpath("//*[@id='join-login']/form/div[4]/button[2]")).click();

		// Hover over the stars and ************verify stars get lit
		// up.**********
		WebElement element = driver.findElement(By.xpath("//*[@id='wh-body-inner']/div[2]/div[3]/span"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

		// Click on the Fifth star.
		driver.findElement(By.xpath("//*[@id='wh-body-inner']/div[2]/div[3]/div[1]/div/a[5]")).click();

		// You will redirected to the write a review action page.
		// Configure the "Please select the policy" dropdown and select the
		// particular value.
		driver.findElement(By.className("dropdown-title")).click();
		driver.findElements(By.xpath("//div[contains(@class,'dropdown-list-new')]/ul/li")).get(3).click();
		Thread.sleep(2000);
		// Click for rating.
		WebElement elements = (new WebDriverWait(driver, 5))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='overallRating']/a[4]")));
		elements.click();

		// Click on text area to write a review and submit.
		driver.findElement(By.id("review-content")).clear();
		driver.findElement(By.id("review-content")).sendKeys(
				"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
		Thread.sleep(2000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@type='submit']")));

		// Verification of confirmation screen after review submit.
		Thread.sleep(2000);
		String msg = "Your Test Insurance Company review has been posted";

		// To get the text then you have to use .getText() option
		String message = driver.findElement(By.xpath("//*[@id='review']/div[1]/h1/span")).getText().toString();

		// When you compare two string the you have to use .equals()
		// .equalsignoreCase()
		if (msg.equals(message)) {
			System.out.println("verified");
			driver.get("https://wallethub.com/profile/suneem_kumar83/");
		} else {
			System.out.println("your review is not successfully submit 'Please check'");
			driver.get("https://wallethub.com/reviews/?review_action=write&iid=13732055");
		}

	}

}
