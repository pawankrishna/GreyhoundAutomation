/*package Scenarios;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import GlobalReusables.GenericMethods;
import GlobalReusables.ObjectRepository;

public class HomePage extends GenericMethods
{

	static ObjectRepository ORHomePage = new ObjectRepository(System.getProperty("user.dir")+"/ObjectRepository/HomePage.xml");

	public void Search(int RowNo)
	{
		replaceTextofTextField(ORHomePage.getElement("Textbox_Search"), getCellData("Viewer.xls", "Document", RowNo, "SearchText"));// Enter value into the Search text box
		
		if(getCellData("Viewer.xls", "Document", RowNo, "SearchText").equalsIgnoreCase("yes"))
		{clickElement(By.xpath("//li[text()='"+getCellData("Viewer.xls", "Document", RowNo, "Title")+"']"));}
		else
		{clickElement(ORHomePage.getElement("Button_Search"));}
		
		String xpathPrefix = "//span[text()='Title']/ancestor::div[5]/following-sibling::div[1]//table";
		int NoOfRecords = driver.findElements(By.xpath(xpathPrefix)).size();

		for (int RecordDetails = 1; RecordDetails <= NoOfRecords; RecordDetails++) 
		{
			String details =  xpathPrefix +"["+RecordDetails+"]//div";

			if(getCellData("Viewer.xls", "Document", RowNo, "Title").trim().equalsIgnoreCase(getText(By.xpath(details)).trim()))
			{
				System.out.println("-----"+
			getText(By.xpath(details)));
			Assert_TwoValues(getText(By.xpath(details)).trim(), "Search FUnctionality", "Search", "Grid Record Title", "Verifying grid record title of the document", getCellData("Viewer.xls", "Document", RowNo, "Title").trim());
				clickElement(By.xpath(details));
				break;				
			}
		}

	}


	public boolean Favourite_TitleStatus(int RowNo)
	{
		//Below if condition is to check whether "Favourite Documents" is in expand view .
		if(getAttributeValue(ORHomePage.getElement("Image_Collapse"), "class").contains("expand"))
		{
			clickElement(ORHomePage.getElement("Image_Collapse"));
		}
		else
		{
			clickElement(ORHomePage.getElement("Image_Collapse"));

			clickElement(ORHomePage.getElement("Image_Collapse"));
		}

		//Below condtion is to check whether document is available in the "Favourite Documents" section of left panel, if title exists then it will return as "true" else it will return as "false"
		boolean TitleAvailability = false;
		String FavouriteXpath = "//div[@componentid='favoriteDcmntsGrid']/div[2]/table";
		int Favourites_ItemsSize = driver.findElements(By.xpath(FavouriteXpath)).size();
		for (int ItemSlNo = 1; ItemSlNo <= Favourites_ItemsSize; ItemSlNo++)
		{
			String FavouriteDetails = FavouriteXpath+"["+ItemSlNo+"]//td/div[1]";
			//System.out.println("----get text-----"+getText(By.xpath(FavouriteDetails)).trim());
			//System.out.println("---------"+getCellData("Viewer.xls", "Document", RowNo, "Title").trim());
			if(getCellData("Viewer.xls", "Document", RowNo, "Title").trim().equalsIgnoreCase(getText(By.xpath(FavouriteDetails)).trim()))
			{
				//System.out.println("88888888");
				TitleAvailability = true;
			}

		}

		return TitleAvailability;
	}

	// Below method will add the documets from favourites panel
	public void Favourites_Add(int RowNo)
	{
		if(!Favourite_TitleStatus(1))//composite condition 
		{
			//pauseExecution(5000);
			clickElement(ORHomePage.getElement("Link_AddFavourite"));
			pauseExecution(3000);
			Assert_TwoValues(ORHomePage.getElement("Label_Favourites"), "Adding to Favourites", "Favourites_Add", "Favourites Label", "Verifying Favourites message", getCellData("Viewer.xls", "Document", RowNo, "Title")+" added to favorites");
		}

		//MainReport("Adding to Favourites", "Favourites_Add", "Pass");
		pauseExecution(5000);
	}

	// Below method will remove the documets from favourites panel
	public void Favourites_Remove(int RowNo)
	{
		//System.out.println("inside remove"+Favourite_TitleStatus(1));
		
		
		if(Favourite_TitleStatus(1))
		{
			//pauseExecution(5000);
			clickElement(ORHomePage.getElement("Link_RemoveFavourite"));
			pauseExecution(3000);
			Assert_TwoValues(ORHomePage.getElement("Label_Favourites"), "Removing from Favourites", "Favourites_Remove", "Favourites Label", "Verifying Favourites message", getCellData("Viewer.xls", "Document", RowNo, "Title")+" removed from favorites");
		}
		
		//MainReport("Removing from Favourites", "Favourites_Remove", "Pass");
	}


	public boolean Subscribe_TitleStatus(int RowNo)
	{
		//Below if condition is to check whether "Favourite Documents" is in expand view .
		if(getAttributeValue(ORHomePage.getElement("Image_Collapse_Subscribe"), "class").contains("expand"))
		{
			clickElement(ORHomePage.getElement("Image_Collapse_Subscribe"));
		}
		else
		{
			clickElement(ORHomePage.getElement("Image_Collapse_Subscribe"));
			//pauseExecution(2000);
			clickElement(ORHomePage.getElement("Image_Collapse_Subscribe"));
		}


		//Below condtion is to check whether document is available in the "Favourite Documents" section of left panel, if title exists then it will return as "true" else it will return as "false"
		boolean SubscribeStatus = false;
		String SubscribeXpath = "//div[@componentid='subscribedDcmntsGrid']/div[2]/table";
		int Favourites_ItemsSize = driver.findElements(By.xpath(SubscribeXpath)).size();
		for (int ItemSlNo = 1; ItemSlNo <= Favourites_ItemsSize; ItemSlNo++)
		{
			String SubscribeDetails = SubscribeXpath+"["+ItemSlNo+"]//td/div[1]";
			if(getCellData("Viewer.xls", "Document", RowNo, "Title").trim().equalsIgnoreCase(getText(By.xpath(SubscribeDetails)).trim()))
			{
				SubscribeStatus = true;
			}
		}
		return SubscribeStatus;
	}


	// Below method will subscribe the documents
	public void Subscribe(int RowNo)
	{

		if(!Subscribe_TitleStatus(1))
		{
			//pauseExecution(5000);
			clickElement(ORHomePage.getElement("Link_Subscribe"));
			pauseExecution(3000);
			Assert_TwoValues(ORHomePage.getElement("Label_Favourites"), "Subscribing Dcoument", "Subscribe", "SubScribe Label", "Verifying Subscribe message", "You are now subscribed to "+getCellData("Viewer.xls", "Document", RowNo, "Title"));
		}

		//MainReport("Subscribe  Document", "Subscribe", "Pass");
		pauseExecution(5000);
	}

	// Below method will un jsubscribe the documents
	public void UnSubscribe(int RowNo)
	{
		//System.out.println("inside unsubscribe");
		pauseExecution(3000);
		if(Subscribe_TitleStatus(1))
		{
			clickElement(ORHomePage.getElement("Link_UnSubscribe"));
			pauseExecution(3000);
			Assert_TwoValues(ORHomePage.getElement("Label_Favourites"), "Unsubscribe Dcoument", "UnSubscribe", "Unsubscribe Label", "Verifying Unsubscribe message", "You are unsubscribed from "+getCellData("Viewer.xls", "Document", RowNo, "Title"));
		}

		//MainReport("UnSubscribe  Document", "UnSubscribe", "Pass");
	}

	//Below method is to send mail.
	public void Mail(int RowNo)
	{
		clickElement(ORHomePage.getElement("Link_Mail"));
		SwitchToWindow();
		replaceTextofTextField(ORHomePage.getElement("Textbox_MailTo"), getCellData("Viewer.xls", "Document", RowNo, "EmailID"));
		clickElement(ORHomePage.getElement("Button_Send"));
		pauseExecution(2000);
		SwitchToWindow();
		Assert_TwoValues(ORHomePage.getElement("Label_mail"), "Mail","Email_sent" ,"mail_Label","Verifying email message" ,"Your email has been sent to "+getCellData("Viewer.xls", "Document", RowNo, "Title"));
		//MainReport("Mail","Email", "Pass");
	}

	//Below is the method to click on document information button
	public void DocumentInformation(int RowNo)
	{
		//System.out.println("document information");
		//pauseExecution(2000);
		driver.switchTo().frame(driver.findElement(ORHomePage.getElement("Frame_DocumentPreview")));
		if(elementExists(ORHomePage.getElement("Link_Header")))
		{

			clickElement(ORHomePage.getElement("Link_Header"));
			Assert_TwoValues(ORHomePage.getElement("Label_DocumentInformation"), "Verifying Header information", "DocumentInformation", "Doucment Information", "Verifying Header information", getCellData("Viewer.xls", "Document", RowNo, "Title"));
			driver.switchTo().defaultContent();
			//pauseExecution(2000);
			clickElement(ORHomePage.getElement("Link_Back"));
		}
		//MainReport("document Information","DocumentInformation", "Pass");

	}

	public void ReferenceMaterial(int RowNo)
	{
		//	pauseExecution(2000);
		driver.switchTo().frame(driver.findElement(ORHomePage.getElement("Frame_DocumentPreview")));
		if(elementExists(ORHomePage.getElement("Link_ReferenceMaterial")))
		{
			clickElement(ORHomePage.getElement("Link_ReferenceMaterial"));
			Assert_TwoValues(ORHomePage.getElement("Label_ReferenceMaterial"), "Verifying Reference Material", "ReferenceMaterial", "Reference Material", "Verifying Reference Material", getCellData("Viewer.xls", "Document", RowNo, "References_Text"));
			driver.switchTo().defaultContent();
			//pauseExecution(2000);
			clickElement(ORHomePage.getElement("Link_Back"));
		}
		//MainReport("Reference Material","ReferenceMaterial", "Pass");
	}

	public void DocumentationCriteria(int RowNo)
	{
		//pauseExecution(2000);
		driver.switchTo().frame(driver.findElement(ORHomePage.getElement("Frame_DocumentPreview")));
		if(elementExists(ORHomePage.getElement("Link_DocumentationCriteria")))
		{
			clickElement(ORHomePage.getElement("Link_DocumentationCriteria"));
			Assert_TwoValues(ORHomePage.getElement("Label_DocumentCriteria"), "Verifying Documentation Criteria", "DocumentationCriteria", "Documentation Criteria", "Verifying Documentation Criteria", getCellData("Viewer.xls", "Document", RowNo, "DocumentationCriteria_Text"));
			driver.switchTo().defaultContent();
			//pauseExecution(2000);
			clickElement(ORHomePage.getElement("Link_Back"));
		}
		//MainReport("Documentation Criteria","DocumentationCriteria", "Pass");

	}


	public void ContactUs(int RowNo)
	{
		clickElement(ORHomePage.getElement("Link_ContactUS"));
		replaceTextofTextField(ORHomePage.getElement("Textbox_Subject"), getCellData("Viewer.xls", "Document", RowNo, "ContactUs_Subject"));
		driver.switchTo().frame(driver.findElement(ORHomePage.getElement("Frame_ContactUS")));
		replaceTextofTextField(By.xpath("/html/body"), getCellData("Viewer.xls", "Document", RowNo, "ContactUs_Body"));
		driver.switchTo().defaultContent();
		clickElement(ORHomePage.getElement("Button_Send"));	
		pauseExecution(2000);
		Assert_TwoValues(By.id("messageLabel"), "ContactUS Functionality", "ContactUs", "Contact US Label", "Verifying Contact US message", getCellData("Viewer.xls", "Document", RowNo, "ContactUs_ConfirmationMessage"));
		//MainReport("ContactUs Functionality","ContactUs", "Pass");
	}

	public void Help(int RowNo)
	{
		clickElement(ORHomePage.getElement("Link_Help"));
		driver.switchTo().frame(driver.findElement(ORHomePage.getElement("Frame_Help")));
		String xpath_Help = "//h1[text()='CPG Viewer FAQ']/following-sibling::p";
		Assert_TwoValues(By.xpath("//h1[text()='CPG Viewer FAQ']"), "Help Functionality", "Help", "Help Label", "Verifying Help message", getCellData("Viewer.xls", "Document", RowNo, "Help_FAQ"));
		for (int HelpTopicNo = 1; HelpTopicNo <= getSize(By.xpath(xpath_Help)); HelpTopicNo++) 
		{
			String HelpTopicNoXpath = xpath_Help+"["+HelpTopicNo+"]/a[1]";
			String HelpTopic = getText(By.xpath(HelpTopicNoXpath));
			//System.out.println("------"+HelpTopic);
			clickElement(By.xpath(HelpTopicNoXpath));
			Assert_TwoValues(By.xpath("//a[text()='"+HelpTopic+"']"), "Help Functionality", "Help", "Help Topics", "Verifying Help Topic Label", HelpTopic);
		}
		//MainReport("Help Functionality","Help", "Pass");
	}

	public void Policy(int RowNo)
	{
		
		driver.switchTo().frame(driver.findElement(ORHomePage.getElement("Frame_CPG")));
		clickElement(ORHomePage.getElement("Link_CPG"));
		//System.out.println("----"+getText(By.xpath("//div[@id='glossaryContent']/following-sibling::h1")));
		Assert_TwoValues(ORHomePage.getElement("Label_CPGPolicyText"), "Verifying CPG Policy Text", "Policy", "CPG PolicyText Label", "Verifying CPG PolicyText", getCellData("Viewer.xls", "Document", RowNo, "CPGPolicyText"));
		driver.switchTo().defaultContent();
		clickElement(ORHomePage.getElement("Link_CPGBack"));
	     //MainReport("CPG Policy Text","CPGPolicyText", "Pass");
	}
    
	public void DocumentDetails(int RowNo)
	{
		
		driver.switchTo().frame(driver.findElement(ORHomePage.getElement("Frame_CPG")));
		clickElement(ORHomePage.getElement("Link_DocumentDetails"));
		//System.out.println("----"+getText(By.xpath("//div[@id='glossaryContent']/following-sibling::h1")));
		Assert_TwoValues(ORHomePage.getElement("Label_DocumentDeatilsText"), "Verifying Document Deatils Text", "DocumentDetail", "Document DetailText Label", "Verifying Document DetailText", getCellData("Viewer.xls", "Document", RowNo, "DocumentDeatilsText"));
		driver.switchTo().defaultContent();
		clickElement(ORHomePage.getElement("Link_CPGBack"));
	     //MainReport("CPG Policy Text","CPGPolicyText", "Pass");
}
}

*/