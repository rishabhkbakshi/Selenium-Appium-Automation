import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

class Constant {
    public static final String filePath = "D:\\FINAL_WORK\\FunctionalAuto\\Data\\";
    public static final String fileName = "Credential.xlsx";
}

class ExcelUtils {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    // Set the Path of the Excel
    public static void setExcelFile(String Path, String SheetName) throws Exception {
	try {
	    FileInputStream ExcelFile = new FileInputStream(Path);
	    ExcelWBook = new XSSFWorkbook(ExcelFile);
	    ExcelWSheet = ExcelWBook.getSheet(SheetName);
	} catch (Exception e) {
	    throw (e);
	}
    }

    // get the Data of the particuler Excel
    public static String getCellData(int RowNum, int ColNum) throws Exception {
	try {
	    Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
	    String CellData = Cell.getStringCellValue();
	    return CellData;
	} catch (Exception e) {
	    return "";
	}
    }

    public static void setCellData(String Result, int RowNum, int ColNum) throws Exception {
	try {
	    Row = ExcelWSheet.getRow(RowNum);
	    Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
	    if (Cell == null) {
		Cell = Row.createCell(ColNum);
		Cell.setCellValue(Result);
	    } else {
		Cell.setCellValue(Result);
	    }
	    FileOutputStream fileOut = new FileOutputStream(Constant.filePath + Constant.fileName);
	    ExcelWBook.write(fileOut);
	    fileOut.flush();
	    fileOut.close();
	} catch (Exception e) {
	    throw (e);
	}
    }
}

public class fullContacts {

    public static void main(String[] args) throws Exception {

	ExcelUtils.setExcelFile(Constant.filePath + Constant.fileName, "Sheet1");

	// User Id / Password
	String userName = ExcelUtils.getCellData(1, 0);

	String password = ExcelUtils.getCellData(1, 1);

	WebDriver driver = new FirefoxDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get("https://www.fullcontact.com/");
	driver.findElement(By.xpath("//*[@id='fc-page']/header[1]/a[2]")).click();

	// Enter UserId/Password
	driver.findElement(By.id("email")).clear();
	driver.findElement(By.id("email")).sendKeys(userName);
	driver.findElement(By.id("password")).clear();
	driver.findElement(By.id("password")).sendKeys(password);
	driver.findElement(By.xpath("//div[@id='view-content-anchor']/section/form/div[2]/button")).click();
	Thread.sleep(3000);

	try {

	    // When Skip pages are shown
	    driver.findElement(By.xpath("//div[@id='modal_68']/div/div/section/footer/button[2]")).click();
	    driver.findElement(By.xpath("//div[@id='modal_68']/div/div/section/footer/button[2]")).click();
	    driver.findElement(By.xpath("//div[@id='modal_68']/div/div/section/footer/button[2]")).click();
	    driver.findElement(By.xpath("//div[@id='modal_68']/div/div/section/footer/button[2]")).click();
	    driver.findElement(By.xpath("//*[@id='tugboat']/main/section[2]/nav[2]/div[1]/span[2]")).click();
	    driver.findElement(By.xpath("//*[@id='tugboat']/header/div[4]/div/div/nav/a")).click();
	} catch (Exception e) {
	    // When Skip pages are not shown
	    driver.findElement(By.xpath("//*[@id='tugboat']/main/section[2]/nav[2]/div[1]/span[2]")).click();
	    driver.findElement(By.xpath("//*[@id='tugboat']/header/div[4]/div/div/nav/a")).click();
	}
	Thread.sleep(2000);

	// Form Filling
	driver.findElement(By.id("edit-prefix")).click();
	driver.findElement(By.id("edit-prefix")).clear();
	driver.findElement(By.id("edit-prefix")).sendKeys("Automation");
	driver.findElement(By.id("edit-first-name")).clear();
	driver.findElement(By.id("edit-first-name")).sendKeys("Automation");

	driver.findElement(By.id("edit-middle-name")).clear();
	driver.findElement(By.id("edit-middle-name")).sendKeys("Automation");
	driver.findElement(By.id("edit-last-name")).clear();
	driver.findElement(By.id("edit-last-name")).sendKeys("Automation");
	driver.findElement(By.id("edit-suffix")).clear();
	driver.findElement(By.id("edit-suffix")).sendKeys("Automation");
	driver.findElement(By.id("edit-nickname")).clear();
	driver.findElement(By.id("edit-nickname")).sendKeys("Automation");
	driver.findElement(By.id("organization-0")).clear();
	driver.findElement(By.id("organization-0")).sendKeys("Skilrock Technologies");
	driver.findElement(By.id("department-0")).clear();
	driver.findElement(By.id("department-0")).sendKeys("automation");
	driver.findElement(By.id("title-0")).clear();
	driver.findElement(By.id("title-0")).sendKeys("Automation");
	driver.findElement(By.xpath("//*[@id='current-view']/div/section/main/article[2]/div/div/input")).click();
	// Email ID
	driver.findElement(By.xpath("//*[@id='current-view']/div/section/main/article[2]/div/div/input"))
		.sendKeys("Automation@gmail.com");

	driver.findElement(By.xpath("//*[@id='current-view']/div/section/main/article[3]/div/div/input")).clear();

	// Your Phone No.
	driver.findElement(By.xpath("//*[@id='current-view']/div/section/main/article[3]/div/div/input"))
		.sendKeys("Automation@1234");

	driver.findElement(By.xpath("//*[@id='current-view']/nav/div[1]")).click();

	driver.findElement(By.className("icon-edit-contact")).click();

	driver.findElement(By.xpath("//*[@id='current-view']/div/section/main/header/div/i[2]")).click();

	driver.findElement(By.id("btn-upload-photo")).click();

	/*
	 * Upload the Picture by .au3 File use this link "
	 * http://toolsqa.com/selenium-webdriver/autoit-selenium-webdriver/"
	 */

	Runtime.getRuntime().exec("D:\\FINAL_WORK\\FunctionalAuto\\au3\\test.exe");
	Thread.sleep(5000);

	driver.findElement(By.xpath("//*[@id='current-view']/nav/div[1]")).click();

	driver.get("https://www.fullcontact.com/");
	driver.quit();
    }
}
