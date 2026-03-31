package tests;

import framework.base.BaseTest;
import framework.config.ConfigReader;
import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    
    @Test(groups = {"smoke"})
    public void testLoginSuccess() {
        String username = ConfigReader.getInstance().getUsername();
        String password = ConfigReader.getInstance().getPassword();

        boolean isInventoryVisible = new LoginPage(getDriver())
                .login(username, password)
                .isLoaded();
        Assert.assertTrue(isInventoryVisible);
    }

    @Test
    public void testLoginLockedOut() {
        String password = ConfigReader.getInstance().getPassword();
        
        String error = new LoginPage(getDriver())
                .loginExpectingFailure("locked_out_user", password)
                .getErrorMessage();
        Assert.assertTrue(error.contains("locked out"));
    }

    @Test
    public void testLoginEmptyUsername() {
        String password = ConfigReader.getInstance().getPassword();
        
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginExpectingFailure("", password);
        Assert.assertTrue(loginPage.isErrorDisplayed());
    }
}