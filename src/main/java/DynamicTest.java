import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DynamicTest {

    private HashMap<String, String> parameters;
    private List<String> classes;
    private String testName;

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    public List<String> getClasses() {
        return classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Override
    public String toString() {
        return "DynamicTest{" +
                "parameters=" + parameters +
                ", classes=" + classes +
                ", testName='" + testName + '\'' +
                '}';
    }


    // Converts a list of strings with class names to a list of Xml Classes

    public List<XmlClass> getXmlClasses(List<String> classes) {

        List<XmlClass> xmlClasses = new ArrayList<XmlClass>();
        for (String className : classes) {
            xmlClasses.add(new XmlClass(className));
        }
        return xmlClasses;
    }


    // Returns an XmlTest

    public XmlTest getXmlTest(XmlSuite suite) {

        //Initiates an XmlTest associated with the suite
        XmlTest xmlTest = new XmlTest(suite);

        // Sets test name
        xmlTest.setName(getTestName());

        // Sets the list of test classes associated with this test
        xmlTest.setClasses(getXmlClasses(getClasses()));

        // Sets the parameters for this test
        xmlTest.setParameters(getParameters());

        return xmlTest;

    }

}
