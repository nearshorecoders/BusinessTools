-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: pvpizza
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `caja`
--

DROP TABLE IF EXISTS `caja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `caja` (
  `idCaja` int(11) NOT NULL AUTO_INCREMENT,
  `inicioDelDia` double DEFAULT '0',
  `finalDelDia` double DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sucursal_idsucursal` int(11) NOT NULL,
  PRIMARY KEY (`idCaja`),
  KEY `fk_caja_sucursal1_idx` (`sucursal_idsucursal`),
  CONSTRAINT `fk_caja_sucursal1` FOREIGN KEY (`sucursal_idsucursal`) REFERENCES `sucursal` (`idsucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caja`
--

LOCK TABLES `caja` WRITE;
/*!40000 ALTER TABLE `caja` DISABLE KEYS */;
INSERT INTO `caja` VALUES (1,500,NULL,'2016-03-09 20:50:11',0),(2,0,NULL,'2016-03-10 18:19:24',0),(3,0,NULL,'2016-03-12 20:31:08',0),(4,0,NULL,'2016-03-15 13:43:34',0),(5,0,NULL,'2016-03-18 14:55:38',0),(6,0,NULL,'2016-03-25 17:35:59',0),(7,0,NULL,'2016-03-28 20:35:16',0),(8,0,NULL,'2016-03-30 02:31:16',0),(9,0,NULL,'2016-04-02 21:38:38',0),(10,0,NULL,'2016-04-05 02:58:37',0),(11,0,NULL,'2016-04-06 18:18:18',0),(12,0,NULL,'2016-04-11 18:19:15',0),(13,0,NULL,'2016-04-14 16:35:41',0),(14,0,NULL,'2016-04-25 23:57:06',0),(15,0,NULL,'2016-04-26 17:32:58',0),(16,0,NULL,'2016-04-27 19:46:43',0),(17,0,0,'2016-05-10 20:47:55',0),(18,0,NULL,'2016-05-11 22:27:20',0),(19,0,NULL,'2016-05-12 20:25:03',0),(20,0,NULL,'2018-03-03 19:38:21',0),(21,0,NULL,'2018-08-11 21:12:14',0),(22,0,NULL,'2018-08-13 15:44:16',0),(23,0,NULL,'2018-08-14 14:27:08',0),(24,0,NULL,'2018-08-15 20:16:42',0),(25,0,NULL,'2018-08-17 14:27:51',0),(26,0,NULL,'2018-08-18 15:53:22',0),(27,0,NULL,'2018-08-20 16:26:13',0),(28,0,NULL,'2018-08-21 18:40:22',0),(29,0,NULL,'2018-08-22 14:27:07',0),(30,0,NULL,'2018-08-23 16:38:34',0),(31,0,NULL,'2018-08-24 15:39:39',0),(32,0,NULL,'2018-08-27 14:39:28',0),(33,0,NULL,'2018-08-28 14:22:09',0),(34,0,NULL,'2018-08-29 14:59:06',0),(35,0,NULL,'2018-08-31 18:02:58',0),(36,0,NULL,'2018-09-01 16:24:44',0);
/*!40000 ALTER TABLE `caja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cliente` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellidop` varchar(45) DEFAULT NULL,
  `apellidom` varchar(45) DEFAULT NULL,
  `dirección` varchar(250) DEFAULT NULL,
  `telefono` varchar(24) DEFAULT NULL,
  `fechaDeRegistro` date DEFAULT NULL,
  `activo` int(11) DEFAULT '1',
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Austin','Ward','Duran','Ap #808-2850 Luctus Av.','1-572-306-3690','0000-00-00',1),(2,'Ciara','Stephenson','Ellison','Ap #661-836 Venenatis St.','1-400-675-2858','0000-00-00',1),(3,'Hyatt','Jenkins','Bird','P.O. Box 435, 2143 Arcu. Road','1-124-112-8736','0000-00-00',1),(4,'Merritt','Waller','Mays','778-4198 A St.','1-983-377-6353','0000-00-00',1),(5,'Xavier','Barnes','Morales','P.O. Box 673, 6132 Erat. Rd.','1-421-247-9025','0000-00-00',1),(6,'Rachel','Wilkins','Gardner','Ap #854-4694 Et Road','1-159-164-4773','0000-00-00',1),(7,'Chandler','Randall','Finley','Ap #105-552 Nec, St.','1-387-784-1209','0000-00-00',1),(8,'Mariko','Duffy','Hogan','9301 In Rd.','1-849-918-7412','0000-00-00',1),(9,'Morgan','Mcguire','Sherman','856-5628 Sed, Av.','1-587-894-6077','0000-00-00',1),(10,'Iona','Cote','Villarreal','Ap #729-8307 Amet Rd.','1-842-376-1331','0000-00-00',1),(11,'Jesse','Cooke','Malone','8143 Posuere Road','1-653-573-3589','0000-00-00',1),(12,'Nell','Drake','Figueroa','343-2338 Ut, Av.','1-839-712-6886','0000-00-00',1),(13,'Thane','Booth','Mullen','919 Blandit St.','1-748-885-5897','0000-00-00',1),(14,'Nora','Wilcox','Myers','P.O. Box 756, 5748 Condimentum. Av.','1-419-962-1587','0000-00-00',1),(15,'Piper','Mcmillan','Mcfarland','725-2455 Donec Ave','1-231-669-5056','0000-00-00',1),(16,'Hedley','Gallagher','Browning','414-7754 Enim St.','1-512-140-5089','0000-00-00',1),(17,'Vaughan','Martinez','Stokes','343-4504 At, Road','1-507-495-6596','0000-00-00',1),(18,'Giacomo','Acosta','Kinney','Ap #292-7925 Neque. St.','1-863-947-6587','0000-00-00',1),(19,'Lunea','Terrell','Ayala','1909 Ipsum St.','1-562-723-8216','0000-00-00',1),(20,'Declan','Sharp','Berg','Ap #348-5787 Lacus Av.','1-365-937-6521','0000-00-00',1),(21,'Barry','Mckenzie','Macias','3307 Tincidunt Street','1-562-674-9889','0000-00-00',1),(22,'Karleigh','Avery','Juarez','P.O. Box 263, 5815 A, Avenue','1-361-864-6264','0000-00-00',1),(23,'Quintessa','Gibson','Bartlett','9608 Rutrum Street','1-707-234-2976','0000-00-00',1),(24,'Melodie','Weiss','Soto','889-1010 Tincidunt, Av.','1-176-718-8032','0000-00-00',1),(25,'Emmanuel','Levy','Washington','P.O. Box 414, 9781 Nibh. Avenue','1-631-284-2421','0000-00-00',1),(26,'Brent','Mooney','Puckett','765-5970 Nulla Avenue','1-531-189-1374','0000-00-00',1),(27,'Angelica','Weaver','Hart','Ap #646-5494 Ipsum. St.','1-373-335-1303','0000-00-00',1),(28,'Herrod','French','Donaldson','691-905 Odio Ave','1-485-666-4961','0000-00-00',1),(29,'Otto','Giles','Nixon','7852 Nec Rd.','1-907-150-4780','0000-00-00',1),(30,'Madeline','Avery','Lindsay','P.O. Box 659, 7935 A, St.','1-550-441-7007','0000-00-00',1),(31,'Sigourney','Guzman','Gates','996-5942 Bibendum Ave','1-272-350-7599','0000-00-00',1),(32,'Jonas','Roth','Cummings','331-8619 Elit, Av.','1-284-670-8367','0000-00-00',1),(33,'Freya','Hartman','Pittman','Ap #215-1778 At St.','1-575-947-4566','0000-00-00',1),(34,'Freya','Cantu','Lara','9647 Fermentum Avenue','1-181-835-1021','0000-00-00',1),(35,'Brooke','Ferrell','Dudley','502-5232 Accumsan St.','1-185-933-2739','0000-00-00',1),(36,'Louis','Cameron','Franks','P.O. Box 979, 7374 Aliquam Ave','1-340-705-2335','0000-00-00',1),(37,'Judah','Stout','Atkins','133-7707 Nec, St.','1-637-331-3632','0000-00-00',1),(38,'Ivy','Potts','Frank','P.O. Box 175, 6177 Cursus St.','1-599-422-7189','0000-00-00',1),(39,'Kieran','Pace','Frost','P.O. Box 522, 6579 In Street','1-703-991-6804','0000-00-00',1),(40,'Rhona','Bowers','Burt','Ap #379-2274 Dictum Rd.','1-235-655-0897','0000-00-00',1),(41,'Jesse','Livingston','Rosario','416-387 Dis Rd.','1-662-862-5639','0000-00-00',1),(42,'Zelenia','Stein','Key','Ap #765-2825 Blandit. Rd.','1-952-393-0913','0000-00-00',1),(43,'Hayley','Price','Patterson','P.O. Box 317, 9570 Donec Street','1-772-211-9879','0000-00-00',1),(44,'Chaney','Parrish','House','5661 At, Street','1-625-820-5470','0000-00-00',1),(45,'Lydia','Dorsey','Donovan','655-8866 Pede. Avenue','1-554-742-9595','0000-00-00',1),(46,'Shana','Hodges','Pickett','9066 Elementum, Road','1-301-612-5842','0000-00-00',1),(47,'Jaquelyn','Greene','Farley','483-6941 Libero Road','1-870-203-6442','0000-00-00',1),(48,'Vance','Long','Ellison','Ap #308-657 In St.','1-360-917-2465','0000-00-00',1),(49,'Carolyn','Mendoza','Buckner','Ap #542-8157 Neque St.','1-364-119-0044','0000-00-00',1),(50,'Jacob','Sharpe','Osborn','P.O. Box 958, 8314 Volutpat Av.','1-835-410-4867','0000-00-00',1),(51,'Aline','Lara','Paul','P.O. Box 617, 9127 Nunc St.','1-709-766-5709','0000-00-00',1),(52,'Rafael','Maldonado','Boone','3424 Dignissim Ave','1-319-972-9526','0000-00-00',1),(53,'Ignatius','Floyd','Crawford','899-3824 Est, Rd.','1-838-579-5878','0000-00-00',1),(54,'Avram','Larsen','Winters','Ap #687-8370 Velit. Avenue','1-343-874-2912','0000-00-00',1),(55,'Dawn','Trujillo','Guy','P.O. Box 419, 4785 Quisque Rd.','1-529-298-7092','0000-00-00',1),(56,'Alexander','Ortiz','Delaney','P.O. Box 778, 3785 A St.','1-339-546-3195','0000-00-00',1),(57,'Mark','Bass','Armstrong','371-176 Penatibus Av.','1-290-429-6648','0000-00-00',1),(58,'Kevin','Wiley','Waters','P.O. Box 709, 5137 Sapien, Ave','1-676-517-3816','0000-00-00',1),(59,'Fulton','Potts','Curry','Ap #453-5350 Nisi. Avenue','1-706-351-7808','0000-00-00',1),(60,'Nehru','Luna','Butler','P.O. Box 803, 8373 Congue, Road','1-611-359-5641','0000-00-00',1),(61,'Colin','Fisher','Chambers','539-5785 Aliquam Av.','1-209-762-8911','0000-00-00',1),(62,'Reese','Gonzales','Velasquez','P.O. Box 303, 7135 Neque Rd.','1-166-421-3113','0000-00-00',1),(63,'Kirby','Riddle','Shields','2268 Nulla Ave','1-589-907-5861','0000-00-00',1),(64,'Hilda','Velez','Page','7869 Sed Road','1-543-452-1745','0000-00-00',1),(65,'Chantale','Moon','Mitchell','512-7105 Sit Road','1-868-996-6476','0000-00-00',1),(66,'Simone','Haley','Perkins','Ap #327-1583 Vulputate, Rd.','1-140-737-3231','0000-00-00',1),(67,'Dane','Hobbs','Day','578-2028 Ac Rd.','1-839-420-0482','0000-00-00',1),(68,'Ima','Bradshaw','Skinner','8488 Sapien Avenue','1-485-217-0158','0000-00-00',1),(69,'Baker','Richmond','Clayton','P.O. Box 662, 448 Nunc St.','1-399-639-1483','0000-00-00',1),(70,'Kenyon','Rosario','French','9202 Dapibus Street','1-773-328-7806','0000-00-00',1),(71,'Hanna','Morrison','Robinson','P.O. Box 323, 3639 Scelerisque Ave','1-745-971-9285','0000-00-00',1),(72,'Lacy','Gill','Hebert','Ap #383-8247 Orci. St.','1-365-790-4575','0000-00-00',1),(73,'Constance','York','Walsh','Ap #713-4200 In Rd.','1-672-607-3842','0000-00-00',1),(74,'Sandra','Hawkins','Cote','7445 Tellus, Rd.','1-610-356-5185','0000-00-00',1),(75,'Camilla','Guerrero','Compton','P.O. Box 803, 4212 Sit Rd.','1-786-841-4084','0000-00-00',1),(76,'Porter','Munoz','Valenzuela','P.O. Box 835, 2507 Enim. St.','1-269-444-5785','0000-00-00',1),(77,'Jolie','Case','Barlow','876-224 Aliquet Ave','1-397-397-9785','0000-00-00',1),(78,'Hammett','Lowery','Joyce','397-2567 Cras St.','1-831-814-4377','0000-00-00',1),(79,'Katell','Torres','Hurst','7592 A Ave','1-375-706-8742','0000-00-00',1),(80,'Vielka','Schwartz','Macias','991-3469 Pede Av.','1-429-616-4406','0000-00-00',1),(81,'Alana','Carver','Vasquez','5769 Iaculis St.','1-612-227-4502','0000-00-00',1),(82,'Benjamin','Bartlett','Wilder','439-7043 Et Rd.','1-818-635-2253','0000-00-00',1),(83,'Logan','Reid','Nicholson','Ap #356-6118 Nulla. Rd.','1-529-429-5327','0000-00-00',1),(84,'Whitney','Buckley','Shaffer','651-412 Nulla Ave','1-842-894-3051','0000-00-00',1),(85,'Ursula','Fisher','House','P.O. Box 475, 5393 Nibh Road','1-762-162-6225','0000-00-00',1),(86,'Adrian','Lewis','Mcbride','821-8856 Magna Av.','1-560-127-6381','0000-00-00',1),(87,'Maia','Gould','Marquez','Ap #534-2704 Ac Ave','1-376-271-4155','0000-00-00',1),(88,'Samson','Montgomery','Wilcox','P.O. Box 621, 7707 Malesuada. Road','1-658-916-0274','0000-00-00',1),(89,'Belle','Rhodes','Whitley','P.O. Box 579, 2761 Est, Avenue','1-436-192-7236','0000-00-00',1),(90,'Nyssa','Leon','Moss','Ap #466-2817 Libero. Road','1-652-437-1940','0000-00-00',1),(91,'Jarrod','Nicholson','Sims','Ap #277-8691 Iaculis Road','1-514-610-2212','0000-00-00',1),(92,'Madeson','Mcintosh','Hill','P.O. Box 916, 5898 Commodo Road','1-578-437-6170','0000-00-00',1),(93,'Madonna','Rutledge','Kaufman','2523 Massa Av.','1-897-859-4060','0000-00-00',1),(94,'Warren','Woodard','Clemons','3963 Ipsum Ave','1-935-473-0647','0000-00-00',1),(95,'Nomlanga','Kelley','Mullen','P.O. Box 210, 1573 Eget Av.','1-391-578-1309','0000-00-00',1),(96,'Xandra','Alvarado','Goff','218-3953 Eu, St.','1-753-238-4340','0000-00-00',1),(97,'Alec','Turner','Mccall','P.O. Box 298, 9398 Felis Street','1-879-827-3573','0000-00-00',1),(98,'Athena','Davenport','Scott','Ap #783-3164 Ornare, Rd.','1-238-364-3555','0000-00-00',1),(99,'Connor','Wade','Lane','Ap #205-2857 Magna. Road','1-447-760-6350','0000-00-00',1),(100,'Nathan','Hale','Chase','P.O. Box 922, 2723 Non Rd.','1-622-417-1477','0000-00-00',1),(101,'Alfredo','Suarez','Romero','calle rio lerma sur #3','45589','0000-00-00',0),(102,'Jose','Perez','Guerrero','avenida rio acotzala #2','14154','0000-00-00',1),(103,'Jose juan','perez','ayala','privada rio misisipi #3','45556','0000-00-00',1),(104,'Gerardo','Romero','Suarez','Reforma # 45','8893','0000-00-00',1),(105,'Nadia','Guerrero','Perez','calle rio acotzala #26','44582','0000-00-00',1),(106,'Valentin','ayala','perez','rio misisipi #39','11245','0000-00-00',1),(107,'Valente','Nogal','Manzano','Florencio espinoza #4','3394','0000-00-00',1),(108,'Pedro','Paramo','Mcqueen','rio acotzala #244','7895','0000-00-00',1),(109,'Juan','palafox','mendoza','rio misisipi SUR #3','2321','0000-00-00',1),(110,'Juan gabriel','Manzano','Nogal','avenida rio lerma NORTE #2','123243','0000-00-00',1),(111,'Gabriel','Mcqueen','Paramo','rio acotzala #21222','11147','0000-00-00',1),(112,'Jose jose','mendoza','palafox','avenida rio misisipi #333','11554','0000-00-00',1),(113,'Jack','daniels','clasic','Prolongacion larguisisisisisima','112','2015-11-26',0),(114,'Valentin','Rodriguez','Medina','Reforma 108 San baltazar entre calle puebla y calle morelos',NULL,'2016-03-02',1),(115,'Alfredo','perea','perea','privada Rio lerma sur#2','4478978','2016-03-10',1),(116,'Alfredo','peña','nieto','cas blanca qasklskslslsss','666','2016-03-10',1),(117,'Enrique','peña','nieto','cas blanca','21213','2016-03-10',1),(118,'test','client','','sssssssssssssssssssssssssss','','2016-03-10',1),(119,'enrique','kjuan','','calle rio lerma','','2016-03-10',1),(120,'test','1','2','sibok666','3','2016-03-12',1),(121,'test1','2','2','direccion  de prueba','3','2016-03-12',1),(122,'1','2','3','1','4','2016-03-12',1),(123,'1','2','3','test','123','2016-03-12',1),(124,'jose','perez','prado','test de direccion de envio','44885','2016-03-15',1),(125,'juan','perez','prado','test de envio','45454','2016-03-15',1),(126,'pedro','perez ','prado','rio juchinaito entre calle progreso y juachinchingadigo','12323','2016-03-15',1),(127,'pedro','pedrin','pedron','Sibok666e','12333','2016-03-15',1),(128,'admin','admin','admin','test','123123','2016-03-15',1),(129,'Juan','Jose','','direccion falsa','','2016-03-15',1),(130,'test','test','werwe','test','123','2016-03-15',1),(131,'1','2','3','5','4','2016-03-15',1),(132,'1','2','','1233344444','','2016-03-15',1),(133,'3','4','5','6','','2016-03-15',1),(134,'1','2','3','5','4','2016-03-15',1),(135,'1','2','3','2222222222222222222222222222','','2016-03-15',1),(136,'1','2','3','33333333333333333','4','2016-03-15',1),(137,'1','2','3','4444444444444444444444444444444444444','','2016-03-15',1),(138,'11','22','33','11111111111111111111','44','2016-03-15',1),(139,'11','22','33','test','44','2016-03-15',1),(140,'11','22','33','55555555','44','2016-03-15',1),(141,'333','444','555','sdsfsdfsdfsdfsdfs','123','2016-03-15',1),(142,'1','2','3','4444444','4','2016-03-15',1),(143,'222','333','444','6666666666666','555','2016-03-15',1),(144,'Juanito','Lopez','','Rio lerma sur # 2','245544','2016-03-18',1),(145,'Juan','Camaney','Perez','Rio lerma sur #2','45555','2016-03-18',1),(146,'Cliente','test','','rio lerma test','78895','2016-03-18',1),(147,'cliente','nuevo','','Direccion de prueba','','2016-03-18',1),(148,'user','test','','123344555','','2016-03-18',1),(149,'test','1','','33333333','','2016-03-18',1),(150,'alfredo','test','juanito','rio lerma sur numero 2','1233445','2016-04-14',1),(151,'juan','lopez','','rio lerma sur #3 ','788787','2016-04-14',1),(152,'juan','juan','juan','rio lerma -------------------------------','12233','2016-04-27',1),(153,'-----','------------','-----------','-------------------------------','121212','2016-04-27',1),(154,'----','--------','-----','------------','-------','2016-04-27',1),(155,'1','1','1','3','1','2016-04-27',1),(156,'111','111111111','1111111','11111111111111','111111111','2016-04-27',1),(157,'nadia','contreras','coronel','camelia 14','2481624747','2018-08-13',1),(158,'nadia','contreras','coronel','camelia 14','2481624747','2018-08-13',1),(159,'na','co','co','ca','','2018-08-13',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalleventa`
--

DROP TABLE IF EXISTS `detalleventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `detalleventa` (
  `iddetalleVenta` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) DEFAULT NULL,
  `precioTotal` double DEFAULT NULL,
  `descripcionProd` varchar(250) DEFAULT NULL,
  `tamanio` varchar(30) DEFAULT NULL,
  `unidadMedida` varchar(30) DEFAULT NULL,
  `Productos_idProductos` int(11) NOT NULL,
  `Extras_idExtras` int(11) DEFAULT NULL,
  `Venta_idVenta` int(11) NOT NULL,
  `consecutivoVenta` int(11) DEFAULT NULL,
  PRIMARY KEY (`iddetalleVenta`),
  KEY `fk_detalleVenta_Productos_idx` (`Productos_idProductos`),
  KEY `fk_detalleVenta_Extras1_idx` (`Extras_idExtras`),
  KEY `fk_detalleVenta_Venta1_idx` (`Venta_idVenta`),
  CONSTRAINT `fk_detalleVenta_Extras1` FOREIGN KEY (`Extras_idExtras`) REFERENCES `extras` (`idextras`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_detalleVenta_Productos` FOREIGN KEY (`Productos_idProductos`) REFERENCES `productos` (`idproductos`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_detalleVenta_Venta1` FOREIGN KEY (`Venta_idVenta`) REFERENCES `venta` (`idventa`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalleventa`
--

LOCK TABLES `detalleventa` WRITE;
/*!40000 ALTER TABLE `detalleventa` DISABLE KEYS */;
INSERT INTO `detalleventa` VALUES (5,1,0,'Toluquenia, Extras:---',NULL,NULL,10,NULL,100,1),(6,1,0,'1/2 Mikos special, 1/2 Miko ultra especial, Extras:---',NULL,NULL,10,NULL,100,2),(7,1,85,'Hawaiana | piña y jamon, Extras:---',NULL,NULL,8,NULL,101,1),(8,1,0,'1/2 Mikos special, 1/2 Miko ultra especial, Extras:---',NULL,NULL,8,NULL,101,2),(9,1,23,'Cafe oaxaqueño',NULL,NULL,5,NULL,102,1),(10,1,85,'Toluqueña, Extras:---',NULL,NULL,9,NULL,102,2),(11,1,0,'1/2 Toluqueña, 1/2 Toluqueña, Extras:---',NULL,NULL,9,NULL,102,3),(12,1,85,'Toluqueña, Extras:---',NULL,NULL,9,NULL,103,1),(13,1,0,'1/2 Toluquenia, 1/2 Mikos, Extras:---',NULL,NULL,9,NULL,103,2),(14,1,23,'Cafe oaxaqueño',NULL,NULL,5,NULL,104,1),(15,1,85,'Toluqueña, Extras:---',NULL,NULL,9,NULL,104,2),(16,1,0,'1/2 Toluquenia, 1/2 Mikos, Extras:---',NULL,NULL,9,NULL,104,3),(17,1,23,'Cafe oaxaqueño',NULL,NULL,5,NULL,105,1),(18,1,85,'Toluqueña, Extras:---',NULL,NULL,9,NULL,105,2),(19,1,0,'1/2 Toluquenia, 1/2 Mikos, Extras:---',NULL,NULL,9,NULL,105,3),(20,1,23,'Cafe oaxaqueño',NULL,NULL,5,NULL,106,1),(21,1,85,'Toluqueña, Extras:---',NULL,NULL,9,NULL,106,2),(22,1,0,'1/2 Toluquenia, 1/2 Mikos, Extras:---',NULL,NULL,9,NULL,106,3),(23,1,8,'herseys',NULL,NULL,1,NULL,107,1),(24,1,85,'Toluqueña, Extras:---',NULL,NULL,9,NULL,107,2),(25,1,0,'1/2 Toluqueña, 1/2 Mikos, Extras:---',NULL,NULL,9,NULL,107,3),(26,1,8,'herseys',NULL,NULL,1,NULL,108,1),(27,1,85,'Hawaiana, Extras:---',NULL,NULL,8,NULL,108,2),(28,1,0,'1/2 Hawaiana, 1/2 Toluqueña, Extras:---',NULL,NULL,8,NULL,108,3),(29,1,8,'herseys',NULL,NULL,1,NULL,109,1),(30,1,180,'Toluqueña, Extras:---',NULL,NULL,9,NULL,109,2),(31,1,0,'1/2 Hawaiana, 1/2 Toluqueña, Extras:---',NULL,NULL,9,NULL,109,3),(32,1,8,'herseys',NULL,NULL,1,NULL,110,1),(33,1,85,'Toluqueña, Extras:---',NULL,NULL,9,NULL,111,1),(34,1,0,'Toluquenia, Extras:---',NULL,NULL,9,NULL,111,2),(35,1,8,'herseys',NULL,NULL,1,NULL,111,3),(36,1,85,'Toluqueña, Extras:---',NULL,NULL,9,NULL,112,1),(37,1,0,'Toluquenia, Extras:---',NULL,NULL,9,NULL,112,2),(38,1,85,'Hawaiana, Extras:---',NULL,NULL,8,NULL,113,1),(39,1,0,'Toluqueña, Extras:---',NULL,NULL,8,NULL,113,2),(40,1,85,'Hawaiana, Extras:---',NULL,NULL,8,NULL,114,1),(41,1,0,'Toluqueña, Extras:---',NULL,NULL,8,NULL,114,2),(42,1,85,'Hawaiana, Extras:---',NULL,NULL,8,NULL,115,1),(43,1,0,'Toluqueña, Extras:---',NULL,NULL,8,NULL,115,2),(44,1,85,'Hawaiana, Extras:---',NULL,NULL,8,NULL,116,1),(45,1,0,'1/2 Mikos, 1/2 Toluqueña, Extras:---',NULL,NULL,8,NULL,116,2),(46,4,8,'herseys',NULL,NULL,1,NULL,116,3),(47,1,85,'Toluqueña, Extras:---',NULL,NULL,9,NULL,117,1),(48,1,0,'1/2 Pizza, 1/2 Mikos, Extras:---',NULL,NULL,9,NULL,117,2),(49,2,15,'coca cola 600',NULL,NULL,17,NULL,117,3),(50,1,85,'Hawaiana, Extras:---',NULL,NULL,8,NULL,118,1),(51,1,0,'Toluquenia, Extras:---',NULL,NULL,8,NULL,118,2),(52,1,85,'Toluqueña, Extras:---',NULL,NULL,9,NULL,119,1),(53,1,0,'Toluqueña, Extras:---',NULL,NULL,9,NULL,119,2),(54,1,85,'Hawaiana, Extras:---',NULL,NULL,8,NULL,120,1),(55,1,0,'Toluquenia, Extras:---',NULL,NULL,8,NULL,120,2),(56,1,85,'Hawaiana, Extras:---',NULL,NULL,8,NULL,121,1),(57,1,0,'Toluquenia, Extras:---',NULL,NULL,8,NULL,121,2),(58,1,85,'Hawaiana, Extras:---',NULL,NULL,8,NULL,122,1),(59,1,0,'Toluquenia, Extras:---',NULL,NULL,8,NULL,122,2),(60,1,85,'Hawaiana, Extras:---',NULL,NULL,8,NULL,123,1),(61,1,0,'Toluquenia, Extras:---',NULL,NULL,8,NULL,123,2),(62,1,8,'herseys',NULL,NULL,1,NULL,124,1),(63,1,8,'herseys',NULL,NULL,1,NULL,125,1),(64,1,8,'herseys',NULL,NULL,1,NULL,126,1),(65,1,85,'Hawaiana, Extras:---',NULL,NULL,8,NULL,127,1),(66,1,0,'Hawaiana, Extras:---',NULL,NULL,8,NULL,127,2),(67,1,8,'herseys',NULL,NULL,1,NULL,127,3),(68,1,45,'Hawaiana, Extras:---',NULL,NULL,8,NULL,128,1),(69,1,0,'Mikos, Extras:---',NULL,NULL,11,NULL,128,2),(70,1,10,'Mikos, Extras:Queso extra',NULL,NULL,11,NULL,128,3),(71,1,85,'Hawaiana, Extras:---',NULL,NULL,8,NULL,129,1),(72,1,10,'Toluqueña, Extras:Queso extra',NULL,NULL,8,NULL,129,2),(73,1,85,'Hawaiana, Extras:---',NULL,NULL,8,NULL,130,1),(74,1,10,'Toluqueña, Extras:Queso extra',NULL,NULL,8,NULL,130,2),(75,1,85,'Toluqueña, Extras:---',NULL,NULL,9,NULL,131,1),(76,1,10,'Toluqueña, Extras:Queso extra',NULL,NULL,9,NULL,131,2),(77,1,45,'Hawaiana, Extras:Queso extra',NULL,NULL,8,NULL,132,1),(78,1,95,'Toluqueña, Extras:Queso extra',NULL,NULL,9,NULL,133,1),(79,1,0,'Toluquenia, Extras:---',NULL,NULL,9,NULL,133,2),(80,1,45,'Hawaiana, Extras:---',NULL,NULL,8,NULL,134,1),(81,1,45,'Hawaiana, Extras:---',NULL,NULL,8,NULL,135,1),(82,10,8,'herseys 2',NULL,NULL,18,NULL,136,1),(83,10,8,'herseys 2',NULL,NULL,18,NULL,137,1),(84,10,8,'herseys 2',NULL,NULL,18,NULL,138,1),(85,1,85,'Toluqueña, Extras:---',NULL,NULL,9,NULL,139,1),(86,1,0,'Toluquenia, Extras:---',NULL,NULL,9,NULL,139,2),(87,5,8,'Carne arabe 1KG',NULL,NULL,19,NULL,140,1),(88,5,8,'Carne arabe 1KG',NULL,NULL,19,NULL,141,1),(89,17,8,'Carne arabe 1KG',NULL,NULL,19,NULL,142,1),(90,1,95,'Hawaiana, Extras:Queso extra',NULL,NULL,8,NULL,143,1),(91,1,10,'Toluqueña, Extras:Extra general',NULL,NULL,8,NULL,143,2),(92,1,95,'Toluqueña, Extras:Queso extra',NULL,NULL,9,NULL,144,1),(93,1,10,'1/2 Toluquenia, 1/2 Toluquenia, Extras:Extra general',NULL,NULL,9,NULL,144,2),(94,1,95,'Hawaiana, Extras:Queso extra',NULL,NULL,8,NULL,145,1),(95,1,10,'Toluqueña, Extras:Extra general',NULL,NULL,8,NULL,145,2),(96,1,95,'Hawaiana, Extras:Queso extra',NULL,NULL,8,NULL,146,1),(97,1,10,'Toluqueña, Extras:Extra general',NULL,NULL,8,NULL,146,2),(98,1,95,'Hawaiana, Extras:Queso extra',NULL,NULL,8,NULL,147,1),(99,1,10,'Toluqueña, Extras:Extra general',NULL,NULL,8,NULL,147,2),(100,1,95,'Hawaiana, Extras:Queso extra',NULL,NULL,8,NULL,148,1),(101,1,10,'Toluqueña, Extras:Extra general',NULL,NULL,8,NULL,148,2),(102,10,8,'Carne arabe 1KG',NULL,NULL,19,NULL,149,1),(103,1,45,'Hawaiana, Extras:---',NULL,NULL,8,NULL,150,1),(104,1,95,'Hawaiana, Extras:Queso extra',NULL,NULL,8,NULL,151,1),(105,1,10,'Mikos, Extras:Extra general',NULL,NULL,8,NULL,151,2),(106,1,95,'Hawaiana, Extras:Queso extra',NULL,NULL,8,NULL,152,1),(107,1,10,'Toluqueña, Extras:Extra general',NULL,NULL,8,NULL,152,2),(108,1,95,'Hawaiana, Extras:Queso extra',NULL,NULL,8,NULL,153,1),(109,1,10,'Mikos, Extras:Extra general',NULL,NULL,8,NULL,153,2),(110,1,95,'Hawaiana, Extras:Queso extra',NULL,NULL,8,NULL,154,1),(111,1,10,'1/2 Hawaiana, 1/2 Hawaiana, Extras:Extra general',NULL,NULL,8,NULL,154,2),(112,1,45,'Hawaiana, Extras:Queso extra','C',NULL,8,NULL,155,1),(113,1,45,'Hawaiana, Extras:Queso extra','C',NULL,8,NULL,156,1),(114,1,45,'Hawaiana, Extras:Queso extra','C',NULL,8,NULL,157,1),(115,1,45,'Hawaiana, Extras:---','C',NULL,8,NULL,158,1),(116,1,45,'Hawaiana, Extras:Queso extra','C',NULL,8,NULL,159,1),(117,1,85,'Hawaiana, Extras:---','C',NULL,8,NULL,160,1),(118,1,0,'Hawaiana, Extras:---','C',NULL,8,NULL,160,2),(119,1,45,'Hawaiana, Extras:Queso extra','C',NULL,8,NULL,161,1),(120,5,8,'Carne arabe 1KG',' ',NULL,19,NULL,162,1),(121,10,85,'rebanadas',' ',NULL,20,NULL,163,1),(122,1,85,'rebanadas',' ',NULL,20,NULL,164,1),(123,1,95,'Hawaiana, Extras:Queso extra','C',NULL,8,NULL,165,1),(124,1,0,'Hawaiana, Extras:---','C',NULL,8,NULL,165,2),(125,1,95,'Hawaiana, Extras:Queso extra','C',NULL,8,NULL,166,1),(126,1,10,'Hawaiana, Extras:Queso extra','C',NULL,8,NULL,166,2),(127,2,8,'1 kg carne arabe',' ',NULL,1,NULL,167,1),(128,1,8,'1 kg carne arabe',' ',NULL,1,NULL,168,1),(129,1,85,'rebanadas',' ',NULL,20,NULL,169,1),(130,1,150,'Hawaiana, Extras:---','M',NULL,8,NULL,170,1),(131,1,0,'Hawaiana, Extras:---','M',NULL,8,NULL,170,2),(132,1,150,'Hawaiana, Extras:---','M',NULL,8,NULL,171,1),(133,1,0,'Hawaiana, Extras:---','M',NULL,8,NULL,171,2),(134,1,85,'Hawaiana, Extras:---','C',NULL,8,NULL,172,1),(135,1,0,'Hawaiana, Extras:---','C',NULL,8,NULL,172,2),(136,1,85,'Toluqueña, Extras:---','C',NULL,9,NULL,173,1),(137,1,0,'Hawaiana, Extras:---','C',NULL,9,NULL,173,2),(138,1,85,'Hawaiana, Extras:---','C',NULL,8,NULL,174,1),(139,1,0,'Toluqueña, Extras:---','C',NULL,8,NULL,174,2),(140,1,85,'Hawaiana, Extras:---','C',NULL,8,NULL,175,1),(141,1,0,'Hawaiana, Extras:---','C',NULL,8,NULL,175,2),(142,1,85,'Hawaiana, Extras:---','C',NULL,8,NULL,176,1),(143,1,0,'Hawaiana, Extras:---','C',NULL,8,NULL,176,2),(144,1,85,'Hawaiana, Extras:---','C',NULL,8,NULL,177,1),(145,1,0,'Toluqueña, Extras:---','C',NULL,8,NULL,177,2),(146,1,85,'Hawaiana, Extras:---','C',NULL,8,NULL,178,1),(147,1,0,'Hawaiana, Extras:---','C',NULL,8,NULL,178,2),(148,1,85,'Toluqueña, Extras:---','C',NULL,9,NULL,179,1),(149,1,0,'Toluqueña, Extras:---','C',NULL,9,NULL,179,2),(150,1,60,'pizza promocion',' ',NULL,21,NULL,180,1),(151,1,60,'pizza promocion',' ',NULL,21,NULL,181,1),(152,1,8,'herseys 2',' ',NULL,18,NULL,181,2),(153,1,85,'Hawaiana, Extras:---','C',NULL,8,NULL,182,1),(154,1,0,'Hawaiana, Extras:---','C',NULL,8,NULL,182,2),(155,1,60,'pizza promocion',' ',NULL,21,NULL,183,1),(156,1,60,'pizza promocion',' ',NULL,21,NULL,184,1),(157,1,60,'pizza promocion',' ',NULL,21,NULL,185,1),(158,1,8,'herseys 2',' ',NULL,18,NULL,185,2),(159,1,85,'Hawaiana, Extras:---','C',NULL,8,NULL,186,1),(160,1,0,'Hawaiana, Extras:---','C',NULL,8,NULL,186,2),(161,10,8,'herseys 2',' ',NULL,18,NULL,187,1),(162,1,150,'Hawaiana, Extras:---','M',NULL,8,NULL,188,1),(163,1,0,'Hawaiana, Extras:---','M',NULL,8,NULL,188,2),(164,5,8,'herseys 2',' ',NULL,18,NULL,189,1),(165,1,85,'Hawaiana, Extras:---','C',NULL,8,NULL,190,1),(166,1,0,'Hawaiana, Extras:---','C',NULL,8,NULL,190,2),(167,1,45,'Hawaiana, Extras:Queso extra','C',NULL,8,NULL,191,1),(168,1,45,'Mikos, Extras:Extra general','C',NULL,11,NULL,192,1),(169,1,120,'Hawaiana, Extras:---','F',NULL,8,NULL,193,1),(170,2,90,'mochilas de blocks',' ',NULL,65,NULL,194,1),(171,2,50,'legos guardianes de la galaxia',' ',NULL,32,NULL,195,1),(172,1,60,'legos avengers',' ',NULL,31,NULL,196,1),(173,1,65,'legos jurassic park',' ',NULL,36,NULL,196,2),(174,1,10,'slaim corazón',' ',NULL,97,NULL,197,1),(175,1,10,'slaim espiral',' ',NULL,98,NULL,197,2),(176,3,60,'legos caballeros del zodiaco ',' ',NULL,103,NULL,198,1),(177,1,50,'legos superma',' ',NULL,29,NULL,199,1),(178,1,580,'mochilas gears negras',' ',NULL,27,NULL,200,1),(179,2,65,'legos jurassic park',' ',NULL,36,NULL,200,2),(180,1,300,'mochila lisa impresión',' ',NULL,102,NULL,201,1),(181,1,10,'slaim espiral',' ',NULL,98,NULL,201,2),(182,1,10,'slaim corazón',' ',NULL,97,NULL,202,1),(183,2,10,'slaim barril',' ',NULL,105,NULL,202,2),(184,1,50,'legos ninjago',' ',NULL,34,NULL,203,1),(185,1,60,'legos caballeros del zodiaco ',' ',NULL,103,NULL,204,1),(186,1,10,'slaim espiral',' ',NULL,98,NULL,205,1),(187,1,180,'caja fuerte',' ',NULL,73,NULL,205,2),(188,1,60,'legos avengers',' ',NULL,31,NULL,206,1),(189,1,10,'slaim barril',' ',NULL,105,NULL,207,1),(190,1,100,'balones futbol',' ',NULL,82,NULL,208,1),(191,1,50,'legos superma',' ',NULL,29,NULL,209,1),(192,1,20,'moños',' ',NULL,92,NULL,210,1),(193,1,60,'legos avengers',' ',NULL,31,NULL,211,1),(194,1,10,'slaim barril',' ',NULL,105,NULL,212,1),(195,1,70,'peluche pony',' ',NULL,89,NULL,213,1),(196,1,10,'slaim corazón',' ',NULL,97,NULL,214,1),(197,3,20,'moños',' ',NULL,92,NULL,214,2),(198,2,60,'legos avengers',' ',NULL,31,NULL,215,1),(199,4,15,'labiales',' ',NULL,75,NULL,216,1),(200,1,40,'monederos chicos ',' ',NULL,106,NULL,216,2),(201,1,20,'moños',' ',NULL,92,NULL,217,1),(202,1,10,'slaim corazón',' ',NULL,97,NULL,218,1),(203,1,10,'slaim barril',' ',NULL,105,NULL,218,2),(204,1,10,'slaim espiral',' ',NULL,98,NULL,219,1),(205,1,20,'moños',' ',NULL,92,NULL,220,1),(206,1,50,'legos guardianes de la galaxia',' ',NULL,32,NULL,221,1),(207,1,15,'labiales',' ',NULL,75,NULL,222,1),(208,2,10,'slaim espiral',' ',NULL,98,NULL,223,1),(209,1,10,'slaim barril',' ',NULL,105,NULL,224,1),(210,2,15,'labiales',' ',NULL,75,NULL,224,2),(211,1,90,'cinturones',' ',NULL,94,NULL,225,1),(212,1,80,'carteras largas',' ',NULL,80,NULL,226,1),(213,1,35,'esmalte',' ',NULL,74,NULL,226,2),(214,1,20,'moños',' ',NULL,92,NULL,227,1),(215,1,10,'slaim espiral',' ',NULL,98,NULL,227,2),(216,1,70,'gorras',' ',NULL,90,NULL,228,1),(217,1,90,'bolsa chica de barbitas',' ',NULL,79,NULL,229,1),(218,1,10,'slaim barril',' ',NULL,105,NULL,230,1),(219,1,20,'diademas unicornio',' ',NULL,99,NULL,231,1),(220,1,65,'legos jurassic park',' ',NULL,36,NULL,232,1);
/*!40000 ALTER TABLE `detalleventa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extras`
--

DROP TABLE IF EXISTS `extras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `extras` (
  `idExtras` int(11) NOT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`idExtras`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extras`
--

LOCK TABLES `extras` WRITE;
/*!40000 ALTER TABLE `extras` DISABLE KEYS */;
/*!40000 ALTER TABLE `extras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `installdata`
--

DROP TABLE IF EXISTS `installdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `installdata` (
  `idinstallData` int(11) NOT NULL AUTO_INCREMENT,
  `dataInstalled` date NOT NULL,
  `iva` double NOT NULL,
  `desc1` double DEFAULT NULL,
  `desc2` double DEFAULT NULL,
  `desc3` double DEFAULT NULL,
  `desc4` double DEFAULT NULL,
  `desc5` double DEFAULT NULL,
  `rfc` varchar(50) DEFAULT NULL,
  `sucursal` varchar(250) DEFAULT NULL,
  `direccion` varchar(250) DEFAULT NULL,
  `slogan` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idinstallData`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `installdata`
--

LOCK TABLES `installdata` WRITE;
/*!40000 ALTER TABLE `installdata` DISABLE KEYS */;
INSERT INTO `installdata` VALUES (1,'2018-01-01',0.16,0.1,0.15,0.2,0.25,0.3,'','Allison Pizzas','Reforma #104 colonia El Cerrito','Toma el control de tu negocio');
/*!40000 ALTER TABLE `installdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permisos`
--

DROP TABLE IF EXISTS `permisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `permisos` (
  `idpermisos` int(11) NOT NULL,
  `nombreModulo` varchar(150) DEFAULT NULL,
  `orden` int(11) DEFAULT NULL,
  `moduloOrigen` int(11) DEFAULT NULL,
  `moduloPadre` int(11) DEFAULT NULL,
  `urlAction` varchar(45) DEFAULT NULL,
  `componentString` varchar(500) DEFAULT NULL,
  `icon` varchar(45) DEFAULT NULL,
  `numberIndex` double DEFAULT NULL,
  PRIMARY KEY (`idpermisos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permisos`
--

LOCK TABLES `permisos` WRITE;
/*!40000 ALTER TABLE `permisos` DISABLE KEYS */;
INSERT INTO `permisos` VALUES (1,'Punto de venta tienda',1,1,0,'#',NULL,NULL,1.1),(2,'Clientes',2,1,1,'/clientes',NULL,NULL,1.2),(3,'Productos',3,1,1,'/productos',NULL,NULL,1.3),(4,'Usuarios',4,1,1,'/usuarios',NULL,NULL,1.4),(5,'Dashboard',1,5,0,'/dashboard',NULL,NULL,2.1),(6,'Configuración',1,6,0,'/config',NULL,NULL,3.1),(7,'Caja',5,1,1,'/caja',NULL,NULL,1.5),(8,'Ventas',6,1,1,'/ventas',NULL,NULL,1.6),(9,'tabletSell',1,9,0,'/tabletCell',NULL,NULL,NULL),(10,'Restaurante',1,10,0,'#',NULL,NULL,NULL),(11,'Administración de mesas',2,10,10,'/administrarMesas',NULL,NULL,NULL),(12,'Asignación de mesas',3,10,10,'/asignarMesas',NULL,NULL,NULL);
/*!40000 ALTER TABLE `permisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `productos` (
  `idProductos` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(250) NOT NULL,
  `unidadesEnCaja` int(11) DEFAULT '0',
  `precioUnitarioC` double NOT NULL,
  `uMedida` varchar(45) DEFAULT NULL,
  `presentacion` varchar(45) DEFAULT NULL,
  `cantidadFraccion` double DEFAULT '0',
  `codigo` varchar(100) NOT NULL,
  `estatus` int(11) DEFAULT '0',
  `precioUnitarioV` double NOT NULL,
  `cantidadVendidos` int(11) DEFAULT '0',
  `activo` int(11) DEFAULT '1',
  `TipoProducto` int(11) DEFAULT '1',
  `precioChica` double DEFAULT NULL,
  `precioMediana` double DEFAULT NULL,
  `precioGrande` double DEFAULT NULL,
  `precioFamiliar` double DEFAULT NULL,
  `sucursal_idsucursal` int(11) NOT NULL,
  PRIMARY KEY (`idProductos`),
  KEY `fk_productos_sucursal1_idx` (`sucursal_idsucursal`),
  CONSTRAINT `fk_productos_sucursal1` FOREIGN KEY (`sucursal_idsucursal`) REFERENCES `sucursal` (`idsucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'1 kg carne arabe',74,4,'Kilogramo','Bolsa',0,'7501024578962',0,8,23,1,0,NULL,NULL,NULL,NULL,0),(2,'1/2 kg carne arabe',100,4,'pza','pza',0,'5425017735953',0,5,4,1,0,NULL,NULL,NULL,NULL,0),(3,'alpura',100,10,'pza','pza',0,'643795541237',0,12.5,1,1,0,NULL,NULL,NULL,NULL,0),(4,'',10,0,'pza','pza',0,'',0,0,0,1,0,NULL,NULL,NULL,NULL,0),(5,'Cafe oaxaqueño',-2,12,'Pieza','Caja',0,'12321333',0,23,7,1,0,NULL,NULL,NULL,NULL,0),(6,'test 2',45,1,'Pieza','Caja',0,'2343',0,34,0,1,0,NULL,NULL,NULL,NULL,0),(7,'fsdfsdfsd',3,2,'Pieza','Caja',0,'23123123',0,2,0,1,0,NULL,NULL,NULL,NULL,0),(8,'Hawaiana | piña y jamon y un chorro de ingredientes mas',-155,15,'Pieza','Caja',0,'12345678',0,23,187,1,1,85,150,180,NULL,0),(9,'Toluqueña | asshashashas',-27,15,'Pieza','Caja',0,'0',0,180,50,1,1,85,150,180,NULL,0),(10,'Toluquenia',-5,85,'---','---',0,'223333',0,85,6,1,1,NULL,NULL,NULL,NULL,0),(11,'Mikos special',-2,0,'---','---',0,'',0,0,3,1,1,NULL,NULL,NULL,NULL,0),(12,'Miko ultra especial',1,0,'---','---',0,'',0,0,0,1,1,1,2,3,4,0),(13,'productos miko corp',15,12,'Pieza','---',0,'',0,15,0,1,0,0,0,0,0,0),(14,'Queso extra',1,1,'Pieza','---',0,'7777',0,15,0,1,3,0,0,0,0,0),(15,'Extra general',1,1,'Pieza','---',0,'444',0,15,0,1,3,0,0,0,0,0),(16,'Pizza especial tocino',1,0,'---','---',0,'',0,0,0,1,1,80,180,220,250,0),(17,'coca cola 600',99,8,'---','---',0,'',0,15,1,1,0,0,0,0,0,0),(18,'herseys 2',60,4,'Kilogramo','Bolsa',0,'7501024578962',0,8,37,1,0,NULL,NULL,NULL,NULL,0),(19,'Carne arabe 1KG',35,4,'Kilogramo','Bolsa',0,'7501024578962',0,8,62,1,4,NULL,NULL,NULL,NULL,0),(20,'rebanadas',3,15,'---','---',0,'223333',0,85,12,1,0,85,155,180,220,0),(21,'pizza promocion',995,60,'---','---',0,'',0,60,5,1,5,0,0,0,0,0),(22,'mochila lalaloopsy',1,242.5,'Pieza','Pieza',0,'',0,485,0,1,0,0,0,0,0,0),(23,'mochila mugosos',3,242.5,'Pieza','Pieza',0,'',0,485,0,1,0,0,0,0,0,0),(24,'mochila precious',1,242.5,'Pieza','Pieza',0,'',0,485,0,1,0,0,0,0,0,0),(25,'mochilas gears azules',6,242.5,'Pieza','Pieza',0,'',0,485,0,1,0,0,0,0,0,0),(26,'mochilas corazones love you',3,265,'Pieza','Pieza',0,'',0,530,0,1,0,0,0,0,0,0),(27,'mochilas gears negras',2,290,'Pieza','Pieza',0,'',0,580,2,1,0,0,0,0,0,0),(28,'mochilas autos',2,255,'Pieza','Pieza',0,'',0,510,0,1,0,0,0,0,0,0),(29,'legos superma',3,15.63833333,'Pieza','Pieza',0,'',0,50,3,1,0,0,0,0,0,0),(30,'legos star wars',6,21.13125,'Pieza','Pieza',0,'',0,60,2,1,0,0,0,0,0,0),(31,'legos avengers',17,23.48208333,'Pieza','Pieza',0,'',0,60,7,1,0,0,0,0,0,0),(32,'legos guardianes de la galaxia',14,15.63888889,'Pieza','Pieza',0,'',0,50,4,1,0,0,0,0,0,0),(33,'legos daredevil',6,15.63833333,'Pieza','Pieza',0,'',0,50,0,1,0,0,0,0,0,0),(34,'legos ninjago',9,15.63916667,'Pieza','Pieza',0,'',0,50,3,1,0,0,0,0,0,0),(35,'legos ironman Ultron',3,15.63833333,'Pieza','Pieza',0,'',0,50,3,1,0,0,0,0,0,0),(36,'legos jurassic park',10,25.570625,'Pieza','Pieza',0,'',0,65,6,1,0,0,0,0,0,0),(37,'caballero zodiaco seiya',0,150,'Pieza','Pieza',0,'',0,300,1,1,0,0,0,0,0,0),(38,'caballero zodiaco radamanthys',1,150,'Pieza','Pieza',0,'',0,300,0,1,0,0,0,0,0,0),(39,'llaveros shreck',2,15,'Pieza','Pieza',0,'',0,30,0,1,0,0,0,0,0,0),(40,'llaveros de peluche',8,25,'Pieza','Pieza',0,'',0,50,0,1,0,0,0,0,0,0),(41,'barbie',2,65,'Pieza','Pieza',0,'',0,130,0,1,0,0,0,0,0,0),(42,'playdoh tarro de galletas',1,80,'Pieza','Pieza',0,'',0,150,0,1,0,0,0,0,0,0),(43,'muñeco avenger patriot',1,65,'Pieza','Pieza',0,'',0,130,0,1,0,0,0,0,0,0),(44,'muñeco avenger war machine',1,65,'Pieza','Pieza',0,'',0,130,0,1,0,0,0,0,0,0),(45,'muñeco avenger vision',1,65,'Pieza','Pieza',0,'',0,130,0,1,0,0,0,0,0,0),(46,'muñeco spiderman',1,65,'Pieza','Pieza',0,'',0,130,0,1,0,0,0,0,0,0),(47,'muñeco batman',0,65,'Pieza','Pieza',0,'',0,130,1,1,0,0,0,0,0,0),(48,'playschool cubo de formas',1,80,'Pieza','Pieza',0,'',0,160,0,1,0,0,0,0,0,0),(49,'muñecos halo',2,65,'Pieza','Pieza',0,'',0,130,0,1,0,0,0,0,0,0),(50,'avenger black widow',1,40,'Pieza','Pieza',0,'',0,80,0,1,0,0,0,0,0,0),(51,'avenger hulk',1,40,'Pieza','Pieza',0,'',0,80,0,1,0,0,0,0,0,0),(52,'avenger iron man',1,40,'Pieza','Pieza',0,'',0,80,0,1,0,0,0,0,0,0),(53,'avenger capitan america',1,40,'Pieza','Pieza',0,'',0,80,0,1,0,0,0,0,0,0),(54,'playdoh helados',1,85,'Pieza','Pieza',0,'',0,170,0,1,0,0,0,0,0,0),(55,'playdoh cocina',1,85,'Pieza','Pieza',0,'',0,170,0,1,0,0,0,0,0,0),(56,'muñeco max steel cytro turbo taladro',1,85,'Pieza','Pieza',0,'',0,170,0,1,0,0,0,0,0,0),(57,'tarjeteros',11,15,'Pieza','Pieza',0,'',0,30,1,1,0,0,0,0,0,0),(58,'carteras p/caballero lisas',3,70,'Pieza','Pieza',0,'',0,140,0,1,0,0,0,0,0,0),(59,'carteras p/caballero piel',4,85,'Pieza','Pieza',0,'',0,170,2,1,0,0,0,0,0,0),(60,'chamarras',2,220,'Pieza','Pieza',0,'',0,320,1,1,0,0,0,0,0,0),(61,'mochilas de rueditas (spiderman, paw patrol, chamoy)',2,170,'Pieza','Pieza',0,'',0,340,1,1,0,0,0,0,0,0),(62,'mochilas lisas (guardianes 2, rana, hello kitty)',4,150,'Pieza','Pieza',0,'',0,300,0,1,0,0,0,0,0,0),(63,'reloj de pared',3,59,'Pieza','Pieza',0,'',0,120,0,1,0,0,0,0,0,0),(64,'sonajas',2,28,'Pieza','Pieza',0,'',0,60,0,1,0,0,0,0,0,0),(65,'mochilas de blocks',0,45,'Pieza','Pieza',0,'',0,90,2,1,0,0,0,0,0,0),(66,'portaretrato bco y café 13*18',2,28,'Pieza','Pieza',0,'',0,56,0,1,0,0,0,0,0,0),(67,'portaretrato 15*20',2,36,'Pieza','Pieza',0,'',0,72,1,1,0,0,0,0,0,0),(68,'portaretrato azul 10*15',1,20,'Pieza','Pieza',0,'',0,40,0,1,0,0,0,0,0,0),(69,'portaretrato de conejo',1,48,'Pieza','Pieza',0,'',0,96,0,1,0,0,0,0,0,0),(70,'portaretrato de corazón',1,31,'Pieza','Pieza',0,'',0,62,0,1,0,0,0,0,0,0),(71,'portaretrato love',1,31,'Pieza','Pieza',0,'',0,62,0,1,0,0,0,0,0,0),(72,'portaretrato verde',1,24,'Pieza','Pieza',0,'',0,48,0,1,0,0,0,0,0,0),(73,'caja fuerte',0,90,'Pieza','Pieza',0,'',0,180,1,1,0,0,0,0,0,0),(74,'esmalte',11,16,'Pieza','Pieza',0,'',0,35,1,1,0,0,0,0,0,0),(75,'labiales',4,7,'Pieza','Pieza',0,'',0,15,8,1,0,0,0,0,0,0),(76,'monederos',2,65,'Pieza','Pieza',0,'',0,130,0,1,0,0,0,0,0,0),(77,'carteras gde.',3,50,'Pieza','Pieza',0,'',0,100,0,1,0,0,0,0,0,0),(78,'monederos (bhuo, flamingo, playa)',3,30,'Pieza','Pieza',0,'',0,60,0,1,0,0,0,0,0,0),(79,'bolsa chica de barbitas',2,45,'Pieza','Pieza',0,'',0,90,1,1,0,0,0,0,0,0),(80,'carteras largas',7,40,'Pieza','Pieza',0,'',0,80,1,1,0,0,0,0,0,0),(81,'dinosaurios chillones',5,14,'Pieza','Pieza',0,'',0,28,2,1,0,0,0,0,0,0),(82,'balones futbol',2,50,'Pieza','Pieza',0,'',0,100,1,1,0,0,0,0,0,0),(83,'lego aviones',3,35,'Pieza','Pieza',0,'',0,70,0,1,0,0,0,0,0,0),(84,'sellos',3,15,'Pieza','Pieza',0,'',0,30,0,1,0,0,0,0,0,0),(85,'espejos',4,13,'Pieza','Pieza',0,'',0,26,1,1,0,0,0,0,0,0),(86,'aviones fricción',11,20,'Pieza','Pieza',0,'',0,30,1,1,0,0,0,0,0,0),(87,'bolsas carita muñeca asiatica',3,50,'Pieza','Pieza',0,'',0,100,0,1,0,0,0,0,0,0),(88,'bolsa carita muñeca asiatica gde',1,60,'Pieza','Pieza',0,'',0,120,0,1,0,0,0,0,0,0),(89,'peluche pony',2,35,'Pieza','Pieza',0,'',0,70,1,1,0,0,0,0,0,0),(90,'gorras',5,35,'Pieza','Pieza',0,'',0,70,1,1,0,0,0,0,0,0),(91,'mochila halo',0,450,'Pieza','Pieza',0,'',0,600,1,1,0,0,0,0,0,0),(92,'moños',17,10,'Pieza','Pieza',0,'',0,20,7,1,0,0,0,0,0,0),(93,'mochilas rueditas gde.',3,160,'Pieza','Pieza',0,'',0,320,0,1,0,0,0,0,0,0),(94,'cinturones',5,45,'Pieza','Pieza',0,'',0,90,1,1,0,0,0,0,0,0),(95,'bolsas de mano',5,100,'Pieza','Pieza',0,'',0,200,2,1,0,0,0,0,0,0),(96,'dinosaurios crecencios',45,3,'Pieza','Pieza',0,'',0,10,3,1,0,0,0,0,0,0),(97,'slaim corazón',19,3.33,'Pieza','Pieza',0,'',0,10,5,1,0,0,0,0,0,0),(98,'slaim espiral',4,6,'Pieza','Pieza',0,'',0,10,8,1,0,0,0,0,0,0),(99,'diademas unicornio',11,10,'Pieza','Pieza',0,'',0,20,1,1,0,0,0,0,0,0),(100,'mochilas de peluche',3,35,'Pieza','Pieza',0,'',0,70,1,1,0,0,0,0,0,0),(101,'cartera sencilla',8,35,'Pieza','Pieza',0,'',0,70,4,1,0,0,0,0,0,0),(102,'mochila lisa impresión',14,150,'Pieza','Pieza',0,'',0,300,6,1,0,0,0,0,0,0),(103,'legos caballeros del zodiaco ',5,40,'Pieza','---',0,'',0,60,4,1,0,0,0,0,0,0),(104,'autos batman',6,164,'Pieza','---',0,'',0,250,0,1,0,0,0,0,0,0),(105,'slaim barril',41,6,'Pieza','---',0,'',0,10,7,1,0,0,0,0,0,0),(106,'monederos chicos ',5,20,'Pieza','---',0,'',0,40,1,1,0,0,0,0,0,0);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retiro`
--

DROP TABLE IF EXISTS `retiro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `retiro` (
  `idretiro` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` double DEFAULT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `usuario` int(11) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `consecutivo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idretiro`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retiro`
--

LOCK TABLES `retiro` WRITE;
/*!40000 ALTER TABLE `retiro` DISABLE KEYS */;
INSERT INTO `retiro` VALUES (1,230,'chelas',NULL,'2016-02-28 00:58:30',NULL),(2,150,'cervezas',NULL,'2016-02-28 01:11:02',NULL),(3,12,'Test',NULL,'2016-02-28 01:13:48',NULL),(4,134,'tetsts',1,'2016-02-28 01:36:15',1),(5,2342,'rwerwerwr',1,'2016-02-28 01:38:14',2),(6,1212,'wrwerwer',1,'2016-02-28 01:42:38',3),(7,1212,'wrwerwer',1,'2016-02-28 01:43:09',4),(8,1212,'wrwerwer',1,'2016-02-28 01:43:10',5),(9,1212,'wrwerwer',1,'2016-02-28 01:43:11',6),(10,1212,'wrwerwer',1,'2016-02-28 01:43:12',7),(11,1212,'wrwerwer',1,'2016-02-28 01:43:13',8),(12,122,'tester',1,'2016-02-28 02:05:55',9),(13,15,'clarasol',1,'2016-03-01 20:26:15',1),(14,20,'clarasol -------------',1,'2016-03-01 20:27:01',2),(15,0,'sin gastos en el día',1,'2016-03-06 19:47:03',1),(16,250,'Queso',1,'2016-03-18 16:11:07',1),(17,0,'Sin  gastos',1,'2016-03-18 16:14:07',2),(18,0,'Sin gasto',1,'2016-03-18 17:08:15',3),(19,250,'Queso mozarella',1,'2016-03-18 17:09:25',4),(20,0,'sin gastos',1,'2016-05-10 20:49:47',1),(21,0,'sin gastos',1,'2016-05-10 22:41:15',2),(22,0,'Sin gastos',1,'2016-05-10 22:44:36',3),(23,0,'sin gastos',1,'2016-05-10 22:46:50',4),(24,0,'150',1,'2016-05-10 22:49:18',5),(25,0,'sin gastos',1,'2016-05-12 20:46:26',1),(26,0,'sin gasto',1,'2016-05-12 20:48:29',2),(27,0,'--------',1,'2016-05-12 20:50:55',3),(28,0,'------------',1,'2016-05-12 21:32:21',4);
/*!40000 ALTER TABLE `retiro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rol` (
  `idrol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'superAdmin','product owner'),(2,'admin','product main client'),(3,'supervisor','confidence personal'),(4,'seller','posUser'),(5,'waiter','mesero');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol_has_permisos`
--

DROP TABLE IF EXISTS `rol_has_permisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rol_has_permisos` (
  `rol_idrol` int(11) NOT NULL,
  `permisos_idpermisos` int(11) NOT NULL,
  PRIMARY KEY (`rol_idrol`,`permisos_idpermisos`),
  KEY `fk_rol_has_permisos_permisos1_idx` (`permisos_idpermisos`),
  KEY `fk_rol_has_permisos_rol1_idx` (`rol_idrol`),
  CONSTRAINT `fk_rol_has_permisos_permisos1` FOREIGN KEY (`permisos_idpermisos`) REFERENCES `permisos` (`idpermisos`),
  CONSTRAINT `fk_rol_has_permisos_rol1` FOREIGN KEY (`rol_idrol`) REFERENCES `rol` (`idrol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol_has_permisos`
--

LOCK TABLES `rol_has_permisos` WRITE;
/*!40000 ALTER TABLE `rol_has_permisos` DISABLE KEYS */;
INSERT INTO `rol_has_permisos` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(4,7),(1,8),(4,8);
/*!40000 ALTER TABLE `rol_has_permisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sucursal` (
  `idsucursal` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `rfc` varchar(15) DEFAULT NULL,
  `razonsocial` varchar(250) DEFAULT NULL,
  `usuarios_idusuario` int(11) NOT NULL,
  PRIMARY KEY (`idsucursal`),
  KEY `fk_sucursal_usuarios1_idx` (`usuarios_idusuario`),
  CONSTRAINT `fk_sucursal_usuarios1` FOREIGN KEY (`usuarios_idusuario`) REFERENCES `usuarios` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
/*!40000 ALTER TABLE `sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuarios` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(45) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `nombre` varchar(150) DEFAULT NULL,
  `apellidop` varchar(150) DEFAULT NULL,
  `apellidom` varchar(150) DEFAULT NULL,
  `c1` int(11) DEFAULT '0',
  `c2` int(11) DEFAULT '0',
  `c3` int(11) DEFAULT '0',
  `c4` int(11) DEFAULT '1',
  `c5` int(11) DEFAULT '0',
  `estatus` int(11) DEFAULT '1',
  `email` varchar(100) DEFAULT NULL,
  `telefono` varchar(16) DEFAULT NULL,
  `rol_idrol` int(11) NOT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `fk_usuarios_rol1_idx` (`rol_idrol`),
  CONSTRAINT `fk_usuarios_rol1` FOREIGN KEY (`rol_idrol`) REFERENCES `rol` (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin','$2a$10$WdN1beQtYfCT/jSp2j1SR.IWG3FboIFVcrGiFVKBwUvmgbCCmmlxC','Juan Jose Perez',NULL,NULL,1,1,1,1,1,1,'a@hotmail.com',NULL,1),(2,'test','test','Test modificacion2',NULL,NULL,1,0,0,1,1,0,NULL,NULL,0),(3,'vendedor','$2a$10$WdN1beQtYfCT/jSp2j1SR.IWG3FboIFVcrGiFVKBwUvmgbCCmmlxC','vendedor',NULL,NULL,0,0,0,1,0,1,'b@hotmail.com',NULL,4),(4,'administrador','administrador2','Jose Perez Lopez',NULL,NULL,0,0,0,1,0,1,NULL,NULL,0),(5,'newUser','newUser','Jose Jose',NULL,NULL,0,0,0,1,0,1,NULL,NULL,0),(6,'vendedor','vendedor','vendedor',NULL,NULL,0,0,0,1,0,1,NULL,NULL,0);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `venta` (
  `idVenta` int(11) NOT NULL AUTO_INCREMENT,
  `lugarDeEntrega` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `cliente_idcliente` int(11) NOT NULL,
  `usuarios_idusuario` int(11) NOT NULL,
  `fechaVenta` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `consecutivoVenta` int(11) DEFAULT NULL,
  `efectivoRecib` double DEFAULT NULL,
  `cambio` double DEFAULT NULL,
  PRIMARY KEY (`idVenta`),
  KEY `fk_Venta_cliente1_idx` (`cliente_idcliente`),
  KEY `fk_Venta_usuarios1_idx` (`usuarios_idusuario`),
  CONSTRAINT `fk_Venta_cliente1` FOREIGN KEY (`cliente_idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Venta_usuarios1` FOREIGN KEY (`usuarios_idusuario`) REFERENCES `usuarios` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=233 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` VALUES (100,NULL,0,1,1,'2016-03-08 19:44:14',1,NULL,NULL),(101,NULL,85,1,1,'2016-03-08 19:44:49',2,NULL,NULL),(102,NULL,108,117,1,'2016-03-10 12:45:11',1,NULL,NULL),(103,NULL,85,118,1,'2016-03-10 13:32:00',2,NULL,NULL),(104,NULL,108,118,1,'2016-03-10 13:32:31',3,NULL,NULL),(105,NULL,108,118,1,'2016-03-10 13:32:51',4,NULL,NULL),(106,NULL,108,118,1,'2016-03-10 13:33:01',5,NULL,NULL),(107,NULL,93,1,1,'2016-03-10 13:34:04',6,NULL,NULL),(108,NULL,93,119,1,'2016-03-10 13:39:19',7,NULL,NULL),(109,NULL,188,1,1,'2016-03-10 13:41:20',8,NULL,NULL),(110,NULL,8,1,1,'2016-03-10 13:44:45',9,NULL,NULL),(111,NULL,93,124,1,'2016-03-15 08:07:16',1,NULL,NULL),(112,NULL,85,124,1,'2016-03-15 08:08:38',2,NULL,NULL),(113,NULL,85,140,1,'2016-03-15 09:06:40',3,NULL,NULL),(114,NULL,85,142,1,'2016-03-15 09:09:42',4,NULL,NULL),(115,NULL,85,143,1,'2016-03-15 09:10:53',5,NULL,NULL),(116,NULL,117,146,1,'2016-03-18 10:19:47',1,NULL,NULL),(117,NULL,115,148,1,'2016-03-18 11:12:54',2,NULL,NULL),(118,NULL,85,149,1,'2016-03-18 11:31:46',3,NULL,NULL),(119,NULL,85,1,1,'2016-03-18 12:46:25',4,NULL,NULL),(120,NULL,85,1,1,'2016-03-25 11:36:16',1,NULL,NULL),(121,NULL,85,1,1,'2016-03-25 11:36:36',2,NULL,NULL),(122,NULL,85,2,1,'2016-03-25 11:36:53',3,NULL,NULL),(123,NULL,85,1,1,'2016-03-25 11:37:19',4,NULL,NULL),(124,NULL,8,1,1,'2016-03-25 12:05:13',5,NULL,NULL),(125,NULL,8,1,1,'2016-03-25 12:07:06',6,NULL,NULL),(126,NULL,8,1,1,'2016-03-25 12:11:23',7,NULL,NULL),(127,NULL,93,1,1,'2016-03-25 12:15:34',8,NULL,NULL),(128,NULL,55,1,1,'2016-03-28 14:56:12',1,NULL,NULL),(129,NULL,95,1,1,'2016-03-28 15:01:44',2,NULL,NULL),(130,NULL,95,1,1,'2016-03-28 15:02:52',3,NULL,NULL),(131,NULL,95,1,1,'2016-03-28 15:05:08',4,NULL,NULL),(132,NULL,45,1,1,'2016-03-28 15:07:25',5,NULL,NULL),(133,NULL,95,1,1,'2016-03-28 15:26:32',6,NULL,NULL),(134,NULL,45,1,1,'2016-03-28 15:29:17',7,NULL,NULL),(135,NULL,45,1,1,'2016-03-28 15:29:48',8,NULL,NULL),(136,NULL,80,1,1,'2016-04-11 13:20:02',1,NULL,NULL),(137,NULL,80,1,1,'2016-04-11 13:23:43',2,NULL,NULL),(138,NULL,80,1,1,'2016-04-11 13:30:10',3,NULL,NULL),(139,NULL,85,1,1,'2016-04-11 13:34:47',4,NULL,NULL),(140,NULL,40,1,1,'2016-04-11 13:39:21',5,NULL,NULL),(141,NULL,40,1,1,'2016-04-11 13:49:53',6,NULL,NULL),(142,NULL,136,1,1,'2016-04-11 13:53:51',7,NULL,NULL),(143,NULL,105,1,1,'2016-04-11 13:56:14',8,NULL,NULL),(144,NULL,105,1,1,'2016-04-11 14:01:33',9,NULL,NULL),(145,NULL,105,1,1,'2016-04-11 14:03:37',10,NULL,NULL),(146,NULL,105,1,1,'2016-04-11 14:04:36',11,NULL,NULL),(147,NULL,105,1,1,'2016-04-11 14:06:55',12,NULL,NULL),(148,NULL,105,1,1,'2016-04-11 14:08:48',13,NULL,NULL),(149,NULL,80,1,1,'2016-04-11 14:10:33',14,NULL,NULL),(150,NULL,45,150,1,'2016-04-14 11:36:43',1,NULL,NULL),(151,NULL,105,1,1,'2016-04-14 13:30:12',2,NULL,NULL),(152,NULL,105,1,1,'2016-04-14 13:32:45',3,NULL,NULL),(153,NULL,105,151,1,'2016-04-14 14:20:02',4,NULL,NULL),(154,NULL,105,1,1,'2016-04-14 14:27:49',5,NULL,NULL),(155,NULL,45,1,1,'2016-04-25 18:57:48',1,NULL,NULL),(156,NULL,45,1,1,'2016-04-25 19:17:56',2,NULL,NULL),(157,NULL,45,1,1,'2016-04-25 20:19:30',3,NULL,NULL),(158,NULL,45,2,1,'2016-04-25 20:48:21',4,NULL,NULL),(159,NULL,45,1,1,'2016-04-26 12:33:51',1,NULL,NULL),(160,NULL,85,1,1,'2016-04-26 12:35:06',2,NULL,NULL),(161,NULL,45,2,1,'2016-04-26 12:38:15',3,NULL,NULL),(162,NULL,40,1,1,'2016-04-26 12:43:10',4,NULL,NULL),(163,NULL,850,1,1,'2016-04-26 12:46:56',5,NULL,NULL),(164,NULL,85,1,1,'2016-04-26 12:47:32',6,NULL,NULL),(165,NULL,95,1,1,'2016-04-26 12:48:03',7,NULL,NULL),(166,NULL,105,1,1,'2016-04-26 14:56:43',8,150,45),(167,NULL,16,1,1,'2016-04-26 14:58:39',9,50,34),(168,NULL,8,2,1,'2016-04-26 15:01:17',10,50,42),(169,NULL,85,1,1,'2016-04-26 15:02:20',11,100,15),(170,NULL,150,152,1,'2016-04-27 14:47:36',1,200,50),(171,NULL,150,1,1,'2016-04-27 14:48:40',2,200,50),(172,NULL,85,154,1,'2016-04-27 14:57:26',3,100,15),(173,NULL,85,3,1,'2016-04-27 15:01:07',4,150,65),(174,NULL,85,155,1,'2016-04-27 15:03:20',5,150,65),(175,NULL,85,155,1,'2016-04-27 15:07:08',6,150,65),(176,NULL,85,156,1,'2016-04-27 15:12:29',7,150,65),(177,NULL,85,1,1,'2016-04-27 15:23:21',8,150,65),(178,NULL,85,1,1,'2016-05-10 15:48:51',1,100,15),(179,NULL,85,1,1,'2016-05-10 15:49:19',2,100,15),(180,NULL,60,1,1,'2016-05-10 17:52:54',3,100,40),(181,NULL,68,1,1,'2016-05-10 17:57:07',4,100,32),(182,NULL,85,1,1,'2016-05-10 17:57:44',5,100,15),(183,NULL,60,1,1,'2016-05-10 17:58:10',6,100,40),(184,NULL,60,1,1,'2016-05-10 18:01:20',7,100,40),(185,NULL,68,1,1,'2016-05-10 18:02:21',8,150,82),(186,NULL,85,1,1,'2016-05-12 15:38:47',1,100,15),(187,NULL,80,1,1,'2016-05-12 15:39:40',2,100,20),(188,NULL,150,1,1,'2016-05-12 15:39:59',3,200,50),(189,NULL,40,1,1,'2016-05-12 15:40:30',4,50,10),(190,NULL,85,1,1,'2016-05-12 15:49:29',5,100,15),(191,NULL,45,1,1,'2018-03-03 13:39:32',1,200,155),(192,NULL,45,1,1,'2018-03-03 14:00:04',2,200,155),(193,NULL,120,1,1,'2018-03-03 14:14:13',3,500,380),(194,NULL,180,1,1,'2018-08-13 10:52:26',1,180,0),(195,NULL,100,1,1,'2018-08-13 18:42:59',2,100,0),(196,NULL,125,1,1,'2018-08-13 18:44:14',3,125,0),(197,NULL,20,1,1,'2018-08-13 19:54:38',4,20,0),(198,NULL,180,1,1,'2018-08-14 10:08:51',1,180,0),(199,NULL,50,1,1,'2018-08-14 12:51:40',2,50,0),(200,NULL,710,1,1,'2018-08-14 13:29:53',3,1000,290),(201,NULL,310,1,1,'2018-08-14 18:44:43',4,310,0),(202,NULL,30,1,1,'2018-08-15 15:21:05',1,30,0),(203,NULL,50,1,1,'2018-08-15 15:21:42',2,50,0),(204,NULL,60,1,1,'2018-08-15 15:53:34',3,60,0),(205,NULL,190,1,1,'2018-08-17 11:01:25',1,190,0),(206,NULL,60,1,1,'2018-08-17 11:11:38',2,100,40),(207,NULL,10,1,1,'2018-08-17 12:16:59',3,10,0),(208,NULL,100,1,1,'2018-08-17 15:13:06',4,100,0),(209,NULL,50,1,1,'2018-08-17 18:49:28',5,100,50),(210,NULL,20,1,1,'2018-08-18 10:54:02',1,100,80),(211,NULL,60,1,1,'2018-08-18 12:29:21',2,60,0),(212,NULL,10,1,1,'2018-08-18 12:33:14',3,10,0),(213,NULL,70,1,1,'2018-08-18 13:30:18',4,70,0),(214,NULL,70,1,1,'2018-08-18 14:11:03',5,70,0),(215,NULL,120,1,1,'2018-08-18 15:11:02',6,120,0),(216,NULL,100,1,1,'2018-08-18 15:16:16',7,100,0),(217,NULL,20,1,1,'2018-08-18 15:31:16',8,20,0),(218,NULL,20,1,1,'2018-08-20 11:26:41',1,200,180),(219,NULL,10,1,1,'2018-08-20 13:54:25',2,10,0),(220,NULL,20,1,1,'2018-08-21 13:40:43',1,20,0),(221,NULL,50,1,1,'2018-08-21 18:12:41',2,50,0),(222,NULL,15,1,1,'2018-08-21 18:13:54',3,20,5),(223,NULL,20,1,1,'2018-08-22 13:39:06',1,20,0),(224,NULL,40,1,1,'2018-08-24 10:40:37',1,50,10),(225,NULL,90,1,1,'2018-08-24 10:41:14',2,90,0),(226,NULL,115,1,1,'2018-08-27 15:09:05',1,115,0),(227,NULL,30,1,1,'2018-08-27 15:11:29',2,30,0),(228,NULL,70,1,1,'2018-08-27 16:29:26',3,70,0),(229,NULL,90,1,1,'2018-08-28 09:22:36',1,90,0),(230,NULL,10,1,1,'2018-08-29 13:40:13',1,10,0),(231,NULL,20,1,1,'2018-08-31 13:03:48',1,200,180),(232,NULL,65,1,1,'2018-08-31 13:28:38',2,65,0);
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-11 16:24:40
