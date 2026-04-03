/*
* @author aram
*/
CREATE TABLE Admin (  
    adminID int PRIMARY KEY,
    adminEmail VARCHAR(20) NOT NULL,
    adminPassword VARCHAR(30) NOT NULL,
    adminName VARCHAR(30) NOT NULL
);
