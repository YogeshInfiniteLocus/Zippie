package pomPages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import generic.BaseTest;
import generic.Libraries;
import generic.Listener;

public class POM_001_HappyFlow_COD extends BaseTest {

	@FindBy(xpath = "//input[@id=\"email\"]")
	WebElement emailTextbox;

	@FindBy(xpath = "//button[@id=\"continueLogin\"]")
	WebElement continueBtn;

	@FindBy(xpath = "//input[@id=\"password\"]")
	WebElement passwordTextbox;

	@FindBy(xpath = "(//button[text()=' Sign In '])[1]")
	WebElement firstSignInBtn;

	@FindBy(xpath = "//span[text()=' Dashboard ']")
	WebElement dashboardBtn;

	@FindBy(xpath = "//span[text()=' Client Dashboard ']")
	WebElement clientDashboardBtn;

	@FindBy(xpath = "//input[@class=\"search_seller\"]")
	WebElement searchTextbox;

	@FindBy(xpath = "//tbody[@class=\"sales_team_data\"]/tr[1]/td[1]")
	WebElement token;

	@FindBy(xpath = "//tbody[@class=\"sales_team_data\"]/tr[1]/td[3]")
	WebElement companyName;

	@FindBy(xpath = "//tbody[@class=\"sales_team_data\"]/tr[1]/td[10]")
	WebElement viewAsBtn;

	@FindBy(xpath = "(//input[@placeholder=\"Search Orders\"])[2]")
	WebElement searchOrderTextbox;

	@FindBy(xpath = "//li[@class=\"mb-1\"]/div")
	List<WebElement> listNumberEqualInSearchbox;

	@FindBy(xpath = "//a[@class=\"fa fa-bars fa-lg\"]")
	WebElement hamburgerMenu;

	@FindBy(xpath = "//a[@id=\"clone_order\"]")
	WebElement cloneOrder;

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

	@FindBy(xpath = "//span[text()=' Orders ']")
	WebElement OrdersBtn;

	@FindBy(xpath = "//span[text()=' Order Batches ']")
	WebElement OrdersBatchesBtn;

	@FindBy(xpath = "//a[text()='Create New Batch']")
	WebElement createNewBatchBtn;

	@FindBy(xpath = "(//a[@class=\"fa fa-bars fa-lg\"])[2]")
	WebElement hamburgerMenuSmall;

	@FindBy(xpath = "(//li[@class=\"addOrders\"])[1]")
	WebElement addOrders;

	@FindBy(xpath = "//div[@id=\"add_to_batch\"]")
	WebElement addToBatch;

	@FindBy(xpath = "//li[@filter_type=\"seller\"]")
	List<WebElement> sellerList;

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

	@FindBy(xpath = "//ul[@class=\"ezPagination\"]/li")
	List<WebElement> pageNumberlist;

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

	@FindBy(xpath = "//input[@id=\"sku\"]")
	WebElement skuTextbox;

	@FindBy(xpath = "//input[@id=\"sku1\"]")
	WebElement skuTextbox1;

	@FindBy(xpath = "//div[@class=\"LoginBoxWrapper\"]/div/input[@id=\"username\"]")
	WebElement emailLoginext;

	@FindBy(xpath = "//input[@id=\"password\"]")
	WebElement passwordLoginext;

	@FindBy(xpath = "//button[@id=\"login\"]")
	WebElement loginBtnLoginext;

	@FindBy(xpath = "(//ul[@class=\"inlineList noMargin\"])[2]/li[4]")
	WebElement searchBtnLoginext;

	@FindBy(xpath = "(//div[text()='Not Dispatched'])[1]")
	WebElement not;

	@FindBy(xpath = "(//input[@placeholder=\"Enter Order no.\"])[2]")
	WebElement searchOrderTexbox;

	@FindBy(xpath = "//div[@class=\"rows-container\"]/div[1]/div/div/div/div/span/span/span/input")
	WebElement checkboxLogiNext;

	@FindBy(xpath = "//button[@id=\"order--actionBar--markAs\"]")
	WebElement markAsBtn;

	@FindBy(xpath = "//div[@class=\"sc-fznzqM zvpxm\"]/div")
	List<WebElement> markAsList;

