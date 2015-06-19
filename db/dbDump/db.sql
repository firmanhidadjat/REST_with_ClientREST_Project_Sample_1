-- MySQL dump 10.13  Distrib 5.6.10, for Win64 (x86_64)
--
-- Host: localhost    Database: type3db
-- ------------------------------------------------------
-- Server version	5.6.10-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `kecamatan`
--

DROP TABLE IF EXISTS `kecamatan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kecamatan` (
  `idkecamatan` bigint(20) NOT NULL,
  `namaKecamatan` varchar(100) NOT NULL,
  `idkota` bigint(20) NOT NULL,
  PRIMARY KEY (`idkecamatan`),
  KEY `fkkotaid_idx` (`idkota`),
  CONSTRAINT `fkkotaid` FOREIGN KEY (`idkota`) REFERENCES `kota` (`idkota`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kecamatan`
--

LOCK TABLES `kecamatan` WRITE;
/*!40000 ALTER TABLE `kecamatan` DISABLE KEYS */;
INSERT INTO `kecamatan` VALUES (1,'Cimahi Utara',1),(2,'Cimahi Selatan',1),(3,'Pontianak Utara',2),(4,'Pontianak Selatan',2);
/*!40000 ALTER TABLE `kecamatan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kelurahan`
--

DROP TABLE IF EXISTS `kelurahan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kelurahan` (
  `idkelurahan` bigint(20) NOT NULL,
  `namaKelurahan` varchar(100) DEFAULT NULL,
  `idkecamatan` bigint(20) NOT NULL,
  PRIMARY KEY (`idkelurahan`),
  KEY `fkkecamatanid_idx` (`idkecamatan`),
  CONSTRAINT `fkkecamatanid` FOREIGN KEY (`idkecamatan`) REFERENCES `kecamatan` (`idkecamatan`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kelurahan`
--

LOCK TABLES `kelurahan` WRITE;
/*!40000 ALTER TABLE `kelurahan` DISABLE KEYS */;
INSERT INTO `kelurahan` VALUES (1,'Kel Cimahi utara 1',1),(2,'Kel Cimahi utara 2',1),(3,'Kel Cimahi Selatan 1',2),(4,'Kel Cimahi Selatan 2',2),(5,'Kel Pontianak ut 1',3),(6,'Kel Pontianak ut 2',3),(7,'Kel Pontianak sel 1',4),(8,'Kel Pontiank sel 2',4);
/*!40000 ALTER TABLE `kelurahan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kota`
--

DROP TABLE IF EXISTS `kota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kota` (
  `idkota` bigint(20) NOT NULL,
  `namaKota` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idkota`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kota`
--

LOCK TABLES `kota` WRITE;
/*!40000 ALTER TABLE `kota` DISABLE KEYS */;
INSERT INTO `kota` VALUES (1,'Cimahi'),(2,'Pontianak');
/*!40000 ALTER TABLE `kota` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-18 13:27:05
