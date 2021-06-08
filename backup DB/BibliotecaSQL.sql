-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: biblioteca
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  `data_nascimento` date NOT NULL,
  `email` varchar(40) NOT NULL,
  `cpf` char(14) NOT NULL,
  `endereco` varchar(50) NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `quantidade_livros` int DEFAULT (0),
  `liberado` tinyint(1) NOT NULL DEFAULT (true),
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Rafael Rodrigues da Silva','1994-10-27','rafael@hotmail.com','156.245.897-85','Rua Bonito de Santa Fé, n°144','(78)5 4854-2154',1,1),(2,'Rebeca Santos Cavalcanti','2008-08-15','rebecasantos22@gmail.com','412.354.778-95','BR-101 km42','(81)5 4654-8798',2,1),(4,'Alessandra Lima de Oliveira','1992-02-23','alessandra@gmail.com','562.325.658-98','Rua dos alfineteiros, n°14','(85)4 5412-1545',0,1),(5,'Robson Silva Castro da Silva','1986-10-25','robsonsilva@gmail.com','654.543.216-54','Rua das neves, n°28','(81)5 2623-2355',0,1),(6,'Letícia Melo Lisboa','1986-11-06','lelele@hotmail.com','654.321.564-68','Rua Lancelot, n°142','(82)4 5698-7878',1,1),(8,'Fábio Henrique Malheiros','1956-10-30','fabio.henrique@hotmail.com','356.254.587-45','Av Bola cheia n° 45A','(96)9 5874-5874',0,1),(9,'Pedro Martins Rocha','1991-01-23','pedro.rocha@gmail.com','652.326.541-25','Rua Almanaque S/N','(89)5 6547-8545',2,1),(14,'Fábio Lins Tarso','1996-05-21','linsfabio@gmail.com','652.326.542-51','Rua Alforjas n°50','(81)5 2525-4256',1,1),(15,'Danilo Costa Júnior','2008-09-21','danilo@gmail.com','326.545.612-35','Av norte, n°2344','(84)5 4513-2165',0,1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emprestimo`
--

DROP TABLE IF EXISTS `emprestimo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emprestimo` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int NOT NULL,
  `codigo_livro` int NOT NULL,
  `data_emprestimo` date NOT NULL,
  `data_prazo_entrega` date NOT NULL,
  `aberto` tinyint(1) DEFAULT (1),
  `data_entrega` date DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `id_cliente` (`id_cliente`),
  KEY `codigo_livro` (`codigo_livro`),
  CONSTRAINT `emprestimo_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `emprestimo_ibfk_2` FOREIGN KEY (`codigo_livro`) REFERENCES `livro` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emprestimo`
--

