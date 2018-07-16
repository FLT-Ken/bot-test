CREATE TABLE `user_info` (
  `id` varchar(32) NOT NULL,
  `name` varchar(45) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `email` varchar(100) NOT NULL,
  `pwd` varchar(12) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4