package Scenarios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import GlobalReusables.GenericMethods;

public class MainClass extends GenericMethods {


	int excelRowNo = 1;
	@BeforeSuite //The annotated method will be run before all tests in this suite have run. 
	public void Setup()
	{
		System.out.println("test");	
		DateFormat dt = new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss");
		Date date = new Date();
		System.out.println("Date Format :: "+dt.format(date).toString());
		setPropertyValue("DateTime",dt.format(date).toString());

		setPropertyValue("ReportPath",System.getProperty("user.dir")+"\\Reports\\"+dt.format(date).toString());
	}

	@BeforeMethod
	public void BeforeTest()
	{
		Login();	
	}
	/*@Test
	public void TermsnCondition() throws Exception{
		TestCases objTest = new TestCases();
		objTest.verifyTermsandConditionsRoadandRewards();
	}*/
	
	/*@Test
	public void verifyInvalidEmailAddress() throws Exception{
		TestCases objTest = new TestCases();
		objTest.verifySignupFuncWithInvalidEmailAddress();
	}*/
	
	/*@Test
	public void verifyBookaTripFields() throws Exception{
		TestCases objTest = new TestCases();
		objTest.verifyBookATripFields();
	}*/
	
	
	
	/*@Test
	public void verifymodifyTripDetailsSection() throws Exception{
		TestCases objTest = new TestCases();
		objTest.modifyTripDetailsSection();
	}*/
	
	/*@Test
	public void verifyloginBeforeBookingATrip() throws Exception{
		TestCases objTest = new TestCases();
		objTest.loginBeforeBookingATrip();
	}*/
	
	/*@Test
	public void verifymodifyTripDetailsSection() throws Exception{
		TestCases objTest = new TestCases();
		objTest.modifyTripDetailsSection();
	}*/

	@Test
	public void verifyBookACompleteTrip() throws Exception{
		TestCases objTest = new TestCases();
		objTest.bookACompleteTrip();
	}
	
	@AfterMethod
	public void Signout(ITestResult result)
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			getScreenshot_Method();
			MainReport(result.getMethod().getDescription(),result.getMethod().getMethodName(), "Fail");
		}
		else
		{
			MainReport(result.getMethod().getDescription(),result.getMethod().getMethodName(), "Pass");
		}

		Logout();
	}
}
