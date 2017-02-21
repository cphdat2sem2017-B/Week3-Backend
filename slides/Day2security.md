#Day 2: Security and Maintainability
This day is about some of the security concerns we have on the backend. And how we build to maintain.

##Reading:
[If you only read one thing](http://www.unixwiz.net/techtips/sql-injection.html)

##Sql Injection
![](../img/sqlinjection.png)    

Always make sure the input from users is checked before it is allowed into the database.

###Counteract by
1. Sanitize user input  
2. use JDBC PreparedStatement object with placeholders

##Demo with java project
Find it [here](../demo/SqlInjectionDemo)  



##Database views
###1. Views can hide complexity

If you have a query that requires joining several tables, or has complex logic or calculations, you can code all that logic into a view, then select from the view just like you would a table.

###2. Views can be used as a security mechanism

A view can select certain columns and/or rows from a table, and permissions set on the view instead of the underlying tables. This allows surfacing only the data that a user needs to see.

###3. Views can simplify supporting legacy code

If you need to refactor a table that would break a lot of code, you can replace the table with a view of the same name. The view provides the exact same schema as the original table, while the actual schema has changed. This keeps the legacy code that references the table from breaking, allowing you to change the legacy code at your leisure.

##Demo
- Create a new user on the database.  
- Create a new view.  
- Give read acces to the user on the view  
`GRANT SELECT ON database1.view1 TO 'someuser'@'somehost';`  
- Create a java program that uses 2 different connections (with different users)
	- One user is used to read from the view
	- Another user is used to insert data to a table

##3 layer - Architecture 
![text](../img/tavle.png)
See the demo project for idea of how to create the
1. Facade pattern and
2. DataMaper pattern
plus also how to structure a web project with folders and java packages to uphold the 3 layer structure as shown on the white board drawing above.