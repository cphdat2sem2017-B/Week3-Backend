#Day 1: Performance
1. MySql Indexes,  
2. Stored Procedures  
3. JDBC Prepared Statements

##Index in Mysql
Updating a table with indexes takes more time than updating a table without (because the indexes also need an update).   
So you should only create indexes on columns (and tables) that will be frequently searched against.  
**Create Index**  
- `CREATE INDEX index_name
ON table_name (column_name)`  
**Create unique index**  
- `CREATE UNIQUE INDEX index_name
ON table_name (column_name)`  
**Create index on combined columns**  
- `CREATE INDEX PIndex
ON Persons (LastName, FirstName)`

####Why using an index
Searching through a large resultset for a particular value takes time.
 `SELECT address FROM customer WHERE lastname = 'Hansen' AND firstname = 'Jesper'`
We would have to through every tuple in the table.
If the lastname column was alphabetisized on the other hand we could make a binary search and be done with it much faster.

####Why not to use index
Well it is extra overhead when we insert and update data, so we should only do it on columns that we frequently select from and on columns that are not often updated.

####Are their any default indexes on a database table?
Yes when we make a primary key on a column. That column will then be indexed.

##Stored Procedures
A Stored Procedure in a relational database is like a method in a java program. A procedure has a name, a parameter list, and SQL statement(s).  

**Stored procedures are fast.**   
MySQL server takes some advantage of caching, just as prepared statements do. The main speed gain comes from reduction of network traffic. If you have a repetitive task that requires checking, looping, multiple statements, and no user interaction, do it with a single call to a procedure that's stored on the server.  
**Stored procedures are portable.**   
When you write your stored procedure in SQL, you know that it will run on every platform that MySQL runs on, without obliging you to install an additional runtime-environment package, or set permissions for program execution in the operating system, or deploy different packages if you have different computer types. That's the advantage of writing in SQL rather than in an external language like Java or C or PHP.  
**Stored procedures are always available** as 'source code' in the database itself. And it makes sense to link the data with the processes that operate on the data.
[see reference.](http://www.w3resource.com/mysql/mysql-procedure.php)  
**User Defined Funcitons (UDF)** are like Stored Procedures but can be accessed directly from Sql statements and does not need the CALL <sp-name> keyword to run. No DML allowed here. [Get more info here.](https://blogs.msdn.microsoft.com/pradeepsvs/2014/10/08/difference-between-a-stored-procedure-and-function/).  
**SP examples**  
####Define the stored procedure like this
```DELIMITER //  
CREATE PROCEDURE GetOfficeByCountry(IN countryName VARCHAR(255))  
 BEGIN  
 SELECT *   
 FROM offices  
 WHERE country = countryName;  
 END //  
DELIMITER ;  
```
####And call the stored procedure like this
`CALL GetOfficeByCountry('USA');` [See more here](http://www.mysqltutorial.org/stored-procedures-parameters.aspx).

##JDBC prepared statements
Benefits are:  
Protection against SQL Injection  
Easy to insert parameters into the SQL statement.  
Easy to reuse the PreparedStatement with new parameters.  
May increase performance of executed statements.  
Enables easier batch updates.  

**Example**
```
String sql = "update people set firstname=? , lastname=? where id=?";  
PreparedStatement preparedStatement = connection.prepareStatement(sql);  
preparedStatement.setString(1, "Gary");  
preparedStatement.setString(2, "Larson");  
preparedStatement.setLong  (3, 123);  
int rowsAffected = preparedStatement.executeUpdate();  
```

####Reuse your statement objects in the java code
[See example here](http://tutorials.jenkov.com/jdbc/preparedstatement.html#preparedstatement-performance).