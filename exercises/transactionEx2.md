# Exercises, Wednesday, February 22
We will today look at two seemingly different topics:

- Transactions
- Exceptions

A database transaction is a series of independent updates / inserts which are either all performed or none of them are performed.

The arch-example is a bank transfer of 300,- dkr between an account A and an account B. Either:

* The amount is deducted from A and added to B
* Or none of the two accounts are affected

To easily share solutions on the blackboard we will use this [google doc sharing document] (https://docs.google.com/document/d/1QvNloN-Wcz505bQciD84EbkbawjRziz4pX2THEaKC4s/edit?usp=sharing)

Note on the **stars** at each assignment. Single * assignments are assignments you should be able to do to pass this subject had there been an exam. Two stars will bring you into the mid level range, and undestanding also the stuff for three stars, you start looking for a top grade.

### Assignment 1 (*):
You shall go in pairs, and set the [small database] (MicroBank.sql) up:

```sql
DROP TABLE IF EXISTS `User`;

CREATE TABLE `User` (
  `userName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`userName`)
)
INSERT INTO `User` VALUES ('ben','3456','cust'),('bill','2345','cust'),('joe','1234','admin');

DROP TABLE IF EXISTS `Account`;

CREATE TABLE `Account` (
  `id` int(11) NOT NULL,
  `owner` varchar(45) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `owner_idx` (`owner`),
  CONSTRAINT `owner` FOREIGN KEY (`owner`) REFERENCES `User` (`userName`) ON DELETE NO ACTION ON UPDATE NO ACTION
) 
INSERT INTO `Account` VALUES (1000,'bill',2500),(1010,'ben',3000);
```

I am not certain you have seen this syntax, but it is a bit cool:

```sql
Update Account set balance = balance - 300 where owner = "bill";
```

Make the four steps in the transaction that draws 300 kr from Bill's account and puts them into Ben's. Keep up from another workspace that is logged into the same database so you follow when the transaction occurs.

### Assignment 2 (*)
Make a jdbc based java method that can move money from one account to another in a transaction. It may, for example. have the following appearance:
`Void transfer (double AMOUNT String FROMUSER, String touser)`

Write a simple driver program (main method), or a unit test that calls your transfer method.

### Assignment 3 (**)
Expand Task 3 such that the transaction is aborted if there is not enough money in the account.

### Assignment 4 (***)
Write transfer method as a stored procedure and call it from JDBC instead. It is important to

---

### Assignment 5 (*)
As you know, you can always go wrong with databases. Write task 2 (or 3, 4) on the catch part will make a rollback.

----

### Assignment 6 (*)
In the three-layer architecture must also take into account exceptions. This task is to make the task 5 such that writes transfer method on so it throws an exception that fits into abstraction layer - example. `TransferException`. 

### Assignment 7 (**)
Make a unit test which check for exceptions being thrown correctly.

### Assignment 8 (***)
Make a trigger which prevents the account to go into negative. For that you need to look into what is called triggers in MySQL. 

The code I used for triggering if one try to transfer more than 500 is:

```sql
CREATE TRIGGER `max500` BEFORE UPDATE ON `Account` FOR EACH ROW
BEGIN
	if (old.balance - new.balance > 500 )then
      signal sqlstate '45000' set message_text = "Max transfer is 500";
	end if;
END
```
