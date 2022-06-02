package MyRunner;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
@CucumberOptions(
        features = "src/main/java/Features/app.feature",
        glue = {"stepDefinitions"}
)
public class appRunner {
    public TestNGCucumberRunner testNGCucumberRunner;
    public static AndroidDriver<AndroidElement> connection;
    @BeforeMethod(alwaysRun = true)
    @Parameters({"deviceName"})
    public void setUpClass(String deviceName) throws Exception {
        System.out.println("Starting setup");
        String username = System.getenv("LT_USERNAME") == null ? "YOUR LT_USERNAME" : System.getenv("LT_USERNAME");
        String accesskey = System.getenv("LT_ACCESS_KEY") == null ? "YOUR LT_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY");

        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("deviceName", deviceName);
        capability.setCapability("build", "Cucumber Sample Build");
        capability.setCapability("name", "Cucumber Sample Test");
        capability.setCapability("app","lt://APP10011921653852606324666");
        capability.setCapability("isRealMobile", true);

        connection = new AndroidDriver<AndroidElement>(
                new URL("https://" + username + ":" + accesskey + "@beta-hub.lambdatest.com/wd/hub"),capability);

        System.out.println(capability);
        System.out.println(connection.getSessionId());
        WebDriverWait wait = new WebDriverWait(connection, 10);
    }
    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        System.out.println("Start Test");
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }
    @DataProvider
    public Object[][] features() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        System.out.println("Cucumber runner initialized");
        System.out.println(testNGCucumberRunner.provideFeatures().length);
        return testNGCucumberRunner.provideFeatures();

    }
    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
}