LOCK TABLES `emprestimo` WRITE;
/*!40000 ALTER TABLE `emprestimo` DISABLE KEYS */;
INSERT INTO `emprestimo` VALUES (1,1,1026,'2021-05-20','2021-05-25',1,NULL),(3,6,1012,'2021-05-23','2021-05-28',0,'2021-06-02'),(5,6,1014,'2021-06-01','2021-06-10',1,NULL),(8,4,1021,'2021-06-02','2021-05-28',0,'2021-06-02'),(9,1,1027,'2021-06-02','2021-06-07',0,'2021-06-03'),(10,6,1020,'2021-06-02','2021-06-07',0,'2021-06-02'),(11,6,1029,'2021-06-02','2021-06-07',0,'2021-06-02'),(12,8,1003,'2021-06-02','2021-06-07',0,'2021-06-02'),(13,2,1016,'2021-06-02','2021-06-07',1,NULL),(14,1,1018,'2021-06-02','2021-06-07',0,'2021-06-02'),(15,2,1020,'2021-06-02','2021-06-07',1,NULL),(18,1,1019,'2021-06-03','2021-06-08',0,'2021-06-03'),(19,1,1022,'2021-06-03','2021-06-08',0,'2021-06-03'),(20,9,1000,'2021-06-04','2021-06-09',1,NULL),(21,9,1012,'2021-06-04','2021-06-09',1,NULL),(22,14,1019,'2021-06-04','2021-06-09',1,NULL);
/*!40000 ALTER TABLE `emprestimo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livro`
--

DROP TABLE IF EXISTS `livro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livro` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(60) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `genero` varchar(20) NOT NULL,
  `editora` varchar(25) NOT NULL,
  `edicao` varchar(20) DEFAULT (_utf8mb4' '),
  `anotacoes` text DEFAULT (_utf8mb4' '),
  `alugavel` tinyint(1) NOT NULL,
  `disponivel` tinyint(1) NOT NULL,
  `restricao_etaria` tinyint(1) NOT NULL DEFAULT (0),
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=1031 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livro`
--

LOCK TABLES `livro` WRITE;
/*!40000 ALTER TABLE `livro` DISABLE KEYS */;
INSERT INTO `livro` VALUES (1000,'Dom Casmurro','Assis, Machado','Conto','Editora Rocco','8° edição, 2010','',1,0,1),(1001,'Genealogia da moral','Nietzsche, Friedrich','Ensaio','Editora Atica','3° edição','',0,0,1),(1002,'Crepúsculo dos ídolos','Nietzsche, Friedrich','ENSAIO','Editora Record','7° edição, 2004','',0,0,0),(1003,'O homem que calculava','Tahan, Malba','AVENTURA','Moderna','','',1,1,0),(1004,'1984','George Orwell','FICCÃO','Companhia das letras','9° edição','',0,0,0),(1005,'Dom Quixote de la Mancha','Cervantes, Miguel de','CONTO','Montecristo editora','','',1,1,0),(1006,'Dom Quixote de la Mancha','Cervantes, Miguel de','CONTO','Montecristo editora','','',0,0,0),(1007,'O Pequeno Príncipe','Saint-Exupéry, Antoine de ','LITERATURA_INFANTIL','Autêntica','','',1,1,0),(1008,'O Bandolim do Capitão Corelli','Bernières, Louis de ','ROMANCE','Record','6° edição','',0,0,0),(1009,'O Conde de Monte Cristo','Dumas, Alexandre','ROMANCE','LeBooks','','',1,1,0),(1010,'Cem Anos de Solidão','Márquez, Gabriel García','DRAMA','Record','','',0,0,0),(1011,'Cem Anos de Solidão','Márquez, Gabriel García','Drama','Record','','',1,1,1),(1012,'Grande Sertão: Veredas','Rosa, João Guimarães','CLÁSSICO','Companhia das letras','','',1,0,0),(1013,'A Hora da Estrela','Lispector, Clarice','LITERATURA_NACIONAL','Editora Rocco','','',1,1,0),(1014,'Hamlet','Shakespeare, William','DRAMA','L&PM pocket','4° edição','Tradução de Millôr Fernandes',1,0,0),(1015,'Hamlet','Shakespeare, William','DRAMA','L&PM pocket','4° edição','Tradução de Millôr Fernandes',0,0,0),(1016,'O Banquete','Platão','CLÁSSICO','L&PM pocket','','',1,0,0),(1017,'Crime e Castigo','Dostoiévski, Fiódor','Clássico','Record','2° edição, 2012','',0,0,0),(1018,'O Retrato de Dorian Gray','Wilde, Oscar','DRAMA','Martin Claret','','',1,1,0),(1019,'Harry Potter e a pedra filosofal','Rowling, J.K','AVENTURA','Atica','','',1,0,0),(1020,'Harry Potter e a câmara secreta','Rowling, J.K','AVENTURA','Atica','','',1,0,0),(1021,'Harry Potter e o cálice de fogo','Rowling, J.K','AVENTURA','Atica','','',1,1,0),(1022,'Harry Potter e as relíquias da morte','Rowling, J.K','AVENTURA','Atica','','',1,1,0),(1023,'Harry Potter e o príncipe mestiço','Rowling, J.K','AVENTURA','Atica','','',1,1,0),(1024,'O Pequeno Príncipe','Saint-Exupéry, Antoine de ','LITERATURA_INFANTIL','Record','','',0,0,0),(1025,'O Banquete','Platão','CLÁSSICO','L&PM pocket','','',0,0,0),(1026,'Genealogia da moral','Nietzsche, Friedrich','Ensaio','Editora Atica','3° edição','',1,0,1),(1027,'A cabana','Young, William P.','Drama','Editora Record','2° edição','',1,1,0),(1029,'Os sertões','da Cunha, Euclides','Literatura nacional','Atica','','',1,1,0),(1030,'Cem Anos de Solidão','Márquez, Gabriel García','Drama','Record','','',1,1,1);
/*!40000 ALTER TABLE `livro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro`
--

DROP TABLE IF EXISTS `registro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registro` (
  `usuario` varchar(20) NOT NULL,
  `descricao` varchar(100) NOT NULL,
  `data` datetime NOT NULL,
  KEY `usuario` (`usuario`),
  CONSTRAINT `registro_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro`
--

LOCK TABLES `registro` WRITE;
/*!40000 ALTER TABLE `registro` DISABLE KEYS */;
INSERT INTO `registro` VALUES ('RafaRodrigues','Cadastro do cliente Fábio Lins Moura no sistema','2021-05-31 08:36:12'),('RafaRodrigues','Alteração dos dados do cliente Fábio Lins Moura ID: 14','2021-05-31 10:04:37'),('admin','Alteração dos dados do livro Dom Casmurro Código: 1000','2021-05-31 10:52:03'),('admin','Alteração dos dados do livro Crime e Castigo Código: 1017','2021-05-31 11:45:07'),('RafaRodrigues','Alteração dos dados do cliente Letícia Melo ID: 6','2021-05-31 12:18:12'),('RafaRodrigues','Alteração dos dados do livro A cabana Código: 1027','2021-05-31 13:03:21'),('RafaRodrigues','Alteração dos dados do livro Cem Anos de Solidão Código: 1011','2021-06-01 11:54:04'),('RafaRodrigues','Empréstimo do livro: O homem que calculava para o Cliente: Fábio Henrique Malheiros','2021-06-02 17:54:10'),('RafaRodrigues','Empréstimo do livro: O Banquete para o Cliente: Rebeca Santos Cavalcanti','2021-06-02 18:04:25'),('RafaRodrigues','Empréstimo do livro: O Retrato de Dorian Gray para o Cliente: Rafael Rodrigues da Silva','2021-06-02 18:04:35'),('admin','Alteração dos dados do cliente Robson Silva Castro ID: 5','2021-06-02 19:19:36'),('admin','Empréstimo do livro: Harry Potter e a câmara secreta para o Cliente: Rebeca Santos Cavalcanti','2021-06-02 19:21:40'),('admin','Devolução do livro: O Retrato de Dorian Gray do Cliente: Rafael Rodrigues da Silva','2021-06-02 19:23:04'),('admin','Devolução do livro: Grande Sertão: Veredas do Cliente: Letícia Melo Lisboa','2021-06-02 19:24:19'),('admin','Devolução do livro: O homem que calculava do Cliente: Fábio Henrique Malheiros','2021-06-02 19:24:35'),('admin','Impressão de relatório de clientes','2021-06-02 19:25:25'),('DouglasBraz','Empréstimo do livro: Harry Potter e a pedra filosofal para o Cliente: Rafael Rodrigues da Silva','2021-06-03 13:35:29'),('DouglasBraz','Impressão de relatório de clientes','2021-06-03 13:35:58'),('RafaRodrigues','Empréstimo do livro: Harry Potter e as relíquias da morte para o Cliente: Rafael Rodrigues da Silva','2021-06-03 13:56:20'),('RafaRodrigues','Devolução do livro: Harry Potter e as relíquias da morte do Cliente: Rafael Rodrigues da Silva','2021-06-03 13:56:41'),('RafaRodrigues','Devolução do livro: A cabana do Cliente: Rafael Rodrigues da Silva','2021-06-03 16:14:11'),('RudimacyAllen','Impressão de relatório de clientes','2021-06-03 19:00:08'),('RudimacyAllen','Alteração dos dados do cliente Rafael Rodrigues da Silva ID: 1','2021-06-04 14:57:24'),('RudimacyAllen','Cadastro do cliente Danilo Costa Júnior no sistema','2021-06-04 14:59:01'),('RafaRodrigues','Impressão de relatório de clientes','2021-06-04 21:12:18'),('RafaRodrigues','Impressão de relatório de clientes','2021-06-04 21:12:24'),('RafaRodrigues','Empréstimo do livro: Dom Casmurro para o Cliente: Pedro Martins Rocha','2021-06-04 21:13:00'),('RafaRodrigues','Empréstimo do livro: Grande Sertão: Veredas para o Cliente: Pedro Martins Rocha','2021-06-04 21:13:52'),('RafaRodrigues','Empréstimo do livro: Harry Potter e a pedra filosofal para o Cliente: Fábio Lins Tarso','2021-06-04 21:14:18'),('RafaRodrigues','Cadastro do livro Cem Anos de Solidão no sistema','2021-06-04 21:15:14');
/*!40000 ALTER TABLE `registro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `usuario` varchar(20) NOT NULL,
  `senha` varchar(30) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `data_nascimento` date NOT NULL,
  PRIMARY KEY (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('admin','123admin456','admin','2000-01-01'),('DouglasBraz','rafa123','Douglas Bráz da Silva','1995-02-23'),('FabricioQueiroz','rafa123','Fabrício Queiroz Moura','1998-03-02'),('RafaRodrigues','rafa123','Rafael Rodrigues da Silva','1994-10-27'),('RudimacyAllen','rafa123','Rudimacy Allen','1998-06-21');
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

-- Dump completed on 2021-06-04 21:54:41
