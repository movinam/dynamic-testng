package accessibilitytests;

import com.deque.axe.AXE;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;

public class TestBasics {

    private static final URL scriptUrl = TestBasics.class.getResource("/axe.min.js");
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Parameters("url")
    public void testAccessibility(String url){
        driver.get(url);
        AXE.inject(driver, scriptUrl);
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();
        JSONArray violations = responseJSON.getJSONArray("violations");
        if (violations.length() == 0)
        {
            Assert.assertTrue(true, "No violations found");
        }
        else {
            AXE.writeResults(violations.toString(), responseJSON);
            Assert.assertTrue(false, AXE.report(violations));
        }
    }

}
