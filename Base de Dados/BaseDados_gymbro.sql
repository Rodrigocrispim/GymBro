-- MySQL dump 10.13  Distrib 8.0.43, for macos15 (arm64)
--
-- Host: localhost    Database: database_gymbro
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `candidatura`
--

DROP TABLE IF EXISTS `candidatura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidatura` (
  `candidatura_id` int NOT NULL AUTO_INCREMENT,
  `status` enum('Pendente','Aceite','Rejeitada') NOT NULL,
  `comentario` mediumtext,
  `data_envio` datetime DEFAULT NULL,
  `oferta_oferta_id` int NOT NULL,
  `candidato_id` int NOT NULL,
  PRIMARY KEY (`candidatura_id`),
  KEY `fk_candidatura_utilizador1_idx` (`candidato_id`),
  KEY `fk_candidatura_oferta1_idx` (`oferta_oferta_id`),
  CONSTRAINT `fk_candidatura_oferta1` FOREIGN KEY (`oferta_oferta_id`) REFERENCES `oferta` (`oferta_id`),
  CONSTRAINT `fk_candidatura_utilizador1` FOREIGN KEY (`candidato_id`) REFERENCES `utilizador` (`utilizador_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidatura`
--

LOCK TABLES `candidatura` WRITE;
/*!40000 ALTER TABLE `candidatura` DISABLE KEYS */;
/*!40000 ALTER TABLE `candidatura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ChatMensagem`
--

DROP TABLE IF EXISTS `ChatMensagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ChatMensagem` (
  `mensagem_id` int NOT NULL AUTO_INCREMENT,
  `texto_mensagem` mediumtext NOT NULL,
  `data_envio` datetime NOT NULL,
  `candidatura_candidatura_id` int NOT NULL,
  `remetente_id` int NOT NULL,
  PRIMARY KEY (`mensagem_id`),
  KEY `fk_ChatMensagem_candidatura1_idx` (`candidatura_candidatura_id`),
  KEY `fk_ChatMensagem_utilizador1_idx` (`remetente_id`),
  CONSTRAINT `fk_ChatMensagem_candidatura1` FOREIGN KEY (`candidatura_candidatura_id`) REFERENCES `candidatura` (`candidatura_id`),
  CONSTRAINT `fk_ChatMensagem_utilizador1` FOREIGN KEY (`remetente_id`) REFERENCES `utilizador` (`utilizador_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ChatMensagem`
--

LOCK TABLES `ChatMensagem` WRITE;
/*!40000 ALTER TABLE `ChatMensagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `ChatMensagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `desporto`
--

DROP TABLE IF EXISTS `desporto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `desporto` (
  `desporto_id` int NOT NULL AUTO_INCREMENT,
  `desporto_nome` varchar(45) NOT NULL,
  PRIMARY KEY (`desporto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `desporto`
--

LOCK TABLES `desporto` WRITE;
/*!40000 ALTER TABLE `desporto` DISABLE KEYS */;
INSERT INTO `desporto` VALUES (1,'Musculação'),(2,'Crossfit'),(3,'Pilates'),(4,'Cycling'),(5,'BodyCombat'),(6,'HIIT'),(7,'Boxe'),(8,'KickBoxing'),(9,'BodyPump'),(10,'Alongamentos');
/*!40000 ALTER TABLE `desporto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localização`
--

DROP TABLE IF EXISTS `localização`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `localização` (
  `localizacao_id` int NOT NULL AUTO_INCREMENT,
  `distrito` varchar(45) NOT NULL,
  `concelho` varchar(45) NOT NULL,
  PRIMARY KEY (`localizacao_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localização`
--

LOCK TABLES `localização` WRITE;
/*!40000 ALTER TABLE `localização` DISABLE KEYS */;
INSERT INTO `localização` VALUES (1,'Lisboa','Lisboa'),(2,'Lisboa','Sintra'),(3,'Lisboa','Cascais'),(4,'Lisboa','Odivelas'),(5,'Porto','Porto'),(6,'Porto','Vila Nova de Gaia'),(7,'Porto','Matosinhos'),(8,'Setúbal','Setúbal'),(9,'Setúbal','Almada');
/*!40000 ALTER TABLE `localização` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NivelTreino`
--

DROP TABLE IF EXISTS `NivelTreino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `NivelTreino` (
  `nivel_id` int NOT NULL AUTO_INCREMENT,
  `nivel_nome` varchar(45) NOT NULL,
  PRIMARY KEY (`nivel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NivelTreino`
--

LOCK TABLES `NivelTreino` WRITE;
/*!40000 ALTER TABLE `NivelTreino` DISABLE KEYS */;
INSERT INTO `NivelTreino` VALUES (1,'Iniciante'),(2,'Intermédio'),(3,'Avançado');
/*!40000 ALTER TABLE `NivelTreino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ObjetivoFitness`
--

DROP TABLE IF EXISTS `ObjetivoFitness`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ObjetivoFitness` (
  `objetivo_id` int NOT NULL AUTO_INCREMENT,
  `objetivo_nome` varchar(45) NOT NULL,
  PRIMARY KEY (`objetivo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ObjetivoFitness`
--

LOCK TABLES `ObjetivoFitness` WRITE;
/*!40000 ALTER TABLE `ObjetivoFitness` DISABLE KEYS */;
INSERT INTO `ObjetivoFitness` VALUES (1,'Perda de Peso'),(2,'Ganho de Massa Muscular'),(3,'Manutenção'),(4,'Competição'),(5,'Social / Divertimento');
/*!40000 ALTER TABLE `ObjetivoFitness` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oferta`
--

DROP TABLE IF EXISTS `oferta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oferta` (
  `oferta_id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `descricao` mediumtext,
  `status_oferta` enum('Aberta','Fechada') NOT NULL,
  `data_criacao` datetime DEFAULT NULL,
  `criador_id` int NOT NULL,
  `localização_localizacao_id` int NOT NULL,
  `desporto_desporto_id` int NOT NULL,
  `NivelTreino_nivel_id` int NOT NULL,
  PRIMARY KEY (`oferta_id`),
  KEY `fk_oferta_utilizador1_idx` (`criador_id`),
  KEY `fk_oferta_localização1_idx` (`localização_localizacao_id`),
  KEY `fk_oferta_desporto1_idx` (`desporto_desporto_id`),
  KEY `fk_oferta_NivelTreino1_idx` (`NivelTreino_nivel_id`),
  CONSTRAINT `fk_oferta_desporto1` FOREIGN KEY (`desporto_desporto_id`) REFERENCES `desporto` (`desporto_id`),
  CONSTRAINT `fk_oferta_localização1` FOREIGN KEY (`localização_localizacao_id`) REFERENCES `localização` (`localizacao_id`),
  CONSTRAINT `fk_oferta_NivelTreino1` FOREIGN KEY (`NivelTreino_nivel_id`) REFERENCES `NivelTreino` (`nivel_id`),
  CONSTRAINT `fk_oferta_utilizador1` FOREIGN KEY (`criador_id`) REFERENCES `utilizador` (`utilizador_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oferta`
--

LOCK TABLES `oferta` WRITE;
/*!40000 ALTER TABLE `oferta` DISABLE KEYS */;
/*!40000 ALTER TABLE `oferta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil` (
  `perfil_id` int NOT NULL AUTO_INCREMENT,
  `nome_perfil` varchar(45) DEFAULT NULL,
  `url_foto_perfil` varchar(512) DEFAULT NULL,
  `biografia` mediumtext,
  `utilizador_utilizador_id` int NOT NULL,
  `localização_localizacao_id` int NOT NULL,
  `desporto_desporto_id` int NOT NULL,
  `NivelTreino_nivel_id` int NOT NULL,
  `ObjetivoFitness_objetivo_id` int NOT NULL,
  PRIMARY KEY (`perfil_id`),
  KEY `fk_perfil_utilizador_idx` (`utilizador_utilizador_id`),
  KEY `fk_perfil_localização1_idx` (`localização_localizacao_id`),
  KEY `fk_perfil_desporto1_idx` (`desporto_desporto_id`),
  KEY `fk_perfil_NivelTreino1_idx` (`NivelTreino_nivel_id`),
  KEY `fk_perfil_ObjetivoFitness1_idx` (`ObjetivoFitness_objetivo_id`),
  CONSTRAINT `fk_perfil_desporto1` FOREIGN KEY (`desporto_desporto_id`) REFERENCES `desporto` (`desporto_id`),
  CONSTRAINT `fk_perfil_localização1` FOREIGN KEY (`localização_localizacao_id`) REFERENCES `localização` (`localizacao_id`),
  CONSTRAINT `fk_perfil_NivelTreino1` FOREIGN KEY (`NivelTreino_nivel_id`) REFERENCES `NivelTreino` (`nivel_id`),
  CONSTRAINT `fk_perfil_ObjetivoFitness1` FOREIGN KEY (`ObjetivoFitness_objetivo_id`) REFERENCES `ObjetivoFitness` (`objetivo_id`),
  CONSTRAINT `fk_perfil_utilizador` FOREIGN KEY (`utilizador_utilizador_id`) REFERENCES `utilizador` (`utilizador_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilizador`
--

DROP TABLE IF EXISTS `utilizador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilizador` (
  `utilizador_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  PRIMARY KEY (`utilizador_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilizador`
--

LOCK TABLES `utilizador` WRITE;
/*!40000 ALTER TABLE `utilizador` DISABLE KEYS */;
/*!40000 ALTER TABLE `utilizador` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-21  0:23:01
