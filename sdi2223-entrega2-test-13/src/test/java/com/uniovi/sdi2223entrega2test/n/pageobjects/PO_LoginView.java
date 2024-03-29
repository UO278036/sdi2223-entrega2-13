package com.uniovi.sdi2223entrega2test.n.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView {

	static public void fillLoginForm(WebDriver driver, String emailp, String passwordp) {
		WebElement email = driver.findElement(By.name("email"));
		email.click();
		email.clear();
		email.sendKeys(emailp);
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);
		//Pulsar el boton de login.
		By boton = By.xpath("/html/body/div/form/div[3]/div/button");
		driver.findElement(boton).click();	
	}
	static public void login(WebDriver driver,String user, String password, String checkText) {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		fillLoginForm(driver, user, password);
		//Comprobamos que entramos en la pagina privada del Profesor
		PO_View.checkElementBy(driver, "text", checkText);
	}

	static public void logout(WebDriver driver, String checkKeyText) {
		String loginText = PO_HomeView.getP().getString(checkKeyText, PO_Properties.getSPANISH());
		PO_PrivateView.clickOption(driver, "logout", "text", loginText);
	}
}
