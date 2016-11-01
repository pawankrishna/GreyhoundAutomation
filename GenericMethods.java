package GlobalReusables;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class GenericMethods 
{
	static ObjectRepository objLogin = new ObjectRepository(System.getProperty("user.dir")+"/ObjectRepository/Login.xml");
	static String ReportDetails ="";
	static boolean dataExists =false;
	static int SlNo =1; 
	static int AssertionReport_SlNo =1;
	//public static String currentDateTime =null ;
	static boolean Assertion_HeaderExists =false;
	public String TitleStartTag ="<title>";
	public String TitleEndTag ="</title>";
	public String bodyStartTag ="<body>";
	public String bodyEndTag ="</body>";
	public String htmlStartTag = "<html>";
	public String tableBorderTag="<table border = \"1\">";
	public String htmlEndTag = "</html>";
	public String TrStartTag ="<tr>";
	public String TrEndTag ="</tr>";
	public String TdStartTag ="<td>";
	public String TdEndTag ="</td>";
	public String brTag ="<br>";
	public String LinkTag = "<a href=\"";
	public String EndTag =">";
	public String LinkEndTag = "</a>";
//	public String ReportPath = getPropertyValue("ReportPath");	
	public static WebDriver driver = null;
	public String Assertion_ImagePath = getPropertyValue("ReportPath")+"\\Assert_"+AssertionReport_SlNo+".jpeg"; 
	public String TestMethod_ImagePath = getPropertyValue("ReportPath")+"\\Assert_"+SlNo+".jpeg";

	/**
	 * @Description Below method is to enter details into the textbox after clearing the data available in the text field.
	 * @param by  Element identifier (id, name ....)
	 * @param text Details to be entered into the text field.	  
	 * @author 
	 *
	 */
	public void replaceTextofTextField(By by, String text) 
	{
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(text);
	}

	
	
	/**
	 * @Description Below method is to click a field in the application.
	 * @param by Element identifier (id, name ....)
	 * @author 
	 * @throws InterruptedException 
	 * 
	 */
	public void clickElement(By by)
	{
		WebElement element = driver.findElement(by);
		element.click();
	}

	public String getAttributeValue(By by, String Attribute)
	{
		String AttributeValue = driver.findElement(by).getAttribute(Attribute);
		return AttributeValue;
	}
	
	/**
	 * @Description Below method is to select value from a dropdown
	 * @param by Element identifier (id, name ....)
	 * @param optionVisibleText Select text which is visible in the crop-down
	 * @author 
	 * 
	 */
	public void selectOptionFromDropDown(By by, String optionVisibleText) 
	{
		Select objSelect = new Select (driver.findElement(by));	
		objSelect.selectByVisibleText(optionVisibleText);
	}


	/**
	 * @Description Below method is fetch details from excel workbook
	 * @param workbookpath Name of the Excel workbook from which details should be fetched.
	 * @param sheetName Name of the Excel sheet from which details should be fetched.
	 * @param RowNo Row number in the excel sheet from which details should be fetched.
	 * @param colName Column Name in the Excel sheet
	 * @return It returns the value which is fetched from the excel sheet.
	 * @author 
	 * 
	 */
	public String getCellData(String workbookpath,String sheetName,int RowNo,String colName) 
	{
		workbookpath = System.getProperty("user.dir")+"/TestData/"+workbookpath;
		String cellContent=null;
		int cell = 0;
		try{
			cell=getColoumnIndex(workbookpath,colName, sheetName);
		}catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
		File file=new File(workbookpath);
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet=wb.getSheet(sheetName);
		Row row=sheet.getRow(RowNo);
		Cell cel=row.getCell(cell);
		cel.setCellType(Cell.CELL_TYPE_STRING);
		cellContent=cel.getStringCellValue().toString();
		return cellContent;
	}
	
	/**
	 * How to read file in java -FileInputStream//In Java, FileInputStream is a bytes stream class that’s used to read bytes from file
	 * @Description Below method is to get the column Index in the excel sheet of Excel workbook 
	 * @param workbookpath Name of the Excel workbook from which details should be fetched.
	 * @param colName Column Name in the Excel sheet
	 * @param sheetName Name of the Excel sheet from which details should be fetched.
	 * @return It returns the index of column name in the excel sheet. 
	 * @throws FileNotFoundException
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @author 
	 * 
	 */
	public int getColoumnIndex(String workbookpath,String colName,String sheetName) throws FileNotFoundException, InvalidFormatException, IOException 
	{	 
		int colIndex=0;
		File file=new File(workbookpath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Workbook wb=null;
		try{
			wb= WorkbookFactory.create(fis);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		Sheet sheet=wb.getSheet(sheetName);
		Row row=sheet.getRow(0);

		for (colIndex = 0; colIndex < row.getPhysicalNumberOfCells(); colIndex++) {
			try{
				String cell=row.getCell(colIndex).getStringCellValue();
				if (cell != null) {
					if ( cell.equalsIgnoreCase(colName)) {
						break;
					}
				}else{
					System.out.println("coloumn with name: "+colName+" not found in "+sheetName+" table");
				}
			}
			catch(Exception e)
			{
				System.out.println("in catch block"+e.getMessage());

			}
		}
		return colIndex;

	}

	/**
	 * @Description Below method is to perform login functionality in the application.
	 * @author 
	 * 
	 */
	public void Login()
	{
		String ChromeDriverpath = System.getProperty("user.dir")+"/Libraries/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", ChromeDriverpath);
		driver = new ChromeDriver();
		
		driver.get(getPropertyValue("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/**
	 * @Description Below to perform Logout functionality in the application. 
	 * @author 
	 */
	public void Logout()
	{

		//GenericMethods.clickElement(By.xpath("//input[@value='Logout']"));
		driver.quit();
	}

	/**
	 * @Description Below method is check whether element is displayed in the application.
	 * @param by Element identifier (id, name ....)
	 * @param FieldName Field Name should be provided.
	 * @author 
	 * 
	 */
	public boolean Field_Displayed(By by, String FieldName)
	{
		if(driver.findElement(by).isDisplayed())
		{
			System.out.println(FieldName + " is displayed");
		}
		else
		{
			System.out.println(FieldName + " is not displayed");
		}

		
		return driver.findElement(by).isDisplayed();

	}

	/**
	 * @Description Below method is to switch from one window to another window in the application.
	 * @author 
	 * 
	 */
	public void SwitchToWindow()
	{
		String child = null;
		for(String WindowName :driver.getWindowHandles())
		{
			//System.out.println(WindowName);
			child =WindowName;
		}
		//System.out.println(child+"-----child name");
		driver.switchTo().window(child);
	}

	/**
	 * @Description Below method is to perform Keyboard operation using Actions class in the webdriver.
	 * @param key Specify the keyboard action which need to be performed
	 * @author 
	 */
	public void KeyboardAction(Keys key)
	{
		Actions action = new Actions(driver);
		action.sendKeys(key).build().perform();

	}

	/**
	 * @Description Below method is to pause the execution. 
	 * @param TimeInMilliSeconds Specify time out in milliseconds
	 * @author 
	 */
	public void pauseExecution(int TimeInMilliSeconds)
	{
		try {
			Thread.sleep(TimeInMilliSeconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	
	/**
	 * @Description Below method is to perform Keyboard operation using Robo class in java.
	 * @author 
	 * 
	 */
	public void KeyboardAction_Robo()
	{
		try 
		{
		Robot objrobot;
		
			objrobot = new Robot();
			objrobot.keyPress(KeyEvent.VK_ENTER);
			objrobot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	//below method for reports
	public  void MainReport(String Scenarioname, String MethodName,String Result)
	{

		try {
			System.out.println("-- main report----"+getPropertyValue("ReportPath"));			
			
			File Directory = new File(getPropertyValue("ReportPath"));
			System.out.println("-- main report exists----"+Directory.exists());
			if(!Directory.exists())
			{
				Directory.mkdir();

			}
			File CustomReport = new File(getPropertyValue("ReportPath")+"/Report_"+getPropertyValue("DateTime")+".html");

			if(!CustomReport.exists())
			{
				CustomReport.createNewFile();

			}


			FileWriter fwriter = new FileWriter(CustomReport,true);
			BufferedWriter out = new BufferedWriter(fwriter);

			if(!dataExists)
			{
				out.write("Autom ation Test Scenario Report");
				ReportDetails = ReportDetails +HTMLTitle();
				ReportDetails = ReportDetails + TableHeader();	
				out.write(ReportDetails);
				dataExists = true;
			}
			out.write(TableData(Scenarioname,MethodName,Result));
			out.close();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	


	public String HTMLTitle()
	{

		String HTMLTitle;
		HTMLTitle = htmlStartTag+TitleStartTag+"Automation Test Report"+TitleEndTag+bodyStartTag+tableBorderTag;
		return HTMLTitle;
	}


	public String TableHeader()
	{

		String Report;
		Report = TrStartTag+TdStartTag+"Sl.No"+TdEndTag
				+TdStartTag+ "Scenario Name"+TdEndTag
				+TdStartTag+ "Method Name"+TdEndTag
				+TdStartTag+ "Result"+TdEndTag+TrEndTag;
		return Report;
	}

	public  String TableData(String ScenarioName, String MethodName, String Result)
	{
		
		String Data = null;
		if(Result.equalsIgnoreCase("Pass")){
		Data = brTag+TrStartTag+TdStartTag+SlNo +TdEndTag
				+TdStartTag+ ScenarioName+TdEndTag
				+TdStartTag+ MethodName+TdEndTag
				+TdStartTag+LinkTag+"AssertionReport_"+getPropertyValue("DateTime")+".html\""+EndTag+ Result+LinkEndTag+TdEndTag+TrEndTag;
		}
		else
		{
			Data = brTag+TrStartTag+TdStartTag+SlNo +TdEndTag
					+TdStartTag+ ScenarioName+TdEndTag
					+TdStartTag+ MethodName+TdEndTag
					+TdStartTag
					+LinkTag+"AssertionReport_"+getPropertyValue("DateTime")+".html\""+EndTag+ Result+LinkEndTag
					+LinkTag+TestMethod_ImagePath+"\""+EndTag+ "Image Link"+LinkEndTag+TdEndTag
					+TrEndTag;
			
				//	TdStartTag+LinkTag+Assertion_ImagePath+EndTag+ Result+TdEndTag+TrEndTag;
		}
		SlNo= SlNo+1;
		return Data;
		
	}
	
	static String Assertion_ReportDetails ="";
	public void Assertion_Report(String Scenarioname, String MethodName,String FieldName,String Description,String ExpectedValue,String ActualValue,String Result)
	{

		try {	
			File Directory = new File(getPropertyValue("ReportPath"));
			System.out.println("----"+Directory.exists());
			System.out.println("--==========+++--"+Directory.getPath());
			System.out.println("--$$$$$$--"+getPropertyValue("ReportPath"));
			
			if(!Directory.exists())
			{
				System.out.println("directory exists----");
				Directory.mkdir();

			}
			
			File CustomReport = new File(getPropertyValue("ReportPath")+"/AssertionReport_"+getPropertyValue("DateTime")+".html");

			//System.out.println(System.getProperty("user.dir")+"/Reports/"+currentDateTime+"/AssertionReport_"+currentDateTime+".html");
			if(!CustomReport.exists())
			{
				CustomReport.createNewFile();

			}


			FileWriter fwriter = new FileWriter(CustomReport,true);
			BufferedWriter out = new BufferedWriter(fwriter);

			if(!Assertion_HeaderExists)
			{
				Assertion_ReportDetails = Assertion_ReportDetails +Assertion_HTMLTitle();
				Assertion_ReportDetails = Assertion_ReportDetails + Assertion_TableHeader();	
				out.write(Assertion_ReportDetails);
				Assertion_HeaderExists = true;
			}
			out.write(Assertion_TableData(Scenarioname,MethodName,FieldName,Description,ExpectedValue,ActualValue,Result));
			out.close();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String Assertion_HTMLTitle()
	{

		String HTMLTitle = htmlStartTag+TitleStartTag+"Automation Test Report for Assertions"+TitleEndTag+bodyStartTag+tableBorderTag;
		return HTMLTitle;
	}


	public String Assertion_TableHeader()
	{

		String Report = TrStartTag+TdStartTag+"Sl.No"+TdEndTag
				+TdStartTag+ "Scenario Name"+TdEndTag
				+TdStartTag+ "Method Name"+TdEndTag
				+TdStartTag+ "FieldName"+TdEndTag
				+TdStartTag+ "Description"+TdEndTag
				+TdStartTag+ "Expected Value"+TdEndTag
				+TdStartTag+ "ActualValue"+TdEndTag
				+TdStartTag+ "Result"+TdEndTag+TrEndTag;
		return Report;
	}
	

	public String Assertion_TableData(String ScenarioName, String MethodName, String FieldName,String Description,String ExpectedValue,String ActualValue,String Result)
	{
		String Data = null;
		if(Result.equalsIgnoreCase("Pass")){
		Data = brTag+TrStartTag+TdStartTag+AssertionReport_SlNo +TdEndTag
				+TdStartTag+ ScenarioName+TdEndTag
				+TdStartTag+ MethodName+TdEndTag
				+TdStartTag+ FieldName+TdEndTag
				+TdStartTag+ Description+TdEndTag
				+TdStartTag+ ExpectedValue+TdEndTag
				+TdStartTag+ ActualValue+TdEndTag
				+TdStartTag+ Result+TdEndTag+TrEndTag;
		}
		else{
			
			Data = brTag+TrStartTag+TdStartTag+AssertionReport_SlNo +TdEndTag
					+TdStartTag+ ScenarioName+TdEndTag
					+TdStartTag+ MethodName+TdEndTag
					+TdStartTag+ FieldName+TdEndTag
					+TdStartTag+ Description+TdEndTag
					+TdStartTag+ ExpectedValue+TdEndTag
					+TdStartTag+ ActualValue+TdEndTag
				//	+TdStartTag+LinkTag+ReportPath+"\\Assert_"+AssertionReport_SlNo+".jpeg"+EndTag+ Result+TdEndTag+TrEndTag;
					+TdStartTag+LinkTag+Assertion_ImagePath+"\""+EndTag+ Result+LinkEndTag+TdEndTag+TrEndTag;
			
		}
		
		AssertionReport_SlNo = AssertionReport_SlNo+1;	
		return Data;
	}
	
	//Verifying whether field is displayed.
	public void Assert_FieldDisplayed(By by, String ScenarioName, String MethodName,String FieldName, String Description, boolean ExpectedValue)
	{
		boolean ActualValue = driver.findElement(by).isDisplayed();
		if(ActualValue == ExpectedValue)
		{
			Assertion_Report(ScenarioName, MethodName, FieldName,Description, ExpectedValue+"", ActualValue+"", "Pass");
		}
		else
		{
			Assertion_Report(ScenarioName, MethodName, FieldName,Description, ExpectedValue+"", ActualValue+"", "Fail");
		}

	}

	//Comparing Two Values with locators
	public void Assert_TwoValues(By by, String ScenarioName, String MethodName,String FieldName, String Description, String ExpectedValue)
	{
		String ActualValue = getText(by);
		System.out.println("Actual Value :"+ActualValue+"--Expected Value:"+ExpectedValue +"$$");
		if(ActualValue.equals(ExpectedValue))
		{
			Assertion_Report(ScenarioName, MethodName, FieldName,Description, ExpectedValue, ActualValue, "Pass");
		}
		else
		{
			
			Assertion_Report(ScenarioName, MethodName, FieldName,Description, ExpectedValue, ActualValue, "Fail");
			getScreenshot();
		}

	}

	public void getScreenshot_Method()
	{
		try {
		File Screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	
			FileUtils.copyFile(Screenshot, new File(TestMethod_ImagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Unable to capture screenshot.");
		}
	}
	public void getScreenshot()
	{
		try {
		File Screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	
			FileUtils.copyFile(Screenshot, new File(Assertion_ImagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Unable to capture screenshot.");
		}
	}
	//Comparing Two Strings without identifiers 
	public void Assert_TwoValues(String ActualValue, String ScenarioName, String MethodName,String FieldName, String Description, String ExpectedValue)
	{
		System.out.println("Actual Value :"+ActualValue+"--Expected Value:"+ExpectedValue +"$$");
		if(ActualValue.equals(ExpectedValue))
		{
			Assertion_Report(ScenarioName, MethodName, FieldName,Description, ExpectedValue, ActualValue, "Pass");
		}
		else
		{
			Assertion_Report(ScenarioName, MethodName, FieldName,Description, ExpectedValue, ActualValue, "Fail");
			getScreenshot();
		}

	}
	
	
	
	//get text from the field.
	public String getText(By by) 
	{
		String text = driver.findElement(by).getText();
		return text;
	}
	
	
	public boolean elementExists(By by)
	{
		boolean elementStatus = driver.findElements(by).size()!=0;
		return elementStatus;
	}
	
	public boolean isElementDisplayed(By by){
		boolean elemStatus =  driver.findElement(by).isDisplayed();
		return elemStatus;
	}
	
	public boolean isElementEnabled(By by){
		boolean elemStatus =  driver.findElement(by).isEnabled();
		return elemStatus;
	}
	
	public int getSize(By by) 
	{
		int text = driver.findElements(by).size();
		return text;
	}
	// Reading value from property file
	public String getPropertyValue(String PropertyName)
	{	
		String Name = null;
		try {

			Properties objproperty = new Properties();		
			String PropertyPath = System.getProperty("user.dir")+"/TestData/base.properties";
			FileInputStream obj_FIS = new FileInputStream(PropertyPath);
			objproperty.load(obj_FIS);
			Name = (String) objproperty.get(PropertyName); 


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Name;

	}

	public void setPropertyValue(String propertyName, String propertyValue)
	{
		try {
			Properties objproperty = new Properties();		
			String PropertyPath = System.getProperty("user.dir")+"/TestData/base.properties";
			FileInputStream obj_FIS;
			obj_FIS = new FileInputStream(PropertyPath);
			objproperty.load(obj_FIS);
			objproperty.setProperty(propertyName, propertyValue);
			FileOutputStream obj_FOS = new FileOutputStream(PropertyPath);
			objproperty.save(obj_FOS, "");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public enum conditionalWait 
	{ 
		VISIBILITY, INVISIBILITY, FRAME , PRESENCE ,POPUPALERT; 
	}
	
	public void ExplicitWait(By elementLocator,int maxTimeOut , String strConditionMode , String strName) throws Exception
	{
		try{

			conditionalWait mode = conditionalWait.valueOf(strConditionMode.toUpperCase());		 

			switch (mode) {
			case VISIBILITY:
				(new org.openqa.selenium.support.ui.WebDriverWait(driver, maxTimeOut))
				.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));			
				break;

			case INVISIBILITY:
				(new org.openqa.selenium.support.ui.WebDriverWait(driver, maxTimeOut))
				.until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
				break;

			case FRAME:
				//Reporter.log("gExplicitWait is called to wait for element :"+ strName);
				(new org.openqa.selenium.support.ui.WebDriverWait(driver, maxTimeOut))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(strName));
				new org.openqa.selenium.support.ui.WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Thomson Reuters/')]")));
				break;	

			case PRESENCE:
				(new org.openqa.selenium.support.ui.WebDriverWait(driver, maxTimeOut))
				.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
				break;	

			case POPUPALERT:	
				List<WebElement> popupElements = driver.findElements(elementLocator);
				int count=popupElements.size();
				if(count>0)
				{
					(new org.openqa.selenium.support.ui.WebDriverWait(driver, maxTimeOut))
					.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
					for(int j =0 ; j<count ; j++)
					{
						popupElements.get(j).click();
						Thread.sleep(1500);
					}
					break;
				}
			}
		}
		catch(Exception e){
			throw new Exception("exceptionMessage");	
		}

	}
	
	public void scrollDown(){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
	}

	public static String getDate(String dateFormat, int NoOfIncrDays){
		DateFormat dateF = new SimpleDateFormat("MM/dd/yyyy");
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, NoOfIncrDays);    
		return dateF.format(cal.getTime());
	}
	
}
     