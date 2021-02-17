# Invocation of classloader by JVM on java new
`mvn compile exec:java -Dexec.mainClass=com.example.test.ClassLoaderWithNew`

# Print compile time classpath
`mvn dependency:build-classpath | grep ".jar" | tr ':' '\n'`

# Print Classloaders
### App
Output depends on JRE and how method is invoked

`java -cp ./target/test-classloader-app.jar "com.example.test.PrintClassLoaders"`

`mvn compile exec:java -Dexec.mainClass=com.example.test.PrintClassLoaders`

### Servlet
`mvn -Pwar tomcat7:run`

and then

`curl http://localhost:9090/test-classloader-app/`

# Delegation model and other tests
mvn test