package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class KeywordLogic extends Base {

	public void startExcecution(String sheetName) {
		try {
			excelFile = new FileInputStream(File_Path);
			workbook = WorkbookFactory.create(excelFile);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = workbook.getSheet(sheetName);
		int lastRow = sheet.getLastRowNum();
		System.out.println(lastRow);
		for (int i = 0; i < lastRow; i++) {
			try {
				int k = 0;
				String data = sheet.getRow(i + 1).getCell(k + 1).getStringCellValue().trim();
				System.out.println("Data" + data);
				if (!data.equalsIgnoreCase("NA")) {
					locatorName = data.split("=")[0].trim();
					System.out.println("LocatorName: " + locatorName);
					locatorValue = data.split("=")[1].trim();
					System.out.println("LocatorValue: " + locatorValue);
				}
				action = sheet.getRow(i + 1).getCell(k + 2).getStringCellValue().trim();
				System.out.println("Action:"+action);
				value = sheet.getRow(i + 1).getCell(k + 3).getStringCellValue().trim();
				System.out.println("value:"+value);
				switch (action) {
				case "open browser":
					init_Properties();

					if (value.isEmpty() || value.equals("NA")) {
						properties.getProperty("browser");
					} else {
						init_Driver(value);
					}

					break;
				case "enter url":
					driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
					driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
					if (value.isEmpty() || value.equals("NA")) {
						properties.getProperty("browser");
					} else {
						driver.get(value);
					}
					break;
				case "sendkeys":
					WebElement ele1 = driver.findElement(By.xpath(locatorValue));
					Thread.sleep(500);
					ele1.clear();
					ele1.sendKeys(value);
					Thread.sleep(500);
					break;
				case "click":
					WebElement ele2 = driver.findElement(By.xpath(locatorValue));
					Thread.sleep(500);
					ele2.click();
					break;

				case "quit":
					driver.quit();
					break;

				default:
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
