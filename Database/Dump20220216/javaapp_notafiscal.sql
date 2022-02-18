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
-- Table structure for table `notafiscal`
--

DROP TABLE IF EXISTS `notafiscal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notafiscal` (
  `idNota` int unsigned NOT NULL AUTO_INCREMENT,
  `dataNota` date NOT NULL,
  `horaNota` varchar(255) NOT NULL,
  `pagForm` varchar(255) NOT NULL,
  `idPess` int unsigned NOT NULL,
  `idEst` int unsigned NOT NULL,
  PRIMARY KEY (`idNota`),
  KEY `idPess` (`idPess`),
  KEY `idEst` (`idEst`),
  CONSTRAINT `notafiscal_ibfk_1` FOREIGN KEY (`idPess`) REFERENCES `usuario` (`idPess`),
  CONSTRAINT `notafiscal_ibfk_2` FOREIGN KEY (`idEst`) REFERENCES `estabelecimento` (`idEst`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notafiscal`
--

LOCK TABLES `notafiscal` WRITE;
/*!40000 ALTER TABLE `notafiscal` DISABLE KEYS */;
INSERT INTO `notafiscal` VALUES (1,'2021-11-17','14:59:23','notCODED',1,1),(2,'2022-02-02','16:16:01','notCODED',2,2),(3,'2022-02-02','16:16:18','notCODED',2,2),(4,'2022-02-02','16:00:45','notCODED',2,4),(5,'2022-02-02','16:01:11','notCODED',2,5),(6,'2022-02-02','16:02:06','notCODED',2,6),(7,'2022-02-02','16:11:28','notCODED',2,7),(8,'2022-02-02','16:14:07','notCODED',2,8),(9,'2022-02-02','16:14:27','notCODED',2,9),(10,'2022-02-02','16:14:54','notCODED',2,10),(11,'2022-02-02','15:57:46','notCODED',2,11),(12,'2022-02-02','16:35:13','notCODED',2,12),(13,'2022-02-02','16:20:07','notCODED',2,13),(14,'2022-02-02','15:59:05','notCODED',2,2),(15,'2022-02-02','15:59:24','notCODED',2,15),(16,'2022-02-02','15:59:48','notCODED',2,16),(17,'2022-02-02','16:00:16','notCODED',2,17),(18,'2022-02-02','16:00:45','notCODED',2,4),(19,'2022-02-02','16:01:11','notCODED',2,5),(20,'2022-02-02','16:02:06','notCODED',2,6),(21,'2022-02-02','16:04:07','notCODED',2,2),(22,'2022-02-02','16:04:29','notCODED',2,2),(23,'2022-02-02','16:04:53','notCODED',2,2),(24,'2022-02-02','16:11:28','notCODED',2,7),(25,'2022-02-02','16:11:57','notCODED',2,17),(26,'2022-02-02','16:12:34','notCODED',2,26),(27,'2022-02-02','16:13:02','notCODED',2,8),(28,'2022-02-02','16:13:24','notCODED',2,2),(29,'2022-02-02','16:13:42','notCODED',2,8),(30,'2022-02-02','16:14:07','notCODED',2,8),(31,'2022-02-02','16:14:27','notCODED',2,9),(32,'2022-02-02','16:14:54','notCODED',2,10),(33,'2022-02-02','16:15:18','notCODED',2,2),(34,'2022-02-02','16:15:32','notCODED',2,2),(35,'2022-02-02','16:16:01','notCODED',2,2),(36,'2022-02-02','16:16:18','notCODED',2,2),(37,'2022-02-02','16:16:45','notCODED',2,16),(38,'2022-02-02','16:17:09','notCODED',2,2),(39,'2022-02-02','16:17:41','notCODED',2,39),(40,'2022-02-02','16:18:07','notCODED',2,40),(41,'2022-02-02','16:18:25','notCODED',2,2),(42,'2022-02-02','16:19:02','notCODED',2,42),(43,'2022-02-02','16:19:32','notCODED',2,2),(44,'2022-02-02','16:25:49','notCODED',2,12),(45,'2022-02-02','16:26:09','notCODED',2,2),(46,'2022-02-02','16:26:35','notCODED',2,46),(47,'2022-02-02','16:26:52','notCODED',2,10),(48,'2022-02-02','16:27:11','notCODED',2,48),(49,'2022-02-02','16:27:40','notCODED',2,17),(50,'2022-02-02','16:28:08','notCODED',2,9),(51,'2022-02-02','16:28:44','notCODED',2,51),(52,'2022-02-02','16:29:10','notCODED',2,52),(53,'2022-02-02','16:29:38','notCODED',2,6),(54,'2022-02-02','16:30:10','notCODED',2,39),(55,'2022-02-02','16:30:27','notCODED',2,10),(56,'2022-02-02','16:30:47','notCODED',2,10),(57,'2022-02-02','16:31:08','notCODED',2,7),(58,'2022-02-02','16:31:29','notCODED',2,9),(59,'2022-02-02','16:31:51','notCODED',2,17),(60,'2022-02-02','16:32:10','notCODED',2,52),(61,'2022-02-02','16:32:32','notCODED',2,61),(62,'2022-02-02','16:32:51','notCODED',2,61),(63,'2022-02-02','16:33:25','notCODED',2,52),(64,'2022-02-02','16:33:41','notCODED',2,64),(65,'2022-02-02','16:34:05','notCODED',2,12),(66,'2022-02-02','16:34:23','notCODED',2,12),(67,'2022-02-02','16:34:38','notCODED',2,67),(68,'2022-02-02','16:34:58','notCODED',2,7),(69,'2022-02-02','16:35:13','notCODED',2,12),(70,'2022-02-02','16:35:33','notCODED',2,70);
/*!40000 ALTER TABLE `notafiscal` ENABLE KEYS */;
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