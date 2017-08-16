-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.17-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table eveniment.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `row_state` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_row_state` (`row_state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table eveniment.event
CREATE TABLE IF NOT EXISTS `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `number_of_persons` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `for_user` int(11) NOT NULL,
  `program_id` int(11) NOT NULL,
  `row_state` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_row_state` (`row_state`),
  KEY `FK_event_users_created_by` (`created_by`),
  KEY `FK_event_users_for_user` (`for_user`),
  KEY `FK_event_program_program_id` (`program_id`),
  CONSTRAINT `FK_event_program_program_id` FOREIGN KEY (`program_id`) REFERENCES `program` (`id`),
  CONSTRAINT `FK_event_users_created_by` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_event_users_for_user` FOREIGN KEY (`for_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table eveniment.event_item
CREATE TABLE IF NOT EXISTS `event_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(1024) NULL DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `product_id` int(11) NULL DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `row_state` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_row_state` (`row_state`),
  KEY `FK_event_item_product_product_id` (`product_id`),
  KEY `FK_event_item_event_event_id` (`event_id`),
  CONSTRAINT `FK_event_item_event_event_id` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  CONSTRAINT `FK_event_item_product_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table eveniment.period
CREATE TABLE IF NOT EXISTS `period` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from` date NOT NULL,
  `to` date NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `row_state` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_row_state` (`row_state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table eveniment.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(1024)  NULL DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `rate` varchar(32) NOT NULL,
  `category_id` int(11)  NULL DEFAULT NULL,
  `row_state` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_row_state` (`row_state`),
  KEY `FK_product_category_category_id` (`category_id`),
  CONSTRAINT `FK_product_category_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table eveniment.program
CREATE TABLE IF NOT EXISTS `program` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `row_state` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_row_state` (`row_state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table eveniment.program_categories
CREATE TABLE IF NOT EXISTS `program_categories` (
  `program_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `add_multiple` tinyint(4) NOT NULL DEFAULT '0',
  `row_state` varchar(32) NOT NULL,
  PRIMARY KEY (`program_id`,`category_id`),
  KEY `FK_program_categoreis_category_category_id` (`category_id`),
  KEY `FK_program_categoreis_category_program_id` (`program_id`),
  KEY `IDX_row_state` (`row_state`),
  CONSTRAINT `FK_program_categoreis_category_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FK_program_categoreis_program_program_id` FOREIGN KEY (`program_id`) REFERENCES `program` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table eveniment.program_products
CREATE TABLE IF NOT EXISTS `program_products` (
  `program_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `add_multiple` tinyint(4) NOT NULL DEFAULT '0',
  `row_state` varchar(32) NOT NULL,
  PRIMARY KEY (`program_id`,`product_id`),
  KEY `IDX_row_state` (`row_state`),
  KEY `FK_program_products_product_product_id` (`product_id`),
  KEY `FK_program_products_program_program_id` (`program_id`),
  CONSTRAINT `FK_program_products_product_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_program_products_program_program_id` FOREIGN KEY (`program_id`) REFERENCES `program` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table eveniment.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `fullName` VARCHAR(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `password` varchar(64) NOT NULL,
  `access_level` smallint(6) NOT NULL,
  `row_state` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_row_state` (`row_state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
