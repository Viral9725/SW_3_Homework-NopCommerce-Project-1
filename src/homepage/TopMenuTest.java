package homepage;

import Utilities.Utilities;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class TopMenuTest extends Utilities {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void launchBrowser() {
        openBrowser(baseUrl);
    }


    // use the WebDriver instance to find and click the menu based on its name
    public void selectMenu(String menu) {
        driver.findElement(By.xpath("//a[contains(text(), '" + menu + "')]")).click();
        Assert.assertEquals("Correct page is not displayed", menu, driver.findElement(By.xpath("//h1[contains(text(),'" + menu + "')]")).getText());
    }

    @Test
    // use WebDriver to verify the page navigation
    public void verifyPageNavigation() {
        //  selectMenu("//body/div[6]/div[2]/ul[1]/li[1]a[1]");
        //    driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]a[1]")).click();
        // System.out.println("selectMenu:" + By.xpath("//body/div[6]/div[2]/ul[1]/li[]a[1])"));
        selectMenu("Gift Cards");
    }

    @After
    public void tearDown(){
            closeBrowser();
    }
}



