CREATE DATABASE rubrica;

USE rubrica;

CREATE TABLE utenti (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `utenti_unique` (`username`)
);

CREATE TABLE `persone` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `cognome` varchar(100) NOT NULL,
  `indirizzo` varchar(100) NOT NULL,
  `numero` varchar(15) NOT NULL,
  `eta` int NOT NULL,
  `owner` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `persone_utenti_FK` (`owner`),
  CONSTRAINT `persone_utenti_FK` FOREIGN KEY (`owner`) REFERENCES `utenti` (`id`)
);


