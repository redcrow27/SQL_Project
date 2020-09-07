package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * This class is a Singleton class. It will make sure that there is a only one instance
 * of WebDriver at any given time
 */
public class Driver {
    private static String propertyPath = "src/test/resources/conf/configuration.properties";
    private static WebDriver driver;

    /**
     * This method is used to get instance of a WebDriver object. If it is null, new instance will be created.
     * @return will return WebDriver object
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            switch (ConfigReader.readProperty("browser", propertyPath)) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("Invalid browser type");
            }
        }
        return driver;
    }

    /**
     * This method will close all open browsers of current WebDriver object and initialize it as null
     */
    public static void quitDriver(){
        getDriver().quit();
        driver = null;
    }

}
