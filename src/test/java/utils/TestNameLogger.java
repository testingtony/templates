package utils;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestNameLogger implements TestRule {

    @Override
    public Statement apply(final Statement statement, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Logger logger = LoggerFactory.getLogger(description.getClass());
                final String methodName = description.getMethodName();
                logger.info("Start Test '" + methodNameToTestName(methodName) + "'");
                try {
                    statement.evaluate();
                    logger.info("Pass Test '" + methodNameToTestName(methodName) + "'");
                    } catch (Throwable e) {
                    logger.info("Fail Test '" + methodNameToTestName(methodName) + "' (" + e.getLocalizedMessage() +")" );
                    throw e;
                }
            }
        };
    }

    public static String methodNameToTestName(String methodName) {
        return methodNameToTestName(methodName, " ");
    }

    public static String methodNameToTestName(final String methodName, final String separtator) {
        final String replacement = "$1" + separtator + "$2";
        String testName = methodName.replaceAll("([a-z])([A-Z])", replacement);
        testName = testName.replaceAll("([A-Z])([A-Z][a-z])", replacement);
        testName = testName.replaceAll("([0-9]+)([A-Z][a-z])", replacement);
        testName = Character.toString(testName.charAt(0)).toUpperCase() + testName.substring(1);

        return testName;
    }

}
