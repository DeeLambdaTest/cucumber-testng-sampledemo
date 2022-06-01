package stepDefinitions;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import MyRunner.appRunner;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class toDoAppDefinition extends appRunner {
    public AndroidDriver<AndroidElement> driver = connection;
    public String a = "I'm in glue";
    @Given("the user clicks on search button")
    public void the_user_clicks_on_search_button() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Given step");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        AndroidElement searchElement = (AndroidElement) wait
                .until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Search Wikipedia")));
        searchElement.click();
    }

    @When("user types LambdaTest")
    public void user_types_lambda_test() {
        System.out.println("When step");
        // Write code here that turns the phrase above into concrete actions
        WebDriverWait wait = new WebDriverWait(driver, 10);
        AndroidElement insertTextElement = (AndroidElement) wait
                .until(ExpectedConditions.elementToBeClickable(MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
        insertTextElement.sendKeys("LambdaTest");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("^list of LambdaTest searches are shown$")
    public void select_second_item() {
        List<AndroidElement> allProductsName = driver.findElementsByClassName("android.widget.TextView");
        assert (allProductsName.size() > 0);
    }
}
