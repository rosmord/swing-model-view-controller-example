/*
 * First version of the database.
 */


 /** A student, with an id, a surname, a name and a grade. */

CREATE TABLE student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    prom VARCHAR(255) NOT NULL,    
    grade FLOAT NOT NULL
);

/* Create a few students. */

INSERT INTO student (firstname, lastname, prom, grade) VALUES
    ('Alan', 'Turing', '2024', 18.5),
    ('Ada', 'Lovelace', '2024', 19.0),
    ('Grace', 'Hopper', '2024', 17.5),
    ('Edsger', 'Dijkstra', '2025', 18.0),
    ('Donald', 'Knuth', '2025', 19.5),
    ('Tim', 'Berners-Lee', '2025', 18.5),
    ('Linus', 'Torvalds', '2026', 17.0),
    ('Margaret', 'Hamilton', '2026', 19.0),
    ('Dennis', 'Ritchie', '2026', 18.0);
