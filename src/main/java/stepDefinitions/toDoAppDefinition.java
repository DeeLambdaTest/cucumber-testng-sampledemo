package stepDefinitions;
import java.util.List;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import MyRunner.appRunner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;

public class toDoAppDefinition extends appRunner {
    public AndroidDriver<AndroidElement> driver = connection;
    @Given("^the user clicks on search button$")
    public void user_already_on_home_page() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        AndroidElement searchElement = (AndroidElement) wait
                .until(ExpectedConditions
                        .elementToBeClickable(MobileBy.AccessibilityId("Search Wikipedia")));
        searchElement.click();
    }
    @When("^user types LambdaTest$")
    public void select_first_item() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        AndroidElement insertTextElement = (AndroidElement) wait
                .until(ExpectedConditions.elementToBeClickable(
                        MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
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
