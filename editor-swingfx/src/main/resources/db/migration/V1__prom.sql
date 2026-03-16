CREATE TABLE promotion (
    prom VARCHAR(255) primary key NOT NULL    
);


/* Insert all known promotions taken from a select on  students. */

INSERT INTO promotion (prom) SELECT DISTINCT prom FROM student;

