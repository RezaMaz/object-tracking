<h1>Object Tracking Management</h1>
it is a software for creating mobile objects and log trajectory(history of coordinations which object passes).

mobile object can be <ins>car</ins>,<ins>person</ins>,<ins>airplane</ins> and <ins>ship</ins>.

it's tiny simple software with inspiration of two below sites:
<ul>
<li>https://www.flightradar24.com</li>
<li>https://www.marinetraffic.com</li>
</ul>

main entity in our software is <strong>Car</strong>.
it has Create,Read,Update,Delete operation.

<i>collision detection</i> and <i>outlier detection</i> implemented in car service.

<h1>How to run:</h1>

This application has Tomcat 8 embedded. No Tomcat installation is necessary. You run it using the java -jar command.

<ol>
<li>Clone this repository</li>
<li>Make sure you are using JDK 1.8 and Maven 3.x</li>
<li>You can build the project and run the tests by running mvn clean package
Once successfully built, you can run the service by one of these two methods:
        java -jar target/object-tracking-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run
</li>
</ol>

<div>Check the stdout log file to make sure no exceptions are thrown</div>
<div>Once the application runs you should see something like this</div>
<br>
<br>
<div>2020-07-25 00:20:04.884  INFO 12996 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''</div>
<div>2020-07-25 00:20:04.892  INFO 12996 --- [           main] i.o.o.ObjectTrackingApplication          : Started ObjectTrackingApplication in 7.257 seconds (JVM running for 8.733)</div>
