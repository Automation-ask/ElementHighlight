package ui.ElementHighlight;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class HighLightWithJse {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:\\chrome exe new\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.cleartrip.com/");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//input[@id='OneWay']")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//input[@name='origin']")).sendKeys("Pune");
		List<WebElement> fromCityList = driver.findElements(By.xpath("//ul[@id='ui-id-1']/li/a"));
		selectCity(fromCityList, "Pune");
		driver.findElement(By.xpath("//input[@id='ToTag']")).sendKeys("Bangalore");
		List<WebElement> toCityList = driver.findElements(By.xpath("//ul[@id='ui-id-2']/li/a"));
		selectCity(toCityList, "Bangalore");
		driver.findElement(By.xpath("//input[@id='DepartDate']")).sendKeys("08/07/2018");
		new Actions(driver).sendKeys(Keys.ESCAPE).build().perform();

		driver.findElement(By.xpath("//select[@id='Adults']")).sendKeys("1");
		driver.findElement(By.xpath("//select[@id='Childrens']")).sendKeys("0");
		driver.findElement(By.xpath("//select[@id='Infants']")).sendKeys("0");

		WebElement element = driver.findElement(By.xpath("//input[@id='SearchBtn']"));
		highLight(element, driver);
		element.click();
	}

	public static void selectCity(List<WebElement> list, String city) {
		for (int k = 0; k < list.size(); k++) {
			if (list.get(k).getText().contains(city)) {
				list.get(k).click();
			}
		}
	}

	public static void highLight(WebElement element, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < 50; i++) {
			String bcgColor = element.getCssValue("backgroundColor");
			highLight_Element("rgb(255,255,0)", element, driver);
			highLight_Element(bcgColor, element, driver);
		}

	}

	public static void highLight_Element(String colour, WebElement element, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor = '" + colour + "'", element);

	}
}
