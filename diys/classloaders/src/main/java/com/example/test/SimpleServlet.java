package com.example.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleServlet extends HttpServlet {

    private String message;

    public void init() throws ServletException {
        // Do required initialization
        message = "Hello World";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Actual logic goes here.
        PrintWriter out = response.getWriter();

        Utils.printCLs(SimpleServlet.class, (str) -> out.println("<h1 font-family=\"Arial\">"+str+"</h1>"));

        out.flush();
    }

    public void destroy() {
        // do nothing.
    }

    void printCLs(ClassLoader cl) {
        while(cl != null) {
            System.out.println("CL: "+cl);
            cl = cl.getParent();
        }
    }

//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter pw = resp.getWriter();
//        pw.println("servlet");
//
////        printClasspaths(MyServlet.class.getClassLoader());
////        printClasspaths(MyServlet.class.getClassLoader().getParent());
////        printClasspaths(MyServlet.class.getClassLoader().getParent().getParent());
////        printClasspaths(MyServlet.class.getClassLoader().getParent().getParent().getParent());
////        printClasspaths(MyServlet.class.getClassLoader().getParent().getParent().getParent().getParent());
//
//        try {
//            Cl1 cl1 = new Cl1();
//            cl1.doSomething();
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//    }

//    void printClasspaths(ClassLoader cl) {
//        System.out.println(cl);
//        URLClassLoader ucl = (URLClassLoader) cl;
//        System.out.println((Arrays.toString(ucl.getURLs())).replace(',','\n'));
//    }
}