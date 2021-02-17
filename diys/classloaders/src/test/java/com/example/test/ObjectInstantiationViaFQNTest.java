package com.example.test;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import ch.qos.logback.classic.MyLoggerFactory;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Illustrate object instatiation methods by taking logback as example.
 * Other ways like new, clone, deserialize are not shown.
 */

public class ObjectInstantiationViaFQNTest extends BaseTest
{
    @Test
    @Order(1)
    @DisplayName("Create logback via calling Class<?>.newInstance()")
    public void createWithClassInstantiate() throws Throwable {
        String error = doAndGetError(() -> {
            Class<?> loggerClazz = Class.forName("ch.qos.logback.classic.Logger");
            Logger logger = (Logger) loggerClazz.newInstance();
            assertTrue(logger!=null,"Failed to create logger - unknown reason");
        });
        assertTrue(error.length() > 0);
        System.out.println(error);
    }



    @Test
    @Order(2)
    @DisplayName("Create logback via calling Class<?>.getConstructor(params).newInstance(args)")
    public void createWithClassGetConstructorAndThenInstantiate() throws Throwable {
        String error = doAndGetError(() -> {
            Class<?> loggerClazz = Class.forName("ch.qos.logback.classic.Logger");
            Class<?> loggerContextClazz = Class.forName("ch.qos.logback.classic.LoggerContext");
            Constructor<?> constructor = loggerClazz.getConstructor(String.class, Logger.class, loggerContextClazz);
            Logger logger = (Logger) constructor
                    .newInstance("DEMO",null,null);
        });
        assertTrue(error.length() > 0);
        System.out.println(error);
    }

    @Test
    @Order(3)
    @DisplayName("Create logback via calling Class<?>.getDeclaredConstructor(params).newInstance(args)")
    public void createWithClassGetDeclConstructorAndThenInstantiate() throws Throwable {
        String error = doAndGetError(() -> {
            Class<?> loggerClazz = Class.forName("ch.qos.logback.classic.Logger");
            Class<?> loggerContextClazz = Class.forName("ch.qos.logback.classic.LoggerContext");
            Constructor<?> constructor = loggerClazz.getDeclaredConstructor(String.class, ch.qos.logback.classic.Logger.class, loggerContextClazz);
            Logger logger = (Logger) constructor
                    .newInstance("DEMO",null,null);
        });
        assertTrue(error.length() > 0);
        System.out.println(error);
    }

    @Test
    @Order(4)
    @DisplayName("Create logback via calling Class<?>.getDeclaredConstructor(params).newInstance(args) via factory")
    public void createWithClassGetDeclConstructorAndThenInstantiateFromFactory() throws Throwable {
        Class<?> loggerClazz = Class.forName("ch.qos.logback.classic.Logger");
        Logger instance = (Logger) MyLoggerFactory.getInstance(loggerClazz);
        assertTrue(instance != null);
    }


    @Disabled
    @Test
    @DisplayName("Get logback classic logger ctors")
    public void getCtors() throws Throwable {
        doTest(() -> {
            Class<?> loggerClazz = Class.forName("ch.qos.logback.classic.Logger");
            printCtors(loggerClazz);
        });
    }


    private static void printCtors(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getConstructors();
        System.err.println("\nHere are the n constructors; n="+constructors.length);
        for(Constructor<?> ctor: constructors) {
            System.err.println("\t\tctor:"+ctor);
        }
    }

    private static void printDeclaredCtors(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        System.err.println("\nHere are the n constructors; n="+constructors.length);
        for(Constructor<?> ctor: constructors) {
            System.err.println("\t\tctor:"+ctor);
        }
    }

    protected String doAndGetError(PlainDoer doer) throws Throwable {
        try {
            doer.perform();
        } catch (Throwable t) {
            //t.printStackTrace(System.err);
            return getSummaryFromExceptions(t);
        } finally {
            System.err.flush();
        }
        return null;
    }

    private String getSummaryFromExceptions(Throwable t) {
        StringBuilder sb = new StringBuilder();
        do {
            sb.append("\n"+t);
            t = t.getCause();
        } while(t != null);
        return sb.toString();
    }


}
