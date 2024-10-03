import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ClassFour {

	String url = "https://global.almosafer.com/en";
	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void myTest() {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.findElement(By.xpath("//button[normalize-space()='Kingdom of Saudi Arabia, SAR']")).click();

	}

	@Test()
	public void LanguageRandomly() throws InterruptedException {
		Random rand = new Random();
		String[] arabicCities = { "دبي", "جدة" };

		String[] englishCities = { "dubai", "jeddah", "riyadh", "amman" };

		int RandomArabicCity = rand.nextInt(arabicCities.length);

		int RandomEnglishCity = rand.nextInt(englishCities.length);
		
		String[] myWebSites = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };

		int randomNumber = rand.nextInt(myWebSites.length);

		driver.get(myWebSites[randomNumber]);

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

		HotelTab.click();

		String myWebsiteUrl = driver.getCurrentUrl();
		if (myWebsiteUrl.contains("ar")) {
			WebElement searchHotal = driver
					.findElement(By.xpath("//input[@placeholder='ابحث عن أماكن إقامة أو أماكن']"));
			searchHotal.sendKeys(arabicCities[RandomArabicCity] + Keys.ENTER);
//		WebElement results = driver.findElement(By.xpath("sc-phbroq-4 gGwzVo AutoComplete__List"));
//		List<WebElement> listitem = results.findElements(By.tagName("li"));
//
//		for (int i = 0; i < listitem.size(); i++) {
//			listitem.get(1).click();
//		}
			driver.findElement(By.xpath(
					"//button[@class='sc-jTzLTM eJkYKb sc-1vkdpp9-6 iKBWgG js-HotelSearchBox__SearchButton btn btn-primary btn-block']"))
					.click();
			WebElement myselect = driver.findElement(By.xpath("//select[@class='sc-kqEXUp xIZjM']"));
			Select selector = new Select(myselect);
			selector.selectByIndex(rand.nextInt(2));
			
			String reslutfound = driver.findElement(By.xpath("//span[@class='sc-ctwKVn imWanD']")).getText();
			Assert.assertEquals(reslutfound.contains("وجدنا"),true);
			
		} else if (myWebsiteUrl.contains("en")) {
			WebElement searchHotal = driver
					.findElement(By.xpath("//input[@placeholder='Search for properties or places']"));
			searchHotal.sendKeys(englishCities[RandomEnglishCity] + Keys.ENTER);
		
		driver.findElement(By.xpath(
				"//button[@class='sc-jTzLTM hQpNle sc-1vkdpp9-6 iKBWgG js-HotelSearchBox__SearchButton btn btn-primary btn-block']"))
				.click();

		Thread.sleep(2000);
		
		WebElement myselect = driver.findElement(By.xpath("//select[@class='sc-kqEXUp xIZjM']"));
		Select selector = new Select(myselect);
		selector.selectByIndex(rand.nextInt(2));
		}
		String reslutfound = driver.findElement(By.xpath("//span[@class='sc-ctwKVn imWanD']")).getText();
		Assert.assertEquals(reslutfound.contains("found"),true);
		
	}

	@AfterTest
	public void aftertest() {

	}

}
