DROP DATABASE IF EXISTS spring_db;
CREATE DATABASE spring_db CHARACTER SET utf8 COLLATE utf8_general_ci;
USE spring_db;

DROP TABLE IF EXISTS child;
DROP TABLE IF EXISTS father;
DROP TABLE IF EXISTS family;

CREATE TABLE family (
familyId INT(11) NOT NULL AUTO_INCREMENT,
PRIMARY KEY (familyId)
) AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE child (
childId INT(11) NOT NULL AUTO_INCREMENT,
childIdfamily INT(11) NULL,
firstName VARCHAR(50) NULL,
secondName VARCHAR(50) NULL,
pesel VARCHAR(11) NULL,
sex VARCHAR(1) NULL,
birthDate DATE NULL,
PRIMARY KEY (childId),
KEY child_ibfk_1 (childIdfamily),
CONSTRAINT child_ibfk_1 FOREIGN KEY (childIdfamily) REFERENCES family (familyId)
) AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE father (
fatherId INT(11) NOT NULL AUTO_INCREMENT,
fatherIdfamily INT(11) NOT NULL,
firstName VARCHAR(50) NULL,
secondName VARCHAR(50) NULL,
pesel VARCHAR(11) NULL,
birthDate DATE NULL,
PRIMARY KEY (fatherIdfamily),
KEY(fatherId),
CONSTRAINT fathers_ibfk_1 FOREIGN KEY (fatherIdfamily) REFERENCES family (familyId)
) AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;