	@FindBy(xpath = "//span[text()='Confirm']")
	WebElement confirmLogiBtn;

	@FindBy(xpath = "//div[@id=\"modal-body-id\"]")
	WebElement selectReasonBox;

	@FindBy(xpath = "//div[@id=\"modal-body-id\"]/div[1]/div")
	WebElement selectReasonOption;

	@FindBy(xpath = "//div[@class=\"rows-container\"]/div[2]/div[7]/div[1]")
	WebElement numberOfAttempts;

	@FindBy(xpath = "//button[@id=\"Order-ReasonModal-button-save\"]")
	WebElement saveBtn;

	@FindBy(xpath = "//div[@id=\"modal-body-id\"]/div[1]/div/div/div[2]/div/div/div[1]/div/div/input")
	WebElement selectReasonTextBox;

	@FindBy(xpath = "//button[@id=\"order--actionBar--updateOrder\"]")
	WebElement updateBtn;

	@FindBy(xpath = "//div[text()='Override Status']")
	WebElement overrideStatusBtn;

	@FindBy(xpath = "//div[@title=\"Mark as Not Dispatched\"]")
	WebElement markAsNotDispatched;

	@FindBy(xpath = "//div[@class=\"rows-container\"]/div[2]/div[3]/div[1]")
	WebElement statusLogi;

	public POM_001_HappyFlow_COD() {
		PageFactory.initElements(driver, this);
	}

	Actions a = new Actions(driver);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

	public void verifyEmailTextboxAndPassword() {
		Assert.assertTrue(emailTextbox.isDisplayed(), "both expected and actual are not same");
		emailTextbox.sendKeys("tech@zfwhospitality.com");
		wait.until(ExpectedConditions.visibilityOf(continueBtn));
		Assert.assertTrue(continueBtn.isDisplayed(), "both expected and actual are not same");
		continueBtn.click();
		wait.until(ExpectedConditions.visibilityOf(passwordTextbox));
		Assert.assertTrue(passwordTextbox.isDisplayed(), "both expected and actual are not same");
		passwordTextbox.sendKeys("Aniket@567");
		continueBtn.click();
	}

	public void verifySignIn() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(firstSignInBtn));
		Assert.assertTrue(firstSignInBtn.isDisplayed(), "both expected and actual are not same");
		firstSignInBtn.click();
	}

	public void verifyDashboard() {
		wait.until(ExpectedConditions.visibilityOf(dashboardBtn));
		Assert.assertTrue(dashboardBtn.isDisplayed(), "both expected and actual are not same");
		dashboardBtn.click();
	}

	public void verifyClientDashboard() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(clientDashboardBtn));
		clientDashboardBtn.click();
		wait.until(ExpectedConditions.visibilityOf(searchTextbox));
		Assert.assertTrue(searchTextbox.isDisplayed(), "both expected and actual are not same");
		Thread.sleep(5000);
		searchTextbox.sendKeys("%");
		searchTextbox.sendKeys(Keys.RETURN);
	}

	public void verifyCompanyName() {
		wait.until(ExpectedConditions.visibilityOf(token));
		Assert.assertEquals(token.getText(), "wo3876556644");
		Assert.assertEquals(companyName.getText(), "Fabbox Okhla");
		a.sendKeys(Keys.ARROW_RIGHT).perform();
		wait.until(ExpectedConditions.visibilityOf(viewAsBtn));
		Assert.assertTrue(viewAsBtn.isDisplayed(), "both expected and actual are not same");
		viewAsBtn.click();
	}

	public void verifyViewAsDashboard() throws Exception {
		Set<String> wh = driver.getWindowHandles();
		Iterator itr = wh.iterator();
		String parent = (String) itr.next();
		String newTab = (String) itr.next();
		driver.switchTo().window(newTab);
		wait.until(ExpectedConditions.visibilityOf(searchOrderTextbox));
		Assert.assertTrue(searchOrderTextbox.isDisplayed(), "both expected and actual are not same");
		Thread.sleep(3000);
		searchOrderTextbox.sendKeys("testing0132");
		Thread.sleep(500);
		searchOrderTextbox.click();
		Thread.sleep(2000);
		listNumberEqualInSearchbox.get(0).click();
	}

	public void verifyOrderClone() {
		wait.until(ExpectedConditions.visibilityOf(hamburgerMenu));
		Assert.assertTrue(hamburgerMenu.isDisplayed(), "both expected and actual are not same");
		hamburgerMenu.click();
		wait.until(ExpectedConditions.visibilityOf(cloneOrder));
		cloneOrder.click();
		Set<String> wh = driver.getWindowHandles();
		Iterator itr = wh.iterator();
		String parent = (String) itr.next();
		String newTab = (String) itr.next();
		String newTab1 = (String) itr.next();
		driver.switchTo().window(newTab1);
	}

	public void verifyMsgReceived() throws Exception {
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

//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			Thread.sleep(1000);
		}
		Thread.sleep(5000);
		if (lastMsg0.contains("Order Confirmation")) {
			Assert.assertTrue(lastMsg0.contains("Order Confirmation"), "Expected message did not appear.");
			Assert.assertTrue(lastMsg0.contains(testNumber), "Expected test number not found in message.");
			Assert.assertTrue(lastMsg0.contains("Confirm Order"), "Expected 'Confirm Order' not found.");
			Assert.assertTrue(lastMsg0.contains("Cancel Order"), "Expected 'Cancel Order' not found.");
		} else {
			Assert.fail("The message 'Order Confirmation' was not found within 120 seconds.");
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

		Robot r = new Robot();

		launchEaseComAgain();
		createBatch();

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
		wait.until(ExpectedConditions.visibilityOf(closeBtn));
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
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOf(checkboxAtProcessing));
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
		enterEan();
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

