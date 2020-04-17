package com.wip.pages;

import org.apache.logging.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.wip.util.BasePageObject;

public class Searchpage extends BasePageObject
{
	
	public Searchpage(WebDriver driver)
	{
		super(driver);
	}
	
	public static Logger log= LogManager.getLogger(LoginPage.class.getName());
	

	boolean flag=false;
	/* Web elements */
	By txtbox_searchBox_xpath= By.xpath("//input[@placeholder='Search products']");
	By txt_myaccount_xpath=By.xpath("//h1");
	By txt_result_xpath=By.xpath("//span[@class='ais-stats--nb-results']/strong");
	By lst_select_item_xpath= By.xpath("//div[@class='ais-infinite-hits ais-results-as-block']/div/div/a[@title='Canon PowerShot G7X II Compact Digital Camera']");
	By header_xpath= By.xpath("//h1");
	By btn_cart_id= By.id("AddToCartText");
	
	By lbl_myaccount_xpath= By.xpath("//span[text()='My Account']");
	By btn_logout_xpath= By.xpath("//a[text()='Logout']");
	
	By btn_toggle_myacc_id=By.id("myaccount-toggle");

	public boolean isMyAccountDisplayed() throws Exception 
	{
		try 
		{
			waitForAnElement(lbl_myaccount_xpath, 30);
			flag = isElementPresent(lbl_myaccount_xpath);
			
		}
		catch(Exception e)
		{
			throw new Exception("My account is displayed::" + isMyAccountDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}
	
	public void ClickOnMyAccountButtont() throws Exception
	{
		try 
		{
			waitForAnElement(lbl_myaccount_xpath, 30);
			javaScriptClick(lbl_myaccount_xpath);
		} 
		catch (Exception e) 
		{
			retryingFindClick(btn_toggle_myacc_id);
			throw new Exception("Click on the MY Account"  +e.getLocalizedMessage());
		}
	}
		
		public void MovetoLogout() throws Exception
		{
			try 
			{
				mouseover(lbl_myaccount_xpath);
				
				Actions abt= new Actions(driver);
				WebElement mySub=	driver.findElement(lbl_myaccount_xpath);
				abt.moveToElement(mySub);
				WebElement Logout= driver.findElement(btn_logout_xpath);
				abt.moveToElement(Logout).click().perform();
				System.out.println("Clicked on Logout");
				
				
				Thread.sleep(5000);
			
			} 
			catch (Exception e) {
				
				throw new Exception("Click on the MY Account"  +e.getLocalizedMessage());
			}
		
		
	}
	public String getMyAccountText() throws Exception
	{
		String name= null;
		try 
		{
			explicitWait(10);
			 name=getText(header_xpath);
			 System.out.println(name);
			
		}
		catch(Exception e)
		{
			throw new Exception("My Account is :" + getMyAccountText() + e.getLocalizedMessage());
		}
		
			return name;
	}
	public boolean isSearchTextDisplayed() throws Exception 
	{
		try 
		{
			
			waitForAnElement(txtbox_searchBox_xpath, 30);
			flag = isElementPresent(txtbox_searchBox_xpath);
			
		}
		catch(Exception e)
		{
			throw new Exception("Search textbox  is displayed::" + isSearchTextDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}
	
	public void enterProductNameToSearch(String product) throws Exception
	{
		
		try 
		{
			
			waitForAnElement(txtbox_searchBox_xpath, 30);
			retryingFindClick(txtbox_searchBox_xpath);
			
			clearAndEnterValueInTextBoxAndPressEnter(txtbox_searchBox_xpath, product);
			
		}
		catch(Exception e)
		{
			throw new Exception("Password is Entered ::" + e.getLocalizedMessage());
		}
	}
	public String getExtrcatedText() throws Exception
	{
		String name= null;
		 String extractedvalue=null;
		try 
		{
		//camera
			 name=getText(txt_result_xpath);
			 //extractedvalue= name.substring(name.length()-5);
			 System.out.println(name);
			
		}
		catch(Exception e)
		{
			throw new Exception("My Account is :" + getMyAccountText() + e.getLocalizedMessage());
		}
		
			return name;
	}
	public void  ScrolledTillVisible() throws Exception 
	{
		try 
		{
			explicitWait(4);
		WebElement el= driver.findElement(lst_select_item_xpath);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
			
			
		}
		catch(Exception e)
		{
			throw new Exception("Search textbox  is displayed and Entered the value:" + e.getLocalizedMessage());
		}
		
	
	}
	
		
		public boolean isSearchItemdisplayedAdnScrolledTillVisible() throws Exception 
		{
			try 
			{
				
				//scrollTillElementVisible(searchBox_xpath);
				waitForAnElement(lst_select_item_xpath, 30);
				flag = isElementPresent(lst_select_item_xpath);
				
			}
			catch(Exception e)
			{
				throw new Exception("Search textbox  is displayed and Entered the value:" + isSearchItemdisplayedAdnScrolledTillVisible() + e.getLocalizedMessage());
			}
			return flag;
		
		}
		
		public String  gettheAttributevalueofSearch(String value) throws Exception 
		{
			String name= null;
			try 
			{
				
				name= getAttributeValue(lst_select_item_xpath, value);
				
				
			}
			catch(Exception e)
			{
				throw new Exception("Search textbox  is displayed and Entered the value:" + isSearchItemdisplayedAdnScrolledTillVisible() + e.getLocalizedMessage());
			}
			return name;
		
		}
		
		
		public ProductDetailsPage ClickOnProduct() throws Exception
		{
			try 
			{
				javaScriptClick(lst_select_item_xpath);
			} 
			catch (Exception e) {
				
				throw new Exception("Click on the Product" + ClickOnProduct()  +e.getLocalizedMessage());
			}
			return new ProductDetailsPage(driver);
			
		}
		
		
		
		
		
		
		
		
		
	
}
