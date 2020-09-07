package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import pages.CommonPage;
import step_definitions.Hooks;

/*
This class has only one purpose, to take a screenshot. Call takeScreenshot() method and it will take a screenshot and
attach it to related Scenario step
 */
public class Screenshot {
    public static void takeScreenshot(){
        SeleniumUtils.waitForPageToLoad();
        try{
            byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            Hooks.currentScenario.attach(screenshot, "image/png", "Screenshot | " + CommonPage.CURRENT_DATETIME);
        }catch (WebDriverException e){
            e.printStackTrace();
        }
    }
}