//				String columnValue6Row4 = rs3.getString(6);
//				System.out.println("Row 4, Column 6: " + columnValue6Row4);
//				Assert.assertTrue(columnValue6Row4.contains("Order updated") || columnValue6Row4.contains("{}"),
//						"both expected and actual are not same");
			}
		}

		rs3.close();
		rs4.close();
// -----------------------------DB PART2 END------------------------------------------------------

		loginToLoginext();

		searchOrderTexbox.sendKeys(testNumber);

		pressEnterAndEscKey();
		markAsPickedup();

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

//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
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

		markAsAttemptedDelivery();

		driver.switchTo().window(parent01);

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

//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
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

		updateOrder();

		wait.until(ExpectedConditions.visibilityOf(searchBtnLoginext));
		searchBtnLoginext.click();

		pressEnterAndEscKey();

		markAsPickedup();

		driver.switchTo().window(parent01);
		Thread.sleep(500);

		
		String lastMsg3 = "";
		for (int i = 0; i < 120; i++) {
			firstMsgOnWhatsApp.click();

			wait.until(ExpectedConditions.visibilityOfAllElements(msgList));

			int size1 = msgList.size();
			lastMsg3 = msgList.get(size1 - 1).getText();

			System.out.println("Last Message: " + lastMsg3);

			if (lastMsg3.contains("Arriving Today")) {
				break;
			}

//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			Thread.sleep(1000);
		}
		Thread.sleep(5000);

		if (lastMsg3.contains("Arriving Today")) {
			Assert.assertTrue(lastMsg3.contains("Arriving Today"), "Expected message did not appear.");
			Assert.assertTrue(lastMsg3.contains(testNumber), "Expected test number not found in message.");
			Assert.assertTrue(lastMsg3.contains("Track your order"), "Expected 'Track your order' not found.");
			Assert.assertTrue(lastMsg3.contains("Contact Us"), "Expected 'Contact Us' not found.");
		} else {

			Assert.fail("The message 'Arriving Today' was not found within 2 minutes.");
		}
		driver.switchTo().window(newTab002);

// -------------------------------------DB PART5-------------------------------------------
		PreparedStatement ps10 = conn.prepareStatement(query1);
		ps10.setString(1, testNumber);
		ResultSet rs10 = ps10.executeQuery();

		System.out.println(query1);

		int rowNum10 = 0;
		while (rs10.next()) {
			rowNum10++;

			if (rowNum10 == 7) {

				String columnValue4Row7 = rs10.getString(4);
				System.out.println("Row 7, Column 4: " + columnValue4Row7);
				Assert.assertTrue(columnValue4Row7.contains("PICKEDUPNOTIFICATION"),
						"both expected and actual are not same");
			}
		}

		PreparedStatement ps11 = conn.prepareStatement(query6);
		ps11.setString(1, testNumber);
		ResultSet rs11 = ps11.executeQuery();

		System.out.println(query6);

		int row11Num = 0;
		while (rs11.next()) {
			row11Num++;

			if (row11Num == 4) {

				String columnValue3Row4 = rs11.getString(3);
				System.out.println("Row 4, Column 5: " + columnValue3Row4);
				Assert.assertTrue(columnValue3Row4.contains("arriving_today_comms_v3"),
						"both expected and actual are not same");
			}
		}

		rs10.close();
		rs11.close();
