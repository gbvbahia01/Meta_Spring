/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Guilherme
 * Created: Oct 17, 2016
 */

--Postgres SQL
CREATE TABLE "DEPARTMENT" (
  "ID" bigserial NOT NULL,
  "NAME" varchar(45) DEFAULT NULL,
   "DESCRIPTION" varchar(45) DEFAULT NULL,
  PRIMARY KEY ("ID")
) WITH (
  OIDS=FALSE
);
ALTER TABLE "DEPARTMENT"
  OWNER TO postgres;

  CREATE TABLE "USER" (
  "ID" bigserial NOT NULL,
  "NAME" varchar(45) DEFAULT NULL,
  "DESCRIPTION" varchar(45) DEFAULT NULL,
  "DEPT_ID" bigint DEFAULT NULL,
  PRIMARY KEY ("ID"),
  foreign key("DEPT_ID") references "DEPARTMENT"("ID")
) WITH (
  OIDS=FALSE
);
ALTER TABLE "USER"
  OWNER TO postgres;

CREATE TABLE "PERMISSION" (
  "ID" bigserial NOT NULL,
  "NAME" varchar(45) DEFAULT NULL,
  "DESCRIPTION" varchar(45) DEFAULT NULL,
  PRIMARY KEY ("ID")
) WITH (
  OIDS=FALSE
);
ALTER TABLE "PERMISSION"
  OWNER TO postgres;

CREATE TABLE "USER_PERMISSION" (
  "ID" bigserial NOT NULL,
  "USER_ID" bigint DEFAULT NULL,
  "PERM_ID" bigint DEFAULT NULL,
  PRIMARY KEY ("ID"),
  foreign key("USER_ID") references "USER"("ID"),
  foreign key("PERM_ID") references "PERMISSION"("ID")
)WITH (
  OIDS=FALSE
);
ALTER TABLE "USER_PERMISSION"
  OWNER TO postgres;


-- MYSQL
CREATE TABLE `DEPARTMENT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) DEFAULT NULL,
   `DESCRIPTION` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `USER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) DEFAULT NULL,
  `DESCRIPTION` varchar(45) DEFAULT NULL,
  `DEPT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  foreign key(`DEPT_ID`) references DEPARTMENT(`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `PERMISSION` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) DEFAULT NULL,
`DESCRIPTION` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `USER_PERMISSION` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `PERM_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  foreign key(`USER_ID`) references USER(`ID`),
  foreign key(`PERM_ID`) references PERMISSION(`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;