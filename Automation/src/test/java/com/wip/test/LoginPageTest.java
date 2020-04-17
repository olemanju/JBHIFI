package com.wip.test;

import java.util.logging.LogManager;

import org.apache.logging.log4j.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.wip.common.HIFIConstants;
import com.wip.pages.Homepage;
import com.wip.pages.LoginPage;
import com.wip.pages.MyAccountPage;
import com.wip.pages.PaymentScreenPage;
import com.wip.pages.ProductDetailsPage;
import com.wip.pages.Searchpage;
import com.wip.util.BaseTestObject;




public class LoginPageTest extends BaseTestObject
{

	//Fetching the values from the property file
	public String username = ObjProperty.getProperty("username");
	public String password = ObjProperty.getProperty("password");
	public String searchItem = ObjProperty.getProperty("searchItem");
	public  String searchItem_sel= ObjProperty.getProperty("selectItem");
	public  String invalid_username= ObjProperty.getProperty("invalid_username");
	public  String Invalid_password= ObjProperty.getProperty("invalid_password");
	
	//Creating the objects for the Classes
	Homepage objhomepage;
	LoginPage objloginpage;
	Searchpage objsearchpage;
	ProductDetailsPage objproductdescriptionpage;
	PaymentScreenPage objpaymentscreenpage;
	MyAccountPage objmyaccountpage;
	
	boolean flag=false;
	
	String home_page_title=null;
	String Login=null;
	String Enter_search_value=null;
	String model=null;
	 String SKU=null;
	 String price_val= null;
	  String instock_val= null;
	
	//@Parameters({"browserType"})
	/**
	 * @author Manjunath
	 * Below Test case coveres End to end flow Testcase
	 * 
	 * @throws Exception
	 */
	@Test(priority=0, enabled=true)
	public void verifyEndtoEndFloeOfPurchasingtheProduct() throws Exception
	{
		try 
		{
			objhomepage = new Homepage(driver);
			//Get the Title of the Page compare with Expexted value both are Matching
			home_page_title=objhomepage.verifyHomePageTitle();
			Assert.assertEquals(home_page_title, HIFIConstants.HOME_TITLE,"Title of the Page is not Matching");
			
			//Check My account object is displayed on the page
			flag = objhomepage.isMyAccountDisplayed();
		    Assert.assertTrue(flag, "MyAccount object is not displayed");
		    
		    //Click on Myaccount Navigation will go to Login Page
		   //Login Functionality will Cover here
		    objloginpage =objhomepage.clickOnMyAccount();
		    Login= objloginpage.getMyLoginText();
		    Assert.assertEquals(Login, HIFIConstants.LOGIN_TEXT_HEAD," Login header details are not Matching");
		    objloginpage.isUsernameDisplayed();
		    objloginpage.enterUsername(username);
		     objloginpage.isPasswordDisplayed();
		     objloginpage.enterPassword(password);
		    objsearchpage= objloginpage.clickOnLogin();
		    objsearchpage.explicitWait(5);
		      //Ends Login function
		   	    
		    //Verify Search Autotext is displayed
		    flag=objsearchpage.isSearchTextDisplayed();
		    Assert.assertTrue(flag, "Search Text box object is not displayed");
		    objsearchpage.explicitWait(5);
		    String header= objsearchpage.getMyAccountText();
		    System.out.println("MY account"+header);
		     System.out.println(searchItem);
		    
		     //Enter the Value in the search Box
		    objsearchpage.enterProductNameToSearch(searchItem);
		    objsearchpage.explicitWait(8);
		    
		    //Compare with results wherether Search text is present
		    Enter_search_value= objsearchpage.getExtrcatedText();
		    Assert.assertEquals(Enter_search_value, HIFIConstants.SEARCH_TEXT, " Search element is Missingis not matching");
		    
		    //Scroll the page Till element visible
		    objsearchpage.ScrolledTillVisible();
		
		    
		    //Get the attribute value of the element
		    String titleoftheProduct=objsearchpage.gettheAttributevalueofSearch("title");
		    System.out.println(titleoftheProduct);
		    flag=objsearchpage.isSearchItemdisplayedAdnScrolledTillVisible();
		    Assert.assertTrue(flag, "Search Text box object is not displayed");
		    
		    //Click on the product which is avilable on the page NOt first r Last one
		    objproductdescriptionpage=objsearchpage.ClickOnProduct();
		    objproductdescriptionpage.explicitWait(3);
		    objproductdescriptionpage.isTextDisplayed();
		    
		    //Compare the Name of Product is displayed on the screen 
		    String details=objproductdescriptionpage.nameOftheProduct();
		    Assert.assertEquals(details, HIFIConstants.SELECTEDITEM,"Searched Item is not displayed for Purchase");
		    
		    //Comapare the Model number of the product
		    model=objproductdescriptionpage.nameOftheModel();
		    Assert.assertEquals(model, HIFIConstants.MODEL_NUMBER,"Model Number mismatch");
		    
		   //Compare SKU Number of the Product
		    SKU=objproductdescriptionpage.nameOftheSKU();
		    Assert.assertEquals(SKU, HIFIConstants.SKU_NUMBER,"Sku number mismatch");
		    
		    //Compare the Price of teh product
		    price_val= objproductdescriptionpage.productPrice();
		    Assert.assertEquals(price_val, HIFIConstants.PRICE,"Price values are not matching");
		    
		  //Check the product is in avialble in stock if means go further
		    instock_val= objproductdescriptionpage.verifyStock();
		    Assert.assertEquals(instock_val, HIFIConstants.STOCK,"Item is not in stock or some other Error");
		    
		    objproductdescriptionpage.isCartDisplayed();
		    objproductdescriptionpage.clickOnAddToCart();
		    objproductdescriptionpage.isMyCartDisplayed();
		    
		    //Check the Count in my cart after adding the product
		    String count=objproductdescriptionpage.getTotalCartCount();
		    System.out.println(count);
		    objproductdescriptionpage.clickOnMyCart();
		    objproductdescriptionpage.isCheckOutDisplayed();
		    
		    //Click on Check out button
		    objpaymentscreenpage=objproductdescriptionpage.clickOnCheckOut();
		    //Moved to Payment screen- 
		    objpaymentscreenpage.isReturnToCartDisplayed();
		    //Clicked on return to cart link
		    objsearchpage=objpaymentscreenpage.clickOnReturntoCart();
		    
		    //Move to My account and Logout
		    objsearchpage.isMyAccountDisplayed();
		    objsearchpage.ClickOnMyAccountButtont();
		    objsearchpage.MovetoLogout();
		    
		} 
		catch (Exception e) 
		{
			throw new Exception("FAILED COMPELTE THE END TO FLOW " +e.getLocalizedMessage());
		}

		}
	/**
	 * @author Manjunath
	 * Below Test case coveres the Login and Logout functionality
	 * 
	 * @throws Exception
	 */
	
