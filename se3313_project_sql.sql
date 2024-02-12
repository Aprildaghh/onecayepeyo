drop schema if exists `se3313_project`;
create schema `se3313_project`;
USE `se3313_project`;

DROP TABLE IF EXISTS `coffee_maker`;

--
-- Table structure for table `coffee_maker`
--

CREATE TABLE `coffee_maker` (
  `date` date NOT NULL,
  `total_cups` int NOT NULL,
  PRIMARY KEY (`date`)
);


INSERT INTO `coffee_maker` 
VALUES 
(date('2023-12-29'),5),
(date('2023-12-30'),3);