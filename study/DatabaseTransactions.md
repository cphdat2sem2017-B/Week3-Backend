# Database transactions

The purpose of transactions are to ensure that the database remains **consistent**. This includes several properties which together is often called the ACID criteria (see below).

## Programming model
In MySQL a [transaction is specified](https://dev.mysql.com/doc/refman/5.7/en/commit.html) as:

```sql
START TRANSACTION
... Updates and inserts
COMMIT
```
Or, if something goes wrong

```sql
START TRANSACTION
... Updates and inserts
ROLLBACK
```
From within Java, using JDBC, we will often have this structure on our mapping methods:

```java
/*
salesForWeek is a map: 
Mocca -> 255
HondurasHighland -> 27
...
COFFEES is a table
COF_NAME -- Name of coffee
SALES -- Value of last week of sales
TOTAL -- Total value of sales
*/
public void updateCoffeeSales(HashMap<String, Integer> salesForWeek)
    throws SQLException {

    PreparedStatement updateSales = null;
    PreparedStatement updateTotal = null;

    String updateString =
        "update COFFEES set SALES = ? where COF_NAME = ?";

    String updateStatement =
        "update COFFEES set TOTAL = TOTAL + ? where COF_NAME = ?";

    try (
        updateSales = con.prepareStatement(updateString);
        updateTotal = con.prepareStatement(updateStatement);){
		//After the auto-commit mode is disabled, no SQL statements 
		//are committed until you call the method commit explicitly. 
        con.setAutoCommit(false);

        for (Map.Entry<String, Integer> e : salesForWeek.entrySet()) {
            updateSales.setInt(1, e.getValue().intValue());
            updateSales.setString(2, e.getKey());
            updateSales.executeUpdate();
            updateTotal.setInt(1, e.getValue().intValue());
            updateTotal.setString(2, e.getKey());
            updateTotal.executeUpdate();
            con.commit();
        }
    } catch (Exception e ) {
        if (con != null) {
            try {
                System.err.print("Transaction is being rolled back");
                con.rollback();
            } catch(SQLException excep) {
                JDBCTutorialUtilities.printSQLException(excep);
            }
        }
    } finally{
    	con.setAutoCommit(true);
    }
}
```

Notice in particular how the commit and rollback calls are placed in relation to the exception handling.


## ACID 
([taken from Wikipedia](https://en.wikipedia.org/wiki/ACID))
###Atomicity
Atomicity requires that each transaction be "all or nothing": if one part of the transaction fails, then the entire transaction fails, and the database state is left unchanged. An atomic system must guarantee atomicity in each and every situation, including power failures, errors and crashes. To the outside world, a committed transaction appears (by its effects on the database) to be indivisible ("atomic"), and an aborted transaction does not happen.

###Consistency
The consistency property ensures that any transaction will bring the database from one valid state to another. Any data written to the database must be __valid according to all defined rules__, including _constraints, cascades, triggers, and any combination thereof_. This does not guarantee correctness of the transaction in all ways the application programmer might have wanted (that is the responsibility of application-level code), but merely that any programming errors cannot result in the violation of any defined rules.

###Isolation
The isolation property ensures that the concurrent execution of transactions results in a system state that would be obtained if transactions were executed sequentially, i.e., one after the other. Providing isolation is the main goal of _concurrency control_. Depending on the concurrency control method (i.e., if it uses strict - as opposed to relaxed - serializability), the effects of an incomplete transaction might not even be visible to another transaction.

###Durability
The durability property ensures that once a transaction has been committed, it will remain so, even in the event of power loss, crashes, or errors. In a relational database, for instance, once a group of SQL statements execute, the results need to be stored permanently (even if the database crashes immediately thereafter). To defend against power loss, transactions (or their effects) must be recorded in a non-volatile memory.

In our second semester, you are not likely to encounter situations where Isolation could be an issue - but its key message is that an outside observer (the one not doing the transaction) one cannot see that a transaction is underway. Nothing is visible until the commit.
