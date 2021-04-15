package StepDefinitions;
		
		import PageObjects.HomePage;
		import PageObjects.TestPage;
		import Utilities.ApplitoolsHelper;
		import Utilities.PropertiesReader;
		import com.applitools.eyes.selenium.Eyes;
		import com.applitools.eyes.selenium.fluent.Target;
		import cucumber.api.java.cs.A;
		import cucumber.api.java.en.Given;
		import cucumber.api.java.en.Then;
		import cucumber.api.java.en.When;
		import org.junit.Assert;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.support.ui.WebDriverWait;

public class TestSteps {
	
	private WebDriver driver = Hooks.driver;
	private WebDriverWait wait;
	private ApplitoolsHelper helper = new ApplitoolsHelper();
//	private Eyes eyes =Hooks.eyes;
	
	
	public TestSteps() throws Exception {
		
		PropertiesReader propertiesReader = new PropertiesReader();
		this.wait = new WebDriverWait(driver, propertiesReader.getTimeout());
	}
	
	
	@Given("I am on \"(.*)\" page")
	public void navigateTo(String page) {
		
		TestPage test = new TestPage(driver, wait);
//		home.homePageIsDisplayed();
//		home.clickLoginButton();
	}
	
	
	@Then("I will take visual screenshot \"(.*)\"")
	public void visualValidation(String tag) throws Exception {
	
		if (PropertiesReader.getValue("isVisual") == "true")
		 helper.eyesCheck(tag);
		else{
			System.out.println("Ignore Visual Validation. Ensure you have added visual tag to the scnario.");
		}
	}
	
//	@Then("I should be on \"(.*)\" page")
//	public void loginPage() {
//
//		HomePage home = new HomePage(driver, wait);
//		home.homePageIsDisplayed();
//		home.clickLoginButton();
//	}

}