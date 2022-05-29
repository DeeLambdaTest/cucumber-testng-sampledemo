package stepDefinitions;
import MyRunner.appRunner;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook extends appRunner {
    public AndroidDriver<AndroidElement> driver = this.connection;

    @Before
    public void updateName(Scenario scenario) throws InterruptedException {
        Thread.sleep(30);
        driver.executeScript("lambda-name=" + scenario.getName());
    }

    @After
    public void close_the_browser(Scenario scenario) {
        driver.executeScript("lambda-status=" + (scenario.isFailed() ? "failed" : "passed"));
        System.out.println(driver.getSessionId());
        driver.quit();
    }

}