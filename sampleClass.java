package Scenarios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import GlobalReusables.GenericMethods;
import GlobalReusables.ObjectRepository;

public class sampleClass extends GenericMethods{

	/*static ObjectRepository ORHomePage = new ObjectRepository(System.getProperty("user.dir")+"/ObjectRepository/Login.xml");
	@BeforeSuite //The annotated method will be run before all tests in this suite have run. 
	public void Setup()
	{
		System.out.println("test");	
		Login();
	}
	
	@Test
	public void sampleMethod()
	{
		replaceTextofTextField(ORHomePage.getElement("Textbox_UserName"), getCellData("Viewer.xls", "Mercury", 1, "UserName"));
		
	}
	@AfterSuite
	public void CleanUp()
	{
		Logout();
	}*/
	
	public static void main(String args[]){
	       
		datedddd("MM/dd/yyyy",0);
	}
	
	public static String datedddd(String dateFormat, int NoOfIncrDays){
		   DateFormat dateF = new SimpleDateFormat("MM/dd/yyyy");
	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DATE, NoOfIncrDays);    
			return dateF.format(cal.getTime());
	        
	       
	       
	}

}
