package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pages.homePage;
import pages.userLoginPage;
import pages.userRegisterationPage;

public class UserRegisterationWithParallelTesting extends testBase2{
	
	homePage hpage;
	userRegisterationPage upage;
	userLoginPage lpage;
	Faker fake=new Faker();
	String fname=fake.name().firstName();
	String lname=fake.name().lastName();
	String mail=fake.internet().emailAddress();
	String pass=fake.number().digits(8);
	
	@Test
	public void UserRegisterInParallel()
	{
		hpage=new homePage(getDriver());
		upage=new userRegisterationPage(getDriver());
		lpage=new userLoginPage(getDriver());
		hpage.pressOnRegister();
		upage.UserRegisterSauceLab(fname,lname,mail,pass,pass);
		assertEquals(upage.mess.getText(),"Your registration completed");
		System.out.println("UserData "+fname +" "+lname+" "+mail+" "+pass);
		
		hpage.pressOnLogout();
		
		hpage.pressOnLogin();
		lpage.userLogin(mail, pass);
		assertTrue(hpage.logout.isDisplayed());
		
		hpage.pressOnLogout();
		
	}
		
	}


