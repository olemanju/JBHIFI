package com.wip.pages;

import org.apache.logging.log4j.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.wip.util.BasePageObject;



public class LoginPage extends BasePageObject
{

	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	public static Logger log= LogManager.getLogger(LoginPage.class.getName());
	
	//Page Object Model- Stored all the page related objects
	boolean flag=false;
	/* Web elements */
	
	By txt_username_id= By.id("CustomerEmail");
	By txt_password_id= By.id("CustomerPassword");
	By btn_login_id= By.id("customerloginbutton");		
	
	By searchBox_xpath= By.xpath("//input[@placeholder='Search products']");
	By select_item_xpath= By.xpath("//div[@class='ais-infinite-hits ais-results-as-block']/div/div/a[@title='Canon PowerShot G7X II Compact Digital Camera']");
	By header_xpath= By.xpath("//h1");
	By cart_id= By.id("AddToCartText-");
	By err_message_xpath= By.xpath("(//li[text()='Please check those details and try again.'])[1]/a");
	
	/**
 * @author Manjunath Ole
 * @return
 * @throws Exception
 * Checks Error message is displayed 
 */
	public boolean isErrorMessageDisplayed() throws Exception 
	{
		try 
		{
			waitForAnElement(err_message_xpath, 30);
			flag = isElementPresent(err_message_xpath);
			
		}
		catch(Exception e)
		{
			throw new Exception("Error Message  is displayed::" + isUsernameDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}
	/**
 * @author Manjunath Ole
 * @return
 * @throws Exception
 * Stores the error message
 */
	public String getErrorMessage() throws Exception
	{
		String name= null;
		try 
		{
			waitForAnElement(err_message_xpath, 30);
			 name=getText(err_message_xpath);
			 System.out.println("The Error Message is  " + name);
			
		}
		catch(Exception e)
		{
			throw new Exception("The error Message is:" + getMyLoginText() + e.getLocalizedMessage());
		}
		
			return name;
	}
	
	public boolean isUsernameDisplayed() throws Exception 
	{
		try 
		{
			waitForAnElement(txt_username_id, 30);
			flag = isElementPresent(txt_username_id);
			
		}
		catch(Exception e)
		{
			throw new Exception("Username  is displayed::" + isUsernameDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}
	
	
	public void enterUsername(String username) throws Exception
	{
		
		try 
		{
			clearAndEnterValueInTextBox(txt_username_id, username);
			
		}
		catch(Exception e)
		{
			throw new Exception("Username  is Entered ::" + e.getLocalizedMessage());
		}
		
	}
	
	public boolean isPasswordDisplayed() throws Exception 
	{
		try 
		{
			//waitForAnElement(password_id, 30);
			flag = isElementPresent(txt_password_id);
			
		}
		catch(Exception e)
		{
			throw new Exception("Password  is displayed::" + isPasswordDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}
	
	
	public void enterPassword(String password) throws Exception
	{
		
		try 
		{
			clearAndEnterValueInTextBox(txt_password_id, password);
			
		}
		catch(Exception e)
		{
			throw new Exception("Password is Entered ::" + e.getLocalizedMessage());
		}
		
	}
	
	public boolean isLoginbuttonDisplayed() throws Exception 
	{
		try 
		{
			waitForAnElement(btn_login_id, 30);
			flag = isElementPresent(btn_login_id);
			
		}
		catch(Exception e)
		{
			throw new Exception("Login button is displayed::" + isLoginbuttonDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}
	
	public Searchpage clickOnLogin() throws Exception
	{
		try 
		{
			javaScriptClick(btn_login_id);
			explicitWait(6);
		} 
		catch (Exception e) {
			
			throw new Exception("FAILED CLICKING ON LOGIN " + clickOnLogin()  +e.getLocalizedMessage());
		}
		return new Searchpage(driver);
		
	}
	public String getMyLoginText() throws Exception
	{
		String name= null;
		try 
		{
			waitForAnElement(header_xpath, 30);
			 name=getText(header_xpath);
			 System.out.println("User has reached Login Page " + name);
			
		}
		catch(Exception e)
		{
			throw new Exception("My Account is :" + getMyLoginText() + e.getLocalizedMessage());
		}
		
			return name;
	}
	
	public void clickOnLoginButtonForInvalid() throws Exception
	{
		try 
		{
			javaScriptClick(btn_login_id);
			explicitWait(6);
		} 
		catch (Exception e) {
			
			throw new Exception("FAILED CLICKING ON LOGIN " +e.getLocalizedMessage());
		}
		
		
	}
	
	
}
