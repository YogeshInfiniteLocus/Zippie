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

public class POM_009_COD_WA_Reply_RA_Cancel_Order extends BaseTest{

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

	@FindBy(xpath = "//table[@id=\"orderItemsTables\"]/tbody/tr/td[4]")
	WebElement statusEasy;

	@FindBy(xpath = "//a[@class=\"breakWord order_detail_new_tab\"]")
	List<WebElement> orderNumberList;

	@FindBy(xpath = "//div[@class=\"field_label sub_menu_container_elem\"]/label/input")
	List<WebElement> orderNumberCheckboxList;

	@FindBy(xpath = "//div[@id=\"add_to_batch\"]")
	WebElement addBatchBtn;

	@FindBy(xpath = "(//button[@onclick=\"closeSelectOrdersModal()\"])[1]")
	WebElement closeBtn;

	@FindBy(xpath = "(//button[text()='Start Processing'])[1]")
	WebElement startsProcessingBtn;

	@FindBy(xpath = "//div[@class=\"field_label\"]//span/a")
	WebElement verifyOrder;

	@FindBy(xpath = "(//div[@id=\"process_orders_body\"]/div/div[4]/div/div[1]/div[1]/label/input)[1]")
	WebElement checkboxAtProcessing;

	@FindBy(xpath = "//div[@id=\"confirm_orders\"]")
	WebElement confirmBtn;

	@FindBy(xpath = "//ul[@class=\"nav nav-tabs order_tabs\"]/li[2]/a")
	WebElement print;

	@FindBy(xpath = "//div[@class=\"form-group\"]/div/span[contains(text(),'Ready For Print')]")
	WebElement readyPrint;

	@FindBy(xpath = "//div[@id=\"packing_station\"]")
	WebElement packagingStatio;

	@FindBy(xpath = "//tbody[@class=\"mainTable\"]/tr/td[4]")
	WebElement ean;

	@FindBy(xpath = "//li[@filter_type=\"seller\"]")
	List<WebElement> sellerList;

	@FindBy(xpath = "(//ul[@class=\"inlineList noMargin\"])[2]/li[4]")
	WebElement searchBtnLoginext;

	@FindBy(xpath = "//div[@class=\"rows-container\"]/div[2]/div[7]/div[1]")
	WebElement numberOfAttempts;

	@FindBy(xpath = "//div[@class=\"rows-container\"]/div[2]/div[3]/div[1]")
	WebElement statusLogi;

	@FindBy(xpath = "//*[@id=\"main\"]/div[3]/div/div[2]/div[3]/div//button")
	List<WebElement> BtnOnReattemtMsg;

	public POM_009_COD_WA_Reply_RA_Cancel_Order() {
		PageFactory.initElements(driver, this);
	}

	Actions a = new Actions(driver);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

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
		driver.switchTo().window(newTab3);
		Thread.sleep(500);

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
		String query3 = "select * from zorcs_active_order where ref_code = ?";
		String query4 = "select * from zfw_orders where reference_code = ?";
		String query5 = "select * from zorcs_processed_order where ref_code = ?";
		String query6 = "SELECT zo.reference_code, zo.payment_mode, zwcl.template_name, zwcl.payload, zwcl.added_on, zo.order_date\r\n"
				+ "FROM zfw_orders zo\r\n" + "JOIN zfw_wa_comm_logs zwcl ON zo.reference_code= zwcl.ref_code\r\n"
				+ "WHERE zwcl.ref_code= ?";

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
		}
		rs1.close();
		rs2.close();

