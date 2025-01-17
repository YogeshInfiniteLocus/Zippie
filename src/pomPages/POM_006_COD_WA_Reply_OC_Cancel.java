package pomPages;

import java.awt.Robot;
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

public class POM_006_COD_WA_Reply_OC_Cancel extends BaseTest {

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

	@FindBy(xpath = "(//div[@aria-label=\"Chat list\"]/div/div/div/div)[1]")
	WebElement firstMsgOnWhatsApp;

//	@FindBy(xpath = "//div[@class=\"copyable-text\"]")
	@FindBy(xpath = "//*[@id=\"main\"]/div[3]/div/div[2]/div[3]/div")
	List<WebElement> msgList;

	@FindBy(xpath = "//*[@id=\"main\"]/div[3]/div/div[2]/div[3]/div//button")
	List<WebElement> cancelOrderBtn;

	public POM_006_COD_WA_Reply_OC_Cancel() {
		PageFactory.initElements(driver, this);
	}

	Actions a = new Actions(driver);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

	public void verifyMsgReceived() throws Exception {

		pomPages.POM_001_HappyFlow_COD verification = new pomPages.POM_001_HappyFlow_COD();
		Robot r = new Robot();
		wait.until(ExpectedConditions.visibilityOf(referenceNumber));
		Assert.assertTrue(referenceNumber.isDisplayed(), "both expected and actual are not same");
		String abcd = referenceNumber.getAttribute("value");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ss");
		String second = sdf.format(d);
		System.out.println(second);
		String testNumber = abcd + second;
		referenceNumber.clear();
		Thread.sleep(2000);
		referenceNumber.sendKeys(testNumber);
		Thread.sleep(2000);
		System.out.println(testNumber);
		wait.until(ExpectedConditions.visibilityOf(selectPaymentMode));
		Assert.assertTrue(selectPaymentMode.isDisplayed(), "both expected and actual are not same");
		selectPaymentMode.click();
		Select s = new Select(selectPaymentMode);
		s.selectByVisibleText("COD");
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOf(createOrdrBtn));
		createOrdrBtn.click();
		wait.until(ExpectedConditions.visibilityOf(popupTextAfterOrderCreation));
		System.out.println(popupTextAfterOrderCreation.getText());
		Assert.assertTrue(popupTextAfterOrderCreation.getText().contains(testNumber),
				"both expected and actual are not same");
		okBtn.click();
		Set<String> wh = driver.getWindowHandles();
		Iterator itr = wh.iterator();
		String parent = (String) itr.next();
		String newTab1 = (String) itr.next();
		String newTab2 = (String) itr.next();
		String newTab3 = (String) itr.next();
		driver.switchTo().window(parent).close();
		Thread.sleep(500);
		driver.switchTo().window(newTab1).close();
		Thread.sleep(500);
		driver.switchTo().window(newTab2);
		Thread.sleep(5000);

		driver.get("https://web.whatsapp.com/");
		wait.until(ExpectedConditions.visibilityOf(firstMsgOnWhatsApp));

		String lastMsg0 = "";

		for (int i = 0; i < 120; i++) {
			firstMsgOnWhatsApp.click();
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOfAllElements(msgList));

			int size1 = msgList.size();
			lastMsg0 = msgList.get(size1 - 1).getText();

			System.out.println("Last Message: " + lastMsg0);

			if (lastMsg0.contains(testNumber)) {
				break;
			}

