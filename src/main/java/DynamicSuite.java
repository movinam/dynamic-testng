import java.util.List;

// DynamicSuite POJO

public class DynamicSuite {

    String suiteName;
    List<DynamicTest> tests;

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public List<DynamicTest> getTests() {
        return tests;
    }

    public void setTests(List<DynamicTest> tests) {
        this.tests = tests;
    }

    @Override
    public String toString() {
        return "DynamicSuite{" +
                "suiteName='" + suiteName + '\'' +
                ", tests=" + tests +
                '}';
    }


}
