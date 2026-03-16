# Swing Table Editor

A test for a simple Swing editor for a database table.


To run the test, first start the docker container with the command:

```
docker-compose up
```

Then run the app with 

~~~
./gradlew bootRun
~~~

## Architecture and Swing libraries

The app displays an editable table of students, which can be filtered. It also displays the average grade of all students, which is updated by MVC.

This version in this branch uses **glazedlists** for the list model and **swing-fx-properties** for the rest of the code. There is another branch, called `all-jgoodies` which uses **jgoodies** for everything.
