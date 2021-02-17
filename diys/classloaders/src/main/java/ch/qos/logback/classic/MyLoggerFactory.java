package ch.qos.logback.classic;

import java.lang.reflect.InvocationTargetException;

public class MyLoggerFactory {
    public static Logger getInstance(Class<?> clazz) throws ReflectiveOperationException {
        return (Logger) clazz.getDeclaredConstructor(String.class, Logger.class, LoggerContext.class)
                .newInstance("DEMO",null,null);
    }
}
