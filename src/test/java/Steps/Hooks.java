package Steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
 import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Hooks {

    public final static String URL = "https://www.ivi.ru/watch/185387";
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;
    public static Thread thread;


    @Before
    public void setUp(){
        if(SystemUtils.IS_OS_WINDOWS){
            System.setProperty("webdriver.chrome.driver","src/test/java/resources/drivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("useAutomationExtension", false);
            options.addArguments("disable-infobars");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.get(URL);
        }
        wait = new WebDriverWait(driver,60);
        actions = new Actions(driver);

        }
    @After
    public void embedScreenshot(Scenario scenario){
        if(scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + driver.getCurrentUrl());
                byte[] screnshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screnshot, "src/test/java/resources/image");
            } catch (WebDriverException webDriverException) {
                System.err.println(webDriverException.getMessage());
            }
        }else{
            driver.quit();
        }
        driver.quit();
    }

}
