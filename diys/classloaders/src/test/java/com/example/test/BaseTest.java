package com.example.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class BaseTest {
    TestInfo testInfo;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        this.testInfo = testInfo;
        System.err.flush();
    }

    protected void doErrorTest(PlainDoer plainDoer) throws Throwable {
        System.err.println("\nTest : ["+testInfo.getDisplayName() + "]\n");
        System.err.flush();
        try {
            plainDoer.perform();
            assertTrue(true, "Expected error");
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        } finally {
            System.err.flush();
        }
    }

    protected void doTest(PlainDoer plainDoer) throws Throwable {
        System.err.println("\nTest : ["+testInfo.getDisplayName() + "]\n");
        System.err.flush();
        plainDoer.perform();
        Logger logger = LoggerFactory.getLogger(BaseTest.class.getName());
        logger.info("Hurray!");
        System.err.flush();
    }


}
