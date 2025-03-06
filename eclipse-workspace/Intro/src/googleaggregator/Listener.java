package googleaggregator;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener  implements ITestListener{



	@Override
    public void onTestSuccess(ITestResult result) {
        // Code to execute when a test passes
    	System.out.println("ScreenShot code 2"+ "   "+result.getName());

    	AllFunctions.captureScreenshot(result);
    	
    }
	
	
	
	
	
}
