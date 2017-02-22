DROP TABLE IF EXISTS `User`;

CREATE TABLE `User` (
  `userName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`userName`)
);
INSERT INTO `User` VALUES ('ben','3456','cust'),('bill','2345','cust'),('joe','1234','admin');

DROP TABLE IF EXISTS `Account`;

CREATE TABLE `Account` (
  `id` int(11) NOT NULL,
  `owner` varchar(45) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `owner_idx` (`owner`),
  CONSTRAINT `owner` FOREIGN KEY (`owner`) REFERENCES `User` (`userName`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
INSERT INTO `Account` VALUES (1000,'bill',2500),(1010,'ben',3000);
