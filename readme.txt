1.Create new project using Spring Tool Suite. Click File – New – Spring Starter Project. Fill inputs as required.
2. On the next check Thymeleaf as a template engine. Click Finish.
3. Spring Starter Project will be generated with new Maven project with pom.xml where all needed dependencies for the project are added.
4.create a "index.html" file in "src/main/resources/templates".
5. The last step is creating a controller which is able to show us the index.html page. Create new package “controller” in src/main/java and then new Index class in this package.
Add @Controller stereotype to the class and @RequestMapping(“/”) to intex() method whick return the name of template.
6.Now you can run the project in terminal using command "mvn -e spring-boot:run".
7. Your application will be available on http://localhost:8080.
