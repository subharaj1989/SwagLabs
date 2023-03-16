package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import swaglabs.Hooks;

public class TestNGListeners implements ITestListener{

	@Override
	public void onTestFailure(ITestResult result)
	{
		String methodname=result.getName().toString().trim();
		
		WebDriver driver= Hooks.driver;
		
		utils.ScreenShot.takescreenShot(methodname,driver);
	}
}
