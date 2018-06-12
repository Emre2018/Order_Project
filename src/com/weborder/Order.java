package com.weborder;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Order {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:/Users/Emre/Documents/selenium dependencies/drivers/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.navigate().to("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		// Thread.sleep(2000);

		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		// Thread.sleep(2000);
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		// Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"ctl00_menu\"]/li[3]/a")).click();

		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).click();

		// enter QUANTITY
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(random100());

		// enter USER NAME
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys(userName());

		// enter STREET, CITY, STATE
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("123 Any st");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Anytown");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys("Virginia");

		// random 5 digit number
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(random5Digit());

		// CARD TYPE and CARD NUMBER // "V for Visa", "M for MasterCard", "A for
		// American Express"

		String str = cardType();

		if (str.equals("V")) {
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0")).click();
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(visaCardNumber());
		} else if (str.equals("M")) {
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1")).click();
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(masterCardNumber());
		} else if (str.equals("A")) {
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_2")).click();
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(amexCardNumber());
		}

		// EXPIRATION DATE (mm/yy)
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys("07/18");

		// CLICK PROCESS
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();

		String expected = "New order has been successfully added.";
		String actual = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder\"]/tbody/tr/td/div/strong"))
				.getText();

		if (actual.contains(expected)) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
			System.out.println("Expected :\t" + expected);
			System.out.println("Actual :\t" + actual);

		}

		
		System.out.println("Hi Mehmet.");
		
	}

	public static String cardType() { // this code gives random Visa, MasterCard or American Express

		String randChars = "VMA";
		StringBuilder rand = new StringBuilder();
		Random rnd = new Random();

		while (rand.length() < 1) { // length of the random string.
			int index = (int) (rnd.nextDouble() * randChars.length());
			rand.append(randChars.charAt(index));
		}

		return rand.toString();
	}

	public static CharSequence masterCardNumber() { // this code gives 16 digit random masterCardNumber starting 5
		String randChars = "0123456789";
		StringBuilder rand = new StringBuilder();
		Random rnd = new Random();

		while (rand.length() < 16) { // 16 digits Master Card Number.
			int index = (int) (rnd.nextDouble() * randChars.length());
			rand.append(randChars.charAt(index));
		}

		return rand.replace(0, 1, "5");

	}

	public static CharSequence visaCardNumber() { // this code gives 16 digit random visaCardNumber starting 4
		String randChars = "0123456789";
		StringBuilder rand = new StringBuilder();
		Random rnd = new Random();

		while (rand.length() < 16) { // 16 digits Visa Card Number.
			int index = (int) (rnd.nextDouble() * randChars.length());
			rand.append(randChars.charAt(index));
		}

		return rand.replace(0, 1, "4");

	}

	public static CharSequence amexCardNumber() { // this code gives 15 digit random amexCardNumber starting 3
		String randChars = "0123456789";
		StringBuilder rand = new StringBuilder();
		Random rnd = new Random();

		while (rand.length() < 15) { // 15 digits amex Card Number.
			int index = (int) (rnd.nextDouble() * randChars.length());
			rand.append(randChars.charAt(index));
		}

		return rand.replace(0, 1, "3");

	}

	public static CharSequence random5Digit() { // this is for random ZIPCODE

		String randChars = "0123456789";
		StringBuilder rand = new StringBuilder();
		Random rnd = new Random();

		while (rand.length() < 5) { // length of the random string.
			int index = (int) (rnd.nextDouble() * randChars.length());
			rand.append(randChars.charAt(index));
		}

		return rand;
	}

	public static String userName() { // this code gives a pre-defined string of middle name
		String randChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder rand = new StringBuilder();
		Random rnd = new Random();

		while (rand.length() < 8) { // length of the random string.
			int index = (int) (rnd.nextDouble() * randChars.length());
			rand.append(randChars.charAt(index));
		}
		String randStr = rand.toString().toLowerCase();

		randStr = randStr.replace(randStr.substring(0, 1), randStr.substring(0, 1).toUpperCase());

		return "John " + randStr + " Smith";
	}

	public static String random100() { // this is for 1-100 random number generation
		double randNumber = Math.random() * 100;
		String randomNumber = (int) randNumber + 1 + "";
		return randomNumber;
	}

}
