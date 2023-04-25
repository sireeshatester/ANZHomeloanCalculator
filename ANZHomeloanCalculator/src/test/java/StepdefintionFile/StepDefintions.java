package StepdefintionFile;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefintions {
	public WebDriver driver;
	WebElement livingexpenses;
	WebElement CalculateBtn;
	String findmessage="Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 035 500.";
	
	@Given("^I want use this URL and open the application$")
	public void i_want_use_this_URL_and_open_the_application() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(options);
		driver.get("https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/");
		driver.manage().window().maximize();
		Thread.sleep(1000);
	   
	}

	@When("^I want to click the details of applicationtype$")
	public void i_want_to_click_the_details_of_applicationtype() throws Throwable {
		driver.findElement(By.xpath("//input[@id='application_type_single']")).click();
	}
		

	@When("^enter the number of depenedents$")
	public void enter_the_number_of_depenedents() throws Throwable {
		WebElement dependentElement=driver.findElement(By.xpath("//select[@title=\"Number of dependants\"]"));//number of dependents
		Select dependentDropdown=new Select(dependentElement);
		List<WebElement> DropdownOptions=dependentDropdown.getOptions();
		for(WebElement Options:DropdownOptions) {
			System.out.println(Options.getText());
		}
		dependentDropdown.selectByVisibleText("0");
	}
	   

	@When("^select the type of Home BarrowType$")
	public void select_the_type_of_Home_BarrowType() throws Throwable {
		driver.findElement(By.xpath("//label[@for='borrow_type_home']")).click();
	   
	}

	@When("^enter the earnings of annaulIncome and OtherIncome$")
	public void enter_the_earnings_of_annaulIncome_and_OtherIncome() throws Throwable {
		driver.findElement(By.xpath("//input[@aria-labelledby=\"q2q1\"]")).sendKeys("80000");//your Earnings Income
		driver.findElement(By.xpath("//input[@aria-labelledby=\"q2q2\"]")).sendKeys("10000");
	    
	}

	@When("^enter the expenses of LivingExpenses and HomeLoanRepayments and OtherLoanRepayments$")
	public void enter_the_expenses_of_LivingExpenses_and_HomeLoanRepayments_and_OtherLoanRepayments() throws Throwable {
		WebElement livingexpenses=driver.findElement(By.xpath("//input[@aria-labelledby=\"q3q1\"]"));//Living Expenses
		livingexpenses.sendKeys("500");
		driver.findElement(By.xpath("//input[@aria-labelledby=\"q3q2\"]")).sendKeys("0");//Current Home Loan Repayments  
		driver.findElement(By.xpath("//input[@aria-labelledby=\"q3q3\"]")).sendKeys("100");//other loans Loan Repayments
	   
	}

	@When("^enter the expenses of MonthlyCommitments and CreditCard$")
	public void enter_the_expenses_of_MonthlyCommitments_and_CreditCard() throws Throwable {
		driver.findElement(By.xpath("//input[@aria-labelledby=\"q3q4\"]")).sendKeys("0");//other commitments
		driver.findElement(By.xpath("//input[@aria-labelledby=\"q3q5\"]")).sendKeys("10000");//credit card
	    
	}

	@Then("^I click the barrow button$")
	public void i_click_the_barrow_button() throws Throwable {
		WebElement CalculateBtn=driver.findElement(By.xpath("//button[contains(., 'Work out how much I could borrow')]"));
		CalculateBtn.click();
		Thread.sleep(1000);
	}

	@Then("^I clicks on the startover button$")
	public void i_clicks_on_the_startover_button() throws Throwable {
		driver.findElement(By.xpath("//button[@class=\"start-over\"]")).click();
	}

	@When("^I enter the expenses of LivingExpenses$")
	public void i_enter_the_expenses_of_LivingExpenses() throws Throwable {
		livingexpenses.sendKeys("1");//Living Expenses
		CalculateBtn.click();
		Thread.sleep(1000);
	}

	@Then("^I should see the error mesaage$")
	public void i_should_see_the_error_mesaage() throws Throwable {
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
	}


	
	
	
	
	
		
	}



