package Utilities;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;

public class ApplitoolsHelper {
	public static Eyes eyes;
	public static EyesRunner runner;
	public static Configuration config = new Configuration();
	PropertiesReader propertiesReader = new PropertiesReader();
	
	public static Boolean IsVisualGrid () throws Exception {
		if (PropertiesReader.getValue("IsVisualGrid") == "true")
		{return true;}
		else
		{return false;}
	}
	
	
	public void eyesInitialization(WebDriver driver, Scenario scenario) throws Exception {
		if (PropertiesReader.getValue("IsVisualGrid")=="true") {
			runner = new VisualGridRunner(10);
		
		}else {
			runner = new ClassicRunner();
		}
		eyes = new Eyes(runner);
		eyesConfig(eyes);
		eyes.open(driver, PropertiesReader.getValue("appName"), scenario.getName(), new RectangleSize(800, 600));
	}
	public void eyesClose() throws Exception {
		try{
			eyes.closeAsync();
			TestResultsSummary allTestResults = runner.getAllTestResults(false);
			System.out.println(allTestResults);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			eyes.abortAsync();
		}
		
	}
	public static void eyesConfig(Eyes eyes) throws Exception {
		if (IsVisualGrid()) {
			// You can get your api key from the Applitools dashboard
			config.setApiKey("SnesqlCsa5CqgzGnn99JOk3V105L2P109HN1bl2XcZ6yfDWs110");
			
			// create a new batch info instance and set it to the configuration
			config.setBatch(new BatchInfo("Ultrafast Batch"));
			
			// Add browsers with different viewports
			config.addBrowser(800, 600, BrowserType.CHROME);
			config.addBrowser(700, 500, BrowserType.FIREFOX);
			config.addBrowser(1600, 1200, BrowserType.IE_11);
			config.addBrowser(1024, 768, BrowserType.EDGE_CHROMIUM);
			config.addBrowser(800, 600, BrowserType.SAFARI);
			
			// Add mobile emulation devices in Portrait mode
			config.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT);
			config.addDeviceEmulation(DeviceName.Pixel_2, ScreenOrientation.PORTRAIT);
		} else
			{
				config.setApiKey("SnesqlCsa5CqgzGnn99JOk3V105L2P109HN1bl2XcZ6yfDWs110");
				
				// create a new batch info instance and set it to the configuration
				config.setBatch(new BatchInfo("Ultrafast Batch"));
			}
		eyes.setConfiguration(config);
		
		
	}

	
	public void eyesCheck(String tag) throws Exception {
		eyes.check(Target.window().fully().withName(tag));
	}
	
	
}
