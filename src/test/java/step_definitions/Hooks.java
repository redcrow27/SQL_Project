package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.Driver;
import utils.Screenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {
    private static WebDriver driver;
    public static Scenario currentScenario;

    //Will run before each scenario
    @Before
    public void setUp(Scenario scenario){
        //initializing WebDriver and Scenario objects for each scenario
        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        currentScenario = scenario;
    }

    //Will run after each scenario
    @After
    public void tearDown(Scenario scenario){
        //Screenshot will be taken if scenario fails for any reason
        if (scenario.isFailed()) {
            Screenshot.takeScreenshot();
        }
        Driver.quitDriver();
    }
}
