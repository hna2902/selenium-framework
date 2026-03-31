package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(css = ".cart_item .cart_button")
    private List<WebElement> removeButtons;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {
        return cartItems.size();
    }

    public CartPage removeFirstItem() {
        if (!removeButtons.isEmpty()) {
            waitAndClick(removeButtons.get(0));
        }
        return this;
    }

    public void goToCheckout() {
        waitAndClick(checkoutButton);
    }

    public List<String> getItemNames() {
        List<String> names = new ArrayList<>();
        for (WebElement element : itemNames) {
            names.add(getText(element));
        }
        return names;
    }
}