			Thread.sleep(1000);
		}
		Thread.sleep(5000);

		if (lastMsg0.contains("Order Confirmation")) {
			Assert.assertTrue(lastMsg0.contains("Order Confirmation"), "Expected message did not appear.");
			Assert.assertTrue(lastMsg0.contains(testNumber), "Expected test number not found in message.");
			Assert.assertTrue(lastMsg0.contains("Confirm Order"), "Expected 'Track your order' not found.");
			Assert.assertTrue(lastMsg0.contains("Cancel Order"), "Expected 'Contact Us' not found.");
		} else {

			Assert.fail("The message 'Order Confirmation' was not found within 2 minutes.");
		}

		int size = cancelOrderBtn.size();
		System.out.println(cancelOrderBtn.get(size - 1).getText());
		cancelOrderBtn.get(size - 1).click();
		Thread.sleep(4000);

		String lastMsg1 = "";

		for (int i = 0; i < 60; i++) {
			firstMsgOnWhatsApp.click();
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOfAllElements(msgList));

			int size1 = msgList.size();
			lastMsg1 = msgList.get(size1 - 1).getText();

			System.out.println("Last Message: " + lastMsg1);

			if (lastMsg1.contains("We're currently in the process of canceling your order. ")) {
				break;
			}

			Thread.sleep(1000);
		}

		Assert.assertTrue(lastMsg1.contains("Got it!"), "both expected and actual are not same");
		Assert.assertTrue(lastMsg1.contains("We're currently in the process of canceling your order. "),
				"both expected and actual are not same");
		Assert.assertTrue(lastMsg1.contains("Thank you! For any other assistance, call our Customer Support Team"),
				"both expected and actual are not same");
		Thread.sleep(3000);

//-------------------------------------DB PART1-------------------------------------------
		String dburl = Libraries.fetchPropertyValue("dbUrl02").toString();
		String user = Libraries.fetchPropertyValue("dbUsername").toString();
		String pass = Libraries.fetchPropertyValue("dbPassword").toString();

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection(dburl, user, pass);
		if (conn.isClosed()) {
			System.out.println("Not connected to DB");
		} else {
			System.out.println("Connected to DB");
		}

		String query1 = "SELECT * FROM zfw_webhook_history WHERE reference_code = ?";
		String query2 = "select * from zfw_wa_comm_logs zwcl where ref_code= ?";

		PreparedStatement ps1 = conn.prepareStatement(query1);
		ps1.setString(1, testNumber);
		ResultSet rs1 = ps1.executeQuery();
		System.out.println(query1);

		int rowNum1 = 0;
		while (rs1.next()) {
			rowNum1++;

			if (rowNum1 == 1) {

				String columnValue4Row1 = rs1.getString(4);
				System.out.println("Row 1, Column 4: " + columnValue4Row1);
				Assert.assertTrue(columnValue4Row1.contains("FETCH_ORDER"), "both expected and actual are not same");

				String columnValue6Row1 = rs1.getString(6);
				System.out.println("Row 1, Column 6: " + columnValue6Row1);
				Assert.assertTrue(columnValue6Row1.contains("Webhook processed successfully"),
						"both expected and actual are not same");
			}
		}

		PreparedStatement ps2 = conn.prepareStatement(query2);
		ps2.setString(1, testNumber);
		ResultSet rs2 = ps2.executeQuery();

		System.out.println(query2);

		int rowNum2 = 0;
		while (rs2.next()) {
			rowNum2++;

			if (rowNum2 == 1) {

				String columnValue5Row1 = rs2.getString(5);
				System.out.println("Row 1, Column 5: " + columnValue5Row1);
				Assert.assertTrue(columnValue5Row1.contains("cod_confirmation_comms_v3_1"),
						"both expected and actual are not same");

				String columnValue7Row1 = rs2.getString(7);
				System.out.println("Row 1, Column 7: " + columnValue7Row1);
				Assert.assertTrue(columnValue7Row1.contains(testNumber), "both expected and actual are not same");
			}

			else if (rowNum2 == 2) {

				String columnValue5Row1 = rs2.getString(5);
				System.out.println("Row 1, Column 5: " + columnValue5Row1);
				Assert.assertTrue(columnValue5Row1.contains("Freeform"), "both expected and actual are not same");

				String columnValue7Row1 = rs2.getString(7);
				System.out.println("Row 1, Column 7: " + columnValue7Row1);
				Assert.assertTrue(columnValue7Row1.contains(testNumber), "both expected and actual are not same");

				String columnValue8Row1 = rs2.getString(8);
				System.out.println("Row 1, Column 8: " + columnValue8Row1);
				Assert.assertTrue(columnValue8Row1.contains("We're currently in the process of canceling your order."),
						"both expected and actual are not same");
			}
		}
		rs1.close();
		rs2.close();

//----------------------------------------DB PART1 END------------------------------------------------------	

	}

}
