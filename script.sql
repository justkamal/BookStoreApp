CREATE DATABASE `bookstore` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

use bookstore;

CREATE TABLE `book` (
  `isbn` int NOT NULL,
  `title` varchar(100) NOT NULL,
  `author` varchar(45) NOT NULL,
  `price` float NOT NULL,
  `available_copies` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`isbn`),
  KEY `titleIndex` (`title`),
  KEY `authorIndex` (`author`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO book VALUES(313, "new title", "new author", 90.4, 3);
INSERT INTO book VALUES(315, "new title2", "new author2", 90.4, 3);
INSERT INTO book VALUES(318, "new title3", "new author3", 90.4, 2);
INSERT INTO book VALUES(314, "new title4", "new author4", 90.4, 1);
INSERT INTO book VALUES(317, "new title5", "new author5", 90.4, 3);
