package pomPages;

import java.awt.Robot;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import generic.BaseTest;
import io.appium.java_client.windows.WindowsDriver;

public class OrderConfirm extends BaseTest {

	@FindBy(xpath = "//input[@placeholder=\"Enter Reference No.\"]")
	WebElement referenceNumber;

	@FindBy(xpath = "//div[@class=\"col-lg-3\"]/select")
	WebElement selectPaymentMode;

	@FindBy(xpath = "//button[text()='Create Order']")
	WebElement createOrdrBtn;

	@FindBy(xpath = "//div[@id=\"swal2-content\"]")
	WebElement popupTextAfterOrderCreation;

	@FindBy(xpath = "//button[text()='OK']")
	WebElement okBtn;

	public OrderConfirm() {
		PageFactory.initElements(driver, this);
	}

	Actions a = new Actions(driver);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

	public void verifyMsgReceived() throws Exception {

//		pomPages.POM_001_HappyFlow_COD verification = new pomPages.POM_001_HappyFlow_COD();
//		Robot r = new Robot();
//		wait.until(ExpectedConditions.visibilityOf(referenceNumber));
//		Assert.assertTrue(referenceNumber.isDisplayed(), "both expected and actual are not same");
//		String abcd = referenceNumber.getAttribute("value");
//		Date d = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("ss");
//		String second = sdf.format(d);
//		System.out.println(second);
//		String testNumber = abcd + second;
//		referenceNumber.clear();
//		Thread.sleep(2000);
//		referenceNumber.sendKeys(testNumber);
//		Thread.sleep(2000);
//		System.out.println(testNumber);
//		wait.until(ExpectedConditions.visibilityOf(selectPaymentMode));
//		Assert.assertTrue(selectPaymentMode.isDisplayed(), "both expected and actual are not same");
//		selectPaymentMode.click();
//		Select s = new Select(selectPaymentMode);
//		s.selectByVisibleText("COD");
//		Thread.sleep(4000);
//		wait.until(ExpectedConditions.visibilityOf(createOrdrBtn));
//		createOrdrBtn.click();
//		wait.until(ExpectedConditions.visibilityOf(popupTextAfterOrderCreation));
//		System.out.println(popupTextAfterOrderCreation.getText());
//		Assert.assertTrue(popupTextAfterOrderCreation.getText().contains(testNumber),
//				"both expected and actual are not same");
//		okBtn.click();
//		Set<String> wh = driver.getWindowHandles();
//		Iterator itr = wh.iterator();
//		String parent = (String) itr.next();
//		String newTab1 = (String) itr.next();
//		String newTab2 = (String) itr.next();
//		String newTab3 = (String) itr.next();
//		driver.switchTo().window(parent).close();
//		Thread.sleep(500);
//		driver.switchTo().window(newTab1).close();
//		Thread.sleep(500);
//		driver.switchTo().window(newTab2);

		Thread.sleep(5000);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Windows");
		capabilities.setCapability("deviceName", "WindowsPC");
		capabilities.setCapability("appId", "5319275A.WhatsAppDesktop_cv1g1gvanyjgm"); 
		capabilities.setCapability("automationName", "Windows");


		URL appiumServerUrl = new URL("http://127.0.0.1:4723/");
		WindowsDriver driver1 = new WindowsDriver(appiumServerUrl, capabilities);

	}

}
