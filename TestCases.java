package Scenarios;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.ExplicitGroup;

import AppReusables.AppReusables;
import GlobalReusables.GenericMethods;
import GlobalReusables.ObjectRepository;


public class TestCases extends GenericMethods{

	
	static ObjectRepository ORLoginPage = new ObjectRepository(System.getProperty("user.dir")+"/ObjectRepository/Login.xml");
	static ObjectRepository ORRoadsRewardsPage = new ObjectRepository(System.getProperty("user.dir")+"/ObjectRepository/HomePage.xml");
	static ObjectRepository ORBookATripPage = new ObjectRepository(System.getProperty("user.dir")+"/ObjectRepository/BookATrip.xml");
	
	//TestCase 1
	public void verifyTermsandConditionsRoadandRewards() throws Exception{
		Assert.assertEquals(driver.getTitle(), "Greyhound");
		clickElement(ORRoadsRewardsPage.getElement("MenuRoadsAndRewards"));
		Assert.assertEquals(driver.getTitle(), "Road-Rewards : Greyhound");
		ExplicitWait(ORRoadsRewardsPage.getElement("BtnSignup"), 30, "visibility", "");
		clickElement(ORRoadsRewardsPage.getElement("BtnSignup"));
		ExplicitWait(ORRoadsRewardsPage.getElement("TitleSignupRoadsandRewards"), 30, "visibility", "");
		Assert.assertEquals(getText(ORRoadsRewardsPage.getElement("TitleSignupRoadsandRewards")), "Sign up to Road Rewards");
		scrollDown();
		ExplicitWait(ORRoadsRewardsPage.getElement("LinkTermsConditions"), 30, "visibility", "");
		clickElement(ORRoadsRewardsPage.getElement("LinkTermsConditions"));
		
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
	    Assert.assertEquals(getText(ORRoadsRewardsPage.getElement("TitleTermsandConditions")), "Terms and Conditions");
	    driver.close();
	    driver.switchTo().window(tabs.get(0));
		
	}
	
	//TestCase 2
	public void verifySignupFuncWithInvalidEmailAddress() throws Exception{
		Assert.assertEquals(driver.getTitle(), "Greyhound");
		clickElement(ORRoadsRewardsPage.getElement("MenuRoadsAndRewards"));
		Assert.assertEquals(driver.getTitle(), "Road-Rewards : Greyhound");
		ExplicitWait(ORRoadsRewardsPage.getElement("BtnSignup"), 30, "visibility", "");
		clickElement(ORRoadsRewardsPage.getElement("BtnSignup"));
		ExplicitWait(ORRoadsRewardsPage.getElement("TitleSignupRoadsandRewards"), 30, "visibility", "");
		Assert.assertEquals(getText(ORRoadsRewardsPage.getElement("TitleSignupRoadsandRewards")), "Sign up to Road Rewards");
		
		replaceTextofTextField(ORRoadsRewardsPage.getElement("Textbox_UserName"), getCellData("Viewer.xls", "Mercury", 1, "UserName"));
		
	}
	
	//TestCase 3
	public void verifyBookATripFields() throws Exception{
		Assert.assertEquals(driver.getTitle(), "Greyhound");
		clickElement(ORBookATripPage.getElement("MenuBookATrip"));
		ExplicitWait(ORBookATripPage.getElement("TitleBookATrip"), 30, "Visibility", "");
		Assert.assertEquals(getText(ORBookATripPage.getElement("TitleBookATrip")), "BOOK A TRIP");
		
		isElementDisplayed(ORBookATripPage.getElement("TextFromLocation"));
		isElementDisplayed(ORBookATripPage.getElement("TextToLocation"));
		isElementDisplayed(ORBookATripPage.getElement("CalenDepartOn"));
		isElementDisplayed(ORBookATripPage.getElement("CalenAddReturnTrip"));
		isElementDisplayed(ORBookATripPage.getElement("DDLTotalPassengers"));
		AppReusables ar = new AppReusables();
		ar.selectValueFromLocationDdl(ORBookATripPage.getElement("TextFromLocation"), "Dallas", "Dallas, TX");
		ar.selectValueToLocationDdl(ORBookATripPage.getElement("TextToLocation"), "Austin", "Austin, TX");
		replaceTextofTextField(ORBookATripPage.getElement("CalenDepartOn"), "10/31/2016");
		KeyboardAction(Keys.TAB);
		KeyboardAction(Keys.ENTER);
		
		replaceTextofTextField(ORBookATripPage.getElement("CalenAddReturnTrip"), "11/03/2016");
		KeyboardAction(Keys.TAB);
		
		clickElement(ORBookATripPage.getElement("BtnSearch"));
		ExplicitWait(ORBookATripPage.getElement("TitleOutgoingTrip"), 40, "Visibility", "");
		Assert.assertEquals(getText(ORBookATripPage.getElement("TitleOutgoingTrip")), "Choose your outgoing trip");
		
		clickElement(ORBookATripPage.getElement("OutgoingTripGridRow"));
		ExplicitWait(ORBookATripPage.getElement("BtnOutgoingEconomyBookThis"), 40, "Visibility", "");
		clickElement(ORBookATripPage.getElement("BtnOutgoingEconomyBookThis"));
		
		ExplicitWait(ORBookATripPage.getElement("TitleReturnTrip"), 40, "Visibility", "");
		Assert.assertEquals(getText(ORBookATripPage.getElement("TitleReturnTrip")), "Choose your return trip");
		
		clickElement(ORBookATripPage.getElement("ReturnTripGridRow"));
		ExplicitWait(ORBookATripPage.getElement("BtnEconomyBookThis"), 40, "Visibility", "");
		clickElement(ORBookATripPage.getElement("BtnEconomyBookThis"));
		
		ExplicitWait(ORBookATripPage.getElement("TitleLastBitDetails"), 40, "Visibility", "");
		
		///Sign in with Roads n Rewards id
		//elisia.therese@greyhound.com

		///creamstone
		///greyhoundtest01@gmail.com//automation
	}
	