//----------------------------------------DB PART1 END------------------------------------------------------		

		wait.until(ExpectedConditions.visibilityOf(statusEasy));
		Assert.assertEquals(statusEasy.getText(), "Assigned", "both expected and actual are not same");

		verification.launchEaseComAgain();
		verification.createBatch();

		System.out.println(sellerList.size());
		for (int i = 0; i < sellerList.size(); i++) {
			a.moveToElement(sellerList.get(i)).build().perform();
			if (sellerList.get(i).getText().contains("Fabbox Okhla")) {
				sellerList.get(i).click();
			}
		}
		Thread.sleep(5000);
		System.out.println(orderNumberCheckboxList.size());
		System.out.println(orderNumberList.size());
		js.executeScript("document.querySelector(\"#selectOrders\").scrollBy(0,-2000)");
		for (int i = 0; i < orderNumberCheckboxList.size(); i++) {
			a.moveToElement(orderNumberCheckboxList.get(i)).build().perform();
			String number = orderNumberList.get(i).getText();
			System.out.println(number);
			if (number.contains(testNumber)) {
				a.moveToElement(orderNumberCheckboxList.get(i)).click().build().perform();
			}
		}

		wait.until(ExpectedConditions.visibilityOf(addBatchBtn));
		addBatchBtn.click();
		Thread.sleep(6000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(6000);
		closeBtn.click();
		wait.until(ExpectedConditions.visibilityOf(startsProcessingBtn));
		startsProcessingBtn.click();
		Thread.sleep(5000);
		Set<String> wh1 = driver.getWindowHandles();
		Iterator itr1 = wh1.iterator();
		String parent1 = (String) itr1.next();
		String newTab01 = (String) itr1.next();
		String newTab02 = (String) itr1.next();
		driver.switchTo().window(newTab01);
		Thread.sleep(2000);
		driver.switchTo().window(newTab02);
		Thread.sleep(2000);
		a.moveToElement(checkboxAtProcessing).click().build().perform();
		System.out.println(checkboxAtProcessing.isDisplayed());
		Thread.sleep(2000);
		confirmBtn.click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(print));
		print.click();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(8000);
		wait.until(ExpectedConditions.visibilityOf(readyPrint));
		a.moveToElement(checkboxAtProcessing).click().build().perform();
		Thread.sleep(2000);
		packagingStatio.click();

		verification.enterEan();

		Set<String> wh2 = driver.getWindowHandles();
		Iterator itr2 = wh2.iterator();
		String parent01 = (String) itr2.next();
		String newTab001 = (String) itr2.next();
		String newTab002 = (String) itr2.next();
		driver.switchTo().window(newTab001).close();
		Thread.sleep(500);
		driver.switchTo().window(newTab002);
		Thread.sleep(500);

//-------------------------------------DB PART2-------------------------------------------
		Thread.sleep(20000);

		PreparedStatement ps4 = conn.prepareStatement(query4);
		ps4.setString(1, testNumber);
		ResultSet rs4 = ps4.executeQuery();

		System.out.println(query4);

		int rowNum4 = 0;
		while (rs4.next()) {
			rowNum4++;

			if (rowNum4 == 1) {

				String columnValue6Row1 = rs4.getString(6);
				System.out.println("Row 1, Column 7: " + columnValue6Row1);
				Assert.assertTrue(columnValue6Row1.contains(testNumber), "both expected and actual are not same");
			}
		}

		Thread.sleep(10000);

		PreparedStatement ps3 = conn.prepareStatement(query1);
		ps3.setString(1, testNumber);
		ResultSet rs3 = ps3.executeQuery();

		System.out.println(query1);

		int row3Num = 0;
		while (rs3.next()) {
			row3Num++;

			if (row3Num == 2) {

				String columnValue4Row2 = rs3.getString(4);
				System.out.println("Row 2, Column 4: " + columnValue4Row2);
				Assert.assertTrue(columnValue4Row2.contains("ORDERCREATIONNOTIFICATION")
						|| columnValue4Row2.contains("COMFIRM_ORDER"), "both expected and actual are not same");

				String columnValue6Row2 = rs3.getString(6);
				System.out.println("Row 2, Column 6: " + columnValue6Row2);
				Assert.assertTrue(
						columnValue6Row2.contains("Webhook processed successfully") || columnValue6Row2.contains("{}"),
						"both expected and actual are not same");
			} else if (row3Num == 3) {

				String columnValue4Row3 = rs3.getString(4);
				System.out.println("Row 3, Column 4: " + columnValue4Row3);
				Assert.assertTrue(columnValue4Row3.contains("ORDERCREATIONNOTIFICATION")
						|| columnValue4Row3.contains("COMFIRM_ORDER"), "both expected and actual are not same");
			} else if (row3Num == 4) {

				String columnValue4Row4 = rs3.getString(4);
				System.out.println("Row 4, Column 4: " + columnValue4Row4);
				Assert.assertTrue(columnValue4Row4.contains("ORDERCREATIONNOTIFICATION")
						|| columnValue4Row4.contains("COMFIRM_ORDER"), "both expected and actual are not same");

			}
		}

		rs3.close();
		rs4.close();
