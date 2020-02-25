-- -------------------------------------------------------------
-- TablePlus 2.9(262)
--
-- https://tableplus.com/
--
-- Database: pis_db
-- Generation Time: 2020-02-25 07:32:58.4940
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pis_delivery` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `create_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `pis_bakery` (`id`, `name`, `username`, `password`, `address`, `phone`, `email`) VALUES ('5', 'Binh Nguyen', 'binh', 'baonguyen', 'HCM', '0989382810', 'nqbao.qn@gmail.com'),
('6', 'Cafe SaiGon', 'ping', '111111', 'HCM', '0989382810', 'nqbao.qn@gmail.com'),
('7', 'Tony Dang', 'hoa', '11111111', 'HCM', '0989382810', 'nqbao.qn@gmail.com'),
('8', 'Ping Huynh', 'huynh', '11111111', 'HCM', '0987654321', 'nqbao.qn@gmail.com');

INSERT INTO `pis_cake` (`id`, `name`, `price`, `piece_on_tray`) VALUES ('9', 'Cake 1', '2', '5'),
('10', 'Cake 2', '2', '5'),
('11', 'Cake 3', '3', '5'),
('12', 'Cake 4', '5', '5'),
('13', 'Cake 5', '3', '5'),
('14', 'Cake 6', '4', '5');

INSERT INTO `pis_daily_sale` (`id`, `bakery_id`, `cake_id`, `piece`) VALUES ('19', '5', '9', '5'),
('20', '6', '9', '5'),
('21', '5', '10', '5'),
('22', '6', '10', '5'),
('23', '5', '11', '5'),
('24', '6', '11', '5'),
('25', '5', '12', '5'),
('26', '6', '12', '5'),
('27', '5', '13', '5'),
('28', '6', '13', '5'),
('29', '5', '14', '5'),
('30', '6', '14', '5'),
('31', '7', '9', '5'),
('32', '7', '10', '5'),
('33', '7', '11', '5'),
('34', '7', '12', '5'),
('35', '7', '13', '5'),
('36', '7', '14', '5'),
('37', '8', '9', '5'),
('38', '8', '10', '5'),
('39', '8', '11', '5'),
('40', '8', '12', '5'),
('41', '8', '13', '5'),
('42', '8', '14', '5');

INSERT INTO `pis_delivery` (`id`, `create_at`) VALUES ('6', '2020-02-25'),
('7', '2020-02-25');

INSERT INTO `pis_delivery_detail` (`id`, `create_at`, `bakery_id`, `cake_id`, `piece`, `tray`, `delivery_id`) VALUES ('31', '2020-02-25', '5', '9', '20', '4', '6'),
('32', '2020-02-25', '5', '10', '15', '3', '6'),
('33', '2020-02-25', '5', '11', '15', '3', '6'),
('34', '2020-02-25', '5', '12', '15', '3', '6'),
('35', '2020-02-25', '5', '13', '15', '3', '6'),
('36', '2020-02-25', '5', '14', '15', '3', '6'),
('37', '2020-02-25', '6', '9', '15', '3', '7'),
('38', '2020-02-25', '6', '10', '15', '3', '7'),
('39', '2020-02-25', '6', '11', '15', '3', '7'),
('40', '2020-02-25', '6', '12', '15', '3', '7'),
('41', '2020-02-25', '6', '13', '15', '3', '7'),
('42', '2020-02-25', '6', '14', '15', '3', '7');

INSERT INTO `pis_request` (`id`, `bakery_id`, `cake_id`, `piece`, `create_at`) VALUES ('3', '6', '11', '3', '2020-02-22'),
('4', '6', '9', '5', '2020-02-22'),
('5', '6', '12', '4', '2020-02-22'),
('6', '6', '13', '4', '2020-02-22'),
('7', '6', '14', '1', '2020-02-22');

INSERT INTO `pis_setting` (`id`, `bakery_id`, `cake_id`, `min_stock`, `max_stock`) VALUES ('19', '5', '9', '1', '5'),
('20', '6', '9', '1', '5'),
('21', '5', '10', '1', '5'),
('22', '6', '10', '1', '5'),
('23', '5', '11', '1', '5'),
('24', '6', '11', '1', '5'),
('25', '5', '12', '1', '5'),
('26', '6', '12', '1', '5'),
('27', '5', '13', '1', '5'),
('28', '6', '13', '1', '5'),
('29', '5', '14', '1', '5'),
('30', '6', '14', '1', '5'),
('31', '7', '9', '1', '5'),
('32', '7', '10', '1', '5'),
('33', '7', '11', '1', '5'),
('34', '7', '12', '1', '5'),
('35', '7', '13', '1', '5'),
('36', '7', '14', '1', '5'),
('37', '8', '9', '1', '5'),
('38', '8', '10', '1', '5'),
('39', '8', '11', '1', '5'),
('40', '8', '12', '1', '5'),
('41', '8', '13', '1', '5'),
('42', '8', '14', '1', '5');

INSERT INTO `pis_store` (`id`, `bakery_id`, `cake_id`, `piece`, `update_at`, `daily_update`) VALUES ('15', '5', '9', '25', NULL, NULL),
('16', '6', '9', '25', NULL, NULL),
('17', '5', '10', '25', NULL, NULL),
('18', '6', '10', '25', NULL, NULL),
('19', '5', '11', '25', NULL, NULL),
('20', '6', '11', '25', NULL, NULL),
('21', '5', '12', '25', NULL, NULL),
('22', '6', '12', '25', NULL, NULL),
('23', '5', '13', '25', NULL, NULL),
('24', '6', '13', '25', NULL, NULL),
('25', '5', '14', '25', NULL, NULL),
('26', '6', '14', '25', NULL, NULL),
('27', '7', '9', '10', NULL, NULL),
('28', '7', '10', '10', NULL, NULL),
('29', '7', '11', '10', NULL, NULL),
('30', '7', '12', '10', NULL, NULL),
('31', '7', '13', '10', NULL, NULL),
('32', '7', '14', '10', NULL, NULL),
('33', '8', '9', '25', NULL, NULL),
('34', '8', '10', '10', NULL, NULL),
('35', '8', '11', '10', NULL, NULL),
('36', '8', '12', '10', NULL, NULL),
('37', '8', '13', '10', NULL, NULL),
('38', '8', '14', '10', NULL, NULL);

INSERT INTO `pis_user` (`id`, `username`, `password`, `bakery_id`, `role`) VALUES ('1', 'bao', '$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2', NULL, 'ADMIN'),
('5', 'binh', '$2a$10$ayAObh5ash.w6/88mEvomOmMMixvf9SeUYc.goq1fPTqvHE9Tc3Qi', '5', 'USER'),
('6', 'ping', '$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2', '6', 'USER'),
('7', 'hoa', '$2a$10$cM92K80z7HUYlmGl2D524.IfC0ub5CaDFcrL6/iX.ebZ9X6uq0Y7m', '7', 'USER'),
('8', 'huynh', '$2a$10$T5Vxa/UIIE3vgEJZIEbsquJszYx.mEB4ULmFEdAtqr5QKxIY3Gzma', '8', 'USER');




/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;