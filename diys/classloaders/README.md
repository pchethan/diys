# View classloader invocation callstack on Java new
`mvn compile exec:java -Dexec.mainClass=com.example.test.ClassLoaderWithNew`

# View compile classpath
`mvn dependency:build-classpath | grep ".jar" | tr ':' '\n'`

# View runtime classpath
`mvn compile exec:java -Dexec.mainClass=com.example.test.PrintClassLoaders`

# View class loader hierarchy
### Standalone App
`java -cp ./target/test-classloader-app.jar "com.example.test.PrintClassLoaders"`

`mvn compile exec:java -Dexec.mainClass=com.example.test.PrintClassLoaders`

Output depends on JRE and how method is invoked

### Servlet
`mvn -Pwar tomcat7:run`
and then

`curl http://localhost:9090/test-classloader-app/`

# Test that show delegation model
mvn test