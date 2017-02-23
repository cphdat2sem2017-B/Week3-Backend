-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `library` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `library` ;

-- -----------------------------------------------------
-- Table `library`.`Book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`Book` (
  `isbn` INT NOT NULL COMMENT '',
  `title` VARCHAR(45) NOT NULL COMMENT '',
  `language` VARCHAR(3) NULL COMMENT '',
  PRIMARY KEY (`isbn`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`Location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`Location` (
  `locationId` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `description` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`locationId`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`Copy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`Copy` (
  `copyNo` INT NOT NULL COMMENT '',
  `maxLoanDates` INT NULL DEFAULT 30 COMMENT '',
  `locationId` INT NOT NULL COMMENT '',
  `isbn` INT NOT NULL COMMENT '',
  PRIMARY KEY (`copyNo`, `isbn`)  COMMENT '',
  INDEX `fk_Copy_Location1_idx` (`locationId` ASC)  COMMENT '',
  INDEX `fk_Copy_Book1_idx` (`isbn` ASC)  COMMENT '',
  CONSTRAINT `fk_Copy_Location1`
    FOREIGN KEY (`locationId`)
    REFERENCES `mydb`.`Location` (`locationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Copy_Book1`
    FOREIGN KEY (`isbn`)
    REFERENCES `mydb`.`Book` (`isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
PACK_KEYS = DEFAULT;


-- -----------------------------------------------------
-- Table `library`.`Author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`Author` (
  `authorID` INT NOT NULL COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`authorID`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`BookAuthor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`BookAuthor` (
  `isbn` INT NOT NULL COMMENT '',
  `authorID` INT NOT NULL COMMENT '',
  PRIMARY KEY (`isbn`, `authorID`)  COMMENT '',
  INDEX `fk_Book_has_Author_Author1_idx` (`authorID` ASC)  COMMENT '',
  INDEX `fk_Book_has_Author_Book_idx` (`isbn` ASC)  COMMENT '',
  CONSTRAINT `fk_Book_has_Author_Book`
    FOREIGN KEY (`isbn`)
    REFERENCES `mydb`.`Book` (`isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Book_has_Author_Author1`
    FOREIGN KEY (`authorID`)
    REFERENCES `mydb`.`Author` (`authorID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
PACK_KEYS = DEFAULT;


-- -----------------------------------------------------
-- Table `library`.`Borrower`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`Borrower` (
  `ssn` INT NOT NULL COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  `address` VARCHAR(45) NOT NULL COMMENT '',
  `phone` VARCHAR(45) NULL COMMENT '',
  `status` TINYINT(1) NULL DEFAULT 1 COMMENT '',
  PRIMARY KEY (`ssn`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`Reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`Reservation` (
  `isbn` INT NOT NULL COMMENT '',
  `ssn` INT NOT NULL COMMENT '',
  `reserveDate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '',
  PRIMARY KEY (`isbn`, `ssn`)  COMMENT '',
  INDEX `fk_Book_has_Borrower_Borrower1_idx` (`ssn` ASC)  COMMENT '',
  INDEX `fk_Book_has_Borrower_Book1_idx` (`isbn` ASC)  COMMENT '',
  CONSTRAINT `fk_Book_has_Borrower_Book1`
    FOREIGN KEY (`isbn`)
    REFERENCES `mydb`.`Book` (`isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Book_has_Borrower_Borrower1`
    FOREIGN KEY (`ssn`)
    REFERENCES `mydb`.`Borrower` (`ssn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`Loan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`Loan` (
  `ssn` INT NOT NULL COMMENT '',
  `copyNo` INT NOT NULL COMMENT '',
  `isbn` INT NOT NULL COMMENT '',
  `loanDate` DATE NOT NULL COMMENT '',
  PRIMARY KEY (`ssn`, `copyNo`, `isbn`)  COMMENT '',
  INDEX `fk_Borrower_has_Copy_Copy1_idx` (`copyNo` ASC, `isbn` ASC)  COMMENT '',
  INDEX `fk_Borrower_has_Copy_Borrower1_idx` (`ssn` ASC)  COMMENT '',
  CONSTRAINT `fk_Borrower_has_Copy_Borrower1`
    FOREIGN KEY (`ssn`)
    REFERENCES `mydb`.`Borrower` (`ssn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Borrower_has_Copy_Copy1`
    FOREIGN KEY (`copyNo` , `isbn`)
    REFERENCES `mydb`.`Copy` (`copyNo` , `isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `library`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `library`.`Copy_AFTER_INSERT` AFTER INSERT ON `Copy` FOR EACH ROW
BEGIN
END
$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
