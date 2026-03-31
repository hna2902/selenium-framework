package tests;

import framework.base.BaseTest;
import framework.pages.LoginPage;
import framework.utils.JsonReader;
import framework.utils.UserData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class UserLoginTest extends BaseTest {
    @DataProvider(name = "jsonUsers")
    public Object[][] getUsersFromJson() throws IOException {
        List<UserData> users = JsonReader.readUsers("src/test/resources/testdata/users.json");
        return users.stream()
                .map(u -> new Object[] { u })
                .toArray(Object[][]::new);
    }

    @Test(dataProvider = "jsonUsers")
    public void testLoginWithJson(UserData user) {
        getDriver().get("https://www.saucedemo.com");
        LoginPage loginPage = new LoginPage(getDriver());
        
        if (user.expectSuccess) {
            boolean isLoaded = loginPage.login(user.username, user.password).isLoaded();
            Assert.assertTrue(isLoaded, "Lỗi: " + user.description);
        } else {
            loginPage.loginExpectingFailure(user.username, user.password);
            Assert.assertTrue(loginPage.isErrorDisplayed(), "Lỗi: " + user.description);
        }
    }
}