// -----------------------------DB PART2 END------------------------------------------------------

		verification.loginToLoginext();

		verification.searchOrderTexbox.sendKeys(testNumber);

		verification.pressEnterAndEscKey();
		verification.markAsPickedup();

		driver.switchTo().window(parent01);
		Thread.sleep(500);

		String lastMsg1 = "";

		for (int i = 0; i < 120; i++) {
			firstMsgOnWhatsApp.click();

			wait.until(ExpectedConditions.visibilityOfAllElements(msgList));

			int size1 = msgList.size();
			lastMsg1 = msgList.get(size1 - 1).getText();

			System.out.println("Last Message: " + lastMsg1);

			if (lastMsg1.contains("Arriving Today")) {
				break;
			}

			Thread.sleep(1000);
		}
		Thread.sleep(5000);

		if (lastMsg1.contains("Arriving Today")) {
			Assert.assertTrue(lastMsg1.contains("Arriving Today"), "Expected message did not appear.");
			Assert.assertTrue(lastMsg1.contains(testNumber), "Expected test number not found in message.");
			Assert.assertTrue(lastMsg1.contains("Track your order"), "Expected 'Track your order' not found.");
			Assert.assertTrue(lastMsg1.contains("Contact Us"), "Expected 'Contact Us' not found.");
		} else {

			Assert.fail("The message 'Arriving Today' was not found within 2 minutes.");
		}

		driver.switchTo().window(newTab002);

// -------------------------------------DB PART3-------------------------------------------
		PreparedStatement ps5 = conn.prepareStatement(query1);
		ps5.setString(1, testNumber);
		ResultSet rs5 = ps5.executeQuery();

		System.out.println(query1);

		int rowNum5 = 0;
		while (rs5.next()) {
			rowNum5++;

			if (rowNum5 == 5) {

				String columnValue4Row5 = rs5.getString(4);
				System.out.println("Row 5, Column 4: " + columnValue4Row5);
				Assert.assertTrue(columnValue4Row5.contains("PICKEDUPNOTIFICATION"),
						"both expected and actual are not same");
			}
		}

		PreparedStatement ps6 = conn.prepareStatement(query6);
		ps6.setString(1, testNumber);
		ResultSet rs6 = ps6.executeQuery();

		System.out.println(query6);

		int row6Num = 0;
		while (rs6.next()) {
			row6Num++;

			if (row6Num == 2) {

				String columnValue3Row2 = rs6.getString(3);
				System.out.println("Row 2, Column 5: " + columnValue3Row2);
				Assert.assertTrue(columnValue3Row2.contains("arriving_today_comms_v3"),
						"both expected and actual are not same");
			}
		}

		rs5.close();
		rs6.close();
