package triangle.utils;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimingExtention implements BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback {
    private static Logger logger = LoggerFactory.getLogger(TimingExtention.class);
    private long allTestTime = 0;

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        long timeAfterTest = System.currentTimeMillis();
        long timeDiff = timeAfterTest - (long) extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).get("start time");
        allTestTime += timeDiff;
        logger.info("Test execution time {}", timeDiff);
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put("start time", System.currentTimeMillis());
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        System.out.println("Test passed! Time is: " + allTestTime + " ms");
    }
}

