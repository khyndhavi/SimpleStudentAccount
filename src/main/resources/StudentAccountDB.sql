
delimiter ;
DROP SCHEMA IF EXISTS `simple_student_account`; 
CREATE SCHEMA `simple_student_account` ;

USE SCHEMA `simple_student_account` ;


CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `user_password` varchar(45) DEFAULT NULL,
  `user_type` varchar(45) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_student_key_idx` (`student_id`),
  CONSTRAINT `user_student_key` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_type` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT '0.00',
  `update_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `student` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) NOT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  `phone_number` varchar(13) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `customer_id_UNIQUE` (`customer_id`),
  UNIQUE KEY `last_name_UNIQUE` (`last_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `student_account` (
  `customer_id` int(11) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL,
  KEY `customer_idx` (`customer_id`),
  KEY `account_idx` (`account_id`),
  CONSTRAINT `account` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `customer` FOREIGN KEY (`customer_id`) REFERENCES `student` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `transaction` (
  `transaction_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `amount` decimal(2,2) DEFAULT NULL,
  `transaction_type` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `transaction_date` datetime DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `transaction_account_idx` (`account_id`),
  CONSTRAINT `transaction_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;