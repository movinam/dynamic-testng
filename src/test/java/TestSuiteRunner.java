import org.testng.TestNG;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;

public class TestSuiteRunner {

    private DynamicTestNG dynamicTestNG;
    private List<XmlSuite> xmlSuites;
    private TestNG testNG;

    @BeforeTest
    public void setUpData() throws Exception {

        // Initialising dynamicTestNG will create the DynamicSuite POJO with the data from the JSON file
        dynamicTestNG = new DynamicTestNG();
        xmlSuites = new ArrayList<XmlSuite>();
        testNG = new TestNG();

    }

    @Test
    public void runTestSuite() {

        XmlSuite xmlSuite = dynamicTestNG.getXmlSuite();

        // Adds xmlSuite to lest of XML Suites
        xmlSuites.add(xmlSuite);

        // Sets suites that TestNG has to run
        testNG.setXmlSuites(xmlSuites);

        // TestNG runs the tests
        testNG.run();

    }

}
