package uk.co.automationtesting;

import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BasePage;
import base.Hooks;
import pageObjects.Homepage;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

public class AddRemoveItemBasketTest extends Hooks {

	public AddRemoveItemBasketTest() throws IOException {
		super();
		
	}
	
	@Test
	public void addRemoveItem() throws IOException {
		
		Homepage home = new Homepage();
		home.getTestStoreLink().click();


		ShopHomepage shopHome = new ShopHomepage();
		shopHome.getProdOne().click();

		
		ShopProductPage shopProd = new ShopProductPage();
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		shopProd.getQuantIncrease().click();
		shopProd.getAddToCartBtn().click();

	
		ShopContentPanel cPanel = new ShopContentPanel();
		cPanel.getContinueShopBtn().click();
		shopProd.getHomepageLink().click();
		shopHome.getProdTwo().click();
		shopProd.getAddToCartBtn().click();
		cPanel.getCheckoutBtn().click();


		ShoppingCart cart = new ShoppingCart();
		cart.getDeleteItemTwo().click();

	
		waitForElementInvisible(cart.getDeleteItemTwo(),10);

		// printing the total amount to console
		System.out.println(cart.getTotalAmount().getText());
		
		// using an assertion to make sure the total amount is what we are expecting
		Assert.assertEquals(cart.getTotalAmount().getText(), "$45.24");

	}

}
