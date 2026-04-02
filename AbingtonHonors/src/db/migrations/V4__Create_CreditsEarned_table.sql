/*
* @author aram
*/
CREATE TABLE CreditsEarned (  
    creditsEarnedID int PRIMARY KEY,
    creditsEarned DECIMAL(4,1) NOT NULL,
    creditSource VARCHAR(255) NOT NULL,
    psuID int NOT NULL,
    validity boolean,
    validityComment VARCHAR(255),
    FOREIGN KEY (psuid) REFERENCES HonorStudent(psuid)
);
