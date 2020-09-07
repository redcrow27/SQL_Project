package utils;

import io.cucumber.java.Scenario;
import pages.CommonPage;
import step_definitions.Hooks;
/*
All Cucumber related re-usable methods should be stored in this class
 */
public class CucumberUtils {

    /**
     * This method is used to log information along with a screenshot
     * @param msg is our input text we want to include in reports
     * @param takeScreenshot is a boolean parameter. If its true, screenshot will be taken and attached to
     *                       Scenario step
     */
    public static void logInfo(String msg, boolean takeScreenshot){
        Scenario scenario = Hooks.currentScenario;
        scenario.log(CommonPage.CURRENT_DATETIME + " INFO: " + msg);
        if (takeScreenshot)
            Screenshot.takeScreenshot();
    }
}
