import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DynamicTestNG {

    private DynamicSuite dynamicSuite;

    DynamicTestNG() throws Exception {

        //Reads data from the JSON file when initialised
        dynamicSuite = readJsonFile();

    }

    public DynamicSuite getDynamicSuite() {
        return dynamicSuite;
    }

    public void setDynamicSuite(DynamicSuite dynamicSuite) {
        this.dynamicSuite = dynamicSuite;
    }

    private DynamicSuite readJsonFile() throws Exception {

        Object object = new JsonParser().parse(new FileReader("src/main/resources/data.json"));

        //Maps object to the Dynamic Suite class.
        DynamicSuite dynamicSuite = new ObjectMapper().readValue(object.toString(), DynamicSuite.class);

        return dynamicSuite;

    }


    // Returns XmlSuite(TestNG suite with all the tests from the JSON file)

    public XmlSuite getXmlSuite() {

        XmlSuite xmlSuite = new XmlSuite();

        // Sets Suite name
        xmlSuite.setName(getSuiteName());

        // Sets Suite's tests
        xmlSuite.setTests(getXmlTests(xmlSuite));

        return xmlSuite;

    }


    // Returns Suite name

    private String getSuiteName() {
        return dynamicSuite.getSuiteName();
    }


    // Returns the list of XmlTests associated with xmlSuite

    private List<XmlTest> getXmlTests(XmlSuite xmlSuite) {

        List<XmlTest> xmlTests = new ArrayList<XmlTest>();
        List<DynamicTest> dynamicTests = dynamicSuite.getTests();

        // Gets the XML tests and adds it to the list
        for (DynamicTest test : dynamicTests) {
            xmlTests.add(test.getXmlTest(xmlSuite));
        }

        return xmlTests;

    }

}
