package GlobalReusables;


import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class ObjectRepository extends GenericMethods{

	By by = null;
	DocumentBuilderFactory dbf;
	DocumentBuilder db;
	Document document ;

	/**
	 * It is a constructor which will create a parser by taking file path as an argument. will have the same name of the class name 
	 * @param filename The path of the OR file.
	 * @author 
	 */
	public ObjectRepository(String filename)
	{
		try {
			dbf = DocumentBuilderFactory.newInstance();
			db   = dbf.newDocumentBuilder();
			document = db.parse(new File(filename));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("File not found.");
		}
	}

	/**
	 * This method will read the OR xml file and get locator and value for the given elementName.
	 * @param ElementName Key name of the element in the xml file.
	 * @return webElement.
	 * Node list will get the all the elements which has a start name of element
	 * @author 	 
	 */
	public   By getElement(String ElementName) {
		try 
		{
			NodeList nodeList = this.document.getElementsByTagName("Element");
			for(int x=0,size= nodeList.getLength(); x<size; x++) 
			{
				String FieldName=nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue();
				if(FieldName.equalsIgnoreCase(ElementName))
				{
					ElementName=nodeList.item(x).getTextContent();
					String LocatorType=nodeList.item(x).getAttributes().getNamedItem("locator").getNodeValue();
					if(LocatorType.equalsIgnoreCase("id"))
					{
						by =By.id(ElementName);
						break;
					}
					else if(LocatorType.equalsIgnoreCase("name"))
					{
						by  = By.name(ElementName); 
						break;
					}
					else if(LocatorType.equalsIgnoreCase("className"))
					{
						by=By.className(ElementName);
						break;
					}
					else if(LocatorType.equalsIgnoreCase("cssSelector"))
					{
						by=By.cssSelector(ElementName);
						break;
					}
					else if(LocatorType.equalsIgnoreCase("xpath")){

						by=By.xpath(ElementName);
						break;
					}
					else if(LocatorType.equalsIgnoreCase("linkText")){
						by=By.linkText(ElementName);
						break;
					}
					else if(LocatorType.equalsIgnoreCase("partialLinkText")){
						by=By.partialLinkText(ElementName);
						break;
					}
					else if(LocatorType.equalsIgnoreCase("tagName")){
						by=By.tagName(ElementName);
						break;
					}
					else 
					{
						by = null;
					}
				}

			}	return by;	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Element not found.");    
			return null;
		}
	} 




}

