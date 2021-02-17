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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DelegationModelTest1 extends BaseTest
{
    @Test
    @DisplayName("JVM chooses the right CL to load class as per delegation model. Custom Cl is only used with parents cant find")
    public void jvmChoosesDefinitionCL() throws Throwable {
        String fqn = "com.example.test.DelegationModelTest1";
        ClassLoader thisCL = DelegationModelTest1.class.getClassLoader();

        {
            Class<?> aClass = Class.forName(fqn, true, thisCL);
            assertTrue(aClass.getClassLoader() == thisCL);
        }

        {
            ClassLoader cl = silentRun(() -> {
                return Class.forName(fqn, true, thisCL.getParent())
                        .getClassLoader();
            });
            assertTrue(cl == null);
        }


        {
            DelegationModelTest1.CustomClassLoader customClassLoader =
                    new DelegationModelTest1.CustomClassLoader(thisCL);
            Class<?> aClass = Class.forName(fqn, true, customClassLoader);
            assertTrue(aClass.getClassLoader() == thisCL);
        }
    }

    private static class CustomClassLoader extends ClassLoader {
        public CustomClassLoader(ClassLoader parent) {
            super(parent);
        }
    }

    protected ClassLoader silentRun(SimpleRunner<ClassLoader> runner) throws Throwable {
        try {
            return runner.run();
        } catch (Throwable t) {
            //t.printStackTrace(System.err);
        } finally {
            System.err.flush();
        }
        return null;
    }
}
