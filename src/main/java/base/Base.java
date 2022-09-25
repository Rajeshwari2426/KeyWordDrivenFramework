package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	protected static WebDriver driver;
	protected static Properties properties;
	protected static FileInputStream configFile;
	protected static Workbook workbook;
	protected static org.apache.poi.ss.usermodel.Sheet sheet;
	protected static FileInputStream excelFile;
	protected static String locatorName;
	protected static String locatorValue;
	protected static String action;
	protected static String value;
	protected static KeywordLogic keywordLogic;

	public WebDriver init_Driver(String browserName) {
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		return driver;
	}

	public Properties init_Properties() {
		properties = new Properties();
		try {
			configFile = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
			properties.load(configFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	final static String File_Path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
			+ File.separator + "resources"  + File.separator + "KeywordData.xlsx";

}
