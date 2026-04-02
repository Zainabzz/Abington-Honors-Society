/*
* @author aram
*/
CREATE TABLE HonorStudent (  
    psuID int PRIMARY KEY,
    firstName VARCHAR(30) NOT NULL,
    lastName VARCHAR(30) NOT NULL,
    psuEmail VARCHAR(20) NOT NULL,
    studentPassword VARCHAR(30) NOT NULL,
    honorsCredits DECIMAL(4,1) NOT NULL,
    advisorName VARCHAR(30) NOT NULL,
    advisorEmail VARCHAR(20) NOT NULL
);