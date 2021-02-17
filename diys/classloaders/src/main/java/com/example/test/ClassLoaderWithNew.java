package com.example.test;

import org.apache.commons.lang3.RegExUtils;

public class ClassLoaderWithNew
{
    public static void main( String[] args )  {
        try {
            new RegExUtils();
        } catch (Throwable t) {
            //commons lang jar is not included in jar.
            //Stack trace shows loadClass invoked from new.
            t.printStackTrace();
        }
    }
}
