package tests;

import framework.base.BaseTest;
import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void testLoginSuccess() {
        boolean isInventoryVisible = new LoginPage(getDriver())
                .login("standard_user", "secret_sauce")
                .isLoaded();
        Assert.assertTrue(isInventoryVisible);
    }

    @Test
    public void testLoginLockedOut() {
        String error = new LoginPage(getDriver())
                .loginExpectingFailure("locked_out_user", "secret_sauce")
                .getErrorMessage();
        Assert.assertTrue(error.contains("locked out"));
    }

    @Test
    public void testLoginEmptyUsername() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginExpectingFailure("", "secret_sauce");
        Assert.assertTrue(loginPage.isErrorDisplayed());
    }
}