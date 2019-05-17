CREATE DATABASE IF NOT EXISTS `bahari_test`;

USE `bahari_test`;

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB;

-- Dumping data for table `hibernate_sequence`

LOCK TABLES `hibernate_sequence` WRITE;

INSERT INTO `hibernate_sequence` VALUES (1);

UNLOCK TABLES;


DROP TABLE IF EXISTS `privileges`;

CREATE TABLE `privileges` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB;


DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `issystem` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB;



DROP TABLE IF EXISTS `roles_privileges`;

CREATE TABLE `roles_privileges` (
  `role_id` int(11) NOT NULL,
  `privilege_id` varchar(45) NOT NULL,
  KEY `FK629oqwrudgp5u7tewl07ayugj` (`role_id`)
) ENGINE=InnoDB ;



DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL DEFAULT '""',
  `email_address` varchar(45) NOT NULL,
  `created_at` date NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `updated_at` date NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `usertype` varchar(45) NOT NULL,
  `isenabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Id`) 
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`)
) ENGINE=InnoDB;

INSERT INTO privileges (name) VALUES ("USER_CREATE");
INSERT INTO privileges (name) VALUES ("USER_READ");
INSERT INTO privileges (name) VALUES ("USER_UPDATE");
INSERT INTO privileges (name) VALUES ("USER_DELETE");

INSERT INTO roles (name,issystem) VALUES ('ROLE_USER',1);
INSERT INTO roles (name,issystem) VALUES ('ROLE_ADMIN',1);

INSERT INTO roles_privileges (role_id,privilege_id) SELECT r.id,p.id FROM roles r, privileges p WHERE r.name = 'ROLE_ADMIN';

INSERT INTO users
(
`first_name`,
`last_name`,
`phone`,
`email_address`,
`created_at`,
`created_by`,
`updated_at`,
`updated_by`,
`username`,
`password`,
`usertype`,
`isenabled`)
VALUES
(
"Admin",
"Admin",
"",
"bahariadmin@upepo.io",
now(),
"Admin",
now(),
"Admin",
"Admin",
"$2a$10$f.5eN9SAoDAm6LXl1A9GReqvZREpUJ1N34bGx9t.ZVBHxlLFAzXGW",
"Administrator",
1);

INSERT INTO user_roles (user_id,role_id)  select u.id,r.id from users u, roles r  where u.username='Admin' and r.name ='ROLE_ADMIN';