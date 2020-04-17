package com.wip.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.wip.util.BasePageObject;

public class PaymentScreenPage extends BasePageObject
{
	public PaymentScreenPage(WebDriver driver)
	{
		super(driver);
	}
	Boolean flag=false;
	
	By txt_returntoCart_xpath=By.xpath("//span[text()='Return to cart']");
	
	public boolean isReturnToCartDisplayed() throws Exception 
	{
		try 
		{
			waitForAnElement(txt_returntoCart_xpath, 30);
			flag = isElementPresent(txt_returntoCart_xpath);
			
			
		}
		catch(Exception e)
		{
			throw new Exception("Retrun to cart is displayed::" + isReturnToCartDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}
	
	public Searchpage  clickOnReturntoCart() throws Exception
	{
		try 
		{
			javaScriptClick(txt_returntoCart_xpath);
		} 
		catch (Exception e)
		{
			
			throw new Exception("FAILED CLICKING ON RETURN TO CART"  +e.getLocalizedMessage());
		}
				return new Searchpage(driver);
	}
}