	//TestCase 6
	public void modifyTripDetailsSection() throws Exception{
		Assert.assertEquals(driver.getTitle(), "Greyhound");
		AppReusables ar = new AppReusables();
		ar.selectValueFromLocationDdl(ORBookATripPage.getElement("TextFromLocation"), "Dallas", "Dallas, TX");
		
		ar.selectValueToLocationDdl(ORBookATripPage.getElement("TextToLocation"), "Austin", "Austin, TX");
		replaceTextofTextField(ORBookATripPage.getElement("CalenDepartOn"), "11/02/2016");
		KeyboardAction(Keys.TAB);
		KeyboardAction(Keys.ENTER);
		
		replaceTextofTextField(ORBookATripPage.getElement("CalenAddReturnTrip"), "11/05/2016");
		KeyboardAction(Keys.TAB);
		
		clickElement(ORBookATripPage.getElement("BtnSearch"));
		ExplicitWait(ORBookATripPage.getElement("TitleOutgoingTrip"), 40, "Visibility", "");
		Assert.assertEquals(getText(ORBookATripPage.getElement("TitleOutgoingTrip")), "Choose your outgoing trip");
		
		Assert.assertEquals(getText(ORBookATripPage.getElement("TitleChooseOutgoingFrom")), "Dallas, TX");
		Assert.assertEquals(getText(ORBookATripPage.getElement("TitleChooseReturnFrom")), "Austin, TX");
		Assert.assertEquals(getText(ORBookATripPage.getElement("BtnEditYourTrip")), "EDIT YOUR TRIP");
		clickElement(ORBookATripPage.getElement("BtnEditYourTrip"));
		
		
		ar.selectValueFromLocationDdl(ORBookATripPage.getElement("TextFromLocation"), "New Bern", "New Bern, NC");
		isElementEnabled(ORBookATripPage.getElement("BtnEditReportUpdateSearchButton"));
		clickElement(ORBookATripPage.getElement("BtnEditReportUpdateSearchButton"));
		ExplicitWait(ORBookATripPage.getElement("TitleOutgoingTrip"), 40, "Visibility", "");
		
		Assert.assertEquals(getText(ORBookATripPage.getElement("TitleInGridForFromLocation")), "NEW BERN, NC");
		Assert.assertEquals(getText(ORBookATripPage.getElement("TitleInGridForToLocation")), "AUSTIN, TX");
		
	}
	
