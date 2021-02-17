package com.example.test;

import java.io.OutputStream;
import java.io.Writer;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

public class Utils {
    public static void printCLs(Class<?> clazz, Consumer<String> out) {
        out.accept("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        printCLs(Optional.ofNullable(clazz.getClassLoader()), out);
        out.accept("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    public static void printCLs(Optional<ClassLoader> cl, Consumer<String> out) {
        if(cl.isPresent()) {
            out.accept(cl.get().toString());
            if(cl.get() instanceof URLClassLoader) {
                URLClassLoader ucl = (URLClassLoader) cl.get();
                String msg = Arrays.toString(ucl.getURLs());
                msg = msg.replace(", ", "\n");
                out.accept(msg);
            }
            out.accept("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            printCLs(Optional.ofNullable(cl.get().getParent()), out);
        } else {
            out.accept("Bootstrap classloader or null");
            return;
        }
    }

    public static void testMethod() {
        System.out.println("Util::testMethod");
    }
}
