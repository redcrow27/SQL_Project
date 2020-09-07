package pages;

import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/*
All WebElements, also known as page objects of Home Page are stored in this class. Page Factory is used to initialize
our objects so we can use them. Driver.getDriver() method is used to connect our WebElements with that driver.
 */
public class CommonPage {
    public CommonPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public static final String CURRENT_DATETIME = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss"));


}
