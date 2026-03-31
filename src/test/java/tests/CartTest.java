package tests;

import framework.base.BaseTest;
import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    @Test
    public void testAddProductCount() {
        int count = new LoginPage(getDriver())
                .login("standard_user", "secret_sauce")
                .addFirstItemToCart()
                .getCartItemCount();
        Assert.assertEquals(count, 1);
    }

    @Test
    public void testRemoveProduct() {
        int count = new LoginPage(getDriver())
                .login("standard_user", "secret_sauce")
                .addFirstItemToCart()
                .goToCart()
                .removeFirstItem()
                .getItemCount();
        Assert.assertEquals(count, 0);
    }

    @Test
    public void testFluentChaining() {
        String firstItemName = "Sauce Labs Backpack";
        boolean isCorrectItem = new LoginPage(getDriver())
                .login("standard_user", "secret_sauce")
                .addItemByName(firstItemName)
                .goToCart()
                .getItemNames()
                .contains(firstItemName);
        Assert.assertTrue(isCorrectItem);
    }
}