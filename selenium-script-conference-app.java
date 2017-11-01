import org.junit.After;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;
import java.net.URL;
public class confappTest {
    private WebDriver driver;
    
    @Before
    public void setUp() throws Exception {
        String serverUrl = System.getProperty("grid.server.url");
		String gridServerUrl = "http://192.168.1.54:4444/wd/hub";
		if (serverUrl != null) {
			gridServerUrl = serverUrl;
		}
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		URL gridUrl = new URL(gridServerUrl);
		driver = new RemoteWebDriver(gridUrl, capability);
		driver.get("http://192.168.1.54:48080");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void confappTest() {
        driver.findElement(By.linkText("Conference Schedule")).click();
        driver.findElement(By.linkText("Venue Map")).click();
        driver.findElement(By.linkText("User feedback")).click();
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("test2");
        driver.findElement(By.id("feedbackContent")).click();
        driver.findElement(By.id("feedbackContent")).clear();
        driver.findElement(By.id("feedbackContent")).sendKeys("test1");
        driver.findElement(By.id("addFeedback")).click();
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
    
    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}