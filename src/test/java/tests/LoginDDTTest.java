package tests;

import framework.base.BaseTest;
import framework.pages.LoginPage;
import framework.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginDDTTest extends BaseTest {
    private final String PATH = "src/test/resources/testdata/login_data.xlsx";

    @DataProvider(name = "SmokeData")
    public Object[][] getSmoke() { return ExcelReader.getData(PATH, "SmokeCases"); }

    @DataProvider(name = "NegativeData")
    public Object[][] getNeg() { return ExcelReader.getData(PATH, "NegativeCases"); }

    @Test(dataProvider = "SmokeData", groups = "smoke")
    public void testLoginExcel(String user, String pass, String url, String desc) {
        getDriver().get("https://www.saucedemo.com");
        new LoginPage(getDriver()).login(user, pass);
        Assert.assertTrue(getDriver().getCurrentUrl().contains(url), desc);
    }
}