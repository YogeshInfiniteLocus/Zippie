package pomPages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import generic.BaseTest;
import generic.Libraries;

public class POM_010_COD_WA_Reply_Delivered_Rate_Experience extends BaseTest {

	@FindBy(xpath = "//*[@id=\"main\"]/div[3]/div/div[2]/div[3]/div//button")
	List<WebElement> BtnOnDeliveredMsg;

//	@FindBy(xpath = "//iframe[@class=\"loginext-iframe\"]")
	@FindBy(xpath = "//iframe[@title='Order Tracking']")
	WebElement frame1;

//	@FindBy(xpath = "//div//textarea[@placeholder=\"Tell us about your experience\"]")
	@FindBy(xpath = "//textarea[@placeholder='Tell us about your experience']")	
	WebElement rateTextbox;

	@FindBy(xpath = "//*[@id=\"track_orderingExperience\"]/span/label[5]")
	WebElement star1;

	@FindBy(xpath = "//*[@id=\"track_deliveryExperience\"]/span/label[5]")
	WebElement star2;

	@FindBy(xpath = "//*[@id=\"track_qualityOfProduct\"]/span/label[5]")
	WebElement star3;

	@FindBy(xpath = "//*[@id=\"send_rating\"][text()='Submit']")
	WebElement submitBtn;

	@FindBy(xpath = "(//div[text()='Thanks for your feedback'])[1]")
	WebElement thanksText;
	
	@FindBy(xpath = "//div[text()='Yes, open']")
	WebElement yesBtn;

	public POM_010_COD_WA_Reply_Delivered_Rate_Experience() {
		PageFactory.initElements(driver, this);
	}

	Actions a = new Actions(driver);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

	public void verifyRateExperience() throws Exception {

		Set<String> wh = driver.getWindowHandles();
		Iterator itr = wh.iterator();
		String parent = (String) itr.next();
		String tab1 = (String) itr.next();

		driver.switchTo().window(tab1);
		System.out.println(driver.getCurrentUrl());

		driver.switchTo().window(parent);
		System.out.println(driver.getCurrentUrl());

		Thread.sleep(2000);

		int size = BtnOnDeliveredMsg.size();
		System.out.println(BtnOnDeliveredMsg.get(size - 1).getText());
		BtnOnDeliveredMsg.get(size - 1).click();
		Thread.sleep(1000);
		yesBtn.click();
		Thread.sleep(4000);

		Set<String> wh3 = driver.getWindowHandles();
		Iterator itr3 = wh3.iterator();
		String parent001 = (String) itr3.next();
		String newTab0001 = (String) itr3.next();
		String newTab0002 = (String) itr3.next();

		driver.switchTo().window(parent001);
		System.out.println(driver.getCurrentUrl());

		driver.switchTo().window(newTab0001);
		System.out.println(driver.getCurrentUrl());

		driver.switchTo().window(newTab0002);
		System.out.println(driver.getCurrentUrl());

		Assert.assertTrue(driver.getCurrentUrl().contains("https://www.zippee.delivery/track-order?orderNumber="),
				"both expected and actual are not same");
		driver.switchTo().frame(frame1);
		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOf(rateTextbox));
		Thread.sleep(1000);
		rateTextbox.sendKeys("Test Order");
		Thread.sleep(1000);

		star1.click();
		Thread.sleep(1000);
		star2.click();
		Thread.sleep(1000);
		star3.click();
		Thread.sleep(2000);

		driver.switchTo().defaultContent();
		Thread.sleep(1000);

		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,-800)");
		Thread.sleep(1000);
		
		driver.switchTo().frame(frame1);
		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOf(submitBtn));
		Thread.sleep(1000);
		a.moveToElement(submitBtn).click().build().perform();
//		submitBtn.click();
		wait.until(ExpectedConditions.visibilityOf(thanksText));

		Assert.assertTrue(thanksText.getText().contains("Thanks for your feedback"),
				"both expected and actual are not same");
		Thread.sleep(5000);

		driver.switchTo().window(newTab0002);

	}

}
