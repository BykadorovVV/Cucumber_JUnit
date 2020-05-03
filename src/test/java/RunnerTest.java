import cucumber.api.*;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/resources/Feature",
        glue = {"Steps"},
        tags = {"@find"},
        plugin = {"pretty", "html:target/error.html"},
        strict = true
 )







public class RunnerTest {




}
