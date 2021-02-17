package com.example.test;

/*

+-------------------------------+-----------------------------------------------+
|                               | ----- Class loading order and visibility ---> |
+-------------------------------+---------------+--------------+----------------+
|  Code loaded by Bootstrap CL  |  Bootstrap CP |              |                |
+-------------------------------+---------------+--------------+----------------+
|  Code loaded by Extension CL  |  Bootstrap CP | Extension CP |                |
+-------------------------------+---------------+--------------+----------------+
| Code loaded by Application CL |  Bootstrap CP | Extension CP | Application CP |
+-------------------------------+---------------+--------------+----------------+

 */


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.jar.JarInputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DelegationModelTest2 extends BaseTest
{
    @Test
    @DisplayName("Parent CL cannot load class that child CL has laoded")
    public void jvmChoosesCL2() throws ReflectiveOperationException, MalformedURLException {
        CustomClassLoader customClassLoader = new CustomClassLoader(DelegationModelTest2.class.getClassLoader());

        Class<?> libClass = Class.forName("com.example.Calculator", true, customClassLoader);
        Object instance = libClass.newInstance();
        Method method = libClass.getMethod("add",Integer.class,Integer.class);
        Long result = (Long) method.invoke(instance,2,3);
        assert result.intValue()==5;

        Class<?> calc = null;
        try {
            calc = Class.forName("com.example.Calculator");
        } catch (Throwable t) {
            //
        }
        assertTrue (calc == null, "Class loaded from child CL is visible to parent");
    }

    private static class CustomClassLoader extends ClassLoader {
        private ClassLoader parent;

        public CustomClassLoader(ClassLoader parent) throws MalformedURLException {
            super(parent);
            this.parent = parent;
        }

        @Override
        public Class findClass(String name) throws ClassNotFoundException {
            byte[] b = new byte[0];
            try {
                b = loadClassFromFile(name);
                return defineClass(name, b, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        private byte[] loadClassFromFile(String fileName) throws IOException {
            fileName = fileName.replace("MyLib","TestMyLib");

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                    "calculator.jar");

            JarInputStream jar = new JarInputStream(inputStream);
            byte[] libBytes = null;
            int offset=0;

            while (true) {
                java.util.jar.JarEntry jarEntry = jar.getNextJarEntry();
                if(jarEntry == null) {
                    break;
                }
                if(jarEntry.getName().contains("Calculator.class") == false) {
                    continue;
                }

                libBytes = new byte[(int)jarEntry.getSize()];
                jar.read(libBytes, offset, (int)jarEntry.getSize());
                break;
            }
            jar.close();

            return libBytes;


//            byte[] buffer;
//            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
//            int nextValue = 0;
//            try {
//                while ( (nextValue = inputStream.read()) != -1 ) {
//                    byteStream.write(nextValue);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            buffer = byteStream.toByteArray();
//            return buffer;
        }
    }
}
