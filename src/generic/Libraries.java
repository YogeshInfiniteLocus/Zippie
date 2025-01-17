package generic;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class Libraries {
	
	public static WebDriver driver;

	public static String fetchPropertyValue(String key) throws Exception {
		 
		Properties p = new Properties();
		File f = new File("config/data");
		FileInputStream fis = new FileInputStream(f);
		p.load(fis);
		return (String) p.get(key);
	}

}
