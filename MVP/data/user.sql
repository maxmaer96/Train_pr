-- MariaDB dump 10.17  Distrib 10.4.14-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: user
-- ------------------------------------------------------
-- Server version	10.4.14-MariaDB

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
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(300) NOT NULL,
  `kkal` int(11) NOT NULL,
  `description` varchar(300) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'Яблоко',100,'Фрукт свежий'),(2,'Алёнка',400,'Шоколад черный');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sotrudnikroles`
--

DROP TABLE IF EXISTS `sotrudnikroles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sotrudnikroles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sotrudnikroles`
--

LOCK TABLES `sotrudnikroles` WRITE;
/*!40000 ALTER TABLE `sotrudnikroles` DISABLE KEYS */;
INSERT INTO `sotrudnikroles` VALUES (1,'Бухгалтер'),(2,'Директор по развитию'),(3,'Менеджер по работе с клиентами'),(4,'Руководитель отдела технической поддержки'),(5,'Руководитель по работе с клиентами'),(6,'Специалист ТП (выездной инженер)'),(7,'Технический департамент'),(8,'Консультант'),(9,'Оператор');
/*!40000 ALTER TABLE `sotrudnikroles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sotrudniks`
--

DROP TABLE IF EXISTS `sotrudniks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sotrudniks` (
  `Num` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `SecondName` varchar(100) NOT NULL,
  `FirstName` varchar(100) NOT NULL,
  `LastName` varchar(100) NOT NULL,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`Num`),
  KEY `role` (`role`) USING BTREE,
  CONSTRAINT `sotrudniks_ibfk_1` FOREIGN KEY (`role`) REFERENCES `sotrudnikroles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sotrudniks`
--

LOCK TABLES `sotrudniks` WRITE;
/*!40000 ALTER TABLE `sotrudniks` DISABLE KEYS */;
INSERT INTO `sotrudniks` VALUES ('ID1516','1516','Шилов','Вольдемар','Степанович',5),('ID1517','1517','Мясников','Власий','Лаврентьевич',4),('ID1518','1518','Макаров','Овидий','Вячеславович',6),('ID1519','1519','Рябов','Валентин','Миронович',6),('ID1520','1520','Архипов','Валентин','Богуславович',6),('ID1521','1521','Федосеев','Август','Аристархович',6),('ID1522','1522','Козлов','Азарий','Владимирович',6),('ID1523','1523','Кондратьев','Владислав','Агафонович',6),('ID1524','1524','Лапин','Альфред','Александрович',6),('ID1525','1525','Тимофеев','Май','Филиппович',6),('ID1526','1526','Сидоров','Арсений','Богуславович',2),('ID1527','1527','Самсонов','Егор','Тимофеевич',7),('ID1528','1528','Журавлёв','Панкрат','Валерьянович',7),('ID1529','1529','Жуков','Виталий','Проклович',7),('ID1530','1530','Якушев','Гордий','Гордеевич',7),('ID1531','1531','Симонова','Сильвия','Валерьевна',1),('ID1532','1532','Дмитриева','Вероника','Прокловна',1),('ID1533','1533','Панова','Марина','Викторовна',1),('ID1534','1534','Афанасьева','Дарина','Львовна',3),('ID1535','1535','Шубина','Мелитта','Тарасовна',3),('ID1536','1536','Белякова','Элла','Игнатьевна',3),('ID1537','1537','Осипова','Индира','Оскаровна',3),('ID1538','1538','Ефремова','Анна','Николаевна',3),('ID1539','1539','Симонова','Герда','Наумовна',3),('ID1540','1540','Авдеева','Нега','Евгеньевна',3),('ID1541','1541','Никифорова','Алиса','Робертовна',3),('ID1542','1542','Семёнова','Габриэлла','Иринеевна',3),('ID1543','1543','Авдеева','Таисия','Анатольевна',3),('ID1544','1544','Мухина','Доминика','Лукьяновна',3),('ID1545','1545','Муравьёва','Римма','Максовна',3),('ID1546','1546','Оперотова','Оператор','Оператовна',9),('ID1547','1547','Оператов','Оператор','Оператов',9),('ID1548','1548','Консультатова','Консультант','Консультантовна',8),('ID1549','1549','Консультантов','Консультант','Консультантов',8);
/*!40000 ALTER TABLE `sotrudniks` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-15 17:15:01
