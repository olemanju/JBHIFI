package com.wip.pages;

import java.sql.Driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.wip.util.BasePageObject;

public class ProductDetailsPage extends BasePageObject
{

	public ProductDetailsPage(WebDriver driver)
	{
		super(driver);
	}
	public static Logger log= LogManager.getLogger(LoginPage.class.getName());
	Boolean flag= false;
	
	By header_xpath= By.xpath("//h1");
	By cart_id= By.id("AddToCartText-");
	By cardt_count= By.id("cart-count");
	By mycart_btn= By.id("minicart-toggle");
	By Checkout_name= By.name("checkout");
	
	By model_xpath=By.xpath("//dl[@class='product-meta prod-code']//dd[1]");
	By sku_xpath=By.xpath("//dl[@class='product-meta prod-code']/dd[2]");
	By lbl_price_css=By.cssSelector("span.price");
	By btn_instock_css=By.cssSelector("span.in-stock");
	
	public boolean isTextDisplayed() throws Exception 
	{
		try 
		{
			waitForAnElement(header_xpath, 30);
			flag = isElementPresent(header_xpath);
			
			
		}
		catch(Exception e)
		{
			throw new Exception("product details are displayed::" + isTextDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}
	
	public String nameOftheProduct() throws Exception
	{
		String name= null;
		try 
		{
			waitForAnElement(header_xpath, 30);
			 name=getText(header_xpath);
			 System.out.println("Details of the Product  is " + name);
			
		}
		catch(Exception e)
		{
			throw new Exception("product name is :" + nameOftheProduct() + e.getLocalizedMessage());
		}
		
			return name;
	}
	
	public boolean isCartDisplayed() throws Exception 
	{
		try 
		{
			waitForAnElement(cart_id, 30);
			flag = isElementPresent(cart_id);
			
		}
		catch(Exception e)
		{
			throw new Exception("Add to Cart is displayed::" + isCartDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}
	
	public void  clickOnAddToCart() throws Exception
	{
		try 
		{
			javaScriptClick(cart_id);
		} 
		catch (Exception e)
		{
			
			throw new Exception("FAILED CLICKING ON ADD TO CART "  +e.getLocalizedMessage());
		}
				
	}
	
	public boolean isMyCartDisplayed() throws Exception 
	{
		try 
		{
			waitForAnElement(cardt_count, 30);
			flag = isElementPresent(cardt_count);
			
		}
		catch(Exception e)
		{
			throw new Exception("My Cart is displayed::" + isMyCartDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}
	
	public String getTotalCartCount() throws Exception
	{
		String name= null;
		try 
		{
			waitForAnElement(cardt_count, 30);
			 name=getText(cardt_count);
			 if(name.equals("1"))
			 {
				 System.out.println("Item is Added to the cart");
			 }
			 else
			 {
				 System.out.println("Not Listed");
			 }
			
		}
		catch(Exception e)
		{
			throw new Exception("The Product total count is :" + getTotalCartCount() + e.getLocalizedMessage());
		}
		
			return name;
	}
	
	public void  clickOnMyCart() throws Exception
	{
		try 
		{
			javaScriptClick(cardt_count);
		} 
		catch (Exception e)
		{
			
			throw new Exception("FAILED CLICKING ON MY CART "  +e.getLocalizedMessage());
		}
				
	}
	
	public boolean isCheckOutDisplayed() throws Exception 
	{
		try 
		{
			waitForAnElement(Checkout_name, 30);
			flag = isElementPresent(Checkout_name);
			
		}
		catch(Exception e)
		{
			throw new Exception("Check out button is ::" + isMyCartDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}
	
	
	public PaymentScreenPage  clickOnCheckOut() throws Exception
	{
		try 
		{
			javaScriptClick(Checkout_name);
			explicitWait(7);
		} 
		catch (Exception e)
		{
			
			throw new Exception("FAILED CLICKING ON Check Out "  +e.getLocalizedMessage());
		}
				return new PaymentScreenPage(driver);
	}
	
	
	public String nameOftheModel() throws Exception
	{
		String name= null;
		try 
		{
			waitForAnElement(model_xpath, 30);
			 name=getText(model_xpath);
			 System.out.println("Name of the Model is " + name);
			
		}
		catch(Exception e)
		{
			throw new Exception("Model name is :" + nameOftheProduct() + e.getLocalizedMessage());
		}
		
			return name;
	}
	
	public String nameOftheSKU() throws Exception
	{
		String name= null;
		try 
		{
			waitForAnElement(sku_xpath, 30);
			 name=getText(sku_xpath);
			 
			 System.out.println("SKU of the Product is " + name);
			
		}
		catch(Exception e)
		{
			throw new Exception("Sku name is :" + nameOftheProduct() + e.getLocalizedMessage());
		}
		
			return name;
	}
	
	public String productPrice() throws Exception
	{
		String name= null;
		try 
		{
			waitForAnElement(lbl_price_css, 30);
			 name=getText(lbl_price_css);
			 
			 System.out.println("Selected Item Price is " + name);
			
		}
		catch(Exception e)
		{
			throw new Exception("Price of the Product :" + nameOftheProduct() + e.getLocalizedMessage());
		}
		
			return name;
	}
	public String verifyStock() throws Exception
	{
		String name= null;
		try 
		{
			waitForAnElement(btn_instock_css, 30);
			 name=getText(btn_instock_css);
			 
			 System.out.println("Item stock details " + name);
			
		}
		catch(Exception e)
		{
			throw new Exception("Item stock details  :" + nameOftheProduct() + e.getLocalizedMessage());
		}
		
			return name;
	}
	
	
	
	
	
	
	
}
