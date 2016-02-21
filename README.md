# url-shortener
My attempt at formulating an answer to the interview question "design a URL shortener".

Built with:
 * [Spark](http://sparkjava.com/) as the web framework.
 * sql2o as a SQL library
 * NodeJS for testing
 * MySQL for relational storage

Requirements:
* MySql
* Java 8
* Tests require nodeJS

Run locally:
Run the WebApplication.main method.
Or build a deployable jar:

`mvn package`

Then:

 `java -jar target/url-shortener-1.0.0.jar`

Plans:
* Learn how to do decent load tests in order to determine what the capabilities of the system are at it's current state
* Scale up tests and system design to handle additional features and load
* Practice scaling a system from scratch
