# Friday calendar

## Getting started

Friday Calendar is an application that prevents its users from being late.

## Prerequisities

* openjdk 17.0.1

## Build the project

```
./mwnw clean install
```

This command will install all the dependencies.
It will even install maven, npm et node if there are not already installed.

## Launch the calendar

```
java -jar target/FridayCalendar.jar
```

It will run the built project and run it on 8080 server port.
In case the port is already taken, the application will not launch.

## Features

This calendar has the following features:

* It can create an user
* It can display an event

## Still on progress

Friday is also on progress and not fully implemented, we are still looking for
those features:

* Synchronization with Google Calendar
* Add/Update/Remove an event from the database
* Determine how much times it takes to go from one location to another
* Prevent event from being accessed by any other user
* Implement calendar import using icalendar norm
* Add recurring event
