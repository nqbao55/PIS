-- -------------------------------------------------------------
-- TablePlus 2.9(262)
--
-- https://tableplus.com/
--
-- Database: pis_db
-- Generation Time: 2020-02-17 05:55:58.8760
-- -------------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


CREATE TABLE `pis_bakery` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`username`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pis_cake` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pis_daily_sale` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `bakery_id` bigint(20) unsigned NOT NULL,
  `cake_id` bigint(20) unsigned NOT NULL,
  `piece` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `bakery_id` (`bakery_id`),
  KEY `cake_id` (`cake_id`),
  CONSTRAINT `pis_daily_sale_ibfk_1` FOREIGN KEY (`bakery_id`) REFERENCES `pis_bakery` (`id`),
  CONSTRAINT `pis_daily_sale_ibfk_2` FOREIGN KEY (`cake_id`) REFERENCES `pis_cake` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pis_request` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `bakery_id` bigint(20) unsigned DEFAULT NULL,
  `cake_id` bigint(20) unsigned DEFAULT NULL,
  `piece` int(11) DEFAULT NULL,
  `create_at` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `bakery_id` (`bakery_id`),
  KEY `cake_id` (`cake_id`),
  CONSTRAINT `pis_request_ibfk_1` FOREIGN KEY (`bakery_id`) REFERENCES `pis_bakery` (`id`),
  CONSTRAINT `pis_request_ibfk_2` FOREIGN KEY (`cake_id`) REFERENCES `pis_cake` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pis_setting` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `bakery_id` bigint(20) unsigned DEFAULT NULL,
  `cake_id` bigint(20) unsigned DEFAULT NULL,
  `min_stock` int(11) DEFAULT NULL,
  `max_stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `bakery_id` (`bakery_id`),
  KEY `cake_id` (`cake_id`),
  CONSTRAINT `pis_setting_ibfk_1` FOREIGN KEY (`bakery_id`) REFERENCES `pis_bakery` (`id`),
  CONSTRAINT `pis_setting_ibfk_2` FOREIGN KEY (`cake_id`) REFERENCES `pis_cake` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pis_store` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `bakery_id` bigint(20) unsigned NOT NULL,
  `cake_id` bigint(20) unsigned NOT NULL,
  `piece` int(11) NOT NULL,
  `update_at` time DEFAULT NULL,
  `daily_update` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `bakery_id` (`bakery_id`),
  KEY `cake_id` (`cake_id`),
  CONSTRAINT `pis_store_ibfk_1` FOREIGN KEY (`bakery_id`) REFERENCES `pis_bakery` (`id`),
  CONSTRAINT `pis_store_ibfk_2` FOREIGN KEY (`cake_id`) REFERENCES `pis_cake` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pis_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `bakery_id` bigint(20) unsigned DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`username`),
  UNIQUE KEY `id` (`id`),
  KEY `bakery_id` (`bakery_id`),
  CONSTRAINT `pis_user_ibfk_1` FOREIGN KEY (`bakery_id`) REFERENCES `pis_bakery` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `pis_bakery` (`id`, `name`, `username`, `password`, `address`, `phone`, `email`) VALUES ('1', 'Bao Nguyen', 'baonguyen', '1234', '27a Hoang Viet', '', 'nqbao.qn@gmail.com'),
('2', 'Ping Huynh', 'pinghuynh', '1234', '27a Hoang Viet', '', 'nqbao.qn@gmail.com');

INSERT INTO `pis_cake` (`id`, `name`, `price`) VALUES ('1', 'Cake 1', '2'),
('2', 'Cake 2', '4'),
('3', 'Cake 3', '4'),
('4', 'Cake 4', '5'),
('5', 'Cake 5', '6');

INSERT INTO `pis_daily_sale` (`id`, `bakery_id`, `cake_id`, `piece`) VALUES ('1', '1', '1', '10'),
('2', '1', '2', '5'),
('3', '1', '3', '5'),
('4', '1', '4', '5'),
('5', '1', '5', '5'),
('6', '2', '1', '10'),
('7', '2', '2', '10'),
('8', '2', '3', '10'),
('9', '2', '4', '10'),
('10', '2', '5', '10');

INSERT INTO `pis_setting` (`id`, `bakery_id`, `cake_id`, `min_stock`, `max_stock`) VALUES ('1', '1', '1', '5', '50'),
('2', '1', '2', '5', '20'),
('3', '1', '3', '5', '20'),
('4', '1', '4', '5', '20'),
('5', '1', '5', '5', '20'),
('6', '2', '1', '5', '20'),
('7', '2', '2', '5', '20'),
('8', '2', '3', '5', '20'),
('9', '2', '4', '5', '20'),
('10', '2', '5', '5', '20');

INSERT INTO `pis_user` (`id`, `username`, `password`, `bakery_id`, `role`) VALUES ('1', 'bao', '$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2', NULL, 'ADMIN'),
('3', 'ping', '$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2', '2', 'USER');




/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;