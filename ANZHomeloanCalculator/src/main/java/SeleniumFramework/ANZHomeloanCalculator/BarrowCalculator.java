package SeleniumFramework.ANZHomeloanCalculator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class BarrowCalculator {

	public static void main(String[] args) throws InterruptedException {
		String findmessage= "Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 035 500.";
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(options);
		driver.get("https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//input[@id='application_type_single']")).click();//application type
		WebElement dependentElement=driver.findElement(By.xpath("//select[@title=\"Number of dependants\"]"));//number of dependents
		Select dependentDropdown=new Select(dependentElement);
		List<WebElement> DropdownOptions=dependentDropdown.getOptions();
		for(WebElement Options:DropdownOptions) {
			System.out.println(Options.getText());
		}
		dependentDropdown.selectByVisibleText("0");
		
		driver.findElement(By.xpath("//label[@for='borrow_type_home']")).click();//Borrower type
		
		driver.findElement(By.xpath("//input[@aria-labelledby=\"q2q1\"]")).sendKeys("80000");//your Earnings Income
		driver.findElement(By.xpath("//input[@aria-labelledby=\"q2q2\"]")).sendKeys("10000");//Your other Income
		
		WebElement livingexpenses=driver.findElement(By.xpath("//input[@aria-labelledby=\"q3q1\"]"));//Living Expenses
		livingexpenses.sendKeys("500");
		driver.findElement(By.xpath("//input[@aria-labelledby=\"q3q2\"]")).sendKeys("0");//Current Home Loan Repayments  
		driver.findElement(By.xpath("//input[@aria-labelledby=\"q3q3\"]")).sendKeys("100");//other loans Loan Repayments
		driver.findElement(By.xpath("//input[@aria-labelledby=\"q3q4\"]")).sendKeys("0");//other commitments
		driver.findElement(By.xpath("//input[@aria-labelledby=\"q3q5\"]")).sendKeys("10000");//credit card
		
		WebElement CalculateBtn=driver.findElement(By.xpath("//button[contains(., 'Work out how much I could borrow')]"));
		CalculateBtn.click();
		Thread.sleep(1000);

		
		
		driver.findElement(By.xpath("//button[@class=\"start-over\"]")).click();//Clicking the ‘start over’ button clears the form.
		
		livingexpenses.sendKeys("1");//Living Expenses
		CalculateBtn.click();
		Thread.sleep(1000);
		
		String errormessage=driver.findElement(By.xpath("//div[@class=\"borrow__error__text\"]")).getText();
		System.out.println("The resultant errormessage is :"+errormessage);	
		if(errormessage.equalsIgnoreCase(findmessage)) {
			System.out.println("Error message correct");
		
		}
		else {
			System.out.println("Error message failed!");
			System.out.println("From Browser: " + errormessage);
			System.out.println("Epexceted Message: " + findmessage);
		}
		
		driver.close();
	}

}
