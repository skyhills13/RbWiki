DROP TABLE IF EXISTS USERS; 
 
CREATE TABLE USERS ( 
 userId varchar(12) NOT NULL, 
 password varchar(12) NOT NULL, 
 name varchar(20) NOT NULL, 
 email varchar(50), 
 
 PRIMARY KEY (userId) 
); 
 
INSERT INTO USERS VALUES('1111', '111111', '일일일', '111@111.com');