// -----------------------------DB PART5 END------------------------------------------------------

		markAsDelivered();

		driver.switchTo().window(parent01);
		Thread.sleep(500);

		String lastMsg4 = "";
		for (int i = 0; i < 120; i++) {
			firstMsgOnWhatsApp.click();

			wait.until(ExpectedConditions.visibilityOfAllElements(msgList));

			int size1 = msgList.size();
			lastMsg4 = msgList.get(size1 - 1).getText();

			System.out.println("Last Message: " + lastMsg4);

			if (lastMsg4.contains("Delivered")) {
				break;
			}

			Thread.sleep(1000);
		}
		Thread.sleep(5000);

		if (lastMsg4.contains("Delivered")) {
			Assert.assertTrue(lastMsg4.contains("Delivered"), "Expected message did not appear.");
			Assert.assertTrue(lastMsg4.contains(testNumber), "Expected test number not found in message.");
			Assert.assertTrue(lastMsg4.contains("Rate your experience"), "Expected 'Track your order' not found.");
		} else {

			Assert.fail("The message Delivered was not found within 2 minutes.");
		}

		driver.switchTo().window(newTab002);

//-------------------------------------DB PART6-------------------------------------------
		PreparedStatement ps13 = conn.prepareStatement(query1);
		ps13.setString(1, testNumber);
		ResultSet rs13 = ps13.executeQuery();

		System.out.println(query1);

		int rowNum13 = 0;
		while (rs13.next()) {
			rowNum13++;

			if (rowNum13 == 8) {

				String columnValue4Row8 = rs13.getString(4);
				System.out.println("Row 8, Column 4: " + columnValue4Row8);
				Assert.assertTrue(columnValue4Row8.contains("DELIVEREDNOTIFICATION"),
						"both expected and actual are not same");
			}
		}

		PreparedStatement ps14 = conn.prepareStatement(query6);
		ps14.setString(1, testNumber);
		ResultSet rs14 = ps14.executeQuery();

		System.out.println(query6);

		int row14Num = 0;
		while (rs14.next()) {
			row14Num++;

			if (row14Num == 5) {

				String columnValue3Row5 = rs14.getString(3);
				System.out.println("Row 5, Column 5: " + columnValue3Row5);
				Assert.assertTrue(columnValue3Row5.contains("order_delivered_comms_v3"),
						"both expected and actual are not same");
			}
		}

//		PreparedStatement ps15 = conn.prepareStatement(query3);
//		ps15.setString(1, testNumber);
//		ResultSet rs15 = ps15.executeQuery();
//		int rowNum15 = 0;
//		while (rs15.next()) {
//			rowNum15++;
//
//			if (rowNum15 == 1) {
//
//				String columnValue4Row1 = rs15.getString(4);
//				System.out.println("Row 1, Column 4: " + columnValue4Row1);
//				Assert.assertTrue(columnValue4Row1.contains("NULL"), "both expected and actual are not same");
//			}
//		}

//		PreparedStatement ps16 = conn.prepareStatement(query5);
//		ps16.setString(1, testNumber);
//		ResultSet rs16 = ps16.executeQuery();
//		int rowNum16 = 0;
//		while (rs16.next()) {
//			rowNum16++;
//
//			if (rowNum16 == 1) {
//
//				String columnValue4Row1 = rs16.getString(4);
//				System.out.println("Row 1, Column 4: " + columnValue4Row1);
//				Assert.assertTrue(columnValue4Row1.contains(testNumber), "both expected and actual are not same");
//
//				String columnValue6Row1 = rs16.getString(6);
//				System.out.println("Row 1, Column 6: " + columnValue6Row1);
//				Assert.assertTrue(columnValue6Row1.contains("1"), "both expected and actual are not same");
//			}
//
//		}

		rs13.close();
		rs14.close();
