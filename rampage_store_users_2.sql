CREATE DATABASE  IF NOT EXISTS `rampage_store` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `rampage_store`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: rampage_store
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `grupo` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (4,'456.995.918-00','Lucas_Almeida.97@hotmail.com','Administrador','Lucas','$2a$10$AyVaSNkZcxG7psQRkpvUMe1gGjCpldNpWVz7T5Bxn3AjXaCIneJpu',_binary ''),(5,'456.995.918-00','caio_almeida.97@hotmail.com','Administrador','caio','$2a$10$v8Bmz.2ZtWK0ylo5A34zW.upBnt0o23g98rQPxnKC.M8R06ZwNxB6',_binary ''),(6,'456.995.918-00','tadeu_Almeida.97@hotmail.com','Estoquista','tadeu','$2a$10$XkQpKoySQrOCQppmGGCSje76HKbxyKMLlQpC2BgGpNc5C6w8vTFry',_binary ''),(7,'456.995.918-00','jose_algusto.97@hotmail.com','Administrador','joao','$2a$10$Wg0GJpICtB3CZUMiWIyyCOvMiNwz2rNAb3VYk51osuQiwe3/gC5gO',_binary ''),(8,'456.995.918-00','carlo_Almeida.97@hotmail.com','Estoquista','carlos','$2a$10$gUI4AqYcMYRCihRGA8it3uKc4.2ghLdob/S2nJIcOftSurL1ADgSq',_binary ''),(9,'456.995.918-00','jose_almeida.97@hotmail.com','Administrador','jose','$2a$10$qb5CH6x4jywQog3nrtNEZu4WV7HmQlnw0AYPVMsenydFOJPsYpFrG',_binary ''),(10,'456.995.918-00','carlos_anderson.55@hotmail.com','Administrador','Carlos','$2a$10$C61wBb4S4dW5PP1Lkn8RQ.AIRJs4z0VV0JK/cFRYPQtIr4C/QbuCm',_binary ''),(11,'456.995.918-00','caio_algusto@gmail.com','Estoquista','Kaio','$2a$10$MU6ZS2DINL21abBH6Y9khuyEYX9WKTQlN5gl0UvWx/3lp.lp8dIdC',_binary '\0'),(12,'456.995.918-00','aroudo_almeida.97@hotmail.com','Administrador','aroudo','$2a$10$bTgPzp2RsU86l5uHDErm8eOjnsbhx3O3mjPdOavjJJPQ6i9wN65wq',_binary ''),(13,'456.995.918-00','teste_Adm@outlook.com','Administrador','Teste Administrador','$2a$10$.UaYSxgSiXQX8.ML4RHV6uhPldtrhEfX8Fhn/JlkwtXUJ0s/LnBre',_binary ''),(14,'456.995.918-00','teste_Estoque@outlook.com','Estoquista','Teste Estoquista','$2a$10$KXlWGGJX5hQSE99BQoMmU.dK5LnimUqFJddUzeVpvHDEjVSSv.fLy',_binary ''),(15,'456.995.918-00','teste_Cliente@outlook.com','Cliente','Teste Cliente','$2a$10$ioeOuG7EUCVFOL8iuSltxe/PwMzTax60uWz./r88L5ddJktV9A1.6',_binary ''),(16,'456.995.918-00','teste_desativado@outlook.com','Estoquista','Teste Desativado','$2a$10$Wf5l2e6b10u756RVCjJOEeP2gbiyHRFB20gDfkyj2KZWk7uYjtg0C',_binary '\0');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-05 14:54:40
