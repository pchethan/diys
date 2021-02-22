package com.example.test;

import java.util.Optional;

public class PrintClassLoaders
{
    public static void main( String[] args ) throws InterruptedException {
        Utils.printCLs(PrintClassLoaders.class, (str) -> System.out.println(str));
        Thread.sleep(1000000l);
    }
}