//		rs15.close();
//		rs16.close();
//-----------------------------DB PART6 END------------------------------------------------------

		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(searchBtnLoginext));
		searchBtnLoginext.click();
		pressEnterAndEscKey();
		Assert.assertEquals(numberOfAttempts.getText(), "2", "both expected and actual are not same");
	}

	public void pressEnterAndEscKey() throws Exception {
		Robot r = new Robot();
		Thread.sleep(500);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
		r.keyPress(KeyEvent.VK_ESCAPE);
		r.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(1000);

	}

	public void launchEaseComAgain() throws Exception {
		String url = "https://app.easyecom.io/V2/account/auth/login";
		driver.get(url);
		wait.until(ExpectedConditions.visibilityOf(emailTextbox));
		Assert.assertTrue(emailTextbox.isDisplayed(), "both expected and actual are not same");
		emailTextbox.sendKeys("delhicd5@zfwhospitality.com");
		wait.until(ExpectedConditions.visibilityOf(continueBtn));
		Assert.assertTrue(continueBtn.isDisplayed(), "both expected and actual are not same");
		continueBtn.click();
		wait.until(ExpectedConditions.visibilityOf(passwordTextbox));
		Assert.assertTrue(passwordTextbox.isDisplayed(), "both expected and actual are not same");
		passwordTextbox.sendKeys("Okhla@123");
		continueBtn.click();
		verifySignIn();
		driver.navigate().refresh();
		Thread.sleep(15000);
		driver.navigate().refresh();
		Thread.sleep(15000);
	}

	public void createBatch() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(OrdersBtn));
		Assert.assertTrue(OrdersBtn.isDisplayed(), "both expected and actual are not same");
		OrdersBtn.click();
		wait.until(ExpectedConditions.visibilityOf(OrdersBatchesBtn));
		Assert.assertTrue(OrdersBatchesBtn.isDisplayed(), "both expected and actual are not same");
		OrdersBatchesBtn.click();
		driver.navigate().refresh();
		Thread.sleep(2500);
		driver.navigate().refresh();
		Thread.sleep(2500);
		driver.navigate().refresh();
		Thread.sleep(2500);
		wait.until(ExpectedConditions.visibilityOf(hamburgerMenu));
		hamburgerMenu.click();
		wait.until(ExpectedConditions.visibilityOf(createNewBatchBtn));
		Assert.assertTrue(createNewBatchBtn.isDisplayed(), "both expected and actual are not same");
		createNewBatchBtn.click();
		Thread.sleep(3500);
		a.sendKeys(Keys.ARROW_RIGHT).perform();
		wait.until(ExpectedConditions.visibilityOf(hamburgerMenuSmall));
		hamburgerMenuSmall.click();
		wait.until(ExpectedConditions.visibilityOf(addOrders));
		addOrders.click();
		wait.until(ExpectedConditions.visibilityOf(addToBatch));
		a.moveToElement(addToBatch).build().perform();
		Thread.sleep(500);
		js.executeScript("document.querySelector(\"#selectOrders\").scrollBy(0,2000)");
		Thread.sleep(3000);
	}

	public void enterEan() throws Exception {
		Robot r = new Robot();
		wait.until(ExpectedConditions.visibilityOf(ean));
		a.doubleClick(ean).build().perform();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_C);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_C);
		Thread.sleep(2000);
		skuTextbox.click();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		wait.until(ExpectedConditions.visibilityOf(skuTextbox1));
		skuTextbox1.click();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(8000);
	}

	public void markAsPickedup() throws Exception {
		a.moveToElement(checkboxLogiNext).click().build().perform();
		wait.until(ExpectedConditions.visibilityOf(markAsBtn));
		markAsBtn.click();
		Thread.sleep(2500);
		try {
			for (int i = 0; i < markAsList.size() - 1; i++) {
				if (markAsList.get(i).getText().contains("Mark as Pickedup")) {
					markAsList.get(i).click();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(2500);
		wait.until(ExpectedConditions.visibilityOf(confirmLogiBtn));
		confirmLogiBtn.click();
		Thread.sleep(5000);
	}

	public void markAsAttemptedDelivery() throws Exception {
		Robot r = new Robot();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(searchBtnLoginext));
		searchBtnLoginext.click();
		pressEnterAndEscKey();
		a.moveToElement(checkboxLogiNext).click().build().perform();
		wait.until(ExpectedConditions.visibilityOf(markAsBtn));
		markAsBtn.click();
		Thread.sleep(2500);
		try {
			for (int i = 0; i < markAsList.size() - 1; i++) {
				if (markAsList.get(i).getText().contains("Mark as Attempted Delivery")) {
					markAsList.get(i).click();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(2500);
		wait.until(ExpectedConditions.visibilityOf(selectReasonBox));
		selectReasonOption.click();
		Thread.sleep(500);
		selectReasonTextBox.sendKeys("No");
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1500);
		saveBtn.click();
		wait.until(ExpectedConditions.visibilityOf(confirmLogiBtn));
		confirmLogiBtn.click();
		Thread.sleep(5000);
	}

	public void updateOrder() throws Exception {
		Robot r = new Robot();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(searchBtnLoginext));
		searchBtnLoginext.click();
		Thread.sleep(2000);
		pressEnterAndEscKey();
		a.moveToElement(checkboxLogiNext).click().build().perform();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(updateBtn));
		Thread.sleep(2000);
		Assert.assertEquals(numberOfAttempts.getText(), "1", "both expected and actual are not same");
		Thread.sleep(1500);
		updateBtn.click();
		Thread.sleep(500);
		a.moveToElement(overrideStatusBtn).build().perform();
		Thread.sleep(200);
		markAsNotDispatched.click();
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(selectReasonBox));
		selectReasonOption.click();
		Thread.sleep(500);
		selectReasonTextBox.sendKeys("Not");
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1500);
		saveBtn.click();
		wait.until(ExpectedConditions.visibilityOf(confirmLogiBtn));
		confirmLogiBtn.click();
		Thread.sleep(5000);
	}

	public void markAsDelivered() throws Exception {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(searchBtnLoginext));
		searchBtnLoginext.click();
		pressEnterAndEscKey();
		a.moveToElement(checkboxLogiNext).click().build().perform();
		wait.until(ExpectedConditions.visibilityOf(markAsBtn));
		markAsBtn.click();
		Thread.sleep(2500);
		try {
			for (int i = 0; i < markAsList.size() - 1; i++) {
				if (markAsList.get(i).getText().contains("Mark as Delivered")) {
					markAsList.get(i).click();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(2500);
		wait.until(ExpectedConditions.visibilityOf(confirmLogiBtn));
		confirmLogiBtn.click();
		Thread.sleep(2500);
	}

	public void loginToLoginext() throws Exception {
		driver.get("https://products.loginextsolutions.com/");
		Thread.sleep(8000);
//		wait.until(ExpectedConditions.visibilityOf(emailLoginext));
//		emailLoginext.clear();
//		Thread.sleep(1000);
//		emailLoginext.sendKeys("tech@zfwhospitality.com");
//		passwordLoginext.clear();
//		passwordLoginext.sendKeys("Aniket@567567");
//		loginBtnLoginext.click();
		wait.until(ExpectedConditions.visibilityOf(searchBtnLoginext));
		searchBtnLoginext.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(searchOrderTexbox));
	}

	public void markAsCancelled() throws Exception {
		Robot r = new Robot();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(searchBtnLoginext));
		searchBtnLoginext.click();
		pressEnterAndEscKey();
		a.moveToElement(checkboxLogiNext).click().build().perform();
		wait.until(ExpectedConditions.visibilityOf(markAsBtn));
		markAsBtn.click();
		Thread.sleep(2500);
		try {
			for (int i = 0; i < markAsList.size() - 1; i++) {
				if (markAsList.get(i).getText().contains("Mark as Cancelled")) {
					markAsList.get(i).click();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(2500);
		wait.until(ExpectedConditions.visibilityOf(selectReasonBox));
		selectReasonOption.click();
		Thread.sleep(500);
		selectReasonTextBox.sendKeys("Cus");
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1500);
		saveBtn.click();
		wait.until(ExpectedConditions.visibilityOf(confirmLogiBtn));
		confirmLogiBtn.click();
		Thread.sleep(5000);
	}
}
