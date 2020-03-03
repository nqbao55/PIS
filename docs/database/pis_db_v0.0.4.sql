-- -------------------------------------------------------------
-- TablePlus 2.9(262)
--
-- https://tableplus.com/
--
-- Database: pis_db
-- Generation Time: 2020-02-28 07:19:42.1160
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
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`username`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pis_cake` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `piece_on_tray` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pis_delivery` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `create_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pis_delivery_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `create_at` date DEFAULT NULL,
  `bakery_id` bigint(20) unsigned DEFAULT NULL,
  `cake_id` bigint(20) unsigned DEFAULT NULL,
  `piece` int(11) DEFAULT NULL,
  `tray` float DEFAULT NULL,
  `delivery_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `bakery_id` (`bakery_id`),
  KEY `cake_id` (`cake_id`),
  KEY `delivery_id` (`delivery_id`),
  CONSTRAINT `pis_delivery_detail_ibfk_1` FOREIGN KEY (`bakery_id`) REFERENCES `pis_bakery` (`id`),
  CONSTRAINT `pis_delivery_detail_ibfk_2` FOREIGN KEY (`cake_id`) REFERENCES `pis_cake` (`id`),
  CONSTRAINT `pis_delivery_detail_ibfk_3` FOREIGN KEY (`delivery_id`) REFERENCES `pis_delivery` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pis_request` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `bakery_id` bigint(20) unsigned DEFAULT NULL,
  `cake_id` bigint(20) unsigned DEFAULT NULL,
  `piece` int(11) DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `bakery_id` (`bakery_id`),
  KEY `cake_id` (`cake_id`),
  CONSTRAINT `pis_request_ibfk_1` FOREIGN KEY (`bakery_id`) REFERENCES `pis_bakery` (`id`),
  CONSTRAINT `pis_request_ibfk_2` FOREIGN KEY (`cake_id`) REFERENCES `pis_cake` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pis_store` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `bakery_id` bigint(20) unsigned NOT NULL,
  `cake_id` bigint(20) unsigned NOT NULL,
  `piece` int(11) NOT NULL,
  `update_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `daily_update` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `bakery_id` (`bakery_id`),
  KEY `cake_id` (`cake_id`),
  CONSTRAINT `pis_store_ibfk_1` FOREIGN KEY (`bakery_id`) REFERENCES `pis_bakery` (`id`),
  CONSTRAINT `pis_store_ibfk_2` FOREIGN KEY (`cake_id`) REFERENCES `pis_cake` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;