	public void loginBeforeBookingATrip() throws Exception{
		Assert.assertEquals(driver.getTitle(), "Greyhound");
		clickElement(ORBookATripPage.getElement("MenuBookATrip"));
		ExplicitWait(ORBookATripPage.getElement("TitleBookATrip"), 30, "Visibility", "");
        Assert.assertEquals(getText(ORBookATripPage.getElement("TitleBookATrip")), "BOOK A TRIP");
		isElementDisplayed(ORBookATripPage.getElement("TextFromLocation"));
		isElementDisplayed(ORBookATripPage.getElement("TextToLocation"));
		isElementDisplayed(ORBookATripPage.getElement("CalenDepartOn"));
		isElementDisplayed(ORBookATripPage.getElement("CalenAddReturnTrip"));
		isElementDisplayed(ORBookATripPage.getElement("DDLTotalPassengers"));
		replaceTextofTextField(ORBookATripPage.getElement("RoadRewardsUserId"), "elisia");
		replaceTextofTextField(ORBookATripPage.getElement("RoadRewardsPassword"), "elisia");
		Assert.assertEquals(getText(ORBookATripPage.getElement("RoadRewardsUserId")),"elisia");

	}

	
	//TestCase 9
	public void bookACompleteTrip() throws Exception{
		
		Assert.assertEquals(driver.getTitle(), "Greyhound");
		AppReusables ar = new AppReusables();
		ar.selectValueFromLocationDdl(ORBookATripPage.getElement("TextFromLocation"), getCellData("GreyHound.xls", "BookCompleteTrip", 1, "Departure"), getCellData("GreyHound.xls", "BookCompleteTrip", 1, "DepartureFullName"));
		
		ar.selectValueToLocationDdl(ORBookATripPage.getElement("TextToLocation"), getCellData("GreyHound.xls", "BookCompleteTrip", 1, "Destination"), getCellData("GreyHound.xls", "BookCompleteTrip", 1, "DestinationFullName"));
		replaceTextofTextField(ORBookATripPage.getElement("CalenDepartOn"), getDate("MM/dd/yyyy", 1));
		KeyboardAction(Keys.TAB);
		KeyboardAction(Keys.ENTER);
		
		replaceTextofTextField(ORBookATripPage.getElement("CalenAddReturnTrip"), getDate("MM/dd/yyyy", 4));
		KeyboardAction(Keys.TAB);
		
		ExplicitWait(ORBookATripPage.getElement("DDLTotalPassengers"), 30, "Visibility", "");
		KeyboardAction(Keys.ENTER);
		
		ExplicitWait(ORBookATripPage.getElement("ChbTravelOnWheelChair"), 30, "Visibility", "");
		
		ar.addPassengerDetails("adult-num",2);
		ar.addPassengerDetails("children-num",2);
		ar.addPassengerDetails("senior-num",2);
		
		clickElement(ORBookATripPage.getElement("ChbTravelOnWheelChair"));
		
		clickElement(ORBookATripPage.getElement("BtnChildrenWheelNumber"));
		ExplicitWait(ORBookATripPage.getElement("BtnAdultWheelNumber"), 30, "Visibility", "");
		clickElement(ORBookATripPage.getElement("BtnAdultWheelNumber"));
		
		clickElement(ORBookATripPage.getElement("BtnSearch"));
		
		ExplicitWait(ORBookATripPage.getElement("TitleOutgoingTrip"), 40, "Visibility", "");
		Assert.assertEquals(getText(ORBookATripPage.getElement("TitleOutgoingTrip")), "Choose your outgoing trip");
		
		Assert.assertEquals(getText(ORBookATripPage.getElement("TitleChooseOutgoingFrom")), getCellData("GreyHound.xls", "BookCompleteTrip", 1, "DepartureFullName"));
		Assert.assertEquals(getText(ORBookATripPage.getElement("TitleChooseReturnFrom")), getCellData("GreyHound.xls", "BookCompleteTrip", 1, "DestinationFullName"));
		
		clickElement(ORBookATripPage.getElement("OutgoingTripGridRow"));
		ExplicitWait(ORBookATripPage.getElement("BtnOutgoingEconomyBookThis"), 40, "Visibility", "");
		clickElement(ORBookATripPage.getElement("BtnOutgoingEconomyBookThis"));
		
		ExplicitWait(ORBookATripPage.getElement("TitleReturnTrip"), 40, "Visibility", "");
		Assert.assertEquals(getText(ORBookATripPage.getElement("TitleReturnTrip")), "Choose your return trip");
		
		clickElement(ORBookATripPage.getElement("ReturnTripGridRow"));
		ExplicitWait(ORBookATripPage.getElement("BtnEconomyBookThis"), 40, "Visibility", "");
		clickElement(ORBookATripPage.getElement("BtnEconomyBookThis"));
		
		ExplicitWait(ORBookATripPage.getElement("TitleLastBitDetails"), 40, "Visibility", "");
		Assert.assertTrue(isElementDisplayed(ORBookATripPage.getElement("TitleLastBitDetails")), "Element not present");
		
		replaceTextofTextField(ORBookATripPage.getElement("TxtCardHolderFirstName"), getCellData("GreyHound.xls", "BookCompleteTrip", 1, "FirstName"));
		replaceTextofTextField(ORBookATripPage.getElement("TxtCardHolderLastName"), getCellData("GreyHound.xls", "BookCompleteTrip", 1, "LastName"));
		
		replaceTextofTextField(ORBookATripPage.getElement("TxtcardNumber"), getCellData("GreyHound.xls", "BookCompleteTrip", 1, "CardNumber"));
		replaceTextofTextField(ORBookATripPage.getElement("TxtCardExp"), getCellData("GreyHound.xls", "BookCompleteTrip", 1, "ExpiryDate"));
		replaceTextofTextField(ORBookATripPage.getElement("TxtCardCCV"), getCellData("GreyHound.xls", "BookCompleteTrip", 1, "CVV"));
		
		ExplicitWait(ORBookATripPage.getElement("RadioBtnPrintYourBtnAtHome"), 40, "Visibility", "");
		clickElement(ORBookATripPage.getElement("RadioBtnPrintYourBtnAtHome"));
		
	}
}
