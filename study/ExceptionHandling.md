# Exception handling

Exception handling is a programming language construct which exist in almost all programming languages. The usage scenario is well illustrated by considering the relationship between the team of programmers who wrote the jdbc driver and you who are writing a program using that driver.

The developers behind jdbc is writing some low level code which communicates to the MySQL database. One key method is the `executeQuery()` method. The developers here can end up in situations where

* The query string they got from the user is not valid SQL
* The query refers to a table which you do not have the right to access
* The username of the connection is not valid (could for example have been changed since the connection was made)
* The network connection to the database was lost
* and so on...

The JDBC developers know that all these situations can happen and they can recognize when it happens.

But they do not know what to do about it. It is you who in your program who must decide what to do when something like that happens.

In summary:

* The jdbc developers know **when** something bad happenes.
* You know **what** do do when something bad happens

The exception handling mechanism is designed with this in mind. The jdbc developers **throw** an exception (something bad happened), and you **catch** that exception (do something with it).

## Three layer architecture
In a three layer architecture, the datamappers have direct contact with the database and the jdbc objects (connections, statements, result sets). The purpose of the persistence layer is to encapsulate all this database stuff from the upper two layers. 

However, it is rarely possible to know **what** to do if something goes bad in the database. But we do know **when** something goes wrong, namely if the jdbc layer throws an exception. 

What the database mapper layer can do is to catch and rethrow.

```java
try (stmt = con.prepareStatement(...) ){
	// use statement to talk to database
} catch (SQLException e){
	throw new MapperException("Failed to do database stuff");
}
```
Often the MapperException is then passed up through the layers. In the end it might show up as a user alert "Failed to save changes, try again", and perhaps you redirect the user back to a web page where you know you are able to show a consistent page again.

