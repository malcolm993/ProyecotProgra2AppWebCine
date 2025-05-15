-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: bbdd-proyectoprogra2
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
-- Table structure for table `funcion`
--
CREATE DATABASE bbdd-proyectoprogra2;
USE bbdd-proyectoprogra2;
DROP TABLE IF EXISTS `funcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcion` (
  `id_funcion` int NOT NULL AUTO_INCREMENT,
  `horario` varchar(20) NOT NULL,
  `fecha` varchar(20) NOT NULL,
  `id_pelicula` int NOT NULL,
  `id_sala` int NOT NULL,
  PRIMARY KEY (`id_funcion`),
  KEY `fk_FUNCION_PELICULA1_idx` (`id_pelicula`),
  KEY `fk_FUNCION_SALA1_idx` (`id_sala`),
  CONSTRAINT `fk_FUNCION_PELICULA1` FOREIGN KEY (`id_pelicula`) REFERENCES `pelicula` (`id_pelicula`),
  CONSTRAINT `fk_FUNCION_SALA1` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcion`
--

LOCK TABLES `funcion` WRITE;
/*!40000 ALTER TABLE `funcion` DISABLE KEYS */;
INSERT INTO `funcion` VALUES (6,'18:00','2025-05-12',1,3);
/*!40000 ALTER TABLE `funcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pelicula`
--

DROP TABLE IF EXISTS `pelicula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pelicula` (
  `id_pelicula` int NOT NULL AUTO_INCREMENT,
  `duracion_min` int NOT NULL,
  `nombre_pelicula` varchar(45) NOT NULL,
  `sinopsis` varchar(200) NOT NULL,
  `Apto_publico` varchar(45) NOT NULL,
  `fecha_estreno` varchar(20) NOT NULL,
  `director` varchar(45) NOT NULL,
  `imagen` varchar(20) NOT NULL,
  `is_cartelera` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_pelicula`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pelicula`
--

LOCK TABLES `pelicula` WRITE;
/*!40000 ALTER TABLE `pelicula` DISABLE KEYS */;
INSERT INTO `pelicula` VALUES (1,160,'Terminator 2','maquina de matar del futuro','+18','1995-02-05','James Cameron','placeholder.jpg',1),(54,400,'testPrueba2','casdcvasv','+16','1998-01-01','Fulano','placeholder.jpg',1);
/*!40000 ALTER TABLE `pelicula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva_entrada`
--

DROP TABLE IF EXISTS `reserva_entrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva_entrada` (
  `id_reserva_entrada` int NOT NULL AUTO_INCREMENT,
  `id_funcion` int NOT NULL,
  `costo_reserva` int NOT NULL,
  `fecha_reserva` varchar(20) NOT NULL,
  `horario_reserva` varchar(40) NOT NULL,
  `id_usuario` int NOT NULL,
  `cantidad_entradas` int NOT NULL,
  `is_cancelable` tinyint(1) NOT NULL DEFAULT '1',
  `id_pelicula` int NOT NULL,
  PRIMARY KEY (`id_reserva_entrada`),
  KEY `fk_RESERVA_ENTRADA_FUNCION1_idx` (`id_funcion`),
  KEY `fk_RESERVA_ENTRADA_USUARIO1_idx` (`id_usuario`),
  KEY `fk_RESERVA_ENTRADA_PELICULA` (`id_pelicula`),
  CONSTRAINT `fk_RESERVA_CAPACIDAD_FUNCION1` FOREIGN KEY (`id_funcion`) REFERENCES `funcion` (`id_funcion`),
  CONSTRAINT `fk_RESERVA_ENTRADA_PELICULA` FOREIGN KEY (`id_pelicula`) REFERENCES `pelicula` (`id_pelicula`),
  CONSTRAINT `fk_RESERVA_ENTRADA_USUARIO1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva_entrada`
--

LOCK TABLES `reserva_entrada` WRITE;
/*!40000 ALTER TABLE `reserva_entrada` DISABLE KEYS */;
INSERT INTO `reserva_entrada` VALUES (1,6,1000,'2025-05-10','20:19:10.465671700',2,1,0,1);
/*!40000 ALTER TABLE `reserva_entrada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sala` (
  `id_sala` int NOT NULL AUTO_INCREMENT,
  `cantidad_butacas` int NOT NULL,
  `tipo_sala` enum('_2D','_3D','D_BOX') NOT NULL,
  PRIMARY KEY (`id_sala`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
INSERT INTO `sala` VALUES (3,97,'_2D'),(4,100,'_3D'),(5,50,'D_BOX');
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `contrasenia` varchar(45) NOT NULL,
  `credito` int DEFAULT NULL,
  `rol_usuario` enum('admin','cliente') NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'user1','apellido1','user@gmail.com','1234',10000,'cliente'),(2,'admin1','apellido2','admin@gmail.com','1234',10000,'admin');
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

-- Dump completed on 2025-05-13 20:56:13
