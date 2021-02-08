CREATE TABLE `employee` (
  `employeename` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `port` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`employeename`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci