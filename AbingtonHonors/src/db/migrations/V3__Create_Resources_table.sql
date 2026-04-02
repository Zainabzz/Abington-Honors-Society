/*
* @author aram
*/
CREATE TABLE Resources (  
    fileID int PRIMARY KEY,
    fileName VARCHAR(255) NOT NULL,
    fileData BLOB NOT NULL,
    viewer int NOT NULL
);