// -----------------------------DB PART3 END------------------------------------------------------

		verification.markAsAttemptedDelivery();

		driver.switchTo().window(parent01);
		Thread.sleep(500);

		String lastMsg2 = "";
		for (int i = 0; i < 120; i++) {
			firstMsgOnWhatsApp.click();

			wait.until(ExpectedConditions.visibilityOfAllElements(msgList));

			int size1 = msgList.size();
			lastMsg2 = msgList.get(size1 - 1).getText();

			System.out.println("Last Message: " + lastMsg2);

			if (lastMsg2.contains("Re-attempt Confirmation")) {
				break;
			}

			Thread.sleep(1000);
		}
		Thread.sleep(5000);
		if (lastMsg2.contains("Re-attempt Confirmation")) {
			Assert.assertTrue(lastMsg2.contains("Re-attempt Confirmation"), "both expected and actual are not same");
			Assert.assertTrue(lastMsg2.contains(testNumber), "both expected and actual are not same.");
			Assert.assertTrue(lastMsg2.contains("Deliver Tomorrow"), "both expected and actual are not same");
			Assert.assertTrue(lastMsg2.contains("Cancel Order"), "both expected and actual are not same");
			Assert.assertTrue(lastMsg2.contains("Deliver on Later Date"), "both expected and actual are not same");
		} else {

			Assert.fail("The message 'Re-attempt Confirmation' was not found within 2 minutes.");
		}
		
		int size = BtnOnReattemtMsg.size();
		System.out.println(BtnOnReattemtMsg.get(size - 2).getText());
		BtnOnReattemtMsg.get(size - 2).click();
		Thread.sleep(4000);

		String lastMsg01 = "";

		for (int i = 0; i < 60; i++) {
			firstMsgOnWhatsApp.click();
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOfAllElements(msgList));

			int size1 = msgList.size();
			lastMsg01 = msgList.get(size1 - 1).getText();

			System.out.println("Last Message: " + lastMsg01);

			if (lastMsg01.contains("This message can't be shown on WhatsApp Web. Open WhatsApp on your phone to view.")) {
				break;
			}

			Thread.sleep(1000);
		}

		Assert.assertTrue(lastMsg01.contains("This message can't be shown on WhatsApp Web. Open WhatsApp on your phone to view."), "both expected and actual are not same");
		Thread.sleep(3000);
		
		driver.switchTo().window(newTab002);

// -------------------------------------DB PART4 -------------------------------------------
		PreparedStatement ps7 = conn.prepareStatement(query1);
		ps7.setString(1, testNumber);
		ResultSet rs7 = ps7.executeQuery();

		System.out.println(query1);

		int rowNum7 = 0;
		while (rs7.next()) {
			rowNum7++;

			if (rowNum7 == 6) {

				String columnValue4Row6 = rs7.getString(4);
				System.out.println("Row 5, Column 4: " + columnValue4Row6);
				Assert.assertTrue(columnValue4Row6.contains("NOTDELIVEREDNOTIFICATION"),
						"both expected and actual are not same");
			}
		}

		PreparedStatement ps8 = conn.prepareStatement(query6);
		ps8.setString(1, testNumber);
		ResultSet rs8 = ps8.executeQuery();

		System.out.println(query6);

		int row8Num = 0;
		while (rs8.next()) {
			row8Num++;

			if (row8Num == 3) {

				String columnValue3Row3 = rs8.getString(3);
				System.out.println("Row 3, Column 5: " + columnValue3Row3);
				Assert.assertTrue(columnValue3Row3.contains("reattempt_delivery_comms_v3"),
						"both expected and actual are not same");
			}
		}

		PreparedStatement ps9 = conn.prepareStatement(query3);
		ps9.setString(1, testNumber);
		ResultSet rs9 = ps9.executeQuery();

		System.out.println(query3);

		int rowNum9 = 0;
		while (rs9.next()) {
			rowNum9++;

			if (rowNum9 == 1) {

				String columnValue4Row1 = rs9.getString(4);
				System.out.println("Row 1, Column 4: " + columnValue4Row1);
				Assert.assertTrue(columnValue4Row1.contains(testNumber), "both expected and actual are not same");

				String columnValue6Row1 = rs9.getString(6);
				System.out.println("Row 1, Column 6: " + columnValue6Row1);
				Assert.assertTrue(columnValue6Row1.contains("1"), "both expected and actual are not same");
			}
		}

		PreparedStatement ps12 = conn.prepareStatement(query5);
		ps12.setString(1, testNumber);
		ResultSet rs12 = ps12.executeQuery();

		System.out.println(query5);

		int rowNum12 = 0;
		while (rs12.next()) {
			rowNum12++;

			if (rowNum12 == 1) {

				String columnValue4Row1 = rs12.getString(4);
				System.out.println("Row 1, Column 4: " + columnValue4Row1);
				Assert.assertTrue(columnValue4Row1.contains("NULL"), "both expected and actual are not same");
			}
		}

		rs7.close();
		rs8.close();
		rs9.close();
		rs12.close();
// -----------------------------DB PART4 END------------------------------------------------------

	}
}
