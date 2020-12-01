CREATE TABLE cars (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    production_year VARCHAR(4),
    model           VARCHAR(255),
    manufacturer    VARCHAR(255)
);

INSERT INTO cars VALUES (1, "1989", "126p", "Fiat");
INSERT INTO cars VALUES (2, "2006", "Fusion", "Ford");
INSERT INTO cars VALUES (3, "2019", "XC60", "Volvo");