package generic;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.google.common.io.Files;

public class Listener extends BaseTest implements ITestListener {

	private boolean testFailed = false; 
	private StringBuilder failedTests = new StringBuilder(); 
	private StringBuilder passedTests = new StringBuilder(); 
	private StringBuilder screenshotPaths = new StringBuilder(); 


	@Override
	public void onTestFailure(ITestResult result) {
		testFailed = true; 
		failedTests.append(result.getName()).append("\n");

		
		TakesScreenshot ts = (TakesScreenshot) driver; 
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File("screenshot/" + result.getName() + ".png");

		try {
			Files.copy(source, destination);
			screenshotPaths.append(destination.getAbsolutePath()).append("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void onTestSuccess(ITestResult result) {
		passedTests.append(result.getName()).append("\n"); // Store passed test names
	}

//    @Override
//    public void onTestFinish(ITestResult result) {
//        // No changes needed for this method
//    }

	@Override
	public void onFinish(ITestContext context) {
		if (testFailed) {
			try {
				sendMail2(failedTests.toString(), screenshotPaths.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				sendMail1(passedTests.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// Method to send email when the test passed
	private void sendMail1(String passedTests) throws Exception {
		String subject = "Automation Test Report - PASSED";
		String body = "Hello Team,\nAll tests passed successfully!!\n\n";
		body += "Tests passed:\n\n" + passedTests;
		body += "You can access the report link here:  " + reportURL();
		body += "\n\nBest Regards,\nInfinite Locus QA Team";

		sendMail(subject, body, null);
	}

	// Method to send email when the test fails
	private void sendMail2(String failedTests, String screenshotPaths) throws Exception {
		String subject = "Automation Test Report - FAILED";
		String body = "Hello Team,\nSome of the test cases got failed:\n\n" + failedTests;
		body += "\nPlease find the attached screenshots and test automation report link.\n";
		body += "You can access the report link here:  " + reportURL();
		body += "\n\nBest Regards,\nInfinite Locus QA Team";

		sendMail(subject, body, screenshotPaths);
	}

	// Method to send the actual email (common for both success and failure)
	private void sendMail(String subject, String body, String screenshotPaths) {
		String to = "abhishek.kumar.gupta@infinitelocus.com"; 
		String from = "automationtestingil05@gmail.com";
		String host = "smtp.gmail.com"; 

		System.out.println("Sending email to: " + to);

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("automationtestingil05@gmail.com", "fdtm mkmq rpxk ebyg");
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setSubject(subject);
			message.setText(body);

			if (to != null && !to.isEmpty()) {
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			} else {
				System.out.println("No recipient address provided.");
				return;
			}

			if (screenshotPaths != null && !screenshotPaths.isEmpty()) {
				String[] screenshotFiles = screenshotPaths.split("\n");

				Multipart multipart = new MimeMultipart();
				
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setText(body);
				multipart.addBodyPart(messageBodyPart);

				
				for (String screenshotPath : screenshotFiles) {
					File screenshotFile = new File(screenshotPath);
					if (screenshotFile.exists()) {
						MimeBodyPart attachmentPart = new MimeBodyPart();
						FileDataSource source = new FileDataSource(screenshotFile);
						attachmentPart.setDataHandler(new DataHandler(source));
						attachmentPart.setFileName(screenshotFile.getName());
						multipart.addBodyPart(attachmentPart);
					}
				}

				
				message.setContent(multipart);
			}

			// Send email
			Transport.send(message);
			System.out.println("Email sent successfully");

		} catch (MessagingException e) {
			e.printStackTrace(); 
		}
	}

	public void generateReport() throws Exception {
		driver.get(Libraries.fetchPropertyValue("netlifyURl").toString());
		Thread.sleep(3000);
		WebElement loginOption = driver.findElement(By.xpath(Libraries.fetchPropertyValue("loginOptn")));
		loginOption.click();
		Thread.sleep(3000);
		WebElement email = driver.findElement(By.xpath(Libraries.fetchPropertyValue("id")));
		email.clear();
//		email.sendKeys("automationtestingil05@gmail.com");
		email.sendKeys("infinitelocustest1@gmail.com");
		Thread.sleep(2000);
		WebElement password = driver.findElement(By.xpath(Libraries.fetchPropertyValue("pass")));
		password.clear();
//		password.sendKeys("infinitelocustest@12345");
		password.sendKeys("Varanasi@23");
		Thread.sleep(1000);
		WebElement login = driver.findElement(By.xpath(Libraries.fetchPropertyValue("signIn")));
		login.click();
		Thread.sleep(8000);
		WebElement fileInput = driver.findElement(By.xpath(Libraries.fetchPropertyValue("fileUpload")));
		fileInput.click();
		Thread.sleep(2000);
		Robot robot = new Robot();

		// Use StringSelection to copy the file path to the clipboard
		String filePath = Libraries.fetchPropertyValue("file").toString();
		StringSelection selection = new StringSelection(filePath);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, null);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(4000);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_LEFT);
		robot.keyRelease(KeyEvent.VK_LEFT);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_LEFT);
		robot.keyRelease(KeyEvent.VK_LEFT);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(25000);
		WebElement optionBtn = driver.findElement(By.xpath(Libraries.fetchPropertyValue("optionButton")));
		optionBtn.click();
		Thread.sleep(500);
		WebElement deployBtn = driver.findElement(By.xpath(Libraries.fetchPropertyValue("deploySetting")));
		deployBtn.click();
		Thread.sleep(5000);
		WebElement site = driver.findElement(By.xpath(Libraries.fetchPropertyValue("siteDetails")));
		site.click();
		Thread.sleep(1000);
		WebElement changeSiteName = driver.findElement(By.xpath(Libraries.fetchPropertyValue("changeSite")));
		changeSiteName.click();
		Thread.sleep(1000);
		WebElement field = driver.findElement(By.xpath(Libraries.fetchPropertyValue("siteInput")));
		field.click();
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_BACK_SPACE);
		robot.keyRelease(KeyEvent.VK_BACK_SPACE);

		Date d = new Date();
		String timeDateStamp = d.toString().replace(":", "_").replace(" ", "_");
		field.sendKeys("zippeeautomationreport" + timeDateStamp);
		Thread.sleep(1000);
		WebElement saveBtn = driver.findElement(By.xpath(Libraries.fetchPropertyValue("saveButton")));
		saveBtn.click();
		Thread.sleep(4000);
		WebElement report = driver.findElement(By.xpath(Libraries.fetchPropertyValue("link")));
		report.click();
		Thread.sleep(5000);
		Set<String> wh = driver.getWindowHandles();
		Iterator itr = wh.iterator();
		String parent = (String) itr.next();
		String newTab = (String) itr.next();
		String newTab1 = (String) itr.next();
		
		driver.switchTo().window(parent);
		Thread.sleep(1000);
		System.out.println(driver.getCurrentUrl());
		
		driver.switchTo().window(newTab1);
		Thread.sleep(1000);
		System.out.println(driver.getCurrentUrl());
		
		driver.switchTo().window(newTab);
		Thread.sleep(1000);
		System.out.println(driver.getCurrentUrl());
		
		WebElement icon = driver.findElement(By.xpath(Libraries.fetchPropertyValue("iconButton")));
		icon.click();
		Thread.sleep(500);
		WebElement out = driver.findElement(By.xpath(Libraries.fetchPropertyValue("signout")));
		out.click();
		Thread.sleep(500);
		driver.switchTo().window(newTab1);
		Thread.sleep(2000);
		reportURL();
		System.out.println(reportURL());
		
		System.out.println(driver.getTitle());
		
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File("reportSS/reportSS_.png");
		Files.copy(src, des);

		
	}

	public String reportURL() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	public void runAllureReportFolderCommand() throws Exception {
		String command = Libraries.fetchPropertyValue("allureCommand").toString();

		try {
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			process.waitFor(); // Wait for the command to finish
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
