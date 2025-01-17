package generic;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest extends Libraries {

	public static WebDriver driver;

	protected ChromeOptions co = new ChromeOptions();

	@BeforeClass
	public void openBrowser() throws Exception {
		System.out.println("Your OS version-->" + System.getProperty("os.name"));
		String osname = System.getProperty("os.name");
		if (osname.toLowerCase().contains("linux")) {
//			System.setProperty("webdriver.chrome.driver", "");
		} else if (osname.toLowerCase().contains("windows 11")) {
			String filePathToSet = System.getProperty("user.dir") + "/driver/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", filePathToSet);
		} else {
			String filePathToSet = System.getProperty("user.dir") + "/driver/mac/chromedriver";
			System.setProperty("webdriver.chrome.driver", filePathToSet);
		}

		// Path to your Chrome user data directory (your existing profile with WhatsApp
		// Web logged in)
		String userDataDir = "C:/Users/DELL/AppData/Local/Google/Chrome/User Data";

		co.addArguments("user-data-dir=" + userDataDir); // Use your own user profile
		co.addArguments("profile-directory=Default");

		driver = new ChromeDriver(co);
		co.addArguments("--disable-notifications");

		System.out.println("Browser launched");
		driver.manage().window().maximize();
		System.out.println("browser maximized sucessfully");
		driver.get(Libraries.fetchPropertyValue("prodURL").toString());
		System.out.println("URL Launched");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}

	public void openReport() throws Exception {
		generic.Listener abcd = new generic.Listener();
		abcd.runAllureReportFolderCommand();
		Thread.sleep(8000);
		System.out.println("folder created");
//		abcd.generateReport();
	}

	@AfterClass
	public void closeBrowser() throws Exception {
		openReport();
		System.out.println("browser closed");
//		driver.close();
		driver.quit();

	}

}
