DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `register_id` varchar(60) NOT NULL,
  `password` varchar(60) NOT NULL,
  `gender` enum('male','female') NOT NULL,
  `register_time` date NOT NULL,
  `last_login_time` date NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `avatar_url` varchar(250),
  `describe` varchar(250) DEFAULT 'Hello World!',
  `valid` boolean DEFAULT TRUE,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `register_id_UNIQUE` (`register_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

