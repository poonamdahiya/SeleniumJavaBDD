package StepDefinitions;

import Utilities.ApplitoolsHelper;
import Utilities.PropertiesReader;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestSteps {
	
	private WebDriver driver = Hooks.driver;
	private WebDriverWait wait;
	private ApplitoolsHelper helper = new ApplitoolsHelper();
	
	
	public TestSteps() throws Exception {
		PropertiesReader propertiesReader = new PropertiesReader();
		this.wait = new WebDriverWait(driver, propertiesReader.getTimeout());
	}
	
	
	@Given("I am on \"(.*)\" page")
	public void navigateTo(String page) throws Exception {
		driver.get(PropertiesReader.getValue("url"));
		
	}
	
	
	@Then("I will take visual screenshot \"(.*)\"")
	public void visualValidation(String tag) throws Exception {
		if (PropertiesReader.getValue("isVisual").equals("true"))
		 helper.eyesCheck(tag);
		else{
			System.out.println("Ignore Visual Validation. Ensure you have added visual tag to the scenario.");
		}
	}

}