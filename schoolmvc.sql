-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: localhost    Database: schoolmvc
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.19.04.1

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
-- Table structure for table `Class`
--

DROP TABLE IF EXISTS `Class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Class` (
  `level` int(11) NOT NULL,
  PRIMARY KEY (`level`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Class`
--

LOCK TABLES `Class` WRITE;
/*!40000 ALTER TABLE `Class` DISABLE KEYS */;
INSERT INTO `Class` VALUES (1),(2),(3),(4),(5),(6),(7),(8);
/*!40000 ALTER TABLE `Class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Course`
--

DROP TABLE IF EXISTS `Course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Course` (
  `courseid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `teacherid` int(11) DEFAULT NULL,
  PRIMARY KEY (`courseid`),
  KEY `level` (`level`),
  KEY `teacherid` (`teacherid`),
  CONSTRAINT `Course_ibfk_1` FOREIGN KEY (`level`) REFERENCES `Class` (`level`),
  CONSTRAINT `Course_ibfk_2` FOREIGN KEY (`teacherid`) REFERENCES `Teacher` (`teacherid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course`
--

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` VALUES (1,'English',1,8),(2,'Hindi',1,13),(3,'Mathematics',8,8),(4,'Science',5,15),(5,'Science',6,15),(6,'Social Science',5,15),(7,'Physics',8,15);
/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CoursePage`
--

DROP TABLE IF EXISTS `CoursePage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CoursePage` (
  `cpid` int(11) NOT NULL AUTO_INCREMENT,
  `courseid` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`cpid`),
  KEY `courseid` (`courseid`),
  CONSTRAINT `CoursePage_ibfk_1` FOREIGN KEY (`courseid`) REFERENCES `Course` (`courseid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CoursePage`
--

LOCK TABLES `CoursePage` WRITE;
/*!40000 ALTER TABLE `CoursePage` DISABLE KEYS */;
INSERT INTO `CoursePage` VALUES (1,2,2019),(2,2,2020),(4,6,2019),(5,7,2020);
/*!40000 ALTER TABLE `CoursePage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Employee` (
  `empid` int(11) NOT NULL,
  `salary` int(11) DEFAULT NULL,
  PRIMARY KEY (`empid`),
  CONSTRAINT `Employee_ibfk_1` FOREIGN KEY (`empid`) REFERENCES `User` (`userid`),
  CONSTRAINT `Employee_ibfk_2` FOREIGN KEY (`empid`) REFERENCES `User` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (1,10000),(2,17000),(4,NULL),(5,NULL),(6,NULL),(7,NULL),(8,NULL),(9,NULL),(10,NULL),(13,NULL),(14,NULL),(15,50000),(36,12345);
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee_Role`
--

DROP TABLE IF EXISTS `Employee_Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Employee_Role` (
  `empid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  KEY `empid` (`empid`),
  KEY `roleid` (`roleid`),
  CONSTRAINT `Employee_Role_ibfk_1` FOREIGN KEY (`empid`) REFERENCES `Employee` (`empid`),
  CONSTRAINT `Employee_Role_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `Role` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee_Role`
--

LOCK TABLES `Employee_Role` WRITE;
/*!40000 ALTER TABLE `Employee_Role` DISABLE KEYS */;
INSERT INTO `Employee_Role` VALUES (2,2),(2,3),(2,5),(4,3),(4,4),(5,2),(5,3),(5,4),(6,1),(8,1),(8,2),(8,3),(8,4),(8,5),(10,1),(10,2),(13,1),(14,1),(15,1),(36,2),(36,5);
/*!40000 ALTER TABLE `Employee_Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Enrollment`
--

DROP TABLE IF EXISTS `Enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Enrollment` (
  `enrollid` int(11) NOT NULL AUTO_INCREMENT,
  `studentid` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `status` enum('ONGOING','PASSED','FAILED') DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`enrollid`),
  KEY `studentid` (`studentid`),
  KEY `level` (`level`),
  CONSTRAINT `Enrollment_ibfk_1` FOREIGN KEY (`studentid`) REFERENCES `Student` (`studentid`),
  CONSTRAINT `Enrollment_ibfk_2` FOREIGN KEY (`level`) REFERENCES `Class` (`level`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Enrollment`
--

LOCK TABLES `Enrollment` WRITE;
/*!40000 ALTER TABLE `Enrollment` DISABLE KEYS */;
INSERT INTO `Enrollment` VALUES (1,35,8,'ONGOING',NULL),(2,34,8,'ONGOING',NULL);
/*!40000 ALTER TABLE `Enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Expense`
--

DROP TABLE IF EXISTS `Expense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Expense` (
  `expenseid` int(11) NOT NULL AUTO_INCREMENT,
  `cost` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `manager` int(11) DEFAULT NULL,
  PRIMARY KEY (`expenseid`),
  KEY `manager` (`manager`),
  CONSTRAINT `Expense_ibfk_1` FOREIGN KEY (`manager`) REFERENCES `Employee` (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Expense`
--

LOCK TABLES `Expense` WRITE;
/*!40000 ALTER TABLE `Expense` DISABLE KEYS */;
/*!40000 ALTER TABLE `Expense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Expense_Item`
--

DROP TABLE IF EXISTS `Expense_Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Expense_Item` (
  `quantity` int(11) DEFAULT NULL,
  `expenseid` int(11) DEFAULT NULL,
  `itemid` int(11) DEFAULT NULL,
  KEY `expenseid` (`expenseid`),
  CONSTRAINT `Expense_Item_ibfk_1` FOREIGN KEY (`expenseid`) REFERENCES `Expense` (`expenseid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Expense_Item`
--

LOCK TABLES `Expense_Item` WRITE;
/*!40000 ALTER TABLE `Expense_Item` DISABLE KEYS */;
/*!40000 ALTER TABLE `Expense_Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Fee`
--

DROP TABLE IF EXISTS `Fee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Fee` (
  `feeid` int(11) NOT NULL AUTO_INCREMENT,
  `studentid` int(11) DEFAULT NULL,
  `manager` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `month` enum('January','Februrary','March','April','May','June','July','August','September','October','November','December') DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`feeid`),
  KEY `studentid` (`studentid`),
  KEY `manager` (`manager`),
  CONSTRAINT `Fee_ibfk_1` FOREIGN KEY (`studentid`) REFERENCES `Student` (`studentid`),
  CONSTRAINT `Fee_ibfk_2` FOREIGN KEY (`manager`) REFERENCES `Employee` (`empid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fee`
--

LOCK TABLES `Fee` WRITE;
/*!40000 ALTER TABLE `Fee` DISABLE KEYS */;
INSERT INTO `Fee` VALUES (1,35,1,1550,'November',2019,'2019-10-14');
/*!40000 ALTER TABLE `Fee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FeeChart`
--

DROP TABLE IF EXISTS `FeeChart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FeeChart` (
  `level` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  KEY `level` (`level`),
  CONSTRAINT `FeeChart_ibfk_1` FOREIGN KEY (`level`) REFERENCES `Class` (`level`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FeeChart`
--

LOCK TABLES `FeeChart` WRITE;
/*!40000 ALTER TABLE `FeeChart` DISABLE KEYS */;
INSERT INTO `FeeChart` VALUES (1,500),(2,550),(3,600),(4,700),(5,850),(6,950),(7,1050),(8,1550);
/*!40000 ALTER TABLE `FeeChart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Item` (
  `itemid` int(11) NOT NULL AUTO_INCREMENT,
  `itemname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
/*!40000 ALTER TABLE `Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Notification`
--

DROP TABLE IF EXISTS `Notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Notification` (
  `noteid` int(11) NOT NULL AUTO_INCREMENT,
  `cpid` int(11) DEFAULT NULL,
  `text` varchar(1024) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`noteid`),
  KEY `cpid` (`cpid`),
  CONSTRAINT `Notification_ibfk_1` FOREIGN KEY (`cpid`) REFERENCES `CoursePage` (`cpid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Notification`
--

LOCK TABLES `Notification` WRITE;
/*!40000 ALTER TABLE `Notification` DISABLE KEYS */;
INSERT INTO `Notification` VALUES (4,2,'Hi','2019-10-13'),(5,2,'Assignment 1','2019-10-13'),(6,2,'Never cheat\r\n','2019-10-13'),(7,2,'Venkat has arrived.','2019-10-13'),(8,5,'Welcome to the new session!','2019-10-14');
/*!40000 ALTER TABLE `Notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Result`
--

DROP TABLE IF EXISTS `Result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Result` (
  `enrollid` int(11) DEFAULT NULL,
  `grade` enum('A1','A2','B1','B2','C1','C2','D1','D2','F') DEFAULT NULL,
  `courseid` int(11) DEFAULT NULL,
  KEY `enrollid` (`enrollid`),
  KEY `courseid` (`courseid`),
  CONSTRAINT `Result_ibfk_1` FOREIGN KEY (`enrollid`) REFERENCES `Enrollment` (`enrollid`),
  CONSTRAINT `Result_ibfk_2` FOREIGN KEY (`courseid`) REFERENCES `Course` (`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Result`
--

LOCK TABLES `Result` WRITE;
/*!40000 ALTER TABLE `Result` DISABLE KEYS */;
INSERT INTO `Result` VALUES (1,'A1',7),(2,'B2',7),(1,'A1',7),(2,'A1',7);
/*!40000 ALTER TABLE `Result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role` (
  `roleid` int(11) NOT NULL,
  `rolename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'TEACHER'),(2,'USER_MANAGER'),(3,'ACCOUNT_MANAGER'),(4,'EXPENSE_MANAGER'),(5,'COURSE_MANAGER'),(6,'EVENT_MANAGER');
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Salary`
--

DROP TABLE IF EXISTS `Salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Salary` (
  `salaryid` int(11) NOT NULL AUTO_INCREMENT,
  `empid` int(11) DEFAULT NULL,
  `manager` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `month` enum('January','Februrary','March','April','May','June','July','August','September','October','November','December') DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`salaryid`),
  KEY `empid` (`empid`),
  KEY `manager` (`manager`),
  CONSTRAINT `Salary_ibfk_1` FOREIGN KEY (`empid`) REFERENCES `Employee` (`empid`),
  CONSTRAINT `Salary_ibfk_2` FOREIGN KEY (`manager`) REFERENCES `Employee` (`empid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Salary`
--

LOCK TABLES `Salary` WRITE;
/*!40000 ALTER TABLE `Salary` DISABLE KEYS */;
INSERT INTO `Salary` VALUES (12,1,1,10000,'November',2019,'2019-10-14'),(13,2,1,17000,'November',2019,'2019-10-14'),(14,4,1,0,'November',2019,'2019-10-14'),(15,5,1,0,'November',2019,'2019-10-14'),(16,6,1,0,'November',2019,'2019-10-14'),(17,7,1,0,'November',2019,'2019-10-14'),(18,8,1,0,'November',2019,'2019-10-14'),(19,9,1,0,'November',2019,'2019-10-14'),(20,10,1,0,'November',2019,'2019-10-14'),(21,13,1,0,'November',2019,'2019-10-14'),(22,14,1,0,'November',2019,'2019-10-14'),(23,15,1,50000,'November',2019,'2019-10-14');
/*!40000 ALTER TABLE `Salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student`
--

DROP TABLE IF EXISTS `Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student` (
  `studentid` int(11) NOT NULL,
  PRIMARY KEY (`studentid`),
  CONSTRAINT `Student_ibfk_1` FOREIGN KEY (`studentid`) REFERENCES `User` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student`
--

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;
INSERT INTO `Student` VALUES (34),(35);
/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Teacher`
--

DROP TABLE IF EXISTS `Teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Teacher` (
  `teacherid` int(11) NOT NULL,
  `qualifications` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teacherid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Teacher`
--

LOCK TABLES `Teacher` WRITE;
/*!40000 ALTER TABLE `Teacher` DISABLE KEYS */;
INSERT INTO `Teacher` VALUES (1,'PhD'),(8,'PhD'),(13,''),(14,''),(15,'B tech.');
/*!40000 ALTER TABLE `Teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(1000) DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `type` enum('ADMIN','EMPLOYEE','STUDENT') DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'piyushmaurya','$2a$10$6Emq/MgRKnrMUUTYyEHoO.aF2ocrnz57VLdaKLa6685dE0cjGL5em','Visvesaraya','Piyush Maurya','M','ADMIN'),(2,'nujan','$2a$10$Td7fW/jd/rdY1JbEzhb9Ye47qxVrhJCzpM9YmTylqH8pT3njn0Fky','India','Nujan','M','EMPLOYEE'),(3,'loading','$2a$10$TmOBqEHWZMNFBU14KZMgj./JbwUPd2kAxTRKwHR2Qy0KnBqVLWmHe','India','Loading','F','EMPLOYEE'),(4,'asish','$2a$10$wsYkoOOKedsL0TdWGua7Ne21k2ETTQOzlOj8Tbq9pDW4zel51dN5.','India','Pankaj','F','EMPLOYEE'),(5,'ASHISH','$2a$10$IfdWs9gGdbncztiNGb9lquc9FWiligREvut9EW4f2Gpf7FwPxDkfG','India','Pankaj','F','EMPLOYEE'),(6,'Ashish','$2a$10$oMLdJ0FUdlwRs1qe0lejp.5DqAZoJL.0hR3VvhEm6oAoCOBeqLYhe','India','Pankaj','F','EMPLOYEE'),(7,'piyush','$2a$10$8N0y0.s6iJRJBxCUgtqNAeSYxO8eneSQC92omWVZahc8I/vAI0MUy','India','Piyush Maury','M','STUDENT'),(8,'piyushmauryaa','$2a$10$cXU772CNUlXf7J.nJg7HzetOZTCadm0M8NFTgJUTHA1/tFNvRvBnG','India','Mayank Pathroliya','M','EMPLOYEE'),(9,'aaa','$2a$10$8Yz7YW1tDpUYS4P0CvyxFedgHzjThC9vGRbVXUiJKpSeX.kOHNVO2','India','AAA BBB','M','STUDENT'),(10,'xasa','$2a$10$iV8t4hu6sgoCyifIaX2NkuXuI7874Sd46pM9ff5OOY38wgUYTPxTK','India','Pankaj','M','EMPLOYEE'),(13,'nsvenkat','$2a$10$t61Y.LUPMcAZKcE3XsEoJ.4M349p7ZfPNsNz9u5rCcz6MBX9Aya.O','India','Narayana Shanmukha Venkat','M','EMPLOYEE'),(14,'nikhil','$2a$10$21D.zWQl5rpQ/vY0nnNDQub4nVF4wU3RLn7VuRHjVLKV.rFQPFXOy','India','Nikhil Kumar','M','EMPLOYEE'),(15,'ashishgupta','$2a$10$xYrwaCJu.fZlsOcCrqTkUeUwjq6MpOyoiUS2kyAk9WiTMMSbmKxMu','India','Ashish Gupta','M','EMPLOYEE'),(34,'nancy','$2a$10$vAvcSQ8SCi8.xMOniN79nuIN16lsMYd4uMGmzh5T3oVcp6CKUB8BW','India','Nancy','F','STUDENT'),(35,'frank','$2a$10$UJYlHBcHk7n6UEMe2UZXFOoQM4rGY0SZDQLDBAF8SdfwqqWBwBnTW','India','Frank','M','STUDENT'),(36,'hello','$2a$10$psqlsNs1WVpG6cxG7yLg5Off7Q7V2dvflgb1DADSitjqA1RgjHsgK','India','Hello','F','EMPLOYEE');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-14 16:43:18
