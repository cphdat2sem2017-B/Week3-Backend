CREATE TABLE `glazier`.`prices` (
  `id` VARCHAR(25) NOT NULL,
  `price` DECIMAL NULL,
  PRIMARY KEY (`id`));


INSERT INTO `glazier`.`prices` (`id`, `price`) VALUES ('type1', '100');
INSERT INTO `glazier`.`prices` (`id`, `price`) VALUES ('type2', '200');
INSERT INTO `glazier`.`prices` (`id`, `price`) VALUES ('type3', '350');
INSERT INTO `glazier`.`prices` (`id`, `price`) VALUES ('glass', '300');