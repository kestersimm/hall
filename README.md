# Monty's choice
I have written a spring boot app with a rest service which will try to solve the problem.


# Install

    $ mvn install
   
## Start up with maven

    $ mvn spring-boot:run 

## Start up with java

    $ java -jar /target/hall-0.0.1-SNAPSHOT.jar
## Calling the service
with default value: 100000
http://localhost:8088/run/monty

    $ curl http://localhost:8088/run/monty
with url parameter games
http://localhost:8088/run/monty?games=9999999

    $ curl http://localhost:8088/run/monty?games=9999999
## Example response

    {"games":1000000,"doors":3,"wins":{"notSwitched":333806.0,"notSwitchedPercent":33.38,"switched":666194.0,"switchedPercent":66.62},"statistics":{"prizeDoor":997830,"chosenDoor":1000229,"montyDoor":1001358,"makeASwitch":998413}}
