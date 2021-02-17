package com.example.test;

import java.util.Optional;

public class PrintClassLoaders
{
    public static void main( String[] args )  {
        Utils.printCLs(PrintClassLoaders.class, (str) -> System.out.println(str));
    }
}
