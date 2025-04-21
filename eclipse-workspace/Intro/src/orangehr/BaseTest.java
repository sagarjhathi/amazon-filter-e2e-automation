package orangehr;



import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import googleaggregatorpomupdated.DriverManager;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
      DriverManager.getDriver();

    }

    @AfterMethod
    public void tearDown() {
    DriverManager.getDriver();

    }
}