	@Test(priority=1, enabled=true)
	public void verifySuccessfullLoginFunctionality() throws Exception
	{
		try 
		{
			objhomepage = new Homepage(driver);
			//Get the Title of the Page compare with Expexted value both are Matching
			home_page_title=objhomepage.verifyHomePageTitle();
			Assert.assertEquals(home_page_title, HIFIConstants.HOME_TITLE,"Title of the Page is not Matching");
			
			//Check My account object is displayed on the page
			flag = objhomepage.isMyAccountDisplayed();
		    Assert.assertTrue(flag, "MyAccount object is not displayed");
		    
		    //Click on Myaccount Navigation will go to Login Page
		   //Login Functionality will Cover here
		    objloginpage =objhomepage.clickOnMyAccount();
		    Login= objloginpage.getMyLoginText();
		    Assert.assertEquals(Login, HIFIConstants.LOGIN_TEXT_HEAD," Login header details are not Matching");
		    objloginpage.isUsernameDisplayed();
		    objloginpage.enterUsername(username);
		     objloginpage.isPasswordDisplayed();
		     objloginpage.enterPassword(password);
		    objsearchpage= objloginpage.clickOnLogin();
		    objsearchpage.explicitWait(5);
		      //Ends Login function
		  
		    flag=objsearchpage.isSearchTextDisplayed();
		    Assert.assertTrue(flag, "Search Text box object is not displayed");
		
		    
		    //Move to My account and Logout
		    objsearchpage.isMyAccountDisplayed();
		    objsearchpage.ClickOnMyAccountButtont();
		    objsearchpage.MovetoLogout();
		   
		} 
		catch (Exception e) 
		{
			throw new Exception("Valid Login Scenario " +e.getLocalizedMessage());
		}

		}
	/**
	 * @author Manjunath
	 * Below Test case covers Invalid Login functionality
	 * 
	 * @throws Exception
	 */
	@Test(priority=2, enabled=true)
	public void verifyInvalidLoginFunctionality() throws Exception
	{
		try 
		{
			objhomepage = new Homepage(driver);
			//Get the Title of the Page compare with Expexted value both are Matching
			home_page_title=objhomepage.verifyHomePageTitle();
			Assert.assertEquals(home_page_title, HIFIConstants.HOME_TITLE,"Title of the Page is not Matching");
			
			//Check My account object is displayed on the page
			flag = objhomepage.isMyAccountDisplayed();
		    Assert.assertTrue(flag, "MyAccount object is not displayed");
		    
		    //Click on Myaccount Navigation will go to Login Page
		   //Login Functionality will Cover here
		    objloginpage =objhomepage.clickOnMyAccount();
		    Login= objloginpage.getMyLoginText();
		    Assert.assertEquals(Login, HIFIConstants.LOGIN_TEXT_HEAD," Login header details are not Matching");
		    objloginpage.isUsernameDisplayed();
		    objloginpage.enterUsername(invalid_username);
		     objloginpage.isPasswordDisplayed();
		     objloginpage.enterPassword(Invalid_password);
		     objloginpage.clickOnLoginButtonForInvalid();
		     objsearchpage.explicitWait(1);
		     flag=objloginpage.isErrorMessageDisplayed();
		     Assert.assertTrue(flag, "Error Message is not displayed");
		     String msg=objloginpage.getErrorMessage();
		     Assert.assertEquals(msg, HIFIConstants.ERROR_MSG," Both Error Messages are not matching");	  
		   
		} 
		catch (Exception e) 
		{
			throw new Exception("InValid Login Scenario " +e.getLocalizedMessage());
		}

		}
	
	
	
		
	}
	

