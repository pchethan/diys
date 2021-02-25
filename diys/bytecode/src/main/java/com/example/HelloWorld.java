package com.example;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

public class HelloWorld extends ClassLoader implements Closeable {

    public URLConnection urlConnection;

    private static int i = 181;

    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }

    @Override
    public void close() throws IOException {

    }
}
