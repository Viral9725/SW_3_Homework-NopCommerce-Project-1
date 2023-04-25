package computer;

import Utilities.Utilities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utilities {

    String baseUrl = "https://demo.nopcommerce.com/";
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }
    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {

        clickOnElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/a[1]"));
        //driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/a[1]")).click();
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));
        // driver.findElement(By.xpath("//body/div[6]/div[3]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]")).click();
        clickOnElement(By.xpath("//option[contains(text(),'Name: Z to A')]"));
        //driver.findElement(By.xpath("//option[contains(text(),'Name: Z to A')]")).click();
        // Verify that the products are arranged in descending alphabetical order
        List<WebElement> productNames = driver.findElements(By.xpath("//h2[@class='product-name']/a"));
        List<String> productNameStrings = new ArrayList<String>();
        for (WebElement productName : productNames) {
            productNameStrings.add(productName.getText());
        }
        List<String> sortedProductNames = new ArrayList<String>(productNameStrings);
        Collections.sort(sortedProductNames, Collections.reverseOrder());
        Assert.assertEquals(productNameStrings, sortedProductNames);
    }
    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        clickOnElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/a[1]"));
        //driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/a[1]")).click();
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));
        // driver.findElement(By.xpath("//body/div[6]/div[3]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]")).click();
        clickOnElement(By.xpath("//option[contains(text(),'Name: A to Z')]"));
        //driver.findElement(By.xpath("//option[contains(text(),'Name: A to Z')]")).click();

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//body/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/button[1]")).click();
        Assert.assertEquals("Build your computer page is not displayed", "Build your own computer", getTextFromElement(By.xpath("//h1[contains(text(),'Build your own computer')]")));
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='product_attribute_1']"), "2.2 GHz Intel Pentium Dual-Core E2200");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='product_attribute_2']"), "8GB [+$60.00]");
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));
        clickOnElement(By.xpath("//input[@id='product_attribute_4_9']"));
        Thread.sleep(5000);
        clickOnElement(By.xpath("//input[@id='product_attribute_5_12']"));
        Thread.sleep(5000);
        Assert.assertEquals("Wrong price displayed", "$1,475.00", getTextFromElement(By.xpath("//span[@id='price-value-1']")));
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));
        Assert.assertEquals("Green banner not displayed", "The product has been added to your shopping cart", getTextFromElement(By.xpath("//body/div[@id='bar-notification']/div[1]")));
        clickOnElement(By.xpath("//body/div[@id='bar-notification']/div[1]/span[1]"));
        mouseHoverOnElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));
        //2.12 product added to shopping cart
        Assert.assertEquals("Shopping Cart not displayed", "Shopping cart", getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]")));
        WebElement element = driver.findElement(By.xpath("//input[@class='qty-input']"));
        element.click();
        element.sendKeys(Keys.CONTROL + "a"); // Select all existing text
        element.sendKeys("2"); // Type in the new text
        clickOnElement(By.xpath("//button[@id='updatecart']"));
        Assert.assertEquals("Total displayed not correct", "$2,950.00", getTextFromElement(By.xpath("//tbody/tr[1]/td[6]/span[1]")));
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));
        Assert.assertEquals("Welcome sign in message not displayed", "Welcome, Please Sign In!", getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]")));
        clickOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "Viral");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "patel");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), "Viral1234@Gmail.com");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "10 Downing Street");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "SW1 1AA");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "007007007007007");
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[1]/div[2]/div[1]/button[4]"));
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[1]/div[2]/div[1]/button[4]"));
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[3]/div[2]/form[1]/div[1]/div[1]/div[1]/ul[1]/li[2]/div[1]"));
        //clickOnElement(By.name("No Thanks"), alertDismiss());
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[3]/div[2]/form[1]/div[2]/button[1]"));
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[4]/div[2]/div[1]/button[1]"));
        // Thread.sleep(3000);
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Master card");
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "MR BBAA");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"),"0759 2136 2156 5987");
        sendTextToElement(By.xpath("//select[@id='ExpireMonth']"), "12");
        sendTextToElement(By.xpath("//select[@id='ExpireYear']"), "2026");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "111");
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[5]/div[2]/div[1]/button[1]"));
        Assert.assertEquals("Payment Method incorrect", "Credit Card", getTextFromElement(By.xpath("//span[contains(text(),'Credit Card')]")));
        Assert.assertEquals("Incorrect Shipping Method", "Next Day Air", getTextFromElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[6]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/ul[1]/li[1]/span[2]")));
        Assert.assertEquals("Incorrect payment", "$2,950.00", getTextFromElement(By.xpath("//body[1]/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[6]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/table[1]/tbody[1]/tr[4]/td[2]/span[1]/strong[1]")));
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));
        Assert.assertEquals("Sorry", "Thank you", getTextFromElement(By.xpath("//h1[contains(text(),'Thank you')]")));
        Assert.assertEquals("Your Order has been cancelled", "Your order has been successfully processed!", getTextFromElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]")));
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));
        Assert.assertEquals("Welcome Error Message", "Welcome to our store", getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]")));

    }
}
