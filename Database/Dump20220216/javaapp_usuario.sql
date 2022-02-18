-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: javaapp
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idPess` int unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `cpf` char(11) NOT NULL,
  PRIMARY KEY (`idPess`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Sergio Mergen','93950136053'),(2,'Wilson Steinmetz','52331342091'),(3,'Wilson Steinmetz','52331342091'),(4,'Wilson Steinmetz','52331342091'),(5,'Wilson Steinmetz','52331342091'),(6,'Wilson Steinmetz','52331342091'),(7,'Wilson Steinmetz','52331342091'),(8,'Wilson Steinmetz','52331342091'),(9,'Wilson Steinmetz','52331342091'),(10,'Wilson Steinmetz','52331342091'),(11,'Wilson Steinmetz','52331342091'),(12,'Wilson Steinmetz','52331342091'),(13,'Wilson Steinmetz','52331342091'),(14,'Wilson Steinmetz','52331342091'),(15,'Wilson Steinmetz','52331342091'),(16,'Wilson Steinmetz','52331342091'),(17,'Wilson Steinmetz','52331342091'),(18,'Wilson Steinmetz','52331342091'),(19,'Wilson Steinmetz','52331342091'),(20,'Wilson Steinmetz','52331342091'),(21,'Wilson Steinmetz','52331342091'),(22,'Wilson Steinmetz','52331342091'),(23,'Wilson Steinmetz','52331342091'),(24,'Wilson Steinmetz','52331342091'),(25,'Wilson Steinmetz','52331342091'),(26,'Wilson Steinmetz','52331342091'),(27,'Wilson Steinmetz','52331342091'),(28,'Wilson Steinmetz','52331342091'),(29,'Wilson Steinmetz','52331342091'),(30,'Wilson Steinmetz','52331342091'),(31,'Wilson Steinmetz','52331342091'),(32,'Wilson Steinmetz','52331342091'),(33,'Wilson Steinmetz','52331342091'),(34,'Wilson Steinmetz','52331342091'),(35,'Wilson Steinmetz','52331342091'),(36,'Wilson Steinmetz','52331342091'),(37,'Wilson Steinmetz','52331342091'),(38,'Wilson Steinmetz','52331342091'),(39,'Wilson Steinmetz','52331342091'),(40,'Wilson Steinmetz','52331342091'),(41,'Wilson Steinmetz','52331342091'),(42,'Wilson Steinmetz','52331342091'),(43,'Wilson Steinmetz','52331342091'),(44,'Wilson Steinmetz','52331342091'),(45,'Wilson Steinmetz','52331342091'),(46,'Wilson Steinmetz','52331342091'),(47,'Wilson Steinmetz','52331342091'),(48,'Wilson Steinmetz','52331342091'),(49,'Wilson Steinmetz','52331342091'),(50,'Wilson Steinmetz','52331342091'),(51,'Wilson Steinmetz','52331342091'),(52,'Wilson Steinmetz','52331342091'),(53,'Wilson Steinmetz','52331342091'),(54,'Wilson Steinmetz','52331342091'),(55,'Wilson Steinmetz','52331342091'),(56,'Wilson Steinmetz','52331342091'),(57,'Wilson Steinmetz','52331342091'),(58,'Wilson Steinmetz','52331342091'),(59,'Wilson Steinmetz','52331342091'),(60,'Wilson Steinmetz','52331342091'),(61,'Wilson Steinmetz','52331342091'),(62,'Wilson Steinmetz','52331342091'),(63,'Wilson Steinmetz','52331342091'),(64,'Wilson Steinmetz','52331342091'),(65,'Wilson Steinmetz','52331342091'),(66,'Wilson Steinmetz','52331342091'),(67,'Wilson Steinmetz','52331342091'),(68,'Wilson Steinmetz','52331342091'),(69,'Wilson Steinmetz','52331342091'),(70,'Wilson Steinmetz','52331342091');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-16 13:09:55
